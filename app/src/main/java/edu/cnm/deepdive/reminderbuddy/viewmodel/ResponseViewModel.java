package edu.cnm.deepdive.reminderbuddy.viewmodel;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import edu.cnm.deepdive.reminderbuddy.model.entity.Card;
import edu.cnm.deepdive.reminderbuddy.model.entity.Response;
import edu.cnm.deepdive.reminderbuddy.service.CardRepository;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class ResponseViewModel extends AndroidViewModel implements DefaultLifecycleObserver {

  private final CardRepository cardRepository;
  private final MutableLiveData<Long> cardId;
  private final LiveData<Card> card;
  private final MutableLiveData<Long> userId;
  private final LiveData<List<Card>> futureCards;
  private final MutableLiveData<Map<Boolean, Long>> summary;
  private final MutableLiveData<Response> response;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  /**
   *
   * @param application
   */
  public ResponseViewModel(@NonNull Application application) {
    super(application);
    cardRepository = new CardRepository(application);
    cardId = new MutableLiveData<>();
    card = Transformations.switchMap(cardId, cardRepository::get);
    userId = new MutableLiveData<>();
    futureCards = Transformations.switchMap(userId,
        (id) -> cardRepository.getAllByUser(id, new Date()));
    summary = new MutableLiveData<>();
    response = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  /**
   *
   * @param cardId
   */
  public void setCardId(long cardId) {
    this.cardId.setValue(cardId);
  }

  /**
   *
   * @return
   */
  public LiveData<Card> getCard() {
    return card;
  }

  /**
   *
   * @param userId
   */
  public void setUserId(long userId) {
    this.userId.setValue(userId);
    refreshSummary(userId);
  }

  /**
   *
   * @return
   */
  public LiveData<List<Card>> getFutureCards() {
    return futureCards;
  }

  /**
   *
   * @return
   */
  public LiveData<Map<Boolean, Long>> getSummary() {
    return summary;
  }

  /**
   *
   * @param response
   */
  public void save(Response response) {
    Disposable disposable = cardRepository
        .save(response)
        .subscribe(
            (resp) -> {
            },
            this::postThrowable
        );
    pending.add(disposable);
  }

  /**
   *
   * @return
   */
  public MutableLiveData<Response> getResponse() {
    return response;
  }

  /**
   *
   * @return
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  private void refreshSummary(long userId) {
    Disposable disposable = cardRepository
        .getSummary(userId)
        .subscribe(
            summary::postValue,
            this::postThrowable
        );
    pending.add(disposable);
  }

  @Override
  public void onStop(@NonNull LifecycleOwner owner) {
    DefaultLifecycleObserver.super.onStop(owner);
    pending.clear();
  }

  private void postThrowable(Throwable throwable) {
    Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);
  }
}
