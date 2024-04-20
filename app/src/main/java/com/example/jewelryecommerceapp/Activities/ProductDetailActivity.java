package com.example.jewelryecommerceapp.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.example.jewelryecommerceapp.Adapters.CommentAdapter;
import com.example.jewelryecommerceapp.Adapters.ImageAdapter;
import com.example.jewelryecommerceapp.Models.Comment;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.Models.User;
import com.example.jewelryecommerceapp.R;

public class ProductDetailActivity extends AppCompatActivity {

    LinearLayout watch_more;
    LinearLayout watch_more_layout;
    ImageView more_not;

    // ảnh của sản phẩm
    RecyclerView rc_prd_image;
    ImageAdapter imgAdapter;
    ArrayList<Integer> imgList;

    // comment

    RecyclerView rc_cmt;
    CommentAdapter cmtAdapter;
    ArrayList<Comment> cmtList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // xem thêm
        watch_more=findViewById(R.id.bt_watch_more);
        watch_more_layout=findViewById(R.id.watch_more_layout);
        more_not=findViewById(R.id.more_notmore);
        // lấy các ảnh sản phẩm và gan vào rcview
        imgList = new ArrayList<>();
        getProductImage(imgList);
        imgAdapter = new ImageAdapter(imgList);
        rc_prd_image=findViewById(R.id.rc_prd_image);
        rc_prd_image.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rc_prd_image.setHasFixedSize(true);
        rc_prd_image.setAdapter(imgAdapter);

        // lấy các comment
        cmtList= new ArrayList<>();
       getComment(cmtList);

        cmtAdapter = new CommentAdapter(cmtList);
        rc_cmt=findViewById(R.id.rc_comment);
        rc_cmt.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rc_cmt.setHasFixedSize(true);
       rc_cmt.setAdapter(cmtAdapter);
        // nhấn vào xem mô tả
        watch_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(watch_more_layout.getLayoutParams().height==0) {
                    watch_more_layout.getLayoutParams().height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    more_not.setImageResource(R.drawable.ic_not_more);
                }
                else
                {
                    watch_more_layout.getLayoutParams().height=0;
                    more_not.setImageResource(R.drawable.ic_more);
                }
                watch_more_layout.requestLayout();
            }
        });


    }
    void getProductImage(ArrayList<Integer> list){
        list.add(R.drawable.demo);
        list.add(R.drawable.demo);
        list.add(R.drawable.demo);
        list.add(R.drawable.demo);
        list.add(R.drawable.demo);
        list.add(R.drawable.demo);
        list.add(R.drawable.demo);
        list.add(R.drawable.demo);
    }
    void getComment(ArrayList<Comment> cmtList){
        cmtList.add(new Comment(new User("User name ",R.drawable.default_avatar),new Product(),4,"Nội dung của phần comment"));
        cmtList.add(new Comment(new User("User name ",R.drawable.default_avatar),new Product(),3,"Nội dung của phần comment"));
        cmtList.add(new Comment(new User("User name ",R.drawable.default_avatar),new Product(),5,"Nội dung của phần comment"));
        cmtList.add(new Comment(new User("User name ",R.drawable.default_avatar),new Product(),5,"Nội dung của phần comment"));
        cmtList.add(new Comment(new User("User name ",R.drawable.default_avatar),new Product(),4,"Nội dung của phần comment"));

    }
}