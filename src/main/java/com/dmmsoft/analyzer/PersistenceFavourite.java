package com.dmmsoft.analyzer;

import com.dmmsoft.analyzer.analysis.comparison.AnalysisComparisonContainer;
import com.dmmsoft.analyzer.analysis.investmentindicator.PersistedComparatorIndicatorCriteria;
import com.dmmsoft.analyzer.analysis.investmentindicator.PersistedIndicatorCriteria;
import com.dmmsoft.analyzer.analysis.investmentrevenue.InvestmentRevenueServlet;
import com.dmmsoft.analyzer.analysis.investmentrevenue.PersistedInvestmentRevenueCriteria;
import com.dmmsoft.app.analyzer.analyses.AnalysisCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashSet;
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

    @Override
    public List<AnalysisComparisonContainer> getAllUserFavouriteAnalysisContainers(long UserId) {
        List<AnalysisComparisonContainer> list = em
                .createQuery("select m from AnalysisComparisonContainer m left join fetch m.user t WHERE t.id=:Id AND m.isFavouriteChecked=true", AnalysisComparisonContainer.class)
                .setParameter("Id", UserId)
                .getResultList();
        return list;
    }

   /* @Override
    public List<PersistedIndicatorCriteria> getAllUserFavouriteAnalaysisCriteria(long UserId) {
       List<PersistedIndicatorCriteria> criteria = new ArrayList<>();

       for(AnalysisComparisonContainer container: this.getAllUserFavouriteAnalysisContainers(UserId)) {
            for(AnalysisCriteria analysisCriteria : container.getCriteriaSet()){
                if(analysisCriteria.getClass().isInstance(PersistedIndicatorCriteria.class)){
                    criteria.add((PersistedIndicatorCriteria) analysisCriteria);
                    LOGGER.info("is istance of PersistedIndicatorCriteria: true");
                }
                LOGGER.info("is istance of PersistedIndicatorCriteria: false");
            }
       }
        return criteria;
    }*/



}
