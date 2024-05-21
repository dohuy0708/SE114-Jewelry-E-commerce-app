package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jewelryecommerceapp.Models.Customer;
import com.example.jewelryecommerceapp.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    private List<Customer> customerList;
    private Context context;

    public CustomerAdapter(List<Customer> customerList, Context context) {
        this.customerList = customerList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_customer, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customer customer = customerList.get(position);
        holder.customerName.setText(customer.getName());
        holder.dateCreate.setText(customer.getDateCreate());
        holder.UserId.setText(String.valueOf(customer.getId()));

    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }
    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView customerName;
        TextView dateCreate;
        TextView UserId;
        ImageView btnDelete;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.img_avatar);
            customerName = itemView.findViewById(R.id.Customer_name);
            UserId = itemView.findViewById(R.id.Customer_id);
            dateCreate = itemView.findViewById(R.id.dateCreate);
            btnDelete = itemView.findViewById(R.id.delete_user);

        }
    }
}
