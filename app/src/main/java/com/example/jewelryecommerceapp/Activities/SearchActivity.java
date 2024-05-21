package com.example.jewelryecommerceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Adapters.CategoryAdapter;
import com.example.jewelryecommerceapp.Adapters.ProductAdapter;
import com.example.jewelryecommerceapp.Fragments.FilterFragment;
import com.example.jewelryecommerceapp.Models.CategoryItem;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity implements FilterFragment.OnDataPass {
    private ImageView btnFilter;
    private Button Apply;
    private FrameLayout filterFrame;
    private Fragment filterFragment;
    private TextView categoryName;
    private ImageView btnback, btnSearch;
    public static EditText etSearch;
    private RecyclerView rc_product;
    private ArrayList<Product> productList;

    private ProductAdapter productAdapter;
    private LoadingDialog loadingDialog;
    private int selectedPrice;
    private String Material,Mounted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search);
        loadingDialog = new LoadingDialog(SearchActivity.this);
        reference();
        getEvent();



        //Nhaapjn dữ liệu intent qua
        Intent myintent =getIntent();

       String categoryname=myintent.getStringExtra("categoryitem");
       String input = myintent.getStringExtra("input");
       categoryName.setText(categoryname);
        productList =new ArrayList<>();
       if(input!="")
       {
           etSearch.setText(input);
       }


       if(categoryname.equals("Sản phẩm bán chạy")||categoryname.equals("Sản phẩm mới"))
       {
          GetProductListDetailFromFireBase(categoryname);
       }
       else {
           GetProductListFromFireBase(input,categoryname);
           GetProductListSearchFromFireBase(input);
       }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newinput = etSearch.getText().toString();
                categoryName.setText("Sản phẩm tìm kiếm");
                productList.clear();

                GetProductListSearchFromFireBase(newinput);
            }
        });

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

    private void GetProductListDetailFromFireBase(String categoryname) {
        loadingDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(categoryname)  ;
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Product product =  dataSnapshot.getValue(Product.class);

                    productList.add(product);

                }
                SetUI();
                //    Toast.makeText(SearchActivity.this,productList.get(0).getProductName(),Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        loadingDialog.cancel();
    }

    private void GetProductListSearchFromFireBase(String input) {
        loadingDialog.show();
        // chuẩn hóa dữ liệu search về chữ thường và bỏ dấu
        String newInput = chuanHoaChuoi(input);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // DataSnapshot là tổng các Product , chứa các item trong đó, khi getChildren() , thì ta sẽ lấy từng item  .
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    for (DataSnapshot productSnapshot : categorySnapshot.getChildren()) {
                        Product product = productSnapshot.getValue(Product.class);
                        // chuẩn hóa tên về chữ thường và bỏ dấu
                            String newName = (product.getProductName());
                        //Toast.makeText(SearchActivity.this,newName, Toast.LENGTH_LONG).show();
                        if (product != null && newName.contains(newInput)) {

                            productList.add(product);
                        }
                        if(product!=null && product.getAccessory().equals(input))
                        {
                            productList.add(product);
                        }
                    }
                }
                productAdapter.notifyDataSetChanged();
                SetUI();
                //   Toast.makeText(getActivity(),"Finish", Toast.LENGTH_LONG).show();

                loadingDialog.cancel();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //   Toast.makeText(getActivity(),"Fail",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void SetUI() {
        productAdapter=new ProductAdapter(this,productList,1,new ProductAdapter.IClickListener() {
            @Override
            public void OnClickItem(String productType, String productID) {
                Intent intent = new Intent(SearchActivity.this, ProductDetailActivity.class);
                intent.putExtra("type", productType);
                intent.putExtra("ID", productID);
                startActivity(intent);
                 
            }
        });


        rc_product.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rc_product.setHasFixedSize(true);
        rc_product.setLayoutManager(new GridLayoutManager(this,2));
        rc_product.setAdapter(productAdapter);

    }

    private void GetProductListFromFireBase(String input, String categoryname) {
        loadingDialog.show();

         FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product").child(input) ;
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Product product =  dataSnapshot.getValue(Product.class);

                       productList.add(product);

                }
                SetUI();
           //    Toast.makeText(SearchActivity.this,productList.get(0).getProductName(),Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        loadingDialog.cancel();
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
        filterFrame=findViewById(R.id.filter_frame);
        rc_product=findViewById(R.id.rc_product);
        categoryName=findViewById(R.id.category_name);
        btnback=findViewById(R.id.btnback);
        btnSearch = findViewById(R.id.btnSearch);
    }
    public static String chuanHoaChuoi(String chuoi) {
        // Chuyển đổi chuỗi về chữ thường và loại bỏ dấu
        chuoi = chuoi.toLowerCase().replaceAll("\\s", "").replaceAll("[^a-z0-9]", "");
        return chuoi;
    }
    private  void getProduct(){
      /*  productList.add(new Product(R.drawable.demo,"Nhẫn",599999));
        productList.add(new Product(R.drawable.demo,"Nhẫn",599999));
        productList.add(new Product(R.drawable.demo,"Nhẫn",599999));
        productList.add(new Product(R.drawable.demo,"Nhẫn",599999));
        productList.add(new Product(R.drawable.demo,"Nhẫn",599999));
        productList.add(new Product(R.drawable.demo,"Nhẫn",599999));*/
    }
//sự kiện filter
    @Override
    public void onDataPass(String material, String mounted, int price) {
        Material=material;Mounted=mounted;selectedPrice=price;
        Toast.makeText(this, Material+Mounted+selectedPrice, Toast.LENGTH_SHORT).show();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        filterFragment = getSupportFragmentManager().findFragmentByTag(FilterFragment.TAG);

        if (filterFragment != null) {
            if (filterFragment.isVisible()) {
                transaction.replace(R.id.filter_frame, new Fragment()).addToBackStack(FilterFragment.TAG);
                transaction.commit();
            } else {
                getSupportFragmentManager().popBackStack();
            }
        }
    }
}
