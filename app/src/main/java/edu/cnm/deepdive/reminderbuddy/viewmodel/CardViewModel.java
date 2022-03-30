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
import edu.cnm.deepdive.reminderbuddy.service.CardRepository;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.List;

/**
 * Retrieves an instance of the card view model.
 */
public class CardViewModel extends AndroidViewModel implements DefaultLifecycleObserver {

  private final CardRepository repository;

  private final LiveData<Card> card;

  private final MutableLiveData<Throwable> throwable;

  private final MutableLiveData<Long> cardId;

  private final MutableLiveData<Long> userId;

  private final LiveData<List<Card>> cards;

  private final CompositeDisposable pending;


  /**
   *
   * @param application
   */
  public CardViewModel(@NonNull Application application) {
    super(application);
    repository = new CardRepository(application);
    cardId = new MutableLiveData<>();
    card = Transformations.switchMap(cardId, repository::get);
    throwable = new MutableLiveData<>();
    userId = new MutableLiveData<>();
    cards = Transformations.switchMap(this.userId, repository::getAllByUser);
    pending = new CompositeDisposable();
  }

  @Override
  public void onStop(@NonNull LifecycleOwner owner) {
    DefaultLifecycleObserver.super.onStop(owner);
    pending.clear();
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
   * @return
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  /**
   *
   * @return
   */
  public LiveData<List<Card>> getCards() {
    return cards;
  }

  /**
   *
   * @param userId
   */
  public void setUserId (long userId) {
    this.userId.setValue(userId);
  }

  /**
   *
   * @param card
   */
  public void save(Card card) {
    Disposable disposable = repository
        .save(card)
        .subscribe(
            (c) -> {},
            this::postThrowable
        );
    pending.add(disposable);
  }

  /**
   *
   * @param card
   */
  public void delete(Card card) {
    //noinspection ResultOfMethodCallIgnored
    repository
        .delete(card)
        .subscribe(
            () -> {},
            this::postThrowable
        );
  }

  /**
   *
   * @param id
   */
  public void setCardId (long id) {
    cardId.setValue(id);

  }
  private void postThrowable(Throwable throwable) {
    Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);

  }
}
