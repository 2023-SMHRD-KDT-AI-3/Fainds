package com.example.faindsapplication.RegisterDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.ContractDetail.ContractDetailVO;
import com.example.faindsapplication.ContractDetail.ContractDetailViewHolder;
import com.example.faindsapplication.R;

import java.util.ArrayList;

public class RegisterDetailAdapter extends RecyclerView.Adapter<RegisterDetailViewHolder> {
    private ArrayList<RegisterDetailVO> dataset;
    public RegisterDetailAdapter(ArrayList<RegisterDetailVO> dataset) {
        this.dataset = dataset;
    }
    @NonNull
    @Override
    public RegisterDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_register_detail, parent, false);
        RegisterDetailViewHolder holder = new RegisterDetailViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterDetailViewHolder holder, int position) {
        String ContractKey = dataset.get(position).getRegisterDetailKey();
        String ContractValue = dataset.get(position).getRegisterDetailValue();
        int ContractId = dataset.get(position).getRegisterDetailId();
        holder.getContractDetailKey().setText(ContractKey);
        holder.getContractDetailValue().setText(ContractValue);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
