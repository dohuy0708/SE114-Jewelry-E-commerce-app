package com.example.jewelryecommerceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jewelryecommerceapp.Controllers.UserControllers;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    Button btnlogin;
    EditText edtEmail, edtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitUI();
        InitListener();
        testFirebase();

    }

    private void testFirebase() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.push().setValue("New");
        ref.child("key").setValue("newnew");

    }

    private void InitUI() {
        btnlogin = findViewById(R.id.buttonLogin);
        edtEmail = findViewById(R.id.textEmail);
        edtPass= findViewById(R.id.textPassWord);
    }

    private void InitListener() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();
                boolean isSuccess = UserControllers.login(email,pass);
                 isLoginResutl(isSuccess);
            }
        });
    }


    public void isLoginResutl(boolean isSuccess) {
        if (isSuccess  ) {
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            // Chuyển sang màn hình chính hoặc màn hình tiếp theo
        } else {
            Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
        }

    }
}

