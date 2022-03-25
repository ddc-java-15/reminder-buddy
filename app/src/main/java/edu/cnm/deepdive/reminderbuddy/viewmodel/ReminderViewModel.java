package edu.cnm.deepdive.reminderbuddy.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.DefaultLifecycleObserver;

public class ReminderViewModel extends AndroidViewModel implements DefaultLifecycleObserver {

  public ReminderViewModel(@NonNull Application application) {
    super(application);
  }
}
