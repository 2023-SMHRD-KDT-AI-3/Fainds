package com.example.faindsapplication.RegisterDetail;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.R;
import com.example.faindsapplication.Register.RegisterItemClickListener;

public class RegisterDetailViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    RegisterDetailItemClickListener listener;
    private TextView contractDetailKey;
    private TextView contractDetailValue;
    public RegisterDetailViewHolder(@NonNull View itemView) {
        super(itemView);
        this.contractDetailKey = itemView.findViewById(R.id.contractDetailKey);
        this.contractDetailValue = itemView.findViewById(R.id.contractDetailValue);
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
