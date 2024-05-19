package com.example.jewelryecommerceapp.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jewelryecommerceapp.Activities.LoadingDialog;
import com.example.jewelryecommerceapp.Activities.NoticeActivity;
import com.example.jewelryecommerceapp.Activities.ProductDetailActivity;
import com.example.jewelryecommerceapp.Activities.SearchActivity;
import com.example.jewelryecommerceapp.Adapters.ProductAdapter;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.Models.Voucher;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class HomeFragment extends Fragment {



    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {



        }


        ///  Đẩy dữ liệu lên firebase
        //   StorageReference storageReference = FirebaseStorage.getInstance().getReference("Vòng tay");


       /* ArrayList<String> imagelist = new ArrayList<>();
        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/Nh%E1%BA%ABn%2Fsample8%2Fsnztxmw060007-nhan-bac-pnjsilver-01.png?alt=media&token=c5cdb449-87c4-4607-a227-cd19f4c56417");
        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/Nh%E1%BA%ABn%2Fsample8%2Fsnztxmw060007-nhan-bac-pnjsilver-02.png?alt=media&token=659d7394-cc5c-4737-bc69-45617dc033e7");
        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/Nh%E1%BA%ABn%2Fsample8%2Fsnztxmw060007-nhan-bac-pnjsilver-03.png?alt=media&token=7641b0f5-e3eb-444c-865f-d0178d971c8c");
        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/Nh%E1%BA%ABn%2Fsample8%2Fsnztxmw060007-nhan-bac-pnjsilver-04.jpg?alt=media&token=6158040e-c393-45ca-84f0-c60479ae4d33");

        imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/Nh%E1%BA%ABn%2Fsample8%2Fsnztxmw060007-nhan-bac-pnjsilver-05.jpg?alt=media&token=748836db-7743-4c91-86cc-6ce1c350b4b7");
       // imagelist.add("https://firebasestorage.googleapis.com/v0/b/jewelry-b2dcd.appspot.com/o/Nh%E1%BA%ABn%20%C4%91%C3%B4i%2Fsample4%2Fnc466_1_acb49941fe154c22b1cdf524226b3823_master_c105dd97ae4042708fce06c5a15d0da3.webp?alt=media&token=9faf7419-dd0a-4116-8360-c429ef829176");
        Map<String,Integer> sizemap = new HashMap<>();
        sizemap.put("14",10);
        sizemap.put("15",10);
        sizemap.put("16",10);
        sizemap.put("17",10);



        Product testproduct = new Product("8", "Nhẫn","Nhẫn Vàng trắng đính đá RedPearl ","Vàng trắng",imagelist,sizemap,"đá RedPearl",11.6,6250000,"Sang xịn mịn","H-Jewelry");


        FirebaseDatabase data = FirebaseDatabase.getInstance();
        DatabaseReference ref = data.getReference("Sản phẩm bán chạy")  ;


        ref.push().setValue(testproduct, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    // Đã xảy ra lỗi khi đẩy dữ liệu lên Firebase
                   Toast.makeText(getActivity(),error.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    // Đẩy dữ liệu lên Firebase thành công
                    Toast.makeText(getActivity(),"Finish", Toast.LENGTH_LONG).show();
                }
            }
        });*/
    }


        // Tạo đối tượng Voucher với các giá trị ngày dạng String
       /* Voucher voucher = new Voucher("n2", "QTPN83",
                "Nhân ngày Quốc tế phụ nữ cửa hàng khuyến mãi 250k cho hóa đơn trên 8300000.",
                 250000, 83000000,"2024-03-5", "2024-03-08");

        // Khởi tạo Firebase
        FirebaseDatabase data = FirebaseDatabase.getInstance();
        DatabaseReference ref = data.getReference("Voucher");

        // Đẩy đối tượng Voucher lên Firebase
        ref.push().setValue(voucher, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if (error != null) {
                    Toast.makeText(getActivity(), "Đẩy voucher thất bại: " + error.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Đẩy voucher thành công", Toast.LENGTH_LONG).show();
                }
            }
        });*/







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    private LoadingDialog loadingDialog;

    ImageView img_notice, but_search,but_chat;

    ArrayList<Product> myTrendList, myNewList, myCoupleList, myDiamondList, mySetList, myPearlList;

    RecyclerView rc_trend;
    EditText input_search;

    RecyclerView rc_new, rc_diamond, rc_pearl,rc_couple, rc_set;
    ProductAdapter myAdapterTrend, myAdapterNew, myAdapterCouple, myAdapterDiamond, myAdapterSet, myAdapterPearl;

    TextView see_detail_trend, see_detail_new,see_detail_diamond,see_detail_pearl,see_detail_set, see_detail_couple;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingDialog = new LoadingDialog(getActivity());



        view.findViewById(R.id.bt_gold_price).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://bieudogiavang.vn/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

                // Kiểm tra xem có trình duyệt nào có thể xử lý Intent này không
                if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                    // Mở trình duyệt với đường dẫn web đã chỉ định
                    startActivity(intent);
                } else {
                    // Xử lý khi không tìm thấy trình duyệt nào
                    Toast.makeText(requireContext(), "Không tìm thấy trình duyệt.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Khai báo LIST và ADapter
        myTrendList= new ArrayList<>();
        //initProduct(myList);

        myAdapterTrend = new ProductAdapter(getContext(), myTrendList, new ProductAdapter.IClickListener() {
            @Override
            public void OnClickItem(String productType, String productID) {

                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("type", productType);
                intent.putExtra("ID", productID);
                startActivity(intent);
            }
        });
        myNewList= new ArrayList<>();
        //initProduct(myList);


        myAdapterNew= new ProductAdapter(getContext(), myNewList, new ProductAdapter.IClickListener() {
            @Override
            public void OnClickItem(String productType, String productID) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("type", productType);
                intent.putExtra("ID", productID);
               startActivity(intent);
            }
        });
        myCoupleList = new ArrayList<>();
        myAdapterCouple = new ProductAdapter(getContext(), myCoupleList, new ProductAdapter.IClickListener() {
            @Override
            public void OnClickItem(String productType, String productID) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("type", productType);
                intent.putExtra("ID", productID);
                startActivity(intent);
            }
        });
        myPearlList = new ArrayList<>();
        myAdapterPearl = new ProductAdapter(getContext(), myPearlList, new ProductAdapter.IClickListener() {
            @Override
            public void OnClickItem(String productType, String productID) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("type", productType);
                intent.putExtra("ID", productID);
                startActivity(intent);
            }
        });
        myDiamondList = new ArrayList<>();
        myAdapterDiamond = new ProductAdapter(getContext(), myDiamondList, new ProductAdapter.IClickListener() {
            @Override
            public void OnClickItem(String productType, String productID) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("type", productType);
                intent.putExtra("ID", productID);
                startActivity(intent);
            }
        });

        mySetList = new ArrayList<>();
        myAdapterSet = new ProductAdapter(getContext(), mySetList, new ProductAdapter.IClickListener() {
            @Override
            public void OnClickItem(String productType, String productID) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra("type", productType);
                intent.putExtra("ID", productID);
                startActivity(intent);
            }
        });


        // Lấy dữ liệu từ firebase
        GetTrendListFromDataBase();
        GetNewListFromDataBase();
        GetSetListFromDataBase();
        GetDiamondListFromDataBase();
        GetCoupleListFromDataBase();
        GetPearListFromDataBase();



        // tham chiếu sang UI
        rc_trend=view.findViewById(R.id.rc_trend);
        rc_trend.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_trend.setHasFixedSize(true);
        rc_trend.setAdapter(myAdapterTrend);

        rc_new=view.findViewById(R.id.rc_new);
        //newList.setLayoutManager(new GridLayoutManager(getContext(),2));
        rc_new.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_new.setHasFixedSize(true);
        rc_new.setAdapter(myAdapterNew);

        rc_diamond=view.findViewById(R.id.rc_diamond);
        rc_diamond.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_diamond.setHasFixedSize(true);
        rc_diamond.setAdapter(myAdapterDiamond);

        rc_pearl=view.findViewById(R.id.rc_pearl);
        rc_pearl.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_pearl.setHasFixedSize(true);
        rc_pearl.setAdapter(myAdapterPearl);

        rc_couple=view.findViewById(R.id.rc_couple);
        rc_couple.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_couple.setHasFixedSize(true);
        rc_couple.setAdapter(myAdapterCouple);

        rc_set=view.findViewById(R.id.rc_set);
        rc_set.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        rc_set.setHasFixedSize(true);
        rc_set.setAdapter(myAdapterSet);



        // xử lí sự kieenj click nut thông báo
        img_notice=view.findViewById(R.id.img_notice);
        img_notice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NoticeActivity.class);
                startActivity(intent);
            }
        });

        // xử lí sự kiện click nút Search
        input_search = view.findViewById(R.id.editText);
        but_search = view.findViewById(R.id.but_search);
        but_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = input_search.getText().toString();
                if(input.trim().equals(""))
                {
                    // chưa điền dữ liệu vô thì không search được
                }
                else {
                    Intent intent = new Intent(getContext(), SearchActivity.class);
                    intent.putExtra("input",input);
                    intent.putExtra("categoryitem","Sản phẩm tìm kiếm");
                    startActivity(intent);

                }
            }
        });
        // xử lí sự kiện click nút chat
        but_chat = view.findViewById(R.id.but_chat);
        but_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //xử lí sự kiện bấm nút xem thêm
        see_detail_trend = view.findViewById(R.id.see_detail_trend);
        see_detail_new = view.findViewById(R.id.see_detail_new);
        see_detail_diamond= view.findViewById(R.id.see_detail_diamond);
        see_detail_pearl = view.findViewById(R.id.see_detail_pearl);
        see_detail_couple= view.findViewById(R.id.see_detail_couple);
        see_detail_set = view.findViewById(R.id.see_detail_set);


        see_detail_trend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "Sản phẩm bán chạy";
                String input = " ";
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("input",input);
                intent.putExtra("categoryitem",category);
                startActivity(intent);

            }
        });

        see_detail_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "Sản phẩm mới";
                String input = " ";
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("input",input);
                intent.putExtra("categoryitem",category);
                startActivity(intent);

            }
        });

        see_detail_diamond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "Trang sức Kim Cương";
                String input = "Kim cương";
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("input",input);
                intent.putExtra("categoryitem",category);
                startActivity(intent);

            }
        });

        see_detail_pearl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "Trang sức Ngọc Trai";
                String input = "Ngọc trai";
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("input",input);
                intent.putExtra("categoryitem",category);
                startActivity(intent);

            }
        });

        see_detail_couple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "Nhẫn đôi";
                String input = "Nhẫn đôi";
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("input",input);
                intent.putExtra("categoryitem",category);
                startActivity(intent);

            }
        });

        see_detail_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = "Bộ trang sức";
                String input = "Bộ trang sức";
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("input",input);
                intent.putExtra("categoryitem",category);
                startActivity(intent);

            }
        });




    }


    private void GetPearListFromDataBase() {
        loadingDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // DataSnapshot là tổng các Product , chứa các item trong đó, khi getChildren() , thì ta sẽ lấy từng item  .
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    for (DataSnapshot productSnapshot : categorySnapshot.getChildren()) {
                        Product product = productSnapshot.getValue(Product.class);
                        if (product != null && product.getAccessory().equals("Ngọc trai")) {
                            myPearlList.add(product);
                        }
                    }
                }
                //   Toast.makeText(getActivity(),"Finish", Toast.LENGTH_LONG).show();
                myAdapterPearl.notifyDataSetChanged();
                loadingDialog.cancel();
              //  return 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //   Toast.makeText(getActivity(),"Fail",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void GetCoupleListFromDataBase() {
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
                    myCoupleList.add(product);

                }
                //   Toast.makeText(getActivity(),"Finish", Toast.LENGTH_LONG).show();
                myAdapterCouple.notifyDataSetChanged();
                loadingDialog.cancel();
              //  return 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //   Toast.makeText(getActivity(),"Fail",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void GetDiamondListFromDataBase() {
        loadingDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // DataSnapshot là tổng các Product , chứa các item trong đó, khi getChildren() , thì ta sẽ lấy từng item  .
                for (DataSnapshot categorySnapshot : snapshot.getChildren()) {
                    for (DataSnapshot productSnapshot : categorySnapshot.getChildren()) {
                        Product product = productSnapshot.getValue(Product.class);
                        if (product != null && product.getAccessory().equals("Kim cương")) {
                            myDiamondList.add(product);
                        }
                    }
                }
                //   Toast.makeText(getActivity(),"Finish", Toast.LENGTH_LONG).show();
                myAdapterDiamond.notifyDataSetChanged();
                loadingDialog.cancel();
                //return 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //   Toast.makeText(getActivity(),"Fail",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void GetSetListFromDataBase() {
        loadingDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product/Bộ trang sức");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // DataSnapshot là tổng các Product , chứa các item trong đó, khi getChildren() , thì ta sẽ lấy từng item  .
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Product product = dataSnapshot.getValue(Product.class);
                    mySetList.add(product);

                }
                //   Toast.makeText(getActivity(),"Finish", Toast.LENGTH_LONG).show();
                myAdapterSet.notifyDataSetChanged();
                loadingDialog.cancel();
              //  return 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //   Toast.makeText(getActivity(),"Fail",Toast.LENGTH_LONG).show();
            }
        });
    }

    // Lấy dữ liệu từ Database xuống ListTrend
    private void GetNewListFromDataBase() {
        loadingDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Sản phẩm mới");

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
                //return 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
             //   Toast.makeText(getActivity(),"Fail",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void GetTrendListFromDataBase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Sản phẩm bán chạy");



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
              //  return 0;
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