package com.example.sugandhabansal.collegeolx;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    private EditText ed1,ed2,ed3;
    private Button submit;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference reference;
    private String TAG = "one";
    private ProgressBar progressBar;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ed1 = (EditText) findViewById(R.id.editText2);
        ed2 = (EditText) findViewById(R.id.editText4);
        ed3 = (EditText) findViewById(R.id.editText5);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        submit = (Button) findViewById(R.id.button4);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("NewUser");

//        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = ed1.getText().toString().trim();
                final String email = ed2.getText().toString().trim();
                final String password = ed3.getText().toString().trim();

                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    //Toast.makeText(Registration.this, "First IF", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.VISIBLE);
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Registration.this, "Successful", Toast.LENGTH_LONG).show();

                                String id = reference.push().getKey();
                                NewUser user = new NewUser(name,email);
                                reference.child(id).setValue(user);

                                Intent intent = new Intent(Registration.this,MainActivity.class);
                                startActivity(intent);
                            }
                             else {
                                Toast.makeText(Registration.this, "Unsuccessful", Toast.LENGTH_LONG).show();
                            }


                        }
                    });
                }
            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    public void log(View view) {
        Intent intent = new Intent(Registration.this,Login.class);
        startActivity(intent);
    }
}