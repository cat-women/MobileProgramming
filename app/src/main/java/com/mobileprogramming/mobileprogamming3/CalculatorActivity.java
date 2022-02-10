package com.mobileprogramming.mobileprogamming3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {
    EditText getPrinciple,getTime,getRate;
    TextView getResult;
    Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        getPrinciple = findViewById(R.id.principle);
        getTime = findViewById(R.id.time);
        getRate = findViewById(R.id.rate);
        getResult = findViewById(R.id.result);
        calculate = findViewById(R.id.calculate);



    }
    public  void calculate(View view){
        float principle =Float.valueOf(getPrinciple.getText().toString());
        float rate = Float.valueOf(getRate.getText().toString());
        float time = Float.valueOf(getTime.getText().toString());

        float result = (principle * rate * time) / 100 ;
        getResult.setText("The simple Interest is "+String.valueOf(result));

    }
}