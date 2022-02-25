package com.mobileprogramming.mobileprogamming3.poducts;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobileprogramming.mobileprogamming3.UserInfo;

import java.util.ArrayList;

public class ProductBackend extends SQLiteOpenHelper {
    public static final String db_name = "MessengerDB";
    public static  int db_version = 1;

    final String createTableQuery = "CREATE TABLE IF NOT EXISTS \"products\" (\n" +
            "\t\"id\"\tINTEGER,\n" +
            "\t\"name\"\tTEXT,\n" +
            "\t\"description\"\tTEXT,\n" +
            "\t\"category\"\tTEXT,\n" +
            "\t\"price\"\tINTEGER,\n" +
            "\t\"image\"\tBLOB,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ")";

    public ProductBackend(Context context) {

        super(context,db_name,null,db_version);
        getWritableDatabase().execSQL(createTableQuery);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //CRUD
    //Insert
    public boolean insertProduct(ContentValues contentValues){
        //getWritableDatabase().insert("products", null, contentValues);


        try {
            getWritableDatabase().insert("products", null, contentValues);
            return true;
        }catch (Exception e){
            Log.e("dbError",e.getMessage());
            return false;
        }
    }

    //Read data
    @SuppressLint("Range")
    public ArrayList<ProductModel> getProduct(){
        String sql = "SELECT * from products";
        Cursor cursor  = getReadableDatabase().rawQuery(sql,null);

        ArrayList<ProductModel> products = new ArrayList<>();

        while(cursor.moveToNext()){
            ProductModel  product = new ProductModel();
            product.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            product.setName(cursor.getString(cursor.getColumnIndex("name")));
            product.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            product.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            product.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex("price"))));


            products.add(product);
        }
        cursor.close();
        return products;

    }

    //get single user
    @SuppressLint("Range")
    public ProductModel  getSingleProduct( String id){
        String  sql = " SELECT * from products WHERE id  = "+id;
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        ProductModel  product = new ProductModel();
        while(cursor.moveToNext()){
            product.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            product.setName(cursor.getString(cursor.getColumnIndex("name")));
            product.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            product.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            product.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex("price"))));

        }
        cursor.close();
        return product;
    }
    public void deleteProduct(int id){
        getWritableDatabase().delete("products","id="+id+"",null);
    }

}
