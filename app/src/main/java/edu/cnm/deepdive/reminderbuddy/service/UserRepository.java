package edu.cnm.deepdive.reminderbuddy.service;

import android.content.Context;
import android.content.Entity;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.reminderbuddy.model.dao.UserDao;
import edu.cnm.deepdive.reminderbuddy.model.entity.User;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;


public class UserRepository {

  private final Context context;

  private final UserDao userDao;

  public UserRepository(Context context) {
    this.context = context;
    ReminderBuddyDatabase database = ReminderBuddyDatabase.getInstance();
    userDao = database.getUserDao();
  }

  public LiveData<User> get(long id) {
    return userDao.select(id);
  }

  public LiveData<List<User>> getAll() {
    return userDao.select();
  }

  public Single<User> save(User user) {
    return (
        (user.getId() == 0)
            ? userDao
                .insert(user)
                .map((id) -> {
                  user.setId(id);
                  return user;
            })
            : userDao
                .update(user)
                .map((count) -> user)
    )
        .subscribeOn(Schedulers.io());
  }

  public Completable delete(User user) {

    return (
        (user.getId() == 0)
            ? Completable.complete()
            : userDao
                .delete(user)
                .ignoreElement()
    )
        .subscribeOn(Schedulers.io());
  }
}
