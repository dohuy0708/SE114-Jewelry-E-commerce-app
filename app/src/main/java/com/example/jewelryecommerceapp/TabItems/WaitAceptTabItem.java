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
import com.example.jewelryecommerceapp.Activities.View_ListOrder;
import com.example.jewelryecommerceapp.Adapters.OrdersAdapter;
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
import java.util.Objects;

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
    private LoadingDialog loadingDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingDialog = new LoadingDialog(getContext());
        ords=new ArrayList<>();
        adt=new OrdersAdapter(getContext(),ords);

        Sort=view.findViewById(R.id.btnsort);

        waitacp=view.findViewById(R.id.listwait);

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

                        if (order != null && order.getStatus().equals("Đang xử lý")) {
                            Log.d("order", ords.size()+"");
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
        waitacp.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        waitacp.setHasFixedSize(true);
        waitacp.setAdapter(adt);
    }

}