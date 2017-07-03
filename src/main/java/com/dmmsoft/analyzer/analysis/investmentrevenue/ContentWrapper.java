package com.dmmsoft.analyzer.analysis.investmentrevenue;

import com.dmmsoft.app.analyzer.analyses.AnalysisCriteria;
import com.dmmsoft.app.analyzer.analyses.AnalysisResult;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueResult;

/**
 * Created by milo on 02.06.17.
 */
public class ContentWrapper {

    private AnalysisCriteria criteria;
    private AnalysisResult result;
    private String message;

    public AnalysisCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(AnalysisCriteria criteria) {
        this.criteria = criteria;
    }

    public AnalysisResult getResult() {
        return result;
    }

    public void setResult(AnalysisResult result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
     /*  private InvestmentRevenueCriteria criteria;
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
*/
}
