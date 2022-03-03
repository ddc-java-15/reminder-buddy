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
public class Response {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "response_id")
  private long id;

  @ColumnInfo(name = "correct_response")
  private String correctResponse;

  @ColumnInfo(name = "incorrect_response")
  private String incorrectResponse;

  @ColumnInfo(name = "next_reminder")
  @NonNull
  private Date nextReminder;

  @ColumnInfo(name = "card_id", index = true)
  private long cardId;

  @ColumnInfo(name = "notification_id", index = true)
  private long notificationId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCorrectResponse() {
    return correctResponse;
  }

  public void setCorrectResponse(String correctResponse) {
    this.correctResponse = correctResponse;
  }

  public String getIncorrectResponse() {
    return incorrectResponse;
  }

  public void setIncorrectResponse(String incorrectResponse) {
    this.incorrectResponse = incorrectResponse;
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

  public long getNotificationId() {
    return notificationId;
  }

  public void setNotificationId(long notificationId) {
    this.notificationId = notificationId;
  }
}
