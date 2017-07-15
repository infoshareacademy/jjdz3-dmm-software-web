package com.dmmsoft.user.report;

import java.util.ArrayList;
import java.util.List;

import static com.dmmsoft.ConstantsProvider.LINE_SEPARATOR;

/**
 * Created by milo on 15.07.17.
 */
public class CSVGenerator {


    public List<String> generateCSVLines(IUserActivityService userActivityService){

        List<UserActivity> userActivities = userActivityService.getAllUserActivity();
        List<String> lines = new ArrayList<>();

        lines.add("Id, DateTime, Activity, Login, SessionId".concat(System.getProperty(LINE_SEPARATOR)));

        String separator = ",";
        for(UserActivity item : userActivities){

            StringBuilder sb = new StringBuilder();
            sb.append(item.getId()).append(separator);
            sb.append(item.getActivityDateTime().toString()).append(separator);
            sb.append(item.getActivityName()).append(separator);
            sb.append(item.getLogin()).append(separator);
            sb.append(item.getSessionId());
            sb.append(System.getProperty(LINE_SEPARATOR));
            lines.add(sb.toString());
        }
        return lines;
    }





/*
    private List<String> generateCSVLines(ReportComponents reportComponents) {

        List<String> lines = new ArrayList<>();

        List<PersistedInvestmentRevenueCriteria> criteria = reportComponents
                .getFavouriteService()
                .getAllRevenueCriteria();

        lines.add("InvestmentName,InvestedCapital,BuyDate,SellDate"
                .concat(System.getProperty(LINE_SEPARATOR)));

        for (InvestmentRevenueCriteria item : criteria) {

            String separator = ",";
            StringBuilder sb = new StringBuilder();

            sb.append(item.getInvestmentName()).append(separator);
            sb.append(item.getInvestedCapital()).append(separator);
            sb.append(item.getBuyDate().toString()).append(separator);
            sb.append(item.getSellDate().toString());
            sb.append(System.getProperty(LINE_SEPARATOR));
            lines.add(sb.toString());
            LOGGER.info("Adding Line to CSV: {}", sb.toString());
        }
        return lines;
    }*/


}
