package com.kuliahdhevan.pasardesasambungrejo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Product> mProductsData;
    private ProductAdapter mAdapter;
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.kuliahdhevan.pasardesasambungrejo";
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mProductsData = new ArrayList<>();
        mAdapter = new ProductAdapter(this, mProductsData, findViewById(R.id.totalPrice));
        mRecyclerView.setAdapter(mAdapter);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        DatabaseHandler db = new DatabaseHandler(this);
        this.db = db;
        this.fillInitialData();
        initializeData();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.initializeData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.initializeData();
    }

    public void fillInitialData() {
        String[] foodsName = getResources().getStringArray(R.array.foods_name);
        String[] foodsDescription = getResources().getStringArray(R.array.foods_description);
        String[] foodsPrice = getResources().getStringArray(R.array.foods_price);
        String[] foodsCode = getResources().getStringArray(R.array.foods_code);
        TypedArray foodImagesResources =
                getResources().obtainTypedArray(R.array.foods_images);
        for(int i=0;i<foodsName.length;i++){
            db.addProduct(new Product(foodsName[i], foodsDescription[i], Integer.parseInt(foodsPrice[i]), foodImagesResources.getResourceId(i, 0), foodsCode[i]));
        }
    }

    private void initializeData() {
        List<Product> products = db.getAllProduct();
        mProductsData.clear();
        for(Product product : products) {
            mProductsData.add(product);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.callCenter:
                this.goToCallCenter();
                return true;
            case R.id.smsCenter:
                this.goToSmsCenter();
                return true;
            case R.id.map:
                this.openMap();
                return true;
            case R.id.logout:
                this.logout();
                return true;
            case R.id.updateUser:
                this.goToUpdateUser();
                return true;
            case R.id.addProduct:
                this.goToAddProduct();

            default:
                return true;
        }
    }

    public void logout() {
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putString("LOGIN_USERNAME", "false");
        preferencesEditor.apply();
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void goToCallCenter() {
        Intent intent = new Intent(this, CallCenterActivity.class);
        startActivity(intent);
    }

    public void goToSmsCenter() {
        Intent intent = new Intent(this, SmsCenterActivity.class);
        startActivity(intent);
    }

    public void goToUpdateUser() {
        Intent intent = new Intent(this, UpdateUserActivity.class);
        startActivity(intent);
    }

    public void goToAddProduct() {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivityForResult(intent,1);
    }

    public void openMap() {
        Uri gmmIntentUri = Uri.parse("geo:-6.982866,110.4069027");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    public void goToPurchaseDetail(View view) {
        TextView purchaseAmountTv = findViewById(R.id.totalPrice);
        String purchaseAmount = purchaseAmountTv.getText().toString().split("=")[1].trim();
        Intent intent = new Intent(this, PurchaseDetailActivity.class);
        intent.putExtra("purchaseAmount", purchaseAmount);
        startActivity(intent);
    }
}