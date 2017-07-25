package com.dmmsoft.user.report;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by milo on 15.07.17.
 */

public interface IUserActivityService {
   void saveActivity(UserActivity userActivity);

   List<UserActivity> getAllUserActivity();

   void updateAllUserActivityFromMaserAPI(List<UserActivity> activities);



}
