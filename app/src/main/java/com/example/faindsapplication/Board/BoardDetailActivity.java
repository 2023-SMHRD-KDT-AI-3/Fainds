package com.example.faindsapplication.Board;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.faindsapplication.Board.BoardFragment;
import com.example.faindsapplication.Cmt.CmtAdapter;
import com.example.faindsapplication.Cmt.CmtVO;
import com.example.faindsapplication.ContractDetail.ContractDetailAdapter;
import com.example.faindsapplication.ContractDetail.ContractDetailVO;
import com.example.faindsapplication.databinding.ActivityBoardDetailBinding;

import java.util.ArrayList;

public class BoardDetailActivity extends AppCompatActivity {

    private ActivityBoardDetailBinding binding;
    private ArrayList<CmtVO> dataset;
    private CmtAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBoardDetailBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        String boardtitle = getIntent().getStringExtra("boardTitle");
        String boardcontent = getIntent().getStringExtra("boardContent");

        binding.boardDetailTitle.setText(boardtitle);
        binding.boardDetailContent.setText(boardcontent);

        dataset = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.cmtRV.setLayoutManager(manager);
        adapter = new CmtAdapter(dataset);
        binding.cmtRV.setAdapter(adapter);


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
                Intent intent = new Intent(BoardDetailActivity.this,BoardFragment.class);
                startActivity(intent);
                finish();
            }
        });
        // 제목 변경
        binding.boardDetailTitle.setText("test");
        // 내용 변경


    }
}