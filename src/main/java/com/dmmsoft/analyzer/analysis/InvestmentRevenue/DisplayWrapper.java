package com.dmmsoft.analyzer.analysis.InvestmentRevenue;

import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueResult;

/**
 * Created by milo on 02.06.17.
 */
public class DisplayWrapper {

        private InvestmentRevenueCriteria criteria;
        private InvestmentRevenueResult result;
        private String message;

        public InvestmentRevenueCriteria getCriteria() {
            return criteria;
        }

        public void setCriteria(InvestmentRevenueCriteria criteria) {
            this.criteria = criteria;
        }

        public InvestmentRevenueResult getResult() {
            return result;
        }

        public void setResult(InvestmentRevenueResult result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

}
