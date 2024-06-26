package com.example.jewelryecommerceapp.Adapters;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.jewelryecommerceapp.Fragments.StaffChatFragment;
import org.jetbrains.annotations.NotNull;

public class StaffChatBoardAdapter extends FragmentPagerAdapter {
    int tabCount;

    Activity activity;


    public StaffChatBoardAdapter(@NonNull FragmentManager fm, int behavior, Activity activity) {
        super(fm, behavior);
        tabCount=behavior;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return new StaffChatFragment();
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}