package com.example.jewelryecommerceapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jewelryecommerceapp.Interfaces.ApiClient;
import com.example.jewelryecommerceapp.Interfaces.ApiService;
import com.example.jewelryecommerceapp.Interfaces.District;
import com.example.jewelryecommerceapp.Interfaces.DistrictResponse;
import com.example.jewelryecommerceapp.Interfaces.Province;
import com.example.jewelryecommerceapp.Interfaces.ProvinceResponse;
import com.example.jewelryecommerceapp.Interfaces.Ward;
import com.example.jewelryecommerceapp.Interfaces.WardResponse;
import com.example.jewelryecommerceapp.Models.Address;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Delivery_address extends AppCompatActivity {

    EditText name, phone, street, detail ;
    ImageView butback;
    private ApiService apiService;
    private Spinner spinnerProvince;
    private Spinner spinnerDistrict;
    private Spinner spinnerWard;
    private List<Province> provinces = new ArrayList<>();
    private List<District> districts = new ArrayList<>();
    private List<Ward> wards = new ArrayList<>();

    Button bt_finish;
    String userId;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);

        butback= findViewById(R.id.btn_comeback);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        street= findViewById(R.id.street);
        detail= findViewById(R.id.detail);
        bt_finish=findViewById(R.id.bt_finish);
        butback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // nút hoàn thành
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
                        userId = user.getUid();
                    }
                    Address address = new Address(userId,name.getText().toString(),(String)spinnerProvince.getSelectedItem(),(String)spinnerDistrict.getSelectedItem(),(String)spinnerWard.getSelectedItem(),street.getText().toString(),detail.getText().toString(),phone.getText().toString());
                    //
                    FirebaseDatabase data = FirebaseDatabase.getInstance();
                    DatabaseReference ref = data.getReference("Address");

                    // Đẩy đối tượng address lên Firebase
                    ref.push().setValue(address, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                            if(error!=null){
                                showToastWithIcon(R.drawable.fail_icon,"Thêm địa chỉ thất bại");
                            }
                            else
                            {
                                showToastWithIcon(R.drawable.succecss_icon,"Thêm địa chỉ thành công");
                                Intent intent = new Intent(Delivery_address.this, Payment.class);
                                intent.putExtra("address", address); // Truyền đối tượng Address
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }

            }
        });

        spinnerProvince = findViewById(R.id.spinner_province);
        spinnerDistrict = findViewById(R.id.spinner_district);
        spinnerWard = findViewById(R.id.spinner_ward);

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
        Toast toast = new Toast(Delivery_address.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
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

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Delivery_address.this,
                            android.R.layout.simple_spinner_item, provinceNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerProvince.setAdapter(adapter);
                } else {
                    // Xử lý khi không thành công
                    Toast.makeText(Delivery_address.this, "Failed to fetch provinces", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProvinceResponse> call, Throwable t) {
                // Xử lý khi gọi API thất bại
                Toast.makeText(Delivery_address.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Delivery_address.this,
                            android.R.layout.simple_spinner_item, districtNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerDistrict.setAdapter(adapter);
                } else {
                    // Xử lý khi không thành công
                    Toast.makeText(Delivery_address.this, "Failed to fetch districts", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DistrictResponse> call, Throwable t) {
                // Xử lý khi gọi API thất bại
                Toast.makeText(Delivery_address.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Delivery_address.this,
                            android.R.layout.simple_spinner_item, wardNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerWard.setAdapter(adapter);
                } else {
                    // Xử lý khi không thành công
                    Toast.makeText(Delivery_address.this, "Failed to fetch wards", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WardResponse> call, Throwable t) {
                // Xử lý khi gọi API thất bại
                Toast.makeText(Delivery_address.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}