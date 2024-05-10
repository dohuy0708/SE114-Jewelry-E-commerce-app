package com.example.jewelryecommerceapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jewelryecommerceapp.R;

public class ServiceFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate layout for this fragment
        View view = inflater.inflate(R.layout.activity_service, container, false);

        // Ánh xạ các thành phần giao diện từ layout XML
        Button button = view.findViewById(R.id.button8);
        // Thêm logic xử lý sự kiện cho button nếu cần

        return view;
    }
}
