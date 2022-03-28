package edu.cnm.deepdive.reminderbuddy.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.reminderbuddy.model.entity.Response;
import edu.cnm.deepdive.reminderbuddy.model.entity.User;
import edu.cnm.deepdive.reminderbuddy.model.view.ResponseSummary;
import io.reactivex.rxjava3.core.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface ResponseDao {

  @Insert
  Single<Long> insert(Response response);

  @Insert
  Single<List<Long>> insert(Response... responses);

  @Insert
  Single<List<Long>> insert(Collection<Response> responses);

  @Update
  Single<Integer> update(Response response);

  @Update
  Single<Integer> update(Response... responses);

  @Update
  Single<Integer> update(Collection<Response> responses);

  @Delete
  Single<Integer> delete(Response response);

  @Delete
  Single<Integer> delete(Response... responses);

  @Delete
  Single<Integer> delete(Collection<Response> responses);

  @Query("SELECT * FROM response WHERE response_id = :id")
  LiveData<Response> select(long id);

  @Query("SELECT * FROM response ORDER BY response_id ASC")
  LiveData<List<Response>> select();

  @Query("SELECT * FROM response_summary WHERE user_id = :userId ORDER BY correct")
  Single<List<ResponseSummary>> summarize(long userId);
}
