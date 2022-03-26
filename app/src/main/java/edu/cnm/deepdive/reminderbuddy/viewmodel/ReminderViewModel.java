package edu.cnm.deepdive.reminderbuddy.viewmodel;

import android.app.Application;
import android.provider.CalendarContract.Reminders;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ReminderViewModel extends AndroidViewModel implements DefaultLifecycleObserver {

  private final MutableLiveData<Reminders> reminder;
  private final Throwable throwable;

  public ReminderViewModel(@NonNull Application application,
      MutableLiveData<Reminders> reminder, Throwable throwable) {
    super(application);
    this.reminder = reminder;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  public LiveData<Reminders> getReminder() {
    return reminder;
  }
}
