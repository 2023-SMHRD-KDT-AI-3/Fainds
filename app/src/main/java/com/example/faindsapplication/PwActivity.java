package com.example.faindsapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        binding = ActivityPwBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String loginPw = getUserPW();

        if (queue == null) {
            queue = Volley.newRequestQueue(this);
        }
        binding.currentPw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String okpw = binding.currentPw.getText().toString();
                if (!okpw.equals(loginPw)) {
                    binding.tvPwCheck.setVisibility(View.INVISIBLE);
                } else {
                    binding.tvPwCheck.setVisibility(View.VISIBLE);
                }
            }
        });
        binding.btnPw.setOnClickListener(new View.OnClickListener() {
            String currentpw = binding.currentPw.getText().toString();
            String newpw = binding.newPw.getText().toString();
            String confirmpw = binding.confirmPw.getText().toString();
            @Override
            public void onClick(View v) {

                if (currentpw.equals(loginPw)) {
                    // 새 비밀번호와 확인 비밀번호가 일치하는 경우
                    if (newpw.equals(confirmpw)) {

                        //Spring서버에서도 "currentpw","userpw"로 받아야 함
                    } else {
                        // 새 비밀번호와 확인 비밀번호가 일치하지 않는 경우
                        binding.tvNewPwCheck.setVisibility(View.INVISIBLE);
                    }
                } else {
                    // 현재 비밀번호가 일치하지 않는 경우
                    Toast.makeText(PwActivity.this, "현재 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public String getUserPW() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserPW" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("UserPW", null);
    }

    private void sendChangePasswordRequest(String currentPw, String newPw, String confirmPw) {
        String url = "http://192.168.219.48:8089/changepw";

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // 서버 응답을 처리하는 코드
                        // 변경 성공 여부에 따라 적절한 Toast 메시지를 띄울 수 있습니다.
                        if ("success".equals(response)) {
                            Toast.makeText(PwActivity.this, "비밀번호 변경에 성공했습니다.", Toast.LENGTH_SHORT).show();
                            // 변경 성공 시, 현재 액티비티 종료
                            finish();
                        } else {
                            Toast.makeText(PwActivity.this, "비밀번호 변경에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // 에러 응답을 처리하는 코드
                        Toast.makeText(PwActivity.this, "서버 오류로 인해 비밀번호 변경에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("currentpw", currentPw);
                params.put("userPw", newPw);
                params.put("confirmPw", confirmPw);
                return params;
            }
        };

        queue.add(request);
    }

}