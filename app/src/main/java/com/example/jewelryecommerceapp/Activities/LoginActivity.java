package com.example.jewelryecommerceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jewelryecommerceapp.Controllers.UserControllers;
import com.example.jewelryecommerceapp.Controllers.ValidateController;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    Button btnlogin;
    TextView txtSignUp, txtForgotpass , SubText;
    EditText edtEmail, edtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitUI();
        InitListener();


    }



    private void InitUI() {
        btnlogin = findViewById(R.id.buttonLogin);
        edtEmail = findViewById(R.id.textEmail);
        edtPass= findViewById(R.id.textPassWord);
        txtSignUp = findViewById(R.id.txtSignUp);
        txtForgotpass = findViewById(R.id.txtForgotPass);
        SubText = findViewById(R.id.Subtext_login);


    }

    private void InitListener() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();

                {
                    Boolean isSuccess = ValidateController.onClickLogIn(email,pass);
                    if (isSuccess)
                    {
                        Toast.makeText(LoginActivity.this,"Đăng nhập thành công", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        SubText.setText("Đăng nhập thất bại, vui lòng kiểm tra lại email/mật khẩu của bạn");
                    }

                }

            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chuyển tới đăng kí


                Intent intent =new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        txtForgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPassActivity.class);
                startActivity(intent);


            }
        });
    }




}

