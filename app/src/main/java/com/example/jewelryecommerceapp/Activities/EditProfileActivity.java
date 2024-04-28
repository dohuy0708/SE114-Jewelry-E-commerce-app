package com.example.jewelryecommerceapp.Activities;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.mbms.MbmsErrors;
import android.view.View;
import android.widget.*;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.jewelryecommerceapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.PrimitiveIterator;

public class EditProfileActivity extends AppCompatActivity {

    private Uri imageUri;
    private Bitmap avatarUser;
    private ImageView Back,avatar,changeAvatar, datepicker;
    private Button btnSave;
    private TextInputLayout dob;
    ActivityResultLauncher<Intent> resultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initView();
        setupBackButton();
        setupChangeAvatar();
        setupSaveButton();
        setupDatepicker();

    }
    private void initView() {
        btnSave = findViewById(R.id.button_save_editProfile);
        Back = findViewById(R.id.imageview_back);
        avatar = findViewById(R.id.imageview_avatar_editProfile);
        changeAvatar = findViewById(R.id.button_changeAvatar_editProfile);
        datepicker = findViewById(R.id.datepicker_editProfile);
        dob = findViewById(R.id.edittext_dateOfBirth_editProfile);
    }
    private void setupChangeAvatar()
    {
        changeAvatar.setOnClickListener(v -> openGallery());
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
    private  void setupDatepicker()
    {
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int date = calendar.get(Calendar.DATE),
                        month = calendar.get(Calendar.MONTH),
                        year = calendar.get(Calendar.YEAR);

                DatePickerDialog pickerDialog = new DatePickerDialog(EditProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat format = new SimpleDateFormat("dd / MM / yyyy");
                        calendar.set(i, i1, i2);
                        dob.getEditText().setText(format.format(calendar.getTime()));
                    }
                }, year, month, date);

                pickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

                pickerDialog.show();
            }
        });
    }
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int REQUEST_READ_EXTERNAL_STORAGE = 1;
    private void openGallery()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            avatar.setImageURI(selectedImageUri);
        }
    }
}


