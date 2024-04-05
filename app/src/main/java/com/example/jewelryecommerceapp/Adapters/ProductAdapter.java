package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.R;
import com.example.jewelryecommerceapp.Models.Product;

import java.util.ArrayList;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
   Context context;
   ArrayList<Product> productList;
   public ProductAdapter(Context context, ArrayList<Product> productList){
       this.context=context;
       this.productList=productList;
   }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view_holder,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
       Product product=productList.get(position);
       if(product==null)
           return;
        holder.img_product.setImageResource(product.getImg());
        holder.name_product.setText(product.getProductName());
        holder.price_product.setText(product.getProductPrice()+"");
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView img_product;
        TextView name_product,price_product;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product=itemView.findViewById(R.id.img_product);
            name_product=itemView.findViewById(R.id.name_product);
            price_product=itemView.findViewById(R.id.price_product);

        }
    }
}
