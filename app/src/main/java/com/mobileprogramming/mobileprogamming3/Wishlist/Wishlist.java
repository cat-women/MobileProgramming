package com.mobileprogramming.mobileprogamming3.Wishlist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.mobileprogramming.mobileprogamming3.R;
import com.mobileprogramming.mobileprogamming3.poducts.ProductDisplay;
import com.mobileprogramming.mobileprogamming3.poducts.ProductModel;

import java.util.ArrayList;

public class Wishlist extends ArrayAdapter<wishlistModel> {
    Context context;
    WishlistDatabase db;
    wishlistModel info;
    Button remove;

    public Wishlist(@NonNull Context context, ArrayList<wishlistModel> wishlist) {
        super(context, 0, wishlist);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View view = LayoutInflater.from(context).inflate(R.layout.wishlist, null);


        ImageView _image = view.findViewById(R.id.image);
        TextView _name = view.findViewById(R.id.username);
        TextView _price = view.findViewById(R.id.price);
        TextView productname = view.findViewById(R.id.productname);
        TextView _cat = view.findViewById(R.id.category);
        remove = (Button) view.findViewById(R.id.remove);


        info = getItem(position);
        db = new WishlistDatabase(context);


        _name.setText("User Name :\t" + info.getUsername());
        _price.setText("Price: \t" + String.valueOf(info.getPrice()));
        _cat.setText("Category:\t" + info.getCategory());
        productname.setText("product :\t" + info.getProductName());
        _price.setText("Price: Rs. "+info.getPrice());

        delete();
        return view;


    }

    @Nullable
    @Override
    public wishlistModel getItem(int position) {
        return super.getItem(position);
    }

    public void delete(){
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("delete wish ",info.getId()+"");
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setTitle("Remove this from wishlist ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.deleteWish(info.getId());
                        Toast.makeText(context, "Removed successful", Toast.LENGTH_SHORT).show();
                        ((Activity)context).finish();
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
