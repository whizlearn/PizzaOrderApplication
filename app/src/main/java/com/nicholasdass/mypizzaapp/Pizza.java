package com.nicholasdass.mypizzaapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Nicholas Dass on 10/20/16.
 */

public class Pizza implements Parcelable{

    // Field Variables*****************************

    public static final String SMALL = "Small pizza", MEDIUM = "Medium pizza", LARGE = "Large pizza";



    private String size;

    private boolean hasCheese;
    private boolean hasPepperoni;
    private boolean hasGreenPepper;


    //*********************************************

    public Pizza(String size) {
        this.size = size;
    }


    // This method reads data into parcel
    protected Pizza(Parcel in) {
        size = in.readString();
        hasCheese = in.readByte() != 0;
        hasPepperoni = in.readByte() != 0;
        hasGreenPepper = in.readByte() != 0;
    }

    // This method creates the parcel
    public static final Parcelable.Creator<Pizza> CREATOR = new Parcelable.Creator<Pizza>() {
        @Override
        public Pizza createFromParcel(Parcel parcel) {
            return new Pizza(parcel);
        }

        @Override
        public Pizza[] newArray(int i) {
            return new Pizza[0];
        }
    };

    // Getters and Setters*********************************************************

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getHasCheese() {
        return hasCheese;
    }

    public void setHasCheese(boolean hasCheese) {
        this.hasCheese = hasCheese;
    }

    public boolean getHasPepperoni() {
        return hasPepperoni;
    }

    public void setHasPepperoni(boolean hasPepperoni) {
        this.hasPepperoni = hasPepperoni;
    }

    public boolean getHasGreenPepper() {
        return hasGreenPepper;
    }

    public void setHasGreenPepper(boolean hasGreenPepper) {
        this.hasGreenPepper = hasGreenPepper;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    // This method flatens the parcel
    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(size);
        parcel.writeByte((byte) (hasCheese ? 1 : 0));
        parcel.writeByte((byte) (hasPepperoni ? 1 : 0));
        parcel.writeByte((byte) (hasGreenPepper ? 1 : 0));

    }

    //*******************************************************************************

}
