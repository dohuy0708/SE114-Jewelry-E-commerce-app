package com.example.jewelryecommerceapp.TabItems;

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

import com.example.jewelryecommerceapp.Adapters.OrdersAdapter;
import com.example.jewelryecommerceapp.Models.Order;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WaitAceptTabItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaitAceptTabItem extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WaitAceptTabItem() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WaitAceptTabItem.
     */
    // TODO: Rename and change types and number of parameters
    public static WaitAceptTabItem newInstance(String param1, String param2) {
        WaitAceptTabItem fragment = new WaitAceptTabItem();
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
        return inflater.inflate(R.layout.fragment_tabitem_waitacp, container, false);
    }
    ImageView Sort;
    ArrayList<Order> ords;
    OrdersAdapter adt;
    RecyclerView waitacp;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ords=new ArrayList<Order>();
        initOrds(ords);
        Sort=view.findViewById(R.id.btnsort);
        adt=new OrdersAdapter(getContext(),ords);
        waitacp=view.findViewById(R.id.listwaitacp);
        waitacp.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        waitacp.setHasFixedSize(true);
        waitacp.setAdapter(adt);
        adt.notifyDataSetChanged();
    }

    private void initOrds(ArrayList<Order> ors) {
    }
}