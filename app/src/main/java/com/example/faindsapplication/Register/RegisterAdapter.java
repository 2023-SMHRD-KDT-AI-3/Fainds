package com.example.faindsapplication.Register;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.PopupActivity;
import com.example.faindsapplication.R;

import java.util.ArrayList;

public class RegisterAdapter extends RecyclerView.Adapter<RegisterViewHolder> {
    private ArrayList<RegisterVO> dataset;

    public RegisterAdapter(ArrayList<RegisterVO> dataset) {
        this.dataset = dataset;
    }

    // 뷰 홀더 생성 메서드
    @NonNull
    @Override
    public RegisterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 레이아웃 파일을 inflate하여 ViewHolder를 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_register, parent, false);
        RegisterViewHolder holder = new RegisterViewHolder(view);
        return holder;
    }

    // 뷰 홀더에 데이터를 바인딩하는 메서드
    @Override
    public void onBindViewHolder(@NonNull RegisterViewHolder holder, int position) {
        // 데이터셋에서 현재 위치에 해당하는 데이터 가져오기
        String RegisterName = dataset.get(position).getContractName();
        String RegisterExample = dataset.get(position).getContractExample();
        int RegisterTypeImg = dataset.get(position).getContractImg();

        // 뷰홀더에 데이터 설정
        holder.getTvRegisterName().setText(RegisterName);
        holder.getTvRegisterExample().setText(RegisterExample);
        holder.getImgRegisterType().setImageResource(RegisterTypeImg);

        // 아이템 클릭 이벤트 처리
        holder.listener = new RegisterItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Intent intent = new Intent(v.getContext(), PopupActivity.class);
                intent.putExtra("RegisterName", RegisterName);
                v.getContext().startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
