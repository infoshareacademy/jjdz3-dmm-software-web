package com.dmmsoft.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by milo on 26.05.17.
 */
public interface IUserService {


  void  add(User user);

  User get(long userId);




}
