package com.example.faindsapplication.ContractDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.R;
import java.util.ArrayList;

public class ContractDetailAdapter extends RecyclerView.Adapter<ContractDetailViewHolder> {
    private ArrayList<ContractDetailVO> dataset;
    public ContractDetailAdapter(ArrayList<ContractDetailVO> dataset) {
        this.dataset = dataset;
    }
    @NonNull
    @Override
    public ContractDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 레이아웃 파일을 inflate하여 ViewHolder를 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_register_detail, parent, false);
        ContractDetailViewHolder holder = new ContractDetailViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContractDetailViewHolder holder, int position) {
        // 데이터셋에서 현재 위치에 해당하는 데이터 가져오기
        String ContractKey = dataset.get(position).getContractKey();
        String ContractValue = dataset.get(position).getContractValue();
        int ContractId = dataset.get(position).getContractId();

        // 뷰홀더에 데이터 설정
        holder.getContractDetailKey().setText(ContractKey);
        holder.getContractDetailValue().setText(ContractValue);

        // 특정 조건에 따라 경고 메시지 표시
        if ("근무장소".equals(ContractKey) && !"본사 업팀".equals(ContractValue)) {
            holder.getTvWarning().setText("경고: 본사 업팀이 아닙니다.");
        } else {
            // 조건이 충족되지 않으면 경고 텍스트를 숨김
            holder.getTvWarning().setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
