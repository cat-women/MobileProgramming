package com.mobileprogramming.mobileprogamming3.Wishlist;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobileprogramming.mobileprogamming3.DatabaseHelper;
import com.mobileprogramming.mobileprogamming3.UserInfo;
import com.mobileprogramming.mobileprogamming3.poducts.ProductBackend;

import java.util.ArrayList;

public class WishlistDatabase extends SQLiteOpenHelper {
    public static final String db_name = "MessengerDB";
    public static int db_version = 1;
    DatabaseHelper userdb;
    ProductBackend productdb;

    final String createTableQuery = "CREATE TABLE if not exists \"wishlist\" (\n" +
            "\t\"id\"\tINTEGER,\n" +
            "\t\"productId\"\tINTEGER,\n" +
            "\t\"userId\"\tINTEGER,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT),\n" +
            "\tFOREIGN KEY(\"userId\") REFERENCES \"users\"(\"ID\"),\n" +
            "\tFOREIGN KEY(\"productId\") REFERENCES \"products\"(\"id\")\n" +
            ");";

    public WishlistDatabase(Context context) {

        super(context, db_name, null, db_version);
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
    public boolean insertWishlist(ContentValues contentValues) {
        //getWritableDatabase().insert("products", null, contentValues);
        try {
            getWritableDatabase().insert("wishlist", null, contentValues);
            return true;
        } catch (Exception e) {
            Log.e("dbError", e.getMessage());
            return false;
        }
    }

    //Read data
    @SuppressLint("Range")
    public ArrayList<wishlistModel> getWishlist() {
        ArrayList<wishlistModel> wishes = new ArrayList<>();

        try {
            String sql = "SELECT users.Name AS username,products.name AS productname,products.price AS price,products.category AS cat,wishlist.id,wishlist.userId,wishlist.productId\n" +
                    "FROM users,wishlist,products\n" +
                    "where users.ID = wishlist.userId AND products.id = wishlist.productId";

            Cursor cursor = getReadableDatabase().rawQuery(sql, null);
            while (cursor.moveToNext()) {
                wishlistModel wish = new wishlistModel();
                wish.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                wish.setUserId(cursor.getInt(cursor.getColumnIndex("userId")));
                wish.setProductId(cursor.getInt(cursor.getColumnIndex("productId")));
                wish.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                wish.setProductName(cursor.getString(cursor.getColumnIndex("productname")));
                wish.setCategory(cursor.getString(cursor.getColumnIndex("cat")));
                wish.setPrice(cursor.getString(cursor.getColumnIndex("price")));
                wishes.add(wish);
            }
            cursor.close();
        } catch (Exception e) {
            Log.e("dbError", e.getMessage());
        }
        return wishes;

    }

    //Read data
    @SuppressLint("Range")
    public wishlistModel onewish(int id) {
        wishlistModel wish = new wishlistModel();

        try {
            String sql = "SELECT users.Name AS username,products.name AS productname,products.price AS price,products.category AS cat,wishlist.id,wishlist.userId,wishlist.productId\n" +
                    "FROM users,wishlist,products\n" +
                    "where users.ID = wishlist.userId AND products.id = wishlist.productId AND id ="+id;

            Cursor cursor = getReadableDatabase().rawQuery(sql, null);
            while (cursor.moveToNext()) {
                wish.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
                wish.setUserId(cursor.getInt(cursor.getColumnIndex("userId")));
                wish.setProductId(cursor.getInt(cursor.getColumnIndex("productId")));
                wish.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                wish.setProductName(cursor.getString(cursor.getColumnIndex("productname")));
                wish.setCategory(cursor.getString(cursor.getColumnIndex("cat")));
                wish.setPrice(cursor.getString(cursor.getColumnIndex("price")));

            }
            cursor.close();
        } catch (Exception e) {
            Log.e("dbError", e.getMessage());
        }
        return wish;
    }

    public void deleteWish(int id) {
        getWritableDatabase().delete("wishlist", "id=" + id + "", null);
    }


}


