package com.mobileprogramming.mobileprogamming3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    LinearLayout cointainer;
    DatabaseHelper dbhelper;
    ArrayList<UserInfo> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        cointainer = findViewById(R.id.userInforcontainer);

        dynamiDisplay();

    }
    public  void dynamiDisplay(){
        dbhelper = new DatabaseHelper(this);
        users = dbhelper.getUserList();
        cointainer.removeAllViews();
        for(UserInfo user: users){
            View view = LayoutInflater.from(this).inflate(R.layout.user_list_layout,null);
            TextView id = view.findViewById(R.id.id);
            TextView username = view.findViewById(R.id.username);
            TextView email = view.findViewById(R.id.email);
            System.out.println(user);
            //setting value to the textview
            id.setText(String.valueOf(user.getId()));
            username.setText(user.getUsername());
            email.setText(user.getEmail());
            cointainer.addView(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(AdminActivity.this, "View individual data  ", Toast.LENGTH_SHORT).show();
                    startActivity(
                            new Intent(AdminActivity.this, DetailActivity.class).putExtra("id",user.getId())

                    );
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        dynamiDisplay();
    }
}