package com.dmmsoft.analyzer;

import com.dmmsoft.analyzer.analysis.LocalInvestmentRevenueCriteria;

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
    @Transactional
   public void addFavourite(Favourite te){
        em.persist(te);
    }


    @Override
    public List<LocalInvestmentRevenueCriteria> getAllUserFavoutiteCriteria(long UserId) {

       //TODO filter by user Id
       //.createQuery("select m from LocalInvestmentRevenueCriteria m left join fetch m.user t where t.id=1", LocalInvestmentRevenueCriteria.class) // empty!
       //.createQuery("select m from LocalInvestmentRevenueCriteria m where m.id=:Id"  //this works
        // select * from LocalInvestmentRevenueCriteria t1 LEFT JOIN User t2 ON t1.UserId=t2.id where t2.id=1 //ok


       // UserId=1;

       List<LocalInvestmentRevenueCriteria> criteria = em
               .createQuery("select m from LocalInvestmentRevenueCriteria m left join fetch m.user t where t.id=:Id", LocalInvestmentRevenueCriteria.class)
               .setParameter("Id", UserId)
               .getResultList();

       return criteria;
    }
}
