package com.example.jewelryecommerceapp.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jewelryecommerceapp.Controllers.ValidateController;
import com.example.jewelryecommerceapp.Models.User;
import com.example.jewelryecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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


                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user.
                                    if (task.isSuccessful()) {
                                        saveUserInfo(name,email,sdt);
                                        showToastWithIcon(R.drawable.succecss_icon,"Đăng kí thành công");
                                    } else
                                    {
                                        showToastWithIcon(R.drawable.fail_icon,"Đăng kí thất bại!");
                                            }
                                }
                            });

                }
                else
                {
                    subText.setText(isValidate);
                }
            }
        });
        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
    private void saveUserInfo(String name, String email, String number)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String str = user.getUid();
        String path = "User/"+str;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(path);

        User user1 = new User(name,number,email,str);
        myRef.setValue(user1);
    }
    public void showToastWithIcon(int icon, String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        // Tùy chỉnh icon và văn bản trong toast
        ImageView imageView = layout.findViewById(R.id.toast_icon);
        imageView.setImageResource(icon); // Thay 'your_icon' bằng tên icon của bạn
        TextView textView = layout.findViewById(R.id.toast_text);
        textView.setText(message);

        // Tạo và hiển thị toast custom
        Toast toast = new Toast(SignUpActivity.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}