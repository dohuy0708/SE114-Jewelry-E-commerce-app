package com.example.jewelryecommerceapp.Activities;


import android.annotation.SuppressLint;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.jewelryecommerceapp.Controllers.UserControllers;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity{

    EditText edtEmail, edtNumberPhone, edtName, edtPassword, edtRePassword;
    ListView listView;


    @Override
    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        InitUI();
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ListView listView = findViewById(R.id.listView);

        // Tạo một danh sách dữ liệu
        List<String> data = new ArrayList<>();
        data.add(String.valueOf(R.id.container2));
        data.add(String.valueOf(R.id.container3));
        data.add(String.valueOf(R.id.container4));

        // Tạo ArrayAdapter và gán nó cho ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
        // InitListener();
        // testFirebase();

    }
    private void InitUI() {
        edtNumberPhone = findViewById(R.id.phoneNumber_input);
        edtEmail = findViewById(R.id.email_input);
        edtPassword= findViewById(R.id.rePassword_input);
        edtName = findViewById(R.id.name_input);
        edtRePassword = findViewById(R.id.rePassword_input);
    }


}
