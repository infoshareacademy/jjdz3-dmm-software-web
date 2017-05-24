package com.dmmsoft.analyzer;

import com.dmmsoft.app.analyzer.analyses.AnalysisCriteria;
import com.dmmsoft.dbtests.LocalInvestmentRevenueCriteria;

import java.util.List;

/**
 * Created by milo on 19.05.17.
 */
public interface IFavouriteService {


    void addFavourite(Favourite te);

    List<LocalInvestmentRevenueCriteria> getAllUserFavoutiteCriteria(long UserId);



}
