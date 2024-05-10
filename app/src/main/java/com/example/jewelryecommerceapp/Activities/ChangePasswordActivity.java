package com.example.jewelryecommerceapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jewelryecommerceapp.R;

public class ChangePasswordActivity extends AppCompatActivity {
    private ImageView Back;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
        setupListener();
    }
    private void initView()
    {
        Back = findViewById(R.id.imageview_back_changePassword);
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
