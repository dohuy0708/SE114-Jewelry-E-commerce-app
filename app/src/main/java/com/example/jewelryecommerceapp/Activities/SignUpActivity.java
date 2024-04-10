package com.example.jewelryecommerceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jewelryecommerceapp.R;

public class SignUpActivity extends AppCompatActivity {
    EditText edtEmail, edtNumberPhone, edtName, edtPassword, edtRePassword;
    ListView listView;


    @Override
    @SuppressLint("MissingInflatedId")

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        InitUI();
       // @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ListView listView = findViewById(R.id.listView);

        // Tạo một danh sách dữ liệu
      /*  List<String> data = new ArrayList<>();
        data.add(String.valueOf(R.id.container2));
        data.add(String.valueOf(R.id.container3));
        data.add(String.valueOf(R.id.container4));
*/
        // Tạo ArrayAdapter và gán nó cho ListView
       /* ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);*/
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