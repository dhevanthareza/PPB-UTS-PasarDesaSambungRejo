package com.kuliahdhevan.pasardesasambungrejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PurchaseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_detail);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        String purchaseAmount = intent.getStringExtra("purchaseAmount");
        TextView purchaseAmountTv = findViewById(R.id.purchaseAmount);
        purchaseAmountTv.setText(purchaseAmount);
    }
}