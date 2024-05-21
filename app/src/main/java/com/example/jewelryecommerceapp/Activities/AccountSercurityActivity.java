package com.example.jewelryecommerceapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jewelryecommerceapp.Models.User;
import com.example.jewelryecommerceapp.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;
import org.jetbrains.annotations.NotNull;

public class AccountSercurityActivity extends AppCompatActivity
{
    private Button btnSave;
    private ImageView Back, editPassword;
    private TextInputLayout fullname,phoneNumber,email;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_sercurity);
        initView();
        setupClickListener();
        getUserImformation();
    }
    @SuppressLint("WrongViewCast")
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

    private void getUserImformation()
    {
        String path = "User/"+user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(path);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                User user1 = dataSnapshot.getValue(User.class);
                fullname.getEditText().setText(user.getDisplayName());
                phoneNumber.getEditText().setText(user1.getPHONE());
                email.getEditText().setText(user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {

            }


        });

}
}
