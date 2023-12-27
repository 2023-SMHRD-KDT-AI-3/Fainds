package com.example.faindsapplication.RegisterDetail;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.R;
import com.example.faindsapplication.VolleyMultipartRequest;
import com.example.faindsapplication.databinding.ActivityRegisterDetailBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RegisterDetailActivity extends AppCompatActivity {
    private ActivityRegisterDetailBinding binding;
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Bitmap bitmap = null;
        if (intent.getParcelableExtra("TestImg") != null){
            bitmap = (Bitmap) intent.getParcelableExtra("TestImg");
            binding.imgTest.setImageBitmap(bitmap);
        }else {
            Uri uri = intent.getParcelableExtra("ImgUri");
            binding.imgTest.setImageURI(uri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



        //int imgTest = intent.getIntExtra("TestImg",R);
        //binding.imgTest.setImageResource(imgTest);

        //=============================================================================
        String url ="http://192.168.219.46:8089/getimg";
//        String url = "http://192.168.219.46:5000/upload";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArrayOutputStream); // JPEG 형식, 품질 100으로 설정
        byte[] byteArray = byteArrayOutputStream.toByteArray();



        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        // 성공적으로 이미지가 전송되었을 때의 처리
                        String responseData = new String(response.data, StandardCharsets.UTF_8);
                        Log.d("ResponseSuccess", "onResponse: "+responseData);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // 에러 발생 시의 처리
                    }
                }) {

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                params.put("image", new DataPart("filename.png", byteArray)); // 'byteArray'는 위에서 생성한 바이트 배열
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(multipartRequest);


        // 전송할 JSON 데이터
//        JSONObject postData = new JSONObject();
//        try {
//            postData.put("Data", bitmap);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        // JSON Request 생성 및 전송
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // 응답 처리
//                        Log.d("Response", response.toString());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // 에러 처리
//                Log.d("Error.Response", error.toString());
//            }
//        });
//
//        jsonObjectRequest.setRetryPolicy(new com.android.volley.DefaultRetryPolicy(
//
//                50000,
//
//                com.android.volley.DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//
//                com.android.volley.DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        // 요청을 요청 큐에 추가
//        queue.add(jsonObjectRequest);



        //=============================================================================



    }
}