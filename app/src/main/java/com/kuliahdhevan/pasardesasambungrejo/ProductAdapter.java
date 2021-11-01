package com.kuliahdhevan.pasardesasambungrejo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    int price = 0;
    private ArrayList<Product> mFoodsData;
    private Context mContext;
    private WeakReference<TextView> mTotalPriceText;
    ProductAdapter(Context context, ArrayList<Product> foodsData, TextView totalPriceText) {
        this.mFoodsData = foodsData;
        this.mContext = context;
        this.mTotalPriceText = new WeakReference<>(totalPriceText);
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        Product currentFood = mFoodsData.get(position);
        holder.bindTo(currentFood);
    }

    @Override
    public int getItemCount() {
        return mFoodsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameText;
        private TextView mPriceText;
        private ImageView mFoodImage;

        ViewHolder(View itemView) {
            super(itemView);
            mNameText = (TextView)itemView.findViewById(R.id.name);
            mPriceText = (TextView)itemView.findViewById(R.id.price);
            mFoodImage = itemView.findViewById(R.id.foodImage);
            mFoodImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Product currentFood = mFoodsData.get(getAdapterPosition());
                    price += currentFood.getPrice();
                    mTotalPriceText.get().setText("TOTAL = " + String.valueOf(price));
                }
            });
        }

        void bindTo(Product currentFood){
            mNameText.setText(currentFood.getName());
            mPriceText.setText(String.valueOf(currentFood.getPrice()));
            Glide.with(mContext).load(currentFood.getImageResource()).into(mFoodImage);
        }
    }
}
