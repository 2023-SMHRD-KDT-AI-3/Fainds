package com.example.faindsapplication.RegisterDetail;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.R;

public class RegisterDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    RegisterDetailItemClickListener listener;
    private TextView contractDetailKey;
    private TextView contractDetailValue;
    public RegisterDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        // 레이아웃에서 각 요소를 찾아와 변수에 할당
        this.contractDetailKey = itemView.findViewById(R.id.contractDetailKey);
        this.contractDetailValue = itemView.findViewById(R.id.contractDetailValue);
        // 클릭 이벤트 리스너 등록
        itemView.setOnClickListener(this);
    }

    public RegisterDetailItemClickListener getListener() {
        return listener;
    }

    public TextView getContractDetailKey() {
        return contractDetailKey;
    }

    public TextView getContractDetailValue() {
        return contractDetailValue;
    }

    @Override
    public void onClick(View v) {
        this.listener.onItemClickListener(v,getLayoutPosition());
    }
}
