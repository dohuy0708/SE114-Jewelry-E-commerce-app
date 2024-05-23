package com.example.jewelryecommerceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jewelryecommerceapp.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    Log.d("user ", "khac null");
                    if(user.getEmail().equals("huydq58422@gmail.com")||user.getEmail().equals("22520573@gm.uit.edu.vn"))
                    {
                        Log.d("admin","đăng nhập admin");
                        Intent intent = new Intent(SplashScreenActivity.this, AdminHomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Log.d("user", "Đăng nhập user");
                        Intent intent = new Intent( SplashScreenActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
                else{
                    Log.d("Chưa đăng nhập","Khách");

                Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }

            }
        },2000);
    }
}
