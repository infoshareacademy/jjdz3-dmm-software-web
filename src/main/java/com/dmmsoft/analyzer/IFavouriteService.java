package com.dmmsoft.analyzer;

import com.dmmsoft.analyzer.analysis.LocalInvestmentRevenueCriteria;
import com.dmmsoft.user.User;

import java.util.List;

/**
 * Created by milo on 19.05.17.
 */
public interface IFavouriteService {


    void addFavourite(LocalInvestmentRevenueCriteria favouriteCriteria, User user);

    List<LocalInvestmentRevenueCriteria> getAllUserFavoutiteCriteria(long UserId);



}
