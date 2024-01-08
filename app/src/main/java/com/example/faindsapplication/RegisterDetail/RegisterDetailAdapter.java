package com.example.faindsapplication.RegisterDetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterDetailAdapter extends RecyclerView.Adapter<RegisterDetailViewHolder> {
    private ArrayList<RegisterDetailVO> dataset;
    public RegisterDetailAdapter(ArrayList<RegisterDetailVO> dataset) {
        this.dataset = dataset;
    }
    @NonNull
    @Override
    public RegisterDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // XML 레이아웃을 인플레이트하여 ViewHolder 객체 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_register_detail, parent, false);
        RegisterDetailViewHolder holder = new RegisterDetailViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterDetailViewHolder holder, int position) {
        // 각 아이템에 대한 데이터 설정
        String ContractKey = dataset.get(position).getRegisterDetailKey();
        String ContractValue = dataset.get(position).getRegisterDetailValue();
        int ContractId = dataset.get(position).getRegisterDetailId();

        holder.getContractDetailKey().setText(ContractKey);
        holder.getContractDetailValue().setText(ContractValue);

        // 특정 조건에 따라 경고 메시지 표시
        if (ContractValue.equals("미기입")) {
            holder.getTvWarning().setText("경고 : "+ContractKey+"가 작성되어 있지 않습니다.");
        } else if (ContractKey.equals("근로시간") && isOver52Hours(ContractValue)) {
            holder.getTvWarning().setText("경고 : "+ContractKey+"가 32시간 이상입니다.");
        } else {
            // 조건이 충족되지 않으면 경고 텍스트를 숨김
            holder.getTvWarning().setVisibility(View.GONE);
        }
    }
    private boolean isOver52Hours(String timeString) {
        // 시간 형식에서 숫자만 추출하여 비교
        int hours = extractNumberFromTimeString(timeString);
        return hours > 32;
    }

    private int extractNumberFromTimeString(String timeString) {
        // 정규표현식을 사용하여 시간 형식에서 숫자만 추출
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(timeString);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
