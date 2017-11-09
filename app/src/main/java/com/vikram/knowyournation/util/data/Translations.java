package com.vikram.knowyournation.util.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by M1032130 on 11/4/2017.
 */

public class Translations implements Serializable {
    private String hr;
    private String de;
    private String it;
    private String pt;
    private String fa;
    private String fr;
    private String br;
    private String es;
    private String nl;
    private String ja;

   /* @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hr);
        dest.writeString(de);
        dest.writeString(it);
        dest.writeString(pt);
        dest.writeString(fa);
        dest.writeString(fr);
        dest.writeString(br);
        dest.writeString(es);
        dest.writeString(nl);
        dest.writeString(ja);
    }

    private Translations(Parcel in) {
        this.hr = in.readString();
        this.de = in.readString();
        this.it = in.readString();
        this.pt = in.readString();
        this.fa = in.readString();
        this.fr = in.readString();
        this.br = in.readString();
        this.es = in.readString();
        this.nl = in.readString();
        this.ja = in.readString();
    }

    public static final Parcelable.Creator<Translations> CREATOR = new Parcelable.Creator<Translations>() {

        @Override
        public Translations createFromParcel(Parcel source) {
            return new Translations(source);
        }

        @Override
        public Translations[] newArray(int size) {
            return new Translations[size];
        }
    };*/

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getBr() {
        return br;
    }

    public void setBr(String br) {
        this.br = br;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getJa() {
        return ja;
    }

    public void setJa(String ja) {
        this.ja = ja;
    }
}