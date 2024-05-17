package com.example.jewelryecommerceapp.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class FilterFragment extends Fragment {
    public static final String TAG = FilterFragment.class.getName();
    private View view;
    private TextView minPrice,maxPrice;
    public static SeekBar seekBar;
    private Button Applybtn;
    private ArrayList<Button> MaterialList,MountedList;
    private String Material,Mounted;
    int Minprice;
    private OnDataPass dataPasser;

    public interface OnDataPass {
        void onDataPass(String material,String mounted,int price);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dataPasser = (OnDataPass) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnDataPass");
        }
    }

    public void passData(String material,String mounted,int price) {
        dataPasser.onDataPass(material,mounted,price);
    }

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
        seekBar = view.findViewById(R.id.filter_seekbar);
        Applybtn = view.findViewById(R.id.filter_ApplyButton);
    }
    private void getData(){
        MaterialList= new ArrayList<>();
        MaterialList.add(view.findViewById(R.id.filter_btn_gold));
        MaterialList.add(view.findViewById(R.id.filter_btn_whitegold));
        MaterialList.add(view.findViewById(R.id.filter_btn_silver));
        MountedList=new ArrayList<>();
        MountedList.add(view.findViewById(R.id.filter_btn_diamond));
        MountedList.add(view.findViewById(R.id.filter_btn_pearl));
        MountedList.add(view.findViewById(R.id.filter_btn_gemstone));
        minPrice.setText("0");
        maxPrice.setText("100000000");
        seekBar.setMax(100000000);
    }
    private void getEvents() {
            for(Button button : MaterialList) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Material = button.getText().toString();
                        setColorButtonMaterial(button);
                    }
                });
            }
                for(Button button : MountedList){
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Mounted=button.getText().toString();
                            setColorButtonMounted(button);
                        }
                    });
                }
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        minPrice.setText(""+progress);
                        Minprice=progress;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
Applybtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        passData(Material,Mounted,Minprice);
    }
});
    }

    private void setColorButtonMaterial(Button selectedBtn){
        for(Button button : MaterialList)
            if(button.equals(selectedBtn))
                button.setBackgroundColor(Color.parseColor("#FF00C8F0"));
            else {
                button.setBackgroundColor(Color.parseColor("#EEEEEE"));
            }
    }
    private void setColorButtonMounted(Button selectedBtn){
        for(Button button : MountedList)
            if(button.equals(selectedBtn))
                button.setBackgroundColor(Color.parseColor("#FF00C8F0"));
            else
                button.setBackgroundColor(Color.parseColor("#EEEEEE"));
    }

}
