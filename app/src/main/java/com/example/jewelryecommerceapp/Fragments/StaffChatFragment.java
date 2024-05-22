package com.example.jewelryecommerceapp.Fragments;
import static android.content.Context.SEARCH_SERVICE;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jewelryecommerceapp.Models.User;
import com.example.jewelryecommerceapp.R;
import com.example.jewelryecommerceapp.Adapters.StaffChatFragmentAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class StaffChatFragment extends Fragment implements AdapterView.OnItemSelectedListener, Filterable {

    private FirebaseFirestore firebaseFirestore;
    LinearLayoutManager linearLayoutManager;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    Button btn_back;

    //ImageView mimageviewofuser;
    SearchView searchView;
    StaffChatFragmentAdapter adapterChatFragment;

    //FirestoreRecyclerAdapter<User,NoteViewHolder> chatAdapter;
    ProgressDialog progressDialog;
    ArrayList<User> userArrayList;
    RecyclerView mrecyclerview;

    Query query;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_chat_fragment,container,false);
        mrecyclerview=v.findViewById(R.id.recyclerview);
        mrecyclerview.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerview.setLayoutManager(linearLayoutManager);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        userArrayList = new ArrayList<>();
        adapterChatFragment = new StaffChatFragmentAdapter(getContext(),userArrayList);
        firebaseAuth=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        EventInitListener();

        mrecyclerview.setAdapter(adapterChatFragment);

        SearchManager searchManager = (SearchManager) requireActivity().getSystemService(SEARCH_SERVICE);
        searchView = v.findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapterChatFragment.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapterChatFragment.getFilter().filter(s);
                return false;
            }
        });
        return v;

    }

    private void EventInitListener() {
        progressDialog.setTitle("Loading data...");
        progressDialog.show();
        mDatabase.child("NGUOIDUNG").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                userArrayList.clear();
                for(DataSnapshot d : dataSnapshot.getChildren())
                {
                    User object = d.getValue(User.class);
                    assert object != null;
                    if(!object.getUID().equals(firebaseAuth.getUid())) userArrayList.add(object);
                    adapterChatFragment.setData(userArrayList);
                    adapterChatFragment.notifyDataSetChanged();
                    if(progressDialog.isShowing())
                    {
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), "" + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
