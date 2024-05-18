package com.example.jewelryecommerceapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Adapters.adapter_message;
import com.example.jewelryecommerceapp.R;
import com.example.jewelryecommerceapp.item.message_object;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StaffSpecificChatActivity extends AppCompatActivity{

    EditText mgetmessage;
    LinearLayout msendmessagebutton;
    CardView msendmessagecardview;
    androidx.appcompat.widget.Toolbar mtoolbarofspecificchat;
    ImageView mimageviewofspecificuser;
    TextView mnameofspecificuser;
    Intent intent;
    String mrecievername,sendername,mrecieveruid,msenderuid;
    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    String senderroom,recieverroom, currenttime, enteredmessage, type;
    Button mbackbuttonofspecificchat;
    RecyclerView mmessagerecyclerview;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    adapter_message messagesAdapter;
    ArrayList<message_object> messagesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specificchat);

        mgetmessage=findViewById(R.id.getmessage);
        msendmessagecardview=findViewById(R.id.carviewofsendmessage);
        msendmessagebutton=findViewById(R.id.imageviewsendmessage);
        mtoolbarofspecificchat=findViewById(R.id.toolbarofspecificchat);
        mnameofspecificuser=findViewById(R.id.Nameofspecificuser);
        mimageviewofspecificuser=findViewById(R.id.specificuserimageinimageview);
        mbackbuttonofspecificchat=findViewById(R.id.backbuttonofspecificchat);

        messagesArrayList=new ArrayList<>();
        mmessagerecyclerview=findViewById(R.id.recyclerviewofspecific);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        mmessagerecyclerview.setLayoutManager(linearLayoutManager);

        intent=getIntent();

        setSupportActionBar(mtoolbarofspecificchat);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("hh:mm a");


        msenderuid=firebaseAuth.getUid();
        type = intent.getStringExtra("type");
        mrecieveruid=getIntent().getStringExtra("receiveruid");
        mrecievername=getIntent().getStringExtra("name");

        try {
            if (type.isEmpty()) {
                Toast.makeText(getApplicationContext(), "null is recieved", Toast.LENGTH_SHORT).show();
            } else {
                if (type.equals("customer")) {
                    msenderuid = "Staff";
                }
            }
        }
        catch (Exception e) {}

        senderroom = msenderuid + mrecieveruid;
        recieverroom = mrecieveruid + msenderuid;

        DatabaseReference databaseReference=firebaseDatabase.getReference().
                child("chats").child(senderroom).child("messages");
        messagesAdapter=new adapter_message(StaffSpecificChatActivity.this,messagesArrayList);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messagesArrayList.clear();
                for(DataSnapshot snapshot1:snapshot.getChildren())
                {
                    message_object messages=snapshot1.getValue(message_object.class);
                    messagesArrayList.add(messages);
                }
                messagesAdapter.notifyDataSetChanged();
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

        mnameofspecificuser.setText(mrecievername);
        String uri=intent.getStringExtra("imageuri");
        try{
            if(uri.isEmpty())
            {
                Toast.makeText(getApplicationContext(),"null is recieved",Toast.LENGTH_SHORT).show();
            }
            else Picasso.get().load(uri).into(mimageviewofspecificuser);
        }
        catch (Exception e)
        {

        }


        msendmessagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enteredmessage=mgetmessage.getText().toString();
                if(enteredmessage.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter message first",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    Date date=new Date();
                    currenttime=simpleDateFormat.format(calendar.getTime());
                    message_object messages=new message_object(enteredmessage,msenderuid, mrecieveruid,date.getTime(),currenttime);
                    firebaseDatabase=FirebaseDatabase.getInstance();
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

                                                }
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
}
