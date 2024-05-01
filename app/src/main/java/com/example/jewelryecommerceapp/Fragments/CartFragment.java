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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jewelryecommerceapp.Activities.HomeActivity;
import com.example.jewelryecommerceapp.Activities.NoticeActivity;
import com.example.jewelryecommerceapp.Activities.Payment;
import com.example.jewelryecommerceapp.Adapters.CartProductsAdapter;
import com.example.jewelryecommerceapp.Adapters.ProductAdapter;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    ImageView logo;

    ArrayList<Product> Pros;
    RecyclerView inCartPros;
    CartProductsAdapter Adt;
    TextView tienspp;
    TextView totalll;
    TextView promotee;
    Button ordernoww;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        Pros=new ArrayList<>();
        initProduct(Pros);
        Adt=new CartProductsAdapter(getContext(),Pros);
        inCartPros=view.findViewById(R.id.Cartt);
        inCartPros.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        inCartPros.setHasFixedSize(true);
        inCartPros.setAdapter(Adt);

        Adt.notifyDataSetChanged();

        logo=view.findViewById(R.id.logo);
        tienspp=view.findViewById(R.id.tiensp);
        promotee=view.findViewById(R.id.promote);
        ordernoww=view.findViewById(R.id.ordernow);
        totalll=view.findViewById(R.id.tccc);
        totalll.setText("Tổng cộng: ");//+ String.valueOf(Integer.parseInt(tienspp.getText().toString())-Integer.parseInt(promotee.getText().toString())));
        ordernoww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),Payment.class );
                startActivity(i);
            }
        });
    }

    private void initProduct(ArrayList<Product> pros) {

            Pros.add(new Product(R.drawable.ic_home,"aloalo",2999000));
            Pros.add(new Product(R.drawable.demo,"assdasd",2999000));

    }
}