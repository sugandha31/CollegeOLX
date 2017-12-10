package com.example.sugandhabansal.collegeolx;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Comment;

public class Details extends AppCompatActivity {

    private FirebaseDatabase database;
    private EditText ed1,ed2;
    private String image, message;
    private DatabaseReference reference;
    private FirebaseUser firebaseUser;
    private FirebaseAuth mAuth;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ed1 = (EditText) findViewById(R.id.editText6);
        ed2 = (EditText) findViewById(R.id.editText7);

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        reference = database.getReference("Details");
        firebaseUser = mAuth.getCurrentUser();

//        Bundle bundle = getIntent().getExtras();
//        message = bundle.getString("message");
    }

    public void submit(View view) {
        Intent intent1 = getIntent();
        image = intent1.getExtras().getString("key").trim();
//        Toast.makeText(Details.this,image,Toast.LENGTH_LONG).show();

        Users user = new Users(ed1.getText().toString().trim(),ed2.getText().toString().trim(),image);
        String id = reference.push().getKey();
        reference.child(id).setValue(user);
        Toast.makeText(Details.this,"complete",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Details.this,MainPage.class);
        startActivity(intent);
    }

}
