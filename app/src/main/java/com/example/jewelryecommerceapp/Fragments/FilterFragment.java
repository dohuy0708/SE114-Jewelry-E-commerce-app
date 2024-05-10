package com.example.jewelryecommerceapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.jewelryecommerceapp.Adapters.FilterButtonAdapter;
import com.example.jewelryecommerceapp.Models.Filter;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class FilterFragment extends Fragment {
    public static final String TAG = FilterFragment.class.getName();
    private View view;
    private TextView minPrice,maxPrice;
    private GridView gvMaterial;
    public static SeekBar seekBar;
    private Button Applybtn;
    private ArrayList<Filter> MaterialList;
    private FilterButtonAdapter SexAdapter,MaterialAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_filter, container, false);

        reference();

        getData();
        getEvents();


        return view;
    }
    private void reference() {
        minPrice=view.findViewById(R.id.MinPrice);
        maxPrice = view.findViewById(R.id.MaxPrice);
        gvMaterial = view.findViewById(R.id.Material_List);
        seekBar = view.findViewById(R.id.filter_seekbar);
        Applybtn = view.findViewById(R.id.filter_ApplyButton);
    }
    private void getData(){
        MaterialList= new ArrayList<>();
        MaterialList.add(new Filter("Vàng"));
        MaterialList.add(new Filter("Bạc"));
        MaterialList.add(new Filter("Đá Quý"));
        MaterialAdapter=new FilterButtonAdapter(getContext(),MaterialList);
        gvMaterial.setAdapter(MaterialAdapter);


        minPrice.setText("0");
        maxPrice.setText("100000000");
        seekBar.setMax(100000000);
    }
    private void getEvents() {

    }


}
