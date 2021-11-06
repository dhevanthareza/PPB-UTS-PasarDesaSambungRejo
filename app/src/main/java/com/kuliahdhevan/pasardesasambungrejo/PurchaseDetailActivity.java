package com.kuliahdhevan.pasardesasambungrejo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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
        EditText edtPaymentAmount = findViewById(R.id.editTextNumber);
        edtPaymentAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                changePaymentAmount();
            }
        });
    }

    public void changePaymentAmount() {
        Intent intent = getIntent();
        Integer purchaseAmount = Integer.parseInt(intent.getStringExtra("purchaseAmount")) ;
        EditText edtPaymentAmount = findViewById(R.id.editTextNumber);
        TextView returnAmountText = findViewById(R.id.textView18);
        if(edtPaymentAmount.getText().toString().equals("") || edtPaymentAmount.getText().toString().equals("0")) {
            return;
        }
        Integer paymentAmount = Integer.parseInt(edtPaymentAmount.getText().toString());
        Integer returnAmount = paymentAmount - purchaseAmount;
        returnAmountText.setText(returnAmount.toString());
    }
};