package com.example.mahmoud.sanaye;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class EmployeeDetails extends AppCompatActivity {

    TextView tvPhone;
    TextView tvname;
    TextView tvjob;
    private AdView mAdView;
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);


        MobileAds.initialize(this, "ca-app-pub-4797650798439827~1471409016");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);

        tvPhone = findViewById(R.id.phone_tv);
        tvname = findViewById(R.id.name_tv);
        tvjob = findViewById(R.id.job_tv);
        btnFinish=findViewById(R.id.btn_finish);

        EmployeeModel model = getIntent().getExtras().getParcelable("model");

        //tvPhone.setText(model.getPhone().toString());
//        tvname.setText(model.getName().toString());
     //   tvjob.setText(model.getJob().toString());

    }


    public void callNumber(View view) {

        String phone = tvPhone.getText().toString();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phone));

        if (ActivityCompat.checkSelfPermission(EmployeeDetails.this,
                android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        startActivity(callIntent);

    }





    public void finisBtn(View view) {
        finish();
    }






}
