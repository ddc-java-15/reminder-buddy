package model.entity;

import androidx.room.Entity;
import java.util.Date;

@Entity
public class Response {

  private String correctReponse;

  private String incorrectResponse;

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

  public Date getSetNextReminder() {
    return setNextReminder;
  }

  public void setSetNextReminder(Date setNextReminder) {
    this.setNextReminder = setNextReminder;
  }
}
