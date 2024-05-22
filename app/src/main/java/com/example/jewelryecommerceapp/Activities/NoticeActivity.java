package com.example.jewelryecommerceapp.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Adapters.NoticeAdapter;
import com.example.jewelryecommerceapp.Interfaces.SelectNotice;
import com.example.jewelryecommerceapp.Models.Notice;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        getNoticeFromDatabase();
        img_back=findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    void getNoticeFromDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Notice")  ;
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                noticeList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Notice notice = dataSnapshot.getValue(Notice.class);

                    noticeList.add(notice);
                }
                noticeAdapter = new NoticeAdapter(NoticeActivity.this, noticeList, new SelectNotice() {
                    @Override
                    public void onNoticeSelect(Notice notice) {
                        Intent intent = new Intent(NoticeActivity.this, NoticeDetailActivity.class);
                        intent.putExtra("title", notice.getTitleNotice());
                        intent.putExtra("content",notice.getContentNotice());
                        intent.putExtra("date",notice.getDate());
                        startActivity(intent);
                    }
                });
                rc_notice.setLayoutManager(new LinearLayoutManager(NoticeActivity.this, LinearLayoutManager.VERTICAL, false));
                rc_notice.setHasFixedSize(true);
                rc_notice.setAdapter(noticeAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}