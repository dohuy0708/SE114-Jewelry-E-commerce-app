package com.example.jewelryecommerceapp.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.jewelryecommerceapp.Models.Filter;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class FilterButtonAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Filter> FilterList;
    private Button FilterButton;
    private View view;

    public FilterButtonAdapter(Context context,ArrayList<Filter> FilterList){
        this.context=context;
        this.FilterList=FilterList;
    }
    @Override
    public int getCount() {
        return FilterList.size();
    }

    @Override
    public Object getItem(int position) {
        return FilterList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = view = inflater.inflate(R.layout.view_holder_filter_button, null);

        FilterButton = view.findViewById(R.id.filer_button);

        FilterButton.setText(FilterList.get(position).getFilterName());

        if(FilterList.get(position).isSelected())
            FilterButton.setBackgroundColor(Color.parseColor("#DF7861"));
        else
            FilterButton.setBackgroundColor(Color.TRANSPARENT);

        getEvent(position);

        return view;
    }
    private void getEvent(int i) {
        FilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!FilterList.get(i).isSelected()) {
                    //Trường hợp chọn
                    view.findViewById(R.id.filer_button).setBackgroundColor(Color.parseColor("#DF7861"));
                    FilterList.get(i).setSelected(true);

                }else{
                    //Hủy chọn
                    view.findViewById(R.id.filer_button).setBackgroundColor(Color.TRANSPARENT);
                    FilterList.get(i).setSelected(false);
                }
            }
        });
    }
}
