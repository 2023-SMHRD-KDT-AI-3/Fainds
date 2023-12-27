package com.example.faindsapplication.Board;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.faindsapplication.R;
import com.example.faindsapplication.Register.RegisterViewHolder;
import com.example.faindsapplication.databinding.ActivityBoardDetailBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BoardAdapter extends RecyclerView.Adapter<BoardViewHolder> {

    private ArrayList<BoardVO> dataset;
    public BoardAdapter(ArrayList<BoardVO> dataset) {
        this.dataset = dataset;
    }
    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board, parent, false);
        BoardViewHolder holder = new BoardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        String boardTitle = dataset.get(position).getBoardTitle();
        String boardContent = dataset.get(position).getBoardContent();
        int boardCmtNum = dataset.get(position).getBoardCmtNum();
         String createdAt = dataset.get(position).getCreated_at().substring(0,10) +" " +dataset.get(position).getCreated_at().substring(11,16);
       // String createdAt = dataset.get(position).getCreated_at();

        long now =System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String getTime = sdf.format(date);
        Log.d("gettime",getTime);
        Log.d("gettime",createdAt);

        int boardSeq=dataset.get(position).getBoardSeq();
        holder.getBoardTitle().setText(boardTitle);
        holder.getBoardContent().setText(boardContent);
        holder.getBoardCmtNum().setText(String.valueOf(boardCmtNum));
        holder.getBoardTime().setText(createdAt);
        holder.listener = new BoardItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Intent intent = new Intent(v.getContext(), BoardDetailActivity.class);
                intent.putExtra("boardTitle",boardTitle);
                intent.putExtra("boardContent",boardContent);
                intent.putExtra("createdAt",createdAt);
                intent.putExtra("boardSeq",boardSeq);
                v.getContext().startActivity(intent);
            }

        };


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
