package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jewelryecommerceapp.Models.CartItem;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class CartPurchaseAdapter extends RecyclerView.Adapter<CartPurchaseAdapter.ProductViewHolder> {
    Context context;
    ArrayList<CartItem> listPro;
    public CartPurchaseAdapter(Context context, ArrayList<CartItem> listPro)
    {
        this.context=context;
        this.listPro=listPro;
    }
    @NonNull
    @Override
    public CartPurchaseAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_cart_purchase, parent, false);
        return new CartPurchaseAdapter.ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartPurchaseAdapter.ProductViewHolder holder, int position) {
        CartItem pro = listPro.get(position);
        if (pro==null)
            return;
        holder.namePro.setText(pro.getProductName());
        // holder.imgPro.setImageResource(pro.getImg());
        CartPurchaseAdapter.ProductViewHolder productViewHolder= (CartPurchaseAdapter.ProductViewHolder) holder;
        Glide.with(context).load(pro.getImage()).into(((CartPurchaseAdapter.ProductViewHolder) holder ).imgPro);

        // set dữ liệu cho number
        holder.numm.setText("SL: "+pro.getAmount()+"");
        holder.pricee.setText("Đơn giá: "+ formatNumber(pro.getProductPrice())+"VND");

    }

    @Override
    public int getItemCount() {
        return listPro.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPro;
        TextView namePro;
        TextView pricee;

        TextView numm;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPro = itemView.findViewById(R.id.pic);
            namePro = itemView.findViewById(R.id.titletext);
            pricee = itemView.findViewById(R.id.price);
            numm = itemView.findViewById(R.id.number);

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
