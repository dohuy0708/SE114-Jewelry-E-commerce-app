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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
    //   StorageReference storageReference = FirebaseStorage.getInstance().getReference("Vòng tay");


      /*  ArrayList<String> imagelist = new ArrayList<>();
        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/Nh%E1%BA%ABn%20%C4%91%C3%B4i%2Fsample4%2Fnckc466_3_16695c0fe811418682010dc2d0cc717c_master_02785f02098140dea8cdf90a76bfb4fa.webp?alt=media&token=b6522c92-8d9a-4ab9-b580-c93fdae751cc");
        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/Nh%E1%BA%ABn%20%C4%91%C3%B4i%2Fsample4%2Fnckc466_2_9394c175484f43819e0959a9b0a5d72f_master_bbbee07d40ce4dd5a7b2995a381ed7e2.webp?alt=media&token=e507cec7-f5e0-4011-bc42-915a3324ffc8");
        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/Nh%E1%BA%ABn%20%C4%91%C3%B4i%2Fsample4%2F94c175484f43819e0959a9b0a5d72f_master_bbbee07d40ce4dd5a7b2995a381ed7e2_924a0cf7293942d28e00030fedca30b1.webp?alt=media&token=9d370171-e7b3-4635-91d4-42f38ed3ff4f");
        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/Nh%E1%BA%ABn%20%C4%91%C3%B4i%2Fsample4%2Fnckc466_1_ba2f06bf22414cfc9dd3110232f79350_master_3c8e678e53594dbe9051f0605394180e.webp?alt=media&token=58a34460-1424-4987-9cbe-d28bd1f4f37a");

        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/Nh%E1%BA%ABn%20%C4%91%C3%B4i%2Fsample4%2F2f06bf22414cfc9dd3110232f79350_master_3c8e678e53594dbe9051f0605394180e_d08755dd78a847c6832f4707821102f8.webp?alt=media&token=355340b5-4f87-470f-9d81-08bb7f5b71f7");
        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/Nh%E1%BA%ABn%20%C4%91%C3%B4i%2Fsample4%2Fnc466_1_acb49941fe154c22b1cdf524226b3823_master_c105dd97ae4042708fce06c5a15d0da3.webp?alt=media&token=9faf7419-dd0a-4116-8360-c429ef829176");
        Map<String,Integer> sizemap = new HashMap<>();
        sizemap.put("14",10);
        sizemap.put("15",10);
        sizemap.put("16",10);
        sizemap.put("17",10);


        Product testproduct = new Product("4", "Nhẫn đôi","Nhẫn đôi Vàng đánh Kim Cương NDC15 ","Vàng",imagelist,sizemap,"Kim Cương",12.6,14850000,"Sang xịn mịn","H-Jewelry");

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
        });*/
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    private LoadingDialog loadingDialog;

    ImageView img_notice;

    ArrayList<Product> myTrendList, myNewList, myDiamondList, myPearlList, myRubyList, myRingList, myAlbumList;

    RecyclerView rc_trend;
    RecyclerView rc_new;
    RecyclerView rc_diamond, rc_pearl, rc_ruby, rc_ring, rc_album;
    ProductAdapter myAdapterTrend, myAdapterNew, myAdapterDiamond, myAdapterPearl, myAdapterRuby, myAdapterRing, myAdapterAlbum;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingDialog = new LoadingDialog(getActivity());

        myTrendList= new ArrayList<>();
        myAdapterTrend = new ProductAdapter(getContext(),myTrendList);
        GetTrendListFromDataBase();
        rc_trend=view.findViewById(R.id.rc_trend);
        rc_trend.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_trend.setHasFixedSize(true);
        rc_trend.setAdapter(myAdapterTrend);

        myNewList= new ArrayList<>();
        myAdapterNew= new ProductAdapter(getContext(),myNewList);
        GetNewListFromDataBase();
        rc_new=view.findViewById(R.id.rc_new);
        rc_new.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_new.setHasFixedSize(true);
        rc_new.setAdapter(myAdapterNew);
       // myAdapterNew.notifyDataSetChanged();

        myDiamondList= new ArrayList<>();
        myAdapterDiamond=new ProductAdapter(getContext(),myDiamondList);
        GetDiamondListFromDataBase();
        rc_diamond=view.findViewById(R.id.rc_diamond);
        rc_diamond.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_diamond.setHasFixedSize(true);
        rc_diamond.setAdapter(myAdapterNew);

        myPearlList= new ArrayList<>();
        myAdapterPearl=new ProductAdapter(getContext(),myPearlList);
        GetPearlListFromDataBase();
        rc_pearl=view.findViewById(R.id.rc_pearl);
        rc_pearl.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_pearl.setHasFixedSize(true);
        rc_pearl.setAdapter(myAdapterPearl);

        myRubyList= new ArrayList<>();
        myAdapterRuby=new ProductAdapter(getContext(),myRubyList);
        GetRubyListFromDataBase();
        rc_ruby=view.findViewById(R.id.rc_ruby);
        rc_ruby.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_ruby.setHasFixedSize(true);
        rc_ruby.setAdapter(myAdapterRuby);

        myRingList= new ArrayList<>();
        myAdapterRing =new ProductAdapter(getContext(),myRingList);
        GetRingListFromDataBase();
        rc_ring=view.findViewById(R.id.rc_ring);
        rc_ring.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_ring.setHasFixedSize(true);
        rc_ring.setAdapter(myAdapterRing);

        myAlbumList= new ArrayList<>();
        myAdapterAlbum=new ProductAdapter(getContext(),myAlbumList);
        GetAlbumListFromDataBase();
        rc_album=view.findViewById(R.id.rc_album);
        rc_album.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_album.setHasFixedSize(true);
        rc_album.setAdapter(myAdapterAlbum);

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
        DatabaseReference ref = database.getReference("Product/Nhẫn đôi");

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
        DatabaseReference ref = database.getReference("Product/Nhẫn đôi");


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
    private  void GetDiamondListFromDataBase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private  void GetPearlListFromDataBase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private  void GetRubyListFromDataBase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private  void GetRingListFromDataBase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private  void GetAlbumListFromDataBase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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