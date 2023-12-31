package com.example.faindsapplication.Home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.Board.BoardItemClickListener;
import com.example.faindsapplication.ContractDetail.ContractDetailActivity;
import com.example.faindsapplication.MainActivity;
import com.example.faindsapplication.R;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    private ArrayList<HomeVO> dataset;
    public HomeAdapter(ArrayList<HomeVO> dataset) {
        this.dataset = dataset;
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // item_main 레이아웃을 inflate하여 ViewHolder를 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        HomeViewHolder holder = new HomeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        // 데이터셋에서 현재 위치에 해당하는 데이터 가져오기
        int img = R.drawable.icon_contract_architect;
        String contractName = dataset.get(position).getContractName();
        String contractType = dataset.get(position).getContractType();
        String contractId = dataset.get(position).getContractId();
        String res = dataset.get(position).getRes();
        String url = dataset.get(position).getUrl();

        // 계약 유형에 따라 이미지 설정
        if(contractType.equals("표준근로계약서(기간의 정함이 없음)")){
            img = R.drawable.icon_contract_regular;
        } else if (contractType.equals("표준근로계약서(기간의 정함이 있음)")) {
            img = R.drawable.icon_irregular1;
        } else if (contractType.equals("표준근로계약서(18세 미만인 자)")) {
            img = R.drawable.icon_contract_student;
        }

        // 뷰홀더에 데이터 설정
        holder.getTvRegisterName().setText(contractName);
        holder.getTvRegisterExample().setText(contractType);
        holder.getImgRegisterType().setImageResource(img);


        // 아이템 클릭 시의 이벤트 처리
        holder.listener = new BoardItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                // 계약 상세 화면으로 이동하는 Intent 생성
                Intent intent = new Intent(v.getContext(), ContractDetailActivity.class);
                intent.putExtra("contractId",contractId);
                intent.putExtra("res",res);
                intent.putExtra("url",url);
                Log.d("계약서ID", "onCreate: "+contractId);
                v.getContext().startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
