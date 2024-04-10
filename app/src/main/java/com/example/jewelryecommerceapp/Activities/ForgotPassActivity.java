package com.example.jewelryecommerceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jewelryecommerceapp.Controllers.ValidateController;
import com.example.jewelryecommerceapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPassActivity extends AppCompatActivity {

        EditText edtEmail;
        Button btnSendEmail;
        ImageView Back ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
       edtEmail = findViewById(R.id.Edt_resetEmail);
       btnSendEmail = findViewById(R.id.BtnSendEmail);
       Back = findViewById(R.id.imageBack);

       btnSendEmail.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email = edtEmail.getText().toString().trim();

               Boolean isSend = ValidateController.onClickForgotPass(email);
               if( isSend)
               {
                   Toast.makeText(ForgotPassActivity.this,"Email đã được gửi, vui lòng kiểm tra hộp thư của bạn!",Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(ForgotPassActivity.this,"Thật bại , vui lòng kiểm lại email của bạn!",Toast.LENGTH_SHORT).show();
               }


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