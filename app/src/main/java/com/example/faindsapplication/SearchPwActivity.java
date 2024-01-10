package com.example.faindsapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.databinding.ActivityLoginBinding;
import com.example.faindsapplication.databinding.ActivitySearchPwBinding;

import java.util.HashMap;
import java.util.Map;

public class SearchPwActivity extends AppCompatActivity {

    private ActivitySearchPwBinding binding;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchPwBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 뒤로가기 이벤트 처리
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 로고버튼 클릭 이벤트 처리
        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPwActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        // Volley 요청 큐 초기화
        if (queue == null) {
            queue = Volley.newRequestQueue(this);
        }

        // 비밀번호 찾기 버튼 클릭 시 이벤트 처리
        binding.btnSearchPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SearchPwId = binding.tvSearchPwId.getText().toString();
                String SearchPwEmail = binding.tvSearchPwEmail.getText().toString();

                if (SearchPwId.isEmpty() || SearchPwEmail.isEmpty()) {
                    Toast.makeText(SearchPwActivity.this, "입력하지 않은 값이 있습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    StringRequest request = new StringRequest(
                            Request.Method.POST,
                            "http://192.168.219.47:8089/searchPw",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if(response.isEmpty()){
                                        Toast.makeText(SearchPwActivity.this, "잘못된 정보 입니다.", Toast.LENGTH_SHORT).show();
                                    }else {

                                    binding.tvPwResult2.setText(response);
                                    binding.tvPwResult1.setVisibility(View.VISIBLE);
                                    binding.tvPwResult2.setVisibility(View.VISIBLE);
                                    binding.tvPwResult3.setVisibility(View.VISIBLE);
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }

                    ) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            // 전송할 데이터를 Map 형태로 구성
                            //전송방식을 POST로 지정했을 때 사용하는 메소드
                            //데이터를 전송할 때 Map형태로 구성하여 리턴해줘야 한다.
                            // Map<String,String> 앞은 Key 뒤는 Value 임
                            // Map은 인터페이스 Map을 상속받은 클래스가 HashMap
                            String SearchPwId = binding.tvSearchPwId.getText().toString();
                            String SearchPwEmail = binding.tvSearchPwEmail.getText().toString();
                            Map<String, String> params = new HashMap<>();
                            params.put("SearchPwId",SearchPwId);
                            params.put("SearchPwEmail",SearchPwEmail);
                            //Spring서버에서도 "id","pw"로 받아야 함

                            return params;
                        }
                    };

                    queue.add(request);
                }
            }
        });
    }
}