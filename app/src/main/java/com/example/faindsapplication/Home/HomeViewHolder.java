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
    private ImageView deleteContract;

    public HomeViewHolder(@NonNull View itemView) {
        super(itemView);
        // ViewHolder의 각 위젯들을 레이아웃에서 식별하여 참조
        this.tvRegisterName = itemView.findViewById(R.id.tvRegisterName);
        this.tvRegisterExample = itemView.findViewById(R.id.tvRegisterExample);
        this.imgRegisterType = itemView.findViewById(R.id.imgRegisterType);
        this.deleteContract = itemView.findViewById(R.id.deleteContract);
        // 클릭 이벤트를 처리하기 위해 OnClickListener 등록
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

    public ImageView getDeleteContract(){ return deleteContract; }

    @Override
    public void onClick(View v) {
        this.listener.onItemClickListener(v,getLayoutPosition());
    }
}
