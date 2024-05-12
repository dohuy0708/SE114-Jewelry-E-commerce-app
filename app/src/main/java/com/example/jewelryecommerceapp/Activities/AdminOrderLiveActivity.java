package com.example.jewelryecommerceapp.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jewelryecommerceapp.Adapters.AdOrderLiveAdapter;

import com.example.jewelryecommerceapp.Models.AdOrderLive;

import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class AdminOrderLiveActivity extends AppCompatActivity {

    private Context context;
    private Button addButton;
    private AdOrderLiveAdapter adapter;

    private RecyclerView recyclerView;
    private List<AdOrderLive> orderLiveItemList;
    private int numberOfServicesAdded = 0;
    private TextView numberServiceTextView;

    private final double totalValue = 0;
    private TextView totalValueTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_live);
        addButton = findViewById(R.id.button8);
        recyclerView = findViewById(R.id.recyclerview2);
        numberServiceTextView = findViewById(R.id.number_service);
        totalValueTextView = findViewById(R.id.textView32);
        orderLiveItemList = new ArrayList<>();
        adapter = new AdOrderLiveAdapter(context, orderLiveItemList, totalValueTextView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        addButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {
                AdOrderLive newItem = new AdOrderLive();
                //serviceItemList.add(newItem);
                //adapter.notifyDataSetChanged();
                adapter.addOrderLive(newItem);
                numberOfServicesAdded++;
                numberServiceTextView.setText(String.valueOf(numberOfServicesAdded));

                totalValueTextView.setText(String.format("%.0f VND", adapter.calculateTotalValue()));
                Log.d("TotalValue", "Total value: " + totalValue+ adapter.calculateTotalValue());
            }
        });

    }
}
