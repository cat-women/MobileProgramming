package com.mobileprogramming.mobileprogamming3.poducts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mobileprogramming.mobileprogamming3.R;

import java.util.ArrayList;

public class ProductList extends ArrayAdapter<ProductModel> {
    Context context;

    public ProductList(@NonNull Context context, ArrayList<ProductModel> productlist) {
        super(context, 0, productlist);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View view = LayoutInflater.from(context).inflate(R.layout.product_list, null);


        ImageView _image = view.findViewById(R.id.image);
        TextView _name = view.findViewById(R.id.name);
        TextView _price = view.findViewById(R.id.price);
        TextView _description = view.findViewById(R.id.description);
        TextView _cat = view.findViewById(R.id.category);

        Button _wishlistbtn = view.findViewById(R.id.wishbtn);
        Button _detailbtn = view.findViewById(R.id.viewDetail);

        ProductModel info = getItem(position);

        _name.setText("Name:\t"+info.getName());
        _price.setText("Price: \t"+String.valueOf(info.getPrice()));
        _cat.setText("Category:\t"+info.getCategory());

        _description.setText("Description\n"+info.getDescription());
        return view;

    }

    @Nullable
    @Override
    public ProductModel getItem(int position) {
        return super.getItem(position);
    }

}
