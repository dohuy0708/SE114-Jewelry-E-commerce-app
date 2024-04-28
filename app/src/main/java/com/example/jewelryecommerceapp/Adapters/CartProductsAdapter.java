package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.R;
import com.example.jewelryecommerceapp.Models.Product;

import org.w3c.dom.Text;

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
        holder.namePro.setText(pro.getProductName());
        holder.imgPro.setImageResource(pro.getImg());
        String s=holder.numm.getText().toString();
        int numb=Integer.parseInt(s);
        holder.pricee.setText("Tổng giá: "+ pro.getProductPrice()*numb);
        holder.pluss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numb=Integer.parseInt(holder.numm.getText().toString());
                numb++;
                holder.numm.setText(String.valueOf(numb));
            }
        });
        holder.minuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numb=Integer.parseInt(holder.numm.getText().toString());
                numb--;
                if(numb>=1)
                {
                    holder.numm.setText(String.valueOf(numb));
                }
                else
                {
                    listPro.remove(pro);
                }
            }
        });
        holder.binn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPro.remove(pro);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listPro.size();
    }


    public class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView imgPro;
        TextView namePro;
        TextView pricee;
        TextView pluss;
        TextView minuss;
        EditText numm;
        CheckBox check;
        ImageView binn;
        public ProductViewHolder(@NonNull View view) {
            super(view);
            imgPro=view.findViewById(R.id.pic);
            namePro=view.findViewById(R.id.titletext);
            pricee=view.findViewById(R.id.price);
            pluss=view.findViewById(R.id.plusbtn);
            minuss=view.findViewById(R.id.minusbtn);
            numm=view.findViewById(R.id.number);
            check=view.findViewById(R.id.checking);
            binn=view.findViewById(R.id.bin);
            numm.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(!s.toString().isEmpty())
                    {
                        int numb=Integer.parseInt(s.toString());
                        if(numb<=0)
                        {
                            numm.setError("Vui lòng chọn số nguyên lớn hơn 0");
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }
}
