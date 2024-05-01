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

import com.bumptech.glide.Glide;
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
        ArrayList<String> imagelist = productList.get(position).getImagelist();
        if(product==null)
            return;
        ProductViewHolder productViewHolder= (ProductViewHolder) holder;
        Glide.with(context).load(imagelist.get(0)).into(((ProductViewHolder) holder).img_product);
        productViewHolder.name_product.setText(product.getProductName());
        productViewHolder.price_product.setText(formatNumber(product.getProductPrice())+" VND");

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
    public static String formatNumber(int number) {
        String strNumber = String.valueOf(number); // Chuyển đổi số thành chuỗi
        int length = strNumber.length(); // Độ dài của chuỗi số

        // Xây dựng chuỗi kết quả từ phải sang trái, thêm dấu chấm sau mỗi 3 ký tự
        StringBuilder result = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            result.insert(0, strNumber.charAt(i));
            if ((length - i) % 3 == 0 && i != 0) {
                result.insert(0, ".");
            }
        }

        return result.toString(); // Trả về chuỗi kết quả
    }
}
