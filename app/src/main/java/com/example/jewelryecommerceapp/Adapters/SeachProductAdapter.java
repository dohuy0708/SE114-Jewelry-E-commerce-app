package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;

import java.util.List;

public class SeachProductAdapter extends BaseAdapter {
    private Context context;
    private List<Product> productList;

    private TextView name_product;
    private  TextView price_product;
    private ImageView img_product;
    public SeachProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }


    @Override
    public int getCount() {return productList.size();    }

    @Override
    public Object getItem(int position) {return null;
    }

    @Override
    public long getItemId(int position) {return 0;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.view_holder_product, null);
        Product product = productList.get(position);

        name_product = (TextView) convertView.findViewById(R.id.name_product);
        name_product.setText(product.getProductName());
        img_product = (ImageView) convertView.findViewById(R.id.img_product);
        /*img_product.setImageResource(product.getImg());*/
        price_product = (TextView) convertView.findViewById(R.id.price_product);
        price_product.setText(product.getProductPrice()+" ");
        return convertView;
    }

}
