package com.example.jewelryecommerceapp.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Interfaces.SelectListener;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {
    private ArrayList<String> sizes;
    private int selectedPosition = RecyclerView.NO_POSITION;
    SelectListener listener;

    public SizeAdapter(ArrayList<String> sizes,SelectListener listener) {
        this.sizes = sizes;
        this.listener=listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_size, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String size = sizes.get(position);
        holder.textViewSize.setText(size);

        // Kiểm tra xem vị trí hiện tại có phải là vị trí của item được chọn không
        if (position == selectedPosition) {

            holder.textViewSize.setTextColor(Color.RED); // Màu tô đậm của item được chọn
        } else {

            holder.textViewSize.setTextColor(Color.BLACK); // Màu bình thường của các item khác
        }

        // Xử lý sự kiện khi người dùng nhấn vào một item

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int previousSelectedPosition = selectedPosition;
                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(previousSelectedPosition); // Cập nhật giao diện của item trước đó
                notifyItemChanged(selectedPosition); // Cập nhật giao diện của item mới được chọn
                listener.onImageItemClicked(selectedPosition);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(sizes==null)
            return 0;
        return sizes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSize = itemView.findViewById(R.id.text_view_size);
        }
    }
}
