package com.example.jewelryecommerceapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.jewelryecommerceapp.Activities.AddProductsActivity;
import com.example.jewelryecommerceapp.Activities.AdminProductDetailActivity;
import com.example.jewelryecommerceapp.Activities.ProductDetailActivity;
import com.example.jewelryecommerceapp.Activities.SearchActivity;
import com.example.jewelryecommerceapp.Adapters.ProductAdapter;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;
import com.example.jewelryecommerceapp.Adapters.StoreProductAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdStoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdStoreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdStoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdStoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdStoreFragment newInstance(String param1, String param2) {
        AdStoreFragment fragment = new AdStoreFragment();
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
        return inflater.inflate(R.layout.fragment_ad_store, container, false);
    }
    RecyclerView rc_store_product;
    StoreProductAdapter storeProductAdapter;
    ArrayList<Product> productList;
    Button addproduct_btn;
    ImageView btnAdd;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rc_store_product=view.findViewById(R.id.rc_store_prd);
        productList = new ArrayList<>();
        GetProductListFromFireBase("Nhẫn");
       // addproduct_btn=view.findViewById(R.id.button8);
      /*  addproduct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdminProductDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("Title","Add");
                intent.putExtra("Product",bundle);
                startActivity(intent);

            }
        });*/
    }
    private void GetProductListFromFireBase(String categoryname) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    for (DataSnapshot productSnapshot : categorySnapshot.getChildren()) {
                        Product product = productSnapshot.getValue(Product.class);
                        productList.add(product);
                    }
                }
                SetUI();
                //    Toast.makeText(SearchActivity.this,productList.get(0).getProductName(),Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void SetUI() {
        storeProductAdapter=new StoreProductAdapter(productList);
        rc_store_product.setAdapter(storeProductAdapter);
        rc_store_product.setHasFixedSize(true);
        rc_store_product.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        storeProductAdapter.setOnItemClickListener(new StoreProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
                Intent intent = new Intent(getActivity(), AdminProductDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("Title","Update");
                bundle.putString("ProductID",product.getProductId());
                intent.putExtra("Product",bundle);
                startActivity(intent);
            }
        });

       /* addproduct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdminProductDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("Title","Add");
                intent.putExtra("Product",bundle);
                startActivity(intent);

            }
        });*/

        //btnAdd = view.findViewById(R.id.btn_addpro);

        // intent tới thêm sp
      /*  btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddProductsActivity.class);
                startActivity(intent);
            }
        });*/


    }


}