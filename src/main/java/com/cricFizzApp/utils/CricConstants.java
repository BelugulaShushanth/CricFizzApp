package com.cricFizzApp.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CricConstants {

    public static Map<String,String> getAlertTypes(){
        Map<String,String> alertTypesMap = new LinkedHashMap<>();
        alertTypesMap.put("score","Score");
        alertTypesMap.put("wicket","Wicket");
        alertTypesMap.put("matchEnd","Match Ended");
        return alertTypesMap;
    }

    public static Map<Integer,String> getTimePeriod(){
        Map<Integer,String> timePeriodMap = new LinkedHashMap<>();
        timePeriodMap.put(1,"For every minute");
        timePeriodMap.put(2,"For every 2 minutes");
        timePeriodMap.put(5,"For every 5 minutes");
        timePeriodMap.put(10,"For every 10 minutes");
        timePeriodMap.put(15,"For every 15 minutes");
        timePeriodMap.put(30,"For every 30 minutes");
        timePeriodMap.put(60,"For every hour");
        timePeriodMap.put(120,"For every 2 hours");
        return timePeriodMap;
    }
}
