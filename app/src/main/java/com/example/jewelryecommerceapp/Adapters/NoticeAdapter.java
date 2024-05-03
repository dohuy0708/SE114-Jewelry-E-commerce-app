package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Interfaces.SelectListener;
import com.example.jewelryecommerceapp.Interfaces.SelectNotice;
import com.example.jewelryecommerceapp.Models.Notice;
import com.example.jewelryecommerceapp.R;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>{
    Context context;
    ArrayList<Notice> myList;
    SelectNotice listener;
    int type=0;
    public ArrayList<Notice> getNoticeList() {
        return myList;
    }
    public NoticeAdapter(Context context, ArrayList<Notice> myList,SelectNotice listener) {
        this.context = context;
        this.myList = myList;
        this.listener=listener;
    }
    public NoticeAdapter(Context context, ArrayList<Notice> myList,SelectNotice listener,int type) {
        this.context = context;
        this.myList = myList;
        this.listener=listener;
        this.type=1;
    }
    @NonNull
    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_notice,parent,false);

        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        Notice notice=myList.get(position);
        if(notice==null)
            return;

        setImageInView(holder.img_notice,notice.getImgNotice());
        holder.title_notice.setText(notice.getTitleNotice());
        holder.more.setText("xem thêm");
        if(type==0)
            holder.time.setVisibility(View.INVISIBLE);
        else
            holder.time.setText(notice.getDate().toString().replace("T"," "));
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onNoticeSelect(notice);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }



    class NoticeViewHolder extends RecyclerView.ViewHolder {
        ImageView img_notice;
        TextView title_notice,more,time;

        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            img_notice=itemView.findViewById(R.id.img_notice_item);
            title_notice=itemView.findViewById(R.id.title_notice_item);
            more=itemView.findViewById(R.id.more_notice_item);
            time=itemView.findViewById(R.id.time_notice_item);
        }
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
}
