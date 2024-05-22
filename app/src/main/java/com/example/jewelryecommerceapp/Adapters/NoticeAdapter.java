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

        holder.title_notice.setText(notice.getTitleNotice());
        holder.more.setText("xem thêm");
        holder.date.setText(notice.getDate());
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
        TextView title_notice,more,date;
        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            title_notice=itemView.findViewById(R.id.title_notice_item);
            more=itemView.findViewById(R.id.more_notice_item);
            date=itemView.findViewById(R.id.date_notice_item);
        }
    }

}
