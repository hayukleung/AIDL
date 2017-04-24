package com.hayukleung.aidl.common;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * AIDL
 * com.hayukleung.aidl.common
 * Common.java
 *
 * by hayukleung
 * at 2017-04-23 20:41
 */

public class Common implements Parcelable {

  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Common() {
  }

  public Common(Parcel in) {
    id = in.readInt();
    name = in.readString();
  }

  public static final Creator<Common> CREATOR = new Creator<Common>() {
    @Override public Common createFromParcel(Parcel in) {
      return new Common(in);
    }

    @Override public Common[] newArray(int size) {
      return new Common[size];
    }
  };

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(name);
  }
}
