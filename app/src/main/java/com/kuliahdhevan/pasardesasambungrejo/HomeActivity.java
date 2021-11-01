package com.kuliahdhevan.pasardesasambungrejo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
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
            case R.id.logout:
                this.logout();
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
}