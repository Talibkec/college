package com.college.core.controller.store;

import com.college.core.model.FacultyDTO;
import com.college.core.model.OrderDTO;
import com.college.core.model.RequestDTO;
import com.college.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("sk")
public class StoreKeeperController {

    @Autowired
    RequestService requestService;
    @Autowired
    FacultyService facultyService;
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "closefacultyrequest", method = RequestMethod.GET)
    public void closefacultyRequest(@RequestParam("requestId") Long id, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        RequestDTO requestDTO = requestService.getRequest(id);
        FacultyDTO facultyDTO = facultyService.getFacultyById(requestDTO.getFacultyId());
        requestDTO.setStatus("Closed");
        requestDTO.setApprovalDate(new Date());
        saveOrder(requestDTO, facultyDTO.getFacultyName());
        mv.setViewName("/store/storemanager.jsp");
        response.sendRedirect("http://keck.ac.in/sk/storekeeper");
    }

    @RequestMapping(value = "storekeeper", method = RequestMethod.GET)
    public ModelAndView skDashboard(){
        ModelAndView mv = new ModelAndView();
        List<RequestDTO> requestDTOS = requestService.getNewRequest("Approved");
        mv.addObject("request", requestDTOS);
        mv.addObject("requestSize", requestDTOS.size());
        mv.setViewName("/store/storekeeper.jsp");
        return mv;
    }

    @Transactional
    private void saveOrder(RequestDTO requestDTO, String facultyName){
        requestService.deleteFacultyRequest(requestDTO.getRequestId());
        OrderDTO orderDTO = RequestOrderTransformer.convertToOrderDTO(requestDTO);
        orderDTO.setFacultyName(facultyName);
        orderService.saveOrder(orderDTO);
    }
}
