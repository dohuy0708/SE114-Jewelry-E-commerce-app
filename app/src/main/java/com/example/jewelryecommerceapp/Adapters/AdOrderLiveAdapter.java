package com.example.jewelryecommerceapp.Adapters;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jewelryecommerceapp.Models.AdOrderLive;
import com.example.jewelryecommerceapp.Models.AdService;
import com.example.jewelryecommerceapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static java.lang.String.valueOf;

public class AdOrderLiveAdapter extends RecyclerView.Adapter<AdOrderLiveAdapter.adOrderLiveViewHolder> {

    private List<AdOrderLive> orderLiveList;
    private Context context;
    private TextView totalValueTextView;
    private double totalValue = 0;


    public AdOrderLiveAdapter(Context context, List<AdOrderLive> orderLiveList, TextView totalValueTextView) {
        this.context = context;
        this.orderLiveList = orderLiveList;
        this.totalValueTextView = totalValueTextView;
    }

    @NonNull
    @Override
    public adOrderLiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.view_holder_admin_order_live, parent, false);
        return new adOrderLiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adOrderLiveViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (orderLiveList == null || orderLiveList.isEmpty()) {
            return;
        }
        AdOrderLive adOrderLive = orderLiveList.get(position);

        // Gán dữ liệu từ đối tượng OrderItem vào các TextView hoặc EditText
        holder.editTextName.setText(adOrderLive.getAdOrderName());
        holder.editTextSize.setText(valueOf(adOrderLive.getAdOrderSize()));
        holder.editTextPrice.setText(valueOf(adOrderLive.getAdOrderPrice()));
        holder.editTextDay.setText(adOrderLive.getAdOrderDate());
        holder.editTextNumber.setText(valueOf(adOrderLive.getAdOrderNumber()));
        holder.editTextCode.setText(adOrderLive.getAdOrderCode());

        holder.editTextSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String inputText = s.toString();
                if (!inputText.isEmpty()) {
                    if (!inputText.matches("^\\d+$")) {
                        s.delete(s.length() - 1, s.length());
                        return;
                    }
                    if (inputText.length() > 1 && inputText.startsWith("0") && !inputText.equals("0")) {
                        s.delete(0, 1);
                    }
                }
            }
        });
        holder.editTextPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String inputText = s.toString();
                if (!inputText.isEmpty()) {
                    if (!inputText.matches("^\\d+$")) {
                        s.delete(s.length() - 1, s.length());
                        return;
                    }
                    if (inputText.length() > 1 && inputText.startsWith("0") && !inputText.equals("0")) {
                        s.delete(0, 1);
                    }
                }
                AdOrderLive adOrderLive = orderLiveList.get(position);
                adOrderLive.setAdOrderPrice((int) Double.parseDouble(s.toString()));
                calculateTotalValue();
            }
        });
        holder.editTextNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String inputText = s.toString();
                if (!inputText.isEmpty()) {
                    if (!inputText.matches("^\\d+$")) {
                        s.delete(s.length() - 1, s.length());
                        return;
                    }
                    if (inputText.length() > 1 && inputText.startsWith("0") && !inputText.equals("0")) {
                        s.delete(0, 1);
                    }
                }

            }
        });
    }

    public void addOrderLive(AdOrderLive adOrderLive) {
        orderLiveList.add(adOrderLive);
        notifyItemInserted(orderLiveList.size() - 1);

        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = dateFormat.format(calendar.getTime());
        adOrderLive.setAdOrderDate(currentDate);
        calculateTotalValue();
    }

    public double calculateTotalValue() {
        totalValue = 0;
        for (AdOrderLive orderLive : orderLiveList) {
            totalValue += orderLive.getAdOrderPrice();
        }
        totalValueTextView.setText(String.valueOf(totalValue));

        return totalValue;

    }



    @Override
    public int getItemCount() {
        return orderLiveList.size();
    }

    public class adOrderLiveViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName, textViewSize, textViewPrice, textViewDay, textViewNumber, textViewCode;
        public EditText editTextName, editTextSize, editTextPrice, editTextDay, editTextNumber, editTextCode;
        public ImageButton buttonCalendar;
        public adOrderLiveViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewSize = itemView.findViewById(R.id.textViewSize);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewDay = itemView.findViewById(R.id.textViewDay);
            textViewNumber = itemView.findViewById(R.id.textViewNumber);
            textViewCode = itemView.findViewById(R.id.textViewCode);
            editTextName = itemView.findViewById(R.id.editTextName);
            editTextSize = itemView.findViewById(R.id.editTextSize);
            editTextPrice = itemView.findViewById(R.id.editTextPrice);
            editTextDay = itemView.findViewById(R.id.editTextDay);
            editTextNumber = itemView.findViewById(R.id.editTextNumber);
            editTextCode = itemView.findViewById(R.id.editTextCode);
            buttonCalendar = itemView.findViewById(R.id.imageButtonCalendar);

            buttonCalendar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showCalendarDialog(editTextDay);
                }
            });
        }

        private void showCalendarDialog(final EditText editText) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    context,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                            String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year; // Tháng trong Java bắt đầu từ 0
                            editText.setText(selectedDate);
                        }
                    },
                    year,
                    month,
                    dayOfMonth
            );

            datePickerDialog.show();
        }
    }
}
