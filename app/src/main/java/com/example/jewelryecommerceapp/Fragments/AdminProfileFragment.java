package com.example.jewelryecommerceapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.jewelryecommerceapp.Activities.AccountSercurityActivity;
import com.example.jewelryecommerceapp.Activities.EditProfileActivity;
import com.example.jewelryecommerceapp.R;

public class AdminProfileFragment extends Fragment {
    public  AdminProfileFragment() {}
    public static AdminProfileFragment newInstance(String param1, String param2) {
        AdminProfileFragment fragment = new AdminProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_profile, container, false);
    }
    Button btnAccountSercurity,btnCustomerManagement,btnEditProfile;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAccountSercurity = view.findViewById(R.id.account_sercurity_button);
        btnCustomerManagement = view.findViewById(R.id.customers_management_button);
        btnEditProfile = view.findViewById(R.id.edit_profile_admin);
        setupButton();
    }
    private void setupButton() {
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
        btnAccountSercurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountSercurityActivity.class);
                startActivity(intent);
            }
        });
    }
}