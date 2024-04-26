package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Models.CategoryItem;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    Context context;
    ArrayList<CategoryItem> myList;
    private OnItemClickListener listener;

    public CategoryAdapter(Context context, ArrayList<CategoryItem> myList){
        this.context=context;
        this.myList=myList;
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_category,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryItem categoryItem =myList.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!= null) {
                    listener.onItemClick(categoryItem);
                }
            }
        });
        if (categoryItem==null)
            return;
    holder.category_item_name.setText(categoryItem.getCategoryName());
    }

    @Override
    public int getItemCount() {
        if(myList==null)
            return 0;
        return myList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        TextView category_item_name;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            category_item_name=itemView.findViewById(R.id.category_item_name);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(CategoryItem categoryItem);
    }
        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

}