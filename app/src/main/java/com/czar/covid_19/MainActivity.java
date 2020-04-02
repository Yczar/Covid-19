package com.czar.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.czar.covid_19.Adapter.TrendingAdapter;
import com.czar.covid_19.Models.TrendingModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout lin_news, lin_hospital, lin_education;
    RecyclerView recyclerView;
    private List<TrendingModel> trendingModelList;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private TrendingAdapter trendingAdapter;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lin_education = findViewById(R.id.lin_education);
        lin_hospital = findViewById(R.id.lin_hospital);
        lin_news = findViewById(R.id.lin_news);
        layout = findViewById(R.id.corona_test_lay);
        recyclerView = findViewById(R.id.trending_rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        trendingModelList = new ArrayList<>();
        trendingAdapter = new TrendingAdapter(this, trendingModelList);
        recyclerView.setAdapter(trendingAdapter);
        readPost();

        lin_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NewsActivity.class));
            }
        });


        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void readPost() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Posts");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                trendingModelList.clear();
                for (DataSnapshot snapshot :dataSnapshot.getChildren()){
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

    public void trending(View view) {
        startActivity(new Intent(MainActivity.this, TrendingActivity.class));
    }
//        recyclerView = findViewById(R.id.trending_rv);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        trendingModelList = new ArrayList<>();
//        trendingAdapter = new TrendingAdapter(getApplicationContext(), trendingModelList);
//
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("Posts");
//
//

//
//        lin_hospital.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(getApplicationContext(), HospitalsActivity.class));
//
//            }
//        });
//
//        lin_education.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(getApplicationContext(), EducationActivity.class));
//
//            }
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                trendingModelList = new ArrayList<>();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    TrendingModel trendingModel = snapshot.getValue(TrendingModel.class);
//                    trendingModelList.add(trendingModel);
//                }
//                trendingAdapter = new TrendingAdapter(getApplicationContext(), trendingModelList);
//                recyclerView.setAdapter(trendingAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
}
