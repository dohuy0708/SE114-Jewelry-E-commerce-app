package com.example.jewelryecommerceapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Activities.LoadingDialog;
import com.example.jewelryecommerceapp.Activities.Payment;
import com.example.jewelryecommerceapp.Activities.ProductDetailActivity;
import com.example.jewelryecommerceapp.Adapters.CartProductsAdapter;
import com.example.jewelryecommerceapp.Models.CartItem;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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


        // nếu chưa thì show dialog
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);



    }

    ImageView logo;
    private LoadingDialog loadingDialog;
    ArrayList<CartItem> Pros;
    RecyclerView inCartPros;
    CartProductsAdapter Adt;
    TextView tienspp;
    TextView totalll;
    TextView promotee;
    Button ordernoww;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingDialog = new LoadingDialog(getActivity());
        Pros = new ArrayList<>();
        inCartPros = view.findViewById(R.id.Cartt);



        logo = view.findViewById(R.id.logo);
        tienspp = view.findViewById(R.id.tiensp);
        promotee = view.findViewById(R.id.promote);
        ordernoww = view.findViewById(R.id.ordernow);
        totalll = view.findViewById(R.id.tccc);
        totalll.setText("Tổng cộng: ");//+ String.valueOf(Integer.parseInt(tienspp.getText().toString())-Integer.parseInt(promotee.getText().toString())));
        ordernoww.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Payment.class);
                startActivity(i);
            }
        });
        //  Kiem tra da dang nhap hay chua
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            // showToastWithIcon(R.drawable.attention_icon,"Bạn cần Đăng nhập để xem Giỏ hàng!");

        } else {
            String userID = user.getUid();
            GetCartItemFromFireBase(userID);
        }


    }

    private void SetUI() {
        Adt = new CartProductsAdapter(getContext(), Pros);

        inCartPros.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        inCartPros.setHasFixedSize(true);
        inCartPros.setAdapter(Adt);

    }


    private void GetCartItemFromFireBase(String userID) {
        loadingDialog.show();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Cart").child(userID);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // DataSnapshot là tổng các Product , chứa các item trong đó, khi getChildren() , thì ta sẽ lấy từng item  .
               Pros.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CartItem item = dataSnapshot.getValue(CartItem.class);
                    Pros.add(item);

                }

                /*// Sử dụng một map để theo dõi số lượng sản phẩm
                Map<String, CartItem> productMap = new HashMap<>();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CartItem item = dataSnapshot.getValue(CartItem.class);

                    if (item != null) {
                        // Kiểm tra nếu sản phẩm đã tồn tại trong map
                        if (productMap.containsKey(item.getProductName())) {
                            // Tăng amount của sản phẩm đã tồn tại
                            CartItem existingItem = productMap.get(item.getProductName());
                            existingItem.setAmount(existingItem.getAmount() + item.getAmount());
                        } else {
                            // Thêm sản phẩm mới vào map
                            productMap.put(item.getProductName(), item);
                        }
                    }
                }

                // Thêm tất cả sản phẩm từ map vào danh sách Pros
                Pros.addAll(productMap.values());

                //   Toast.makeText(getActivity(),"Finish", Toast.LENGTH_LONG).show();
*/
                SetUI();
                loadingDialog.cancel();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //   Toast.makeText(getActivity(),"Fail",Toast.LENGTH_LONG).show();
            }
        });



    }




    public void showToastWithIcon(int icon, String message) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        // Tùy chỉnh icon và văn bản trong toast
        ImageView imageView = layout.findViewById(R.id.toast_icon);
        imageView.setImageResource(icon); // Thay 'your_icon' bằng tên icon của bạn
        TextView textView = layout.findViewById(R.id.toast_text);
        textView.setText(message);

        // Tạo và hiển thị toast custom
        Toast toast = new Toast(getActivity());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}

