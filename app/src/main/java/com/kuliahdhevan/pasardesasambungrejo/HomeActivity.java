package com.kuliahdhevan.pasardesasambungrejo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Product> mProductsData;
    private ProductAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mProductsData = new ArrayList<>();
        mAdapter = new ProductAdapter(this, mProductsData, findViewById(R.id.totalPrice));
        mRecyclerView.setAdapter(mAdapter);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

//    public void logout(View view) {
//        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
//        preferencesEditor.putString("LOGIN_USERNAME", "false");
//        preferencesEditor.apply();
//    }
}