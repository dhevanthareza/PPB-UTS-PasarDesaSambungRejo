package com.kuliahdhevan.pasardesasambungrejo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Product> mProductsData;
    private ProductAdapter mAdapter;
    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "com.kuliahdhevan.pasardesasambungrejo";
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
        initializeData();
    }

    private void initializeData() {
        String[] foodsName = getResources().getStringArray(R.array.foods_name);
        String[] foodsPrice = getResources().getStringArray(R.array.foods_price);
        TypedArray foodImagesResources =
                getResources().obtainTypedArray(R.array.foods_images);
        mProductsData.clear();
        for(int i=0;i<foodsName.length;i++){
            mProductsData.add(new Product(foodsName[i], Integer.parseInt(foodsPrice[i]), foodImagesResources.getResourceId(i, 0)));
        }
        foodImagesResources.recycle();
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

    public void openMap() {
        Uri gmmIntentUri = Uri.parse("geo:-6.982866,110.4069027");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    public void goToPurchaseDetail(View view) {
        Intent intent = new Intent(this, PurchaseDetailActivity.class);
        startActivity(intent);
    }
}