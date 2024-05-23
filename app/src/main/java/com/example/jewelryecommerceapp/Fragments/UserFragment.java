package com.example.jewelryecommerceapp.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.Glide;
import com.example.jewelryecommerceapp.Activities.*;
import com.example.jewelryecommerceapp.Models.User;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;
import org.jetbrains.annotations.NotNull;


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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null)
        {
            btnSignOut.setText("Đăng nhập");
        }
        else
        {
            btnSignOut.setText("Đăng xuất");
        }
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null)
                {
                    ((HomeActivity) getActivity()).ClickSignOut(new HomeFragment());
                }
                else
                {
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    startActivity(i);
                }
            }
        });
        btnOrderList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                if(user!=null)
                {
                    Intent intent = new Intent(getActivity(), View_ListOrder.class);
                    startActivity(intent);
                }
                else {
                    showToastWithIcon(R.drawable.attention_icon,"Bạn cần đăng nhập để xem đơn hàng");
                }
            }

        });
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null)
                {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                activityResultLauncher.launch(intent);
                }
                else {
                    showToastWithIcon(R.drawable.attention_icon,"Bạn chưa đăng nhập ");

                }

            }
        });
        btnAccountSercurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user!=null)
                {
                    Intent intent = new Intent(getActivity(), AccountSercurityActivity.class);
                    activityResultLauncher.launch(intent);
                }
                else {
                    showToastWithIcon(R.drawable.attention_icon,"Bạn chưa đăng nhập ");

                }
            }
        });


    }
    private void getUserImformation()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null) {
            String path = "User/" + user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference(path);
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                    User user1 = dataSnapshot.getValue(User.class);
                    tvUserName.setText(user1.getNAME());
                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError databaseError) {

                }


            });
        }

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
        Toast toast = new Toast(getContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}
