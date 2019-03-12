package com.college.core.controller.store;

import com.college.core.controller.ControllerUtility;
import com.college.core.entity.Role;
import com.college.core.model.*;
import com.college.service.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    OrderService orderService;

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;


    Gson gson=new GsonBuilder().setDateFormat("dd/MM/yyyy").create();


    @Value("#{${users}}") private Map<String,String> userMap;
    @Value("#{${dept}}") private Map<String,String> deptMap;

    private Logger logger = LoggerFactory.getLogger(StoreFacultyController.class);
    private static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    @RequestMapping(value = "order")
    public ModelAndView facultyOrderRequest()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/store/facultyproductsearch");
        return mv;
    }

    @RequestMapping(value = "submitFacultySearchOrder", method = RequestMethod.POST)
    public ModelAndView submitFacultySearchOrder(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate){

        List<OrderDTO> orders;
        Date to = new Date(), from = new Date();
        try {
            from = DATE_TIME_FORMATTER.parse(fromDate);
            to = DATE_TIME_FORMATTER.parse(toDate);
        } catch (ParseException e) {
            logger.error("Could not parse the either to or from date, Please contact to administrator.");
        }
        FacultyDTO facultyDTO = facultyService.getFaculty(ControllerUtility.getUserName());
        orders = orderService.getOrderByFacultyName(facultyDTO.getFacultyId(), from, to);
        ModelAndView mv = new ModelAndView();
        mv.addObject("orders", orders);
        mv.setViewName("/store/orderdetails");
        return mv;
    }


    @RequestMapping(value = "facultyOrderHistory")
    public ModelAndView facultyOrderHistory()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/store/searchfacultyorder");
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
                               @RequestParam("productId") Long productId, @RequestParam("requestedQty") Integer requestedQty, HttpServletResponse response) throws ParseException, IOException {
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
    @ResponseBody
    @RequestMapping(value = "searchFacultyName", method = RequestMethod.GET)
    public String searchFacultyByName(@RequestParam("facultyName") String facultyNames, Model model){
       //ModelAndView mv = new ModelAndView();
        List<FacultyDTO> faculties = facultyService.getFacultyByName(facultyNames);
        List<Role> roles = roleService.getALLRoles();
        //String smRole ="SM";
        Integer FacultySize = 0;
        /*if(faculty != null) {
            mv.addObject("faculty", faculty);
            mv.addObject("roles",roles);
            mv.setViewName("/store/smincharge");
        }
        else{
            mv.addObject("facultyFound", false);
            mv.setViewName("/store/smincharge");
        }*/
        JsonObject obj = null;
        if(faculties.size() > 0) {
            obj = new JsonObject();
            FacultyDTO facultyDTO = faculties.get(0);
            obj.addProperty("name", facultyDTO.getFacultyName());
            obj.addProperty("id", facultyDTO.getFacultyId());
        }
        if(obj == null){
            return null;
        }
         return  obj.toString();
    }

    @RequestMapping(value = "saveRole", method = RequestMethod.POST)
    public void setRoles(@RequestParam("facultyDetails") String facultyDetails) throws IOException {
        ModelAndView mv=new ModelAndView();
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        FacultyDTO facultyDTO = new FacultyDTO();

        Role role = roleService.getRole("SM");
        userRoleDTO = gson.fromJson(facultyDetails, UserRoleDTO.class);
        userRoleDTO.setRoleId(role.getId());
        userService.saveUserRole(userRoleDTO);
        mv.setViewName("/store/editrequest");
    }

    @RequestMapping(value = "deleteUserRole",method = RequestMethod.GET)
    public void deleteUserRole(HttpServletRequest request, HttpServletResponse response,@RequestParam("facultyDetails") String facultyDetails) throws IOException {
        ModelAndView mv = new ModelAndView();
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO = gson.fromJson(facultyDetails, UserRoleDTO.class);
        Role role = roleService.getRole("SM");
        userRoleDTO.setRoleId(role.getId());
        userService.deleteUserRole(userRoleDTO);
        mv.setViewName("/store/editrequest");
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

    @RequestMapping(value = "deletefacultyrequest")
    public void deletefacultyrequest(@RequestParam("requestId") Long requestId, HttpServletResponse response) throws IOException {
        RequestDTO requestDTO = requestService.getRequest(requestId);
        requestService.deleteFacultyRequest(requestDTO.getRequestId());
        response.sendRedirect("/department/cse/mta");

    }

    @RequestMapping(value = "smincharge")
    public ModelAndView smincharge(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/store/smincharge");
        return mv;
    }


    @RequestMapping(value = "updateRequest", method = RequestMethod.POST)
    public void updateRequest(@RequestParam("requestId") Long id, @RequestParam("requestedQty") Integer requestedQty, HttpServletResponse response) throws IOException {
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

    @RequestMapping(value = "approvefacultyrequest", method = RequestMethod.GET)
    public void updateRequest(@RequestParam("requestId") Long id, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        RequestDTO requestDTO = requestService.getRequest(id);
        requestDTO.setStatus("Approved");
        requestDTO.setApprovalDate(new Date());
        requestService.saveRequest(requestDTO);
        mv.setViewName("/store/storemanager");
        response.sendRedirect("http://localhost/store/smdashboard");
    }

    @RequestMapping(value = "rejectfacultyrequest", method = RequestMethod.GET)
    public void rejectRequest(@RequestParam("requestId") Long id, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        RequestDTO requestDTO = requestService.getRequest(id);
        requestDTO.setStatus("Rejected");
        requestDTO.setApprovalDate(new Date());
        requestService.saveRequest(requestDTO);
        mv.setViewName("/store/storemanager");
        response.sendRedirect("http://localhost/store/smdashboard");
    }

    @RequestMapping(value = "closefacultyrequest", method = RequestMethod.GET)
    public void closefacultyRequest(@RequestParam("requestId") Long id, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        RequestDTO requestDTO = requestService.getRequest(id);
        FacultyDTO facultyDTO = facultyService.getFacultyById(requestDTO.getFacultyId());
        requestDTO.setStatus("Closed");
        requestDTO.setApprovalDate(new Date());
        saveOrder(requestDTO, facultyDTO.getFacultyName());
        mv.setViewName("/store/storemanager");
        response.sendRedirect("http://localhost/store/storekeeper");
    }

    @Transactional
    private void saveOrder(RequestDTO requestDTO, String facultyName){
        requestService.deleteFacultyRequest(requestDTO.getRequestId());
        OrderDTO orderDTO = RequestOrderTransformer.convertToOrderDTO(requestDTO);
        orderDTO.setFacultyName(facultyName);
        orderService.saveOrder(orderDTO);
    }


}
