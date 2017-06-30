package com.dmmsoft.analyzer.analysis.wrapper;

import com.dmmsoft.analyzer.analysis.investmentindicator.PersistedComparatorIndicatorCriteria;
import com.dmmsoft.analyzer.analysis.investmentindicator.PersistedIndicatorCriteria;
import com.dmmsoft.app.analyzer.analyses.AnalysisResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo on 28.06.17.
 */
public class ComparisonContentWrapper {

    private List<AnalysisContent> ananysisContent = new ArrayList<>();
    private String userCustomName;



    private PersistedComparatorIndicatorCriteria comparatorIndicatorCriteria;
    private List<AnalysisResult> resultList =new ArrayList<>();
    private PersistedIndicatorCriteria persistedIndicatorCriteria;

    public String getUserCustomName() {
        return userCustomName;
    }

    public void setUserCustomName(String userCustomName) {
        this.userCustomName = userCustomName;
    }

    public List<AnalysisContent> getAnanysisContent() {
        return ananysisContent;
    }

    public void setAnanysisContent(List<AnalysisContent> ananysisContent) {
        this.ananysisContent = ananysisContent;
    }

    public PersistedIndicatorCriteria getPersistedIndicatorCriteria() {
        return persistedIndicatorCriteria;
    }

    public void setPersistedIndicatorCriteria(PersistedIndicatorCriteria persistedIndicatorCriteria) {
        this.persistedIndicatorCriteria = persistedIndicatorCriteria;
    }

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
