package com.example.jewelryecommerceapp.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jewelryecommerceapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.PrimitiveIterator;

public class EditProfileActivity extends AppCompatActivity {

    private Uri imageUri;
    private Bitmap avatarUser;
    private ImageView Back,avatar,changeAvatar;
    private Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initView();
        setupBackButton();
        setupChangeAvatar();
        setupSaveButton();

    }
    private void initView() {
        btnSave = findViewById(R.id.button_save_editProfile);
        Back = findViewById(R.id.imageview_back);
        avatar = findViewById(R.id.imageview_avatar_editProfile);
        changeAvatar = findViewById(R.id.button_changeAvatar_editProfile);
    }
    private void setupChangeAvatar()
    {
    //setup nut doi avatar
    }
    private void setupBackButton()
    {
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setupSaveButton()
    {

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Nhan thong tin duoc thay doi va luu xuong database
                finish();
            }
        });
    }


}
