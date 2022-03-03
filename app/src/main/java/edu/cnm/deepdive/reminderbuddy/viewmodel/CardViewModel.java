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
import java.util.Date;
import java.util.List;

public class CardViewModel extends AndroidViewModel implements DefaultLifecycleObserver {

  private final CardRepository repository;

  private final LiveData<Card> card;

  private final MutableLiveData<Throwable> throwable;

  private final MutableLiveData<Long> cardId;

  private final CompositeDisposable pending;



  public CardViewModel(@NonNull Application application) {
    super(application);
    repository = new CardRepository(application);
    cardId = new MutableLiveData<>();
    card = Transformations.switchMap(cardId, repository::get);
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  @Override
  public void onStop(@NonNull LifecycleOwner owner) {
    DefaultLifecycleObserver.super.onStop(owner);
    pending.clear();
  }

  public LiveData<Card> getCard() {
    return card;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

  public LiveData<List<Card>> getCards() {
    return repository.getAll();
  }

  public void save(Card card) {
    Disposable disposable = repository
        .save(card)
        .subscribe(
            (c) -> {},
            this::postThrowable
        );
    pending.add(disposable);
  }

  public void delete(Card card) {
    //noinspection ResultOfMethodCallIgnored
    repository
        .delete(card)
        .subscribe(
            () -> {},
            this::postThrowable
        );
  }

  private void postThrowable(Throwable throwable) {
    Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
    this.throwable.postValue(throwable);

  }
}
