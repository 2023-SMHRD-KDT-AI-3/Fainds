package com.example.faindsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.faindsapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

        private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //아이디, 비빈 입력 받고 일치하면 MainActivity 이동
        // 지금은 로그인 버튼 누르면 이동하게 해둠
        binding.loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}