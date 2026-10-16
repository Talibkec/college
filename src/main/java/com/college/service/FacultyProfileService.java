package com.college.service;

import com.college.core.entity.User;
import com.college.core.model.FacultyDTO;
import com.college.core.model.FacultyDocumentsDTO;
import com.college.core.model.FacultyKeyPropValuesDTO;
import com.college.core.model.FacultyKeyPropsDTO;
import com.college.core.model.UserRoleDTO;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Faculty accounts and profiles: add faculty, edit profile, profile photo, documents, department list. */
@Service
public class FacultyProfileService {

    private static final Logger logger = LoggerFactory.getLogger(FacultyProfileService.class);

    /** Shown for faculty and staff without a photo (grey person icon). */
    private static final String DEFAULT_PHOTO = "data:image/png;base64,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";

    private final FacultyService facultyService;
    private final FacultyDocumentsService facultyDocumentsService;
    private final UserService userService;

    public FacultyProfileService(FacultyService facultyService, FacultyDocumentsService facultyDocumentsService,
                                 UserService userService) {
        this.facultyService = facultyService;
        this.facultyDocumentsService = facultyDocumentsService;
        this.userService = userService;
    }

    /**
     * Image URL for a faculty/staff photo: a data URL for png/jpg/jpeg photos, otherwise the default icon.
     */
    public static String photoDataUrl(byte[] photo, String fileType) {
        if (photo != null && "png".equalsIgnoreCase(fileType)) {
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(photo);
        } else if (photo != null && "jpg".equalsIgnoreCase(fileType)) {
            return "data:image/jpg;base64," + Base64.getEncoder().encodeToString(photo);
        } else if (photo != null && "jpeg".equalsIgnoreCase(fileType)) {
            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(photo);
        }
        return DEFAULT_PHOTO;
    }

    /**
     * Faculty of a department for the faculty list page: photos as data URLs, entries whose name contains "HOD"
     * dropped, the HOD (by name) moved to the top.
     */
    public List<FacultyDTO> getFacultyList(Long deptno, String hodName) {
        List<FacultyDTO> facultyList = facultyService.getFacultyByDeptNo(deptno);
        Iterator<FacultyDTO> iterator = facultyList.iterator();
        FacultyDTO hod = null;
        while (iterator.hasNext()) {
            FacultyDTO dto = iterator.next();
            dto.setImageURL(photoDataUrl(dto.getFacultyPhoto(), dto.getFileType()));
            if (dto.getFacultyName() != null && dto.getFacultyName().contains("HOD")) {
                iterator.remove();
            } else if (dto.getFacultyName() != null && dto.getFacultyName().equalsIgnoreCase(hodName)) {
                hod = dto;
                iterator.remove();
            }
        }
        if (hod != null) {
            facultyList.add(0, hod);
        }
        return facultyList;
    }

    /**
     * Creates a faculty account (user with role Faculty, password = user name) and the faculty row.
     * Returns the message shown on the page (error or success).
     */
    public String addFaculty(String facultyPersonalEmail, String facultyOfficialEmail, Long facultyMobNo,
                             String facultyName, Long deptId, String username) {
        String message = "";
        boolean error = false;
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setDepartmentId(deptId);

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher m = pattern.matcher(facultyOfficialEmail);
        if (!m.matches()) {
            error = true;
            message = " Invalid Official Email address entered";
        }
        if (!facultyOfficialEmail.substring(facultyOfficialEmail.indexOf("@") + 1).equals("keck.ac.in")) {
            error = true;
            message = " Official email doesn't belong to keck.ac.in ";
        }
        facultyDTO.setFacultyOfficialEmail(facultyOfficialEmail);
        facultyDTO.setFacultyPersonalEmail(facultyPersonalEmail);
        facultyDTO.setFacultyMobNo(facultyMobNo);
        facultyDTO.setFacultyName(facultyName);

        User user = userService.findByUsername(username);
        if ("".equals(username)) {
            message = "Faculty username can't be null";
            error = true;
        }
        if (user != null) {
            message = "Faculty already registered with given username :-  " + username;
            error = true;
        }
        if (!error) {
            message = "Faculty added successfully you can loging using username= " + username + " password :- " + username;
            User user2 = new User();
            user2.setUsername(username);
            user2.setPassword(username);
            user2.setRole("2");
            userService.save(user2);

            UserRoleDTO userRoleDTO = new UserRoleDTO();
            userRoleDTO.setRoleId(2L);
            userRoleDTO.setUserId(user2.getId());
            userService.saveUserRole(userRoleDTO);

            facultyDTO.setUser(user2);
            facultyService.saveFaculty(facultyDTO);
        }
        return message;
    }

