package com.example.vibhu.imdb;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by VIBHU on 3/30/2018.
 */

public class SimilarAdapter extends RecyclerView.Adapter<SimilarAdapter.UserViewHolder> {

    ArrayList<SimilarMoviesHeirarchy.ResultsBean> users;
    Context context;

    public SimilarAdapter(ArrayList<SimilarMoviesHeirarchy.ResultsBean> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder
    {

        public UserViewHolder(View itemView) {
            super(itemView);
        }
    }
}
