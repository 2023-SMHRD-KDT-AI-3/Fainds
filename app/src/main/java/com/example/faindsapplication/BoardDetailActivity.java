package com.example.faindsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.faindsapplication.databinding.ActivityBoardDetailBinding;

public class BoardDetailActivity extends AppCompatActivity {

    private ActivityBoardDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBoardDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}