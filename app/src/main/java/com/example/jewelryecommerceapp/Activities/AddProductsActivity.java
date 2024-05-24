package com.example.jewelryecommerceapp.Activities;



import android.content.Context;
import android.database.Cursor;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.provider.MediaStore;
import android.net.Uri;
import android.widget.Toast;
import android.widget.RelativeLayout;
import android.graphics.Bitmap;

import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.text.TextUtils;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Calendar;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddProductsActivity extends AppCompatActivity {


    Context context;
    EditText nameIn;
    Spinner typeIn;
    EditText maspIn;
    EditText desIn;
    EditText priceIn;
    EditText numsize11In;
    EditText numsize12In;
    EditText numsize13In;
    EditText numsize14In;
    EditText nccIn;
    EditText weightIn;
    EditText chatlieuIn;
    EditText dinhkemIn;
    ImageView image_1;
    TextView tv_1;
    ImageView image_2;
    TextView tv_2;
    ImageView image_3;
    TextView tv_3;
    ImageView _back;
    Button submit;

    Uri photoUri1;
    String im1URL;
    String im2URL;
    String im3URL;
    Uri photoUri2;
    Uri photoUri3;

    FirebaseStorage storage;
    StorageReference storageReference;


//    private final ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult o) {
//                    if (o.getResultCode()== Activity.RESULT_OK)
//                    {
//                        Intent data=o.getData();
//                        photoUri1=data.getData();
//                        image_1.setImageURI(photoUri1);
//
//                    }
//                    else {
//                        Toast.makeText(AddProductsActivity.this, "Không có hình chọn", Toast.LENGTH_SHORT);
//                    }
//                }
//            }
//            result -> {
//                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
//                    Uri imageUri = result.getData().getData();
//                    image_1.setImageURI(imageUri);
//                    tv_1.setVisibility(RelativeLayout.GONE);
//                    uploadImagetoFirebase(imageUri);
//                } else {
//                    Toast.makeText(this, "Hành động đã hủy", Toast.LENGTH_SHORT).show();
//                }
//            }
//    );
//
//
//
//    private final ActivityResultLauncher<Intent> takePhotoLauncher = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
//                    Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
//                    image_1.setImageBitmap(photo);
//                    tv_1.setVisibility(RelativeLayout.GONE);
//                    uploadImage1ToFirebase(photo);
//                } else {
//                    Toast.makeText(this, "Hành động đã hủy", Toast.LENGTH_SHORT).show();
//                }
//            }
//    );
//
//
//
//
//    private final ActivityResultLauncher<Intent> pickImageLauncher2 = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
//                    Uri imageUri = result.getData().getData();
//                    image_2.setImageURI(imageUri);
//                    tv_2.setVisibility(RelativeLayout.GONE);
//                    uploadImagetoFirebase(imageUri);
//                } else {
//                    Toast.makeText(this, "Hành động đã hủy", Toast.LENGTH_SHORT).show();
//                }
//            }
//    );
//
//    private final ActivityResultLauncher<Intent> takePhotoLauncher2 = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
//                    Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
//                    image_2.setImageBitmap(photo);
//                    tv_2.setVisibility(RelativeLayout.GONE);
//                    uploadImage2ToFirebase(photo);
//                } else {
//                    Toast.makeText(this, "Hành động đã hủy", Toast.LENGTH_SHORT).show();
//                }
//            }
//    );
//
//
//    private final ActivityResultLauncher<Intent> pickImageLauncher3 = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
//                    Uri imageUri = result.getData().getData();
//                    image_3.setImageURI(imageUri);
//                    tv_3.setVisibility(RelativeLayout.GONE);
//                    uploadImagetoFirebase(imageUri);
//                } else {
//                    Toast.makeText(this, "Hành động đã hủy", Toast.LENGTH_SHORT).show();
//                }
//            }
//    );
//
//    private final ActivityResultLauncher<Intent> takePhotoLauncher3 = registerForActivityResult(
//            new ActivityResultContracts.StartActivityForResult(),
//            result -> {
//                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
//                    Bitmap photo = (Bitmap) result.getData().getExtras().get("data");
//                    image_3.setImageBitmap(photo);
//                    tv_3.setVisibility(RelativeLayout.GONE);
//                    uploadImage3ToFirebase(photo);
//                } else {
//                    Toast.makeText(this, "Hành động đã hủy", Toast.LENGTH_SHORT).show();
//                }
//            }
//    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_products);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nameIn = findViewById(R.id.name_input);
        typeIn = findViewById(R.id.type_input);
        maspIn=findViewById(R.id.masp_input);
        desIn = findViewById(R.id.des_input);
        numsize11In=findViewById(R.id.size11_input);
        numsize12In=findViewById(R.id.size12_input);
        numsize13In=findViewById(R.id.size13_input);
        numsize14In=findViewById(R.id.size14_input);
        weightIn=findViewById(R.id.weight_input);
        priceIn = findViewById(R.id.price_input);
        chatlieuIn=findViewById(R.id.chatlieu_input);
        dinhkemIn=findViewById(R.id.dinhkem_input);
        nccIn = findViewById(R.id.ncc_input);
        image_1 = findViewById(R.id.image1);
        image_2 = findViewById(R.id.image2);
        image_3 = findViewById(R.id.image3);
        submit = findViewById(R.id.btn_submit);
        _back = findViewById(R.id.addProback);
        tv_1 = findViewById(R.id.textView01);
        tv_2 = findViewById(R.id.textView02);
        tv_3 = findViewById(R.id.textView03);
        maspIn=findViewById(R.id.masp_input);

ActivityResultLauncher<Intent> pickImageLauncher = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if(o.getResultCode()==RESULT_OK)
                {
                    Intent data=o.getData();
                    photoUri1=data.getData();
                    image_1.setImageURI(photoUri1);
                }
                else
                {
                    Toast.makeText(AddProductsActivity.this,"Không có hình chọn", Toast.LENGTH_SHORT).show();
                }
            }
        }
);
        ActivityResultLauncher<Intent> pickImageLauncher2 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if(o.getResultCode()==RESULT_OK)
                        {
                            Intent data=o.getData();
                            photoUri2=data.getData();
                            image_2.setImageURI(photoUri2);
                        }
                        else
                        {
                            Toast.makeText(AddProductsActivity.this,"Không có hình chọn", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        ActivityResultLauncher<Intent> pickImageLauncher3 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if(o.getResultCode()==RESULT_OK)
                        {
                            Intent data=o.getData();
                            photoUri3=data.getData();
                            image_3.setImageURI(photoUri3);
                        }
                        else
                        {
                            Toast.makeText(AddProductsActivity.this,"Không có hình chọn", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );





        image_1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent photopicker=new Intent(Intent.ACTION_PICK);
                                photopicker.setType("image/*");
                                pickImageLauncher.launch(photopicker);
                                tv_1.setVisibility(RelativeLayout.GONE);
                            }
                        });
        image_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photopicker=new Intent(Intent.ACTION_PICK);
                photopicker.setType("image/*");
                pickImageLauncher2.launch(photopicker);
                tv_2.setVisibility(RelativeLayout.GONE);
            }
        });
        image_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photopicker=new Intent(Intent.ACTION_PICK);
                photopicker.setType("image/*");
                pickImageLauncher3.launch(photopicker);
                tv_3.setVisibility(RelativeLayout.GONE);
            }
        });
        _back.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        });


                        ArrayAdapter<CharSequence> adt = ArrayAdapter.createFromResource(this, R.array.ProType, android.R.layout.simple_spinner_item);
                        adt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        typeIn.setAdapter(adt);


        InputFilter inputFilter = new InputFilter() {
                            @Override
                            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                                if (source.length() > 0 && !Character.isDigit(source.charAt(0))) {
                                    return "";
                                }
                                if (source.toString().equals("0") && dstart == 0) {
                                    return "";
                                }
                                return null;
                            }
                        };
        numsize11In.setFilters(new InputFilter[]{inputFilter});
        numsize11In.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                try {
                                    // Lấy giá trị từ EditText và chuyển thành số nguyên
                                    int value = Integer.parseInt(s.toString());
                                    if (value < 0) {
                                        numsize11In.setError("Vui lòng nhập số lớn hơn 0");
                                    }
                                } catch (NumberFormatException e) {
                                    // Nếu không thể chuyển đổi, có thể là do chuỗi rỗng
                                    numsize11In.setError("Vui lòng nhập số hợp lệ");
                                }
                            }
                        });
        numsize12In.setFilters(new InputFilter[]{inputFilter});
        numsize12In.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                try {
                                    // Lấy giá trị từ EditText và chuyển thành số nguyên
                                    int value = Integer.parseInt(s.toString());
                                    if (value < 0) {
                                        numsize12In.setError("Vui lòng nhập số lớn hơn 0");
                                    }
                                } catch (NumberFormatException e) {
                                    // Nếu không thể chuyển đổi, có thể là do chuỗi rỗng
                                    numsize12In.setError("Vui lòng nhập số hợp lệ");
                                }
                            }
                        });
        numsize13In.setFilters(new InputFilter[]{inputFilter});
        numsize13In.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                try {
                                    // Lấy giá trị từ EditText và chuyển thành số nguyên
                                    int value = Integer.parseInt(s.toString());
                                    if (value < 0) {
                                        numsize13In.setError("Vui lòng nhập số lớn hơn 0");
                                    }
                                } catch (NumberFormatException e) {
                                    // Nếu không thể chuyển đổi, có thể là do chuỗi rỗng
                                    numsize13In.setError("Vui lòng nhập số hợp lệ");
                                }
                            }
                        });
        priceIn.setFilters(new InputFilter[]{inputFilter});
        priceIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    // Lấy giá trị từ EditText và chuyển thành số nguyên
                    int value = Integer.parseInt(s.toString());
                    if (value < 0) {
                        priceIn.setError("Vui lòng nhập số lớn hơn 0");
                    }
                } catch (NumberFormatException e) {
                    // Nếu không thể chuyển đổi, có thể là do chuỗi rỗng
                    priceIn.setError("Vui lòng nhập số hợp lệ");
                }

            }
        });

        weightIn.setFilters(new InputFilter[]{inputFilter});
        weightIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    // Lấy giá trị từ EditText và chuyển thành số nguyên
                    int value = Integer.parseInt(s.toString());
                    if (value < 0) {
                        weightIn.setError("Vui lòng nhập số lớn hơn 0");
                    }
                } catch (NumberFormatException e) {
                    // Nếu không thể chuyển đổi, có thể là do chuỗi rỗng
                    weightIn.setError("Vui lòng nhập số hợp lệ");
                }

            }
        });

        numsize14In.setFilters(new InputFilter[]{inputFilter});
        numsize14In.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {

                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                try {
                                    // Lấy giá trị từ EditText và chuyển thành số nguyên
                                    int value = Integer.parseInt(s.toString());
                                    if (value < 0) {
                                        numsize14In.setError("Vui lòng nhập số lớn hơn 0");
                                    }
                                } catch (NumberFormatException e) {
                                    // Nếu không thể chuyển đổi, có thể là do chuỗi rỗng
                                    numsize14In.setError("Vui lòng nhập số hợp lệ");
                                }
                            }
                        });


