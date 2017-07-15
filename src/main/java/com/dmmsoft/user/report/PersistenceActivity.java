package com.dmmsoft.user.report;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by milo on 15.07.17.
 */

@Default
public class PersistenceActivity implements IUserActivityService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void saveActivity(UserActivity userActivity) {
    em.persist(userActivity);
    }

    @Override
    public List<UserActivity> getAllUserActivity() {

        List<UserActivity> activities = em.createQuery("select m from UserActivity m", UserActivity.class)
                .getResultList();
        return activities;
    }
}
