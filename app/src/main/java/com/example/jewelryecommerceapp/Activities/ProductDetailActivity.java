package com.example.jewelryecommerceapp.Activities;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.jewelryecommerceapp.Adapters.CommentAdapter;
import com.example.jewelryecommerceapp.Adapters.ImageAdapter;
import com.example.jewelryecommerceapp.Adapters.ProductAdapter;
import com.example.jewelryecommerceapp.Adapters.SizeAdapter;
import com.example.jewelryecommerceapp.Adapters.ViewPagerImageAdapter;
import com.example.jewelryecommerceapp.Interfaces.SelectListener;
import com.example.jewelryecommerceapp.Models.Comment;
import com.example.jewelryecommerceapp.Models.Product;
import com.example.jewelryecommerceapp.Models.User;
import com.example.jewelryecommerceapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.A;

public class ProductDetailActivity extends AppCompatActivity {

    // xem mô tả
    LinearLayout watch_more;
    LinearLayout watch_more_layout;
    ImageView more_not, backhome , cart_but;
    // ảnh của sản phẩm
    TextView img_number ;
    RecyclerView rc_prd_image,rc_viewpager;
    ImageAdapter imgAdapter;
    ArrayList<String> imgList;
    ViewPagerImageAdapter viewPagerAdapter;
    //
    LinearLayout prd_star;
    // comment
    RecyclerView rc_cmt;
    CommentAdapter cmtAdapter;
    ArrayList<Comment> cmtList;
    TextView cmt_watch_more;
    // sản phẩm tương tự
    RecyclerView rc_same_prd;
    ProductAdapter samePrdAdapter;
    ArrayList<Product> samePrdList;
    ConstraintLayout viewpager_layout;
    // mấy cái nút
    ImageButton chat_but,add_cart_but;
    Button buy_now;
    BottomSheetDialog dialog;

    TextView Prd_name , Prd_price , prd_sold, prd_rate;



    Product productdetail = new Product() ;

    //
    int price=0;
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



        initUi();
        // lấy dữ liệu vho UI


        // lấy dữ liệu của product từ màn hình home ;

        Intent myintent  = getIntent();
       String productType = myintent.getStringExtra("type");
       String productID= myintent.getStringExtra("ID");

        // get product detail từ firebase

