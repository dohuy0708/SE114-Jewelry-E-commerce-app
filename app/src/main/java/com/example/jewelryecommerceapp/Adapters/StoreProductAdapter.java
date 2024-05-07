package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class StoreProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<Product> productList;
    private OnItemClickListener listener;
    public StoreProductAdapter(Context context, ArrayList<Product> productList){
        this.context=context;
        this.productList=productList;
    }
    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_product_tablerow,parent,false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product product=productList.get(position);
        if(product==null)
            return;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!= null) {
                    listener.onItemClick(product);
                }
            }
        });
        TableViewHolder tableViewHolder= (TableViewHolder) holder;
        tableViewHolder.store_id_product.setText(product.getProductId());
        tableViewHolder.store_name_product.setText(product.getProductName());
        tableViewHolder.store_price_product.setText(product.getProductPrice()+"");
        /*tableViewHolder.store_amount_product.setText(product.getRemainAmount()+"");*/
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class TableViewHolder extends RecyclerView.ViewHolder {

        TextView store_id_product,store_name_product,store_price_product,store_amount_product;
        public TableViewHolder(@NonNull View itemView) {
            super(itemView);
            store_id_product=itemView.findViewById(R.id.store_id_product);
            store_name_product=itemView.findViewById(R.id.store_name_product);
            store_price_product=itemView.findViewById(R.id.store_price_product);
            store_amount_product=itemView.findViewById(R.id.store_amount_product);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Product product);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
