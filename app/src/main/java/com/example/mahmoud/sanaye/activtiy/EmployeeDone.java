package com.example.mahmoud.sanaye.activtiy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mahmoud.sanaye.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class EmployeeDone extends AppCompatActivity {
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_done);




        MobileAds.initialize(this,"ca-app-pub-4797650798439827~1471409016");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);



    }
}
