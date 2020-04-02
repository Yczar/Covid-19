package com.czar.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.czar.covid_19.Adapter.NewsAdapter;
import com.czar.covid_19.Models.NewsModel;
import com.czar.covid_19.Models.TrendingModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private List<NewsModel> newsModelList;
    private NewsAdapter newsAdapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView = findViewById(R.id.news_rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        newsModelList = new ArrayList<>();
        newsAdapter = new NewsAdapter(this, newsModelList);
        recyclerView.setAdapter(newsAdapter);
        readPost();
    }

    private void readPost() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("News");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                newsModelList.clear();
                for (DataSnapshot snapshot :dataSnapshot.getChildren()){
                    NewsModel newsModel = snapshot.getValue(NewsModel.class);
                    newsModelList.add(newsModel);
                }
                newsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
