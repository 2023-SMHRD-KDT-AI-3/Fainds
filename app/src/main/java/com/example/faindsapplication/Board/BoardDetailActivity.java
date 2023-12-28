package com.example.faindsapplication.Board;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.Board.BoardFragment;
import com.example.faindsapplication.databinding.ActivityBoardDetailBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BoardDetailActivity extends AppCompatActivity {

    private ActivityBoardDetailBinding binding;

    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityBoardDetailBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        String boardtitle = getIntent().getStringExtra("boardTitle");
        String boardcontent = getIntent().getStringExtra("boardContent");

        binding.boardDetailTitle.setText(boardtitle);
        binding.boardDetailContent.setText(boardcontent);

        setContentView(binding.getRoot());
        if(queue==null){
            queue = Volley.newRequestQueue(this);
        }



        binding.imgCmtWrite.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                String cmtContent = binding.tvCmtWrite.getText().toString();
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("cmtContent", cmtContent);
                    jsonBody.put("boardSeqId", getIntent().getIntExtra("boardSeq", 0));
                    jsonBody.put("cmtUser", getUserId());
                    Log.d("jsonbody", jsonBody.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("jsonbody", jsonBody.toString());
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        "http://192.168.219.54:8089/cmtwrite",
                        jsonBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("response",response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                queue.add(jsonObjectRequest);
            }
        });
        


        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardDetailActivity.this,BoardFragment.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public String getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserID" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("UserID", null);
    }

}