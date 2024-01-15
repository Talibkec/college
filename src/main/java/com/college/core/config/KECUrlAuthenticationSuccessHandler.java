package com.college.core.config;

import com.college.core.model.FacultyDTO;
import com.college.service.FacultyService;
import com.college.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
public class KECUrlAuthenticationSuccessHandler
        implements AuthenticationSuccessHandler {
    @Autowired
    FacultyService facultyService;
    protected Log logger = LogFactory.getLog(this.getClass());

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);

    }

    protected void handle(HttpServletRequest request,
                          HttpServletResponse response, Authentication authentication)
            throws IOException {

        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            logger.debug(
                    "Response has already been committed. Unable to redirect to "
                            + targetUrl);
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        boolean isSM = false;
        boolean isSK = false;
        boolean isAdmin=false;
        boolean isFaculty =false;
        boolean isHOD =false;
        Long facultyId=null;
        String currentUserName= authentication.getName();
       
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("SM")) {
                isSM = true;
                break;
            } else if (grantedAuthority.getAuthority().equals("SK")) {
                isSK = true;
                break;
            }
            else if (grantedAuthority.getAuthority().equals("Admin")){
                isAdmin=true;
            }
            else if(grantedAuthority.getAuthority().equals("Faculty")){
                FacultyDTO facultyDTO=facultyService.getFaculty(currentUserName);
                facultyId = facultyDTO.getFacultyId();
                isFaculty = true;
            }
            else if(grantedAuthority.getAuthority().equals("HOD")){
                isHOD=true;
            }
        }

        if (isSM) {
            return "/store/smdashboard";
        } else if (isSK) {
            return "/sk/storekeeper";
        }
        else if(isAdmin){
            return "/auth/uploadDashboard";
        }
        else if(isFaculty){
           
            return "/facultyDetails?facultyId="+facultyId;
        }
        else if(isHOD){
            return "/hod/hodDashboard";
        }
        else {
            return "/";
        }
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
