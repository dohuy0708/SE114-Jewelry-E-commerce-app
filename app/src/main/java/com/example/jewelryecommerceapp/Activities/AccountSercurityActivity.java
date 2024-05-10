package com.example.jewelryecommerceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jewelryecommerceapp.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountSercurityActivity extends AppCompatActivity
{
    private Button btnSave;
    private ImageView Back, editPassword;
    private TextInputLayout fullname,phoneNumber,email;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_sercurity);
        initView();
        setupClickListener();
        getUserInformation();
    }
    private void initView()
    {
        btnSave = findViewById(R.id.button_save_account_sercurity);
        Back = findViewById(R.id.imageview_back_sercurity);
        editPassword = findViewById(R.id.imageview_changePassword_accountSercurity);
        fullname = findViewById(R.id.edittext_fullname_accountSercurity);
        phoneNumber = findViewById(R.id.edittext_phoneNumber_accountSercurity);
        email = findViewById(R.id.edittext_email_accountSercurity);
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
    private void getUserInformation()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null)
        {
            return;
        }
        String Name = user.getDisplayName();
        String Email = user.getEmail();
        String Phone = user.getPhoneNumber();
        fullname.getEditText().setText(Name);
        phoneNumber.getEditText().setText(Phone);
        email.getEditText().setText(Email);

    }

}
