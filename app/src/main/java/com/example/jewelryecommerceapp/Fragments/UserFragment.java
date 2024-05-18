package com.example.jewelryecommerceapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

<<<<<<<<< Temporary merge branch 1
=========
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
>>>>>>>>> Temporary merge branch 2
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

<<<<<<<<< Temporary merge branch 1
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;
=========
import com.bumptech.glide.Glide;
>>>>>>>>> Temporary merge branch 2
import com.example.jewelryecommerceapp.Activities.AccountSercurityActivity;
import com.example.jewelryecommerceapp.Activities.AddressBookActivity;
import com.example.jewelryecommerceapp.Activities.EditProfileActivity;
import com.example.jewelryecommerceapp.Activities.HomeActivity;
import com.example.jewelryecommerceapp.Adapters.ViewPagerAdapter;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.auth.FirebaseAuth;

import static android.content.Intent.getIntent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {

<<<<<<<<< Temporary merge branch 1

=========
    Button btnEditProfile, btnOrderList, btnSignOut , btnAccountSercurity, btnHelp;
    TextView tvUserName;
    ImageView avatar;
>>>>>>>>> Temporary merge branch 2
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
    Button btnEditProfile, btnOrderList, btnSignOut , btnAccountSercurity, btnAddressBook;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnOrderList = view.findViewById(R.id.orderlist);
        btnSignOut = view.findViewById(R.id.SignOut);
        btnEditProfile = view.findViewById(R.id.edit_profile);
        btnAccountSercurity = view.findViewById(R.id.account_sercurity);
<<<<<<<<< Temporary merge branch 1
        btnAddressBook = view.findViewById(R.id.address_book);
        setupButton();
=========
        btnHelp = view.findViewById(R.id.help);
        tvUserName = view.findViewById(R.id.tv_username);
        avatar = view.findViewById(R.id.imageView_avatar_userProfile);
        setupButton();
        getUserImformation();

    }
    @Override
    public void onResume() {
        super.onResume();
        getUserImformation();
>>>>>>>>> Temporary merge branch 2
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
<<<<<<<<< Temporary merge branch 1
        btnAddressBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddressBookActivity.class);
                startActivity(intent);
            }
        });
=========


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
        Glide.with(this).load(user.getPhotoUrl()).error(R.drawable.ic_user).into(avatar);
>>>>>>>>> Temporary merge branch 2

    }


}
