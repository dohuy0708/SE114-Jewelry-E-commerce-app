package com.example.jewelryecommerceapp.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Adapters.CartProductsAdapter;
import com.example.jewelryecommerceapp.Adapters.CartPurchaseAdapter;
import com.example.jewelryecommerceapp.Interfaces.ApiClient;
import com.example.jewelryecommerceapp.Interfaces.ApiService;
import com.example.jewelryecommerceapp.Interfaces.District;
import com.example.jewelryecommerceapp.Interfaces.DistrictResponse;
import com.example.jewelryecommerceapp.Interfaces.Province;
import com.example.jewelryecommerceapp.Interfaces.ProvinceResponse;
import com.example.jewelryecommerceapp.Interfaces.Ward;
import com.example.jewelryecommerceapp.Interfaces.WardResponse;
import com.example.jewelryecommerceapp.Models.Address;
import com.example.jewelryecommerceapp.Models.CartItem;
import com.example.jewelryecommerceapp.Models.Order;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.Models.Voucher;
import com.example.jewelryecommerceapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    BottomSheetDialog dialog;
    String userid="";
    ApiService apiService;
    Spinner spinnerProvince;
    Spinner spinnerDistrict;
    Spinner spinnerWard;
    Address Address = new Address();
    List<Province> provinces = new ArrayList<>();
    List<District> districts = new ArrayList<>();
    List<Ward> wards = new ArrayList<>();
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
        if(user!=null) {
            userid = user.getUid();
            // tìm thử có thông tin địa chỉ chưa
            GetAddressFromFirebase(userid);
        }
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

        // Đặt hàng
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Kiểm tra đã có địa chỉ giao hàng chưa, địa chỉ phải đầy đủ thông tin

                // nếu có rồi thì cho đặt
                // nếu getcurrent user khác null thì lấy địa chỉ có user ID bằng user ID này bỏ vô
                // lấy thông tin giao hàng đã lưu trước đó
                // nếu chưa đăng nhập

                if( add.getText().toString().equals("Chưa có địa chỉ giao hàng"))
                {
                    showToastWithIcon(R.drawable.attention_icon, "Vui lòng thêm thông tin giao hàng!");

                }
                else {
                    GetDialog();
                }

            }
        });
        setaddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog= new BottomSheetDialog(Payment.this);
                createDialog();
                dialog.show();
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

            }
        });
        appVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckVoucherInFireBase(totalprice,promotee.getText().toString());
            }
        });


    }

    private void GetAddressFromFirebase(String userid) {
        loadingDialog.show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Address");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // Kiểm tra xem key của dataSnapshot có khớp với userid không
                    if (dataSnapshot.getKey().equals(userid)) {
                        Address address = dataSnapshot.getValue(Address.class);
                        Log.d("tìm địa chỉ","tìm");
                        if (address != null) {
                            add.setText(address.getFullName() + ", sđt: " + address.getPhoneNumber() + "\n" +
                                    address.getStreet() + ", " + address.getWard() + ", " +
                                    address.getDistrict() + ", " + address.getProvince() + "\n" +
                                    "Ghi chú: " + address.getDetail());


                            // sửa tên nút thêmthanfnhf sửa
                            setaddr.setText("SỬA");
                            Address=address;
                        }



                        break; // Dừng vòng lặp nếu đã tìm thấy địa chỉ phù hợp
                    }
                }

                loadingDialog.cancel();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loadingDialog.cancel();
            }
        });


    }

    void createDialog(){
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_address,null,false);
        EditText name, phone, street, detail ;
        Button bt_finish;
        name = view.findViewById(R.id.name);
        phone = view.findViewById(R.id.phone);
        street= view.findViewById(R.id.street);
        detail= view.findViewById(R.id.detail);
        bt_finish=view.findViewById(R.id.bt_finish);
        spinnerProvince = view.findViewById(R.id.spinner_province);
        spinnerDistrict = view.findViewById(R.id.spinner_district);
        spinnerWard = view.findViewById(R.id.spinner_ward);
        apiService = ApiClient.getClient().create(ApiService.class);

        // Điền dữ liệu vào Spinner tỉnh
        populateProvinceSpinner();

        // Xử lý sự kiện khi chọn tỉnh từ Spinner
        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Lấy tỉnh được chọn từ Spinner
                Province selectedProvince = provinces.get(position);

                // Gọi API để lấy danh sách quận huyện của tỉnh đã chọn
                getDistrictsByProvince(selectedProvince.getProvinceId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có tỉnh nào được chọn
            }
        });
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Lấy quận huyện được chọn từ Spinner
                District selectedDistrict = districts.get(position);

                // Gọi API để lấy danh sách phường/xã của quận huyện đã chọn
                getWardsByDistrict(selectedDistrict.getDistrictId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có quận huyện nào được chọn
            }
        });
        bt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().equals("")||phone.getText().equals("")||street.getText().equals(""))
                {
                    showToastWithIcon(R.drawable.attention_icon,"Vui lòng nhập đâ đủ thông tin");
                }
                else {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user!=null) {
                        userid = user.getUid();
                        Address address = new Address(userid,name.getText().toString(),(String)spinnerProvince.getSelectedItem(),(String)spinnerDistrict.getSelectedItem(),(String)spinnerWard.getSelectedItem(),street.getText().toString(),detail.getText().toString(),phone.getText().toString());
                        //
                        Address = address;
                        FirebaseDatabase data = FirebaseDatabase.getInstance();
                        DatabaseReference ref = data.getReference("Address");

                        // Đẩy đối tượng address lên Firebase
                        ref.child(userid).setValue(address, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                if(error!=null){
                                    showToastWithIcon(R.drawable.fail_icon,"Thêm địa chỉ thất bại");
                                }
                                else
                                {
                                    showToastWithIcon(R.drawable.succecss_icon,"Thêm địa chỉ thành công");
                                    add.setText(address.getFullName()+", sđt: "+address.getPhoneNumber()+"\n"+address.getStreet()+", "+address.getWard()+", "+address.getDistrict()+", "+address.getProvince()+"\n"+"Ghi chú: "+address.getDetail());
                                }
                            }
                        });
                    }
                    else {
                        Address address = new Address(userid,name.getText().toString(),(String)spinnerProvince.getSelectedItem(),(String)spinnerDistrict.getSelectedItem(),(String)spinnerWard.getSelectedItem(),street.getText().toString(),detail.getText().toString(),phone.getText().toString());
                        showToastWithIcon(R.drawable.succecss_icon,"Thêm địa chỉ thành công");
                        add.setText(address.getFullName()+", sđt: "+address.getPhoneNumber()+"\n"+address.getStreet()+", "+address.getWard()+", "+address.getDistrict()+", "+address.getProvince()+"\n"+"Ghi chú: "+address.getDetail());
                        Address = address;
                    }


                }

            }
        });
        dialog.setContentView(view);
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
    private void DeleteChosenProducts(String userID) {
        loadingDialog.show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Cart").child(userID);
        Log.d("xóa","chạy");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CartItem item = dataSnapshot.getValue(CartItem.class);
                    if (item != null && item.getIsChoose() == 1) {
                        dataSnapshot.getRef().removeValue();
                    }
                }
                loadingDialog.cancel();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                loadingDialog.cancel();
                // Handle the error
            }
        });
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
    void GetDialog()
    {
        AlertDialog.Builder mydialog = new AlertDialog.Builder(Payment.this);
        mydialog.setTitle("Thông báo!");
        mydialog.setMessage("Xác nhận đặt đơn hàng");
        mydialog.setIcon(R.drawable.ic_alarm);
        mydialog.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //showToastWithIcon(R.drawable.succecss_icon,"Đặt hàng thành công, đơn hàng đang được xử lý.");

                // xóa các sản phẩm đã chọn ra khỏi firebase
                // DeleteChosenProducts(userid);
                // tạo 1 đơn hàng leen firebaseO
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null) {
                    userid = user.getUid();
                    // lấy ra ngày hiện tại
                    String currentdate = getCurrentDateString();
                    // lấy ra tổng tiền
                    String code = generateRandomString();
                    Order order = new Order(code,VoucherSale,totalprice,currentdate,"Đang xử lý",Address,listpro);
                    FirebaseDatabase data = FirebaseDatabase.getInstance();
                    DatabaseReference ref = data.getReference("Đơn hàng");

                    // Đẩy đối tượng address lên Firebase
                    ref.child(userid).push().setValue(order, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            if(error!=null){

                            }
                            else
                            {
                                showToastWithIcon(R.drawable.succecss_icon,"Đặt hàng thành công, đơn hàng đang được xử lý.");

                            }
                        }
                    });
                    //
                }
                else {
                    showToastWithIcon(R.drawable.succecss_icon,"Đơn hàng đã được đặt, vui lòng chờ cửa hàng liên hệ để xác nhận!");
                }

                // trở lại màn hình home

                finish();
                dialog.dismiss();
            }
        });
        mydialog.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        mydialog.create().show();
    }




    public String getCurrentDateString() {
        // Định dạng ngày theo ý muốn, ví dụ: "dd/MM/yyyy"
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        // Lấy ngày hiện tại
        Date currentDate = new Date();
        // Chuyển đổi ngày hiện tại thành chuỗi
        return sdf.format(currentDate);
    }
    private void populateProvinceSpinner() {
        // Gọi API để lấy danh sách tỉnh
        apiService.getProvinces().enqueue(new Callback<ProvinceResponse>() {
            @Override
            public void onResponse(Call<ProvinceResponse> call, Response<ProvinceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    provinces = response.body().getResults();

                    List<String> provinceNames = new ArrayList<>();
                    for (Province province : provinces) {
                        provinceNames.add(province.getProvinceName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Payment.this,
                            android.R.layout.simple_spinner_item, provinceNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerProvince.setAdapter(adapter);
                } else {
                    // Xử lý khi không thành công
                    Toast.makeText(Payment.this, "Failed to fetch provinces", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProvinceResponse> call, Throwable t) {
                // Xử lý khi gọi API thất bại
                Toast.makeText(Payment.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getDistrictsByProvince(int provinceId) {
        // Gọi API để lấy danh sách quận huyện của tỉnh với provinceId đã chọn
        apiService.getDistrictsByProvince(provinceId).enqueue(new Callback<DistrictResponse>() {
            @Override
            public void onResponse(Call<DistrictResponse> call, Response<DistrictResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    districts = response.body().getResults();

                    List<String> districtNames = new ArrayList<>();
                    for (District district : districts) {
                        districtNames.add(district.getDistrictName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Payment.this,
                            android.R.layout.simple_spinner_item, districtNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerDistrict.setAdapter(adapter);
                } else {
                    // Xử lý khi không thành công
                    Toast.makeText(Payment.this, "Failed to fetch districts", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DistrictResponse> call, Throwable t) {
                // Xử lý khi gọi API thất bại
                Toast.makeText(Payment.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getWardsByDistrict(int districtId) {
        // Gọi API để lấy danh sách phường/xã của quận huyện với districtId đã chọn
        apiService.getWardsByDistrict(districtId).enqueue(new Callback<WardResponse>() {
            @Override
            public void onResponse(Call<WardResponse> call, Response<WardResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    wards = response.body().getResults();

                    List<String> wardNames = new ArrayList<>();
                    for (Ward ward : wards) {
                        wardNames.add(ward.getWardName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Payment.this,
                            android.R.layout.simple_spinner_item, wardNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerWard.setAdapter(adapter);
                } else {
                    // Xử lý khi không thành công
                    Toast.makeText(Payment.this, "Failed to fetch wards", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WardResponse> call, Throwable t) {
                // Xử lý khi gọi API thất bại
                Toast.makeText(Payment.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public char getRandomLetter() {
        Random random = new Random();
        return (char) ('A' + random.nextInt(26));
    }

    public char getRandomDigit() {
        Random random = new Random();
        return (char) ('0' + random.nextInt(10));
    }

    public String generateRandomString() {
        StringBuilder randomString = new StringBuilder();

        // Tạo 4 chữ cái ngẫu nhiên
        for (int i = 0; i < 4; i++) {
            randomString.append(getRandomLetter());
        }

        // Tạo 4 chữ số ngẫu nhiên
        for (int i = 0; i < 4; i++) {
            randomString.append(getRandomDigit());
        }

        return randomString.toString();
    }

}