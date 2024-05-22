package com.example.jewelryecommerceapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jewelryecommerceapp.Adapters.CartPurchaseAdapter;
import com.example.jewelryecommerceapp.Adapters.OrdersAdapter;
import com.example.jewelryecommerceapp.Models.CartItem;
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

public class Order_detail extends AppCompatActivity {


    TextView add ,masp,status,total,voucher,totallord;
    RecyclerView rc_product;
    Button btn_review ;
    ImageView back;
    private LoadingDialog loadingDialog;
    CartPurchaseAdapter adt;
    ArrayList<CartItem> listpro;
    Order orderchoose = new Order();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        loadingDialog = new LoadingDialog(Order_detail.this);
        // khai báo UI
        initUI();
        // lấy intent
        Intent myintent =getIntent();
        String ID = myintent.getStringExtra("ID");
        Log.d("ID", ID);


        // init list + adapter
        listpro=new ArrayList<>();
        adt=new CartPurchaseAdapter(this, listpro);



        // Lấy dữ liệu trên firebase
        GetOrderFromFireBase(ID);


        // sự kiện
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chuyển tới đánh giá đơn hàng
            }
        });




    }

    private void GetOrderFromFireBase(String ID) {
        loadingDialog.show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Đơn hàng").child(userid);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {

                    Order order = dataSnapshot.getValue(Order.class);

                    if ( order!=null&& order.getOrderID().equals(ID))
                    {


                        orderchoose =order;
                        listpro = orderchoose.getListPurchaseProduct();


                        break;

                    }



                }
                adt.notifyDataSetChanged();

                SetUI();



            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        loadingDialog.cancel();

    }

    private void SetUI()
    {
        // dịa chỉ
        add.setText(orderchoose.getAddress().getFullName()+", sđt: "+orderchoose.getAddress().getPhoneNumber()+"\n"+orderchoose.getAddress().getStreet()+", "+orderchoose.getAddress().getWard()+", "+orderchoose.getAddress().getDistrict()+", "+orderchoose.getAddress().getProvince()+"\n"+"Ghi chú: "+orderchoose.getAddress().getDetail());
        //masp
        masp.setText(orderchoose.getOrderID());
        //status
        status.setText(orderchoose.getStatus());
        //total
        total.setText(formatNumber(orderchoose.getMoney()+orderchoose.getVoucher())+"");
        // totallord
        totallord.setText(formatNumber(orderchoose.getMoney())+"");
        // voucher
        voucher.setText(formatNumber(orderchoose.getVoucher())+"");
        // button
        if(orderchoose.getStatus().equals("Đã nhận"))
        {

            btn_review.setVisibility(View.VISIBLE);
        }



        //rcView
        adt=new CartPurchaseAdapter(this, listpro);
        adt.notifyDataSetChanged();
        Log.d("Size list", listpro.size()+"");
        rc_product.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rc_product.setHasFixedSize(true);
        rc_product.setAdapter(adt);


    }


    private void initUI() {
        add = findViewById(R.id.addd);
        masp= findViewById(R.id.masp);
        status= findViewById(R.id.statusdetail);
        total = findViewById(R.id.totalsp);
        voucher=findViewById(R.id.discountvoucher);
        totallord = findViewById(R.id.totalord);

        rc_product=findViewById(R.id.rc_product_list);
        btn_review=findViewById(R.id.btnreview);
        back = findViewById(R.id.btnbackkkdetail);
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
    public void showToastWithIcon(int icon, String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        // Tùy chỉnh icon và văn bản trong toast
        ImageView imageView = layout.findViewById(R.id.toast_icon);
        imageView.setImageResource(icon); // Thay 'your_icon' bằng tên icon của bạn
        TextView textView = layout.findViewById(R.id.toast_text);
        textView.setText(message);

        // Tạo và hiển thị toast custom
        Toast toast = new Toast(Order_detail.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}