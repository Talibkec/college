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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "store")
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(ProductController.class);
    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

    @Autowired
    ProductService productService;
    @Autowired
    PurchaseService purchaseService;


    @RequestMapping(value = "addProduct", method = RequestMethod.POST)
    public void addProduct(@RequestParam("productDetails") String productDetails) {
        ProductDTO product = gson.fromJson(productDetails, ProductDTO.class);
        productService.save(product);
        logger.info(productDetails);

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
        List<ProductDTO> productNames = new ArrayList<>();
        productNames = productService.getProductDetails(prodName, vendorName, productId);
        mv.addObject("prodList", productNames);
        mv.addObject("noOfItems", productNames.size());
        mv.setViewName("/store/productdetails");
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

    @RequestMapping(value = "addPurchase", method = RequestMethod.POST)
    public void addPurchase(@RequestParam("purchaseDetails") String purchaseDetails){
    PurchaseDTO purchaseDTO = gson.fromJson(purchaseDetails, PurchaseDTO.class);
        purchaseService.savePurchaseDetails(purchaseDTO);
        logger.info(purchaseDetails);

}


}

