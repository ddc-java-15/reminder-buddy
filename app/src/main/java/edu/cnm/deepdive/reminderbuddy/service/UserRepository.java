package edu.cnm.deepdive.reminderbuddy.service;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.reminderbuddy.model.dao.UserDao;
import edu.cnm.deepdive.reminderbuddy.model.entity.User;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;


public class UserRepository {

  private final Context context;

  private final UserDao userDao;

  private final GoogleSignInService signInService;

  private final MutableLiveData<User> user;


  public UserRepository(Context context) {
    this.context = context;
    ReminderBuddyDatabase database = ReminderBuddyDatabase.getInstance();
    userDao = database.getUserDao();
    signInService = GoogleSignInService.getInstance();
    user = new MutableLiveData<>();
    getOrCreate()
        .subscribe(
            this.user::postValue,
            (throwable) -> Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable)
        );
  }

  public LiveData<User> getUser() {
    return user;
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

  public Single<User> getOrCreate() {
    return signInService
            .refresh()
            .flatMap((account) -> userDao.getByOauthKey(account.getId())
                .switchIfEmpty(
                    Single
                        .fromCallable(() -> {
                          User user = new User();
                          user.setOauthKey(account.getId());
                          user.setName(account.getDisplayName());
                          return user;
                        })
                        .flatMap(this::save)
                )
            )
        .subscribeOn(Schedulers.io());


  }
}
