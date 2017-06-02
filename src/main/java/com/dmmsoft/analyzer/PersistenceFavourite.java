package com.dmmsoft.analyzer;

import com.dmmsoft.analyzer.analysis.LocalInvestmentRevenueCriteria;
import com.dmmsoft.user.User;

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
   public void addFavourite(LocalInvestmentRevenueCriteria criteria, User user){

        user.getFavourites().add(criteria);
        em.persist(user);
    }


    @Override
    public List<LocalInvestmentRevenueCriteria> getAllUserFavoutiteCriteria(long UserId) {

       List<LocalInvestmentRevenueCriteria> criteria = em
               .createQuery("select m from LocalInvestmentRevenueCriteria m left join fetch m.user t where t.id=:Id", LocalInvestmentRevenueCriteria.class)
               .setParameter("Id", UserId)
               .getResultList();

       return criteria;
    }
}
