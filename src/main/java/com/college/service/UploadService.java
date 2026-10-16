package com.college.service;

import com.college.core.model.AicteDocumentsDTO;
import com.college.core.model.DownloadDTO;
import com.college.core.model.GalleryImageDTO;
import com.college.core.model.HolidayDTO;
import com.college.core.model.ImageSlideDTO;
import com.college.core.model.LabDocumentDTO;
import com.college.core.model.NoticeBoardDTO;
import com.college.core.model.ResponsibilityDocDTO;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Saving uploaded files: notices, slide and gallery images, and the downloadable documents (shared by the admin,
 * HOD and faculty upload pages). For documents the caption is stored as the file name, as before.
 */
@Service
public class UploadService {

    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    private final NoticeBoardService noticeBoardService;
    private final ImageSlideService imageSlideService;
    private final GalleryImageService galleryImageService;
    private final AllDocuments allDocuments;
    private final DownloadService downloadService;
    private final ResponsibilityDocService responsibilityDocService;
    private final HolidayService holidayService;
    private final LabDocumentService labDocumentService;

    public UploadService(NoticeBoardService noticeBoardService,
            ImageSlideService imageSlideService,
            GalleryImageService galleryImageService,
            AllDocuments allDocuments,
            DownloadService downloadService,
            ResponsibilityDocService responsibilityDocService,
            HolidayService holidayService,
            LabDocumentService labDocumentService) {
        this.noticeBoardService = noticeBoardService;
        this.imageSlideService = imageSlideService;
        this.galleryImageService = galleryImageService;
        this.allDocuments = allDocuments;
        this.downloadService = downloadService;
        this.responsibilityDocService = responsibilityDocService;
        this.holidayService = holidayService;
        this.labDocumentService = labDocumentService;
    }

    /**
     * Saves a notice. {@code link} may be {@code null} (the HOD form has no link field). {@code date} is
     * dd/MM/yyyy; empty means today, an unparseable date is stored as NULL.
     */
    public void saveNotice(String userName, String noticeHeader, String noticeType, String link, Boolean isScrollable,
                           MultipartFile uploadfile, String date) {
        String fileName = uploadfile.getOriginalFilename();
        NoticeBoardDTO noticeBoardDTO = new NoticeBoardDTO();
        noticeBoardDTO.setHeadLine(noticeHeader);
        noticeBoardDTO.setNoticeType(noticeType);
        noticeBoardDTO.setUploadedBy(userName);
        noticeBoardDTO.setDate(parseNoticeDate(date));
        noticeBoardDTO.setUploadedFileName(fileName);
        noticeBoardDTO.setLink(link);
        if (isScrollable) {
            noticeBoardDTO.setIsScrollable(1);
        }
        noticeBoardDTO.setNotice(readBytes(uploadfile, "notice file"));
        noticeBoardDTO.setFileType(FilenameUtils.getExtension(fileName));
        noticeBoardService.saveNoticeBoard(noticeBoardDTO);
    }

