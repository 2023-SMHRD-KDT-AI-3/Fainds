package com.example.faindsapplication.Board;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.faindsapplication.BoardDetailActivity;
import com.example.faindsapplication.BoardWriteActivity;
import com.example.faindsapplication.R;
import com.example.faindsapplication.Register.RegisterViewHolder;
import com.example.faindsapplication.databinding.ActivityBoardDetailBinding;

import java.util.ArrayList;

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
        //String boardCmtNum = dataset.get(position).getBoardCmtNum();
        holder.getBoardTitle().setText(boardTitle);
        holder.getBoardContent().setText(boardContent);
      //  holder.getBoardCmtNum().setText(boardCmtNum);
        holder.listener = new BoardItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Intent intent = new Intent(v.getContext(), BoardDetailActivity.class);
                v.getContext().startActivity(intent);
            }
        };


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