//        image_1.setOnClickListener(v -> {
//            CharSequence[] options = {"Chọn từ Gallery", "Chụp ảnh"};
//            AlertDialog.Builder builder = new AlertDialog.Builder(AddProductsActivity.this);
//            builder.setTitle("Chọn ảnh");
//            builder.setItems(options, (dialog, which) -> {
//                if (which == 0) {
//                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    pickImageLauncher.launch(intent);
//                } else if (which == 1) {
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    if (intent.resolveActivity(getPackageManager()) != null) {
//                        // Tạo tệp để lưu ảnh
//                        File photoFile = null;
//                        try {
//                            photoFile = createImageFile();
//                        } catch (IOException e) {
//                            Toast.makeText(this, "Lỗi khi tạo tệp ảnh", Toast.LENGTH_SHORT).show();
//                        }
//                        if (photoFile != null) {
//                            photoUri1 = FileProvider.getUriForFile(this, "com.example.jewelryecommerceapp.fileprovider", photoFile);
//                            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri1);
//                            takePhotoLauncher.launch(intent);
//                        }
//                    }
//                }
//            });
//            builder.show();
//        });
//
//        image_2.setOnClickListener(v -> {
//            CharSequence[] options = {"Chọn từ Gallery", "Chụp ảnh"};
//            AlertDialog.Builder builder = new AlertDialog.Builder(AddProductsActivity.this);
//            builder.setTitle("Chọn ảnh");
//            builder.setItems(options, (dialog, which) -> {
//                if (which == 0) {
//                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    pickImageLauncher2.launch(intent);
//                } else if (which == 1) {
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    if (intent.resolveActivity(getPackageManager()) != null) {
//                        // Tạo tệp để lưu ảnh
//                        File photoFile = null;
//                        try {
//                            photoFile = createImageFile();
//                        } catch (IOException e) {
//                            Toast.makeText(this, "Lỗi khi tạo tệp ảnh", Toast.LENGTH_SHORT).show();
//                        }
//                        if (photoFile != null) {
//                            photoUri2 = FileProvider.getUriForFile(this, "com.example.jewelryecommerceapp.fileprovider", photoFile);
//                            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri2);
//                            takePhotoLauncher2.launch(intent);
//                        }
//                    }
//                }
//            });
//            builder.show();
//        });
//
//        image_3.setOnClickListener(v -> {
//            CharSequence[] options = {"Chọn từ Gallery", "Chụp ảnh"};
//            AlertDialog.Builder builder = new AlertDialog.Builder(AddProductsActivity.this);
//            builder.setTitle("Chọn ảnh");
//            builder.setItems(options, (dialog, which) -> {
//                if (which == 0) {
//                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    pickImageLauncher3.launch(intent);
//                } else if (which == 1) {
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    if (intent.resolveActivity(getPackageManager()) != null) {
//                        // Tạo tệp để lưu ảnh
//                        File photoFile = null;
//                        try {
//                            photoFile = createImageFile();
//                        } catch (IOException e) {
//                            Toast.makeText(this, "Lỗi khi tạo tệp ảnh", Toast.LENGTH_SHORT).show();
//                        }
//                        if (photoFile != null) {
//                            photoUri3 = FileProvider.getUriForFile(this, "com.example.jewelryecommerceapp.fileprovider", photoFile);
//                            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri3);
//                            takePhotoLauncher3.launch(intent);
//                        }
//                    }
//                }
//            });
//            builder.show();
//        });


        submit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (true) {
                                    //Log.d("abc","xyz");

                                    StorageReference storageReference1=FirebaseStorage.getInstance().getReference().child(typeIn.getSelectedItem().toString()).child(photoUri1.getLastPathSegment());
                                    AlertDialog.Builder builder=new AlertDialog.Builder(AddProductsActivity.this);
                                    builder.setCancelable(false);
                                    builder.setView(R.layout.activity_add_products);
                                    AlertDialog dialog=builder.create();
                                    dialog.show();
                                    storageReference1.putFile(photoUri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            Task<Uri> uriTask= taskSnapshot.getStorage().getDownloadUrl();
                                            while (!uriTask.isComplete());
                                            Uri urlImage1=uriTask.getResult();
                                            im1URL= getRealPathFromURI(urlImage1);
                                            dialog.dismiss();
                                           // Log.d("laay anh", " anh");
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            dialog.dismiss();
                                        }
                                    });

                                    StorageReference storageReference2=FirebaseStorage.getInstance().getReference().child(typeIn.getSelectedItem().toString()).child(photoUri2.getLastPathSegment());
                                    AlertDialog.Builder builder2=new AlertDialog.Builder(AddProductsActivity.this);
                                    builder.setCancelable(false);
                                    builder.setView(R.layout.activity_add_products);
                                    AlertDialog dialog2=builder2.create();
                                    dialog2.show();
                                    storageReference2.putFile(photoUri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            Task<Uri> uriTask= taskSnapshot.getStorage().getDownloadUrl();
                                            while (!uriTask.isComplete());
                                            Uri urlImage2=uriTask.getResult();
                                            im2URL=urlImage2.toString();
                                            dialog2.dismiss();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            dialog2.dismiss();
                                        }
                                    });

                                    StorageReference storageReference3=FirebaseStorage.getInstance().getReference().child(typeIn.getSelectedItem().toString()).child(photoUri3.getLastPathSegment());
                                    AlertDialog.Builder builder3=new AlertDialog.Builder(AddProductsActivity.this);
                                    builder.setCancelable(false);
                                    builder.setView(R.layout.activity_add_products);
                                    AlertDialog dialog3=builder3.create();
                                    dialog3.show();
                                    storageReference3.putFile(photoUri3).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            Task<Uri> uriTask= taskSnapshot.getStorage().getDownloadUrl();
                                            while (!uriTask.isComplete());
                                            Uri urlImage3=uriTask.getResult();
                                            im3URL=urlImage3.toString();
                                            dialog3.dismiss();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            dialog3.dismiss();
                                        }
                                    });





                                    Calendar calendar = Calendar.getInstance();
                                    int year = calendar.get(Calendar.YEAR);
                                    int month = calendar.get(Calendar.MONTH) + 1; // Tháng bắt đầu từ 0
                                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                                    String currentDate = year + "-" + month + "-" + day;
                                    String s1 = nameIn.getText().toString();
                                    String s2 = typeIn.getSelectedItem().toString();
                                    String s3 = desIn.getText().toString();
                                    String s4 = priceIn.getText().toString();
                                    String s5 = weightIn.getText().toString();
                                    String s6 = chatlieuIn.getText().toString();
                                    String s7 = nccIn.getText().toString();
                                    String s8 = dinhkemIn.getText().toString();
                                    String s11 = numsize11In.getText().toString();
                                    String s12 = numsize12In.getText().toString();
                                    String s13 = numsize13In.getText().toString();
                                    String s14 = numsize14In.getText().toString();
                                    String s15 = maspIn.getText().toString();
                                    Map<String, Integer> sizeMap = new HashMap<>();
                                    sizeMap.put("11", Integer.valueOf(s11));
                                    sizeMap.put("12", Integer.valueOf(s12));
                                    sizeMap.put("13", Integer.valueOf(s13));
                                    sizeMap.put("14", Integer.valueOf(s14));
                                    String im1 = photoUri1.toString();
                                    String im2 = photoUri2.toString();
                                    String im3 = photoUri3.toString();

                                    ArrayList<String> listImg = new ArrayList<>();
                                    listImg.add(im1);
                                    listImg.add(im2);
                                    listImg.add(im3);


                                    Product p = new Product(s15, s2, s1, s6, listImg, sizeMap, s8, Double.valueOf(s5), Integer.valueOf(s4), s3, s7);
                                    Log.d("dau pro", " anh" + p.getProductName());
