package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class TopRateAdapter extends RecyclerView.Adapter<TopRateAdapter.ProductViewHolder>{

    Context context;
    ArrayList<Product> myList;
    public TopRateAdapter(Context context, ArrayList<Product> myList){
        this.context=context;
        this.myList=myList;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_top_rate,parent,false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product=myList.get(position);
        if(product==null)
            return;
        holder.img_product.setImageResource(product.getImg());
        holder.name_product.setText(product.getProductName());
        double rate=product.getRatingStar();


        if(rate-(int)rate==0)
        {
            int rate1=(int)rate;
        holder.rate_product.setText("Đánh giá: "+rate1+"/5");
        }
        else {
            holder.rate_product.setText("Đánh giá: "+rate+"/5");
        }

    }

    @Override
    public int getItemCount() {
        if(myList==null)
            return 0;
        return myList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView img_product;
        TextView name_product,rate_product;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product=itemView.findViewById(R.id.img_product);
            name_product=itemView.findViewById(R.id.name_product);
            rate_product=itemView.findViewById(R.id.rate_product);
        }
    }
}
