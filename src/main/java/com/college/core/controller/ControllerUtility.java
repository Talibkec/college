package com.college.core.controller;

import com.college.KECDateHelper;
import com.college.core.model.NoticeBoardDTO;
import org.apache.commons.io.FilenameUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class ControllerUtility {

    public static List<String> getRole() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> userRole = new ArrayList<>();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = ((UserDetails) principal);
            for (GrantedAuthority authority : userDetails.getAuthorities()) {
                userRole.add(authority.getAuthority());
            }

        }
        return userRole;
    }

    public static String getUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = null;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        }
        return userName;
    }

    public static List<NoticeBoardDTO> getNoticelist(List<NoticeBoardDTO> list) {
        List<NoticeBoardDTO> noticeList = new ArrayList<>();
        for (NoticeBoardDTO dto : list) {
            dto.setFileType(("." + FilenameUtils.getExtension(dto.getUploadedFileName())));
        }
        return noticeList;
    }

    /**
     * Prepares notices for display (file type with dot, notice age) and returns either the scrolling notices
     * ({@code isScrollable == 1}) or the normal ones ({@code isScrollable == null}).
     */
    public static List<NoticeBoardDTO> filterNotices(List<NoticeBoardDTO> list, boolean scrollable) {
        List<NoticeBoardDTO> result = new ArrayList<>();
        for (NoticeBoardDTO dto : list) {
            dto.setFileType(("." + FilenameUtils.getExtension(dto.getUploadedFileName())));
            dto.setNoticeAge(KECDateHelper.getNoticeAge(dto));
            if (scrollable) {
                if (dto.getIsScrollable() != null && dto.getIsScrollable() == 1)
                    result.add(dto);
            } else {
                if (dto.getIsScrollable() == null)
                    result.add(dto);
            }
        }
        return result;
    }
}
