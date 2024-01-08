package com.example.faindsapplication.ContractDetail;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.R;

public class ContractDetailViewHolder extends RecyclerView.ViewHolder {


    private TextView contractDetailKey;
    private TextView contractDetailValue;
    private TextView tvWarning;

    public ContractDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        // 레이아웃에서 각 뷰 요소를 찾아와 변수에 할당
        this.contractDetailKey = itemView.findViewById(R.id.contractDetailKey);
        this.contractDetailValue = itemView.findViewById(R.id.contractDetailValue);
        this.tvWarning = itemView.findViewById(R.id.tvWarning);

    }

    public TextView getContractDetailKey() {
        return contractDetailKey;
    }

    public TextView getContractDetailValue() {
        return contractDetailValue;
    }

    public TextView getTvWarning() {
        return tvWarning;
    }
}
