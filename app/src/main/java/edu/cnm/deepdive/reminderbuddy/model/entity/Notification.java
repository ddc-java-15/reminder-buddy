package edu.cnm.deepdive.reminderbuddy.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(
    tableName = "notification",
    foreignKeys = {
    @ForeignKey(
        entity = Card.class,
        parentColumns = "card_id",
        childColumns = "card_id",
        onDelete = ForeignKey.RESTRICT
    )
}
)
/**
 *
 */
public class Notification {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "notification_id")
  private long id;

  @ColumnInfo(name = "card_id", index = true)
  private long cardId;

  private Date frequency;

  private String soundbite;

  private String hint;

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
  public Date getFrequency() {
    return frequency;
  }

  /**
   *
   * @return
   */
  public void setFrequency(Date frequency) {
    this.frequency = frequency;
  }

  /**
   *
   * @return
   */
  public String getSoundbite() {
    return soundbite;
  }

  /**
   *
   * @return
   */
  public void setSoundbite(String soundbite) {
    this.soundbite = soundbite;
  }

  /**
   *
   * @return
   */
  public String getHint() {
    return hint;
  }

  /**
   *
   * @return
   */
  public void setHint(String hint) {
    this.hint = hint;
  }
}
