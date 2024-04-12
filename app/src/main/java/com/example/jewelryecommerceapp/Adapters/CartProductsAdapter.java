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
public class CartProductsAdapter extends RecyclerView.Adapter<CartProductsAdapter.ProductViewHolder> {

    Context context;
    ArrayList<Product> listPro;
    public CartProductsAdapter(Context context, ArrayList<Product> listPro)
    {
        this.context=context;
        this.listPro=listPro;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_cart, parent, false);
        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position){
        Product pro=listPro.get(position);
        if (pro==null)
            return;
        holder.idPro.setText("Mã sản phẩm: " + pro.getProductId());
        holder.imgPro.setImageResource(pro.getImg());
        holder.namePro.setText(pro.getProductName());
    }

    @Override
    public int getItemCount() {
        return listPro.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPro;
        TextView namePro;
        TextView idPro;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPro=itemView.findViewById(R.id.img_product);
            namePro=itemView.findViewById(R.id.name_product);
            idPro =itemView.findViewById(R.id.id_product);

        }
    }
}
