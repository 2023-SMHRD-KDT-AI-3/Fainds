package com.example.faindsapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.databinding.ActivityLoginBinding;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

        private ActivityLoginBinding binding;
        private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(queue==null){
            queue = Volley.newRequestQueue(this);
        }

        //아이디, 비빈 입력 받고 일치하면 MainActivity 이동
        binding.loginBtnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        "http://localhost:8089/login",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        String id = binding.loginIdHint.getText().toString();
                        String pw = binding.loginPwHint.getText().toString();
                        Map<String,String> params = new HashMap<>();
                        params.put("id",id);
                        params.put("pw",pw);
                        return params;
                    }
                };
                queue.add(request);
            }
        });
        Intent intent =new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
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