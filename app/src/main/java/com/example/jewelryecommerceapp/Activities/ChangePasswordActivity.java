package com.example.jewelryecommerceapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jewelryecommerceapp.R;
import com.example.jewelryecommerceapp.Controllers.ValidateController;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {
    private ImageView Back;
    private Button ChangePassword;
    private TextInputLayout OldPassword, NewPassword, ConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initView();
        setupListener();
    }
    private void initView()
    {
        Back = findViewById(R.id.imageview_back_changePassword);
        ChangePassword = findViewById(R.id.button_save_changePassword);
//        OldPassword = findViewById(R.id.edittext_currentPassword_changePassword);
        NewPassword = findViewById(R.id.edittext_newPassword_changePassword);
        ConfirmPassword = findViewById(R.id.edittext_confirmNewPassword_changePassword);
    }
    private void setupListener()
    {
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           finish();
            }
        });
        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickChangePassword();
            }
        });
    }
    private void onClickChangePassword()
    {
//        String oldPassword = OldPassword.getEditText().getText().toString();
        String newPassword = NewPassword.getEditText().getText().toString();
        String confirmPassword = ConfirmPassword.getEditText().getText().toString();
        if((newPassword == confirmPassword)&ValidateController.isPassword(newPassword))
        {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            user.updatePassword(newPassword)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ChangePasswordActivity.this, "Password changed", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                reAuthenticate();
                            }
                        }
                    });
        }
    }
    private void reAuthenticate()
    {
        //Yeu cau nguoi dung nhap email mat khau cu
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        AuthCredential credential = EmailAuthProvider
                .getCredential("user@example.com", "password1234");


        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        onClickChangePassword();
                    }
                });

    }


}
