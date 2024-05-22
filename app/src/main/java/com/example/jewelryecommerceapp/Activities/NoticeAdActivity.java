package com.example.jewelryecommerceapp.Activities;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Adapters.NoticeAdapter;
import com.example.jewelryecommerceapp.Interfaces.SelectNotice;
import com.example.jewelryecommerceapp.Models.Notice;
import com.example.jewelryecommerceapp.Models.Voucher;
import com.example.jewelryecommerceapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NoticeAdActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SUB_ACTIVITY = 1;

    ImageView img_back;
    EditText txt_title;
    TextInputEditText txt_content;
    Button add_notice;

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
        txt_title=findViewById(R.id.txt_title);
        txt_content=findViewById(R.id.txt_content);
        add_notice=findViewById(R.id.add_notice);

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
              if(txt_content.getText().toString().equals("")||txt_title.getText().toString().equals("")){
                  Toast.makeText(NoticeAdActivity.this, "Vui lòng điền đủ thông tin", Toast.LENGTH_LONG).show();
              }
              else {
                  Date currentDate = Calendar.getInstance().getTime();

                  // Định dạng ngày thành chuỗi
                  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                  String dateString = dateFormat.format(currentDate);
                  pushNoticeToDatabase(new Notice(txt_title.getText().toString(),txt_content.getText().toString(),dateString));
              }
            }
        });

    }
    void pushNoticeToDatabase(Notice notice){
        FirebaseDatabase data = FirebaseDatabase.getInstance();
        DatabaseReference ref = data.getReference("Notice");

        // Đẩy đối tượng thông báo lên Firebase
        ref.push().setValue(notice, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    Toast.makeText(NoticeAdActivity.this, "Thêm thông báo thất bại", Toast.LENGTH_LONG).show();
                } else {
                    showToastWithIcon(R.drawable.succecss_icon,"Thêm thông báo thành công");
                }
            }
        });
    }
   /* void getNoticeFromDatabase(){
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
                noticeAdapter = new NoticeAdapter(NoticeAdActivity.this, noticeList, new SelectNotice() {
                    @Override
                    public void onNoticeSelect(Notice notice) {
                        dialog= new BottomSheetDialog(NoticeAdActivity.this);
                        createDetailDialog(notice);
                        dialog.show();
                        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    }
                },1);
                rc_notice.setLayoutManager(new LinearLayoutManager(NoticeAdActivity.this,LinearLayoutManager.VERTICAL,false));
                rc_notice.setHasFixedSize(true);
                rc_notice.setAdapter(noticeAdapter);
                noticeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
    public void showToastWithIcon(int icon, String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        // Tùy chỉnh icon và văn bản trong toast
        ImageView imageView = layout.findViewById(R.id.toast_icon);
        imageView.setImageResource(icon); // Thay 'your_icon' bằng tên icon của bạn
        TextView textView = layout.findViewById(R.id.toast_text);
        textView.setText(message);

        // Tạo và hiển thị toast custom
        Toast toast = new Toast(NoticeAdActivity.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}