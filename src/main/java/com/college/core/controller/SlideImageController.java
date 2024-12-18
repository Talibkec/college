package com.college.core.controller;
import com.college.core.model.*;
import com.college.service.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
@Controller
public class SlideImageController {
    @Autowired
    ImageSlideService imageSlideService;

    @Autowired
    AllDocuments allDocuments;
    @Autowired
    DownloadService downloadService;
    @Autowired
    ResponsibilityDocService responsibilityDocService;
    @Autowired
    HolidayService holidayService;
    @Autowired
    GalleryImageService galleryImageService;
    private final Logger logger = LoggerFactory.getLogger(SlideImageController.class);
    @Autowired
    LabDocumentService labDocumentService;

    //private final String UPLOADED_FOLDER = getPath();
    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadslideimage", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
            @RequestParam("slideImage") MultipartFile uploadfile,
            @RequestParam("caption") String caption,
            @RequestParam("flag") int flag) {
        logger.debug("Single file upload!");
        String fileName = uploadfile.getOriginalFilename();
        String fileType = uploadfile.getContentType();
        if (uploadfile.isEmpty() || StringUtils.isEmpty(caption)) {
            String msg = "";
            if (uploadfile.isEmpty()) {
                msg = "Please select a file.";
            } else {
                msg = "Please give a proper caption";
            }
            return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

        try {
            String msg;
            BufferedImage image = ImageIO.read(uploadfile.getInputStream());
            int h = image.getHeight();
            int b = image.getWidth();
            logger.info("Image resolution is " + h + "  " + b);
            if ((flag == 0 && (b != 2000 || h != 550)) || (flag == 1 && (b != 1080 || h != 1920))){
                msg = "Kindly Resize Your image in " + (flag == 0 ? "2000 * 550" : "1080 * 1920") + " format";
                return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //fileName = UploadFileUtility.saveUploadedFiles(Arrays.asList(uploadfile), UPLOADED_FOLDER);
        saveImageSlide(uploadfile, caption, fileName, fileType, flag);
        //return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);


        return new ResponseEntity("Document uploaded successfully", new HttpHeaders(), HttpStatus.OK);

    }
    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadAICTEapproval", method = RequestMethod.POST)
    public ResponseEntity<?> uploadAICTEapproval(
            @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption) {
        logger.debug("Single aicte file upload!");
        String fileName = uploadfile.getOriginalFilename();
        String fileType = uploadfile.getContentType();
        if (uploadfile.isEmpty() || StringUtils.isEmpty(caption)) {
            String msg = "";
            if (uploadfile.isEmpty()) {
                msg = "Please select a file.";
            } else {
                msg = "Please provide a year";
            }
            return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }


        //fileName = UploadFileUtility.saveUploadedFiles(Arrays.asList(uploadfile), UPLOADED_FOLDER);
        saveAicteDocument(uploadfile, caption, fileName, fileType);
        //return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);


        return new ResponseEntity("Document uploaded successfully", new HttpHeaders(), HttpStatus.OK);

    }
    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadDownload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadDownload(
            @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption , Authentication authentication) {
        logger.debug("Single download file upload!");
        String fileName = uploadfile.getOriginalFilename();
        String fileType = uploadfile.getContentType();
        if (uploadfile.isEmpty() || StringUtils.isEmpty(caption)) {
            String msg = "";
            if (uploadfile.isEmpty()) {
                msg = "Please select a file.";
            } else {
                msg = "Please provide a caption";
            }
            return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }


        //fileName = UploadFileUtility.saveUploadedFiles(Arrays.asList(uploadfile), UPLOADED_FOLDER);
        saveDownloadFile(uploadfile, caption, fileName, fileType , authentication);
        //return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);


        return new ResponseEntity("Document uploaded successfully", new HttpHeaders(), HttpStatus.OK);

    }


    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadResponsibilityDoc", method = RequestMethod.POST)
    public ResponseEntity<?> uploadResponsibilityDoc(
            @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption , Authentication authentication) {
        logger.debug("Single responsibility file upload!");
        String fileName = uploadfile.getOriginalFilename();
        String fileType = uploadfile.getContentType();
        if (uploadfile.isEmpty() || StringUtils.isEmpty(caption)) {
            String msg = "";
            if (uploadfile.isEmpty()) {
                msg = "Please select a file.";
            } else {
                msg = "Please provide a session";
            }
            return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }


        //fileName = UploadFileUtility.saveUploadedFiles(Arrays.asList(uploadfile), UPLOADED_FOLDER);
        saveResponsibilityDoc(uploadfile, caption, fileName, fileType , authentication);
        //return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);


        return new ResponseEntity("Responsibility Doc uploaded successfully", new HttpHeaders(), HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadHoliday", method = RequestMethod.POST)
    public ResponseEntity<?> uploadHoliday(
            @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption , Authentication authentication) {
        logger.debug("Single responsibility file upload!");
        String fileName = uploadfile.getOriginalFilename();
        String fileType = uploadfile.getContentType();
        if (uploadfile.isEmpty() || StringUtils.isEmpty(caption)) {
            String msg = "";
            if (uploadfile.isEmpty()) {
                msg = "Please select a file.";
            } else {
                msg = "Please provide a year";
            }
            return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }


        //fileName = UploadFileUtility.saveUploadedFiles(Arrays.asList(uploadfile), UPLOADED_FOLDER);
        saveHolidayDoc(uploadfile, caption, fileName, fileType , authentication);
        //return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);


        return new ResponseEntity("Holiday Doc uploaded successfully", new HttpHeaders(), HttpStatus.OK);

    }


    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadLabDoc", method = RequestMethod.POST)
    public ResponseEntity<?> uploadLabDoc(
            @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption ,@RequestParam("deptId") Integer depId , Authentication authentication) {
        logger.debug("Single lab file upload!");
        String fileName = uploadfile.getOriginalFilename();
        String fileType = uploadfile.getContentType();
        if (uploadfile.isEmpty() || StringUtils.isEmpty(caption)) {
            String msg = "";
            if (uploadfile.isEmpty()) {
                msg = "Please select a file.";
            } else {
                msg = "Please provide a year";
            }
            return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }


        //fileName = UploadFileUtility.saveUploadedFiles(Arrays.asList(uploadfile), UPLOADED_FOLDER);
        saveLabDoc(uploadfile, caption , depId, fileName,  fileType , authentication);
        //return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);


        return new ResponseEntity("Lab Doc uploaded successfully", new HttpHeaders(), HttpStatus.OK);

    }
    private void saveLabDoc(MultipartFile uploadfile, String caption , Integer deptId, String fileName, String fileType , Authentication authentication) {
        if(!authentication.isAuthenticated()){
            return ;
        }
        LabDocumentDTO holidayDTO = new LabDocumentDTO();
        holidayDTO.setFileName(caption);
        holidayDTO.setDepId(deptId);
        String username = authentication.getName();
        holidayDTO.setUploadedBy(authentication.getName());
        try {
            holidayDTO.setUploadedFile(uploadfile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        holidayDTO.setFileType(FilenameUtils.getExtension(fileName));
        labDocumentService.saveDownloadBoard(holidayDTO);
    }


    private void saveHolidayDoc(MultipartFile uploadfile, String caption, String fileName, String fileType , Authentication authentication) {
        if(!authentication.isAuthenticated()){
            return ;
        }
        HolidayDTO holidayDTO = new HolidayDTO();
        holidayDTO.setFileName(caption);

        String username = authentication.getName();
        holidayDTO.setUploadedBy(authentication.getName());
        try {
            holidayDTO.setUploadedFile(uploadfile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        holidayDTO.setFileType(FilenameUtils.getExtension(fileName));
        holidayService.saveDownloadBoard(holidayDTO);
    }
    private void saveResponsibilityDoc(MultipartFile uploadfile, String caption, String fileName, String fileType , Authentication authentication) {
        if(!authentication.isAuthenticated()){
            return ;
        }
        ResponsibilityDocDTO responsibilityDocDTO = new ResponsibilityDocDTO();
        responsibilityDocDTO.setFileName(caption);

        String username = authentication.getName();
        responsibilityDocDTO.setUploadedBy(authentication.getName());
        try {
            responsibilityDocDTO.setUploadedFile(uploadfile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        responsibilityDocDTO.setFileType(FilenameUtils.getExtension(fileName));
        responsibilityDocService.saveDownloadBoard(responsibilityDocDTO);
    }
    private void saveDownloadFile(MultipartFile uploadfile, String caption, String fileName, String fileType , Authentication authentication) {
        if(!authentication.isAuthenticated()){
                return ;
        }
        DownloadDTO downloadDTO = new DownloadDTO();
        downloadDTO.setFileName(caption);

        String username = authentication.getName();
        downloadDTO.setUploadedBy(authentication.getName());
        try {
            downloadDTO.setUploadedFile(uploadfile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        downloadDTO.setFileType(FilenameUtils.getExtension(fileName));
        downloadService.saveDownloadBoard(downloadDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadgalleryimage", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFiles(
            @RequestParam("galleryImage") MultipartFile uploadfile, @RequestParam("caption") String caption) {
        logger.debug("Single file upload!");
        String fileName = uploadfile.getOriginalFilename();
        String fileType = uploadfile.getContentType();
        if (uploadfile.isEmpty() || StringUtils.isEmpty(caption)) {
            String msg = "";
            if (uploadfile.isEmpty()) {
                msg = "Please select a file.";
            } else {
                msg = "Please give a proper caption";
            }
            return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }


        //fileName = UploadFileUtility.saveUploadedFiles(Arrays.asList(uploadfile), UPLOADED_FOLDER);
        saveImageGallery(uploadfile, caption, fileName, fileType);


        String notice = "/wp-content/uploads/notice/" + fileName;
        notice = notice + "," + caption;
        notice = notice + "," + uploadfile;
        return new ResponseEntity(notice, new HttpHeaders(), HttpStatus.OK);

    }

    @RequestMapping(value = "/auth/uploadfile/uploadslideimage", method = RequestMethod.GET)
    public ModelAndView displaySlideImage() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadslideimage.jsp");
        return model;
    }

    private void saveImageSlide(MultipartFile uploadfile, String caption, String fileName, String fileType, int flag) {
        ImageSlideDTO imageSlideDTO = new ImageSlideDTO();
        imageSlideDTO.setCaption(caption);
        imageSlideDTO.setFileName(fileName);
        imageSlideDTO.setFileType(fileType);
        imageSlideDTO.setFlag(flag);
        try {
            imageSlideDTO.setImage(uploadfile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageSlideDTO.setFileType(FilenameUtils.getExtension(fileName));
        imageSlideService.saveImageSlide(imageSlideDTO);
    }
    private void saveAicteDocument(MultipartFile uploadfile, String caption, String fileName, String fileType) {
        AicteDocumentsDTO aicteDocumentsDTO = new AicteDocumentsDTO();
        aicteDocumentsDTO.setTitle(caption);
        aicteDocumentsDTO.setFileName(fileName);
        aicteDocumentsDTO.setFileType(fileType);
        try {
            aicteDocumentsDTO.setImage(uploadfile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        aicteDocumentsDTO.setFileType(FilenameUtils.getExtension(fileName));
        allDocuments.saveAicteDocument(aicteDocumentsDTO);
    }
        private void saveImageGallery(MultipartFile uploadfile, String caption, String fileName, String fileType) {
        GalleryImageDTO galleryImageDTO = new GalleryImageDTO();
        galleryImageDTO.setCaption(caption);
        galleryImageDTO.setFileName(fileName);
        galleryImageDTO.setFileType(fileType);
        galleryImageDTO.getCaption();
        try {
            galleryImageDTO.setImage(uploadfile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        galleryImageDTO.setFileType(FilenameUtils.getExtension(fileName));
        galleryImageService.saveImageSlide(galleryImageDTO);
    }
}


