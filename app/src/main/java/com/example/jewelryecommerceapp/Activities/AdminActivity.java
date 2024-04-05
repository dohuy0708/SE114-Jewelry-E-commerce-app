package com.example.jewelryecommerceapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.jewelryecommerceapp.R;

public class AdminActivity extends Activity {
    Button btnAccount,btnRevenue,btnLogistic,btnRate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        InitUI();
    }
    private void InitUI()
    {
        btnAccount = findViewById(R.id.account_button);
        btnRevenue = findViewById(R.id.revenue_button);
        btnLogistic = findViewById(R.id.logistic_button);
        btnRate = findViewById(R.id.rate_button);
    }
    private void InitListener()
    {
        btnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminActivity.this, "alfajlfajlfhajkfhaskf", Toast.LENGTH_SHORT).show();
            }
        });
        btnRevenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminActivity.this, "2", Toast.LENGTH_SHORT).show();
            }
        });
        btnLogistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminActivity.this, "3", Toast.LENGTH_SHORT).show();
            }
        });
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AdminActivity.this, "4", Toast.LENGTH_SHORT).show();
            }
        });




    }



}
