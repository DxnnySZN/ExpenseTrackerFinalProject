package com.example.expensetrackerproject.FirstThreeSlides;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class LastOnboardingItem implements Parcelable {
    private int imageResourceId;
    private String textOnTop;

    public LastOnboardingItem(int imageResourceId, String textOnTop) {
        this.imageResourceId = imageResourceId;
        this.textOnTop = textOnTop;
    }

    protected LastOnboardingItem(Parcel in) {
        imageResourceId = in.readInt();
        textOnTop = in.readString();
    }

    public static final Creator<LastOnboardingItem> CREATOR = new Creator<LastOnboardingItem>() {
        @Override
        public LastOnboardingItem createFromParcel(Parcel in) {
            return new LastOnboardingItem(in);
        }

        @Override
        public LastOnboardingItem[] newArray(int size) {
            return new LastOnboardingItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(imageResourceId);
        parcel.writeString(textOnTop);
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getTextOnTop() {
        return textOnTop;
    }

    public void setTextOnTop(String textOnTop) {
        this.textOnTop = textOnTop;
    }

 // Replace with your actual button ID

}
