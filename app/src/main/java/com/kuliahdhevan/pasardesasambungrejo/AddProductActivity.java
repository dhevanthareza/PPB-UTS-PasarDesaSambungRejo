package com.kuliahdhevan.pasardesasambungrejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        final DatabaseHandler db = new DatabaseHandler(this);
        final EditText edtCode = (EditText) findViewById(R.id.edtCode);
        final EditText edtName = (EditText)findViewById(R.id.edtName);
        final EditText edtDescription = (EditText)findViewById(R.id.edtDescription);
        final EditText edtrice = (EditText)findViewById(R.id.edtPrice);
        Button btnTambah = (Button) findViewById(R.id.btnTambah);
        Button btnBatal = (Button) findViewById(R.id.btnBatal);
    }

    public void goBack() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",1);
        setResult(1,returnIntent);
        finish();
    }
}