package com.example.mahmoud.sanaye;

import android.content.Context;
import android.content.SharedPreferences;

public class Constants {


    public static String phoneNumber;
    public static boolean isLoged = false;
    public static boolean type = false;


    /// shardPref
    public static String SHARDPREFNAME="SHARDPREFNAME";
    public static String NAME="NAME";
    public static String PHONE="PHONE";
    public static String JOB="JOB";
    public static String LOCATION="LOCATION";

    public static String getSharedNumber(Context context){
        SharedPreferences sp1 = context.getSharedPreferences("Login", 0);
        String Phonesh = sp1.getString("phonesh"," ");
        return  Phonesh;
    }
}
