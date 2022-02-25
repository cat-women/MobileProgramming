package com.mobileprogramming.mobileprogamming3.poducts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mobileprogramming.mobileprogamming3.DatabaseHelper;
import com.mobileprogramming.mobileprogamming3.R;
import com.mobileprogramming.mobileprogamming3.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {
    TextView _name,_desc,_price,_cat;
    Button saveBtn;
    ProductBackend db;
    ContentValues contentValues;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        _name = findViewById(R.id.productname);
        _desc = findViewById(R.id.productdetail);
        _price = findViewById(R.id.productprice);
        saveBtn = findViewById(R.id.save);
        Spinner mySpinner = (Spinner) findViewById(R.id.category);
        String category = mySpinner.getSelectedItem().toString();

        contentValues = new ContentValues();
        db = new ProductBackend(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView selectedCat = findViewById(R.id.selected);
                selectedCat.setText(category);
                Log.i("spiiner value", "value form spiiner" + category);
                Log.i("test", "this is test");

                String name = _name.getText().toString();
                String desc = _desc.getText().toString();
                String price = _price.getText().toString();
                Log.i("values ","name"+name+"desc"+desc+"price "+price);

                contentValues.put("name",name);
                contentValues.put("description",desc);
                contentValues.put("category",category);
                contentValues.put("price",Integer.valueOf(price));
                boolean result = db.insertProduct(contentValues);
                if (result = true) {
                    Toast.makeText(AddProductActivity.this, "product added  ", Toast.LENGTH_LONG).show();
                    startActivity( new Intent(AddProductActivity.this,ProductDisplay.class));
                }else {
                    Toast.makeText(AddProductActivity.this, "Failed to add new product  ", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
