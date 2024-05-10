package com.example.jewelryecommerceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.jewelryecommerceapp.Adapters.ViewPagerAdapter;
import com.example.jewelryecommerceapp.Fragments.CartFragment;
import com.example.jewelryecommerceapp.Fragments.CategoryFragment;
import com.example.jewelryecommerceapp.Fragments.HomeFragment;
import com.example.jewelryecommerceapp.Fragments.UserFragment;
import com.example.jewelryecommerceapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ViewPager2 pagerMain;
    ArrayList<Fragment> fragmentArrayList;
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        pagerMain=findViewById(R.id.pagerMain);
        fragmentArrayList= new ArrayList<>();
        bottomNav=findViewById(R.id.bottomNav);
        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new CategoryFragment());
        fragmentArrayList.add(new CartFragment());
        fragmentArrayList.add(new UserFragment());

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
                        bottomNav.setSelectedItemId(R.id.itCategory);
                        break;
                    case 2:
                        bottomNav.setSelectedItemId(R.id.itCart);
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
                else if(item.getItemId()==R.id.itCategory)
                    pagerMain.setCurrentItem(1);
                else if(item.getItemId()==R.id.itCart)
                    pagerMain.setCurrentItem(2);
                else if(item.getItemId()==R.id.itUser)
                    pagerMain.setCurrentItem(3);

                return true;
            }
        });



    }

    // khi click sign out thì khởi động lại activityhome
    public void ClickSignOut( Fragment fragment)
    {
        FirebaseAuth.getInstance().signOut();


        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}