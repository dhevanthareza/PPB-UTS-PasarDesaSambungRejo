package com.kuliahdhevan.pasardesasambungrejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProductDetailAActivity extends AppCompatActivity {
    private TextView mNameText, mDescriptionText;
    private ImageView mProductImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_aactivity);
        mProductImage = findViewById(R.id.productImage);
        mNameText = findViewById(R.id.productName);
        mDescriptionText = findViewById(R.id.productDescription);
        Intent intent = getIntent();
        String productName = intent.getStringExtra("productName");
        String productDescription = intent.getStringExtra("productDescription");
        int productImage = intent.getIntExtra("productImage", 0);
        Glide.with(this).load(productImage).into(mProductImage);
        mNameText.setText(productName);
        mDescriptionText.setText(productDescription);
    }
}