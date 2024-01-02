package com.example.faindsapplication.ContractDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.faindsapplication.Home.HomeFragment;
import com.example.faindsapplication.MainActivity;
import com.example.faindsapplication.R;
import com.example.faindsapplication.databinding.ActivityContractDetailBinding;

import java.util.ArrayList;

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

        dataset = new ArrayList<>();

        dataset.add(new ContractDetailVO(1,"계약서 종류","표준근로계약서(미성년자)"));
        dataset.add(new ContractDetailVO(1,"시급","10500원"));
        dataset.add(new ContractDetailVO(1,"근무시간","18시 00분 부터 21시 00분 까지(휴게시간 : 없음"));

        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.contractDetailRV.setLayoutManager(manager);
        adapter = new ContractDetailAdapter(dataset);
        binding.contractDetailRV.setAdapter(adapter);
    }
}