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
import com.czar.covid_19.Models.TrendingModel;
import com.czar.covid_19.R;
import com.czar.covid_19.ReadNewsActivity;
import com.czar.covid_19.ReadTrends;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.ViewHolder> {
    public Context mContext;
    public List<TrendingModel> trendingModelList;
    FirebaseUser firebaseUser;
    private final int limit = 5;

    public TrendingAdapter() {
    }

    public TrendingAdapter(Context mContext, List<TrendingModel> trendingModelList) {
        this.mContext = mContext;
        this.trendingModelList = trendingModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_news_rv_layout, parent, false);
        return new TrendingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final TrendingModel trendingModel = trendingModelList.get(position);
        Glide.with(mContext).load(trendingModel.getImage()).into(holder.imageView);
        holder.description.setText(trendingModel.getDescription());
        holder.title.setText(trendingModel.getTitle());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postid = trendingModel.getPostid();

                Intent intent = new Intent(mContext, ReadTrends.class);
                intent.putExtra("postid", postid);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
//        if (trendingModelList.size() > limit) {
//            return limit;
//        } else {
            return trendingModelList.size();
//        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView title, description;
        private LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            linearLayout = itemView.findViewById(R.id.linearlay);
            description = itemView.findViewById(R.id.description);
        }

    }
}
