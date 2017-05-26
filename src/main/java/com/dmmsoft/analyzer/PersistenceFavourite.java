package com.dmmsoft.analyzer;

import com.dmmsoft.dbtests.LocalInvestmentRevenueCriteria;

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

// select m from LocalInvestmentRevenueCriteria m left join fetch m.user t where t.id=:id
        //.setParameter("id", UserId)



       List<LocalInvestmentRevenueCriteria> criteria = em
               .createQuery("select m from LocalInvestmentRevenueCriteria m", LocalInvestmentRevenueCriteria.class)
               .getResultList();

       return criteria;
    }
}
