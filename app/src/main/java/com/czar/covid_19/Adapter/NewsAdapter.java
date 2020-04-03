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
import com.czar.covid_19.Models.NewsModel;
import com.czar.covid_19.Models.TrendingModel;
import com.czar.covid_19.R;
import com.czar.covid_19.ReadNewsActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    public Context mContext;
    public List<NewsModel> newsModelList;
    FirebaseUser firebaseUser;

    public NewsAdapter(Context mContext, List<NewsModel> newsModelList) {
        this.mContext = mContext;
        this.newsModelList = newsModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_news_rv_layout,parent,false);
        return new ViewHolder(view);
    }

    public NewsAdapter() {
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final NewsModel newsModel = newsModelList.get(position);
        Glide.with(mContext).load(newsModel.getImage()).into(holder.imageView);
        holder.description.setText(newsModel.getDescription());
        holder.title.setText(newsModel.getTitle());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postid = newsModel.getPostid();

                Intent intent = new Intent(mContext, ReadNewsActivity.class);
                intent.putExtra("postid", postid);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        private LinearLayout linearLayout;
        public TextView title, description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            linearLayout = itemView.findViewById(R.id.linearlay);
            description = itemView.findViewById(R.id.description);

        }
    }
}
