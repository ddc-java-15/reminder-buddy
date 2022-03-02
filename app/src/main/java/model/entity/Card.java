package model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import java.util.Date;

@Entity
public class Card {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "card_id", index = true)
  private long id;

  @Expose
  @NonNull
  private long created;

  @Expose
  @NonNull
  private long information;

  @Expose
  @ColumnInfo(name = "add_date")
  @NonNull
  private Date addDate;

  @Expose
  @ColumnInfo(name = "add_location")
  @NonNull
  private String addLocation;

  @Expose
  @ColumnInfo(name = "add_sound_bite")
  private long addSoundBite;

  public Card() {
  }

  public long getCreated() {
    return created;
  }

  public void setCreated(long created) {
    this.created = created;
  }

  public long getInformation() {
    return information;
  }

  public void setInformation(long information) {
    this.information = information;
  }

  public Date getAddDate() {
    return addDate;
  }

  public void setAddDate(Date addDate) {
    this.addDate = addDate;
  }

  public String getAddLocation() {
    return addLocation;
  }

  public void setAddLocation(long addLocation) {
    this.addLocation = String.valueOf(addLocation);
  }

  public long getAddSoundBite() {
    return addSoundBite;
  }

  public void setAddSoundBite(long addSoundBite) {
    this.addSoundBite = addSoundBite;
  }
}
