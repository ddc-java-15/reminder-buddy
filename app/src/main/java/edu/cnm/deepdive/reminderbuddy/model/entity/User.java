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
 *
 */
public class User {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_id")
  private long id;

  @ColumnInfo(name = "oauth_key")
  private String oauthKey;

  private int age;

  /**
   *
   * @return
   */
  public long getId() {
    return id;
  }

  /**
   *
   * @param id
   */
  public void setId(long id) {
    this.id = id;
  }

  /**
   *
   * @return
   */
  public int getAge() {
    return age;
  }

  /**
   *
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
