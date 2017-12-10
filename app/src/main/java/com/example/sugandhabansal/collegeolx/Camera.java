package com.example.sugandhabansal.collegeolx;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Camera extends AppCompatActivity {

    ImageView imageView;
    Button b1;
    //final Uri url = null;
    private static final int CAMERA_REQUEST_CODE = 1;
    private StorageReference mStorage;
    ProgressBar progressBar;
    Uri downloaduri,uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = (ImageView) findViewById(R.id.camera);
        b1 = (Button) findViewById(R.id.button5);
        mStorage = FirebaseStorage.getInstance().getReference();
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST_CODE);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
            uri = data.getData();
            imageView.setImageURI(uri);
        }
    }

    public void next(View view) {
        Toasts();
    }

    private void Toasts() {
        StorageReference filepath = mStorage.child("Photos").child((uri.getLastPathSegment()));
        filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                @SuppressWarnings("VisibleForTests") Uri uri = taskSnapshot.getDownloadUrl();
                Toast.makeText(Camera.this,"Upload Complete..",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Camera.this,Details.class);
                intent.putExtra("key",uri.toString());
                startActivity(intent);
            }
        });
    }
}
