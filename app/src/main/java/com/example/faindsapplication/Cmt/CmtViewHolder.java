package com.example.faindsapplication.Cmt;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.R;

public class CmtViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    // 클릭 리스너 인터페이스
    CmtItemClickListener listener;

    // 아이템 뷰의 각 요소
    private TextView cmtWriter;
    private TextView cmtContent;
    private TextView cmtTime;

    // ViewHolder 생성자
    public CmtViewHolder(@NonNull View itemView) {
        super(itemView);
        // 아이템 뷰에서 각 요소를 찾아 연결
        this.cmtWriter = itemView.findViewById(R.id.tvCmtWriter);
        this.cmtContent = itemView.findViewById(R.id.tvCmtContent);
        this.cmtTime = itemView.findViewById(R.id.tvCmtTime);
        // 아이템 뷰에 클릭 리스너 설정
        itemView.setOnClickListener(this);
    }

    // Getter메소드
    public CmtItemClickListener getListener() {
        return listener;
    }

    public TextView getCmtWriter() {
        return cmtWriter;
    }

    public TextView getCmtContent() {
        return cmtContent;
    }

    public TextView getCmtTime() {
        return cmtTime;
    }

    // 클릭 이벤트 처리
    @Override
    public void onClick(View v) {
        this.listener.onItemClickListener(v,getLayoutPosition());
    }
}
