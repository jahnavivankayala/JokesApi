package com.example.cse.jokesapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.JokeViewHolder> {
    Context context;
    ArrayList<JokeModel> arrayList;

    public JokeAdapter(JokesDetail jokesDetail, ArrayList<JokeModel> arrayLis) {
        this.context=jokesDetail;
        this.arrayList=arrayLis;
    }

    @NonNull
    @Override
    public JokeAdapter.JokeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.rowdesign,viewGroup,false);
        return new JokeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JokeAdapter.JokeViewHolder jokeSubClass, int i) {
        JokeModel jokeModel=arrayList.get(i);
        jokeSubClass.textView.setText(jokeModel.getJoke());
        Log.i("tag",jokeModel.getJoke());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class JokeViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public JokeViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.txt1);
        }
    }
}
