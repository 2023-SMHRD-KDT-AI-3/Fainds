package com.example.faindsapplication.ContractDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.R;
import com.example.faindsapplication.ContractDetail.ContractDetailVO;
import com.example.faindsapplication.ContractDetail.ContractDetailAdapter;

import java.util.ArrayList;

public class ContractDetailAdapter extends RecyclerView.Adapter<ContractDetailViewHolder> {
    private ArrayList<ContractDetailVO> dataset;
    public ContractDetailAdapter(ArrayList<ContractDetailVO> dataset) {
        this.dataset = dataset;
    }
    @NonNull
    @Override
    public ContractDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_register_detail, parent, false);
        ContractDetailViewHolder holder = new ContractDetailViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContractDetailViewHolder holder, int position) {
        String ContractKey = dataset.get(position).getContractKey();
        String ContractValue = dataset.get(position).getContractValue();
        int ContractId = dataset.get(position).getContractId();
        holder.getContractDetailKey().setText(ContractKey);
        holder.getContractDetailValue().setText(ContractValue);
        if ("근무장소".equals(ContractKey) && !"본사 영업팀".equals(ContractValue)) {
            holder.getTvWarning().setText("경고: 본사 영업팀이 아닙니다.");
        } else {
            // 조건이 충족되지 않으면 경고 텍스트를 초기화
            holder.getTvWarning().setText("");
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
