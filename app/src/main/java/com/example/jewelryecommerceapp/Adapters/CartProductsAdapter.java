package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
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

import com.bumptech.glide.Glide;
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
        ArrayList<String> imagelist = listPro.get(position).getImagelist();
        if (pro==null)
            return;
        holder.namePro.setText(pro.getProductName());
       // holder.imgPro.setImageResource(pro.getImg());
        CartProductsAdapter.ProductViewHolder productViewHolder= (CartProductsAdapter.ProductViewHolder) holder;
        Glide.with(context).load(imagelist.get(0)).into(((ProductViewHolder) holder).imgPro);
        String s=holder.numm.getText().toString();
        int numb=Integer.parseInt(s);
        holder.pricee.setText("Đơn giá: "+ pro.getProductPrice());
        holder.pluss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nt=holder.numm.getText().toString();
                if(!TextUtils.isEmpty(nt))
                {
                    int numb=Integer.parseInt(nt);
                    numb++;
                    holder.numm.setText(String.valueOf(numb));
                }
                else
                {
                    holder.numm.setError("Vui lòng nhập số!");
                }
            }
        });
        holder.minuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(holder.numm.getText().toString()))
                {
                    int numb=Integer.parseInt(holder.numm.getText().toString());
                    numb--;
                    if(numb>=1)
                    {
                        holder.numm.setText(String.valueOf(numb));
                    }
                    else
                    {
                        holder.numm.setText("1");
                    }
                }
                else
                {
                    holder.numm.setError("Vui lòng nhập số!");
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


    public class ProductViewHolder extends RecyclerView.ViewHolder {

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
            imgPro = view.findViewById(R.id.pic);
            namePro = view.findViewById(R.id.titletext);
            pricee = view.findViewById(R.id.price);
            pluss = view.findViewById(R.id.plusbtn);
            minuss = view.findViewById(R.id.minusbtn);
            numm = view.findViewById(R.id.number);
            check = view.findViewById(R.id.checking);
            binn = view.findViewById(R.id.bin);
            numm.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString()=="0")
                    {
                        numm.setText("1");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            numm.setFilters(new InputFilter[]{new InputFilter() {
                @Override
                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                    for (int i = start; i < end; i++) {
                        if (!isValidCharacter(source.charAt(i))) {
                            return "";
                        }
                    }
                    return null;
                }
            }});
//        }
//    }
//
//    private boolean isValidInput(String string) {
//        try
//        {
//            int i=Integer.parseInt(string);
//            return i>=1;
//        }
//        catch(NumberFormatException e)
//        {
//            return false;
//        }
//    }
        }

        private boolean isValidCharacter(char c) {
            return Character.isDigit(c);
        }
    }
}

