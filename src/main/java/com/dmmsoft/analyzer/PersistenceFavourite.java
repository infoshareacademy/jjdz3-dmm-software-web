package com.dmmsoft.analyzer;
import com.dmmsoft.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.dmmsoft.analyzer.analysis.investmentrevenue.PersistedInvestmentRevenueCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Singleton;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceFavourite.class);
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
    @Transactional
    public List<PersistedInvestmentRevenueCriteria> getAllRevenueCriteria() {
        List<PersistedInvestmentRevenueCriteria> list = em
                .createQuery("select m from PersistedInvestmentRevenueCriteria m left join fetch m.user", PersistedInvestmentRevenueCriteria.class)
                .getResultList();
        return list;
    }

    @Override
    public List<AnalysisComparisonContainer> getAllUserFavouriteAnalysisContainers(long UserId) {
        List<AnalysisComparisonContainer> list = em
                .createQuery("select m from AnalysisComparisonContainer m left join fetch m.user t WHERE t.id=:Id AND m.isFavouriteChecked=true", AnalysisComparisonContainer.class)
                .setParameter("Id", UserId)
                .getResultList();
        return list;
    }

}
