package com.example.mahmoud.sanaye.activtiy;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.mahmoud.sanaye.Constants;
import com.example.mahmoud.sanaye.FacebookAccountActivity;
import com.example.mahmoud.sanaye.R;
import com.example.mahmoud.sanaye.activtiy.user.SelectAtivity;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;
import com.github.florent37.runtimepermission.RuntimePermission;
import com.github.florent37.runtimepermission.callbacks.PermissionListener;

import java.util.List;

import static com.example.mahmoud.sanaye.Constants.isLoged;
import static com.github.florent37.runtimepermission.RuntimePermission.askPermission;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    Intent intro;
    String s ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);


        askPermission(this, Manifest.permission.READ_CONTACTS, Manifest.permission.READ_SMS)
                .ask(new PermissionListener() {

                    @Override
                    public void onAccepted(RuntimePermission runtimePermission, List<String> accepted) {

                    }

                    @Override
                    public void onDenied(RuntimePermission runtimePermission, List<String> denied, List<String> foreverDenied) {
                        //the list of denied permissions
                        for (String permission : denied) {

                        }
                        //permission denied, but you can ask again, eg:

                /*
                new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Please accept our permissions")
                        .setPositiveButton("yes", (dialog, which) -> { result.askAgain(); })
                        .setNegativeButton("no", (dialog, which) -> { dialog.dismiss(); })
                        .show();
                */

                        //the list of forever denied permissions, user has check 'never ask again'
                        for (String permission : foreverDenied) {

                        }
                        // you need to open setting manually if you really need it
                        //runtimePermission.goToSettings();
                    }
                });


        //new activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                // if (isLoged) {
                // user
                // intro = new Intent(SplashScreen.this, SelectAtivity.class);

                // }

              s = Constants.getSharedNumber(SplashScreen.this);
                if (s.length()>5) {
                    intro = new Intent(SplashScreen.this, TypeActivity.class);

                } else {
                    intro = new Intent(SplashScreen.this, FacebookAccountActivity.class);
                }
                startActivity(intro);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);


    }

}
