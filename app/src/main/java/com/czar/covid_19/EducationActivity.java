package com.czar.covid_19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.czar.covid_19.Adapter.EducationAdapter;
import com.czar.covid_19.Adapter.NewsAdapter;
import com.czar.covid_19.Models.EducationModel;
import com.czar.covid_19.Models.NewsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EducationActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private List<EducationModel> educationModelList;
    private EducationAdapter educationAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);
        recyclerView = findViewById(R.id.education_rv);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        educationModelList = new ArrayList<>();
        educationAdapter = new EducationAdapter(this, educationModelList);
        recyclerView.setAdapter(educationAdapter);
        readPost();
    }
    private void readPost() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Education");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                educationModelList.clear();
                for (DataSnapshot snapshot :dataSnapshot.getChildren()){
                    EducationModel educationModel  = snapshot.getValue(EducationModel.class);
                    educationModelList.add(educationModel);
                }
                educationAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
