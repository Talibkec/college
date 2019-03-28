package com.college.core.controller.store;

import com.college.FacultyHelper;
import com.college.core.entity.Role;
import com.college.core.model.*;
import com.college.service.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "store")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
    private static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
    @Autowired
    ProductService productService;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    RequestService requestService;
    @Autowired
    FacultyService facultyService;
    @Autowired
    OrderService orderService;
    @Autowired
    FacultyHelper facultyHelper;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "addProduct", method = RequestMethod.POST)
    public Boolean addProduct(@RequestParam("productDetails") String productDetails) {
        ProductDTO product = gson.fromJson(productDetails, ProductDTO.class);
        product.setAvailableQuantity(product.getProductQuantity());
        productService.save(product);
        logger.info(productDetails);
        return true;
    }

    @RequestMapping(value = "addProduct", method = RequestMethod.GET)
    public ModelAndView addProduct(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/store/addproduct.jsp");
        return mv;
    }

    @RequestMapping(value = "getProductDetails", method = RequestMethod.GET)
    public ModelAndView getVendorName(@RequestParam("prodName") String prodName, @RequestParam("vendorName") String vendorName,
                                      @RequestParam("productId") String productId) {
        ModelAndView mv = new ModelAndView();
        List<ProductDTO> products = new ArrayList<>();
        products = productService.getProductDetails(prodName, vendorName, productId);
        Integer productSize = 0;
        if(products.size() != 0){
            productSize = products.size();
            mv.addObject("prodList", products);
            mv.addObject("noOfItems", products.size());
            mv.addObject("productSize", productSize);
            mv.setViewName("/store/productdetails.jsp");
        }
        else{
            mv.addObject("productFound", false);
            mv.setViewName("/store/storemanager.jsp");

        }

        return mv;
    }

    @RequestMapping(value = "addPurchase")
    public ModelAndView addPurchase(@RequestParam("vendor") String vendorName,
                                    @RequestParam("name") String prodName,
                                    @RequestParam("productQuantity") String productQuantity,
                                    @RequestParam("productId") Long productId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("productName", prodName);
        mv.addObject("vendorName", vendorName);
        mv.addObject("productQuantity", productQuantity);
        mv.addObject("productId",productId);
        mv.setViewName("/store/addpurchase.jsp");
        return mv;
    }

    @RequestMapping(value = "purchasedetails")
    public ModelAndView purchasedetails(@RequestParam("vendor") String vendorName,
                                    @RequestParam("name") String prodName,
                                    @RequestParam("productQuantity") String productQuantity,
                                    @RequestParam("productId") Long productId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("productName", prodName);
        mv.addObject("vendorName", vendorName);
        mv.addObject("productQuantity", productQuantity);
        mv.addObject("productId",productId);
        mv.setViewName("/store/searchpurchasedetails.jsp");
        return mv;
    }

    @RequestMapping(value = "purchasedetail")
    public ModelAndView purchasedetail(@RequestParam("toDatepicker") String toDate,
                                        @RequestParam("fromDatepicker") String fromDate,
                                        @RequestParam("productId") Long productId) {
        ModelAndView mv = new ModelAndView();
        Date to = new Date(), from = new Date();
        try {
            from = DATE_TIME_FORMATTER.parse(fromDate);
            to = DATE_TIME_FORMATTER.parse(toDate);
        } catch (ParseException e) {
            logger.error("Could not parse the either to or from date, Please contact to administrator.");
        }
        List<PurchaseDTO> purchaseDetails = purchaseService.getPurchaseByProductId(productId, from, to);
        mv.addObject("purchases", purchaseDetails);
        mv.setViewName("/store/purchasedetails.jsp");
        return mv;
    }

    @RequestMapping(value = "getPurchaseBtweenDates")
    public ModelAndView getPurchaseBtweenDates(@RequestParam("fromDate") String fromDate,
                                       @RequestParam("toDate") String toDate) {
        ModelAndView mv = new ModelAndView();
        Date to = new Date(), from = new Date();
        try {
            from = DATE_TIME_FORMATTER.parse(fromDate);
            to = DATE_TIME_FORMATTER.parse(toDate);
        } catch (ParseException e) {
            logger.error("Could not parse the either to or from date, Please contact to administrator.");
        }
        List<PurchaseDTO> purchaseDetails = purchaseService.getPurchaseBtweenDates( from, to);
        mv.addObject("purchases", purchaseDetails);
        mv.setViewName("/store/purchasedetails.jsp");
        return mv;
    }

    @RequestMapping(value = "editPurchase")
    public ModelAndView editPurchase(@RequestParam("purchaseId") Long purchaseId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("purchaseId",purchaseId);
        PurchaseDTO purchase =  purchaseService.getPurchase(purchaseId);
        mv.addObject("purchase", purchase);
        mv.setViewName("/store/editpurchase.jsp");
        return mv;
    }
    @RequestMapping(value = "editProduct")
    public ModelAndView editProduct(@RequestParam("productId") Long productId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("productId",productId);
        ProductDTO productDTO =  productService.getProduct(productId);
        mv.addObject("product", productDTO);
        mv.setViewName("/store/editproduct.jsp");
        return mv;
    }

    @RequestMapping(value = "deletePurchase")
    public void deletePurchase(HttpServletRequest request, HttpServletResponse response, @RequestParam("purchaseId") Long purchaseId) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("purchaseId",purchaseId);
        purchaseService.deletePurchase(purchaseId);
        String url = request.getHeader("Referer");
        if(StringUtils.isEmpty(url)){
            url = "/";
        }
        response.sendRedirect(url);

    }
    @RequestMapping(value = "deleteProduct")
    public void deleteProduct(HttpServletRequest request, HttpServletResponse response, @RequestParam("productId") Long productId) throws IOException {
        ModelAndView mv = new ModelAndView();
        mv.addObject("productId",productId);
        productService.deleteProduct(productId);
        String url = request.getHeader("Referer");
        if(StringUtils.isEmpty(url)){
            url = "/";
        }
        response.sendRedirect(url);
    }

    @RequestMapping(value = "addPurchase", method = RequestMethod.POST)
    public ModelAndView addPurchase(HttpServletRequest request, HttpServletResponse response, @RequestParam("purchaseDetails") String purchaseDetails) throws IOException {

        ModelAndView mv = new ModelAndView();
        PurchaseDTO purchaseDTO = gson.fromJson(purchaseDetails, PurchaseDTO.class);
        purchaseService.savePurchaseDetails(purchaseDTO);
        logger.info(purchaseDetails);
        mv.addObject("","");
        mv.setViewName("/store/storemanager.jsp");
        return  mv;
    }

    @RequestMapping(value = "smdashboard", method = RequestMethod.GET)
    public ModelAndView smDashBoard(){
        ModelAndView mv = new ModelAndView();
        List<RequestDTO> requestDTOS = requestService.getNewRequest("New");
        mv.addObject("productFound", true);
        mv.addObject("request", requestDTOS);
        mv.addObject("requestSize", requestDTOS.size());
        mv.setViewName("/store/storemanager.jsp");
        return mv;
    }



    @RequestMapping(value = "orderPage", method = RequestMethod.GET)
    public ModelAndView showOrderPage(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/store/searchorder.jsp");
        return mv;
    }

    @RequestMapping(value = "submitSearchOrder", method = RequestMethod.POST)
    public ModelAndView submitSearchOrder(@RequestParam("prodName") String prodName, @RequestParam("facultyName") String facultyName
            , @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate){

        List<OrderDTO> orders = new ArrayList<>();
        Date to = new Date(), from = new Date();
        try {
            from = DATE_TIME_FORMATTER.parse(fromDate);
            to = DATE_TIME_FORMATTER.parse(toDate);
        } catch (ParseException e) {
            logger.error("Could not parse the either to or from date, Please contact to administrator.");
        }
        if(StringUtils.isEmpty(prodName ) && !StringUtils.isEmpty(facultyName)){
            List<FacultyDTO> facultyDTO = facultyService.getFacultyByName(facultyName);
            for(FacultyDTO faculty : facultyDTO){
                List<OrderDTO> orderDTOS = orderService.getOrderByFacultyName(faculty.getFacultyId(), from, to);
                if(orderDTOS != null)
                    orders.addAll(orderService.getOrderByFacultyName(faculty.getFacultyId(), from, to));
            }

        }
        else if(StringUtils.isEmpty(facultyName) && !StringUtils.isEmpty(prodName ) ){
            orders = orderService.getOrderByProductName(prodName, from, to);
        }
        else{
            orders = orderService.getOrderBetweenDate(from, to);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("orders", orders);
        mv.setViewName("/store/orderdetails.jsp");
        return mv;
    }

    @RequestMapping(value = "approvefacultyrequest", method = RequestMethod.GET)
    public void updateRequest(@RequestParam("requestId") Long id, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        RequestDTO requestDTO = requestService.getRequest(id);
        requestDTO.setStatus("Approved");
        requestDTO.setApprovalDate(new Date());
        requestService.saveRequest(requestDTO);
        mv.setViewName("/store/storemanager.jsp");
        response.sendRedirect("http://keck.ac.in/store/smdashboard");
    }

    @RequestMapping(value = "rejectfacultyrequest", method = RequestMethod.GET)
    public void rejectRequest(@RequestParam("requestId") Long id, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        RequestDTO requestDTO = requestService.getRequest(id);
        requestDTO.setStatus("Rejected");
        requestDTO.setApprovalDate(new Date());
        requestService.saveRequest(requestDTO);
        mv.setViewName("/store/storemanager.jsp");
        response.sendRedirect("http://keck.ac.in/store/smdashboard");
    }

    @RequestMapping(value = "smincharge")
    public ModelAndView smincharge(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/store/smincharge.jsp");
        return mv;
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
        mv.setViewName("/store/editrequest.jsp");
    }

    @RequestMapping(value = "deleteUserRole",method = RequestMethod.GET)
    public void deleteUserRole(HttpServletRequest request, HttpServletResponse response,@RequestParam("facultyDetails") String facultyDetails) throws IOException {
        ModelAndView mv = new ModelAndView();
        UserRoleDTO userRoleDTO = new UserRoleDTO();
        userRoleDTO = gson.fromJson(facultyDetails, UserRoleDTO.class);
        Role role = roleService.getRole("SM");
        userRoleDTO.setRoleId(role.getId());
        userService.deleteUserRole(userRoleDTO);
        mv.setViewName("/store/editrequest.jsp");
    }



}

