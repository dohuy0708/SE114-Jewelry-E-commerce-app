package com.example.jewelryecommerceapp.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jewelryecommerceapp.Adapters.AdServiceAdapter;
import com.example.jewelryecommerceapp.Fragments.ServiceFragment;
import com.example.jewelryecommerceapp.Models.AdService;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private Button addButton;

    private Button buttonServiceType;
    private AdServiceAdapter adapter;
    private List<AdService> serviceItemList;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_service);


            recyclerView = findViewById(R.id.recyclerview2);
            addButton = findViewById(R.id.button8);

            serviceItemList = new ArrayList<>();
            adapter = new AdServiceAdapter(serviceItemList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tạo một ServiceItem mới hoặc tùy chỉnh theo yêu cầu của bạn
                    AdService newItem = new AdService();

                    // Thêm ServiceItem mới vào danh sách dữ liệu
                    //serviceItemList.add(newItem);

                    // Thông báo cho adapter về việc thay đổi trong danh sách dữ liệu
                    //adapter.notifyDataSetChanged();
                    adapter.addService(newItem);
                }


            });




        }



}
