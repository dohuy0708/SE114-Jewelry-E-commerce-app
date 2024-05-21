package com.example.jewelryecommerceapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.Glide;
import com.example.jewelryecommerceapp.Activities.AccountSercurityActivity;
import com.example.jewelryecommerceapp.Activities.EditProfileActivity;
import com.example.jewelryecommerceapp.Activities.HomeActivity;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class UserFragment extends Fragment {

    Button btnEditProfile, btnOrderList, btnSignOut , btnAccountSercurity, btnHelp;
    TextView tvUserName;
    ImageView avatar;
    private ActivityResultLauncher<Intent> activityResultLauncher;
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
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            // Reload lại Fragment
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.detach(UserFragment.this).attach(UserFragment.this).commit();
                        }
                    }
                });
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
    }
    private void setupButton()
    {
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
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
                activityResultLauncher.launch(intent);

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
        Glide.with(this).load(user.getPhotoUrl()).error(R.drawable.ic_user).into(avatar);

    }


}
