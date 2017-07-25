package com.dmmsoft.user.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by milo on 15.07.17.
 */

@Default
public class PersistenceActivity implements IUserActivityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceActivity.class);

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

    @Override
    @Transactional
    public void updateAllUserActivityFromMaserAPI(List<UserActivity> activities) {

        em.createQuery("delete from UserActivity").executeUpdate();

        for (UserActivity item : activities){

            UserActivity itemToPersist = new UserActivity();
            itemToPersist.setActivityDateTime(item.getActivityDateTime());
            itemToPersist.setActivityName(item.getActivityName());
            itemToPersist.setLogin(item.getLogin());
            itemToPersist.setSessionId(item.getSessionId());

            saveActivity(itemToPersist);

            LOGGER.info("UPDATE FROM MASTER API: {} {} {} {} {}",item.getId(),
                    item.getActivityDateTime(),
                    item.getActivityName(),
                    item.getLogin(),
                    item.getSessionId());
        }

    }
}
