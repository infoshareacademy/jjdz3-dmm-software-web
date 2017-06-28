package com.dmmsoft.user;

import com.dmmsoft.analyzer.analysis.investmentrevenue.InvestmentRevenueServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by milo on 26.05.17.
 */


public class UserPersistence implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPersistence.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public User get(long userId) {
        return em.find(User.class, userId);
    }

    @Override
    public List<User> getUserByEmail(String userEmail) {

        // TODO implement criteria
        return em.createQuery("select distinct m from User m where m.login=:login", User.class)
                .setParameter("login", userEmail)
                .getResultList();
    }

    @Override
    public List<User> getAllUsers() {
        return em.createQuery("select m from User m", User.class)
                .getResultList();
    }

    @Override
    @Transactional
    public void update(User user) {
       User userToUpdate =  em.find(User.class, user.getId());
       userToUpdate.setFavourites(user.getFavourites());
       userToUpdate.setLastLoginDateTime(user.getLastLoginDateTime());
       userToUpdate.setFavourireIndicatorsCompareSet(user.getFavourireIndicatorsCompareSet());
       userToUpdate.setFavouriteInvestmentIndicators(user.getFavouriteInvestmentIndicators());

       userToUpdate.setComparisonContainers(user.getComparisonContainers());
       em.merge(userToUpdate);
    }
}
