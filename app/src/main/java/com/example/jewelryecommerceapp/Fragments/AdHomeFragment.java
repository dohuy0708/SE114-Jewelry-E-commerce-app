package com.example.jewelryecommerceapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jewelryecommerceapp.Adapters.ProductAdapter;
import com.example.jewelryecommerceapp.Adapters.TopRateAdapter;
import com.example.jewelryecommerceapp.Adapters.TopWeekAdapter;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdHomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdHomeFragment newInstance(String param1, String param2) {
        AdHomeFragment fragment = new AdHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ad_home, container, false);
    }

    RecyclerView rc_topWeak, rc_topRate,rc_service;

    TopRateAdapter topRateAdapter;
    TopWeekAdapter topWeekAdapter;
    ArrayList<Product> topRateList,topWeekList,serviceList;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rc_service=view.findViewById(R.id.serviceList);
        serviceList = new ArrayList<>();

        //myAdapter = new ProductAdapter(getContext(),myList);

        topWeekList = new ArrayList<>();
        topWeekList.add( new Product(R.drawable.demo,"Nhẫn","A12"));
        topWeekList.add(new Product(R.drawable.demo,"Vòng cổ","A14"));
        topWeekList.add(new Product(R.drawable.demo,"Bông tai","A26"));
        topWeekList.add(new Product(R.drawable.demo,"Lắc tay","B88"));
        topWeekList.add(new Product(R.drawable.demo,"Trâm","B77"));
        rc_topWeak=view.findViewById(R.id.topWeekList);
        topWeekAdapter=new TopWeekAdapter(getContext(),topWeekList);
        rc_topWeak.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_topWeak.setHasFixedSize(true);
        rc_topWeak.setAdapter(topWeekAdapter);

        topRateList = new ArrayList<>();
        topRateList.add( new Product(R.drawable.demo,"Nhẫn Vàng",5.0));
        topRateList.add(new Product(R.drawable.demo,"Nhẫn Bạc",4.9));
        topRateList.add(new Product(R.drawable.demo,"Nhẫn kim cương",4.9));
        topRateList.add(new Product(R.drawable.demo,"Lắc tay",4.9));
        topRateList.add(new Product(R.drawable.demo,"Trâm",4.8));
        rc_topRate=view.findViewById(R.id.topRateList);
        topRateAdapter=new TopRateAdapter(getContext(),topRateList);
        rc_topRate.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_topRate.setHasFixedSize(true);
        rc_topRate.setAdapter(topRateAdapter);

    }
}