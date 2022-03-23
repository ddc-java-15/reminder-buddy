package edu.cnm.deepdive.reminderbuddy.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.reminderbuddy.model.dao.CardDao;
import edu.cnm.deepdive.reminderbuddy.model.dao.UserDao;
import edu.cnm.deepdive.reminderbuddy.model.entity.Card;
import edu.cnm.deepdive.reminderbuddy.model.entity.User;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.List;

public class CardRepository {

  private final Context context;

  private final CardDao cardDao;

  public CardRepository(Context context) {
    this.context = context;
    ReminderBuddyDatabase database = ReminderBuddyDatabase.getInstance();
    cardDao = database.getCardDao();
  }

  public LiveData<Card> get(long id) {
    return cardDao.select(id);
  }

  public LiveData<List<Card>> getAll() {
    return cardDao.select();
  }

  public LiveData<List<Card>> getAllByUser(long userId) {
    return cardDao;
  }

  public Single<Card> save(Card card) {
    return (
        (card.getId() == 0)
            ? cardDao
            .insert(card)
            .map((id) -> {
              card.setId(id);
              return card;
            })
            : cardDao
                .update(card)
                .map((count) -> card)
    )
        .subscribeOn(Schedulers.io());
  }

  public Completable delete(Card card) {

    return (
        (card.getId() == 0)
            ? Completable.complete()
            : cardDao
                .delete(card)
                .ignoreElement()
    )
        .subscribeOn(Schedulers.io());
  }


}
