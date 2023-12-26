package com.example.faindsapplication.Home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.Board.BoardItemClickListener;
import com.example.faindsapplication.R;

public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    BoardItemClickListener listener;

    private TextView tvRegisterName;
    private TextView tvRegisterExample;
    private ImageView imgRegisterType;

    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvRegisterName = itemView.findViewById(R.id.tvRegisterName);
        this.tvRegisterExample = itemView.findViewById(R.id.tvRegisterExample);
        this.imgRegisterType = itemView.findViewById(R.id.imgRegisterType);

        itemView.setOnClickListener(this);
    }

    public BoardItemClickListener getListener() {
        return listener;
    }

    public TextView getTvRegisterName() {
        return tvRegisterName;
    }

    public TextView getTvRegisterExample() {
        return tvRegisterExample;
    }

    public ImageView getImgRegisterType() {
        return imgRegisterType;
    }

    @Override
    public void onClick(View v) {
        this.listener.onItemClickListener(v,getLayoutPosition());
    }
}
