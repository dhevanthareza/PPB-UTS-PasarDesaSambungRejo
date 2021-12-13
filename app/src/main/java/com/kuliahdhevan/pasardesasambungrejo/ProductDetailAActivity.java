package com.kuliahdhevan.pasardesasambungrejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProductDetailAActivity extends AppCompatActivity {
    private TextView mNameText, mDescriptionText;
    private ImageView mProductImage;
    public Button mBtnDelete, mBtnEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_aactivity);
        mProductImage = findViewById(R.id.productImage);
        mNameText = findViewById(R.id.productName);
        mDescriptionText = findViewById(R.id.productDescription);
        mBtnDelete = findViewById(R.id.btnDelete);
        mBtnEdit = findViewById(R.id.btnEdit);
        Intent intent = getIntent();
        String productCode = intent.getStringExtra("productCode");
        String productName = intent.getStringExtra("productName");
        String productDescription = intent.getStringExtra("productDescription");
        int productImage = intent.getIntExtra("productImage", 0);
        int productPrice = intent.getIntExtra("productPrice", 0);
        Glide.with(this).load(productImage).into(mProductImage);
        mNameText.setText(productName);
        mDescriptionText.setText(productDescription);
        final DatabaseHandler db = new DatabaseHandler(this);
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteProduct(productCode);
                finish();
            }
        });
        Context m = this;
        mBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(m, EditProductActivity.class);
                intent.putExtra("productCode", productCode);
                intent.putExtra("productName", productName);
                intent.putExtra("productDescription", productDescription);
                intent.putExtra("productPrice", productPrice);
                finish();
                startActivity(intent);
            }
        });
    }
}