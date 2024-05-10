package com.example.jewelryecommerceapp.Adapters;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.jewelryecommerceapp.Fragments.StaffChatFragment;
import org.jetbrains.annotations.NotNull;

public class StaffChatBoardAdapter extends FragmentPagerAdapter {
    int tabcount;

    Activity activity;


    public StaffChatBoardAdapter(@NonNull FragmentManager fm, int behavior, Activity activity) {
        super(fm, behavior);
        tabcount=behavior;
        this.activity = activity;
    }

    public StaffChatBoardAdapter(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new StaffChatFragment();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