//                    Map<String, Object> data = new HashMap<>();
//                    db.collection("").add(data).addOnSuccessListener(documentReference -> {
//                        Toast.makeText(AddProductsActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
//                    }).addOnFailureListener(e -> {
//                        Toast.makeText(AddProductsActivity.this, "Lỗi: "+e.getMessage(), Toast.LENGTH_SHORT).show();
//                    });
//                    db.collection("Product").add(p).addOnSuccessListener(documentReference -> {
//                        Toast.makeText(AddProductsActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
//                    }).addOnFailureListener(e -> {
//                        Toast.makeText(AddProductsActivity.this, "Lỗi: "+e.getMessage(), Toast.LENGTH_SHORT).show();
//                    });

//                                    FirebaseDatabase.getInstance().getReference("Product").child(typeIn.getSelectedItem().toString()).setValue(p)
//                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<Void> task) {
//                                                    if(task.isSuccessful())
//                                                    {
//                                                        Toast.makeText(AddProductsActivity.this, "Thêm sản phẩm thành công", Toast.LENGTH_SHORT).show();
//                                                        finish();
//                                                    }
//                                                }
//                                            }).addOnFailureListener(new OnFailureListener() {
//                                                @Override
//                                                public void onFailure(@NonNull Exception e) {
//                                                    Toast.makeText(AddProductsActivity.this, e.getMessage().toString(),Toast.LENGTH_SHORT).show();
//                                                }
//                                            });
                                    FirebaseDatabase database=FirebaseDatabase.getInstance();
                                    DatabaseReference databaseReference=database.getReference("Product").child(typeIn.getSelectedItem().toString());
                                    databaseReference.push().setValue(p, new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                            if (error != null) {
                                                // Đã xảy ra lỗi khi đẩy dữ liệu lên Firebase
                                                Toast.makeText(AddProductsActivity.this,error.getMessage(), Toast.LENGTH_LONG).show();
                                            } else {
                                                // Đẩy dữ liệu lên Firebase thành công
                                                Toast.makeText(AddProductsActivity.this,"Finish", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }
                                else
                                {

;                                }
                            }

                        });

                    }


    private boolean vadlidNumber() {
                        String s1 = numsize11In.getText().toString();
                        String s2 = numsize12In.getText().toString();
                        String s3 = numsize13In.getText().toString();
                        String s4 = numsize14In.getText().toString();
                        if (Integer.valueOf(s1) == 0 && Integer.valueOf(s2) == 0 && Integer.valueOf(s3) == 0 && Integer.valueOf(s4) == 0) {
                            return false;
                        } else {
                            return false;
                        }
                    }

                    private boolean validInputs() {
                        boolean valid = true;
                        if (TextUtils.isEmpty(nameIn.getText().toString())) {
                            nameIn.setError("Vui lòng nhập tên sản phẩm");
                            valid = false;
                        }

                        if (TextUtils.isEmpty(desIn.getText().toString())) {
                            desIn.setError("Vui lòng nhập mô tả sản phẩm");
                            valid = false;
                        }

                        if (TextUtils.isEmpty(priceIn.getText().toString())) {
                            priceIn.setError("Vui lòng nhập giá sản phẩm");
                            valid = false;
                        }

                        if (TextUtils.isEmpty(nccIn.getText().toString())) {
                            nccIn.setError("Vui lòng nhập tên nhà cung cấp");
                            valid = false;
                        }
                        if (TextUtils.isEmpty(maspIn.getText().toString())) {
                            maspIn.setError("Vui lòng nhập mã sản phẩm");
                            valid = false;
                        }
                        if (TextUtils.isEmpty(weightIn.getText().toString())) {
                            weightIn.setError("Vui lòng nhập khối lượng sản phẩm");
                            valid = false;
                        }
                        if (TextUtils.isEmpty(chatlieuIn.getText().toString())) {
                            chatlieuIn.setError("Vui lòng nhập chất liệu sản phẩm");
                            valid = false;
                        }
                        if (TextUtils.isEmpty(dinhkemIn.getText().toString())) {
                            dinhkemIn.setError("Vui lòng nhập đính kèm");
                            valid = false;
                        }
                        // Kiểm tra spinner1

                        return valid;
                    }




