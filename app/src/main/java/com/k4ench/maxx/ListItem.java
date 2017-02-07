package com.k4ench.maxx;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hp on 28-01-2017.
 */

public class ListItem implements Parcelable {
    private int catg_id;
    private String name;

    protected ListItem(Parcel in) {
        catg_id = in.readInt();
        name = in.readString();
    }

    public static final Creator<ListItem> CREATOR = new Creator<ListItem>() {
        @Override
        public ListItem createFromParcel(Parcel in) {
            return new ListItem(in);
        }

        @Override
        public ListItem[] newArray(int size) {
            return new ListItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(catg_id);
        parcel.writeString(name);
    }

    public int getCatg_id() {
        return catg_id;
    }

    public void setCatg_id(int catg_id) {
        this.catg_id = catg_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
