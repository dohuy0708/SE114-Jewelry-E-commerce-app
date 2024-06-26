package com.example.jewelryecommerceapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jewelryecommerceapp.Adapters.CartPurchaseAdapter;
import com.example.jewelryecommerceapp.Adapters.OrdersAdapter;
import com.example.jewelryecommerceapp.Models.CartItem;
import com.example.jewelryecommerceapp.Models.Comment;
import com.example.jewelryecommerceapp.Models.Order;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.Models.User;
import com.example.jewelryecommerceapp.Models.Voucher;
import com.example.jewelryecommerceapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order_detail extends AppCompatActivity {


    TextView add ,masp,status,total,voucher,totallord;
    RecyclerView rc_product;
    Button btn_review ;
    ImageView back;
    private LoadingDialog loadingDialog;
    CartPurchaseAdapter adt;
    ArrayList<CartItem> listpro;
    BottomSheetDialog dialog;
    Order orderchoose = new Order();
    User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        loadingDialog = new LoadingDialog(Order_detail.this);
        btn_review= findViewById(R.id.btnreview);
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
                dialog= new BottomSheetDialog(Order_detail.this);
                createDialog();
                dialog.show();
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        });
    }
    void createDialog(){
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_review,null,false);
        TextView prd_name_review;
        RatingBar ratingBar;
        Button add_review;
        TextInputEditText content_review;
        prd_name_review= view.findViewById(R.id.prd_name_review);
        ratingBar= view.findViewById(R.id.ratingBar);
        add_review=view.findViewById(R.id.bt_add_review);
        content_review=view.findViewById(R.id.content_review);

        // prd_name_review : hiển thị tên của sản phẩm đánh giá
        // nút đăng comment
        add_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // số sao
                int rating =(int)ratingBar.getRating();
                // nội dung
                String content_comment=content_review.getText().toString();
                //
                AddCommentToFirebase(content_comment,rating);

            }
        });
        dialog.setContentView(view);
    }
    private void GetOrderFromFireBase(String ID) {
        loadingDialog.show();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userid = user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Đơn hàng");
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
    public void AddCommentToFirebase(String content,int rating){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid = firebaseUser.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("User");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userSnapshot:snapshot.getChildren()){
                    User user=userSnapshot.getValue(User.class);
                    if(user!=null&&user.getUID().equals(userid)){
                        Addcomment(user,content,rating);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void Addcomment(User user,String content,int rating){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("Comment");
        String productID = listpro.get(0).getProductID();
        String productType=listpro.get(0).getProductType();
        Comment comment=new Comment(user,productID,productType,rating,content);
        ref.push().setValue(comment, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    // Đã xảy ra lỗi khi đẩy dữ liệu lên Firebase

                    Toast.makeText(Order_detail.this, error.getMessage(), Toast.LENGTH_LONG).show();

                } else {
                    // Đẩy dữ liệu lên Firebase thành công
                    Toast.makeText(Order_detail.this, "Đã đánh giá", Toast.LENGTH_LONG).show();
                }
            }
        });
        ref = database.getReference("Product");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    for (DataSnapshot productSnapshot : categorySnapshot.getChildren()) {
                        Product product = productSnapshot.getValue(Product.class);

                        if(product.getProductId().equals(productID)&&product.getType().equals(productType))
                        {
                            Map<String, Object> updates = new HashMap<>();
                            updates.put("ratingAmount", product.getRatingAmount()+1);
                            updates.put("sold",product.getSold()+1);
                            productSnapshot.getRef().updateChildren(updates)
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            showToastWithIcon(R.drawable.succecss_icon,"Đã đánh giá thành công");
                                            btn_review.setEnabled(false);
                                            btn_review.setTextColor(Color.GRAY);
                                        } else {
                                                    }
                                    });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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