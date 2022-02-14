package com.mobileprogramming.mobileprogamming3;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "MessengerDb";
    static int version = 1;

    String createTableQuery = "CREATE TABLE if not exists 'users' (ID integer primary key,Name varchar(20) , Email varchar(20), Contact varchar(20), Gender varchar(10),Username varchar(20),Password varchar(20))";

    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTableQuery);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertUser(ContentValues contentValues){
            getWritableDatabase().insert("users",null, contentValues);
    }


        //select user data
    @SuppressLint("Range")
    public ArrayList<UserInfo>  getUserList(){
        String  sql = " SELECT * from users";
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        ArrayList<UserInfo> userlist = new ArrayList<>();

        while(cursor.moveToNext()){
            UserInfo  user = new UserInfo();
            user.setId( cursor.getString(cursor.getColumnIndex("ID")));
            user.setFullname( cursor.getString(cursor.getColumnIndex("Name")));
            user.setContact( cursor.getString(cursor.getColumnIndex("Contact")));
            user.setEmail( cursor.getString(cursor.getColumnIndex("Email")));
            user.setGender( cursor.getString(cursor.getColumnIndex("Gender")));
            user.setPassword( cursor.getString(cursor.getColumnIndex("Password")));
            //user.setImage( cursor.getbyte(cursor.getColumnIndex("Image")));

            userlist.add(user);
        }
        cursor.close();
        return userlist;

    }

    //get single user
    @SuppressLint("Range")
    public UserInfo  getUser( String id){
        String  sql = " SELECT * from users WHERE id  = "+id;
        Cursor cursor = getReadableDatabase().rawQuery(sql,null);
        UserInfo  user = new UserInfo();

        while(cursor.moveToNext()){
            user.setId( cursor.getString(cursor.getColumnIndex("ID")));
            user.setFullname( cursor.getString(cursor.getColumnIndex("Name")));
            user.setContact( cursor.getString(cursor.getColumnIndex("Contact")));
            user.setEmail( cursor.getString(cursor.getColumnIndex("Email")));
            user.setGender( cursor.getString(cursor.getColumnIndex("Gender")));
            user.setPassword( cursor.getString(cursor.getColumnIndex("Password")));
        }
        cursor.close();
        return user;
    }

    public void updateUser(String id,ContentValues contentValues){
            getWritableDatabase().update("users",contentValues,"ID= ?",new String[]{id});

    }


    public void deleteUser(String id){
        getWritableDatabase().delete("users","ID="+id,null);

    }


    public boolean islogin(String pwd, String username){
        String sql = "SELECT * FROM users where Password='"+pwd+"' and Username = '"+username+"'";
        SQLiteStatement stm = getReadableDatabase().compileStatement(sql);
        long l  = stm.simpleQueryForLong();
        if(l == 1){
            return true;
        }else
            return false;
    }


}
