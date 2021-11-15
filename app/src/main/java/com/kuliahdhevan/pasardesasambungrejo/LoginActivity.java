package com.kuliahdhevan.pasardesasambungrejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.kuliahdhevan.pasardesasambungrejo";
    private EditText edtPassword, edtUsername, edtFullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        getSupportActionBar().hide();
    }

    public void login(View view) {
        String storedUsername = mPreferences.getString("USERNAME", "secret");
        String storedPassword = mPreferences.getString("PASSWORD", "secret");
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        if(username.equals(storedUsername) && password.equals(storedPassword)) {
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putString("LOGIN_USERNAME", edtUsername.getText().toString());
            preferencesEditor.apply();
            Intent intent = new Intent(this, HomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            this.finish();
        } else {
            Toast.makeText(getApplicationContext(), "Username atau Password salah", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToRegister(View view) {
        this.finish();
    }
}