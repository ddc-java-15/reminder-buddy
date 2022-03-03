package edu.cnm.deepdive.reminderbuddy.model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import java.util.Date;

@Entity(
    tableName = "card"

)
public class Card {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "card_id")
  private long id;


  @NonNull
  private Date created = new Date();

  private String information;

  @NonNull
  private Date date;

  private String location;

  @ColumnInfo(name = "sound_bite")
  private String soundBite;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
}