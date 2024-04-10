package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Models.Notice;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>{
    Context context;
    ArrayList<Notice> myList;

    public NoticeAdapter(Context context, ArrayList<Notice> myList) {
        this.context = context;
        this.myList = myList;
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
        holder.img_notice.setImageResource(notice.getImgNotice());
        holder.txt_notice.setText(notice.getTxtNotice());
        holder.more.setText("xem thêm");
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    class NoticeViewHolder extends RecyclerView.ViewHolder {
        ImageView img_notice;
        TextView txt_notice,more;
        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            img_notice=itemView.findViewById(R.id.img_notice_item);
            txt_notice=itemView.findViewById(R.id.txt_notice_item);
            more=itemView.findViewById(R.id.more_notice_item);
        }
    }
}
