package edu.cnm.deepdive.reminderbuddy;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.reminderbuddy.service.GoogleSignInService;
import edu.cnm.deepdive.reminderbuddy.service.ReminderBuddyDatabase;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Initializes (in the {@link #onCreate()} method) application-level resources. This class
 * <strong>must</strong> be referenced in {@code AndroidManifest.xml}, or it will not be loaded and
 * used by the Android system.
 */
public class ReminderBuddyApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setContext(this);
    Stetho.initializeWithDefaults(this);
    ReminderBuddyDatabase.setContext(this);
    ReminderBuddyDatabase
        .getInstance()
        .getUserDao()
        .delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
  }

}
