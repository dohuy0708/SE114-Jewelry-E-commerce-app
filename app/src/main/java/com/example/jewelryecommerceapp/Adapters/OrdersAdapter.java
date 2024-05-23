package com.example.jewelryecommerceapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Activities.LoginActivity;
import com.example.jewelryecommerceapp.Activities.Order_detail;
import com.example.jewelryecommerceapp.Activities.ProductDetailActivity;
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

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {
    Context context;
    ArrayList<Order> ListOrder;
    private IClickListener mIClicklistener;

    public interface IClickListener {
        void OnClickItem(String productType, String productID);
    }

    public OrdersAdapter(Context context, ArrayList<Order> listOrder) {
        this.context = context;
        this.ListOrder = listOrder;
    }

    public OrdersAdapter(Context context, ArrayList<Order> listOrder, IClickListener listener) {
        this.context = context;
        this.ListOrder = listOrder;
        this.mIClicklistener = listener;

    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int newType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_orders, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = ListOrder.get(position);
        if (order == null)
            return;
        holder.orderID.setText("Mã đơn hàng: " + order.getOrderID());
        //holder.ordererName.setText("Tên khách hàng: "+order.getOrdererName());
        //   holder.ordererSDT.setText("Số điện thoại: "+order.getOrdererSDT());
        //  holder.ordererAdd.setText("Địa chỉ: "+order.getOrdererAdd());
        holder.datee.setText("Ngày đặt hàng: " + order.getDatee());
        holder.money.setText( formatNumber(order.getMoney())+ " VND");
        holder.status.setText(order.getStatus()+"");

        String status = order.getStatus();

        if (status.equals("Đang xử lý")) {
            Log.d("status", status);
            holder.cancel.setEnabled(true);
            // holder.cancel.setTextColor(Color.GRAY); // Ẩn Button nhưng vẫn chiếm không gian
            holder.cancel.setVisibility(View.VISIBLE);
            holder.finish.setVisibility(View.GONE);
        }
        else if (status.equals("Đang giao")){
            holder.finish.setVisibility(View.VISIBLE);
            holder.cancel.setEnabled(true);
            holder.cancel.setVisibility(View.GONE);
            holder.status.setTextColor(Color.MAGENTA);

        }
        else {
            holder.finish.setVisibility(View.VISIBLE);
            holder.cancel.setEnabled(false);
            holder.cancel.setVisibility(View.GONE);
            holder.finish.setTextColor(Color.GRAY);
            holder.status.setTextColor(Color.GREEN);
        }

        // hủy đơn
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetDialogCancel("Bạn chắn chắn muốn hủy đơn hàng!",order);


            }
        });

        // hoàn tất đơn
        holder.finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetDialogFinish("Bạn đã nhận đơn hàng thành công",order);
            }
        });

// xem chi tiết đơn hàng

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Order_detail.class);
                String ID = order.getOrderID();
                intent.putExtra("ID", ID);
                context.startActivity(intent);


            }
        });


    }

    @Override
    public int getItemCount() {

        if (ListOrder == null) {
            return 0;

        }
        return ListOrder.size();
    }



    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView orderID;
        TextView ordererName;
        TextView ordererSDT;
        TextView status;
        TextView datee;
        TextView money;

        Button detail;
        Button cancel;
        Button finish;
        public OrderViewHolder(@NonNull View view)
        {
            super(view);
            orderID=view.findViewById(R.id.ordertitletext);
            //   ordererName=view.findViewById(R.id.orderernamee);
            // ordererSDT=view.findViewById(R.id.orderersdt);
            // ordererAdd=view.findViewById(R.id.ordereradd);
            datee=view.findViewById(R.id.ordererdate);
            money=view.findViewById(R.id.money);
            status = view.findViewById(R.id.status);
            detail= view.findViewById(R.id.btndetail);
            cancel=view.findViewById(R.id.btncancel);
            finish=view.findViewById(R.id.btnfinish);
        }
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
    void GetDialogCancel(String Message, Order order)
    {
        AlertDialog.Builder mydialog = new AlertDialog.Builder(context);
        mydialog.setTitle("Thông báo!");
        mydialog.setMessage(Message);
        mydialog.setIcon(R.drawable.ic_alarm);
        mydialog.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userid = user.getUid();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("Đơn hàng").child(userid);


                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {

                            Order orderget = dataSnapshot.getValue(Order.class);

                            if ( orderget!=null && orderget.getOrderID().equals(order.getOrderID()))
                            {


                            }



                        }
                        //    ordersAdapter.notifyDataSetChanged();

                        //    Toast.makeText(SearchActivity.this,productList.get(0).getProductName(),Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        mydialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        mydialog.create().show();
    }

    void GetDialogFinish(String Message, Order order)
    {
        AlertDialog.Builder mydialog = new AlertDialog.Builder(context);
        mydialog.setTitle("Thông báo!");
        mydialog.setMessage(Message);
        mydialog.setIcon(R.drawable.ic_alarm);
        mydialog.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userid = user.getUid();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("Đơn hàng").child(userid);


                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
                        {

                            Order orderget = dataSnapshot.getValue(Order.class);

                            if ( orderget!=null && orderget.getOrderID().equals(order.getOrderID()))
                            {
                                dataSnapshot.getRef().removeValue().addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {


                                    } else {
                                        Log.e("Delete order", "Failed to delete order", task.getException());
                                    }
                                });

                            }



                        }
                        //    ordersAdapter.notifyDataSetChanged();

                        //    Toast.makeText(SearchActivity.this,productList.get(0).getProductName(),Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        mydialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        mydialog.create().show();
    }

}