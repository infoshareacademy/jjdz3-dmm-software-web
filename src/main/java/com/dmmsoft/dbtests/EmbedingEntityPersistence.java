package com.dmmsoft.dbtests;

import com.dmmsoft.app.analyzer.analyses.revenue.InvestmentRevenueCriteria;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by milo on 22.05.17.
 */

@Default
public class EmbedingEntityPersistence implements IEmbedingEntityService {


    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void addEmbedingEntity(EmbedingEntity embedingEntity) {
        em.persist(embedingEntity);
    }

    @Override
    @Transactional
    public void addJarEntity(InvestmentRevenueCriteria investmentRevenueCriteria) {
        em.persist(investmentRevenueCriteria);
    }

    @Override
    @Transactional
    public void addUser(User user) {
        em.persist(user);
    }
}



