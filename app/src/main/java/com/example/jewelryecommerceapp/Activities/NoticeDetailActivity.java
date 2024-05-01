package com.example.jewelryecommerceapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.jewelryecommerceapp.Models.Notice;
import com.example.jewelryecommerceapp.R;

public class NoticeDetailActivity extends AppCompatActivity {

    TextView content,title;
    ImageView img_notice_detail;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notice_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        content=findViewById(R.id.content_notice_detail);
        title=findViewById(R.id.title_notice_detail);
        img_notice_detail=findViewById(R.id.img_notice_detail);

        Intent intent = getIntent();
        if (intent != null) {
            Notice notice = (Notice) intent.getSerializableExtra("notice");
            String imgUri = intent.getStringExtra("img");

            // Xử lý dữ liệu được gửi từ intent
            if (notice != null) {
                // Hiển thị thông tin của đối tượng Notice trên giao diện
                content.setText(notice.getContentNotice());
                title.setText(notice.getTitleNotice());
                if (!imgUri.isEmpty()) {
                    // Kiểm tra và đặt ảnh vào ImageView
                    notice.setImgNotice(Uri.parse(imgUri));

                }
            }
        }

    }
    public void setImageInView(ImageView imageView, Object imageObject) {
        if (imageObject != null) {
            if (imageObject instanceof Uri) {
                // Đặt ảnh từ Uri vào ImageView
                imageView.setImageURI((Uri) imageObject);
            } else if (imageObject instanceof Integer) {
                // Đặt ảnh từ resource ID vào ImageView
                imageView.setImageResource((Integer) imageObject);
            } else {
                // Xử lý trường hợp không mong đợi
                // Ví dụ: Log hoặc thông báo lỗi
                Toast.makeText(this, "Unsupported image type", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Xử lý khi imageObject là null
            Toast.makeText(this, "Image object is null", Toast.LENGTH_SHORT).show();
        }

    }
}