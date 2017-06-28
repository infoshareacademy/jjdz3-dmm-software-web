package com.dmmsoft.analyzer.analysis.investmentrevenue;

import com.dmmsoft.analyzer.analysis.investmentindicator.PersistedComparatorIndicatorCriteria;

/**
 * Created by milo on 28.06.17.
 */
public class ComparatorContentWrapper extends ContentWrapper {

    private PersistedComparatorIndicatorCriteria comparatorIndicatorCriteria;

    public PersistedComparatorIndicatorCriteria getComparatorIndicatorCriteria() {
        return comparatorIndicatorCriteria;
    }

    public void setComparatorIndicatorCriteria(PersistedComparatorIndicatorCriteria comparatorIndicatorCriteria) {
        this.comparatorIndicatorCriteria = comparatorIndicatorCriteria;
    }
}
