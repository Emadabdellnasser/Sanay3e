package com.example.mahmoud.sanaye.activtiy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mahmoud.sanaye.Constants;
import com.example.mahmoud.sanaye.FacebookAccountActivity;
import com.example.mahmoud.sanaye.R;
import com.example.mahmoud.sanaye.activtiy.user.SelectAtivity;

public class TypeActivity extends AppCompatActivity {

    LinearLayout btnUser, btnEmployee;

TextView tx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        tx=findViewById(R.id.logoutbt);
        btnUser = findViewById(R.id.loginLay);
        btnEmployee = findViewById(R.id.btn_employee);
        Constants.isLoged = true;

    }


    public void employeeAction(View view) {
        Constants.type = true;
        Intent employeeIntent = new Intent(TypeActivity.this, UserRegisteration.class);
        startActivity(employeeIntent);
    }

    public void userAction(View view) {
        Constants.type = false;
        Intent employeeIntent = new Intent(TypeActivity.this, SelectAtivity.class);
        startActivity(employeeIntent);
        finish();

    }
    public void logout(View view) {
        SharedPreferences sp=getSharedPreferences("Login", 0);
        SharedPreferences.Editor Ed=sp.edit();
        Ed.putString("phonesh","");
        Ed.commit();
        Intent employeeIntent = new Intent(TypeActivity.this, FacebookAccountActivity.class);
        startActivity(employeeIntent);
        finish();

    }
}
