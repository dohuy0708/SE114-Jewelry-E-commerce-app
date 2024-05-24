package com.example.jewelryecommerceapp.TabItems;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Activities.LoadingDialog;
import com.example.jewelryecommerceapp.Adapters.OrdersAdapter;
import com.example.jewelryecommerceapp.Adapters.ShippedOrdsAdapter;
import com.example.jewelryecommerceapp.Models.Order;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    OrdersAdapter adt;
    RecyclerView shipped;
    private LoadingDialog loadingDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingDialog = new LoadingDialog(getContext());

        ords=new ArrayList<>();

        Sort=view.findViewById(R.id.btnsort);

        adt=new OrdersAdapter(getContext(),ords);

        shipped=view.findViewById(R.id.listshipped);

        GetOrderFromfirebase();

    }

    private void GetOrderFromfirebase() {
        loadingDialog.show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Đơn hàng") ;


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ords.clear();

                for (DataSnapshot childSnapshot : snapshot.getChildren()) {

                        Order order = childSnapshot.getValue(Order.class);

                        if (order != null && order.getStatus().equals("Đã nhận")) {
                            Log.d("order đã giao", ords.size()+"");
                            ords.add(order);
                        }

                }
                adt.notifyDataSetChanged();
                SetUI();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        loadingDialog.cancel();
    }
    private void SetUI()
    {

        adt=new OrdersAdapter(getContext(),ords);
        adt.notifyDataSetChanged();
        shipped.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        shipped.setHasFixedSize(true);
        shipped.setAdapter(adt);

    }


}