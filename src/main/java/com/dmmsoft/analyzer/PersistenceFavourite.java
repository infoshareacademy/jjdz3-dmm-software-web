package com.dmmsoft.analyzer;

import com.dmmsoft.analyzer.analysis.investmentrevenue.PersistedInvestmentRevenueCriteria;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by milo on 19.05.17.
 */

@Default
public class PersistenceFavourite implements IFavouriteService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<PersistedInvestmentRevenueCriteria> getAllUserFavoutiteCriteria(long UserId) {

        List<PersistedInvestmentRevenueCriteria> list = em
                .createQuery("select m from PersistedInvestmentRevenueCriteria m left join fetch m.user t where t.id=:Id AND m.isFavourite=true", PersistedInvestmentRevenueCriteria.class)
                .setParameter("Id", UserId)
                .getResultList();
        return list;
    }

    @Override
    public List<PersistedInvestmentRevenueCriteria> getAllCriteria() {
        return  em.createQuery("select m from PersistedInvestmentRevenueCriteria m", PersistedInvestmentRevenueCriteria.class)
                  .getResultList();
    }

}
