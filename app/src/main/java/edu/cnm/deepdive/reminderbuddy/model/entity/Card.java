package edu.cnm.deepdive.reminderbuddy.model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import java.util.Date;

@Entity(
    tableName = "card",
    foreignKeys = @ForeignKey(
        entity = User.class,
        childColumns = "user_id",
        parentColumns = "user_id",
        onDelete = ForeignKey.CASCADE
    )

)
/**
 * Implements a card that stores an event to be used as a reminder.
 */
public class Card {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "card_id")
  private long id;

  @ColumnInfo(name = "user_id", index = true)
  private long userId;

  @NonNull
  private Date created = new Date();

  private String information;

  @NonNull
  private Date date = new Date();

  private String location;

  @ColumnInfo(name = "sound_bite")
  private String soundBite;

  private String hint;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  @NonNull
  public Date getCreated() {
    return created;
  }

  public void setCreated(@NonNull Date created) {
    this.created = created;
  }

  public String getInformation() {
    return information;
  }

  public void setInformation(String information) {
    this.information = information;
  }

  @NonNull
  public Date getDate() {
    return date;
  }

  public void setDate(@NonNull Date date) {
    this.date = date;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getSoundBite() {
    return soundBite;
  }

  public void setSoundBite(String soundBite) {
    this.soundBite = soundBite;
  }

  public String getHint() {
    return hint;
  }

  public void setHint(String hint) {
    this.hint = hint;
  }
}