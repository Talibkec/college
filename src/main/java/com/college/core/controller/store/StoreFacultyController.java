package com.college.core.controller.store;

import com.college.core.controller.ControllerUtility;
import com.college.core.model.FacultyDTO;
import com.college.core.model.ProductDTO;
import com.college.core.model.RequestDTO;
import com.college.service.FacultyService;
import com.college.service.ProductService;
import com.college.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("store/faculty")
@PropertySource("classpath:employee.properties")
@ConfigurationProperties
public class StoreFacultyController {
    @Autowired
    RequestService requestService;

    @Autowired
    FacultyService facultyService;

    @Autowired
    ProductService productService;


    @Value("#{${users}}") private Map<String,String> userMap;
    @Value("#{${dept}}") private Map<String,String> deptMap;

    private static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    @RequestMapping(value = "order")
    public ModelAndView facultyOrderRequest()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/store/facultyproductsearch");
        return mv;
    }

    @RequestMapping(value = "getProductDetails", method = RequestMethod.GET)
    public ModelAndView getVendorName(@RequestParam("prodName") String prodName, @RequestParam("vendorName") String vendorName,
                                      @RequestParam("productId") String productId) {
        ModelAndView mv = new ModelAndView();
        List<ProductDTO> productNames = productService.getProductDetails(prodName, vendorName, productId);
        mv.addObject("prodList", productNames);
        mv.addObject("noOfItems", productNames.size());
        mv.setViewName("/store/productrequest");
        return mv;
    }

    @RequestMapping(value = "productrequest", method = RequestMethod.GET)
    public void productRequest(@RequestParam("productName") String prodName, @RequestParam("vendorName") String vendorName,
                               @RequestParam("productId") Long productId, @RequestParam("requestedQty") Long requestedQty, HttpServletResponse response) throws ParseException, IOException {
        RequestDTO requestDTO = new RequestDTO();
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(productId);
        requestDTO.setProduct(productDTO);
        requestDTO.setProductQuantity(requestedQty);
        requestDTO.setRequestDate(new Date());
        String userName = ControllerUtility.getUserName();
        FacultyDTO facultyDTO = facultyService.getFaculty(userName);
        requestDTO.setFacultyId(facultyDTO.getFacultyId());
        requestDTO.setStatus("New");
        requestService.saveRequest(requestDTO);
        String url = "http://localhost/department/" +
                deptMap.get(facultyDTO.getDepartmentId().toString()) + "/" +
                userMap.get(facultyDTO.getUser().getUsername());
        response.sendRedirect(url);

    }

    @RequestMapping(value = "editfacultyrequest", method = RequestMethod.GET)
    public ModelAndView editFacultyRequest(@RequestParam("id") Long id){
        ModelAndView mv = new ModelAndView();
        RequestDTO requestDTO = requestService.getRequest(id);
        mv.addObject("requestId", id);
        mv.addObject("request", requestDTO);
        mv.setViewName("/store/editrequest");
        return mv;
    }

    @RequestMapping(value = "updateRequest", method = RequestMethod.POST)
    public void updateRequest(@RequestParam("requestId") Long id, @RequestParam("requestedQty") Long requestedQty, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        RequestDTO requestDTO = requestService.getRequest(id);
        String userName = ControllerUtility.getUserName();
        FacultyDTO facultyDTO = facultyService.getFaculty(userName);
        requestDTO.setProductQuantity(requestedQty);
        requestService.saveRequest(requestDTO);
        mv.setViewName("/store/editrequest");
        String url = "http://localhost/department/" +
                deptMap.get(facultyDTO.getDepartmentId().toString()) + "/" +
                userMap.get(facultyDTO.getUser().getUsername());
        response.sendRedirect(url);
    }

    @RequestMapping(value = "deletefacultyrequest", method = RequestMethod.GET)
    public void deleteFacultyRequest(@RequestParam("requestId") Long requestId, HttpServletResponse response, HttpServletRequest request) throws IOException {
        ModelAndView mv = new ModelAndView();
        requestService.deleteFacultyRequest(requestId);
        String url = request.getHeader("Referer");
        if(StringUtils.isEmpty(url)){
            url = "/";
        }
        response.sendRedirect(url);
    }
}
