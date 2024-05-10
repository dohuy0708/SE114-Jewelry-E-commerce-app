package com.example.jewelryecommerceapp.Adapters;

import static com.example.jewelryecommerceapp.R.layout.*;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Models.Comment;
import com.example.jewelryecommerceapp.Models.User;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{
    ArrayList<Comment> cmtList;


    public CommentAdapter(ArrayList<Comment> cmtList){


        this.cmtList=cmtList;
    }



    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_comment,parent,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment cmt = cmtList.get(position);
        User user= cmt.getUser();
        holder.cmt_content.setText(cmt.getContent());
        holder.cmt_name.setText(user.getNAME());
       // holder.cmt_avt.setImageResource(user.getImgTest());

        int desiredWidthInDp = 20 * cmt.getRate(); // Số nguyên bất kỳ nhân với cmt.getRate() để có chiều rộng mong muốn tính theo dp

        // Chuyển đổi từ dp sang px
        Resources resources = Resources.getSystem();
        float density = resources.getDisplayMetrics().density;
        int desiredWidthInPx = (int) (desiredWidthInDp * density);

        // Lấy LayoutParams hiện tại của cmt_star
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.cmt_star.getLayoutParams();

        // Đặt chiều rộng mới tính theo px cho layoutParams
        layoutParams.width = desiredWidthInPx;

        // Đặt lại LayoutParams cho cmt_star
        holder.cmt_star.setLayoutParams(layoutParams);
        holder.cmt_star.requestLayout();

    }


    @Override
    public int getItemCount() {
        if(cmtList!=null)
        return cmtList.size();
        return 0;
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        ImageView cmt_avt;
        TextView cmt_name, cmt_content;
        LinearLayout cmt_star;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            cmt_avt=itemView.findViewById(R.id.cmt_avt);
            cmt_name=itemView.findViewById(R.id.cmt_name);
            cmt_content=itemView.findViewById(R.id.cmt_content);
            cmt_star=itemView.findViewById(R.id.cmt_star);
        }

    }
}
