package com.example.jewelryecommerceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jewelryecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    Button btnlogin;
    TextView txtSignUp, txtForgotpass , SubText;
    EditText edtEmail, edtPass;
    ImageView back_login;
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

       back_login= findViewById(R.id.back_login);


    }

    private void InitListener() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String pass = edtPass.getText().toString().trim();

                if ( email.isEmpty() || pass.isEmpty())
                {
                    SubText.setText("Vui lòng nhập email/mật khẩu của bạn!");
                }
                else
                {

                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful() && email.equals("22520573@gm.uit.edu.vn")) {
                                        // Sign in success, update UI with the signed-in user's information
                                        showToastWithIcon(R.drawable.succecss_icon,"Đăng nhập tài khoản Admin thành công!");
                                         Intent intent = new Intent(LoginActivity.this, AdminHomeActivity.class);
                                        startActivity(intent);
                                        // FirebaseUser user = mAuth.getCurrentUser();
                                    }
                                    else if (task.isSuccessful()) {

                                        // Sign in success, update UI with the signed-in user's information
//                                        Toast.makeText(LoginActivity.this,"Đăng nhập thành công", Toast.LENGTH_LONG).show();
//                                        Intent intent = new Intent(LoginActivity.this, SplashScreenActivity.class);
                                        //Toast.makeText(LoginActivity.this,"Đăng nhập thành công", Toast.LENGTH_LONG).show();
                                        showToastWithIcon(R.drawable.succecss_icon,"Đăng nhập thành công!");
                                        Intent intent = new Intent(LoginActivity.this,SplashScreenActivity.class);
                                        startActivity(intent);
                                        // FirebaseUser user = mAuth.getCurrentUser();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        SubText.setText("Đăng nhập thất bại, vui lòng kiểm tra lại email/mật khẩu của bạn");

                                    }

                                }

                            });


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
        back_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);*/
                finish();
            }
        });
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
        Toast toast = new Toast(LoginActivity.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }


}

