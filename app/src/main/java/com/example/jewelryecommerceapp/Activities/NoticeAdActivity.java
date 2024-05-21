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

import java.util.ArrayList;

public class NoticeAdActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_SUB_ACTIVITY = 1;

    ImageView img_back;
    RecyclerView rc_notice;
    NoticeAdapter noticeAdapter;
    Button add_notice;
    ArrayList<Notice> noticeList;
    BottomSheetDialog dialog;

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
        noticeList= new ArrayList<>();


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
               /* Intent intent = new Intent(NoticeAdActivity.this, NoticeAddActivity.class);
                startActivityForResult(intent,REQUEST_CODE_SUB_ACTIVITY);*/
                dialog= new BottomSheetDialog(NoticeAdActivity.this);
                createDialog();
                dialog.show();
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        });

    }

    @SuppressLint("MissingInflatedId")

    void createDialog(){
    View view = getLayoutInflater().inflate(R.layout.bottom_sheet_notice,null,false);
    EditText txt_title = view.findViewById(R.id.txt_title);
    TextInputEditText txt_content= view.findViewById(R.id.txt_content);

    view.findViewById(R.id.bt_add).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String title = txt_title.getText().toString();
            String content = txt_content.getText().toString();
            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(NoticeAdActivity.this, "Vui lòng nhập đủ tiêu đề và nội dung", Toast.LENGTH_SHORT).show();
            } else {
                Notice notice = new Notice();
                notice.setTitleNotice(title);
                notice.setContentNotice(content);
                noticeList.add(notice);
                noticeAdapter.notifyDataSetChanged();
                // Ở đây bạn có thể lưu trữ hoặc xử lý đối tượng 'notice' theo yêu cầu của ứng dụng của bạn
                dialog.dismiss();
            }
        }
    });
    view.findViewById(R.id.bt_send).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    });
    dialog.setContentView(view);


    }

    void createDetailDialog(Notice notice){
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_notice,null,false);
        EditText txt_title = view.findViewById(R.id.txt_title);
        TextInputEditText txt_content= view.findViewById(R.id.txt_content);
        TextView title_add= view.findViewById(R.id.title_add);
        title_add.setText("Thông báo chờ gửi");

        txt_content.setText(notice.getContentNotice());
        txt_title.setText(notice.getTitleNotice());

        Button bt_add= view.findViewById(R.id.bt_add);
        bt_add.setText("Xong");
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = txt_title.getText().toString();
                String content = txt_content.getText().toString();
                if (title.isEmpty() || content.isEmpty()) {
                    Toast.makeText(NoticeAdActivity.this, "Vui lòng nhập đủ tiêu đề và nội dung", Toast.LENGTH_SHORT).show();
                } else {
                    notice.setTitleNotice(title);
                    notice.setContentNotice(content);
                    noticeAdapter.notifyDataSetChanged();
                    // Ở đây bạn có thể lưu trữ hoặc xử lý đối tượng 'notice' theo yêu cầu của ứng dụng của bạn
                    dialog.dismiss();
                }
            }
        });
    view.findViewById(R.id.bt_send).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String title = txt_title.getText().toString();
            String content = txt_content.getText().toString();
            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(NoticeAdActivity.this, "Vui lòng nhập đủ tiêu đề và nội dung", Toast.LENGTH_SHORT).show();
            } else {
               // Ở đây bạn có thể lưu trữ hoặc xử lý đối tượng 'notice' theo yêu cầu của ứng dụng của bạn
                dialog.dismiss();
            }
        }
    });
    dialog.setContentView(view);
    }
    void pushNoticeToDatabase(Notice notice){
        FirebaseDatabase data = FirebaseDatabase.getInstance();
        DatabaseReference ref = data.getReference("Voucher");

        // Đẩy đối tượng Voucher lên Firebase
        ref.push().setValue(notice, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    Toast.makeText(NoticeAdActivity.this, "Thêm voucher thất bại", Toast.LENGTH_LONG).show();
                } else {
                    showToastWithIcon(R.drawable.succecss_icon,"Thêm voucher thành công");
                }
            }
        });
    }
    void getNoticeFromDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Notice")  ;
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                noticeAdapter = new NoticeAdapter(NoticeAdActivity.this, noticeList, new SelectNotice() {
                    @Override
                    public void onNoticeSelect(Notice notice) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Voucher voucher = dataSnapshot.getValue(Voucher.class);
                            noticeList.add(notice);
                        }
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
    }
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