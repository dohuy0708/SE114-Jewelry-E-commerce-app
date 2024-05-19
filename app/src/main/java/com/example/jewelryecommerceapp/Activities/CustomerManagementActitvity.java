package com.example.jewelryecommerceapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jewelryecommerceapp.Adapters.CustomerAdapter;
import com.example.jewelryecommerceapp.Models.Customer;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerManagementActitvity extends AppCompatActivity {
    private ImageView Back;
    private RecyclerView recyclerView;
    private CustomerAdapter customerAdapter;
    private List<Customer> customerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_management);
        initView();
        setupListener();
    }
    private void initView()
    {
        Back = findViewById(R.id.imageview_back_Customer_management);
        recyclerView = findViewById(R.id.customer_recycleView);
        customerList = new ArrayList<>();
        customerList.add(new Customer(1111 , "Nguyễn Văn A","01/01/2020"));
        customerList.add(new Customer(1112 , "Nguyễn Văn B","01/01/2021"));
        customerList.add(new Customer(1113 , "Nguyễn Văn C","01/01/2022"));
        customerAdapter = new CustomerAdapter(customerList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customerAdapter);
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
