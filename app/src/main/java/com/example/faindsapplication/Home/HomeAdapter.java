package com.example.faindsapplication.Home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.Board.BoardItemClickListener;
import com.example.faindsapplication.Board.BoardVO;
import com.example.faindsapplication.Board.BoardViewHolder;
import com.example.faindsapplication.BoardDetailActivity;
import com.example.faindsapplication.ContractDetail.ContractDetailActivity;
import com.example.faindsapplication.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    private ArrayList<HomeVO> dataset;
    public HomeAdapter(ArrayList<HomeVO> dataset) {
        this.dataset = dataset;
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        HomeViewHolder holder = new HomeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        int img = R.drawable.icon_icon;
        String contractName = dataset.get(position).getContractName();
        String contractType = dataset.get(position).getContractType();
        int contractId = dataset.get(position).getContractId();
        if(contractType.equals("표준근로계약서(기간의 정함이 있음)")){
            img = R.drawable.icon_contract_regular;
        } else if (contractType.equals("표준근로계약서(기간의 정함이 없음)")) {
            img = R.drawable.icon_contract_irregular;
        } else if (contractType.equals("표준근로계약서(미성년자)")) {
            img = R.drawable.icon_contract_student;
        }
        holder.getTvRegisterName().setText(contractName);
        holder.getTvRegisterExample().setText(contractType);
        holder.getImgRegisterType().setImageResource(img);
        holder.listener = new BoardItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Intent intent = new Intent(v.getContext(), ContractDetailActivity.class);
                intent.putExtra("contractId",contractId);
                v.getContext().startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
