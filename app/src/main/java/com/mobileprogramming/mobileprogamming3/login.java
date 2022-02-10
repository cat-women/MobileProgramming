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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_form);

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
                sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);

                String defaultUserName = sharedPreferences.getString("username", "");

                String defaultPwd = sharedPreferences.getString("password","123");

                Boolean rem = sharedPreferences.getBoolean("isRemember",false);


                if(defaultUserName == "" || defaultPwd == ""){
                    Toast.makeText(login.this, "values are empty  ", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (usernameValue.equals(defaultUserName) && pwdValue.equals(defaultPwd)) {
                    Toast.makeText(login.this, "login successful ", Toast.LENGTH_SHORT).show();
                    if (rememberme.isChecked()) {
                        sharedPreferences.edit().putBoolean("isRemember",true).apply();
                    }else{
                        sharedPreferences.edit().putBoolean("isRemember",false).apply();
                    }
                    Intent intent = new Intent(login.this, MessengerActivity.class);
                    intent.putExtra("name",usernameValue);
                    startActivity(intent);

                    finish();
                } else {
                    Toast.makeText(login.this, "login failed ", Toast.LENGTH_SHORT).show();

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
//        if(sharedPreferences.getBoolean("isRemember",false)){
//            startActivity(new Intent(login.this,MessengerActivity.class));
//            finish();
//        }

    }
}


