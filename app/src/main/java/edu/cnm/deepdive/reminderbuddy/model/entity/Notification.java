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
public class Notification {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "notification_id")
  private long id;

  @ColumnInfo(name = "card_id", index = true)
  private long cardId;

  private Date frequency;

  private String soundbite;

  private String hint;

  public long getCardId() {
    return cardId;
  }

  public void setCardId(long cardId) {
    this.cardId = cardId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Date getFrequency() {
    return frequency;
  }

  public void setFrequency(Date frequency) {
    this.frequency = frequency;
  }

  public String getSoundbite() {
    return soundbite;
  }

  public void setSoundbite(String soundbite) {
    this.soundbite = soundbite;
  }

  public String getHint() {
    return hint;
  }

  public void setHint(String hint) {
    this.hint = hint;
  }
}
