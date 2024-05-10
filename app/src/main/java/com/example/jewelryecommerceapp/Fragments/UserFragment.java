package com.example.jewelryecommerceapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jewelryecommerceapp.Activities.AccountSercurityActivity;
import com.example.jewelryecommerceapp.Activities.EditProfileActivity;
import com.example.jewelryecommerceapp.Activities.HomeActivity;
import com.example.jewelryecommerceapp.Activities.LoginActivity;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class UserFragment extends Fragment {

    Button btnEditProfile, btnOrderList, btnSignOut , btnAccountSercurity;
    TextView tvUserName;
    public UserFragment() {
        // Required empty public constructor
    }


    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
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
        return inflater.inflate(R.layout.fragment_user, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnOrderList = view.findViewById(R.id.orderlist);
        btnSignOut = view.findViewById(R.id.SignOut);
        btnEditProfile = view.findViewById(R.id.edit_profile);
        btnAccountSercurity = view.findViewById(R.id.account_sercurity);
        tvUserName = view.findViewById(R.id.tv_username);

        getUserImformation();
        setupButton();
    }

    private void setupButton()
    {
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((HomeActivity) getActivity()).ClickSignOut(new HomeFragment());
            }
        });
        btnOrderList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
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


    }
    private void getUserImformation()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null)
        {
            return;
        }
        String Name = user.getDisplayName();
        tvUserName.setText(Name);

    }

}
