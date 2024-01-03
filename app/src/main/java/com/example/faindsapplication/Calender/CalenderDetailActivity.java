package com.example.faindsapplication.Calender;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TimePicker;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.MainActivity;
import com.example.faindsapplication.R;
import com.example.faindsapplication.WorkPopupActivity;
import com.example.faindsapplication.databinding.ActivityCalenderDetailBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CalenderDetailActivity extends AppCompatActivity {
    private TimePicker startTimePicker, endTimePicker;
    private ActivityCalenderDetailBinding binding;
    private static final int REQUEST_CODE_WORK_POPUP_START = 1;
    private static final int REQUEST_CODE_WORK_POPUP_END = 2;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalenderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Volley 요청을 위한 큐 초기화
        if (queue == null) {
            queue = Volley.newRequestQueue(this);
        }

        // 시작시간을 workPopupActivity에서 가져옴
        binding.tvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderDetailActivity.this, WorkPopupActivity.class);
                intent.putExtra("ID", "workStartTime");
                startActivityForResult(intent, REQUEST_CODE_WORK_POPUP_START);
            }
        });
        // 종료시간을 workPopupActivity에서 가져옴
        binding.tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderDetailActivity.this, WorkPopupActivity.class);
                intent.putExtra("ID", "workEndTime");
                startActivityForResult(intent, REQUEST_CODE_WORK_POPUP_END);
            }
        });

        // 뒤로가기 버튼
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // db에 저장된 해당 날짜의 근무기록 보여주기
        String formattedDate = getIntent().getStringExtra("currentDate");
        String tvSalary = getIntent().getStringExtra("workPay");
        String startedAt = getIntent().getStringExtra("startedAt");
        String endedAt = getIntent().getStringExtra("endedAt");
        String workTime = getIntent().getStringExtra("workTimeString");
        String totalSalary = getIntent().getStringExtra("totalSalaryString");
        binding.tvDay.setText(formattedDate);
        binding.tvSalary.setText(tvSalary);
        binding.tvStartTime.setText(startedAt);
        binding.tvEndTime.setText(endedAt);
        binding.tvResult.setText(workTime);
        binding.tvDailySalary.setText(totalSalary);

        // 로고 클릭으로 홈 Fragment로 이동
        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderDetailActivity.this, MainActivity.class);
                intent.putExtra("moveFl","home");
                startActivity(intent);
            }
        });

        // 근무기록 삭제
        binding.btnCalenderDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 근무기록 수정
        binding.btnCalenderFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // 근무기록 등록
        binding.btnRegisterSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력된 정보 가져오기
                String currentDate = binding.tvDay.getText().toString();
                String startTime = binding.tvStartTime.getText().toString();
                String endTime = binding.tvEndTime.getText().toString();
                String salary = binding.tvSalary.getText().toString();
                String dailySalary = binding.tvDailySalary.getText().toString();

                // 현재 로그인한 사용자 id 가져오는 메소드
                String userId = getUserId();
                // 근무기록 등록 메소드
                RegisterSalary(currentDate, startTime, endTime, salary, userId, dailySalary);

                // 등록 시 딜레이
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 등록 후 CalenderActivity로 이동
                        Intent intent = new Intent(CalenderDetailActivity.this, CalenderActivity.class);
                        startActivity(intent);
                    }
                }, 500); // 1000 밀리초 (1초) 딜레이
            }
        });
    }

    // 근무기록 등록 메소드
    private void RegisterSalary(String formattedDate, String startTime, String endTime, String salary, String userId, String dailySalary) {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.219.54:8089/regiSalary",
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
                Map<String, String> params = new HashMap<>();
                params.put("workDay", formattedDate);
                params.put("startedAt", startTime);
                params.put("endedAt", endTime);
                params.put("workPay", salary);
                params.put("dailyWorkPay", dailySalary);
                params.put("workUser", userId);
                //Spring서버에서도 "id","pw"로 받아야 함
                return params;
            }
        };
        queue.add(request);
    }

    // 시작시간 종료시간 받아오는 메소드
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_WORK_POPUP_START) {
                // 시작 시간을 설정하는 팝업에서 받아온 경우
                String startTime = data.getStringExtra("startTime");
                binding.tvStartTime.setText(startTime);
            } else if (requestCode == REQUEST_CODE_WORK_POPUP_END) {
                // 종료 시간을 설정하는 팝업에서 받아온 경우
                String endTime = data.getStringExtra("endTime");
                binding.tvEndTime.setText(endTime);
            }
        }
        // 시작 시간과 종료 시간을 가져와서 처리
        Calendar startTime = parseTimeString(binding.tvStartTime.getText().toString());
        Calendar endTime = parseTimeString(binding.tvEndTime.getText().toString());

        // 시작 시간과 종료 시간이 모두 유효한 경우 계산 및 표시
        if (startTime != null && endTime != null) {
            // 두 시간의 차이를 밀리초 단위로 계산
            long timeDifferenceInMillis = endTime.getTimeInMillis() - startTime.getTimeInMillis();
            // 계산된 차이를 형식화한 문자열로 변환
            String totalTime = formatTimeDifference(timeDifferenceInMillis);
            // TextView에 총 근무 시간 표시
            binding.tvResult.setText(totalTime);
            // tvSalary에 입력된 급여값을 가져오기
            String salaryString = binding.tvSalary.getText().toString();

            try {
                // 급여값을 숫자로 파싱
                double salary = Double.parseDouble(salaryString);

                // 총 일한 시간과 급여를 곱해서 일일 급여를 계산
                double dailySalary = timeDifferenceInMillis * salary / (60 * 60 * 1000); // 시간을 시간 단위로 변환
                // 계산된 일일 급여를 정수로 변환하여 형식화한 문자열로 설정
                String formattedDailySalary = String.format(Locale.getDefault(), "%.0f", dailySalary);
                // TextView에 일일 급여 표시
                binding.tvDailySalary.setText(formattedDailySalary);
            } catch (NumberFormatException e) {
                // 급여값이 숫자로 변환되지 않으면 예외 처리
            }
        } else {
            // 시작 시간이나 종료 시간이 유효하지 않은 경우 처리

        }
    }

    // 주어진 시간 문자열을 Calendar 객체로 파싱하는 메소드
    private Calendar parseTimeString(String timeString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
            Date date = sdf.parse(timeString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
            // 파싱 오류를 처리
            return null;
        }
    }

    // 두 시간 사이의 차이를 받아와 시간과 분으로 형식화하여 반환하는 메소드
    private String formatTimeDifference(long timeDifferenceInMillis) {
        // 시간 차이를 시간과 분으로 형식화
        long hours = timeDifferenceInMillis / (60 * 60 * 1000);
        long minutes = (timeDifferenceInMillis % (60 * 60 * 1000)) / (60 * 1000);

        return String.format("%d시간 %d분", hours, minutes);
    }

    public String getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserID" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("UserID", null);
    }
    public  void updatesal(String formattedDate, String startTime, String endTime, String salary, String userId, String dailySalary){
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.219.54:8089/updatesal",
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

                Map<String,String> params = new HashMap<>();
                params.put("workDay",formattedDate);
                params.put("startedAt",startTime);
                params.put("endedAt",endTime);
                params.put("workPay",salary);
                params.put("workUser",userId);
                //Spring서버에서도 "id","pw"로 받아야 함
                return params;
            }
        };
        queue.add(request);
    }
    public  void deletesal(int workSeq){
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.219.54:8089/deletesal",
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

                Map<String,String> params = new HashMap<>();
                params.put("workSeq", String.valueOf(workSeq));
                //Spring서버에서도 "id","pw"로 받아야 함
                return params;
            }
        };
        queue.add(request);
    }

}