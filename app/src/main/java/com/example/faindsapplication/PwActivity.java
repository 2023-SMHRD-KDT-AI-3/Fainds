package com.example.faindsapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.databinding.ActivityPwBinding;

import java.util.HashMap;
import java.util.Map;

public class PwActivity extends AppCompatActivity {
    private ActivityPwBinding binding;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPwBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(queue==null){
            queue = Volley.newRequestQueue(this);
        }

        binding.btnPw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        "http://192.168.219.48:8089/chepw",
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
                        String currentpw = binding.currentPw.getText().toString();
                        String newpw = binding.newPw.getText().toString();
                        String confirmpw = binding.confirmPw.getText().toString();
                        //전송방식을 POST로 지정했을 때 사용하는 메소드
                        //데이터를 전송할 때 Map형태로 구성하여 리턴해줘야 한다.
                        // Map<String,String> 앞은 Key 뒤는 Value 임
                        // Map은 인터페이스 Map을 상속받은 클래스가 HashMap
                        Map<String,String> params = new HashMap<>();
                        params.put("currentPw",currentpw);
                        params.put("newPw",newpw);
                        params.put("confirmPw",confirmpw);
                        //Spring서버에서도 "currentpw","userpw"로 받아야 함

                        return params;
                    }
                };

                queue.add(request);
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}