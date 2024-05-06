package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.jewelryecommerceapp.Interfaces.SelectListener;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{
    ArrayList<Integer> imgList;

    public int selectedItem = 0;
    private SelectListener listener;
    public ImageAdapter(ArrayList<Integer> imgList, SelectListener listener ){
        this.imgList=imgList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        int imgUrl = imgList.get(position);
        holder.imageView.setImageResource(imgUrl);
        holder.img_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onImageItemClicked(holder.getAdapterPosition());
            }
        });
        if(selectedItem==position)
            holder.img_border.setVisibility(View.VISIBLE);
        else holder.img_border.setVisibility(View.INVISIBLE);

    }
    public void setSelectedItem(int position) {
        selectedItem = position;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public  class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView img_layout;
        LinearLayout img_border;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            img_layout=itemView.findViewById(R.id.img_layout);
            img_border=itemView.findViewById(R.id.img_border);
        }
    }
}
