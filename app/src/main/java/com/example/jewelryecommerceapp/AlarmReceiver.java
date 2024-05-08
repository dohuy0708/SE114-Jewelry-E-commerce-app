package com.example.jewelryecommerceapp;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Activities.NoticeAdActivity;
import com.example.jewelryecommerceapp.Adapters.NoticeAdapter;
import com.example.jewelryecommerceapp.Models.Notice;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class AlarmReceiver extends BroadcastReceiver {
    final String CHANEL_ID ="201";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("noticeAction")){
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            String id= intent.getStringExtra("id");
            // Tạo thông báo
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANEL_ID)
                    .setSmallIcon(R.drawable.ic_notice)
                    .setContentTitle("Thông báo")
                    .setContentText("Đã gửi 1 thông báo mới.")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            // Hiển thị thông báo
//            notificationManager.notify(1, builder.build());
            Intent intentToStartActivity = new Intent(context, NoticeAdActivity.class);
            intentToStartActivity.putExtra("id",id);
            intentToStartActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Cần thêm flag này nếu không đang trong context của BroadcastReceiver
            context.startActivity(intentToStartActivity);

            // tự xóa notice treen firebase

        }
    }

}
