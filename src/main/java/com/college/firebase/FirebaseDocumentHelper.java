package com.college.firebase;

import com.google.firebase.database.DataSnapshot;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class FirebaseDocumentHelper {

    public Map<String, Map<String, Boolean>> getReportInfo(DataSnapshot document) {

        Map<String, Map<String, Boolean>> reportInfo = new HashMap<>();
        if(document != null ){

            for (DataSnapshot child : document.getChildren()) {
                DataSnapshot attendanceList = child.child("attendanceList");
                String date = ( String )child.child("date").getValue();
                if(attendanceList != null) {
                    Map<String, Boolean> attendanceListVal = (Map<String, Boolean>) attendanceList.getValue();
                    reportInfo.put(date, attendanceListVal);
                }
            }

        }
        return reportInfo;
    }
}
