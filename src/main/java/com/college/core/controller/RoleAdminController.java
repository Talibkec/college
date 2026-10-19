package com.college.core.controller;

import com.college.core.entity.User;
import com.college.service.RoleAssignmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/** Admin only ({@code /auth/**}): who holds which website role, and what each role may edit. */
@Controller
public class RoleAdminController {
    private final RoleAssignmentService roleAssignmentService;

    public RoleAdminController(RoleAssignmentService roleAssignmentService) {
        this.roleAssignmentService = roleAssignmentService;
    }

    @GetMapping("/auth/roles")
    public ModelAndView users(@RequestParam(value = "q", required = false) String query,
                              @RequestParam(value = "saved", required = false) String saved) {
        ModelAndView mv = new ModelAndView("roles/users.jsp");
        mv.addObject("users", roleAssignmentService.users(query));
        mv.addObject("q", query);
        mv.addObject("saved", saved);
        return mv;
    }

    @GetMapping("/auth/roles/user/{userId}")
    public ModelAndView user(@PathVariable("userId") Long userId) {
        User user = roleAssignmentService.user(userId);
        if (user == null) {
            return new ModelAndView("redirect:/auth/roles");
        }
        Set<String> held = new HashSet<>();
        if (user.getRoles() != null) {
            user.getRoles().forEach(role -> held.add(role.getName()));
        }
        ModelAndView mv = new ModelAndView("roles/user.jsp");
        mv.addObject("user", user);
        mv.addObject("held", held);
        mv.addObject("assignableRoles", roleAssignmentService.assignableRoles());
        return mv;
    }

    @PostMapping("/auth/roles/user/{userId}")
    public String saveUser(@PathVariable("userId") Long userId,
                           @RequestParam(value = "role", required = false) List<String> roles) {
        User user = roleAssignmentService.user(userId);
        if (user == null) {
            return "redirect:/auth/roles";
        }
        roleAssignmentService.assignRoles(userId, roles == null ? Collections.emptyList() : roles);
        return "redirect:/auth/roles?saved=" + URLEncoder.encode(user.getUsername(), StandardCharsets.UTF_8);
    }

    @GetMapping("/auth/roles/areas")
    public ModelAndView areas(@RequestParam(value = "saved", required = false) String saved) {
        ModelAndView mv = new ModelAndView("roles/areas.jsp");
        mv.addObject("areas", roleAssignmentService.areas());
        mv.addObject("roles", roleAssignmentService.matrixRoles());
        mv.addObject("matrix", roleAssignmentService.matrix());
        mv.addObject("saved", saved != null);
        return mv;
    }

    /** Form fields are named {@code cell:<area>|<role>} with the values "", "A" or "E". */
    @PostMapping("/auth/roles/areas")
    public String saveAreas(HttpServletRequest request) {
        Map<String, String> cells = new LinkedHashMap<>();
        for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
            if (param.getKey().startsWith("cell:") && param.getValue().length > 0) {
                cells.put(param.getKey().substring("cell:".length()), param.getValue()[0]);
            }
        }
        roleAssignmentService.updateMatrix(cells);
        return "redirect:/auth/roles/areas?saved=1";
    }
}
