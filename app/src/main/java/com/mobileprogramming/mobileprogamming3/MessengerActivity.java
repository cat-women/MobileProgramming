package com.mobileprogramming.mobileprogamming3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MessengerActivity extends AppCompatActivity {
Button logout;
SharedPreferences sharedPreferences;
DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);

        // values from login
        String name = getIntent().getStringExtra("name");
        TextView title = (TextView)findViewById(R.id.title);
        title.setText(name);
        logout = findViewById(R.id.logout);
        sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }
    public void logoutUser(View view){
        sharedPreferences.edit().putBoolean("isRemember",false).commit();
        startActivity(new Intent(MessengerActivity.this,login.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu1:
                Toast.makeText(this, "this is menu one ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Intent intent = new Intent(MessengerActivity.this,AdminActivity.class);
                startActivity(intent);
                Toast.makeText(this, "going  admin panel ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu3:
                Toast.makeText(this, "this is menu three ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu4:
                Toast.makeText(this, "this is menu four ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.submenu1:
                Toast.makeText(this, "this is sub menu 1 ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.submenu2:
                Toast.makeText(this, "this is sub menu  2 ", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //for pop up menu
    public void showPopUPMenu(View view){
        PopupMenu menu = new PopupMenu(this,view);
        getMenuInflater().inflate(R.menu.pop_up,menu.getMenu());
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.changeName:
                        Toast.makeText(MessengerActivity.this, "chang name ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.changeProfile:
                        Toast.makeText(MessengerActivity.this, "chang profile ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.viewDetail:
                        Toast.makeText(MessengerActivity.this, "view detail ", Toast.LENGTH_SHORT).show();
                        break;
                }
                return  false;
            }

        });
        menu.show();
    }
}