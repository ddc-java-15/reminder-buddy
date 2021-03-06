package edu.cnm.deepdive.reminderbuddy.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.reminderbuddy.model.dao.CardDao;
import edu.cnm.deepdive.reminderbuddy.model.dao.ResponseDao;
import edu.cnm.deepdive.reminderbuddy.model.entity.Card;
import edu.cnm.deepdive.reminderbuddy.model.entity.Response;
import edu.cnm.deepdive.reminderbuddy.model.view.ResponseSummary;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 *
 */
public class CardRepository {

  private final Context context;

  private final CardDao cardDao;

  private final ResponseDao responseDao;

  /**
   *
   * @param context
   */
  public CardRepository(Context context) {
    this.context = context;
    ReminderBuddyDatabase database = ReminderBuddyDatabase.getInstance();
    cardDao = database.getCardDao();
    responseDao = database.getResponseDao();
  }

  /**
   *
   * @param id
   * @return
   */
  public LiveData<Card> get(long id) {
    return cardDao.select(id);
  }

  /**
   *
   * @return
   */
  public LiveData<List<Card>> getAll() {
    return cardDao.select();
  }

  /**
   *
   * @param userId
   * @return
   */
  public LiveData<List<Card>> getAllByUser(long userId) {
    return (cardDao.selectByUser(userId));
  }

  /**
   *
   * @param userId
   * @param date
   * @return
   */
  public LiveData<List<Card>> getAllByUser(long userId, Date date) {
    return (cardDao.selectByUser(userId, date));
  }

  /**
   *
   * @param card
   * @return
   */
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

  /**
   *
   * @param response
   * @return
   */
  public Single<Response> save(Response response) {
    return (
        (response.getId() == 0)
            ? responseDao
            .insert(response)
            .map((id) -> {
              response.setId(id);
              return response;
            })
            : responseDao
                .update(response)
                .map((count) -> response)
    )
        .subscribeOn(Schedulers.io());
  }

  /**
   *
   * @param card
   * @return
   */
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

  /**
   *
   * @param userId
   * @return
   */
  public Single<Map<Boolean, Long>> getSummary(long userId) {
    return responseDao
        .summarize(userId)
        .map((summaries) -> summaries
            .stream()
            .collect(Collectors.toMap(ResponseSummary::isCorrect, ResponseSummary::getCount))
        )
        .subscribeOn(Schedulers.io());
  }
}
