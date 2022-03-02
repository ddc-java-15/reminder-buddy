package model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import java.util.Date;

@Entity
public class Response {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "response_id")
  private long id;

  @Expose
  @NonNull
  private String correctReponse;

  @Expose
  @NonNull
  private String incorrectResponse;

  @Expose
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
