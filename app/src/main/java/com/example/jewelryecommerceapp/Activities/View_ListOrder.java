package com.example.jewelryecommerceapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.jewelryecommerceapp.Adapters.OrdersAdapter;
import com.example.jewelryecommerceapp.Adapters.ProductAdapter;
import com.example.jewelryecommerceapp.Models.Order;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class View_ListOrder extends AppCompatActivity {


    private RecyclerView rc_listOrder;
    private ArrayList<Order> OrderList = new ArrayList<>();

    private OrdersAdapter ordersAdapter;
    private LoadingDialog loadingDialog;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_order);
        loadingDialog = new LoadingDialog(View_ListOrder.this);
        back = findViewById(R.id.btnbackkk);
        rc_listOrder= findViewById(R.id.rc_listorder);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        OrderList = new ArrayList<>();
        ordersAdapter=new OrdersAdapter(this, OrderList, new OrdersAdapter.IClickListener() {
            @Override
            public void OnClickItem(String productType, String productID) {
                // intent tới màn hình xem chi tiết sản phẩm

            }
        });

        GetOrderFromfirebase();

    }

    private void GetOrderFromfirebase() {
        loadingDialog.show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Đơn hàng");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                OrderList.clear();
                ArrayList<Order> processingOrders = new ArrayList<>();
                ArrayList<Order> shippingOrders = new ArrayList<>();
                ArrayList<Order> receivedOrders = new ArrayList<>();

                OrderList.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Order order = dataSnapshot.getValue(Order.class);
                    if(order.getUserID().equals(userid))
                    {
                        if (order != null) {
                            switch (order.getStatus()) {
                                case "Đang xử lý":
                                    processingOrders.add(order);
                                    break;
                                case "Đang giao":
                                    shippingOrders.add(order);
                                    break;
                                case "Đã nhận":
                                    receivedOrders.add(order);
                                    break;
                            }
                        }
                    }


                }

                OrderList.addAll(processingOrders);
                OrderList.addAll(shippingOrders);
                OrderList.addAll(receivedOrders);
                 SetUI();
                //    Toast.makeText(SearchActivity.this,productList.get(0).getProductName(),Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        loadingDialog.cancel();
    }

    /// Dòng 90
    private void SetUI() {

        ordersAdapter.notifyDataSetChanged();
        rc_listOrder.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rc_listOrder.setHasFixedSize(true);
        rc_listOrder.setAdapter(ordersAdapter);

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