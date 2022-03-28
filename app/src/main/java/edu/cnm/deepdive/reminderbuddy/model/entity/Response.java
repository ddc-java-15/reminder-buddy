package edu.cnm.deepdive.reminderbuddy.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(
    tableName = "response",
    foreignKeys = {
        @ForeignKey(
            entity = Card.class,
            parentColumns = "card_id",
            childColumns = "card_id",
            onDelete = ForeignKey.RESTRICT
        ),
        @ForeignKey(
            entity = Notification.class,
            parentColumns = "notification_id",
            childColumns = "notification_id",
            onDelete = ForeignKey.RESTRICT
        )
    }
)
/**
 *
 */

public class Response {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "response_id")
  private long id;

  @ColumnInfo(name = "user_response")
  private String userResponse;

  private boolean correct;

  @ColumnInfo(name = "next_reminder")
  @NonNull
  private Date nextReminder;

  @ColumnInfo(name = "card_id", index = true)
  private long cardId;

  @ColumnInfo(name = "notification_id", index = true)
  private Long notificationId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUserResponse() {
    return userResponse;
  }

  public void setUserResponse(String userResponse) {
    this.userResponse = userResponse;
  }

  public boolean isCorrect() {
    return correct;
  }

  public void setCorrect(boolean correct) {
    this.correct = correct;
  }

  @NonNull
  public Date getNextReminder() {
    return nextReminder;
  }

  public void setNextReminder(@NonNull Date nextReminder) {
    this.nextReminder = nextReminder;
  }

  public long getCardId() {
    return cardId;
  }

  public void setCardId(long cardId) {
    this.cardId = cardId;
  }

  public Long getNotificationId() {
    return notificationId;
  }

  public void setNotificationId(Long notificationId) {
    this.notificationId = notificationId;
  }
}