        GetProductDetailFromFireBase( productType, productID);


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
        //comment
        cmtList= new ArrayList<>();
        getComment(cmtList);
        cmtAdapter = new CommentAdapter(cmtList);
        rc_cmt.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rc_cmt.setHasFixedSize(true);
        rc_cmt.setAdapter(cmtAdapter);
        // xem thêm cmt
        cmt_watch_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lấy thêm cmt rồi hiển thị hoặc sang 1 activity cmt
            }
        });
        // sản phẩm tương tự
        samePrdList = new ArrayList<>();
        getSameProduct(samePrdList);
        samePrdAdapter= new ProductAdapter(this,samePrdList,1);
       // rc_same_prd.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rc_same_prd.setLayoutManager(new GridLayoutManager(this,2));
        rc_cmt.setHasFixedSize(true);
        rc_same_prd.setAdapter(samePrdAdapter);
        // nhấn mấy cái nút
        chat_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to chat

            }
        });
        add_cart_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // nếu chưa đăng nhập thì show dialog
                // if( chưa đăng nhập)
                GetDialog();
                // else (đã đăng nhập)
               /* dialog= new BottomSheetDialog(ProductDetailActivity.this);
                createDialog();
                dialog.show();
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);*/


            }
        });
        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog= new BottomSheetDialog(ProductDetailActivity.this);


                createDialog();
                dialog.show();
                dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            }
        });



        // Trở về home
        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cart_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // nếu chưa đăng nhập thì
                GetDialog();
                // nếu đăng nhập rồi thì intent tới Giỏ hàng
            }
        });
    }


    // lấy chi tiết sản phẩm từ firebase

    private void GetProductDetailFromFireBase(String productType, String productID) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Product").child(productType);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Product product =  dataSnapshot.getValue(Product.class);


                    if( product.getProductId().equals(productID))
                    {
                        productdetail= product;

                        SetUi();

                    }



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProductDetailActivity.this,"cannot get product", Toast.LENGTH_LONG).show();

            }
        });






    }

    private void SetUi() {
        imgList = new ArrayList<>( productdetail.getImagelist());





       // int s = imgList.size();
      // Toast.makeText(ProductDetailActivity.this,s+" ", Toast.LENGTH_LONG).show();

        img_number.setText(1+"/"+imgList.size());
// set giá và tên
        Prd_price.setText(formatNumber(productdetail.getProductPrice())+" VND");
        Prd_name.setText(productdetail.getProductName());
// set số lượng đã bán và ánh giá
       // prd_sold.setText(productdetail.getSold());




        // phần chứa ảnh hiển thị
        viewPagerAdapter = new ViewPagerImageAdapter(imgList, new SelectListener() {
            //click xem ảnh
            @Override
            public void onImageItemClicked(int position) {
                if (position != RecyclerView.NO_POSITION) {
                    String  imageUrl = imgList.get(position);
                    Intent intent = new Intent(ProductDetailActivity.this, ImageActivity.class);
                    intent.putStringArrayListExtra(ImageActivity.EXTRA_IMAGE_URL,imgList);
                    intent.putExtra("position",position);
                    startActivity(intent);

                }
            }
        });

        rc_viewpager.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rc_viewpager.setHasFixedSize(true);
        rc_viewpager.setAdapter(viewPagerAdapter);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(rc_viewpager);
        // vuốt qua lại các ảnh
        rc_viewpager.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // Trạng thái cuộn đã kết thúc, vị trí item hiện tại đã được cố định
                    int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    imgAdapter.setSelectedItem(position);
                    // Xử lý sự kiện tại vị trí position
                    img_number.setText(position+1+"/"+imgList.size());

                }
            }
        });


        // nhấn mấy cái ảnh nhỏ
        imgAdapter = new ImageAdapter(imgList, new SelectListener() {
            @Override
            // click vào 1 ảnh trong list các ảnh
            public void onImageItemClicked(int position) {
                rc_viewpager.smoothScrollToPosition(position);
                imgAdapter.setSelectedItem(position);
            }
        });
        // phần các ảnh khác của sản phẩm
        rc_prd_image.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        rc_prd_image.setHasFixedSize(true);
        rc_prd_image.setAdapter(imgAdapter);
        // hiển thị số sao đánh giá
        // Khởi tạo một LinearLayout.LayoutParams với chiều rộng WRAP_CONTENT và chiều cao là 20dp
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                (int) (20 * getResources().getDisplayMetrics().density)
        );

// Tính toán chiều rộng mong muốn của LinearLayout dựa trên số sao trung bình (đơn vị là dp)
        float averageRating = 4.5f; // Đây là số đánh giá trung bình, bạn có thể thay đổi
        int desiredWidthInDp = (int) (20 * averageRating); // Giả sử mỗi sao có chiều rộng 20dp

// Chuyển đổi từ dp sang px
        Resources resources = getResources();
        float density = resources.getDisplayMetrics().density;
        int desiredWidthInPx = (int) (desiredWidthInDp * density);

// Đặt chiều rộng mới tính theo px cho layoutParams
        layoutParams.width = desiredWidthInPx;

