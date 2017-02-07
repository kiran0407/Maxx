package com.k4ench.maxx;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hp on 29-01-2017.
 */

public class ListItem1 implements Parcelable {
    String name,email,number,typ;
    int id;

    protected ListItem1(Parcel in) {
        name = in.readString();
        email = in.readString();
        number = in.readString();
        typ = in.readString();
        id = in.readInt();
    }

    public static final Creator<ListItem1> CREATOR = new Creator<ListItem1>() {
        @Override
        public ListItem1 createFromParcel(Parcel in) {
            return new ListItem1(in);
        }

        @Override
        public ListItem1[] newArray(int size) {
            return new ListItem1[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(number);
        parcel.writeString(typ);
        parcel.writeInt(id);
    }
}
