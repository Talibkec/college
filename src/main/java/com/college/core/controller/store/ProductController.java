package com.college.core.controller.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "store")
public class ProductController {

    private Logger Logger = LoggerFactory.getLogger(ProductController.class);
    @RequestMapping(value = "addProduct" , method = RequestMethod.POST)
    public void addProduct(){
        Logger.info("Method called correctly.");
    }
}
