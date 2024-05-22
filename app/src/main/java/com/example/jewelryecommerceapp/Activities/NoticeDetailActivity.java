package com.example.jewelryecommerceapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jewelryecommerceapp.R;

public class NoticeDetailActivity extends AppCompatActivity {

    TextView title_detail,content_detail,date_detail;
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
        title_detail=findViewById(R.id.title_notice_detail);
        content_detail=findViewById(R.id.content_notice_detail);
        date_detail=findViewById(R.id.date_notice_detail);
        Intent intent = getIntent();

        // Extract the data
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        String date=intent.getStringExtra("date");
        title_detail.setText(title);
        content_detail.setText(content);
        date_detail.setText("Ngày gửi: "+date);
    }
}