package com.example.jewelryecommerceapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jewelryecommerceapp.Models.Address;
import com.example.jewelryecommerceapp.R;

import java.util.ArrayList;

public class AddressBookActivity extends AppCompatActivity {
    private Button btnAddaddress;
    private ImageView Back;
    private ListView lvAddressBook;
    private ArrayAdapter<Address> addresses=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.id.address_book);
        initView();
        setupButton();
    }
    private void initView()
    {
        Back = findViewById(R.id.imageview_back_AddressBook);
        btnAddaddress = findViewById(R.id.btn_add_address);
    }
    private void setupButton()
    {
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void setupListView()
    {

        lvAddressBook = findViewById(R.id.listview_addressBook);

    }


}
