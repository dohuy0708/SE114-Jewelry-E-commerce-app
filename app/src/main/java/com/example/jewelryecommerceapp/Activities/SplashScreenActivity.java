package com.example.jewelryecommerceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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
//                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                if (user != null) {
//                    if(Objects.equals(user.getEmail(), "huydq58422@gmail.com"))
//                    {
//                        Intent intent = new Intent(SplashScreenActivity.this, AdminHomeActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                    Intent intent = new Intent( SplashScreenActivity.this,HomeActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                else{
                  Intent intent = new Intent( SplashScreenActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }
        },2000);
    }
}
