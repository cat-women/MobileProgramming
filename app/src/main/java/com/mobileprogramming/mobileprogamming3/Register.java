package com.mobileprogramming.mobileprogamming3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    EditText username, pwd, fullname, email, contact;
    RadioGroup gender;
    RadioButton male, female, genderValue;
    Button submit;
    SharedPreferences sharedPreferences;
    DatabaseHelper databse;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        databse = new DatabaseHelper(this);
        id = getIntent().getIntExtra("id",0);
        Log.e("id", "value of id"+String.valueOf(id));
        sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

        username = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);
        fullname = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        contact = findViewById(R.id.contactNo);
        gender = findViewById(R.id.gender);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        if(id != 0){
            UserInfo user = databse.getUser(String.valueOf(id));
            username.setText(user.getUsername());
            email.setText(user.getEmail());
            pwd.setText(user.getPassword());
            contact.setText(user.getContact());
            fullname.setText(user.getFullname());

            if(user.getGender().equals("Male")) {
                ((RadioButton)findViewById(R.id.male)).setChecked(true);
            } else{
                ((RadioButton)findViewById(R.id.female)).setChecked(true);
            }
            submit = findViewById(R.id.submit);
            submit.setText("Update");

            ((TextView)findViewById(R.id.registerTitle)).setText("Update ");
        }


 }



    public void registerUser(View view ){
        String usernameName = username.getText().toString();
        String pwdValue = pwd.getText().toString();
        String fullnameValue = fullname.getText().toString();
        String emailValue = email.getText().toString();
        String contactValue = contact.getText().toString();

        RadioButton checkedBtn = findViewById(gender.getCheckedRadioButtonId());
        String genderValue = checkedBtn.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //Database call
        databse = new DatabaseHelper(this);

//        editor.putString("username",usernameName);
//        editor.putString("password ",pwdValue.toString());
//        editor.putString("name",fullnameValue);
//        editor.putString("email",emailValue);
//        editor.putString("contact",contactValue);
//        editor.putString("gender",genderValue);
//        editor.apply();
//        Toast.makeText(Register.this, "user added successfully ", Toast.LENGTH_SHORT).show();


        ContentValues contentValues = new ContentValues();
        //contentValues.put("ID","1");
        contentValues.put("Username",usernameName);
        contentValues.put("Email",emailValue);
        contentValues.put("Name",fullnameValue);
        contentValues.put("Gender",genderValue);
        contentValues.put("Contact",contactValue);
        contentValues.put("Password",pwdValue);

        if(id == 0) {
            databse.insertUser(contentValues);
            Toast.makeText(this,"userinfo saved ",Toast.LENGTH_LONG).show();
            startActivity(new Intent(Register.this,login.class));
        }else{
            databse.updateUser(id+"",contentValues);
            Toast.makeText(this,"user data updated ",Toast.LENGTH_LONG).show();
            finish();

        }





    }
}