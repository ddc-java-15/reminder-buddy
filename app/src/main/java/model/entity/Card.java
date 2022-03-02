package model.entity;


import androidx.room.Entity;
import java.util.Date;

@Entity
public class Card {

  private long created;

  private long information;

  private Date addDate;

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
