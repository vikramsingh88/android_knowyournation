package com.vikram.knowyournation.util.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by M1032130 on 11/4/2017.
 */

public class RegionalBlocs implements Parcelable, Serializable {
    private String[] otherAcronyms;
    private String acronym;
    private String name;
    private String[] otherNames;

    public RegionalBlocs(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        int size = otherAcronyms.length;
        if (otherAcronyms != null) {
            dest.writeStringArray(otherAcronyms);
        }
        dest.writeString(acronym);
        dest.writeString(name);
        if (otherNames != null) {
            dest.writeStringArray(otherNames);
        }
    }

    private RegionalBlocs(Parcel in){
        this.otherAcronyms = in.createStringArray();
        this.acronym = in.readString();
        this.name = in.readString();
        this.otherNames = in.createStringArray();
    }

    public static final Parcelable.Creator<RegionalBlocs> CREATOR = new Parcelable.Creator<RegionalBlocs>() {

        @Override
        public RegionalBlocs createFromParcel(Parcel source) {
            return new RegionalBlocs(source);
        }

        @Override
        public RegionalBlocs[] newArray(int size) {
            return new RegionalBlocs[size];
        }
    };

    public String[] getOtherAcronyms() {
        return otherAcronyms;
    }

    public void setOtherAcronyms(String[] otherAcronyms) {
        this.otherAcronyms = otherAcronyms;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String[] otherNames) {
        this.otherNames = otherNames;
    }
}