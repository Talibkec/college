package com.college.core.controller.firebase;

import com.college.firebase.FirebaseDocumentHelper;
import com.google.firebase.database.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class FacultyGenerateReport {

    @Autowired
    FirebaseDocumentHelper firebaseDocumentHelper;

    @ResponseBody
    @RequestMapping(value = "/rn", method = RequestMethod.GET)
    public String FirebaseReportController(/*@RequestParam("param") String params */ ) {

        String dept = "Computer Sc. & Engineering";
        String sub = "Major Projct";
        String startDate = "2020-04-01";
        String endDate = "2020-04-05";

        Query query = FirebaseDatabase.getInstance()
                .getReference("attendance").orderByChild("date").startAt(startDate).endAt(endDate);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Map<String, Boolean>> reportInfo = firebaseDocumentHelper.getReportInfo(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        return "";
    }



}
