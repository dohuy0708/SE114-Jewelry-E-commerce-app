package com.example.jewelryecommerceapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jewelryecommerceapp.Models.AdService;
import com.example.jewelryecommerceapp.R;
import com.example.jewelryecommerceapp.ViewHolder.AdServiceViewHolder;
import java.util.List;

public class AdServiceAdapter extends RecyclerView.Adapter<AdServiceViewHolder> {
    private Context context;
    private List<AdService> serviceList;



    public AdServiceAdapter(List<AdService> serviceList) {
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public AdServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_ad_service, parent, false);
        return new AdServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdServiceViewHolder holder, int position) {
        if (serviceList == null || serviceList.isEmpty()) {
            return;
        }
        AdService service = serviceList.get(position);



        // Hiển thị thông tin của dịch vụ
        /*holder.textView1.setText("Loại dịch vụ: " + service.getServiceType());
        holder.textView2.setText("Mã dịch vụ: " + service.getServiceCode());
        holder.textView3.setText("Giá dịch vụ: " + String.valueOf(service.getServicePrice()));
        holder.textView4.setText("Mô tả dịch vụ: " + service.getServiceDescription());*/

        /*//holder.editText1.setText(service.getServiceType());
        //holder.editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] serviceTypes = {"Mạ vàng", "Đánh bóng"};

                // Tạo một AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Chọn loại dịch vụ");

                // Thiết lập danh sách các loại dịch vụ trong AlertDialog
*//*
                builder.setItems(serviceTypes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý khi người dùng chọn một loại dịch vụ
                        String selectedServiceType = serviceTypes[which];
                        // Update text cho nút "Chọn loại dịch vụ"
                        holder.editText1.setText(selectedServiceType);
                    }

                });
*//**//*
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        // Điền thông tin vào các EditText

            }*//*
        //});
        holder.editText2.setText(service.getServiceCode());
        holder.editText3.setText(String.valueOf(service.getServicePrice()));
        holder.editText4.setText(service.getServiceDescription());*/
    };

    @Override
    public int getItemCount() {
        return serviceList == null ? 0 : serviceList.size();
    }


    public void addService(AdService service) {
        serviceList.add(service);
        notifyItemInserted(serviceList.size() - 1);
    }

    
}
