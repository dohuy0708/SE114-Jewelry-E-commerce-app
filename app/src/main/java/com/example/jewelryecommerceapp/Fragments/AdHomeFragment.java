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
import android.widget.Toast;

import com.example.jewelryecommerceapp.Activities.CustomerViewChatActivity;
import com.example.jewelryecommerceapp.Activities.NoticeAdActivity;
import com.example.jewelryecommerceapp.Activities.StaffChatBoardActivity;
import com.example.jewelryecommerceapp.Adapters.ServiceAdapter;
import com.example.jewelryecommerceapp.Adapters.TopRateAdapter;
import com.example.jewelryecommerceapp.Adapters.TopWeekAdapter;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.Models.Service;
import com.example.jewelryecommerceapp.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.type.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdHomeFragment extends Fragment {


    public AdHomeFragment() {
        // Required empty public constructor
    }


    public static AdHomeFragment newInstance(String param1, String param2) {
        AdHomeFragment fragment = new AdHomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        return inflater.inflate(R.layout.fragment_ad_home, container, false);
    }
    BarChart chart;
    ArrayList<BarEntry> revenue;

    ImageView ad_notice,ad_chat;
    RecyclerView rc_topWeak, rc_topRate,rc_service;

    TopRateAdapter topRateAdapter;
    TopWeekAdapter topWeekAdapter;
    ServiceAdapter serviceAdapter;
    ArrayList<Product> topRateList,topWeekList;
    ArrayList<Service> serviceList;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ad_notice= view.findViewById(R.id.ad_notice);
        ad_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NoticeAdActivity.class);
                startActivity(intent);
            }
        });

        chart = view.findViewById(R.id.chart);
        chart.getAxisRight().setDrawLabels(false);
        revenue= new ArrayList<>();

        revenue.add(new BarEntry(0,156.33f));
        revenue.add(new BarEntry(1,123.7f));
        revenue.add(new BarEntry(2,157.95f));
        revenue.add(new BarEntry(3,168.2f));
        revenue.add(new BarEntry(4,100.0f));
        revenue.add(new BarEntry(5,98.35f));
        revenue.add(new BarEntry(6,88.2f));
        revenue.add(new BarEntry(7,120.3f));
        revenue.add(new BarEntry(8,103.2f));
        revenue.add(new BarEntry(9,63.98f));
        revenue.add(new BarEntry(10,135.2f));
        revenue.add(new BarEntry(11,123.2f));

        BarDataSet barDataSet = new BarDataSet(revenue,"Doanh thu (triệu đồng)");
        barDataSet.setColor(android.graphics.Color.parseColor("#2196F3"));
        barDataSet.setValueTextSize(10f);
        barDataSet.setDrawValues(true);

        BarData barData = new BarData(barDataSet);
        chart.setData(barData);
        chart.getDescription().setEnabled(false);
        chart.animateY(1000);
        chart.invalidate();


        YAxis yAxis =chart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(180f);
        yAxis.setAxisLineWidth(30f);
        yAxis.setAxisLineColor(Color.ALPHA_FIELD_NUMBER);
        yAxis.setLabelCount(10);





        String[] xValues = new String[] {"1","2","3","4","5","6","7","8","9","10","11","12"};
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValues));
        chart.getXAxis().setLabelCount(revenue.size(), false);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawGridLines(false); // Loại bỏ đường kẻ trên trục x
        chart.getAxisRight().setDrawGridLines(false);



       /* chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                // Xử lý sự kiện khi người dùng chọn một cột trên biểu đồ
                if (e == null) {
                    return;
                }
                Toast.makeText(getContext(), "Hello", Toast.LENGTH_SHORT).show();
            }



            @Override
            public void onNothingSelected() {
                // Xử lý sự kiện khi không có giá trị nào được chọn
            }
        });*/

        topWeekList = new ArrayList<>();
       /* topWeekList.add( new Product(R.drawable.demo,"Nhẫn","A12"));
        topWeekList.add(new Product(R.drawable.demo,"Vòng cổ","A14"));
        topWeekList.add(new Product(R.drawable.demo,"Bông tai","A26"));
        topWeekList.add(new Product(R.drawable.demo,"Lắc tay","B88"));
        topWeekList.add(new Product(R.drawable.demo,"Trâm","B77"));*/
        rc_topWeak=view.findViewById(R.id.topWeekList);
        topWeekAdapter=new TopWeekAdapter(getContext(),topWeekList);
        rc_topWeak.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_topWeak.setHasFixedSize(true);
        rc_topWeak.setAdapter(topWeekAdapter);

        topRateList = new ArrayList<>();
       /* topRateList.add( new Product(R.drawable.demo,"Nhẫn Vàng",5.0));
        topRateList.add(new Product(R.drawable.demo,"Nhẫn Bạc",4.9));
        topRateList.add(new Product(R.drawable.demo,"Nhẫn kim cương",4.9));
        topRateList.add(new Product(R.drawable.demo,"Lắc tay",4.9));
        topRateList.add(new Product(R.drawable.demo,"Trâm",4.8));*/
        rc_topRate=view.findViewById(R.id.topRateList);
        topRateAdapter=new TopRateAdapter(getContext(),topRateList);
        rc_topRate.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_topRate.setHasFixedSize(true);
        rc_topRate.setAdapter(topRateAdapter);

        serviceList = new ArrayList<>();
        serviceList.add(new Service("SV01",R.drawable.demo2,"Đánh bóng trang sức",324));
        serviceList.add(new Service("SV02",R.drawable.demo2,"Gia công sửa chữa",530));
        serviceList.add(new Service("SV03",R.drawable.demo2,"Thiết kế trang sức",21));
        serviceList.add(new Service("SV04",R.drawable.demo2,"Đánh bóng trang sức",21));

        rc_service=view.findViewById(R.id.rc_service);
        serviceAdapter= new ServiceAdapter(getContext(),serviceList);
        rc_service.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_service.setHasFixedSize(true);
        rc_service.setAdapter(serviceAdapter);

        ad_chat = view.findViewById(R.id.ad_chat);
        ad_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getContext(), StaffChatBoardActivity.class);
                startActivity(intent);
            }
        });

    }
}