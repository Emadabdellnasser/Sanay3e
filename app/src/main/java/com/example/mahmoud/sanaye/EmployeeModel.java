package com.example.mahmoud.sanaye;

import android.os.Parcel;
import android.os.Parcelable;

public class EmployeeModel implements Parcelable{

    String locationName;
    String job;
    String phone;
    String name;

    public EmployeeModel(String locationName, String job, String phone, String name) {
        this.locationName = locationName;
        this.job = job;
        this.phone = phone;
        this.name = name;
    }

    protected EmployeeModel(Parcel in) {
        locationName = in.readString();
        job = in.readString();
        phone = in.readString();
        name = in.readString();
    }

    public static final Creator<EmployeeModel> CREATOR = new Creator<EmployeeModel>() {
        @Override
        public EmployeeModel createFromParcel(Parcel in) {
            return new EmployeeModel(in);
        }

        @Override
        public EmployeeModel[] newArray(int size) {
            return new EmployeeModel[size];
        }
    };

    public String getLocationSelected() {

        return locationName;
    }

    public void setLocationSelected(String locationSelected) {
        this.locationName= locationSelected;
    }

    public String getJob() {
        return job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(locationName);
        dest.writeString(job);
        dest.writeString(phone);
        dest.writeString(name);
    }
}
