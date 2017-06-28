package com.dmmsoft.analyzer.analysis.investmentrevenue;

import com.dmmsoft.analyzer.analysis.investmentindicator.PersistedComparatorIndicatorCriteria;
import com.dmmsoft.app.analyzer.analyses.AnalysisResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo on 28.06.17.
 */
public class ComparatorContentWrapper extends ContentWrapper {

    private PersistedComparatorIndicatorCriteria comparatorIndicatorCriteria;
    private List<AnalysisResult> resultList =new ArrayList<>();

    public List<AnalysisResult> getResultList() {
        return resultList;
    }

    public void setResultList(List<AnalysisResult> resultList) {
        this.resultList = resultList;
    }

    public PersistedComparatorIndicatorCriteria getComparatorIndicatorCriteria() {
        return comparatorIndicatorCriteria;
    }

    public void setComparatorIndicatorCriteria(PersistedComparatorIndicatorCriteria comparatorIndicatorCriteria) {
        this.comparatorIndicatorCriteria = comparatorIndicatorCriteria;
    }
}
