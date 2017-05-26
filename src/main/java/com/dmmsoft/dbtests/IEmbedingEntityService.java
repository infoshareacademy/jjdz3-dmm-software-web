package com.dmmsoft.dbtests;

import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;
import com.dmmsoft.user.User;

/**
 * Created by milo on 22.05.17.
 */
public interface IEmbedingEntityService {

    void addEmbedingEntity(EmbedingEntity embedingEntity);

    void addJarEntity(InvestmentRevenueCriteria investmentRevenueCriteria);

    void addUser(User user);
}