    /**
     * Saves the edited profile: contact fields that are not empty, and the key properties of {@code submitted}
     * (id -9999 marks a new key property or value).
     */
    public void updateFacultyDetails(Long facultyId, String facultyPersonalEmail, String facultyOfficialEmail,
                                     Long facultyMobNo, String facultyName, FacultyDTO submitted) {
        FacultyDTO newFacultyDTO = new FacultyDTO();
        FacultyDTO oldFacultyDTO = facultyService.getFacultyById(facultyId);
        replaceKeyProperties(submitted, oldFacultyDTO);
        BeanUtils.copyProperties(oldFacultyDTO, newFacultyDTO);
        if (!StringUtils.isEmpty(facultyOfficialEmail)) {
            newFacultyDTO.setFacultyOfficialEmail(facultyOfficialEmail);
        }
        if (!StringUtils.isEmpty(facultyPersonalEmail)) {
            newFacultyDTO.setFacultyPersonalEmail(facultyPersonalEmail);
        }
        if (!StringUtils.isEmpty(facultyMobNo)) {
            newFacultyDTO.setFacultyMobNo(facultyMobNo);
        }
        if (!StringUtils.isEmpty(facultyName)) {
            newFacultyDTO.setFacultyName(facultyName);
        }
        facultyService.saveFaculty(newFacultyDTO);
    }

    private void replaceKeyProperties(FacultyDTO submitted, FacultyDTO oldFacultyDTO) {
        oldFacultyDTO.getFacultyKeyProps().clear();
        Iterator<FacultyKeyPropsDTO> keyIterator = submitted.getFacultyKeyProps().iterator();
        List<FacultyKeyPropsDTO> keyPropsDTOS = new ArrayList<>();
        while (keyIterator.hasNext()) {
            FacultyKeyPropsDTO keyDTO = keyIterator.next();
            if (!StringUtils.isEmpty(keyDTO.getKeyPropertyName())) {
                if (keyDTO.getKeyPropertyId() == -9999) {
                    keyDTO.setKeyPropertyId(null);
                }
                Iterator<FacultyKeyPropValuesDTO> valueIterator = keyDTO.getKeyPropVals().iterator();
                List<FacultyKeyPropValuesDTO> valDTOs = new ArrayList<>();
                while (valueIterator.hasNext()) {
                    FacultyKeyPropValuesDTO valueDTO = valueIterator.next();
                    if (valueDTO.getKeyPropValuesId() == -9999) {
                        valueDTO.setKeyPropValuesId(null);
                    }
                    valueDTO.setFacultyKeyProps(keyDTO);
                    valDTOs.add(valueDTO);
                    logger.debug("Key property value: {}", valueDTO.getKeyPropVal());
                }
                keyDTO.getKeyPropVals().addAll(valDTOs);
                keyDTO.setFaculty(oldFacultyDTO);
                keyPropsDTOS.add(keyDTO);
            }
        }
        oldFacultyDTO.getFacultyKeyProps().addAll(keyPropsDTOS);
    }

    /** New profile photo of the logged-in faculty member; empty contact fields keep the stored values. */
    public void saveProfilePhoto(String userName, MultipartFile uploadfile, String facultyPersonalEmail,
                                 String facultyOfficialEmail, Long facultyMobNo, String facultyName) {
        String fileName = uploadfile.getOriginalFilename();
        FacultyDTO facultyDTO = facultyService.getFaculty(userName);
        byte[] photo = UploadService.readBytes(uploadfile, "profile photo");
        if (photo != null) {
            facultyDTO.setFacultyPhoto(photo); // an unreadable upload keeps the old photo
        }
        facultyDTO.setFileType(FilenameUtils.getExtension(fileName));
        if (!StringUtils.isEmpty(facultyOfficialEmail)) {
            facultyDTO.setFacultyOfficialEmail(facultyOfficialEmail);
        }
        if (!StringUtils.isEmpty(facultyPersonalEmail)) {
            facultyDTO.setFacultyPersonalEmail(facultyPersonalEmail);
        }
        if (!StringUtils.isEmpty(facultyMobNo)) {
            facultyDTO.setFacultyMobNo(facultyMobNo);
        }
        if (!StringUtils.isEmpty(facultyName)) {
            facultyDTO.setFacultyName(facultyName);
        }
        facultyService.saveFaculty(facultyDTO);
    }

    /** A document (file or link) on the profile of the logged-in faculty member. */
    public void saveDocument(String userName, String facultydocumentsHeader, MultipartFile uploadFile, boolean isLink,
                             String linkAddress) {
        FacultyDocumentsDTO facultyDocumentsDTO = new FacultyDocumentsDTO();
        facultyDocumentsDTO.setHeadLine(facultydocumentsHeader);
        facultyDocumentsDTO.setUploadedFileName(uploadFile.getOriginalFilename());
        facultyDocumentsDTO.setUploadedBy(userName);
        facultyDocumentsDTO.setDate(new Date());
        facultyDocumentsDTO.setLink(isLink);
        facultyDocumentsDTO.setLinkAddress(linkAddress);
        facultyDocumentsDTO.setDocument(UploadService.readBytes(uploadFile, "faculty document"));
        facultyDocumentsService.saveFacultydocumentsDetails(facultyDocumentsDTO);
    }
}
