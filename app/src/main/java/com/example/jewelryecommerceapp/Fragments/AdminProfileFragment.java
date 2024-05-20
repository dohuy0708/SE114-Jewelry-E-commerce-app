package com.example.jewelryecommerceapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.jewelryecommerceapp.Activities.*;
import com.example.jewelryecommerceapp.Models.User;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;
import org.jetbrains.annotations.NotNull;

public class AdminProfileFragment extends Fragment {

    Button btnAccountSercurity,btnCustomerManagement,btnEditProfile, btnSetting, btnSignOut;
    TextView tvAdname;
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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAccountSercurity = view.findViewById(R.id.account_sercurity_button);
        btnCustomerManagement = view.findViewById(R.id.customers_management_button);
        btnEditProfile = view.findViewById(R.id.edit_profile_admin);
        btnSetting = view.findViewById(R.id.setting_button);
        btnSignOut = view.findViewById(R.id.SignOut);
        tvAdname = view.findViewById(R.id.tv_adname);
        getUserImformation();
        setupButton();
    }
    private void setupButton() {

        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               FirebaseAuth.getInstance().signOut();
               Intent intent = new Intent(getActivity(), HomeActivity.class);
               startActivity(intent);
            }
        });
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
        btnCustomerManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((getActivity()), CustomerManagementActitvity.class);
                startActivity(intent);
            }
        });
    }
    private void getUserImformation()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String path = "User/"+user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(path);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                User user1 = dataSnapshot.getValue(User.class);
                tvAdname.setText(user1.getNAME());
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {

            }


        });

}}
