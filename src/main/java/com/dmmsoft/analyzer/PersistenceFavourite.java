package com.dmmsoft.dbtest;

import com.dmmsoft.app.analyzer.analyses.stats.ItemStatsCriteria;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by milo on 19.05.17.
 */

@Default
public class TestPersistenceEntity implements ITestStorageService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
   public void addTestEntity(TestEntity te){
        em.persist(te);
    }






}
