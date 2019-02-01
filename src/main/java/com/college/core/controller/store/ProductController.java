package com.college.core.controller.store;

import com.college.core.model.ProductDTO;
import com.college.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    Gson gson=new GsonBuilder().setDateFormat("dd/MM/yyyy").create();

    @Autowired
    ProductService productService;

    @RequestMapping(value = "addProduct" , method = RequestMethod.POST)
    public void addProduct(@RequestParam("productDetails") String productDetails){
        ProductDTO product = gson.fromJson(productDetails, ProductDTO.class);
        productService.save(product);
        logger.info(productDetails);

    }

    @ResponseBody
    @RequestMapping(value = "getProductName" , method = RequestMethod.GET)
    public List<String> getProductName(@RequestParam("prodName") String prodName){
        List<String> productNames = productService.prodName(prodName);
        return productNames;
    }

    @ResponseBody
    @RequestMapping(value = "getVendorName" , method = RequestMethod.GET)
    public List<String> getVendorName(@RequestParam("vendorName") String vendorName){
        List<String> productNames = productService.vendorName(vendorName);
        return productNames;
    }

}
