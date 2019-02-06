package com.college;

import com.college.core.model.FacultyDocumentsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class UploadFileUtility {

    //save file
    final static Logger logger = LoggerFactory.getLogger(UploadFileUtility.class);
    public static String saveUploadedFiles(List<MultipartFile> files, String uploadFolder) {

        String fileName = null;
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //next pls
            }
            try {
            byte[] bytes = file.getBytes();
            fileName = file.getOriginalFilename();
            logger.info("Filel to upload is " + uploadFolder+ File.separatorChar+ fileName);

            Path path = Paths.get(uploadFolder+ File.separatorChar+ fileName);

                Files.write(path, bytes);
            } catch (IOException e) {
                logger.error("Could not upload the file, IOException has occured", e);
            }

        }

        return fileName;
    }

    public static void setFileType(List<FacultyDocumentsDTO> facultyDocuments) {
        for(FacultyDocumentsDTO fd : facultyDocuments){
            String fileName = fd.getUploadedFileName();
            Integer index = fileName.lastIndexOf(".");
            fd.setFileType(fileName.substring(index, fileName.length()));
        }
    }
}
