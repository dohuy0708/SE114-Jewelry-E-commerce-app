package com.example.jewelryecommerceapp.Activities;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;
import com.example.jewelryecommerceapp.Models.Voucher;

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
        voucherList= new ArrayList<>();
        getVoucherFromDatabase();


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
        EditText name,discount,from,id,content;
        TextView sDay,eDay;
        sDay=view.findViewById(R.id.txt_sDay);
        eDay=view.findViewById(R.id.txt_eDay);
        id=view.findViewById(R.id.txt_id_voucher);
        name=view.findViewById(R.id.txt_name_voucher);
        content=view.findViewById(R.id.txt_content_voucher);
        discount=view.findViewById(R.id.txt_discount_voucher);
        from=view.findViewById(R.id.txt_from_voucher);
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
                if(id.getText().toString()==""){
                    Toast.makeText(VoucherActivity.this, "ID không hợp lệ", Toast.LENGTH_SHORT).show();
                    return;
                }

                int isName= isValidName(name.getText().toString());
                if (isName==1){
                    Toast.makeText(VoucherActivity.this, "Mã voucher phải chứa từ 4 kí tự trở lên!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isName==2){
                    Toast.makeText(VoucherActivity.this, "Mã voucher chỉ được chứa chữ cái và số!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(discount.getText().toString().equals("")||from.getText().toString().equals("")||content.getText().toString().equals(""))
                {
                    Toast.makeText(VoucherActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    if(Integer.parseInt(discount.getText().toString())>Integer.parseInt(from.getText().toString()))
                    {
                        Toast.makeText(VoucherActivity.this, "Giá giảm lớn hơn giá gốc", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                catch (Exception e){

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

               Voucher voucher= new Voucher(id.getText().toString(),name.getText().toString(),content.getText().toString(),Integer.parseInt(discount.getText().toString()),Integer.parseInt(from.getText().toString()),formatDateVoucher(startDay),formatDateVoucher(endDay));
               pushVoucherToDatabase(voucher);
               voucherList.add(0,voucher);
               voucherAdapter.notifyDataSetChanged();
            }
        });


        dialog.setContentView(view);
    }
    void pushVoucherToDatabase(Voucher voucher){
        FirebaseDatabase data = FirebaseDatabase.getInstance();
        DatabaseReference ref = data.getReference("Voucher");

        // Đẩy đối tượng Voucher lên Firebase
        ref.push().setValue(voucher, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                     Toast.makeText(VoucherActivity.this, "Thêm voucher thất bại", Toast.LENGTH_LONG).show();
                } else {
                    showToastWithIcon(R.drawable.succecss_icon,"Thêm voucher thành công");
            }
            }
        });

    }
    String formatDateVoucher(String date){
        try {
            String[] a= date.split("/");
            return a[2]+"-"+a[1]+"-"+a[0];
        }
        catch (Exception e)
        {
            return date;
        }
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
   void getVoucherFromDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Voucher")  ;
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Voucher voucher = dataSnapshot.getValue(Voucher.class);
                    voucherList.add(voucher);
                }
                voucherAdapter = new VoucherAdapter(VoucherActivity.this,voucherList);
                rc_voucher.setLayoutManager(new GridLayoutManager(VoucherActivity.this,2));
                rc_voucher.setHasFixedSize(true);
                rc_voucher.setAdapter(voucherAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void showToastWithIcon(int icon, String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        // Tùy chỉnh icon và văn bản trong toast
        ImageView imageView = layout.findViewById(R.id.toast_icon);
        imageView.setImageResource(icon); // Thay 'your_icon' bằng tên icon của bạn
        TextView textView = layout.findViewById(R.id.toast_text);
        textView.setText(message);

        // Tạo và hiển thị toast custom
        Toast toast = new Toast(VoucherActivity.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}