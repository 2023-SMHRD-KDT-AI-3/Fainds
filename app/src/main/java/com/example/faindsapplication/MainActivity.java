package com.example.faindsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

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

        // .setOnItemSelectedListener()
        binding.bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // item : 내가 클릭한 item의 정보(속성, id, icon, title... )
                if (R.id.tab1 == item.getItemId()){
                    getSupportFragmentManager().beginTransaction().replace(
                            // 1) 어디에
                            R.id.fl,
                            // 2)어떤 프래그먼트
                            new HomeFragment()
                    ).commit();
                }else if(R.id.tab2 == item.getItemId()){
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.fl,
                            new RegisterFragment()
                    ).commit();
                }else if(R.id.tab3 == item.getItemId()){
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.fl,
                            new BoardFragment()
                    ).commit();
                }else if(R.id.tab4 == item.getItemId()) {
                    getSupportFragmentManager().beginTransaction().replace(
                            R.id.fl,
                            new SettingFragment()
                    ).commit();
                }
                return true;
            }
        });
    }
}