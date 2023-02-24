package com.example.gamestesting.game2048test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamestesting.R;
import com.example.gamestesting.game2048test.Score;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    private List<Score> scoreList;

    public RecyclerAdapter(List<Score> scoreList) {
        this.scoreList = scoreList;
    }


    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_list_view,parent,false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, int position) {
        Score score = scoreList.get(position);
        holder.tvId.setText(String.valueOf(score.getId()));
        holder.tvName.setText(score.getName());
        holder.tvScore.setText(String.valueOf(score.getScore()));
        holder.tvTime.setText(score.getTime());

    }

    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    public static class RecyclerHolder extends RecyclerView.ViewHolder  {
        private String id;
        private TextView tvId;
        private TextView tvName;
        private TextView tvScore;
        private TextView tvTime;



        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.id_text);
            tvName = itemView.findViewById(R.id.name_text);
            tvScore = itemView.findViewById(R.id.score_text);
            tvTime = itemView.findViewById(R.id.time_text);

        }

    }



}
