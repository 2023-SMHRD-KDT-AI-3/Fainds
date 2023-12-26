package com.example.faindsapplication.RegisterDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.faindsapplication.ContractDetail.ContractDetailAdapter;
import com.example.faindsapplication.ContractDetail.ContractDetailVO;
import com.example.faindsapplication.R;
import com.example.faindsapplication.Register.RegisterFragment;
import com.example.faindsapplication.databinding.ActivityContractDetailBinding;
import com.example.faindsapplication.databinding.ActivityRegisterDetailBinding;

import java.util.ArrayList;

public class RegisterDetailActivity extends AppCompatActivity {
    private ActivityRegisterDetailBinding binding;
    private ArrayList<RegisterDetailVO> dataset;
    private RegisterDetailAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterDetailActivity.this, RegisterFragment.class);
                startActivity(intent);
            }
        });

        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterDetailActivity.this, RegisterFragment.class);
                startActivity(intent);
            }
        });

        dataset = new ArrayList<>();

        dataset.add(new RegisterDetailVO(1,"계약서 종류","표준근로계약서(미성년자)"));
        dataset.add(new RegisterDetailVO(1,"시급","10500원"));
        dataset.add(new RegisterDetailVO(1,"근무시간","18시 00분 부터 21시 00분 까지(휴게시간 : 없음"));

        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.RegisterDetailRV.setLayoutManager(manager);
        adapter = new RegisterDetailAdapter(dataset);
        binding.RegisterDetailRV.setAdapter(adapter);
    }
}