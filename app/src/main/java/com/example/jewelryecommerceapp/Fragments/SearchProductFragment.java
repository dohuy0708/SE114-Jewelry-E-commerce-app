package com.example.jewelryecommerceapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.Adapters.SeachProductAdapter;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class SearchProductFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_product, container, false);
    }
    private GridView Search_Product_List;
    SeachProductAdapter seachProductAdapter;
    public ArrayList<Product> productList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Search_Product_List = view.findViewById(R.id.search_product_list);
        productList = new ArrayList<>();
        productList.add(new Product(R.drawable.demo, "Nhẫn Vàng", 299999));
        productList.add(new Product(R.drawable.demo, "Nhẫn Vàng", 299999));
        productList.add(new Product(R.drawable.demo, "Nhẫn Vàng", 299999));
        productList.add(new Product(R.drawable.demo, "Nhẫn Vàng", 299999));
        productList.add(new Product(R.drawable.demo, "Nhẫn Vàng", 5687));
        seachProductAdapter = new SeachProductAdapter(this.getContext(), productList);
        Search_Product_List.setAdapter(seachProductAdapter);
    }
    }

