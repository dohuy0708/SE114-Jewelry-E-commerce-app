package com.example.jewelryecommerceapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Adapters.NoticeAdapter;
import com.example.jewelryecommerceapp.Interfaces.SelectListener;
import com.example.jewelryecommerceapp.Interfaces.SelectNotice;
import com.example.jewelryecommerceapp.Models.Notice;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity {

    ImageView img_back;
    RecyclerView rc_notice;
    NoticeAdapter noticeAdapter;
    ArrayList<Notice> noticeList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notice);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rc_notice=findViewById(R.id.rc_notice_ad);
        noticeList= new ArrayList<>();
        initNotice(noticeList);
        noticeAdapter = new NoticeAdapter(this, noticeList, new SelectNotice() {
            @Override
            public void onNoticeSelect(Notice notice) {
                Intent intent= new Intent(NoticeActivity.this, NoticeDetailActivity.class);
                intent.putExtra("notice",notice);
                startActivity(intent);
            }
        });
                rc_notice.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rc_notice.setHasFixedSize(true);
        rc_notice.setAdapter(noticeAdapter);

        img_back=findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void initNotice(ArrayList<Notice> mylist){

        mylist.add(new Notice(R.drawable.ic_chat,"Thông báo giảm giá các sản phẩm nữ ngày 8/3","quá mệt mỏi"));
        mylist.add(new Notice(R.drawable.ic_chat,"Thông báo giảm giá các sản phẩm nữ ngày 8/3","kkkkkkkkk"));
        mylist.add(new Notice(R.drawable.ic_chat,"Thông báo giảm giá các sản phẩm nữ ngày 8/3","giảm giá mua đi"));

    }
}