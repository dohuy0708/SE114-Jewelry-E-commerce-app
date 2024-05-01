package com.example.jewelryecommerceapp.TabItems;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jewelryecommerceapp.Adapters.ShippedOrdsAdapter;
import com.example.jewelryecommerceapp.Adapters.ShippingOrdsAdapter;
import com.example.jewelryecommerceapp.Models.Order;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class AceptedTabItem extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabitem_acpted, container, false);
    }
    ImageView Sort;
    ArrayList<Order> ords;
    ShippingOrdsAdapter adt;
    RecyclerView acpted;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ords=new ArrayList<Order>();
        initOrds(ords);
        Sort=view.findViewById(R.id.btnsort);
        adt=new ShippingOrdsAdapter(getContext(),ords);
        acpted=view.findViewById(R.id.listacpted);
        acpted.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        acpted.setHasFixedSize(true);
        acpted.setAdapter(adt);
        adt.notifyDataSetChanged();
    }

    private void initOrds(ArrayList<Order> ords) {
        ords.add(new Order("abc","acbc","123","",345,"",""));
    }
}