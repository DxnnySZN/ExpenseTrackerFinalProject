package com.example.expensetrackerproject.Expenses;

import android.os.Parcel;
import android.os.Parcelable;

public class Expense implements Parcelable {
    String docId;
    String name;
    String description;


    public Expense(String name, String description) {
        this.name = name;
        this.description = description;
        docId = "None yet";
    }

    public Expense() {
        name = "";
        docId = "";
        description = "";
    }

    protected Expense(Parcel in) {
        docId = in.readString();
        name = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(docId);
        dest.writeString(name);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Expense> CREATOR = new Creator<Expense>() {
        @Override
        public Expense createFromParcel(Parcel in) {
            return new Expense(in);
        }

        @Override
        public Expense[] newArray(int size) {
            return new Expense[size];
        }
    };

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
