package com.college;

import com.college.core.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductTransformer {

    public static List<String> getProductName(List<Product> products) {
        List<String> names = new ArrayList<>();
        for(Product prod : products){
            names.add(prod.getProductName());
        }
        return names;
    }

    public static List<String> getvendorName(List<Product> products) {
        List<String> vendorName = new ArrayList<>();
        for(Product prod : products){
            vendorName.add(prod.getVendorName());
        }
        return vendorName;
    }
}