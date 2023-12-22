package com.example.faindsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

//import com.example.faindsapplication.databinding.ActivityMainBinding;
import com.example.faindsapplication.Board.BoardFragment;
import com.example.faindsapplication.Home.HomeFragment;
import com.example.faindsapplication.Register.RegisterFragment;
import com.example.faindsapplication.Setting.SettingFragment;
import com.example.faindsapplication.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}