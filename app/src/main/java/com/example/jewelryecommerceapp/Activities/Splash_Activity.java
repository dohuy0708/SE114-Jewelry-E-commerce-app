package com.example.jewelryecommerceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Splash_Activity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( Splash_Activity.this,HomeActivity.class);
                startActivity(intent);
                finish();

            }
        },2000);

    }



}
