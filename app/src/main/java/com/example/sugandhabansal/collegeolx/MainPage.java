package com.example.sugandhabansal.collegeolx;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainPage extends AppCompatActivity {

    FirebaseAuth.AuthStateListener stateListener;
    Button b1, b2, b3;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        b1 = (Button) findViewById(R.id.button6);
        b2 = (Button) findViewById(R.id.button7);
        b3 = (Button) findViewById(R.id.button8);

        mAuth = FirebaseAuth.getInstance();

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });

        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //Toast.makeText(MainPage.this, "ni ho rha", Toast.LENGTH_LONG).show();
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    Intent intent = new Intent(MainPage.this, Login.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }


    public void buy(View view) {
        Intent intent = new Intent(MainPage.this, BuyActivity.class);
        startActivity(intent);
    }

    public void signup(View view) {
        Intent intent = new Intent(MainPage.this, Camera.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(stateListener);
    }
}

