    package com.kuliahdhevan.pasardesasambungrejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

    public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.kuliahdhevan.pasardesasambungrejo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        checkUser();
                    }
                }, 5000);
    }

    protected void checkUser() {
        String storedUsername = mPreferences.getString("LOGIN_USERNAME", "false");
        if(storedUsername.equals("false")) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
            this.finish();
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            this.finish();
        }

    }
}