package com.example.faindsapplication.Board;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.R;

public class BoardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    BoardItemClickListener listener;

    private TextView boardTitle;
    private TextView boardContent;
    private TextView boardTime;
    private ImageView imgCmt;
    private TextView boardCmtNum;
    public BoardViewHolder(@NonNull View itemView) {
        super(itemView);
        this.boardTitle = itemView.findViewById(R.id.boardTitle);
        this.boardContent = itemView.findViewById(R.id.boardContent);
        this.boardTime = itemView.findViewById(R.id.boardTime);
        this.imgCmt = itemView.findViewById(R.id.imgCmt);
        this.boardCmtNum = itemView.findViewById(R.id.boardCmtNum);

        itemView.setOnClickListener(this);
    }

    public BoardItemClickListener getListener() {
        return listener;
    }

    public TextView getBoardTitle() {
        return boardTitle;
    }

    public TextView getBoardContent() {
        return boardContent;
    }

    public TextView getBoardTime() {
        return boardTime;
    }

    public ImageView getImgCmt() {
        return imgCmt;
    }

    public TextView getBoardCmtNum() {
        return boardCmtNum;
    }

    @Override
    public void onClick(View v) {
        this.listener.onItemClickListener(v,getLayoutPosition());
    }
}
