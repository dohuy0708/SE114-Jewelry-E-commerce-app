package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Models.Service;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class ServiceAdapter  extends RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>{

    ArrayList<Service> svList;
    Context context;

    public ServiceAdapter( Context context, ArrayList<Service> svList) {
        this.svList = svList;
        this.context = context;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_service,parent,false);

        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        Service sv= svList.get(position);
        holder.img_service.setImageResource(sv.getSvImg());
        holder.name_service.setText(sv.getSvName());
        holder.amount_service.setText("Đã đặt: "+sv.getSvNumber());
    }

    @Override
    public int getItemCount() {
        if (svList==null)
            return 0;
        return svList.size();
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder {
        ImageView img_service;
        TextView name_service, amount_service;
        public ServiceViewHolder(@NonNull View itemView) {
            super(itemView);
            img_service=itemView.findViewById(R.id.img_service);
            name_service=itemView.findViewById(R.id.name_service);
            amount_service=itemView.findViewById(R.id.amount_service);
        }
    }
}
