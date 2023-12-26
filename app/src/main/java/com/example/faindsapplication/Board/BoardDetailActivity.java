package com.example.faindsapplication.Board;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.faindsapplication.databinding.ActivityBoardDetailBinding;

public class BoardDetailActivity extends AppCompatActivity {

    private ActivityBoardDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBoardDetailBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        String boardtitle = getIntent().getStringExtra("boardTitle");
        String boardcontent = getIntent().getStringExtra("boardContent");

        binding.bdDetailTitle.setText(boardtitle);
        // binding.이름.setText(boardcontent);

        setContentView(binding.getRoot());
    }
}