package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Models.Order;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class ShippedOrdsAdapter extends RecyclerView.Adapter<ShippedOrdsAdapter.OrderViewHolder> {
    Context context;
    ArrayList<Order> ListOrder;

    public ShippedOrdsAdapter(Context context, ArrayList<Order> listOrder) {
        this.context = context;
        this.ListOrder = listOrder;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int newType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_ad_order, parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position)
    {
        Order order=ListOrder.get(position);
        if(order==null)
            return;
        holder.orderID.setText("Mã đơn hàng: "+ order.getOrderID());
        holder.ordererName.setText("Tên khách hàng: "+order.getOrdererName());
        holder.ordererSDT.setText("Số điện thoại: "+order.getOrdererSDT());
        holder.ordererAdd.setText("Địa chỉ: "+order.getOrdererAdd());
        holder.datee.setText("Ngày đặt đơn: "+order.getDatee());
        holder.money.setText("Tổng tiền: "+order.getMoney());
        holder.dtls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderID;
        TextView ordererName;
        TextView ordererSDT;
        TextView ordererAdd;
        TextView datee;
        TextView money;
        Button dtls;
        public OrderViewHolder(@NonNull View view)
        {
            super(view);
            orderID=view.findViewById(R.id.ordertitletext);
            ordererName=view.findViewById(R.id.orderernamee);
            ordererSDT=view.findViewById(R.id.orderersdt);
            ordererAdd=view.findViewById(R.id.ordereradd);
            datee=view.findViewById(R.id.ordererdate);
            money=view.findViewById(R.id.orderermoney);
            dtls=view.findViewById(R.id.buttonDtls);
        }
    }
}