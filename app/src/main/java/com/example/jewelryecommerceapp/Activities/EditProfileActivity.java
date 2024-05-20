package com.example.jewelryecommerceapp.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
import com.bumptech.glide.Glide;
import com.example.jewelryecommerceapp.Models.User;
import com.example.jewelryecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.PrimitiveIterator;

public class EditProfileActivity extends AppCompatActivity {

    private Uri imageUri;
    private Bitmap avatarUser;
    private ImageView Back,avatar,changeAvatar, datepicker;
    private Button btnSave;
    private TextInputLayout fullname, dob, email, phoneNumber;
    ActivityResultLauncher<Intent> resultLauncher;
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private int gender;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale, radioButtonFemale, radioButtonOther;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initView();
        setupListener();
        getUserImformation();

    }
    private void initView() {
        btnSave = findViewById(R.id.button_save_editProfile);
        Back = findViewById(R.id.imageview_back);
        avatar = findViewById(R.id.imageview_avatar_editProfile);
        changeAvatar = findViewById(R.id.button_changeAvatar_editProfile);
        datepicker = findViewById(R.id.datepicker_editProfile);
        dob = findViewById(R.id.edittext_dateOfBirth_editProfile);
        fullname = findViewById(R.id.edittext_fullname_editProfile);
        email = findViewById(R.id.edittext_email_editProfile);
        phoneNumber = findViewById(R.id.edittext_phoneNumber_editProfile);
        radioGroupGender = findViewById(R.id.rdog_gender_editProfile);
        radioButtonMale = findViewById(R.id.rdo_male_editProfile);
        radioButtonFemale = findViewById(R.id.rdo_female_editProfile);
        radioButtonOther = findViewById(R.id.rdo_other_editProfile);
    }
    private void setupListener()
    {
        //Back button listener
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //Change avatar listener
        changeAvatar.setOnClickListener(v -> openGallery());
        //Save button listener
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Nhan thong tin duoc thay doi va luu xuong database
                onClickUpdateUserProfile();
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
        //Datepicker listener
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
            imageUri = data.getData();
            avatar.setImageURI(imageUri);
        }
    }

    private void getUserImformation()
    {
        String path = "User/"+user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(path);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                User user1 = dataSnapshot.getValue(User.class);
                fullname.getEditText().setText(user.getDisplayName());
                phoneNumber.getEditText().setText(user1.getPHONE());
                email.getEditText().setText(user.getEmail());
                dob.getEditText().setText(user1.getBIRTHDAY());
                setFieldGender(user1.getGENDER());
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {

            }


        });



    }
    private  void onClickUpdateUserProfile()
    {
        if(user == null)
        {
            Toast.makeText(getApplicationContext(), "Update fail", Toast.LENGTH_SHORT).show();
            return;}
        String strFullName = fullname.getEditText().getText().toString();
        String strEmail = email.getEditText().getText().toString();
        String strPhone = phoneNumber.getEditText().getText().toString();
        String strDateofBirth = dob.getEditText().getText().toString();
        saveUserInfo(strFullName, strEmail, strPhone, strDateofBirth);

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(strFullName)
                .setPhotoUri(imageUri)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Update success", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        finish();

    }
    private void saveUserInfo(String name, String email, String number, String dateofbirth)
    {
        String UID = user.getUid();
        String path = "User/"+UID;



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(path);

        User user1 = new User(name,number, email,UID,dateofbirth, getGender(),avatarUser);
        myRef.setValue(user1);
    }


    private int getGender() {
        int Selectedid = radioGroupGender.getCheckedRadioButtonId();
        gender = 0;

        if (Selectedid == R.id.rdo_male_editProfile)
        {
            gender = 1;
        } else if (Selectedid == R.id.rdo_female_editProfile)
        {
            gender = 2;
        } else if (Selectedid== R.id.rdo_other_editProfile)
        {
            gender = 3;
        }
        return  gender;

    }
    private void setFieldGender(int genderName)  {

            switch (genderName)   {
                case 1:
                    radioButtonMale.setChecked(true);
                    break;
                case 2:
                    radioButtonFemale.setChecked(true);
                    break;
                case 3:
                    radioButtonOther.setChecked(true);
                default:
                    return;
            }
        }
    public Bitmap getBitmapFromUri(Uri uri, ContentResolver contentResolver) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = contentResolver.openInputStream(uri);
            return BitmapFactory.decodeStream(inputStream);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//    private void RequestPermission() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            openGallery();
//
//        }
//        if(getApplicationContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {
//            String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
//            this.requestPermissions(permissions, REQUEST_READ_EXTERNAL_STORAGE);
//        }
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE) {
//            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    openGallery();
//            }
//        }
//    }
}


