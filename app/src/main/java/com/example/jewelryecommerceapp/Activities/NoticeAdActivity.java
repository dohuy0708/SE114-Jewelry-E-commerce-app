package com.example.jewelryecommerceapp.Activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Adapters.NoticeAdapter;
import com.example.jewelryecommerceapp.AlarmReceiver;
import com.example.jewelryecommerceapp.Interfaces.SelectNotice;
import com.example.jewelryecommerceapp.Models.Notice;
import com.example.jewelryecommerceapp.R;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class NoticeAdActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SUB_ACTIVITY = 1;
    ImageView img_back;
    RecyclerView rc_notice;
    NoticeAdapter noticeAdapter;
    Button add_notice;
    ArrayList<Notice> noticeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notice_ad);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        img_back=findViewById(R.id.img_back);
        rc_notice=findViewById(R.id.rc_notice);
        add_notice=findViewById(R.id.add_notice);
        noticeList= initNoticeList(noticeList);
        noticeAdapter = new NoticeAdapter(this, noticeList, new SelectNotice() {
            @Override
            public void onNoticeSelect(Notice notice) {
                Intent intent= new Intent(NoticeAdActivity.this, NoticeDetailActivity.class);
                notice.setImgNotice(notice.getImgNotice().toString());
                intent.putExtra("notice",notice);

                String img = notice.getImgNotice().toString();
                intent.putExtra("img",img);
                startActivity(intent);
            }
        },1);
        rc_notice.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rc_notice.setHasFixedSize(true);
        rc_notice.setAdapter(noticeAdapter);
        noticeAdapter.notifyDataSetChanged();

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add_notice.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NoticeAdActivity.this, NoticeAddActivity.class);
                startActivityForResult(intent,REQUEST_CODE_SUB_ACTIVITY);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SUB_ACTIVITY && resultCode == Activity.RESULT_OK) {
            if (data != null && data.hasExtra("newnotice")) {
                Notice newNotice = (Notice) data.getSerializableExtra("newnotice"); // Lấy đối tượng Notice từ Intent
                String receivedString = data.getStringExtra("uri");
                if (newNotice != null) {
                    Toast.makeText(this,receivedString,Toast.LENGTH_SHORT).show();
                    if(!receivedString.equals(""))
                      newNotice.setImgNotice(Uri.parse(receivedString));
                    noticeList.add(0,newNotice);
                    noticeAdapter.notifyDataSetChanged();
                }
                else   Toast.makeText(this,"không láy được",Toast.LENGTH_SHORT).show();

            }
        }
    }
     ArrayList<Notice> initNoticeList(ArrayList<Notice> list){
        list= new ArrayList<>();

        return list;
    }
}