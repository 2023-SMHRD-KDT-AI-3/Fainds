package com.example.faindsapplication;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.window.BackEvent;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.Board.BoardFragment;
import com.example.faindsapplication.Home.HomeFragment;
import com.example.faindsapplication.Setting.SettingFragment;
import com.example.faindsapplication.databinding.ActivityBoardWriteBinding;

import java.util.HashMap;
import java.util.Map;

public class BoardWriteActivity extends AppCompatActivity {

    private ActivityBoardWriteBinding binding;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardWriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (queue == null) {
            queue = Volley.newRequestQueue(this);
        }
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.edtTv.getText().toString().trim();
                String content = binding.edtContent.getText().toString().trim();
                // 댓글이 비어 있는지 확인
                if (title.isEmpty() || content.isEmpty()) {
                    // 댓글이 비어 있을 경우 Toast 메시지 표시
                    Toast.makeText(BoardWriteActivity.this, "댓글 제목과 내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    // 댓글이 비어 있지 않은 경우 서버에 저장 요청
                    StringRequest request = new StringRequest(
                            Request.Method.POST,
                            "http://192.168.219.54:8089/boardwrite",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    finish();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            String title = binding.edtTv.getText().toString();
                            String content = binding.edtContent.getText().toString();
                            String id = getUserId();
                            Map<String, String> params = new HashMap<>();
                            Log.d("qwer", id);
                            params.put("BoardUser", id);
                            params.put("boardTitle", title);
                            params.put("boardContent", content);

                            return params;
                        }
                    };
                    queue.add(request);
                }
            }
        });
    }

    public String getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserID" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("UserID", null);
    }
}