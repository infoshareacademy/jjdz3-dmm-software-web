package com.dmmsoft.adminpanel.report;

import com.dmmsoft.analyzer.IFavouriteService;
import com.dmmsoft.analyzer.analysis.investmentrevenue.PersistedInvestmentRevenueCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo on 08.07.17.
 */

public class CSVGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVGenerator.class);

    @Inject
    private IFavouriteService favouriteService;

    public List<String> generateCSVLines() {

        List<String> lines = new ArrayList<>();

        List<PersistedInvestmentRevenueCriteria> criteria = new ArrayList<>();
                criteria = favouriteService.getAllRevenueCriteria();

        for(InvestmentRevenueCriteria item :criteria){

            String separator = ",";
            StringBuilder sb = new StringBuilder();
            sb.append(item.getInvestmentName()).append(separator);
            sb.append(item.getInvestedCapital()).append(separator);
            sb.append(item.getBuyDate().toString()).append(separator);
            sb.append(item.getSellDate().toString()).append(separator);
            sb.append(System.getProperty("line.separator"));
            lines.add(sb.toString());
            LOGGER.info("Adding Line to CSV: {}", sb.toString());
        }
        return lines;
    }

}
