package com.example.jewelryecommerceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Adapters.CartProductsAdapter;
import com.example.jewelryecommerceapp.Models.CartItem;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {

    ImageView back;
    RecyclerView pross;
    CartProductsAdapter adt;
    ArrayList<CartItem> listpro;
    TextView add;
    TextView shipmoney;
    TextView totalpro;
    TextView totalord;
    Button pay;
    Button setaddr;
    EditText promotee;
    Spinner choosepay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        EdgeToEdge.enable(this);

        Intent myintent  = getIntent();
        String productType = myintent.getStringExtra("type");
        String productID= myintent.getStringExtra("ID");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        pross=findViewById(R.id.ttpros);
        listpro=new ArrayList<>();
        initListPro(listpro);
        adt=new CartProductsAdapter(this, listpro);
        pross.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        pross.setHasFixedSize(true);
        pross.setAdapter(adt);
        back=findViewById(R.id.btnbackkk);
        pay=findViewById(R.id.btnpay);
        setaddr=findViewById(R.id.btnadd);
        add=findViewById(R.id.addd);
        shipmoney=findViewById(R.id.shipfee);
        totalpro=findViewById(R.id.totalp);
        totalord=findViewById(R.id.totalord);
        promotee = findViewById(R.id.inputvoucher);
        choosepay=findViewById(R.id.spn);
        totalpro.setText("Tổng giá trị sản phẩm: ");
        totalord.setText("Tổng hóa đơn: ");
        shipmoney.setText("Phí vận chuyển: ");

        ArrayAdapter<CharSequence> adapterr=ArrayAdapter.createFromResource(this, R.array.items_array, android.R.layout.simple_spinner_item);
        adapterr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        choosepay.setAdapter(adapterr);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedWay=choosepay.getSelectedItem().toString();
                if(selectedWay=="Thanh toán trực tiếp")
                {

                }
                if(selectedWay=="Thanh toán trực tuyến")
                {

                }
            }
        });
        setaddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
    private void initListPro(ArrayList<CartItem> listpro) {
    }
}