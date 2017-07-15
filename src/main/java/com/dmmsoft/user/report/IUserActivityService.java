package com.dmmsoft.user.report;

import java.util.List;

/**
 * Created by milo on 15.07.17.
 */
public interface IUserActivityService {
   void saveActivity(UserActivity userActivity);


   List<UserActivity> getAllUserActivity();

}
