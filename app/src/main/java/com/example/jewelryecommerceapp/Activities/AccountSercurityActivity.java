package com.example.jewelryecommerceapp.Activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jewelryecommerceapp.R;

public class AccountSercurityActivity extends AppCompatActivity
{
    private Button btnSave;
    private ImageView Back;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_sercurity);
        initView();
        setupButton();

    }
    private void initView()
    {
        btnSave = findViewById(R.id.button_save_account_sercurity);
        Back = findViewById(R.id.imageview_back_sercurity);
    }
    private void setupButton()
    {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
