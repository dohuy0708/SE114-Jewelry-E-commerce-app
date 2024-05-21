package com.example.jewelryecommerceapp.Adapters;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jewelryecommerceapp.Models.CartItem;
import com.example.jewelryecommerceapp.R;
import com.example.jewelryecommerceapp.Models.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
public class CartProductsAdapter extends RecyclerView.Adapter<CartProductsAdapter.ProductViewHolder> {

    Context context;
    ArrayList<CartItem> listPro;
    public CartProductsAdapter(Context context, ArrayList<CartItem> listPro)
    {
        this.context=context;
        this.listPro=listPro;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder_cart, parent, false);
        return new ProductViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position){


        CartItem pro = listPro.get(position);
        if (pro==null)
            return;

        holder.check.setChecked(pro.getIsChoose() == 1);


        // Lắng nghe sự kiện click vào checkbox
        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("Ckickcheckbox","onclick");
                // Cập nhật giá trị của isChoose dựa trên trạng thái của checkbox
                pro.setIsChoose(isChecked ? 1 : 0);

                // Thông báo cho Adapter biết dữ liệu đã thay đổi để cập nhật giao diện
               // notifyItemChanged(position);

                // Gọi hàm để cập nhật dữ liệu trên Firebase
                String userID = pro.getUserID();
                String productName = pro.getProductName();
                UpdateCheckInFireBase(userID, productName, pro);
            }
        });


        holder.namePro.setText(pro.getProductName());
       // holder.imgPro.setImageResource(pro.getImg());
        CartProductsAdapter.ProductViewHolder productViewHolder= (CartProductsAdapter.ProductViewHolder) holder;
        Glide.with(context).load(pro.getImage()).into(((ProductViewHolder) holder).imgPro);

        // set dữ liệu cho number
        holder.numm.setText(pro.getAmount()+"");

     /*   String s=holder.numm.getText().toString();
        int numb=Integer.parseInt(s);*/

        holder.pricee.setText("Đơn giá: "+ formatNumber(pro.getProductPrice())+"đ");
        holder.sizee.setText("Size: "+pro.getSize());
        holder.pluss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pro.setAmount(pro.getAmount()+1);

                notifyItemChanged(position);
                    PlusAmountofIteminFirebase(pro.getUserID(),pro.getProductName(),pro);


              //  notifyItemChanged(position);
                /*String nt=holder.numm.getText().toString();
                if(!TextUtils.isEmpty(nt))
                {
                    int numb=Integer.parseInt(nt);
                    numb++;
                    holder.numm.setText(String.valueOf(numb));
                }
                else
                {
                    holder.numm.setError("Vui lòng nhập số!");
                }*/
            }
        });
        holder.minuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if(!TextUtils.isEmpty(holder.numm.getText().toString()))
                {
                    int numb=Integer.parseInt(holder.numm.getText().toString());
                    numb--;
                    if(numb>=1)
                    {
                        holder.numm.setText(String.valueOf(numb));
                    }
                    else
                    {
                        holder.numm.setText("1");
                    }
                }
                else
                {
                    holder.numm.setError("Vui lòng nhập số!");
                }*/

                if(pro.getAmount()==1)
                {
                    // hiện lỗi gì đó
                }
                else
                {
                    pro.setAmount(pro.getAmount()-1);

                    notifyItemChanged(position);
                    SubAmountofIteminFirebase(pro.getUserID(),pro.getProductName(),pro);
                }
            }
        });
        holder.binn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPro.remove(pro);
                notifyItemRemoved(position);
                String userID = pro.getUserID();
                String productName = pro.getProductName();
                RemoveCartItemInFireBase(userID,productName,pro);
            }
        });

    }

    private void SubAmountofIteminFirebase(String userID, String productName, CartItem pro) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Cart").child(userID);
        DatabaseReference itemRef = database.getReference("Cart").child(userID);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CartItem item = dataSnapshot.getValue(CartItem.class);
                    if (item.getProductName().equals(productName)) {

                        // Tìm thấy sản phẩm cần cập nhật, sử dụng key của nó để cập nhật dữ liệu
                        String key = dataSnapshot.getKey();
                        //   DatabaseReference itemRef = ref.child(key);
                        itemRef.child(key).setValue(pro, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                            }
                        }); // Cập nhật dữ liệu mới
                        break; // Kết thúc vòng lặp sau khi cập nhật
                    }
                }
                //return 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu có
            }
        });
    }


    //////???? đang lam f
    private void PlusAmountofIteminFirebase(String userID,String productName, CartItem pro) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Cart").child(userID);
        DatabaseReference itemRef = database.getReference("Cart").child(userID);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CartItem item = dataSnapshot.getValue(CartItem.class);
                    if (item.getProductName().equals(productName)) {

                        // Tìm thấy sản phẩm cần cập nhật, sử dụng key của nó để cập nhật dữ liệu
                        String key = dataSnapshot.getKey();
                        //   DatabaseReference itemRef = ref.child(key);
                        itemRef.child(key).setValue(pro, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                            }
                        }); // Cập nhật dữ liệu mới
                        break; // Kết thúc vòng lặp sau khi cập nhật
                    }
                }
              //  return 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu có
            }
        });

    }

    private void RemoveCartItemInFireBase(String userID, String productName, CartItem pro) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Cart").child(userID);
        DatabaseReference itemRef = database.getReference("Cart").child(userID);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                     CartItem item = dataSnapshot.getValue(CartItem.class);
                    // thêm vòng lặp for , tìm hết tất cả sản phẩm có tên giống thì xóa r
                    if (item.getProductName().equals(productName)) {

                        // Tìm thấy sản phẩm cần cập nhật, sử dụng key của nó để cập nhật dữ liệu
                        String key = dataSnapshot.getKey();
                          itemRef.child(key).removeValue(new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                if (error != null) {
                                 } else {
                                 }
                            }
                        });
                        break; // Kết thúc vòng lặp sau khi cập nhật
                    }
                }
               // return 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu có
            }
        });
    }

    private void UpdateCheckInFireBase(String userID, String productName, CartItem pro) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Cart").child(userID);
        DatabaseReference itemRef = database.getReference("Cart").child(userID);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                     CartItem item = dataSnapshot.getValue(CartItem.class);
                    if (item.getProductName().equals(productName)) {

                        // Tìm thấy sản phẩm cần cập nhật, sử dụng key của nó để cập nhật dữ liệu
                        String key = dataSnapshot.getKey();
                         //   DatabaseReference itemRef = ref.child(key);
                        itemRef.child(key).setValue(pro, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {

                            }
                        }); // Cập nhật dữ liệu mới
                        break; // Kết thúc vòng lặp sau khi cập nhật
                    }
                }
               // return 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi nếu có
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPro.size();
    }





    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPro;
        TextView namePro;
        TextView pricee;
        TextView pluss;
        TextView minuss;
        EditText numm;
        CheckBox check;
       TextView binn;
        TextView sizee;


        public ProductViewHolder(@NonNull View view) {
            super(view);
            imgPro = view.findViewById(R.id.pic);
            namePro = view.findViewById(R.id.titletext);
            pricee = view.findViewById(R.id.price);
            pluss = view.findViewById(R.id.plusbtn);
            minuss = view.findViewById(R.id.minusbtn);
            numm = view.findViewById(R.id.number);
            check = view.findViewById(R.id.checking);
            binn = view.findViewById(R.id.bin);
            sizee=view.findViewById(R.id.sz);

            numm.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString()=="0")
                    {
                        numm.setText("1");
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            numm.setFilters(new InputFilter[]{new InputFilter() {
                @Override
                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                    for (int i = start; i < end; i++) {
                        if (!isValidCharacter(source.charAt(i))) {
                            return "";
                        }
                    }
                    return null;
                }
            }});
//        }
//    }
//
//    private boolean isValidInput(String string) {
//        try
//        {
//            int i=Integer.parseInt(string);
//            return i>=1;
//        }
//        catch(NumberFormatException e)
//        {
//            return false;
//        }
//    }
        }

        private boolean isValidCharacter(char c) {
            return Character.isDigit(c);
        }
    }
    public static String formatNumber(int number) {
        String strNumber = String.valueOf(number); // Chuyển đổi số thành chuỗi
        int length = strNumber.length(); // Độ dài của chuỗi số

        // Xây dựng chuỗi kết quả từ phải sang trái, thêm dấu chấm sau mỗi 3 ký tự
        StringBuilder result = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            result.insert(0, strNumber.charAt(i));
            if ((length - i) % 3 == 0 && i != 0) {
                result.insert(0, ".");
            }
        }

        return result.toString(); // Trả về chuỗi kết quả
    }
}

