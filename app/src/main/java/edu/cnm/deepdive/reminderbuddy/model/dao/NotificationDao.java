package edu.cnm.deepdive.reminderbuddy.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.reminderbuddy.model.entity.Notification;
import edu.cnm.deepdive.reminderbuddy.model.entity.User;
import io.reactivex.rxjava3.core.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface NotificationDao {

  @Insert
  Single<Long> insert(Notification notification);

  @Insert
  Single<List<Long>> insert(Notification... notifications);

  @Insert
  Single<List<Long>> insert(Collection<Notification> notifications);

  @Update
  Single<Integer> update(Notification notification);

  @Update
  Single<Integer> update(Notification... notifications);

  @Update
  Single<Integer> update(Collection<Notification> notifications);

  @Delete
  Single<Integer> delete(Notification notification);

  @Delete
  Single<Integer> delete(Notification... notifications);

  @Delete
  Single<Integer> delete(Collection<Notification> notifications);

  @Query("SELECT * FROM notification WHERE notification_id = :id")
  LiveData<Notification> select(long id);

  @Query("SELECT * FROM notification ORDER BY notification_id ASC")
  LiveData<List<Notification>> select();

}
