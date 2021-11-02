package com.kuliahdhevan.pasardesasambungrejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateUserActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.kuliahdhevan.pasardesasambungrejo";
    EditText edtPassword, edtUsername, edtFullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        edtPassword = findViewById(R.id.edtPassword);
        edtUsername = findViewById(R.id.edtUsername);
        edtFullname = findViewById(R.id.edtFullname);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        getSupportActionBar().hide();
    }

    public void submitRegister(View view) {
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString("FULLNAME", edtFullname.getText().toString());
        preferencesEditor.putString("USERNAME", edtUsername.getText().toString());
        preferencesEditor.putString("LOGIN_USERNAME", edtUsername.getText().toString());
        preferencesEditor.putString("PASSWORD", edtPassword.getText().toString());
        preferencesEditor.apply();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        this.finish();
        Toast.makeText(getApplicationContext(), "Berhasil update user", Toast.LENGTH_SHORT);
    }

}