package com.example.jewelryecommerceapp.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager.widget.PagerAdapter;

import com.example.jewelryecommerceapp.TabItems.AceptedTabItem;
import com.example.jewelryecommerceapp.TabItems.ShippedTabItem;
import com.example.jewelryecommerceapp.TabItems.WaitAceptTabItem;

import java.util.ArrayList;


public class OrderTabItemAdapter extends FragmentStateAdapter {


    public OrderTabItemAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public OrderTabItemAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public OrderTabItemAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
            {
                return new WaitAceptTabItem();
            }
            case 1:
            {
                return new AceptedTabItem();
            }
            case 2:
            {
                return new ShippedTabItem();
            }
            default:
            {
                return new WaitAceptTabItem();
            }
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
