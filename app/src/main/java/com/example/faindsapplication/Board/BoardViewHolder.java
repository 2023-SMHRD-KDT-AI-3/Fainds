package com.example.faindsapplication.Board;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.R;

public class BoardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    // 리사이클러뷰 아이템 클릭 이벤트 리스너
    BoardItemClickListener listener;

    // 아이템 뷰에 포함된 UI 요소들
    private TextView boardTitle;
    private TextView boardContent;
    private TextView boardTime;
    private TextView boardCmtNum;
    private ImageView btnAddPopup;

    // 생성자에서 각 요소들을 초기화하고 아이템 뷰에 클릭 리스너 등록
    public BoardViewHolder(@NonNull View itemView) {
        super(itemView);
        this.boardTitle = itemView.findViewById(R.id.boardTitle);
        this.boardContent = itemView.findViewById(R.id.boardContent);
        this.boardTime = itemView.findViewById(R.id.boardTime);
        this.boardCmtNum = itemView.findViewById(R.id.boardCmtNum);
        this.btnAddPopup = itemView.findViewById(R.id.btnAddPopup);

        itemView.setOnClickListener(this);
    }

    // 외부에서 리스너를 설정할 수 있도록 하는 메서드
    public BoardItemClickListener getListener() {
        return listener;
    }

    // 각 UI 요소에 대한 게터 메서드들
    public TextView getBoardTitle() {
        return boardTitle;
    }
    public TextView getBoardContent() {
        return boardContent;
    }
    public TextView getBoardTime() {
        return boardTime;
    }
    public TextView getBoardCmtNum() {
        return boardCmtNum;
    }
    public ImageView getBtnAddPopup(){ return btnAddPopup; }

    // 아이템 뷰 클릭 시 호출되는 메서드
    @Override
    public void onClick(View v) {
        this.listener.onItemClickListener(v,getLayoutPosition());
    }
}
