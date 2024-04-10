package com.example.jewelryecommerceapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jewelryecommerceapp.Activities.NoticeActivity;
import com.example.jewelryecommerceapp.Adapters.ProductAdapter;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    ImageView img_notice;
    ArrayList<Product> myList;
    RecyclerView rc_trend;

    RecyclerView rc_new;
    ProductAdapter myAdapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        myList= new ArrayList<>();
        initProduct(myList);

        myAdapter = new ProductAdapter(getContext(),myList);

        rc_trend=view.findViewById(R.id.rc_trend);
        rc_trend.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_trend.setHasFixedSize(true);
        rc_trend.setAdapter(myAdapter);

        rc_new=view.findViewById(R.id.rc_new);
        //newList.setLayoutManager(new GridLayoutManager(getContext(),2));
        rc_new.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_new.setHasFixedSize(true);
        rc_new.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        //
        img_notice=view.findViewById(R.id.img_notice);
        img_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NoticeActivity.class);
                startActivity(intent);
            }
        });

    }
    public void initProduct(ArrayList<Product> myList){
        myList.add( new Product(R.drawable.demo,"Selena",2999000));
        myList.add(new Product(R.drawable.demo,"Lucia Plume",2999000));
        myList.add(new Product(R.drawable.demo,"Lucia Crimson Abyss",2999000));
        myList.add(new Product(R.drawable.demo,"Vera Rozen",2999000));
        myList.add(new Product(R.drawable.demo,"Luna Lauriel",2999000));

    }
}