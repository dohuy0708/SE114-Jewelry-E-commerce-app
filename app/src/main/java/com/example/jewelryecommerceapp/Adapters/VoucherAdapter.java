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
       holder.name.setText(voucher.getCode().toString());
        holder.discount.setText(formatNumber(voucher.getDiscount())+"đ");
        holder.from.setText(formatNumber(voucher.getInCase())+"đ");
        holder.start.setText(formatDateVoucher(voucher.getDateBegin().toString()));
        holder.end.setText(formatDateVoucher(voucher.getDateEnd().toString()));

    }

    @Override
    public int getItemCount() {
        if(voucherList==null)
            return 0;
        return voucherList.size();
    }

    public class VoucherViewHolder extends RecyclerView.ViewHolder {
        TextView name,discount,start,end,from;
        public VoucherViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name_voucher);
            discount=itemView.findViewById(R.id.discount_voucher);
            start=itemView.findViewById(R.id.start_day_voucher);
            end=itemView.findViewById(R.id.end_day_voucher);
            from=itemView.findViewById(R.id.from_voucher);
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
    String formatDateVoucher(String date){
        try {
            String[] a= date.split("-");
            return a[2]+"/"+a[1]+"/"+a[0];
        }
        catch (Exception e)
        {
            return date;
        }
    }
}
