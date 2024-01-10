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
            holder.getTvWarning().setText("경고 : " + ContractKey + "가 작성되어 있지 않습니다.");
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
        // 정규표현식을 사용하여 월급에서 숫자만 추출
        Pattern pattern = Pattern.compile("\\d+원");
        Matcher matcher = pattern.matcher(salaryString);
        while (matcher.find()) {
            try {
                // 추출된 문자열이 "원"이라면 숫자 1을 반환
                if ("원".equals(matcher.group())) {
                    return 1;
                }
                // 추출된 문자열을 숫자로 변환하여 반환
                return Integer.parseInt(matcher.group());
            } catch (NumberFormatException ignored) {
            }
        }
        return 0;
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