//    private void performAction() {
//        Toast.makeText(this, "Tất cả thông tin đã được nhập!", Toast.LENGTH_SHORT).show();
//    }
//    private void uploadImagetoFirebase(Uri imageUri) {
//        StorageReference imagesRef = storage.getReference();//.child(typeIn.getSelectedItem().toString() + System.currentTimeMillis());
//
//        imagesRef.putFile(imageUri)
//                .addOnSuccessListener(taskSnapshot -> imagesRef.getDownloadUrl().addOnSuccessListener(uri -> {
//                    String downloadUrl = uri.toString();
//                    // Sử dụng URL này cho các mục đích khác
//                    Toast.makeText(AddProductsActivity.this, "Upload thành công! URL: " + downloadUrl, Toast.LENGTH_SHORT).show();
//                }))
//                .addOnFailureListener(e -> Toast.makeText(AddProductsActivity.this, "Upload thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//    }
//    private void uploadImage1ToFirebase(Bitmap photo) {
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReference();
//
//        storageRef.putFile(photoUri1)
//                .addOnSuccessListener(taskSnapshot -> storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
//                    String downloadUrl = uri.toString();
//                    // Sử dụng URL này cho các mục đích khác
//                    Toast.makeText(AddProductsActivity.this, "Upload thành công! URL: " + downloadUrl, Toast.LENGTH_SHORT).show();
//                }))
//                .addOnFailureListener(e -> Toast.makeText(AddProductsActivity.this, "Upload thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//    }
//    private void uploadImage2ToFirebase(Bitmap photo) {
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReference();//.child( typeIn.getSelectedItem().toString()+ System.currentTimeMillis() + ".jpg");
//
//        storageRef.putFile(photoUri2)
//                .addOnSuccessListener(taskSnapshot -> storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
//                    String downloadUrl = uri.toString();
//                    // Sử dụng URL này cho các mục đích khác
//                    Toast.makeText(AddProductsActivity.this, "Upload thành công! URL: " + downloadUrl, Toast.LENGTH_SHORT).show();
//                }))
//                .addOnFailureListener(e -> Toast.makeText(AddProductsActivity.this, "Upload thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//    }
//    private void uploadImage3ToFirebase(Bitmap photo) {
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReference();//.child(typeIn.getSelectedItem().toString()+ System.currentTimeMillis() + ".jpg");
//
//        storageRef.putFile(photoUri3)
//                .addOnSuccessListener(taskSnapshot -> storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
//                    String downloadUrl = uri.toString();
//                    // Sử dụng URL này cho các mục đích khác
//                    Toast.makeText(AddProductsActivity.this, "Upload thành công! URL: " + downloadUrl, Toast.LENGTH_SHORT).show();
//                }))
//                .addOnFailureListener(e -> Toast.makeText(AddProductsActivity.this, "Upload thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show());
//    }
//
//
//    private File createImageFile() throws IOException {
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String imageFileName = "JPEG_" + timeStamp + "_";
//        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );
//
//        return image;
//    }
public String getRealPathFromURI(Uri contentUri) {
    String[] proj = { MediaStore.Images.Media.DATA };
    Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
    if (cursor == null) return null;
    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    cursor.moveToFirst();
    String path = cursor.getString(column_index);
    cursor.close();
    return path;
}

}