package com.example.faindsapplication.Board;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.example.faindsapplication.Cmt.CmtAdapter;
import com.example.faindsapplication.Cmt.CmtVO;
import com.example.faindsapplication.MainActivity;
import com.example.faindsapplication.databinding.ActivityBoardDetailBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardDetailActivity extends AppCompatActivity {

    private ActivityBoardDetailBinding binding;
    private ArrayList<CmtVO> dataset;
    private CmtAdapter adapter;
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
                    jsonBody.put("cmtWriterUser", getUserId());
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
                Intent intent = new Intent(BoardDetailActivity.this, MainActivity.class);
                intent.putExtra("moveFl","home");
                startActivity(intent);
            }
        });
        dataset = new ArrayList<>();
      //  dataset.add(new CmtVO("test",6));

        getComentData();



        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.cmtRV.setLayoutManager(manager);
        adapter = new CmtAdapter(dataset);
        binding.cmtRV.setAdapter(adapter);


    }


    public String getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserID" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("UserID", null);
    }

    public void getComentData(){
        getIntent().getIntExtra("boardSeq",0);
        String boardSeqId = String.valueOf(getIntent().getIntExtra("boardSeq",0));
        Log.d("boardSeqId12345",boardSeqId);
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.219.54:8089/cmtlist",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String utf8String = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                            JSONArray jsonArray = new JSONArray(utf8String);
                            // 파싱한 데이터를 데이터셋에 추가
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.d("댓글Json",jsonObject.toString());
                                // 각 필요한 데이터를 추출
                                String cmtWriter = jsonObject.getString("cmtWriterUser");
                                String cmtContent = jsonObject.getString("cmtContent");
                                String createdAt = jsonObject.getString("createdAt");
                                Log.d("cmtContent",cmtContent);
                                // 데이터셋에 추가
                                dataset.add(new CmtVO(cmtWriter,cmtContent,createdAt));
                            }
                            // 어댑터에 데이터셋 변경을 알림
                            adapter.notifyDataSetChanged();

                        } catch (UnsupportedEncodingException e) {
                            // 예외 처리
                            e.printStackTrace();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
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
                Map<String, String> params = new HashMap<>();
               params.put("boardSeqId",boardSeqId);
                return params;
            }
        };
        queue.add(request);
    }

}