package com.dmmsoft.analyzer;

import com.dmmsoft.analyzer.analysis.InvestmentRevenue.PersistedInvestmentRevenueCriteria;

import java.util.List;

/**
 * Created by milo on 19.05.17.
 */
public interface IFavouriteService {




    List<PersistedInvestmentRevenueCriteria> getAllUserFavoutiteCriteria(long UserId);



}
