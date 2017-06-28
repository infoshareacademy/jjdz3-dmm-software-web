package com.dmmsoft.analyzer;

import com.dmmsoft.analyzer.analysis.investmentindicator.PersistedComparatorIndicatorCriteria;
import com.dmmsoft.analyzer.analysis.investmentrevenue.PersistedInvestmentRevenueCriteria;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * Created by milo on 19.05.17.
 */

@Default
public class PersistenceFavourite implements IFavouriteService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<PersistedInvestmentRevenueCriteria> getAllFavouriteRevenueCriteria(long UserId) {

        List<PersistedInvestmentRevenueCriteria> list = em
                .createQuery("select m from PersistedInvestmentRevenueCriteria m left join fetch m.user t where t.id=:Id AND m.isFavourite=true", PersistedInvestmentRevenueCriteria.class)
                .setParameter("Id", UserId)
                .getResultList();
        return list;
    }

    @Override
    public List<PersistedInvestmentRevenueCriteria> getAllRevenueCriteria() {
        return  em.createQuery("select m from PersistedInvestmentRevenueCriteria m", PersistedInvestmentRevenueCriteria.class)
                  .getResultList();
    }

    @Override
    public LinkedHashSet<PersistedComparatorIndicatorCriteria> getAllFavouriteComparatorIndicatorCriteria(long UserId) {
        List<PersistedComparatorIndicatorCriteria> list = em
                .createQuery("select m from PersistedComparatorIndicatorCriteria m left join fetch m.user t where t.id=:Id AND m.isFavouriteChecked=true", PersistedComparatorIndicatorCriteria.class)
                .setParameter("Id", UserId)
                .getResultList();
        return new LinkedHashSet<>(list);
    }
}
