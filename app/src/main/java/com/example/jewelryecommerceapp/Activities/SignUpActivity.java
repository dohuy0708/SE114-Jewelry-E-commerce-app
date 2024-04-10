package com.example.jewelryecommerceapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jewelryecommerceapp.Controllers.ValidateController;
import com.example.jewelryecommerceapp.R;

public class SignUpActivity extends AppCompatActivity {
    EditText edtEmail, edtNumberPhone, edtName, edtPassword, edtRePassword;
    ImageView ImgBack;
    Button BtnSignUp;
    TextView subText;


    ListView listView;


    @Override
    @SuppressLint("MissingInflatedId")

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        InitUI();
        InitListener();
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

    private void InitListener() {
        BtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String sdt = edtNumberPhone.getText().toString();
                String pass = edtPassword.getText().toString();
                String isValidate = ValidateController.isValidate(name,sdt,email,pass);

                if (isValidate =="1")
                {

                    boolean isSignUp = ValidateController.onClickSignUp(email,pass);
                    if(isSignUp == true)
                    {
                        Toast.makeText(SignUpActivity.this,"Đăng ký thành công",Toast.LENGTH_LONG).show();
                        // chờ vài giây

                        finish();

                    }
                    else
                        Toast.makeText(SignUpActivity.this,"Đăng ký thất bại",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    subText.setText(isValidate);
                }
            }
        });
    }

    private void InitUI() {
        edtNumberPhone = findViewById(R.id.phoneNumber_input);
        edtEmail = findViewById(R.id.email_input);
        edtPassword= findViewById(R.id.password_input);
        edtName = findViewById(R.id.name_input);
        ImgBack = findViewById(R.id.imageBack);
        BtnSignUp = findViewById(R.id.buttonSignUp);
        subText = findViewById(R.id.SubText);


    }

}