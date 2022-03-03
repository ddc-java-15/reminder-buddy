package edu.cnm.deepdive.reminderbuddy.service;

import android.content.Context;
import android.content.Entity;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.reminderbuddy.model.dao.UserDao;
import edu.cnm.deepdive.reminderbuddy.model.entity.User;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;


public class UserRepository {

private final Context context;

private final UserDao userDao;

  public UserRepository(Context context) {
    this.context = context;
  }

  public UserRepository(UserDao userDao) {
    this.userDao = userDao;
  }

  public LiveData<UserDao> get(long id) {
    return new LiveData<UserDao>;
  }

  public LiveData<List>User>> getAll() {

  }
  public Single<User> save(User user) {
// Get an instance in insert it into the database.
    // Using a get, and then insert? Casting?
    // Using a new instance to save over the current instance?
  }

  public Completable delete(User user) {

  }
}
