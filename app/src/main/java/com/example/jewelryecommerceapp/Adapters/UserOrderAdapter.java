package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Activities.FeedbackActivity;
import com.example.jewelryecommerceapp.Models.Order;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class UserOrderAdapter extends RecyclerView.Adapter<UserOrderAdapter.OrderViewHolder> {
    Context context;
    ArrayList<Order> ListOrder;

    public UserOrderAdapter(Context context, ArrayList<Order> listOrder) {
        this.context = context;
        this.ListOrder = listOrder;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int newType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_user_orders, parent,false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position)
    {
        Order order=ListOrder.get(position);
        if(order==null)
            return;
        holder.userOrderID.setText("Mã đơn hàng: "+ order.getOrderID());
        holder.userOrderDate.setText("Ngày đặt hàng: "+order.getDatee());
       // holder.userOrderRec.setText("Ngày nhận hàng: "+order.getReceive());
        holder.userOrderMoney.setText("Tổng tiền: "+order.getMoney());
        holder.userBtnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.userJudge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context1=v.getContext();
                Intent i=new Intent(context1, FeedbackActivity.class);
                Intent[] intents={i};
                context1.startActivities(intents);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView userOrderID;
        TextView userOrderDate;
        TextView userOrderRec;
        TextView userOrderMoney;
        Button userBtnDetails;
        Button userJudge;

        public OrderViewHolder(@NonNull View view)
        {
            super(view);
            userOrderID=view.findViewById(R.id.UserOrderID);
            userOrderDate=view.findViewById(R.id.userOrderDate);
            userOrderRec=view.findViewById(R.id.userOrderrRec);
            userOrderMoney=view.findViewById(R.id.userOrderMoney);
            userBtnDetails=view.findViewById(R.id.userButtonDtls);
            userJudge=view.findViewById(R.id.btnJudge);

        }
    }
}