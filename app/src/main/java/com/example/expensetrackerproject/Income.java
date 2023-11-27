package com.example.expensetrackerproject;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Income implements Parcelable {
    String docID;
    String periodicityCrontab;
    String amount;

    public Income(String docID, String periodicityCrontab, String amount) {
        this.docID = "None yet";
        this.periodicityCrontab = periodicityCrontab;
        this.amount = amount;
    }
    public Income() {
        docID="";
        periodicityCrontab="";
        amount="";
    }

    protected Income(Parcel in) {
        docID = in.readString();
        periodicityCrontab = in.readString();
        amount = in.readString();
    }

    public static final Creator<Income> CREATOR = new Creator<Income>() {
        @Override
        public Income createFromParcel(Parcel in) {
            return new Income(in);
        }

        @Override
        public Income[] newArray(int size) {
            return new Income[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(docID);
        parcel.writeString(periodicityCrontab);
        parcel.writeString(amount);
    }
}
