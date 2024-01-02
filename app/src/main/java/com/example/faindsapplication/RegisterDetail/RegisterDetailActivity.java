package com.example.faindsapplication.RegisterDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.example.faindsapplication.FlaskConnect;
import com.example.faindsapplication.FlaskResponseListener;
import com.example.faindsapplication.MainActivity;
import com.example.faindsapplication.ProgressDialog;
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
import java.util.Iterator;
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

        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterDetailActivity.this, MainActivity.class);
                intent.putExtra("moveFl", "home");
                startActivity(intent);
            }
        });
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Intent intent = getIntent();
        Bitmap bitmap = null;
        if (intent.getParcelableExtra("TestImg") != null) {
            bitmap = (Bitmap) intent.getParcelableExtra("TestImg");
            binding.imgTest.setImageBitmap(bitmap);
        } else {
            Uri uri = intent.getParcelableExtra("TestImgUri");
            binding.imgTest.setImageURI(uri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        ProgressDialog progressDialog = new ProgressDialog(this); //다이얼로그 선언
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //백그라운를 투명하게
        progressDialog.setCancelable(false); //다이얼로그 외부 클릭으로 종료되지 않게
        progressDialog.show();

        //int imgTest = intent.getIntExtra("TestImg",R);
        //binding.imgTest.setImageResource(imgTest);

        //=============================================================================
        String url = "http://192.168.219.63:8089/getimg";
//        String url = "http://192.168.219.46:5000/upload";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream); // JPEG 형식, 품질 100으로 설정
        byte[] byteArray = byteArrayOutputStream.toByteArray();


        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        // 성공적으로 이미지가 전송되었을 때의 처리
                        String responseData = new String(response.data, StandardCharsets.UTF_8);
                        Log.d("ResponseSuccess", "onResponse: " + responseData);
                        FlaskConnect flask = new FlaskConnect();
                        flask.flaskconn(responseData, RegisterDetailActivity.this, new FlaskResponseListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progressDialog.dismiss();
                                updateUI(response);
                            }

                            @Override
                            public void onError(String error) {
                                Log.d("flaskerror", "onError: flask error");
                                progressDialog.dismiss();
                            }
                        });

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
    }

    public void updateUI(JSONObject response) {

        dataset = new ArrayList<>();
        try {
            JSONObject jsonObject = response;

            // JSON 객체를 반복하면서 key-value 쌍을 추출
            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                String value = jsonObject.getString(key);

                // 각 key-value 쌍을 dataset에 추가
                dataset.add(new RegisterDetailVO(1, key, value));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.RegisterDetailRV.setLayoutManager(manager);
        adapter = new RegisterDetailAdapter(dataset);
        binding.RegisterDetailRV.setAdapter(adapter);
    }
}