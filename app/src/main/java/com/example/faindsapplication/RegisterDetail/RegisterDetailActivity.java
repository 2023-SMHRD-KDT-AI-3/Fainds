package com.example.faindsapplication.RegisterDetail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.provider.MediaStore;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;
import com.example.faindsapplication.ContractDetail.ContractDetailAdapter;
import com.example.faindsapplication.ContractDetail.ContractDetailVO;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.FlaskConnect;
import com.example.faindsapplication.FlaskResponseListener;
import com.example.faindsapplication.MainActivity;
import com.example.faindsapplication.ProgressDialog;
import com.example.faindsapplication.Register.RegisterFragment;
import com.example.faindsapplication.VolleyMultipartRequest;
import com.example.faindsapplication.databinding.ActivityRegisterDetailBinding;

import java.util.ArrayList;

import org.jetbrains.annotations.Nullable;
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
    private String imgurl;
    private RequestQueue queue;
    private String registername;
    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 로고 클릭시 홈으로 이동
        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterDetailActivity.this, MainActivity.class);
                intent.putExtra("moveFl","home");
                startActivity(intent);
            }
        });

        // 뒤로가기 버튼 클릭 시 이벤트
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 전달받은 이미지 데이터 처리
        Intent intent = getIntent();
        registername = intent.getStringExtra("RegisterName");
        Log.d("계약서 종류", "onCreate: "+registername);
        Bitmap bitmap = null;
        if (intent.getParcelableExtra("TestImg") != null){
            bitmap = (Bitmap) intent.getParcelableExtra("TestImg");
            binding.imgTest.setImageBitmap(bitmap);
        }else {
            Uri uri = intent.getParcelableExtra("TestImgUri");
            binding.imgTest.setImageURI(uri);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), uri);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // 서버에 이미지 업로드 및 응답 처리
        ProgressDialog progressDialog = new ProgressDialog(this); //다이얼로그 선언
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //백그라운를 투명하게
        progressDialog.setCancelable(false); //다이얼로그 외부 클릭으로 종료되지 않게
        progressDialog.show();

        //int imgTest = intent.getIntExtra("TestImg",R);
        //binding.imgTest.setImageResource(imgTest);

        //=============================================================================
        // 이미지를 서버에 업로드하기 위한 URL
        String url = "http://192.168.219.65:8089/getimg";
//        String url = "http://192.168.219.46:5000/upload";
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 이미지를 JPEG 형식으로 압축하여 바이트 배열로 변환
        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArrayOutputStream); // JPEG 형식, 품질 100으로 설정
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        // Volley를 사용하여 MultipartRequest를 생성하고 서버에 이미지 업로드 요청
        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        // 성공적으로 이미지가 전송되었을 때의 처리
                        String responseData = new String(response.data, StandardCharsets.UTF_8);
                        Log.d("ResponseSuccess", "onResponse: "+responseData);
                        imgurl = responseData;
                        FlaskConnect flask = new FlaskConnect();
                        flask.flaskconn(responseData, RegisterDetailActivity.this, new FlaskResponseListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progressDialog.dismiss();
                                updateUI(response);
                                //버튼 클릭시 이벤트 몽고DB로 데이터 전송
                                binding.btnContractRegister.setOnClickListener(v -> {
                                    mongoinsert(getUserId(), imgurl, response);
                                    Intent intent = new Intent(RegisterDetailActivity.this, MainActivity.class);
                                    intent.putExtra("moveFl","home");
                                    startActivity(intent);
                                });
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
                // 이미지 데이터를 바이트 데이터로 변환하여 서버에 전송
                Map<String, DataPart> params = new HashMap<>();
                params.put("image", new DataPart("filename.png", byteArray)); // 'byteArray'는 위에서 생성한 바이트 배열
                return params;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(multipartRequest);
    }

    // 서버 응답을 처리하고 UI 갱신 메서드
    public void updateUI(JSONObject response){
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

        String jsonData = "{\"근로개시일\":\"2020-03-05\",\"근무장소\":\"본사 영업팀\",\"업무내용\":\"영업 및 마케팅 관리\",\"근로시간\":\"9:00 - 18:00 (휴게시간: 12:00 - 13:00)\",\"임금\":{\"월급\":\"2,000,000원\",\"상여금\":\"매 분기마다 500,000원\",\"기타급여\":{\"식대\":\"200,000원\",\"가족수당\":\"100,000원\"}},\"사업체명\":\"oo물산\",\"사업체주소\":\"서울시 중구 00대로 000\",\"사업체대표자\":\"남경읍\",\"근로자주소\":\"서울시 은평구 00로 000\",\"근로자연락처\":\"010-9876-5432\",\"근로자명\":\"장그래\"}";

//        String workplace = jsonObject.getString("근무장소");



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

        // RecyclerView 설정
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.RegisterDetailRV.setLayoutManager(manager);
        adapter = new RegisterDetailAdapter(dataset);
        binding.RegisterDetailRV.setAdapter(adapter);


    }
    public String getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserID" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("UserID", null);
    }


    public void mongoinsert(String userid, String url, JSONObject resdata){
        if(queue==null){
            queue = Volley.newRequestQueue(this);
        }
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.219.65:8089/mongo/mongoinsert",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("mongochecking", "onResponse: "+response);
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
                //전송방식을 POST로 지정했을 때 사용하는 메소드
                Map<String,String> params = new HashMap<>();
                params.put("userid",userid);
                params.put("url",url);
                params.put("registername",registername);
                params.put("resdata",resdata.toString());
                return params;
            }
        };

        queue.add(request);
    }
}