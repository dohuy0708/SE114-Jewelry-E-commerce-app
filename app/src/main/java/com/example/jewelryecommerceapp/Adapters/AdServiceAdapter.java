package com.example.jewelryecommerceapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jewelryecommerceapp.Models.AdService;
import com.example.jewelryecommerceapp.R;

import java.util.List;

import static java.lang.String.*;

public class AdServiceAdapter extends RecyclerView.Adapter<AdServiceAdapter.AdServiceViewHolder> {
    private Context context;
    private List<AdService> serviceList;
    private TextView totalValueTextView;
    private double totalValue = 0;



    public AdServiceAdapter(Context context, List<AdService> serviceList,TextView totalValueTextView) {
        this.context = context;
        this.serviceList = serviceList;
        this.totalValueTextView = totalValueTextView;
    }

    @NonNull
    @Override
    public AdServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_ad_service, parent, false);
        return new AdServiceViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdServiceViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (serviceList == null || serviceList.isEmpty()) {
            return;
        }
        AdService service = serviceList.get(position);
        holder.editTextServiceCode.setText(service.getServiceCode());
        holder.editTextServicePrice.setText(valueOf(service.getServicePrice()));
        holder.editTextServiceDescription.setText(service.getServiceDescription());
        holder.buttonServiceType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.buttonServiceType);

                popupMenu.getMenu().add("Mạ vàng");
                popupMenu.getMenu().add("Đánh bóng");

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        String selectedOption = item.getTitle().toString();
                        holder.buttonServiceType.setText(selectedOption);
                        return true;
                    }
                });

                // Hiển thị PopupMenu
                popupMenu.show();
            }
        });

        holder.editTextServicePrice.setKeyListener(DigitsKeyListener.getInstance(false, true));
        holder.editTextServicePrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String inputText = s.toString();
                if (inputText.length() > 1 && inputText.startsWith("0") && !inputText.startsWith("0.")) {
                    s.delete(0, 1);
                        AdService adService = serviceList.get(position);
                        adService.setServicePrice((int) Double.parseDouble(s.toString()));

                        Log.d("QWER","VALUE"+(int) Double.parseDouble(s.toString()));
                        Log.d("ABBCCCC","Value"+service.getServicePrice());
                        calculateTotalValue();
                    }
            }
        });

    };

    @Override
    public int getItemCount() {
        return serviceList == null ? 0 : serviceList.size();
    }


    public void addService(AdService service) {
        serviceList.add(service);
        Log.d("SERVICE", "SIZE" + serviceList.size());
        notifyItemInserted(serviceList.size() - 1);
        calculateTotalValue();
    }

    public double calculateTotalValue() {
        totalValue = 0;
        for (AdService service : serviceList) {
            totalValue += service.getServicePrice();
        }
        //Log.d("TotalValue", "Total value: " + totalValue);

        return totalValue;

    }

    class AdServiceViewHolder extends RecyclerView.ViewHolder {
        public Button buttonServiceType;
        public EditText editTextServiceCode;
        public EditText editTextServicePrice;
        public EditText editTextServiceDescription;

        public AdServiceViewHolder(View itemView) {
            super(itemView);
            buttonServiceType = itemView.findViewById(R.id.button_add);
            editTextServiceCode = itemView.findViewById(R.id.editText2);
            editTextServicePrice = itemView.findViewById(R.id.editText3);
            editTextServiceDescription = itemView.findViewById(R.id.editText4);
        }
    }
}
