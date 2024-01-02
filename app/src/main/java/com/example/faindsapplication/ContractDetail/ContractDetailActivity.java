package com.example.faindsapplication.ContractDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.faindsapplication.Home.HomeFragment;
import com.example.faindsapplication.MainActivity;
import com.example.faindsapplication.R;
import com.example.faindsapplication.RegisterDetail.RegisterDetailVO;
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

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContractDetailActivity.this, MainActivity.class);
                intent.putExtra("moveFl","home");
                startActivity(intent);
            }
        });
        String jsonData = "{\"근로개시일\":\"2020-03-05\",\"근무장소\":\"본사 영업팀\",\"업무내용\":\"영업 및 마케팅 관리\",\"근로시간\":\"9:00 - 18:00 (휴게시간: 12:00 - 13:00)\",\"임금\":{\"월급\":\"2,000,000원\",\"상여금\":\"매 분기마다 500,000원\",\"기타급여\":{\"식대\":\"200,000원\",\"가족수당\":\"100,000원\"}},\"사업체명\":\"oo물산\",\"사업체주소\":\"서울시 중구 00대로 000\",\"사업체대표자\":\"남경읍\",\"근로자주소\":\"서울시 은평구 00로 000\",\"근로자연락처\":\"010-9876-5432\",\"근로자명\":\"장그래\"}";

        dataset = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);

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
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.contractDetailRV.setLayoutManager(manager);
        adapter = new ContractDetailAdapter(dataset);
        binding.contractDetailRV.setAdapter(adapter);
    }
}