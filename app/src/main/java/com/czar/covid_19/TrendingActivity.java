package com.czar.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.czar.covid_19.Adapter.EducationAdapter;
import com.czar.covid_19.Adapter.TrendingAdapter;
import com.czar.covid_19.Models.EducationModel;
import com.czar.covid_19.Models.TrendingModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TrendingActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private List<TrendingModel> trendingModelList;
    private TrendingAdapter trendingAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);
        recyclerView = findViewById(R.id.education_rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        trendingModelList = new ArrayList<>();
        trendingAdapter = new TrendingAdapter(this, trendingModelList);
        recyclerView.setAdapter(trendingAdapter);
        readPost();
    }
    private void readPost() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                trendingModelList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    TrendingModel trendingModel = snapshot.getValue(TrendingModel.class);
                    trendingModelList.add(trendingModel);
                }
                trendingAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
