package com.kuliahdhevan.pasardesasambungrejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        final DatabaseHandler db = new DatabaseHandler(this);
        final EditText edtCode = (EditText) findViewById(R.id.edtCode);
        final EditText edtName = (EditText)findViewById(R.id.edtName);
        final EditText edtDescription = (EditText)findViewById(R.id.edtDescription);
        final EditText edtPrice = (EditText)findViewById(R.id.edtPrice);

        Button btnSimpan = findViewById(R.id.btnSimpan);
        Button btnBatal = findViewById(R.id.btnBatal);

        Intent intent = getIntent();
        String productCode = intent.getStringExtra("productCode");
        String productName = intent.getStringExtra("productName");
        String productDescription = intent.getStringExtra("productDescription");
        int productPrice = intent.getIntExtra("productPrice", 0);

        edtCode.setText(productCode);
        edtName.setText(productName);
        edtDescription.setText(productDescription);
        edtPrice.setText(String.valueOf(productPrice));

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = edtCode.getText().toString();
                String name = edtName.getText().toString();
                String price = edtPrice.getText().toString();
                String description = edtDescription.getText().toString();

                db.updateMethod(code, name, Integer.parseInt(price), description);
                goBack();
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener()
        {public void onClick(View v) {
            goBack();
        }
        });
    }

    public void goBack() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",1);
        setResult(1,returnIntent);
        finish();
    }
}