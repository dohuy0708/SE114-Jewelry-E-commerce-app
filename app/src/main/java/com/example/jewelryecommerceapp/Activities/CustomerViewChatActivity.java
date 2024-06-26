package com.example.jewelryecommerceapp.Activities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Models.User;
import com.example.jewelryecommerceapp.R;
import com.example.jewelryecommerceapp.Adapters.adapter_message;
import com.example.jewelryecommerceapp.item.message_object;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
public class CustomerViewChatActivity extends AppCompatActivity {

    EditText mgetmessage;
    LinearLayout msendmessagebutton;

    CardView msendmessagecardview;
    androidx.appcompat.widget.Toolbar mtoolbarofspecificchat;
    TextView mnameofspecificuser;

    private String enteredmessage;
    Intent intent;
    String mrecievername, sendername, mrecieveruid, msenderuid;
    private String userName;
    private String userImage;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String senderroom, recieverroom;

    Button mbackbuttonofspecificchat;

    RecyclerView mmessagerecyclerview;

    String currenttime;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    adapter_message messagesAdapter;
    ArrayList<message_object> messagesArrayList;

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mgetmessage = findViewById(R.id.getmessage);
        msendmessagecardview = findViewById(R.id.carviewofsendmessage);
        msendmessagebutton = findViewById(R.id.imageviewsendmessage);
        mtoolbarofspecificchat = findViewById(R.id.toolbarofspecificchat);
        mnameofspecificuser = findViewById(R.id.Nameofspecificuser);
        mbackbuttonofspecificchat = findViewById(R.id.backbuttonofspecificchat);

        messagesArrayList = new ArrayList<>();
        mmessagerecyclerview = findViewById(R.id.recyclerviewofspecific);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mmessagerecyclerview.setLayoutManager(linearLayoutManager);

        setSupportActionBar(mtoolbarofspecificchat);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("hh:mm a");


        msenderuid = firebaseAuth.getUid();
        if (msenderuid == null) {
            Intent intent = new Intent (CustomerViewChatActivity.this, LoginActivity.class);
            startActivity(intent);
        }


        mrecieveruid = "budf9eXCvEVnayhfjn8RW3c8vrP2";

        senderroom = msenderuid + mrecieveruid;
        recieverroom = mrecieveruid + msenderuid;


        DatabaseReference databaseReference = firebaseDatabase.getReference().
                child("chats").child(senderroom).child("messages");
        messagesAdapter = new adapter_message(CustomerViewChatActivity.this, messagesArrayList);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messagesArrayList.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    message_object messages = snapshot1.getValue(message_object.class);
                    messagesArrayList.add(messages);
                }
                messagesAdapter.notifyDataSetChanged();
                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("NGUOIDUNG").child(msenderuid);
                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot userSnapshot) {
                        User user = userSnapshot.getValue(User.class);
                        if (user != null) {
                            userName = user.getNAME();
                            userImage = user.getIMG();
                            // Bây giờ bạn đã có tên và URL ảnh của người dùng, có thể dùng để tạo đối tượng User mới
                            Log.d("User Info", "User Name: " + userName + ", User Image: " + userImage);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Xử lý lỗi nếu cần
                        Log.e("Firebase", "Failed to load user data", error.toException());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        mmessagerecyclerview.setAdapter(messagesAdapter);


        mbackbuttonofspecificchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mnameofspecificuser.setText("Admin");


        msendmessagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enteredmessage = mgetmessage.getText().toString();
                if (enteredmessage.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter message first", Toast.LENGTH_SHORT).show();
                } else {
                    Date date = new Date();
                    currenttime = simpleDateFormat.format(calendar.getTime());
                    message_object messages = new message_object(enteredmessage, msenderuid, mrecieveruid, date.getTime(), currenttime);
                    firebaseDatabase = FirebaseDatabase.getInstance();
                    firebaseDatabase.getReference().child("chats")
                            .child(senderroom)
                            .child("messages")
                            .push().setValue(messages).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    firebaseDatabase.getReference()
                                            .child("chats")
                                            .child(recieverroom)
                                            .child("messages")
                                            .push()
                                            .setValue(messages).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        // Lưu đối tượng User vào "NGUOIDUNG" sau khi gửi tin nhắn
                                                        User user = new User(msenderuid, userName, userImage);
                                                        saveUserToFirebase(user);
                                                    }}
                                            });
                                }
                            });

                    mgetmessage.setText(null);
                    mmessagerecyclerview.post(new Runnable() {
                        @Override
                        public void run() {
                            // Call smooth scroll
                            mmessagerecyclerview.smoothScrollToPosition(messagesAdapter.getItemCount());
                        }
                    });
                }
            }
        });
    }

    private void saveUserToFirebase(User user) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("NGUOIDUNG");
        databaseReference.child(user.getUID()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.d("Firebase", "User added successfully");
                } else {
                    Log.e("Firebase", "Failed to add user", task.getException());
                }
            }
        });
    }

    /*protected void onStop() {
        super.onStop();
        DocumentReference documentReference = FirebaseFirestore.getInstance().document(FirebaseAuth.getInstance().getUid());
        documentReference.update("status", "Offline").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        });
        if (messagesAdapter != null) {
            messagesAdapter.notifyDataSetChanged();
        }
    }*/
}
/*@Override*/
   /* protected void onStart() {
        super.onStart();
        DocumentReference documentReference=FirebaseFirestore.getInstance().
                collection("NGUOIDUNG").document(FirebaseAuth.getInstance().getUid());
        documentReference.update("status","Online").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
            }
        });
        messagesAdapter.notifyDataSetChanged();
    }

}
*/