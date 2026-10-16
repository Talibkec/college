package com.college.core.controller.store;

import com.college.FacultyHelper;
import com.college.core.model.FacultyDTO;
import com.college.service.FacultyService;
import com.college.service.ProductService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Store management lookups (/common/store/**). OUT OF SCOPE of the refactoring: the code below is unchanged.
 * The core faculty/staff/placement endpoints that used to live here moved to FacultyProfileController,
 * PeopleAdminController and TrainingPlacementController (see docs/UPGRADE_NOTES.md).
 */
@Controller
public class CommonResourceController {

    @Autowired
    ProductService productService;
    @Autowired
    FacultyService  facultyService;
    @Autowired
    FacultyHelper facultyHelper;

    @ResponseBody
    @RequestMapping(value = "common/store/getProductName", method = RequestMethod.GET)
    public List<String> getProductName(@RequestParam("prodName") String prodName) {
        List<String> productNames = productService.prodName(prodName);
        return productNames;
    }

    @ResponseBody
    @RequestMapping(value = "common/store/getVendorName", method = RequestMethod.GET)
    public List<String> getVendorName(@RequestParam("vendorName") String vendorName) {
        List<String> productNames = productService.vendorName(vendorName);
        return productNames;
    }

    @ResponseBody
    @RequestMapping(value = "/common/store/facultyNameAutocomplete", method = RequestMethod.GET)
    public List<String> facultyNameAutocomplete(@RequestParam("facultyName") String facultyName) {
        List<FacultyDTO> facultyNames = facultyService.searchFacultyName(facultyName);
        return facultyHelper.facultyNames(facultyNames);
    }

    @ResponseBody
    @RequestMapping(value = "/common/store/facultyEmailAutocomplete", method = RequestMethod.GET)
    public List<String> facultyEmailAutocomplete(@RequestParam("facultyEmail") String facultyEmail) {
        List<FacultyDTO> facultyEmails = facultyService.searchFacultyEmail(facultyEmail);
        return facultyHelper.facultyEmails(facultyEmails);
    }

    @ResponseBody
    @RequestMapping(value = "/common/store/searchFacultyName", method = RequestMethod.GET)
    public List<FacultyDTO> searchFacultyName(@RequestParam("facultyName") String facultyName) {
        List<FacultyDTO> facultyNames = facultyService.searchFacultyName(facultyName);
        return facultyNames;
    }

    @ResponseBody
    @RequestMapping(value = "/common/store/sm/searchFacultyName", method = RequestMethod.GET)
    public String searchFacultyByName(@RequestParam("facultyName") String facultyNames, Model model) {

        List<FacultyDTO> faculties = facultyService.getFacultyByName(facultyNames);
        JsonObject obj = null;
        if (faculties.size() > 0) {
            obj = new JsonObject();
            FacultyDTO facultyDTO = faculties.get(0);

            facultyDTO.getUser().getUsername();
            obj.addProperty("name", facultyDTO.getFacultyName());
            obj.addProperty("id", facultyDTO.getFacultyId());
        }
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }
}
