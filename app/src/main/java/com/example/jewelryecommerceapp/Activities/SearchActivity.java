package com.example.jewelryecommerceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Adapters.ProductAdapter;
import com.example.jewelryecommerceapp.Fragments.FilterFragment;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private ImageView btnFilter;
    private FrameLayout filterFrame;
    private Fragment filterFragment;
    private TextView categoryName;
    private ImageView btnback;
    public static EditText etSearch;
    private RecyclerView rc_product;
    private ArrayList<Product> productList;

    private ProductAdapter productAdapter;
    public static int selectedPrice = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        reference();
        getEvent();
        Intent intent =getIntent();
        Bundle bundle =intent.getBundleExtra("categoryname");
        String categoryname=bundle.getString("categoryitem");
        categoryName.setText(categoryname);
        productList =new ArrayList<>();
        getProduct();
        productAdapter=new ProductAdapter(this,productList,1);
        rc_product.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rc_product.setHasFixedSize(true);
        rc_product.setLayoutManager(new GridLayoutManager(this,2));
        rc_product.setAdapter(productAdapter);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product) {
            Intent intent =new Intent(SearchActivity.this, ProductDetailActivity.class);
            startActivity(intent);
            }
        });*/
    }

    private void getEvent() {

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                filterFragment = getSupportFragmentManager().findFragmentByTag(FilterFragment.TAG);

                if (filterFragment != null) {
                    if (filterFragment.isVisible()) {
                        transaction.replace(R.id.filter_frame, new Fragment()).addToBackStack(FilterFragment.TAG);
                        transaction.commit();
                    } else {
                        getSupportFragmentManager().popBackStack();
                    }
                } else {
                    filterFragment = new FilterFragment();
                    transaction.replace(R.id.filter_frame, filterFragment, FilterFragment.TAG);
                    transaction.commit();
                }
            }
        });
    }

    private void reference() {
        btnFilter = findViewById(R.id.btnFilter);
        etSearch = findViewById(R.id.searchFragment_etSearch);
        rc_product=findViewById(R.id.rc_product);
        categoryName=findViewById(R.id.category_name);
        btnback=findViewById(R.id.btnback);
    }
    private  void getProduct(){
      /*  productList.add(new Product(R.drawable.demo,"Nhẫn",599999));
        productList.add(new Product(R.drawable.demo,"Nhẫn",599999));
        productList.add(new Product(R.drawable.demo,"Nhẫn",599999));
        productList.add(new Product(R.drawable.demo,"Nhẫn",599999));
        productList.add(new Product(R.drawable.demo,"Nhẫn",599999));
        productList.add(new Product(R.drawable.demo,"Nhẫn",599999));*/
    }

}
