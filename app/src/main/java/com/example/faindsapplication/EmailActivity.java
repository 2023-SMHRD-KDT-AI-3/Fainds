package com.example.faindsapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.databinding.ActivityEmailBinding;

import java.util.HashMap;
import java.util.Map;

public class EmailActivity extends AppCompatActivity {

    private ActivityEmailBinding binding;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String LoginEmail = intent.getStringExtra("LoginEmail");

        binding.currentEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String okEmail = binding.currentEmail.getText().toString();
                if(okEmail.equals(LoginEmail)){
                    binding.tvEmailCheck.setVisibility(View.VISIBLE);
                }else {
                    binding.tvEmailCheck.setVisibility(View.INVISIBLE);
                }
            }
        });

        if(queue==null){
            queue = Volley.newRequestQueue(this);
        }

        binding.btnEmail.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String currentEmail = binding.currentEmail.getText().toString();
                String newEmail = binding.newEmail.getText().toString();

                if(currentEmail.equals(LoginEmail)){
                    sendChangeEmailRequest(currentEmail,newEmail);
                    Toast.makeText(EmailActivity.this, "이메일 변경 성공", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EmailActivity.this, "현재 이메일이 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendChangeEmailRequest(String currentEmail, String newEmail) {
        String url = "http://192.168.219.60:8089/chemail";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 에러 응답을 처리하는 코드
                Toast.makeText(EmailActivity.this, "서버 오류로 인해 비밀번호 변경에 실패했습니다.", Toast.LENGTH_SHORT).show();
                }
             }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //전송방식을 POST로 지정했을 때 사용하는 메소드
                //데이터를 전송할 때 Map형태로 구성하여 리턴해줘야 한다.
                // Map<String,String> 앞은 Key 뒤는 Value 임
                // Map은 인터페이스 Map을 상속받은 클래스가 HashMap
                Map<String,String> params = new HashMap<>();
                params.put("currentEmail",currentEmail);
                params.put("newEmail",newEmail);
                //Spring서버에서도 "currentemail","newemail"로 받아야 함

                return params;
            }
        };
        queue.add(request);
    }
}