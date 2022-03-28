package edu.cnm.deepdive.reminderbuddy.model.view;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;

@DatabaseView(
    viewName = "response_summary",
    value = "SELECT u.user_id, r.correct, COUNT(*) AS count\n"
        + "FROM response AS r\n"
        + "         JOIN card AS c ON c.card_id = r.card_id\n"
        + "         JOIN user AS u ON u.user_id = c.user_id\n"
        + "GROUP BY u.user_id, r.correct"
)
public class ResponseSummary {

  @ColumnInfo(name = "user_id")
  private long userId;

  private boolean correct;

  private long count;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public boolean isCorrect() {
    return correct;
  }

  public void setCorrect(boolean correct) {
    this.correct = correct;
  }

  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }
}
