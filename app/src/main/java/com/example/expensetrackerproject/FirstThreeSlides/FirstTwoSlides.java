package com.example.expensetrackerproject.FirstThreeSlides;

import android.os.Parcel;
import android.os.Parcelable;


public class

FirstTwoSlides implements Parcelable {
    public FirstTwoSlides(int imgId1, String title) {
        this.imgId1 = imgId1;
        this.title = title;
    }


    protected FirstTwoSlides(Parcel in) {
        imgId1 = in.readInt();
        title = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgId1);
        dest.writeString(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FirstTwoSlides> CREATOR = new Creator<FirstTwoSlides>() {
        @Override
        public FirstTwoSlides createFromParcel(Parcel in) {
            return new FirstTwoSlides(in);
        }

        @Override
        public FirstTwoSlides[] newArray(int size) {
            return new FirstTwoSlides[size];
        }
    };
    private int imgId1;
    private String title;

    public int getImgId1() {
        return imgId1;
    }

    public void setImgId1(int imgId1) {
        this.imgId1 = imgId1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
