package com.kuliahdhevan.pasardesasambungrejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        final DatabaseHandler db = new DatabaseHandler(this);
        final EditText edtCode = (EditText) findViewById(R.id.edtCode);
        final EditText edtName = (EditText)findViewById(R.id.edtName);
        final EditText edtDescription = (EditText)findViewById(R.id.edtDescription);
        final EditText edtPrice = (EditText)findViewById(R.id.edtPrice);
        Button btnTambah = (Button) findViewById(R.id.btnTambah);
        Button btnBatal = (Button) findViewById(R.id.btnBatal);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = edtCode.getText().toString();
                String name = edtName.getText().toString();
                String price = edtPrice.getText().toString();
                String description = edtDescription.getText().toString();
                TypedArray foodImagesResources =
                        getResources().obtainTypedArray(R.array.foods_images);
                Random rn = new Random();
                int randNUmber = rn.nextInt(7 - 0 + 1);
                Product product = new Product(name, description, Integer.parseInt(price),foodImagesResources.getResourceId(randNUmber,0), code);
                db.addProduct(product);
                edtCode.setText("");
                edtName.setText("");
                edtPrice.setText("");
                edtDescription.setText("");
                goBack();
            }
        });
        btnBatal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
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