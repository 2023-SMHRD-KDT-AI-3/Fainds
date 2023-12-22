package com.example.faindsapplication.RegisterDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.faindsapplication.R;
import com.example.faindsapplication.databinding.ActivityRegisterDetailBinding;

public class RegisterDetailActivity extends AppCompatActivity {
    private ActivityRegisterDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        //int imgTest = intent.getIntExtra("TestImg",R);
        //binding.imgTest.setImageResource(imgTest);
    }
}