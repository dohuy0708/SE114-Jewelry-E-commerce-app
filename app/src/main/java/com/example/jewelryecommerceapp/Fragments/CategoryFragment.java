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

import com.example.jewelryecommerceapp.Activities.SearchActivity;
import com.example.jewelryecommerceapp.Adapters.CategoryAdapter;
import com.example.jewelryecommerceapp.Models.CategoryItem;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryFragment newInstance(String param1, String param2) {
        CategoryFragment fragment = new CategoryFragment();
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
        return inflater.inflate(R.layout.fragment_category, container, false);
    }
    RecyclerView rc_category;
    CategoryAdapter categoryAdapter;
    ArrayList<CategoryItem> CategoryList;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rc_category=view.findViewById(R.id.list_category);
        CategoryList = new ArrayList<>();
        CategoryList.add(new CategoryItem("Nhẫn",R.drawable.ic_back));
        CategoryList.add(new CategoryItem("Nhẫn đôi",R.drawable.ic_back));
        CategoryList.add(new CategoryItem("Dây chuyền",R.drawable.ic_back));
        CategoryList.add(new CategoryItem("Vòng tay",R.drawable.ic_back));
        CategoryList.add(new CategoryItem("Bông tai",R.drawable.ic_back));
        CategoryList.add(new CategoryItem("Bộ trang sức",R.drawable.ic_back));
        categoryAdapter=new CategoryAdapter(getContext(),CategoryList);
        rc_category.setHasFixedSize(true);
        rc_category.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rc_category.setAdapter(categoryAdapter);
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CategoryItem categoryItem) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("input",categoryItem.getCategoryName());
                intent.putExtra("categoryitem",categoryItem.getCategoryName());
                startActivity(intent);
            }
        });
    }

    }
