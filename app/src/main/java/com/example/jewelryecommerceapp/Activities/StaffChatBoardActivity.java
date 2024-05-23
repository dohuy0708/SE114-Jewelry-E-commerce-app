package com.example.jewelryecommerceapp.Activities;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.jewelryecommerceapp.Models.User;
import com.example.jewelryecommerceapp.R;
import com.example.jewelryecommerceapp.Adapters.StaffChatBoardAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
public class StaffChatBoardActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem mChat;
    ViewPager viewPager;
    StaffChatBoardAdapter adapterChatBoard;
    androidx.appcompat.widget.Toolbar mtoolbar;
    Button btn_back;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_chat);

        tabLayout = findViewById(R.id.include);
        mChat = findViewById(R.id.chat);
        viewPager = findViewById(R.id.fragmentcontainer);

        firebaseAuth = FirebaseAuth.getInstance();

        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        mDatabase = FirebaseDatabase.getInstance().getReference().getDatabase();

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_baseline_more_vert_24);
        mtoolbar.setOverflowIcon(drawable);

        adapterChatBoard = new StaffChatBoardAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), this);
        viewPager.setAdapter(adapterChatBoard);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

                if(tab.getPosition()==0 || tab.getPosition()==1)
                {
                    adapterChatBoard.notifyDataSetChanged();
                }



            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }


}