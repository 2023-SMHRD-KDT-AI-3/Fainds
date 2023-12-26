package com.example.faindsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.faindsapplication.databinding.ActivityEmailBinding;

public class EmailActivity extends AppCompatActivity {

    private ActivityEmailBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}