package com.example.faindsapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.Home.HomeFragment;
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

        //로그인 기능
        binding.loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        "http://192.168.219.56:8089/login",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("LoginActivity", response);
                                // 응답이 "true"이면 로그인 성공, "false"이면 실패
                                if (response.equals("true")) {
                                    String id = binding.loginIdHint.getText().toString();
                                    // 로그인 성공
                                    saveUserId(id);
                                     Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);

                                } else {
                                    // 로그인 실패
                                    Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }

                ){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        String id = binding.loginIdHint.getText().toString();
                        String pw = binding.loginPwHint.getText().toString();
                        Map<String,String> params = new HashMap<>();
                        params.put("userId",id);
                        params.put("userPw",pw);
                        //Spring서버에서도 "id","pw"로 받아야 함
                        return params;

                    }
                };

                queue.add(request);

            }
        });
        // 회원가입 기능
        binding.loginTvJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,JoinActivity.class);
                startActivity(intent);
            }
        });
        }
    public void saveUserId(String userId) {
        // SharedPreferences 인스턴스 얻기
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // Editor를 사용하여 값을 저장
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserID", userId);
        editor.apply();
    }
}