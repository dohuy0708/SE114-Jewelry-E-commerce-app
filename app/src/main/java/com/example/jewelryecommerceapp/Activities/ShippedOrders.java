package com.example.jewelryecommerceapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Adapters.ShippedOrdsAdapter;
import com.example.jewelryecommerceapp.Adapters.ShippingOrdsAdapter;
import com.example.jewelryecommerceapp.Models.Order;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class ShippedOrders extends AppCompatActivity {

    ImageView back;
    RecyclerView ordds;
    ShippedOrdsAdapter adt;
    ArrayList<Order> listords;
    ImageView Sort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shippedords);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        back=findViewById(R.id.backshipped);
        Sort=findViewById(R.id.sortshipped);
        listords=new ArrayList<>();
        initlistords(listords);
        ordds=findViewById(R.id.listshipped);
        adt=new ShippedOrdsAdapter(this,listords);
        ordds.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ordds.setHasFixedSize(true);
        ordds.setAdapter(adt);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        Sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initlistords(ArrayList<Order> listords) {
    }
}