// Đặt lại LayoutParams cho LinearLayout của bạn (prd_star)
        prd_star.setLayoutParams(layoutParams);




    }

    private void initUi() {
        img_number=findViewById(R.id.img_number);
        rc_prd_image=findViewById(R.id.rc_prd_image);
        watch_more=findViewById(R.id.bt_watch_more);
        watch_more_layout=findViewById(R.id.watch_more_layout);
        more_not=findViewById(R.id.more_notmore);
        rc_cmt=findViewById(R.id.rc_comment);
        cmt_watch_more=findViewById(R.id.cmt_watch_more);
        rc_same_prd=findViewById(R.id.rc_same_prd);
        viewpager_layout=findViewById(R.id.viewpager_layout);
        rc_viewpager=findViewById(R.id.rc_viewpager);
        prd_star=findViewById(R.id.prd_star);
        chat_but=findViewById(R.id.chat_but);
        add_cart_but=findViewById(R.id.add_cart_but);
        buy_now=findViewById(R.id.buy_now_but);
        Prd_name = findViewById(R.id.prd_name);
        Prd_price = findViewById(R.id.prd_price);
        backhome = findViewById(R.id.backhome_icon);
        prd_sold = findViewById(R.id.prd_sold_number);
        prd_rate = findViewById(R.id.prd_rate);
        cart_but = findViewById(R.id.cart_button);




    }

    //
    ArrayList<String> sizeList;

    // Dialof chọn size loại
    private void createDialog(){
        View view = getLayoutInflater().inflate(R.layout.bottom_sheet_buy,null,false);
        //dialog.dismiss();
        TextView bs_sub,bs_number,bs_add,bs_price;
        Button bs_buy= view.findViewById(R.id.bs_buy);
        ImageView bs_image=view.findViewById(R.id.bs_image);
        bs_add=view.findViewById(R.id.bs_add);
        bs_sub=view.findViewById(R.id.bs_sub);
        bs_price=view.findViewById(R.id.bs_price);
        bs_number=  view.findViewById(R.id.bs_number);
        bs_image.setImageResource(R.drawable.demo2);
        RecyclerView rc_size= view.findViewById(R.id.rc_size);
        rc_size.setLayoutManager(new GridLayoutManager(this,4));


        sizeList= new ArrayList<>();
        for (Map.Entry<String, Integer> entry : productdetail.getSizeMap().entrySet()) {
            sizeList.add(entry.getKey()+"mm ");
        }


       SizeAdapter sizeAdapter= new SizeAdapter(sizeList);
        rc_size.setAdapter(sizeAdapter);
        //
        price= productdetail.getProductPrice();
        bs_price.setText(formatNumber(price));

        bs_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n=Integer.parseInt(bs_number.getText().toString());
                if(n>1)
                {
                    n=n-1;
                    bs_number.setText(n+"");
                    bs_price.setText(formatNumber(n*price));
                }
            }
        });
        bs_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n=Integer.parseInt(bs_number.getText().toString());
                n++;
                bs_number.setText(n+"");
                bs_price.setText(formatNumber(n*price));
            }
        });
        dialog.setContentView(view);

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

    ArrayList<Integer> getProductImage(ArrayList<Integer> list){
        list= new ArrayList<>();
        list.add(R.drawable.demo);
        list.add(R.drawable.demo2);
        list.add(R.drawable.demo);
        list.add(R.drawable.demo2);
        list.add(R.drawable.demo);
        list.add(R.drawable.demo2);
        list.add(R.drawable.demo);
        list.add(R.drawable.demo2);
        return  list;
    }
    void getComment(ArrayList<Comment> cmtList){
      /*  cmtList.add(new Comment(new User("User name ",R.drawable.default_avatar),new Product(),4,"Nội dung của phần comment"));
        cmtList.add(new Comment(new User("User name ",R.drawable.default_avatar),new Product(),3,"Nội dung của phần comment"));
        cmtList.add(new Comment(new User("User name ",R.drawable.default_avatar),new Product(),5,"Nội dung của phần comment"));
        cmtList.add(new Comment(new User("User name ",R.drawable.default_avatar),new Product(),5,"Nội dung của phần comment"));
        cmtList.add(new Comment(new User("User name ",R.drawable.default_avatar),new Product(),4,"Nội dung của phần comment"));*/
    }
    void getSameProduct(ArrayList<Product> samePrdList){

        /*samePrdList.add(new Product(R.drawable.demo,"Nhẫn naày đẹp vl",2900000));
        samePrdList.add(new Product(R.drawable.demo,"Nhẫn naày đẹp vl",2900000));
        samePrdList.add(new Product(R.drawable.demo,"Nhẫn naày đẹp vl",2900000));
        samePrdList.add(new Product(R.drawable.demo,"Nhẫn naày đẹp vl",2900000));
        samePrdList.add(new Product(R.drawable.demo,"Nhẫn naày đẹp vl",2900000));
        samePrdList.add(new Product(R.drawable.demo,"Nhẫn naày đẹp vl",2900000));
        samePrdList.add(new Product(R.drawable.demo,"Nhẫn naày đẹp vl",2900000));*/
    }

    void GetDialog()
    {
        AlertDialog.Builder mydialog = new AlertDialog.Builder(ProductDetailActivity.this);
        mydialog.setTitle("Thông báo!");
        mydialog.setMessage("Bạn cần phải đăng nhập!");
        mydialog.setIcon(R.drawable.ic_alarm);
        mydialog.setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(ProductDetailActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        mydialog.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              dialog.cancel();
            }
        });
        mydialog.create().show();
    }
}