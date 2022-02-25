package com.mobileprogramming.mobileprogamming3.poducts;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileprogramming.mobileprogamming3.DatabaseHelper;
import com.mobileprogramming.mobileprogamming3.R;
import com.mobileprogramming.mobileprogamming3.Register;
import com.mobileprogramming.mobileprogamming3.Wishlist.WishlistDatabase;
import com.mobileprogramming.mobileprogamming3.login;

public class DetailViewActivity extends AppCompatActivity {
    Button wishbtn, deleteBtn;
    ProductBackend db;
    TextView _name, _price, _desc, _cat;
    WishlistDatabase wishdb;
    DatabaseHelper userdb;
    ContentValues contentValues;
    SharedPreferences sharedPreferences;
    ProductModel product = new ProductModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String pos = getIntent().getStringExtra("position");
        db = new ProductBackend(this);
        wishdb = new WishlistDatabase(this);
        userdb = new DatabaseHelper(this);


        setContentView(R.layout.activity_detail_view);
        _name = findViewById(R.id.name);
        _price = findViewById(R.id.price);
        _desc = findViewById(R.id.description);
        _cat = findViewById(R.id.category);
        wishbtn = findViewById(R.id.wishbtn);
        deleteBtn = findViewById(R.id.delete);

        product = db.getSingleProduct(pos + "");

        _name.setText("Name:\t" + product.getName());
        _price.setText("Price:\tRs." + product.getPrice());
        _desc.setText("\t" + product.getDescription());
        _cat.setText("Category\n" + product.getCategory());


        wishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences("LoggedIn", MODE_PRIVATE);
                String share_username = sharedPreferences.getString("username", null);
                if (share_username == null) {
                    Toast.makeText(DetailViewActivity.this, "Pleses login first ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DetailViewActivity.this, login.class);
                    startActivity(intent);
                } else {
                    String userid = sharedPreferences.getString("id", "0");
                    contentValues = new ContentValues();
                    contentValues.put("productId", product.getId());
                    contentValues.put("userId", Integer.valueOf(userid));
                    wishdb.insertWishlist(contentValues);
                    Toast.makeText(DetailViewActivity.this, "Added to the wishlist ", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailViewActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Delete this product ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deleteProduct(product.getId());
                        Toast.makeText(DetailViewActivity.this, "Product Delete successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DetailViewActivity.this,ProductDisplay.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
                AlertDialog dailog = builder.create();
                dailog.show();


            }
        });
    }
}