    /**
     * Parses dd/MM/yyyy like the original code: lenient (31/02 rolls over, trailing text is ignored), today's date
     * when empty, {@code null} when unparseable. A new formatter per call, because SimpleDateFormat is not
     * thread-safe (it used to be a shared static field).
     */
    static Date parseNoticeDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if (StringUtils.isEmpty(date)) {
                date = format.format(new Date());
            }
            return format.parse(date);
        } catch (ParseException e) {
            logger.error("Could not parse the date, Please contact to admin");
            return null;
        }
    }

    /**
     * Checks a slide image upload: returns the message for the user, or {@code null} if the upload is fine.
     * flag 0 (desktop) must be 2000x550, flag 1 (mobile) 1080x1920.
     */
    public String slideImageProblem(MultipartFile uploadfile, String caption, int flag) {
        if (uploadfile.isEmpty()) {
            return "Please select a file.";
        }
        if (StringUtils.isEmpty(caption)) {
            return "Please give a proper caption";
        }
        try {
            BufferedImage image = ImageIO.read(uploadfile.getInputStream());
            int h = image.getHeight();
            int b = image.getWidth();
            logger.info("Image resolution is " + h + "  " + b);
            if ((flag == 0 && (b != 2000 || h != 550)) || (flag == 1 && (b != 1080 || h != 1920))) {
                return "Kindly Resize Your image in " + (flag == 0 ? "2000 * 550" : "1080 * 1920") + " format";
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void saveSlideImage(MultipartFile uploadfile, String caption, int flag) {
        String fileName = uploadfile.getOriginalFilename();
        ImageSlideDTO imageSlideDTO = new ImageSlideDTO();
        imageSlideDTO.setCaption(caption);
        imageSlideDTO.setFileName(fileName);
        imageSlideDTO.setFlag(flag);
        imageSlideDTO.setImage(readBytes(uploadfile, "slide image"));
        imageSlideDTO.setFileType(FilenameUtils.getExtension(fileName));
        imageSlideService.saveImageSlide(imageSlideDTO);
    }

    /**
     * Checks a document/gallery upload: "Please select a file." for an empty file, {@code missingCaptionMessage}
     * for an empty caption, {@code null} if the upload is complete.
     */
    public static String documentUploadProblem(MultipartFile uploadfile, String caption, String missingCaptionMessage) {
        if (uploadfile.isEmpty()) {
            return "Please select a file.";
        }
        if (StringUtils.isEmpty(caption)) {
            return missingCaptionMessage;
        }
        return null;
    }

    public void saveGalleryImage(MultipartFile uploadfile, String caption) {
        String fileName = uploadfile.getOriginalFilename();
        GalleryImageDTO galleryImageDTO = new GalleryImageDTO();
        galleryImageDTO.setCaption(caption);
        galleryImageDTO.setFileName(fileName);
        galleryImageDTO.setImage(readBytes(uploadfile, "gallery image"));
        galleryImageDTO.setFileType(FilenameUtils.getExtension(fileName));
        galleryImageService.saveImageSlide(galleryImageDTO);
    }

    public void saveAicteDocument(MultipartFile uploadfile, String caption) {
        String fileName = uploadfile.getOriginalFilename();
        AicteDocumentsDTO aicteDocumentsDTO = new AicteDocumentsDTO();
        aicteDocumentsDTO.setTitle(caption);
        aicteDocumentsDTO.setFileName(fileName);
        aicteDocumentsDTO.setImage(readBytes(uploadfile, "AICTE document"));
        aicteDocumentsDTO.setFileType(FilenameUtils.getExtension(fileName));
        allDocuments.saveAicteDocument(aicteDocumentsDTO);
    }

    /** A document of the downloads page ({@code docType} "download") or the NBA page ("nba"). */
    public void saveDownload(MultipartFile uploadfile, String caption, String docType, String uploadedBy) {
        DownloadDTO downloadDTO = new DownloadDTO();
        downloadDTO.setFileName(caption);
        downloadDTO.setDocType(docType);
        downloadDTO.setUploadedBy(uploadedBy);
        downloadDTO.setUploadedFile(readBytes(uploadfile, docType + " document"));
        downloadDTO.setFileType(FilenameUtils.getExtension(uploadfile.getOriginalFilename()));
        downloadService.saveDownloadBoard(downloadDTO);
    }

    public void saveResponsibilityDoc(MultipartFile uploadfile, String caption, String uploadedBy) {
        ResponsibilityDocDTO dto = new ResponsibilityDocDTO();
        dto.setFileName(caption);
        dto.setUploadedBy(uploadedBy);
        dto.setUploadedFile(readBytes(uploadfile, "responsibility document"));
        dto.setFileType(FilenameUtils.getExtension(uploadfile.getOriginalFilename()));
        responsibilityDocService.saveDownloadBoard(dto);
    }

    public void saveHoliday(MultipartFile uploadfile, String caption, String uploadedBy) {
        HolidayDTO dto = new HolidayDTO();
        dto.setFileName(caption);
        dto.setUploadedBy(uploadedBy);
        dto.setUploadedFile(readBytes(uploadfile, "holiday document"));
        dto.setFileType(FilenameUtils.getExtension(uploadfile.getOriginalFilename()));
        holidayService.saveDownloadBoard(dto);
    }

    public void saveLabDoc(MultipartFile uploadfile, String caption, Integer deptId, String uploadedBy) {
        LabDocumentDTO dto = new LabDocumentDTO();
        dto.setFileName(caption);
        dto.setDepId(deptId);
        dto.setUploadedBy(uploadedBy);
        dto.setUploadedFile(readBytes(uploadfile, "lab document"));
        dto.setFileType(FilenameUtils.getExtension(uploadfile.getOriginalFilename()));
        labDocumentService.saveDownloadBoard(dto);
    }

    /** Content of an uploaded file; {@code null} (logged) if it cannot be read, as before. */
    public static byte[] readBytes(MultipartFile uploadfile, String what) {
        try {
            return uploadfile.getBytes();
        } catch (IOException e) {
            logger.error("Could not read the uploaded {} {}", what, uploadfile.getOriginalFilename(), e);
            return null;
        }
    }
}
