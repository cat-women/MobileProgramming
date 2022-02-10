package com.mobileprogramming.mobileprogamming3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    DatabaseHelper db;
    TextView email,username,pwd,contact,name,gender;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        id = getIntent().getStringExtra("id");
        db = new DatabaseHelper(this);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        pwd = findViewById(R.id.pwd);
        contact = findViewById(R.id.contactNo);
        gender = findViewById(R.id.gender);

        displayInfo();
        findViewById(R.id.update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("data","clicking update button ");
                Toast.makeText(DetailActivity.this, "going to user update ", Toast.LENGTH_SHORT).show();
                Intent intent =  new Intent(DetailActivity.this ,Register.class);
                intent.putExtra("id",Integer.parseInt(id));
                startActivity(intent);
            }
        });

       findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Log.i("data","clicking delete button ");
               showAlertDialog();
           }
       });


    }
    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete user !");
        builder.setMessage("!!! Are you sure ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleteUser(id);
                finish();
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.setCancelable(false);  //dialog can't be removed until cancled
        builder.show();
    }



    public void displayInfo(){
        UserInfo user = db.getUser(id);
        username.setText("Username :"+user.getUsername());
        email.setText("Email :"+user.getEmail());
        pwd.setText("Password :"+user.getPassword());
        contact.setText("Contact :"+user.getContact());
        name.setText("Name :"+user.getFullname());
        gender.setText("Gender :"+user.getGender());
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayInfo();
    }
}