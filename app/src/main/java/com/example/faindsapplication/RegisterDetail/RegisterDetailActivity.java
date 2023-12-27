package com.example.faindsapplication.RegisterDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.provider.MediaStore;
import android.util.Log;

import com.example.faindsapplication.ContractDetail.ContractDetailAdapter;
import com.example.faindsapplication.ContractDetail.ContractDetailVO;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.R;
import com.example.faindsapplication.Register.RegisterFragment;
import com.example.faindsapplication.databinding.ActivityContractDetailBinding;
import com.example.faindsapplication.VolleyMultipartRequest;
import com.example.faindsapplication.databinding.ActivityRegisterDetailBinding;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RegisterDetailActivity extends AppCompatActivity {
    private ActivityRegisterDetailBinding binding;
    private ArrayList<RegisterDetailVO> dataset;
    private RegisterDetailAdapter adapter;

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





        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterDetailActivity.this, RegisterFragment.class);
                startActivity(intent);
                finish();
            }
        });

        dataset = new ArrayList<>();

        dataset.add(new RegisterDetailVO(1, "계약서 종류", "표준근로계약서(미성년자)"));
        dataset.add(new RegisterDetailVO(1, "시급", "10500원"));
        dataset.add(new RegisterDetailVO(1, "근무시간", "18시 00분 부터 21시 00분 까지(휴게시간 : 없음"));

        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.RegisterDetailRV.setLayoutManager(manager);
        adapter = new RegisterDetailAdapter(dataset);
        binding.RegisterDetailRV.setAdapter(adapter);
    }
}