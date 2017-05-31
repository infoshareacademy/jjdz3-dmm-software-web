package com.dmmsoft.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by milo on 26.05.17.
 */


public class UserPersistence implements IUserService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void add(User user) {
        em.persist(user);
    }

    @Override
    @Transactional
    public User get(long userId) {
        User user = em.find(User.class, userId);
        return  user;

    }

    @Override
    @Transactional
    public List<User> getUserByEmail(String userEmail) {

     // TODO implement criteria
     return em.createQuery("select distinct m from User m where m.login=:login", User.class)
                .setParameter("login", userEmail).getResultList();

     }



}
