package com.example.jewelryecommerceapp.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jewelryecommerceapp.AlarmReceiver;
import com.example.jewelryecommerceapp.Models.Notice;
import com.example.jewelryecommerceapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;
@RequiresApi(api = Build.VERSION_CODES.O)

public class NoticeAddActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    Notice notice;

    ImageView img_back,bt_time,bt_date,imgToNotice;
    TextInputEditText txt_content;
    TextView time_notice,date_notice;
    EditText txt_title;
    Button bt_add,select_img;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notice_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        img_back = findViewById(R.id.img_back);
        imgToNotice=findViewById(R.id.imgToNotice);
        imgToNotice.setTag((Object)"");
        select_img=findViewById(R.id.select_img);
        time_notice=findViewById(R.id.time_notice);
        bt_time=findViewById(R.id.bt_time);
        date_notice=findViewById(R.id.date_notice);
        bt_date=findViewById(R.id.bt_date);
        txt_content=findViewById(R.id.txt_content);
        txt_title=findViewById(R.id.txt_title);
        bt_add=findViewById(R.id.bt_add);
        notice= new Notice();
        date_notice.setText(formatNumber(notice.getDate().getDayOfMonth())+"/"+formatNumber(notice.getDate().getMonthValue())+"/"+String.valueOf(notice.getDate().getYear()));
        time_notice.setText(formatNumber(notice.getDate().getHour())+":"+formatNumber(notice.getDate().getMinute()));
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        select_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromDevice();
            }
        });
        bt_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTime();
            }
        });
        bt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openDate();
            }
        });
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()) {
                    addNotice();
                }
            }
        });
    }
    void getImageFromDevice(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            setImageInView(imgToNotice,selectedImageUri);
            imgToNotice.setTag(selectedImageUri);

        }
    }
    void addNotice(){
        LocalDateTime dateTime  = getLocalDateTimeFromTextViews();
        if (dateTime != null) {
            if (dateTime.isBefore(LocalDateTime.now())) {
                // textViewDateTime nhỏ hơn currentDateTime
                Toast.makeText(this, "Thời gian chọn nhỏ hơn thời gian hiện tại!", Toast.LENGTH_SHORT).show();
            } else {
                // textViewDateTime không nhỏ hơn currentDateTime
                Toast.makeText(this, "Hoàn tất", Toast.LENGTH_SHORT).show();
                // thêm notice vào list
                notice.setContentNotice(txt_content.getText().toString());
                notice.setTitleNotice(txt_title.getText().toString());
                notice.setDate(dateTime);
                String imgUri= imgToNotice.getTag().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("newnotice",notice);// key và value của kết quả muốn truyền về Main Activity
                Toast.makeText(this, imgUri, Toast.LENGTH_SHORT).show();
                resultIntent.putExtra("uri",imgUri);
                setResult(Activity.RESULT_OK, resultIntent);

                LocalDateTime localDateTime = notice.getDate(); // Lấy đối tượng LocalDateTime từ notice
                ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault()); // Chuyển đổi thành ZonedDateTime với múi giờ hệ thống
                long timeMillis = zonedDateTime.toInstant().toEpochMilli();

                Intent intent = new Intent(NoticeAddActivity.this,AlarmReceiver.class);
                intent.putExtra("id",notice.getId());
                intent.setAction("noticeAction");
                alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
                pendingIntent=PendingIntent.getBroadcast(NoticeAddActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,timeMillis,pendingIntent);
                finish(); // Kết thúc Sub Activity và trở về Main Activity
            }
        }
        else
            Toast.makeText(this, "Lỗi khi nhận thời gian", Toast.LENGTH_SHORT).show();
    }
    public void setImageInView(ImageView imageView, Object imageObject) {
        if (imageObject instanceof Uri) {
            // Nếu là Uri, đặt ảnh từ Uri vào ImageView
            imageView.setImageURI((Uri) imageObject);
        } else if (imageObject instanceof Integer) {
            // Nếu là resource ID (int), đặt ảnh từ resource ID vào ImageView
            imageView.setImageResource((Integer) imageObject);
        }
    }
    // LẤY THỜI GIAN TỪ CÁC TEXT VIEW
    LocalDateTime getLocalDateTimeFromTextViews() {
        String timeString = time_notice.getText().toString();
        String dateString = date_notice.getText().toString();

        String dateTimeString = dateString + " " + timeString;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);
            return localDateTime;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private void updateLocalDateTime(LocalDateTime selectedDateTime,LocalDateTime newDateTime) {
        if (newDateTime != null) {
            // Thiết lập lại các giá trị cho LocalDateTime
            selectedDateTime = selectedDateTime
                    .withHour(newDateTime.getHour())
                    .withMinute(newDateTime.getMinute())
                    .withDayOfMonth(newDateTime.getDayOfMonth())
                    .withMonth(newDateTime.getMonthValue())
                    .withYear(newDateTime.getYear());

            // Hiển thị thông báo cho người dùng rằng đã cập nhật lại LocalDateTime thành công
            Toast.makeText(this, "Đã cập nhật lại LocalDateTime thành công!", Toast.LENGTH_SHORT).show();
        } else {
            // Hiển thị thông báo cho người dùng nếu có lỗi xảy ra khi cập nhật lại
            Toast.makeText(this, "Lỗi khi cập nhật lại LocalDateTime!", Toast.LENGTH_SHORT).show();
        }

    }
    boolean isValid(){
        if (txt_content.getText().toString().equals("")||txt_title.getText().toString().equals(""))
           return false;
        return true;
    }
    void openTime(){
        LocalDateTime calendar= LocalDateTime.now();
        int hour = calendar.getHour();
        int minute= calendar.getMinute();
        TimePickerDialog timePickerDialog= new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time_notice.setText(formatNumber(hourOfDay)+":"+formatNumber(minute));
            }
        }, hour, minute, true);
        timePickerDialog.show();

    }
        void  openDate(){
            Calendar calendar=Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month= calendar.get(Calendar.MONTH);
            int day= calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    date_notice.setText(formatNumber(dayOfMonth)+"/"+formatNumber(month+1)+"/"+String.valueOf(year));
                }
            }, year, month, day);
            datePickerDialog.show();
        }
    void openDialog(){
        Dialog dialog= new Dialog(this);
        dialog.show();
        Toast.makeText(this,"true",Toast.LENGTH_SHORT).show();

    }
    String formatNumber(int a){
        if (a<10)
            return "0"+String.valueOf(a);
        return String.valueOf(a);
    }

}