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

import com.example.jewelryecommerceapp.Adapters.OrdersAdapter;
import com.example.jewelryecommerceapp.Models.Order;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class WaitAceptTabItem extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabitem_waitacp, container, false);
    }
    ImageView Sort;
    ArrayList<Order> ords;
    OrdersAdapter adt;
    RecyclerView waitacp;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ords=new ArrayList<Order>();
        initOrds(ords);
        Sort=view.findViewById(R.id.btnsort);
        adt=new OrdersAdapter(getContext(),ords);
        waitacp=view.findViewById(R.id.listwait);
        waitacp.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        waitacp.setHasFixedSize(true);
        waitacp.setAdapter(adt);
        adt.notifyDataSetChanged();
    }

    private void initOrds(ArrayList<Order> ors) {
    }
}