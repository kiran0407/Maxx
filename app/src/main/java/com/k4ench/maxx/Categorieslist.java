package com.k4ench.maxx;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hp on 28-01-2017.
 */
public class Categorieslist implements Parcelable {

    private String topic_name,invoice;
    private int id,catg_id;

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    protected Categorieslist(Parcel in) {

        topic_name = in.readString();
        catg_id = in.readInt();
        invoice = in.readString();
        id = in.readInt();
    }

    public static final Creator<Categorieslist> CREATOR = new Creator<Categorieslist>() {
        @Override
        public Categorieslist createFromParcel(Parcel in) {
            return new Categorieslist(in);
        }

        @Override
        public Categorieslist[] newArray(int size) {
            return new Categorieslist[size];
        }
    };

    public String getTopic_name() {
        return topic_name;
    }

    public void setTopic_name(String topic_name) {
        this.topic_name = topic_name;
    }

    public int getCatg_id() {
        return catg_id;
    }

    public void setCatg_id(int catg_id) {
        this.catg_id = catg_id;
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
        parcel.writeString(topic_name);
        parcel.writeInt(catg_id);
        parcel.writeString(invoice);
        parcel.writeInt(id);
    }
}
