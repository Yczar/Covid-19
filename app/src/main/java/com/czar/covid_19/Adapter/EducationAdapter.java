package com.czar.covid_19.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.czar.covid_19.Models.EducationModel;
import com.czar.covid_19.Models.NewsModel;
import com.czar.covid_19.R;
import com.czar.covid_19.ReadEducation;
import com.czar.covid_19.ReadTrends;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class EducationAdapter extends RecyclerView.Adapter<EducationAdapter.ViewHolder> {
    public Context mContext;
    public List<EducationModel> educationModelList;
    FirebaseUser firebaseUser;

    public EducationAdapter(Context mContext, List<EducationModel> educationModelList) {
        this.mContext = mContext;
        this.educationModelList = educationModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_news_rv_layout, parent, false);
        return new EducationAdapter.ViewHolder(view);
    }

    public EducationAdapter() {
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final EducationModel educationModel = educationModelList.get(position);
        Glide.with(mContext).load(educationModel.getImage()).into(holder.imageView);
        holder.description.setText(educationModel.getDescription());
        holder.title.setText(educationModel.getTitle());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postid = educationModel.getPostid();

                Intent intent = new Intent(mContext, ReadEducation.class);
                intent.putExtra("postid", postid);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return educationModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        private LinearLayout linearLayout;
        TextView description, title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            linearLayout = itemView.findViewById(R.id.linearlay);
            description = itemView.findViewById(R.id.description);
            title = itemView.findViewById(R.id.description);
        }
    }
}
