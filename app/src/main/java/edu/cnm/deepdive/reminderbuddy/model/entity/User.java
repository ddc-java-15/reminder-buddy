package edu.cnm.deepdive.reminderbuddy.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "user",
    indices = {
        @Index(value = "oauth_key", unique = true)
    }
)
/**
 * Creates an instance of user to be utilized by the database and other classes.
 */
public class User {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_id")
  private long id;

  @ColumnInfo(name = "oauth_key")
  private String oauthKey;

  private int age;

  /**
   * Gets the unique id of the user currently signed in.
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   * Sets the unique id of the user currently signed in.
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   * Gets age of user.
   * @return
   */
  public int getAge() {
    return age;
  }

  /**
   * Sets age of user.
   * @param age
   */
  public void setAge(int age) {
    this.age = age;
  }

  /**
   *
   * @return
   */
  public String getOauthKey() {
    return oauthKey;
  }

  /**
   *
   * @param oauthKey
   */
  public void setOauthKey(String oauthKey) {
    this.oauthKey = oauthKey;
  }

  /**
   *
   * @param displayName
   */
  public void setName(String displayName) {
  }
}
