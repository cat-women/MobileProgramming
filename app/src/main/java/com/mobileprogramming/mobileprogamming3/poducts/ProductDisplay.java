package com.mobileprogramming.mobileprogamming3.poducts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.mobileprogramming.mobileprogamming3.camera.CameraActivity;
import com.mobileprogramming.mobileprogamming3.MapsActivity;
import com.mobileprogramming.mobileprogamming3.R;
import com.mobileprogramming.mobileprogamming3.Wishlist.DisplayWishlistActivity;

public class ProductDisplay extends AppCompatActivity {
    GridView gridView;
    ProductList productList;
    ProductBackend db;

    Button wishbtn, _viewDetailBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);
        showProductList();
    }

    //setting adapter
    public void showProductList() {
        gridView = (GridView) findViewById(R.id.productlist);
        db = new ProductBackend(this);
        productList = new ProductList(this, db.getProduct());
        gridView.setAdapter(productList);

        wishbtn = findViewById(R.id.wish);
        wishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDisplay.this, DisplayWishlistActivity.class);
                startActivity(intent);
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ProductDisplay.this, DetailViewActivity.class);
                intent.putExtra("position", i + "");
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.deleteSharedPreferences("LoggedIn");

    }

    @Override
    protected void onResume() {
        super.onResume();
        showProductList();

    }

    //Setting menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.add:
                startActivity(new Intent(ProductDisplay.this, AddProductActivity.class));
                break;
            case R.id.Viewwhishlist:
                startActivity(new Intent(ProductDisplay.this, DisplayWishlistActivity.class));
                break;
            case R.id.products:
                startActivity(new Intent(ProductDisplay.this, ProductDisplay.class));
                break;
            case R.id.addProduct:
                startActivity(new Intent(ProductDisplay.this, AddProductActivity.class));
                break;
            case R.id.logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle("Are you sure, you want to logout?");
                builder.setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences sharedPreferences = getSharedPreferences("LoggedIn", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear().commit();
                        deleteSharedPreferences("username");
                        deleteSharedPreferences("passoord");
                        deleteSharedPreferences("id");
                    }
                });
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
                AlertDialog dailog = builder.create();
                dailog.show();
                break;
            case R.id.map:
                startActivity(new Intent(ProductDisplay.this, MapsActivity.class));
                break;

            case R.id.camera:
                startActivity(new Intent(ProductDisplay.this, CameraActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}