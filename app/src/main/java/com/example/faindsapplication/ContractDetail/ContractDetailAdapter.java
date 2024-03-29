package com.example.faindsapplication.ContractDetail;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if (ContractValue.equals("미기입")) {
            holder.getTvWarning().setText("경고 : " + ContractKey + "이(가) 작성되어 있지 않습니다.");
        } else if (ContractKey.equals("근로 시간") && isOver52Hours(ContractValue)) {
            holder.getTvWarning().setText("경고 : " + ContractKey + "이 주 40시간 이상입니다.");
        } else if (ContractKey.equals("근로시간") && isOver52Hours(ContractValue)) {
            holder.getTvWarning().setText("경고 : " + ContractKey + "이 주 40시간 이상입니다.");
        } else if (ContractKey.equals("시급") && isSalaryValid(ContractValue)) {
            holder.getTvWarning().setText("경고 : " + ContractKey + "이 9860원 이하입니다.");
        } else {
            // 조건이 충족되지 않으면 경고 텍스트를 숨김
            holder.getTvWarning().setVisibility(View.GONE);
        }

        // 근무시간 판별
    }

    private boolean isOver52Hours(String timeString) {
        // 시간 형식에서 숫자만 추출하여 비교
        int hours = extractNumberFromTimeString(timeString);
        return hours > 40;
    }

    private int extractNumberFromTimeString(String timeString) {
        // 정규표현식을 사용하여 시간 형식에서 숫자만 추출
        Pattern pattern = Pattern.compile("\\d+|시간");
        Matcher matcher = pattern.matcher(timeString);
        while (matcher.find()) {
            try {
                // 추출된 문자열이 "시간"이라면 숫자 1을 반환
                if ("시간".equals(matcher.group())) {
                    return 1;
                }
                // 추출된 문자열을 숫자로 변환하여 반환
                return Integer.parseInt(matcher.group());
            } catch (NumberFormatException ignored) {
            }
        }
        return 0;
    }

    // 월급 판별
    private boolean isSalaryValid(String salaryString) {
        int salary = getSalaryAmount(salaryString);
        return salary <= 9860;
    }


    private int getSalaryAmount(String salaryString) {
        salaryString = salaryString.replace(",","");
        // 정규표현식을 사용하여 월급에서 숫자만 추출
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(salaryString);
        StringBuilder numbers = new StringBuilder();

        while (matcher.find()) {
            numbers.append(matcher.group());
        }
        // 모든 숫자를 이어붙인 문자열을 int로 변환하여 반환
        try {
            return Integer.parseInt(numbers.toString());
        } catch (NumberFormatException e) {
            return 0; // 숫자 변환에 실패한 경우
        }

    }


    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
