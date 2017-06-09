package com.dmmsoft.user;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by milo on 26.05.17.
 */
public interface IUserService {


  void  add(User user);

  User get(long userId);

  List<User> getUserByEmail(String userEmail);

  void update(User user);

  List<User> getAllUsers();


}
