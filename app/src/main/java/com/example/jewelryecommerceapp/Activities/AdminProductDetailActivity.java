package com.example.jewelryecommerceapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Adapters.ImageAdapter;
import com.example.jewelryecommerceapp.Interfaces.SelectListener;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class AdminProductDetailActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 100;

    Product product;
    private TextView admin_productDetail_Title, admin_productDetail_Save, admin_productDetail_Cancel;
    private EditText admin_product_name, admin_product_ID, admin_product_description, admin_product_price, admin_product_size, admin_product_amount;
    private ImageView admin_product_add_image;
    RecyclerView rc_admin_product_image;
    ImageAdapter imageAdapter;
    ArrayList<String> imglist;
    ArrayList<String> categoryList,materialList;
    ArrayAdapter<String> categoryAdapter,materialAdapter;
    AutoCompleteTextView admin_productdetail_category,admin_productdetail_material;
    private Button admin_product_btnremove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_product_detail);
        reference();
        getCategory();
        getMaterial();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("Product");
        String title=bundle.getString("Title");
        String productID = bundle.getString("ProductID");
        admin_productDetail_Title.setText(title);
        //btn xóa sản phẩm
        admin_product_btnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //btn lưu
        admin_productDetail_Save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //btn hủy
        admin_productDetail_Cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rc_admin_product_image.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        imglist =  getProductImage(imglist);
        rc_admin_product_image.setHasFixedSize(true);


        imageAdapter=new ImageAdapter(imglist, new SelectListener() {
            @Override
            public void onImageItemClicked(int position) {
                if (position != RecyclerView.NO_POSITION) {
                    String imageUrl = imglist.get(position);
                    Intent intent = new Intent(AdminProductDetailActivity.this, ImageActivity.class);
                    intent.putExtra(ImageActivity.EXTRA_IMAGE_URL,imglist);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }

            }
        });
        rc_admin_product_image.setAdapter(imageAdapter);
        admin_product_add_image.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }
    private void reference() {
        admin_productDetail_Title=findViewById(R.id.admin_productDetail_Title);
        admin_productDetail_Save=findViewById(R.id.admin_productDetail_Save);
        admin_productDetail_Cancel=findViewById(R.id.admin_productDetail_Cancel);
        admin_product_name=findViewById(R.id.admin_product_name);
        admin_product_ID=findViewById(R.id.admin_product_ID);
        admin_product_description=findViewById(R.id.admin_product_description);
        admin_product_price=findViewById(R.id.admin_product_price);
        admin_product_size=findViewById(R.id.admin_product_size);
        admin_product_amount=findViewById(R.id.admin_product_amount);
        admin_product_btnremove=findViewById(R.id.admin_product_btnremove);
        admin_product_add_image=findViewById(R.id.admin_product_add_image);
        rc_admin_product_image=findViewById(R.id.rc_admin_prd_image);
        admin_productdetail_category=findViewById(R.id.admin_productdetail_category);
        admin_productdetail_material=findViewById(R.id.admin_productdetail_material);
    }
    ArrayList<String> getProductImage(ArrayList<String> list){
        list= new ArrayList<>();
      /*  list.add(R.drawable.demo);
        list.add(R.drawable.demo2);
        list.add(R.drawable.demo);
        list.add(R.drawable.demo2);
        list.add(R.drawable.demo);
        list.add(R.drawable.demo2);
        list.add(R.drawable.demo);
        list.add(R.drawable.demo2);*/
        return  list;
    }
    private void openGallery() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PICK_IMAGE);
        } else {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, PICK_IMAGE);
        }
    }
// mở ảnh từ local
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            Uri imageUri = data.getData();
            Context context=this;
        }
    }
    public long getImageIdFromUri(Uri uri, Context context) {
        long imageId = -1;
        Cursor cursor = null;
        try {
            // Chỉ lấy cột ID
            String[] projection = {MediaStore.Images.Media._ID};
            // Thực hiện truy vấn
            cursor = context.getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                // Lấy chỉ số cột của _ID
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
                // Lấy giá trị ID của ảnh
                imageId = cursor.getLong(columnIndex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return imageId;
    }
    private void getCategory() {
        categoryList = new ArrayList<>();
        categoryList.add("Nhẫn");
        categoryList.add("Nhẫn Đôi");
        categoryList.add("Dây chuyền");
        categoryList.add("Vòng");
        categoryList.add("Bông tai");
        categoryAdapter = new ArrayAdapter<String>(this, R.layout.item_admin_productdetail_category, categoryList);
        admin_productdetail_category.setAdapter(categoryAdapter);
    }
    private void getMaterial() {
        materialList = new ArrayList<>();
        materialList.add("Vàng");
        materialList.add("Bạc");
        materialList.add("Đá quý");
        materialAdapter = new ArrayAdapter<String>(this, R.layout.item_admin_productdetail_category, materialList);
        admin_productdetail_material.setAdapter(materialAdapter);
    }
}