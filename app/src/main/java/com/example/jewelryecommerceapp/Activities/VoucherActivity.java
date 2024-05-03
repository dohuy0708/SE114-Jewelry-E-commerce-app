package com.example.jewelryecommerceapp.Activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Adapters.VoucherAdapter;
import com.example.jewelryecommerceapp.Models.Voucher;
import com.example.jewelryecommerceapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

@RequiresApi(api = Build.VERSION_CODES.O)

public class VoucherActivity extends AppCompatActivity {

    RecyclerView rc_voucher;
    VoucherAdapter voucherAdapter;
    ArrayList<Voucher> voucherList;
    BottomSheetDialog dialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_voucher);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        
        rc_voucher=findViewById(R.id.rc_voucher);
        voucherList= initList(voucherList);
        voucherAdapter = new VoucherAdapter(this,voucherList);
        rc_voucher.setLayoutManager(new GridLayoutManager(this,2));
        rc_voucher.setHasFixedSize(true);
        rc_voucher.setAdapter(voucherAdapter);

        findViewById(R.id.add_voucher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // thêm voucher
                dialog= new BottomSheetDialog(VoucherActivity.this);
                createDialog();
                dialog.show();
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        });
        
    }
    // hiển thị view để thêm voucher
    @SuppressLint("MissingInflatedId")
    void createDialog(){
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_voucher,null,false);
        EditText name,percent;
        TextView sDay,eDay;
        sDay=view.findViewById(R.id.txt_sDay);
        eDay=view.findViewById(R.id.txt_eDay);
        name=view.findViewById(R.id.txt_name_voucher);
        percent=view.findViewById(R.id.txt_percent_voucher);
        // ngàu bắt đầu
        view.findViewById(R.id.bt_sDay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDate(sDay);
            }
        });
        // ngày kết thúc
        view.findViewById(R.id.bt_eDay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDate(eDay);
            }
        });
        // thêm
        view.findViewById(R.id.bt_add_voucher).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // kiểm tra thông tin voucher có hợp lệ không
                int isName= isValidName(name.getText().toString());
                if (isName==1){
                    Toast.makeText(VoucherActivity.this, "Mã voucher phải chứa từ 4 kí tự trở lên!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isName==2){
                    Toast.makeText(VoucherActivity.this, "Mã voucher chỉ được chứa chữ cái và số!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!isValidPercent(percent.getText().toString()))
                {
                    Toast.makeText(VoucherActivity.this, "Phần trăm giảm không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }
                String startDay=sDay.getText().toString();
                String endDay = eDay.getText().toString();

                if (startDay.equals("dd/MM/yyyy"))
                {
                    Toast.makeText(VoucherActivity.this, "Vui lòng chọn ngày bắt đầu", Toast.LENGTH_SHORT).show();
                    return;
                }
               if (endDay.equals("dd/MM/yyyy"))
                {
                    Toast.makeText(VoucherActivity.this, "Vui lòng chọn ngày kết thúc", Toast.LENGTH_SHORT).show();
                    return;
                }
               DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
               LocalDate currentDate = LocalDate.now();
               LocalDate startDate = LocalDate.parse(startDay, dateFormatter);
               LocalDate endDate = LocalDate.parse(endDay, dateFormatter);
               if (startDate.isBefore(currentDate)) {
                   // Ngày start nhỏ hơn ngày hiện tại
                   Toast.makeText(VoucherActivity.this, "Ngày bắt đầu nhỏ hơn ngày hiện tại!", Toast.LENGTH_SHORT).show();
                   return;
               }
               if (endDate.isBefore(startDate)) {
                   // Ngày end nhỏ hơn ngày star
                   Toast.makeText(VoucherActivity.this, "Ngày kết thúc nhỏ hơn ngày bắt đầu.", Toast.LENGTH_SHORT).show();
                   return;
               }
               // các điều kiện đều thỏa mãn
                Toast.makeText(VoucherActivity.this, "Thêm hoàn tất", Toast.LENGTH_SHORT).show();

               voucherList.add(0,new Voucher(name.getText().toString(),Double.parseDouble(percent.getText().toString()),startDate,endDate));
               voucherAdapter.notifyDataSetChanged();
            }
        });


        dialog.setContentView(view);
    }
    void  openDate(TextView textView){
        Calendar calendar=Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month= calendar.get(Calendar.MONTH);
        int day= calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textView.setText(formatNumber(dayOfMonth)+"/"+formatNumber(month+1)+"/"+String.valueOf(year));
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    int isValidName(String name){
        if(name==null||name.length()<4)
            return 1;
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            // Kiểm tra nếu ký tự không phải là chữ cái hoặc số
            if (!Character.isLetter(ch) && !Character.isDigit(ch)) {
                return 2; // Nếu có ký tự không hợp lệ, trả về false
            }
        }
        return 0;
    }
    boolean isValidPercent(String percent){
        try {
            if (percent==null)return  false;
            // Chuyển đổi chuỗi thành số thực
            double number = Double.parseDouble(percent);
            // Nếu chuyển đổi thành công, đây là số thực
            if(number<=0||number>100)
                return false;
            return true;
        } catch (NumberFormatException e) {
            // Nếu có lỗi trong quá trình chuyển đổi, đây không phải là số thực
            return false;
        }
    }
    String formatNumber(int a){
        if (a<10)
            return "0"+String.valueOf(a);
        return String.valueOf(a);
    }
    ArrayList<Voucher> initList(ArrayList<Voucher> list){
        list= new ArrayList<>();
        LocalDate sDate= LocalDate.now();
        LocalDate eDate= sDate.plusDays(10);
        list.add(new Voucher("CHAOMUNG30T4",3.4,sDate,eDate));
        list.add(new Voucher("CHAOMUNG30T4",3.4,sDate,eDate));
        list.add(new Voucher("CHAOMUNG30T4",3.4,sDate,eDate));
        list.add(new Voucher("CHAOMUNG30T4",3.4,sDate,eDate));
        list.add(new Voucher("CHAOMUNG30T4",3.4,sDate,eDate));
        return list;
    }
}