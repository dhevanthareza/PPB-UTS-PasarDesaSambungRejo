package com.kuliahdhevan.pasardesasambungrejo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION= 4;
    private static final String DATABASE_NAME= "DHEVAN_STORE_DB";
    private static final String TABLE_PRODUCT = "Product";

    public DatabaseHandler(Context context) {
        // TODOAuto-generated constructor stub
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        // TODOAuto-generated method stub
        String query_table_product = "CREATE TABLE " + TABLE_PRODUCT +
                "(" +
                "name" + " TEXT," + // Define a primary key
                "description" + " TEXT," +
                "price" + " INT," +
                "imageResource" + " INT," +
                "code" + " TEXT PRIMARY KEY" +
                ")";
        db.execSQL(query_table_product);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
            onCreate(db);
        }
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("code", product.code);
        values.put("name", product.name);
        values.put("price", product.price);
        values.put("description", product.description);
        values.put("imageResource", product.imageResource);
        db.insert(TABLE_PRODUCT, null, values);
        db.close(); //
    }

    public Product getProduct(String code) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_PRODUCT,
                new String[] {"name", "description", "price", "imageResource", "code"},
                "code"+ "=?",
                new String[] {code},
                null,
                null,
                null,
                null);
        if(cursor != null)
            cursor.moveToFirst();
        Product product = new Product(
                cursor.getString(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getInt(3),
                cursor.getString(4)
        );
        return product;
    }

    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<Product>();
        String query_select_siswa = "SELECT * FROM "+
                TABLE_PRODUCT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query_select_siswa,
                null);
        if(cursor.moveToFirst()) {
            do{
                Product product = new Product(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getInt(3),
                        cursor.getString(4)
                );
                products.add(product);
            } while(cursor.moveToNext());
        }
        return products;
    }

    public void deleteProduct(String code) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PRODUCT, "code" + "='"+
                code+"'",null);
        db.close();
        System.out.println("Data terhapus "+code);
    }
    public void updateMethod(String code, String name, Integer price, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update "+
                TABLE_PRODUCT+
                " set name='"+ name+ "', " +
                " price ='"+price.toString()+ "', " +
                " description='"+description+ "' " +
                "where code='"+code+"'");
        db.close();
        System.out.println("Data sudah di update "+code);
    }

}
