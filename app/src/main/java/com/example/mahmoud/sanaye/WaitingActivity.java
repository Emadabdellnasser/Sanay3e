package com.example.mahmoud.sanaye;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;

import com.example.mahmoud.sanaye.activtiy.EmployeeDone;
import com.example.mahmoud.sanaye.activtiy.TypeActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class WaitingActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    DatabaseReference rootRef;
    Intent intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);

        printHashCode();
        Constants.isLoged = true;

        // check existing user
        rootRef = FirebaseDatabase.getInstance().getReference();
        Query query = rootRef.child("user").orderByChild("phone").equalTo(Constants.phoneNumber);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String phone1 = (String) dataSnapshot.child("phone").getValue();

                if (phone1.equals(Constants.phoneNumber)) {
                    intro = new Intent(WaitingActivity.this, EmployeeDone.class);
                } else {
                    intro = new Intent(WaitingActivity.this, TypeActivity.class);
                }
                startActivity(intro);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void printHashCode() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.example.mahmoud.sanaye", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest ms = MessageDigest.getInstance("SHA");
                ms.update(signature.toByteArray());
                Log.e("KEYHASH", Base64.encodeToString(ms.digest(), Base64.DEFAULT));
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }


}
