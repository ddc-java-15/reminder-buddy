package edu.cnm.deepdive.reminderbuddy.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import edu.cnm.deepdive.reminderbuddy.model.dao.CardDao;
import edu.cnm.deepdive.reminderbuddy.model.dao.NotificationDao;
import edu.cnm.deepdive.reminderbuddy.model.dao.ResponseDao;
import edu.cnm.deepdive.reminderbuddy.model.dao.UserDao;
import java.util.Date;
import edu.cnm.deepdive.reminderbuddy.model.entity.Card;
import edu.cnm.deepdive.reminderbuddy.model.entity.Notification;
import edu.cnm.deepdive.reminderbuddy.model.entity.Response;
import edu.cnm.deepdive.reminderbuddy.model.entity.User;
import edu.cnm.deepdive.reminderbuddy.service.ReminderBuddyDatabase.Converters;

@Database(
    entities =
        {User.class, Notification.class, Card.class, Response.class},
        version = 1
)
@TypeConverters({Converters.class})
public abstract class ReminderBuddyDatabase extends RoomDatabase {

  private static final String DB_NAME = "reminder-buddy";

  private static Application context;

  public static void setContext(Application context) {
    ReminderBuddyDatabase.context = context;
  }

  public static ReminderBuddyDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract UserDao getUserDao();

  public abstract ResponseDao getResponseDao();

  public abstract CardDao getCardDao();

  public abstract NotificationDao getNotificationDao();

  private static class InstanceHolder {

    private static final ReminderBuddyDatabase INSTANCE = Room
        .databaseBuilder(context, ReminderBuddyDatabase.class, DB_NAME)
        .build();
  }

  public static class Converters {

    @TypeConverter
    public static Long toLong(Date value) {
      return (value != null) ? value.getTime() : null ;
    }

    @TypeConverter
    public static Date toDate(Long value) {
      return (value != null) ? new Date(value) : null ;
    }
  }
}
