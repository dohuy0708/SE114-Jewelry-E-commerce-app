package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.R;
import com.example.jewelryecommerceapp.Models.Product;

import java.util.ArrayList;

public class ProductAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   Context context;
   ArrayList<Product> productList;

   int type =0;
   public ProductAdapter(Context context, ArrayList<Product> productList){
       this.context=context;
       this.productList=productList;
   }
    public ProductAdapter(Context context, ArrayList<Product> productList,int type){
        this.context=context;
        this.productList=productList;
        this.type=1;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(type==0)
         view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_product,parent,false);
       else
           view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_product_2columns,parent,false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product product=productList.get(position);
        if(product==null)
            return;
        ProductViewHolder productViewHolder= (ProductViewHolder) holder;
        productViewHolder.img_product.setImageResource(product.getImg());
        productViewHolder.name_product.setText(product.getProductName());
        productViewHolder.price_product.setText(product.getProductPrice()+"");

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
