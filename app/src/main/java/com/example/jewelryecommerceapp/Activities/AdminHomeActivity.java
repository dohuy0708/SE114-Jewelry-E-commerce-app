package com.example.jewelryecommerceapp.Activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.jewelryecommerceapp.Adapters.ViewPagerAdapter;
import com.example.jewelryecommerceapp.Fragments.*;
import com.example.jewelryecommerceapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class AdminHomeActivity extends AppCompatActivity {
    ViewPager2 pagerMain;
    ArrayList<Fragment> fragmentArrayList;
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);

        pagerMain=findViewById(R.id.pagerMain);
        fragmentArrayList= new ArrayList<>();
        bottomNav=findViewById(R.id.bottomNav);
        fragmentArrayList.add(new AdHomeFragment());
        fragmentArrayList.add(new AdStoreFragment());
        fragmentArrayList.add(new AdOrderFragment());
        fragmentArrayList.add(new AdUserFragment());

        ViewPagerAdapter adapterViewPager = new ViewPagerAdapter(this,fragmentArrayList);
        pagerMain.setAdapter(adapterViewPager);
        pagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNav.setSelectedItemId(R.id.itHome);
                        break;
                    case 1:
                        bottomNav.setSelectedItemId(R.id.itStore);
                        break;
                    case 2:
                        bottomNav.setSelectedItemId(R.id.itOrder);
                        break;
                    case 3:
                        bottomNav.setSelectedItemId(R.id.itUser);
                        break;
                }
                super.onPageSelected(position);
            }
        });
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.itHome)
                    pagerMain.setCurrentItem(0);
                else if(item.getItemId()==R.id.itStore)
                    pagerMain.setCurrentItem(1);
                else if(item.getItemId()==R.id.itOrder)
                    pagerMain.setCurrentItem(2);
                else if(item.getItemId()==R.id.itUser)
                    pagerMain.setCurrentItem(3);

                return true;
            }
        });
    }

}