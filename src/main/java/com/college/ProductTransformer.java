package com.college;
import com.college.core.entity.Faculty;
import com.college.core.entity.Product;
import com.college.core.entity.User;
import com.college.core.model.FacultyDTO;
import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

public class ProductTransformer {

    public static List<String> getProductName(List<Product> products) {
        List<String> names = new ArrayList<>();
        for (Product prod : products) {
            names.add(prod.getProductName());
        }
        return names;
    }

    public static List<String> getvendorName(List<Product> products) {
        List<String> vendorName = new ArrayList<>();
        for (Product prod : products) {
            vendorName.add(prod.getVendorName());
        }
        return vendorName;
    }
    public static List<String> getUserName(List<User> users){
        List<String> usernames = new ArrayList<>();
        for(User user : users){
            usernames.add(user.getUsername());
        }
        return usernames;
    }

    public static List<String> getFacultyName(List<Faculty> faculties)
    {
        List<String> facultyName = new ArrayList<>();
        for (Faculty faculty : faculties)
        {
            facultyName.add(faculty.getFacultyName());
        }
        return facultyName;
    }
    public static List<String> getAllUserNames(List<User>users) {
        List<String> allUsername=new ArrayList<>();
        for(User user : users){
            allUsername.add(user.getUsername());
        }
        return  allUsername;
    }
}
