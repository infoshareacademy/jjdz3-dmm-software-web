package com.dmmsoft.analyzer.analysis.wrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo on 28.06.17.
 */
public class ComparisonContentWrapper {

    private List<AnalysisContent> ananysisContentList = new ArrayList<>();
    private String userCustomName;

    public String getUserCustomName() {
        return userCustomName;
    }

    public void setUserCustomName(String userCustomName) {
        this.userCustomName = userCustomName;
    }

    public List<AnalysisContent> getAnanysisContentList() {
        return ananysisContentList;
    }

    public void setAnanysisContentList(List<AnalysisContent> ananysisContentList) {
        this.ananysisContentList = ananysisContentList;
    }

    public ComparisonContentWrapper() {
    }

}
