package com.example.jewelryecommerceapp.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.jewelryecommerceapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserOrderList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserOrderList extends Fragment {


    // TODO: Rename and change types and number of parameters
    public static UserOrderList newInstance() {
        UserOrderList fragment = new UserOrderList();
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
        return inflater.inflate(R.layout.fragment_user_order_list, container, false);
    }
}