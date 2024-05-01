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

import com.example.jewelryecommerceapp.Activities.HomeActivity;
import com.example.jewelryecommerceapp.Activities.LoadingDialog;
import com.example.jewelryecommerceapp.Activities.NoticeActivity;
import com.example.jewelryecommerceapp.Adapters.ProductAdapter;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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



        ///  Đẩy dữ liệu lên firebase
      //  StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        ArrayList<String> imagelist = new ArrayList<>();
        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/B%C3%B4ng%20tai%2Fsample3%2Fbtptb363_e3758ea112c84d298615e66534243eba.webp?alt=media&token=e34834a1-7653-44a9-a7ba-41370c0fa940");
        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/B%C3%B4ng%20tai%2Fsample3%2Fbtptb363_3_c89ebbfbfc7f49dabb1cba750440a536.webp?alt=media&token=13a28ef3-87cb-4bae-a5f9-9f9668f6af7c");
        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/B%C3%B4ng%20tai%2Fsample3%2Fbtptb363_2_4ae654a07a9443ecbf0142315cc2eddc.webp?alt=media&token=338d81b0-eb9b-47d3-b368-ed0d9b666e5d");
        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/B%C3%B4ng%20tai%2Fsample3%2Fbtptb363_1_5157e7a1d2c043a5a620deb78b074354.webp?alt=media&token=15c21f88-bf0e-457d-aa49-2da2a776200f");
      // imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/B%C3%B4ng%20tai%2Fsample1%2Fon-gbztxmh000017-bong-tai-vang-14k-dinh-da-synthetic-pnj-hello-kitty-2.jpg?alt=media&token=79eb2c83-05d5-4f1a-8723-733e4b908d37");

        Map<String,Integer> sizemap = new HashMap<>();
        sizemap.put("11",10);
        sizemap.put("12",10);
        sizemap.put("13",10);
        sizemap.put("14",10);


        Product testproduct = new Product("3", "Bông tai","Bông tai Mặt Trăng đính đá Cubic","Vàng",imagelist,sizemap,"đá Cubic",4.5,4450000,"Sang xịn mịn","H-Jewelry");

        FirebaseDatabase data = FirebaseDatabase.getInstance();
        DatabaseReference ref = data.getReference("Product").child(testproduct.getType());

        ref.push().setValue(testproduct, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    // Đã xảy ra lỗi khi đẩy dữ liệu lên Firebase
                   // Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // Đẩy dữ liệu lên Firebase thành công
                    //Toast.makeText(getActivity(),"Finish", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    private LoadingDialog loadingDialog;

    ImageView img_notice;

    ArrayList<Product> myTrendList, myNewList;

    RecyclerView rc_trend;

    RecyclerView rc_new;
    ProductAdapter myAdapterTrend, myAdapterNew;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingDialog = new LoadingDialog(getActivity());

        myTrendList= new ArrayList<>();
        //initProduct(myList);

        myAdapterTrend = new ProductAdapter(getContext(),myTrendList);
        myNewList= new ArrayList<>();
        //initProduct(myList);


        myAdapterNew= new ProductAdapter(getContext(),myNewList);

        GetTrendListFromDataBase();
        GetNewListFromDataBase();

        rc_trend=view.findViewById(R.id.rc_trend);
        rc_trend.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_trend.setHasFixedSize(true);
        rc_trend.setAdapter(myAdapterTrend);

        rc_new=view.findViewById(R.id.rc_new);
        //newList.setLayoutManager(new GridLayoutManager(getContext(),2));
        rc_new.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_new.setHasFixedSize(true);
        rc_new.setAdapter(myAdapterNew);
        myAdapterNew.notifyDataSetChanged();

        //
        img_notice=view.findViewById(R.id.img_notice);
        img_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NoticeActivity.class);
                startActivity(intent);
            }
        });

    }

    // Lấy dữ liệu từ Database xuống ListTrend
    private void GetNewListFromDataBase() {
        loadingDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product/Bông tai");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // DataSnapshot là tổng các Product , chứa các item trong đó, khi getChildren() , thì ta sẽ lấy từng item  .
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Product product = dataSnapshot.getValue(Product.class);
                    myNewList.add(product);

                }
             //   Toast.makeText(getActivity(),"Finish", Toast.LENGTH_LONG).show();
                myAdapterNew.notifyDataSetChanged();
              loadingDialog.cancel();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
             //   Toast.makeText(getActivity(),"Fail",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void GetTrendListFromDataBase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product/Bông tai");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // DataSnapshot là tổng các Product , chứa các item trong đó, khi getChildren() , thì ta sẽ lấy từng item  .
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Product product =  dataSnapshot.getValue(Product.class);


                    // Query những sản phẩm trending rồi mới add vào.
                    myTrendList.add(product);



                }
            //    Toast.makeText(getActivity(),"Finish", Toast.LENGTH_LONG).show();
                myAdapterTrend.notifyDataSetChanged();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
              //  Toast.makeText(getActivity(),"Fail",Toast.LENGTH_LONG).show();

            }
        });
        
    }

    /*public void initProduct(ArrayList<Product> myList){
        myList.add( new Product(R.drawable.demo,"Nhẫn",2999000));
        myList.add(new Product(R.drawable.demo2,"Nhẫn vàng",2999000));
        myList.add(new Product(R.drawable.demo2,"Nhẫn vàng promax",2999000));
        myList.add(new Product(R.drawable.demo,"Vẫn là nhẫn",2999000));
        myList.add(new Product(R.drawable.demo,"Nhà toàn nhẫn",2999000));

    }*/
}