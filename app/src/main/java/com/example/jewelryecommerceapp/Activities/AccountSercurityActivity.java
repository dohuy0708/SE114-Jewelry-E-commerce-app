package com.example.jewelryecommerceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jewelryecommerceapp.R;

public class AccountSercurityActivity extends AppCompatActivity
{
    private Button btnSave;
    private ImageView Back, editPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_sercurity);
        initView();
        setupClickListener();

    }
    private void initView()
    {
        btnSave = findViewById(R.id.button_save_account_sercurity);
        Back = findViewById(R.id.imageview_back_sercurity);
        editPassword = findViewById(R.id.imageview_changePassword_accountSercurity);
    }
    private void setupClickListener()
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
        editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountSercurityActivity.this,ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }

}
