package com.example.faindsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

        getSupportFragmentManager().beginTransaction().replace(
                // 1) 어디에
                R.id.fl,
                // 2)어떤 프래그먼트
                new HomeFragment()
        ).commit();

        binding.bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.d("Navigation", "Selected Item ID: " + item.getItemId());
                if (R.id.tab1 == item.getItemId()){
                    getSupportFragmentManager().beginTransaction().replace(
                            // 1) 어디에
                            R.id.fl,
                            // 2)어떤 프래그먼트
                            new HomeFragment()
                    ).commit();
                }else if(R.id.tab2 == item.getItemId()){
                    getSupportFragmentManager().beginTransaction().replace(
                            // 1) 어디에
                            R.id.fl,
                            // 2)어떤 프래그먼트
                            new RegisterFragment()
                    ).commit();
                }else if(R.id.tab3 == item.getItemId()){
                    getSupportFragmentManager().beginTransaction().replace(
                            // 1) 어디에
                            R.id.fl,
                            // 2)어떤 프래그먼트
                            new BoardFragment()
                    ).commit();
                }else if(R.id.tab4 == item.getItemId()) {
                    getSupportFragmentManager().beginTransaction().replace(
                            // 1) 어디에
                            R.id.fl,
                            // 2)어떤 프래그먼트
                            new SettingFragment()
                    ).commit();
                }
                return true;
            }
        });


    }

}