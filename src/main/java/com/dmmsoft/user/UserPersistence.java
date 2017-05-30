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

        //List<User> users = em.createQuery("select distinct m from User m where m.id=1").getResultList();
                //.setParameter("Id", userId).getResultList();

        //return users.stream().findAny().orElseThrow(NullPointerException::new);
    }

    @Override
    @Transactional
    public User get(String userEmail) {
        User user = em.find(User.class, userEmail);

        return  user;

        //List<User> users = em.createQuery("select distinct m from User m where m.id=1").getResultList();
        //.setParameter("Id", userId).getResultList();

        //return users.stream().findAny().orElseThrow(NullPointerException::new);
    }



}
