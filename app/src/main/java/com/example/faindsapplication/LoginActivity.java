package com.example.faindsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
        private static RequestQueue queue;
        private String URL="http://172.30.1.28:8080/login";
        private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 요청 객체를 보내주는 requestQueue 객체 초기화
        if(queue == null){
            queue = Volley.newRequestQueue(this);
        }

        //아이디, 비빈 입력 받고 일치하면 MainActivity 이동
        // 지금은 로그인 버튼 누르면 이동하게 해둠
        binding.loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        //회원가입 버튼 누르면 회원가입 JoinActivity 이동
        binding.loginTvJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

    }
}