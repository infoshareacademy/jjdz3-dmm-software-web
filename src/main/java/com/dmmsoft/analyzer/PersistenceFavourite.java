package com.dmmsoft.analyzer;

import com.dmmsoft.app.analyzer.analyses.AnalysisCriteria;
import com.dmmsoft.dbtests.LocalInvestmentRevenueCriteria;
import com.dmmsoft.dbtests.User;

import javax.enterprise.inject.Default;
import javax.jws.soap.SOAPBinding;
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

       List<LocalInvestmentRevenueCriteria> criteria = em
               .createQuery("select m from LocalInvestmentRevenueCriteria m", LocalInvestmentRevenueCriteria.class)
               //.setParameter("id", UserId)
               .getResultList();

       return criteria;
    }
}
