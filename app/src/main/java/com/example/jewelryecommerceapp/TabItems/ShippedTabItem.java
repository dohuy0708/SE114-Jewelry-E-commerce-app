package com.example.jewelryecommerceapp.TabItems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Adapters.ShippedOrdsAdapter;
import com.example.jewelryecommerceapp.Models.Order;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;


public class ShippedTabItem extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabitem_shipped, container, false);
    }
    ImageView Sort;
    ArrayList<Order> ords;
    ShippedOrdsAdapter adt;
    RecyclerView shipped;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ords=new ArrayList<>();
        initOrds(ords);
        Sort=view.findViewById(R.id.btnsort);
        adt=new ShippedOrdsAdapter(getContext(),ords);
        shipped=view.findViewById(R.id.listshipped);
        shipped.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        shipped.setHasFixedSize(true);
        shipped.setAdapter(adt);
        adt.notifyDataSetChanged();
    }

    private void initOrds(ArrayList<Order> ords) {
    }
}