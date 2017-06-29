package com.dmmsoft.analyzer;

import com.dmmsoft.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.dmmsoft.analyzer.analysis.investmentindicator.PersistedComparatorIndicatorCriteria;
import com.dmmsoft.analyzer.analysis.investmentindicator.PersistedIndicatorCriteria;
import com.dmmsoft.analyzer.analysis.investmentrevenue.PersistedInvestmentRevenueCriteria;

import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by milo on 19.05.17.
 */
public interface IFavouriteService {

    List<PersistedInvestmentRevenueCriteria> getAllFavouriteRevenueCriteria(long UserId);

    List<PersistedInvestmentRevenueCriteria> getAllRevenueCriteria();

  LinkedHashSet<PersistedComparatorIndicatorCriteria> getAllFavouriteComparatorIndicatorCriteria(long UserId);

    List<AnalysisComparisonContainer> getAllUserFavouriteAnalysisContainers(long UserId);

}
