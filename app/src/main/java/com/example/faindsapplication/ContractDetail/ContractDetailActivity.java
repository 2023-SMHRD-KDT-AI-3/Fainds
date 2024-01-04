package com.example.faindsapplication.ContractDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.faindsapplication.MainActivity;
import com.example.faindsapplication.databinding.ActivityContractDetailBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class ContractDetailActivity extends AppCompatActivity {

    private ActivityContractDetailBinding binding;
    private ArrayList<ContractDetailVO> dataset;
    private ContractDetailAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContractDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 뒤로가기 버튼
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 홈버튼 클릭
        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContractDetailActivity.this, MainActivity.class);
                intent.putExtra("moveFl","home");
                startActivity(intent);
            }
        });

        // JSON 데이터 생성(가데이터)
        String jsonData = "{\"근로개시일\":\"2020-03-05\",\"근무장소\":\"본사 영업팀\",\"업무내용\":\"영업 및 마케팅 관리\",\"근로시간\":\"9:00 - 18:00 (휴게시간: 12:00 - 13:00)\",\"임금\":{\"월급\":\"2,000,000원\",\"상여금\":\"매 분기마다 500,000원\",\"기타급여\":{\"식대\":\"200,000원\",\"가족수당\":\"100,000원\"}},\"사업체명\":\"oo물산\",\"사업체주소\":\"서울시 중구 00대로 000\",\"사업체대표자\":\"남경읍\",\"근로자주소\":\"서울시 은평구 00로 000\",\"근로자연락처\":\"010-9876-5432\",\"근로자명\":\"장그래\"}";

        // 데이터셋 초기화
        dataset = new ArrayList<>();
        try {
            // JSON 객체 생성
            JSONObject jsonObject = new JSONObject(jsonData);

            // JSON 객체의 키를 반복하면서 데이터셋에 추가
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = jsonObject.getString(key);

                // 각 key-value 쌍을 dataset에 추가
                dataset.add(new ContractDetailVO(1, key, value));
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }

        // 리사이클러뷰 설정
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.contractDetailRV.setLayoutManager(manager);
        adapter = new ContractDetailAdapter(dataset);
        binding.contractDetailRV.setAdapter(adapter);
    }
}