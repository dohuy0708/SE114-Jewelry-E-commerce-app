package com.example.jewelryecommerceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Adapters.CartProductsAdapter;
import com.example.jewelryecommerceapp.Adapters.CartPurchaseAdapter;
import com.example.jewelryecommerceapp.Models.CartItem;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.Models.Voucher;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {

    ImageView back;
    RecyclerView pross;
    private LoadingDialog loadingDialog;
    CartPurchaseAdapter adt;
    ArrayList<CartItem> listpro;
    TextView add;

    TextView totalpro;
    TextView totalord, discount;
    Button pay , appVoucher;
    Button setaddr;
    EditText promotee;
    Spinner choosepay;
    ImageView statusvoucher;

    String userid;

    int checkvoucher = 0;
    int VoucherSale =0;
    int totalprice =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        EdgeToEdge.enable(this);
        loadingDialog = new LoadingDialog(Payment.this);

        Intent myintent  = getIntent();
        int type = myintent.getIntExtra("from",-1);
         totalprice = myintent.getIntExtra("Total",0);
        String productID = myintent.getStringExtra("productID");
        Log.d("ID",productID+"");

        String productType = myintent.getStringExtra("productType");
        Log.d("Type",productType+" ");
        String Size = myintent.getStringExtra("Size");
        Log.d("Type",Size+" ");
        int amount  = myintent.getIntExtra("Amount",1);
        Log.d("Type",amount+" ");



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pross=findViewById(R.id.ttpros);
        listpro=new ArrayList<>();
        adt=new CartPurchaseAdapter(this, listpro);
      //  initListPro(listpro);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userid=user.getUid();

        if(type==2)// mua tu giở hàng
        {
            if (user == null) {// chưa có người dùng
                // showToastWithIcon(R.drawable.attention_icon,"Bạn cần Đăng nhập để xem Giỏ hàng!");

            } else {

                String userID = user.getUid();
                GetPurchaseProduct(userID);

            }
        }
        else// mua ngay , có thể không đăng nhập
        {
                GetProductBuyNow(productID,productType,Size,amount);


        }






        back=findViewById(R.id.btnbackkk);
        statusvoucher= findViewById(R.id.statusvoucher);
        pay=findViewById(R.id.btnpay);
        setaddr=findViewById(R.id.btnadd);
        add=findViewById(R.id.addd);
        appVoucher= findViewById(R.id.apply);
        totalpro=findViewById(R.id.totalp);
        totalord=findViewById(R.id.totalord);
        promotee = findViewById(R.id.inputvoucher);
        choosepay=findViewById(R.id.spn);
        discount= findViewById(R.id.discountvoucher);


        ArrayAdapter<CharSequence> adapterr=ArrayAdapter.createFromResource(this, R.array.items_array, android.R.layout.simple_spinner_item);
        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choosepay.setAdapter(adapterr);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedWay=choosepay.getSelectedItem().toString();
                if(selectedWay=="Thanh toán khi nhận hàng")
                {

                }
                if(selectedWay=="Chuyển khoản qua ngân hàng")
                {

                }
            }
        });
        setaddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        appVoucher.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                CheckVoucherInFireBase(totalprice,promotee.getText().toString());


            }
        });


    }

    private void GetProductBuyNow(String productID, String productType,String Size, int amount) {
        loadingDialog.show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product").child(productType);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Product product =  dataSnapshot.getValue(Product.class);
                    if( product.getProductId().equals(productID))
                    {
                        CartItem item = new CartItem(userid,productID,productType,amount,Size,product.getImagelist().get(0),product.getProductName(),product.getProductPrice());
                        listpro.add(item);
                        totalprice+= product.getProductPrice()*amount;


                    }
                }
                adt.notifyDataSetChanged();
                SetUI();
                loadingDialog.cancel();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loadingDialog.cancel();
             }
        });
    }

    private void CheckVoucherInFireBase(int totalprice, String code) {

        loadingDialog.show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Voucher") ;

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // DataSnapshot là tổng các Product , chứa các item trong đó, khi getChildren() , thì ta sẽ lấy từng item  .

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Voucher voucher = dataSnapshot.getValue(Voucher.class);
                     if (voucher.getCode().equals(code)) {

                         if (totalprice > voucher.getInCase()) {
                            checkvoucher = 1;
                            VoucherSale = voucher.getDiscount();
                            break;

                        }
                    }
                }
                if( checkvoucher ==1)
                {
                    statusvoucher.setImageResource(R.drawable.succecss_icon);

                    totalord.setText(formatNumber(totalprice-VoucherSale)+ "VND");
                    showToastWithIcon(R.drawable.succecss_icon,"Áp dụng voucher thành công!");
                    discount.setText(formatNumber(VoucherSale)+"VND");

                }
                else
                {
                    statusvoucher.setImageResource(R.drawable.fail_icon);
                    showToastWithIcon(R.drawable.fail_icon,"Voucher không hợp lệ!");
                }

                loadingDialog.cancel();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //   Toast.makeText(getActivity(),"Fail",Toast.LENGTH_LONG).show();
                loadingDialog.cancel();
            }
        });

    }

    private void GetPurchaseProduct(String userID) {
        loadingDialog.show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Cart").child(userID);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // DataSnapshot là tổng các Product , chứa các item trong đó, khi getChildren() , thì ta sẽ lấy từng item  .
                listpro.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CartItem item = dataSnapshot.getValue(CartItem.class);
                     if(item.getIsChoose()==1)
                    {

                        listpro.add(item);


                    }

                }
                adt.notifyDataSetChanged();
                SetUI();
                loadingDialog.cancel();


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //   Toast.makeText(getActivity(),"Fail",Toast.LENGTH_LONG).show();
                loadingDialog.cancel();
            }
        });


    }

    private void SetUI() {



        pross.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        pross.setHasFixedSize(true);
        pross.setAdapter(adt);
        totalpro.setText( formatNumber(totalprice)+ "VND");
        totalord.setText(formatNumber(totalprice-VoucherSale)+ "VND");



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
        Toast toast = new Toast(Payment.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }


}