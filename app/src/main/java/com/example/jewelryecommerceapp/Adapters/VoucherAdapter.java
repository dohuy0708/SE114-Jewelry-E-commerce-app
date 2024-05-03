package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Models.Voucher;
import com.example.jewelryecommerceapp.R;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder>{
    Context context;
    ArrayList<Voucher> voucherList;

    public VoucherAdapter(Context context, ArrayList<Voucher> voucherList) {
        this.context = context;
        this.voucherList = voucherList;
    }

    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_voucher,parent,false);
        return  new VoucherViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {
        Voucher voucher= voucherList.get(position);
        if (voucher==null)
            return;
        holder.name.setText(voucher.getName().toString());
        holder.percent.setText(voucher.getPercent()+"%");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        holder.start.setText(voucher.getStartDay().format(formatter));
        holder.end.setText(voucher.getEndDay().format(formatter));

    }

    @Override
    public int getItemCount() {
        if(voucherList==null)
            return 0;
        return voucherList.size();
    }

    public class VoucherViewHolder extends RecyclerView.ViewHolder {
        TextView name,percent,start,end;
        public VoucherViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_voucher);
            percent=itemView.findViewById(R.id.percent_voucher);
            start=itemView.findViewById(R.id.start_day_voucher);
            end=itemView.findViewById(R.id.end_day_voucher);
        }
    }

}
