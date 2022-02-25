package com.mobileprogramming.mobileprogamming3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText username, pwd;
    Button login, register;
    CheckBox rememberme;
    SharedPreferences sharedPreferences;
    DatabaseHelper db;
    UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);
        db = new DatabaseHelper(this);

        username = findViewById(R.id.username);
        pwd = findViewById(R.id.pwd);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        rememberme = findViewById(R.id.remember);

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameValue = username.getText().toString();
                String pwdValue = pwd.getText().toString();
                user = db.atuthenticatication(usernameValue);

                if(usernameValue.isEmpty() || pwdValue.isEmpty()){
                    Toast.makeText(login.this, "values are empty  ", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(usernameValue.equals(user.getUsername()) && pwdValue.equals(user.getPassword())){
                    sharedPreferences = getSharedPreferences("LoggedIn", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("id",user.getId());
                    editor.putString("username",user.getUsername());
                    editor.putString("password",user.getPassword());
                    if(rememberme.isChecked()){
                        editor.putBoolean("rememer",true);
                    }
                    editor.apply();

                    Toast.makeText(login.this, "login successful ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(login.this, MessengerActivity.class);
                    intent.putExtra("name",usernameValue);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(login.this, "User doesn't exits", Toast.LENGTH_SHORT).show();
                }

            }
        });
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, Register.class);
                startActivity(intent);
            }
        });

//        if(sharedPreferences.getBoolean("rememer",true)){
//            startActivity(new Intent(login.this,MessengerActivity.class));
//            finish();
//        }

    }
}


