package model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity
public class Card {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "card_id")
  private long id;

  @NonNull
  private long created;

  @NonNull
  private long information;

  @NonNull
  private Date addDate;

  @NonNull
  private String addLocation;

  private long addMusic;

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

  public long getAddMusic() {
    return addMusic;
  }

  public void setAddMusic(long addMusic) {
    this.addMusic = addMusic;
  }
}
