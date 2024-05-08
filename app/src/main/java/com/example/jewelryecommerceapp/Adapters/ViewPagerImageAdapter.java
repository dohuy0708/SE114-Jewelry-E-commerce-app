package com.example.jewelryecommerceapp.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jewelryecommerceapp.Interfaces.SelectListener;
import com.example.jewelryecommerceapp.R;

import java.util.List;

public class ViewPagerImageAdapter extends RecyclerView.Adapter<ViewPagerImageAdapter.ViewHolder> {
    private List<String> images;
    private SelectListener listener;

    public ViewPagerImageAdapter(List<String> images,SelectListener listener) {
        this.images = images;
        this.listener=listener;

    }
    public ViewPagerImageAdapter(List<String> images) {
        this.images = images;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_image_viewpager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String image = images.get(position);
        Glide.with(holder.imageView.getContext()).load(image).into(((ViewPagerImageAdapter.ViewHolder) holder).imageView);
        //  holder.imageView.setImageResource(image);
        holder.img_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onImageItemClicked(holder.getAdapterPosition());
            }
        });

    }


    @Override
    public int getItemCount() {
        if(images==null)
            return 0;
        return images.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ConstraintLayout img_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            img_layout=itemView.findViewById(R.id.img_layout);
        }
    }
}
