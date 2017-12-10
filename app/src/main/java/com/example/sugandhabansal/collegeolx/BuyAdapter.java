package com.example.sugandhabansal.collegeolx;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sugandha Bansal on 7/30/2017.
 */

public class BuyAdapter extends RecyclerView.Adapter<BuyAdapter.BuyViewHolder> {

    private Context context;
    List<BuyDto> data;
    //ImageView postImage;

    public BuyAdapter(Context context, List<BuyDto> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public BuyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.add_adapter,parent,false);
        BuyViewHolder holder = new BuyViewHolder(view);
        return holder;
    }


    public void onBindViewHolder(BuyViewHolder holder, int position) {
        BuyDto current = data.get(position);
        holder.title.setText(current.getTitle().toString());
        holder.desc.setText(current.getDescription().toString());
        String image = current.getImage();
        Picasso.with(context).load(image).into(holder.postImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class BuyViewHolder extends RecyclerView.ViewHolder{

        TextView title, desc;
        ImageView postImage;
        StorageReference storageRef;


        public BuyViewHolder(View itemView) {
            super(itemView);
            storageRef = FirebaseStorage.getInstance().getReference();
            postImage = (ImageView) itemView.findViewById(R.id.postimage);
            title = (TextView) itemView.findViewById(R.id.posttitle);
            desc = (TextView) itemView.findViewById(R.id.postdesc);
        }
    }
}


