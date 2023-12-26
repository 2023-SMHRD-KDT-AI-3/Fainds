package com.example.faindsapplication.ContractDetail;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.Home.HomeItemClickListener;
import com.example.faindsapplication.R;

public class ContractDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ContractDetailItemClickListener listener;

    private TextView contractDetailKey;
    private TextView contractDetailValue;
    public ContractDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        this.contractDetailKey = itemView.findViewById(R.id.contractDetailKey);
        this.contractDetailValue = itemView.findViewById(R.id.contractDetailValue);

        itemView.setOnClickListener(this);
    }

    public ContractDetailItemClickListener getListener() {
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
