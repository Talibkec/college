package com.college.core.controller.store;

import com.college.core.model.ProductDTO;
import com.college.core.model.PurchaseDTO;
import com.college.repository.PurchaseRepository;
import com.college.service.ProductService;
import com.college.service.PurchaseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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


    @RequestMapping(value = "addProduct", method = RequestMethod.POST)
    public Boolean addProduct(@RequestParam("productDetails") String productDetails) {
        ProductDTO product = gson.fromJson(productDetails, ProductDTO.class);
        productService.save(product);
        logger.info(productDetails);
        return true;
    }

    @RequestMapping(value = "addProduct", method = RequestMethod.GET)
    public ModelAndView addProduct(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/store/addproduct");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "getProductName", method = RequestMethod.GET)
    public List<String> getProductName(@RequestParam("prodName") String prodName) {
        List<String> productNames = productService.prodName(prodName);
        return productNames;
    }

    @ResponseBody
    @RequestMapping(value = "getVendorName", method = RequestMethod.GET)
    public List<String> getVendorName(@RequestParam("vendorName") String vendorName) {
        List<String> productNames = productService.vendorName(vendorName);
        return productNames;
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
            mv.setViewName("/store/productdetails");
        }
        else{
            mv.addObject("productFound", false);
            mv.setViewName("/store/storemanager");

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
        mv.setViewName("/store/addpurchase");
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
        mv.setViewName("/store/searchpurchasedetails");
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
        mv.setViewName("/store/purchasedetails");
        return mv;
    }

    @RequestMapping(value = "editPurchase")
    public ModelAndView editPurchase(@RequestParam("purchaseId") Long purchaseId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("purchaseId",purchaseId);
        PurchaseDTO purchase =  purchaseService.getPurchase(purchaseId);
        mv.addObject("purchase", purchase);
        mv.setViewName("/store/editpurchase");
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

    @RequestMapping(value = "addPurchase", method = RequestMethod.POST)
    public ModelAndView addPurchase(HttpServletRequest request, HttpServletResponse response, @RequestParam("purchaseDetails") String purchaseDetails) throws IOException {

        ModelAndView mv = new ModelAndView();
        PurchaseDTO purchaseDTO = gson.fromJson(purchaseDetails, PurchaseDTO.class);
        purchaseService.savePurchaseDetails(purchaseDTO);
        logger.info(purchaseDetails);
        mv.addObject("","");
        mv.setViewName("/store/storemanager");
        return  mv;
    }

    @RequestMapping(value = "smdashboard", method = RequestMethod.GET)
    public ModelAndView smDashBoard(){
        ModelAndView mv = new ModelAndView();
        mv.addObject("productFound", true);
        mv.setViewName("/store/storemanager");
        return mv;
    }

}

