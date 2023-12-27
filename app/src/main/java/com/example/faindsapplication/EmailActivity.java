package com.example.faindsapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        if(queue==null){
            queue = Volley.newRequestQueue(this);
        }

        setContentView(binding.getRoot());

        binding.btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        "http://192.168.219.54:8089/chemail",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("LoginActivity", response);


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
                        String currentEmail = binding.currentEmail.getText().toString();
                        String newEmail = binding.newEmail.getText().toString();
                        Map<String,String> params = new HashMap<>();
                        params.put("currentEmail",currentEmail);
                        params.put("newEmail",newEmail);
                        //Spring서버에서도 "id","pw"로 받아야 함
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