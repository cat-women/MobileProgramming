package com.mobileprogramming.mobileprogamming3.Wishlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.mobileprogramming.mobileprogamming3.R;
import com.mobileprogramming.mobileprogamming3.poducts.DetailViewActivity;
import com.mobileprogramming.mobileprogamming3.poducts.ProductBackend;
import com.mobileprogramming.mobileprogamming3.poducts.ProductDisplay;
import com.mobileprogramming.mobileprogamming3.poducts.ProductList;

public class DisplayWishlistActivity extends AppCompatActivity {
    GridView gridView;
    Wishlist wishlist;
    WishlistDatabase db;
    Button delete;
    wishlistModel modal  =new wishlistModel();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_wishlist);
        gridView = (GridView) findViewById(R.id.wishlist);
        db = new WishlistDatabase(this);
        delete  = findViewById(R.id.delete);

        load();

//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                modal = db.onewish(i);
//                AlertDialog.Builder builder = new AlertDialog.Builder(DisplayWishlistActivity.this);
//                builder.setCancelable(true);
//                builder.setTitle("Delete this product ?");
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                        Log.w("wishlist pos ",i+"");
//                        db.deleteWish(i);
//                        Toast.makeText(DisplayWishlistActivity.this, "Removed successful \t"+i, Toast.LENGTH_SHORT).show();
////                        Intent intent = new Intent(DisplayWishlistActivity.this,ProductDisplay.class);
////                        startActivity(intent);
//                        finish();
//
//                    }
//                });
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        return;
//                    }
//                });
//                AlertDialog dailog = builder.create();
//                dailog.show();
//            }
//        });
    }
    public void load(){
        wishlist = new Wishlist(this, db.getWishlist());
        gridView.setAdapter(wishlist);


    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
    }
}