package com.example.jewelryecommerceapp.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jewelryecommerceapp.Adapters.AdServiceAdapter;
import com.example.jewelryecommerceapp.Fragments.ServiceFragment;
import com.example.jewelryecommerceapp.Models.AdService;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    private Button addButton;

    private Button buttonServiceType;
    private AdServiceAdapter adapter;
    private List<AdService> serviceItemList;
    private int numberOfServicesAdded = 0;
    private TextView numberServiceTextView;

    private double totalValue = 0;
    private TextView totalValueTextView;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_service);

            recyclerView = findViewById(R.id.recyclerview2);
            addButton = findViewById(R.id.button8);
            numberServiceTextView = findViewById(R.id.number_service);
            totalValueTextView = findViewById(R.id.textView32);

            serviceItemList = new ArrayList<>();
            adapter = new AdServiceAdapter(context, serviceItemList, totalValueTextView);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            addButton.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("DefaultLocale")
                @Override
                public void onClick(View v) {
                    AdService newItem = new AdService();
                    //serviceItemList.add(newItem);
                    //adapter.notifyDataSetChanged();
                    adapter.addService(newItem);
                    numberOfServicesAdded++;
                    numberServiceTextView.setText(String.valueOf(numberOfServicesAdded));

                    totalValueTextView.setText(String.format("%.0f VND", adapter.calculateTotalValue()));
                    Log.d("TotalValue", "Total value: " + totalValue+ adapter.calculateTotalValue());
                }
            });


        }



}
