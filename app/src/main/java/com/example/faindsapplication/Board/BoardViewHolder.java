package com.example.faindsapplication.Board;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BoardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    BoardItemClickListener listener;

    private TextView boardTitle;
    private TextView boardContent;
    private TextView boardTime;
    private ImageView imgCmt;
    private TextView boardCmtNum;
    public BoardViewHolder(@NonNull View itemView) {
        super(itemView);
        //this.boardTitle = itemView.findViewById(R.id.boardTitle);
        //this.boardContent = itemView.findViewById(R.id.boardContent);
        //this.boardTime = itemView.findViewById(R.id.boardTime);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
