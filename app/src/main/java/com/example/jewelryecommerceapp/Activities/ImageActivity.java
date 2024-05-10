package com.example.jewelryecommerceapp.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.jewelryecommerceapp.Adapters.ViewPagerImageAdapter;
import com.example.jewelryecommerceapp.Interfaces.SelectListener;
import com.example.jewelryecommerceapp.R;

import java.util.List;

public class ImageActivity extends AppCompatActivity {
    public static final String EXTRA_IMAGE_URL = "extra_image_url";
    TextView img_num;
    int position;
    private ViewPager2 viewPager;
    private List<String> imageUrls;
    ImageView back_but;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_image);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewPager = findViewById(R.id.viewPager);
        imageUrls = getIntent().getStringArrayListExtra(EXTRA_IMAGE_URL);
        position= getIntent().getIntExtra("position",0);
        img_num= findViewById(R.id.img_num);
       // img_num.setText(position+1+"/"+imageUrls.size());

        ViewPagerImageAdapter adapter = new ViewPagerImageAdapter(imageUrls, new SelectListener() {
            @Override
            public void onImageItemClicked(int imgUrl) {
                // Xử lý sự kiện click trên ảnh tại đây
            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                img_num.setText(position+1+"/"+imageUrls.size());

            }
        });
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
        back_but = findViewById(R.id.back_but);
        back_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}