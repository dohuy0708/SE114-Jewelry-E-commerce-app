package com.example.jewelryecommerceapp.Activities;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jewelryecommerceapp.Adapters.CartProductsAdapter;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {

    ImageView back;
    RecyclerView pross;
    CartProductsAdapter adt;
    ArrayList<Product> listpro;
    TextView add;
    TextView shipmoney;
    TextView totalpro;
    TextView totalord;
    TextView payway;

    TextView choosevoucher;
    Button pay;
    Button setaddr;
    Button setpayway;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        EdgeToEdge.enable(this);

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
        setpayway=findViewById(R.id.btnwaypay);
        add=findViewById(R.id.addd);
        shipmoney=findViewById(R.id.shipfee);
        totalpro=findViewById(R.id.totalp);
        totalord=findViewById(R.id.totalord);
        payway=findViewById(R.id.payway);
        choosevoucher=findViewById(R.id.shipvou);
        choosevoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        setaddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        setpayway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void initListPro(ArrayList<Product> listpro) {
    }
}