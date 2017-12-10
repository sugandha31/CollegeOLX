package com.example.sugandhabansal.collegeolx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BuyActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BuyAdapter adapter;
    private List<BuyDto> listbuy = new ArrayList<>();
    private FirebaseAuth mAuth;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        recyclerView = (RecyclerView) findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Details");
        populate();
    }

    private void populate(){
        reference = FirebaseDatabase.getInstance().getReference();

        reference.child("Details").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listbuy.removeAll(listbuy);

                for(DataSnapshot k: dataSnapshot.getChildren()){
                    BuyDto obj = new BuyDto();
                    //System.out.println("Description"+k.child("Details").getValue());
                    obj.setTitle(k.child("contact").getValue().toString().trim());
                    obj.setDescription(k.child("description").getValue().toString().trim());
                    obj.setImage(k.child("imgUrl").getValue().toString().trim());
                    listbuy.add(obj);
                }
                System.out.print(listbuy);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(BuyActivity.this,"Try Again!",Toast.LENGTH_LONG).show();
            }
        });
        adapter = new BuyAdapter(BuyActivity.this,listbuy);
        LinearLayoutManager manager = new LinearLayoutManager(BuyActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        populate();
    }

    //
//    @Override
//    protected void onStart() {
//        super.onStart();
//        //populate();
//
//        FirebaseRecyclerAdapter<BuyDto, BuyViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<BuyDto, BuyViewHolder>(
//                BuyDto.class,R.layout.row,BuyViewHolder.class,reference
//        ) {
//            @Override
//            protected void populateViewHolder(BuyViewHolder viewHolder, BuyDto model, int position) {
//
//                viewHolder.setContact(model.getTitle());
//                viewHolder.setTittle(model.getDescription());
//
//            }
//        };
//
//        recyclerView.setAdapter(firebaseRecyclerAdapter);
//    }
//
//    public static class BuyViewHolder extends RecyclerView.ViewHolder{
//
//        View view;
//
//        public BuyViewHolder(View itemView) {
//            super(itemView);
//
//            view = itemView;
//        }
//
//        public void setTittle(String desc){
//            TextView post_desc = (TextView) view.findViewById(R.id.text1);
//            post_desc.setText(desc);
//        }
//
//        public void setContact(String contact){
//            TextView post_contact = (TextView) view.findViewById(R.id.text2);
//            post_contact.setText(contact);
//        }
//    }


}
