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
import com.example.faindsapplication.databinding.ActivityJoinBinding;

import java.util.HashMap;
import java.util.Map;

public class JoinActivity extends AppCompatActivity {

    private ActivityJoinBinding binding;

    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJoinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        if(queue==null){
            queue = Volley.newRequestQueue(this);
        }
        // 회원가입 스프링 통신
        binding.joinBtnJoin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        "http://192.168.219.54:8089/join",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

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
                        String id = binding.inputJoinID.getText().toString();
                        String pw = binding.inputJoinPw.getText().toString();
                        String email = binding.inputJoinEmail.getText().toString();
                        String name = binding.inputJoinName.getText().toString();
                        //전송방식을 POST로 지정했을 때 사용하는 메소드
                        //데이터를 전송할 때 Map형태로 구성하여 리턴해줘야 한다.
                        // Map<String,String> 앞은 Key 뒤는 Value 임
                        // Map은 인터페이스 Map을 상속받은 클래스가 HashMap
                        Map<String,String> params = new HashMap<>();
                        params.put("userId",id);
                        params.put("userPw",pw);
                        params.put("userEmail",email);
                        params.put("userName",name);
                        //Spring서버에서도 "id","pw"로 받아야 함

                        return params;
                    }
                };

                queue.add(request);
            }
        });
    }


}