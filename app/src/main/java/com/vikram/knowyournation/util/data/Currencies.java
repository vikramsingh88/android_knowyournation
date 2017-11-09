package com.vikram.knowyournation.util.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by M1032130 on 11/4/2017.
 */

public class Currencies implements Parcelable, Serializable {
    private String symbol;
    private String name;
    private String code;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(symbol);
        dest.writeString(name);
        dest.writeString(code);
    }

    private Currencies(Parcel in){
        this.symbol = in.readString();
        this.name = in.readString();
        this.code = in.readString();
    }

    public static final Parcelable.Creator<Currencies> CREATOR = new Parcelable.Creator<Currencies>() {

        @Override
        public Currencies createFromParcel(Parcel source) {
            return new Currencies(source);
        }

        @Override
        public Currencies[] newArray(int size) {
            return new Currencies[size];
        }
    };

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
