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
 * Creates an instance of response to be utilized by the database and other classes.
 */
public class Response {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "response_id")
  private long id;

  @ColumnInfo(name = "user_response")
  private String userResponse;

  private boolean correct;

  @ColumnInfo(name = "next_reminder")
  private Date nextReminder;

  @ColumnInfo(name = "card_id", index = true)
  private long cardId;

  @ColumnInfo(name = "notification_id", index = true)
  private Long notificationId;

  /**
   *
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   *
   * @return
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   *
   * @return
   */
  public String getUserResponse() {
    return userResponse;
  }

  /**
   *
   * @return
   */
  public void setUserResponse(String userResponse) {
    this.userResponse = userResponse;
  }

  /**
   *
   * @return
   */
  public boolean isCorrect() {
    return correct;
  }

  /**
   *
   * @return
   */
  public void setCorrect(boolean correct) {
    this.correct = correct;
  }

  /**
   *
   * @return
   */
  public Date getNextReminder() {
    return nextReminder;
  }

  /**
   *
   * @return
   */
  public void setNextReminder(Date nextReminder) {
    this.nextReminder = nextReminder;
  }

  /**
   *
   * @return
   */
  public long getCardId() {
    return cardId;
  }

  /**
   *
   * @return
   */
  public void setCardId(long cardId) {
    this.cardId = cardId;
  }

  /**
   *
   * @return
   */
  public Long getNotificationId() {
    return notificationId;
  }

  /**
   *
   * @return
   */
  public void setNotificationId(Long notificationId) {
    this.notificationId = notificationId;
  }
}
