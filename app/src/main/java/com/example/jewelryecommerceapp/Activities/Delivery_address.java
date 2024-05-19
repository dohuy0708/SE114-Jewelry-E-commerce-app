package com.example.jewelryecommerceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jewelryecommerceapp.R;

public class Delivery_address extends AppCompatActivity {

    EditText name, phone,province, street, detail ;
    ImageView butback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);

        butback= findViewById(R.id.btn_comeback);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        province=findViewById(R.id.province);
        street= findViewById(R.id.street);
        detail= findViewById(R.id.detail);

        butback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}