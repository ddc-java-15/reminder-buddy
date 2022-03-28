package edu.cnm.deepdive.reminderbuddy.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.reminderbuddy.model.entity.Card;
import edu.cnm.deepdive.reminderbuddy.model.entity.User;
import io.reactivex.rxjava3.core.Single;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Dao
public interface CardDao {

  @Insert
  Single<Long> insert(Card card);

  @Insert
  Single<List<Long>> insert(Card... cards);

  @Insert
  Single<List<Long>> insert(Collection<Card> cards);

  @Update
  Single<Integer> update(Card card);

  @Update
  Single<Integer> update(Card... cards);

  @Update
  Single<Integer> update(Collection<Card> cards);

  @Delete
  Single<Integer> delete(Card card);

  @Delete
  Single<Integer> delete(Card... cards);

  @Delete
  Single<Integer> delete(Collection<Card> cards);

  @Query("SELECT * FROM card WHERE card_id = :id")
  LiveData<Card> select(long id);

  @Query("SELECT * FROM card ORDER BY card_id ASC")
  LiveData<List<Card>> select();

  @Query("SELECT * FROM card WHERE user_id = :userId ORDER BY card_id ASC")
  LiveData<List<Card>> selectByUser(long userId);

  @Query("SELECT * FROM card WHERE user_id = :userId AND date >= :date ORDER BY card_id ASC")
  LiveData<List<Card>> selectByUser(long userId, Date date);


}
