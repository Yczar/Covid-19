package com.czar.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.czar.covid_19.Models.NewsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReadTrends extends AppCompatActivity {
    private  String postid;
    private ImageView post_image;
    private TextView post_title, post_description;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_trends);

        postid = getIntent().getStringExtra("postid");
        post_image = findViewById(R.id.postimage);
        post_title = findViewById(R.id.post_title);
        post_description = findViewById(R.id.post_description);

        reference = FirebaseDatabase.getInstance().getReference("Posts").child(postid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                NewsModel newsModel = dataSnapshot.getValue(NewsModel.class);

                Glide.with(getApplicationContext()).load(newsModel.getImage()).into(post_image);
                post_title.setText(newsModel.getTitle());
                post_description.setText(newsModel.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
