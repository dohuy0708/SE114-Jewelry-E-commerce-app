package com.example.jewelryecommerceapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class CustomerManagementActitvity extends AppCompatActivity {
    private ImageView Back;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setupListener();
    }
    private void initView()
    {
       /* Back = findViewById(R.id.imageview_back_Customer_management);
        recyclerView = findViewById(R.id.customer_recycleView);*/
    }
    private void setupListener()
    {
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
