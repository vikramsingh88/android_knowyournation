package com.vikram.knowyournation.util.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by M1032130 on 11/4/2017.
 */

public class Languages implements Parcelable, Serializable {
    private String iso639_2;
    private String iso639_1;
    private String name;
    private String nativeName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iso639_2);
        dest.writeString(iso639_1);
        dest.writeString(name);
        dest.writeString(nativeName);
    }

    private Languages(Parcel in){
        this.iso639_2 = in.readString();
        this.iso639_1 = in.readString();
        this.name = in.readString();
        this.nativeName = in.readString();
    }

    public static final Parcelable.Creator<Languages> CREATOR = new Parcelable.Creator<Languages>() {

        @Override
        public Languages createFromParcel(Parcel source) {
            return new Languages(source);
        }

        @Override
        public Languages[] newArray(int size) {
            return new Languages[size];
        }
    };

    public String getIso639_2() {
        return iso639_2;
    }

    public void setIso639_2(String iso639_2) {
        this.iso639_2 = iso639_2;
    }

    public String getIso639_1() {
        return iso639_1;
    }

    public void setIso639_1(String iso639_1) {
        this.iso639_1 = iso639_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}