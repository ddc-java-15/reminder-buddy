package model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity
public class Response {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "response_id")
  private long id;

  @NonNull
  private String correctReponse;

  @NonNull
  private String incorrectResponse;

  @NonNull
  private Date setNextReminder;

  public String getCorrectReponse() {
    return correctReponse;
  }

  public void setCorrectReponse(String correctReponse) {
    this.correctReponse = correctReponse;
  }

  public String getIncorrectResponse() {
    return incorrectResponse;
  }

  public void setIncorrectResponse(String incorrectResponse) {
    this.incorrectResponse = incorrectResponse;
  }
  @NonNull
  public Date getSetNextReminder() {
    return setNextReminder;
  }
  @NonNull
  public void setSetNextReminder(Date setNextReminder) {
    this.setNextReminder = setNextReminder;
  }
}
