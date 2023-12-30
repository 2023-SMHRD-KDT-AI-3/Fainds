package com.example.faindsapplication.Calender;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;


import com.example.faindsapplication.MainActivity;
import com.example.faindsapplication.WorkPopupActivity;
import com.example.faindsapplication.databinding.ActivityCalenderDetailBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalenderDetailActivity extends AppCompatActivity {

    private TimePicker startTimePicker, endTimePicker;

    private ActivityCalenderDetailBinding binding;
    private static final int REQUEST_CODE_WORK_POPUP_START = 1;
    private static final int REQUEST_CODE_WORK_POPUP_END = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalenderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderDetailActivity.this, WorkPopupActivity.class);
                intent.putExtra("ID", "workStartTime");
                startActivityForResult(intent, REQUEST_CODE_WORK_POPUP_START);
            }
        });
        binding.tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderDetailActivity.this, WorkPopupActivity.class);
                intent.putExtra("ID", "workEndTime");
                startActivityForResult(intent, REQUEST_CODE_WORK_POPUP_END);
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String selectedDate = getIntent().getStringExtra("selectedDate");
        String currentMonth = getIntent().getStringExtra("currentMonth");
        String formattedDate = currentMonth +"월"+ selectedDate+"일";
        binding.tvDay.setText(formattedDate);

        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderDetailActivity.this, MainActivity.class);
                intent.putExtra("moveFl","home");
                startActivity(intent);
            }
        });
        binding.btnRegisterSalary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedDate = getIntent().getStringExtra("selectedDate");
                String currentMonth = getIntent().getStringExtra("currentMonth");
                String formattedDate = currentMonth + "월" + selectedDate + "일";
                String startTime = binding.tvStartTime.getText().toString();
                String endTime = binding.tvEndTime.getText().toString();
                String salary = binding.tvSalary.getText().toString();


                String userId = getUserId();
                RegisterSalary(formattedDate, startTime, endTime, salary, userId);
            }
        });


    }

    private void RegisterSalary(String formattedDate, String startTime, String endTime, String salary, String userId) {
        Log.d("test",formattedDate);
        Log.d("test",startTime);
        Log.d("test",endTime);
        Log.d("test",salary);
        Log.d("test",userId);

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_WORK_POPUP_START) {
                String startTime = data.getStringExtra("startTime");
                binding.tvStartTime.setText(startTime);
            } else if (requestCode == REQUEST_CODE_WORK_POPUP_END) {
                String endTime = data.getStringExtra("endTime");
                binding.tvEndTime.setText(endTime);
            }
        }
        Calendar startTime = parseTimeString(binding.tvStartTime.getText().toString());
        Calendar endTime = parseTimeString(binding.tvEndTime.getText().toString());

        if (startTime != null && endTime != null) {
            long timeDifferenceInMillis = endTime.getTimeInMillis() - startTime.getTimeInMillis();
            String totalTime = formatTimeDifference(timeDifferenceInMillis);
            binding.tvResult.setText(totalTime);
            // tvSalary에 입력된 급여값을 가져오기
            String salaryString = binding.tvSalary.getText().toString();

            try {
                // 급여값을 숫자로 파싱
                double salary = Double.parseDouble(salaryString);

                // 총 일한 시간과 급여를 곱해서 tvDailySalary에 표시
                double dailySalary = timeDifferenceInMillis * salary / (60 * 60 * 1000); // 시간을 시간 단위로 변환
                String formattedDailySalary = String.format(Locale.getDefault(), "%.0f", dailySalary);
                binding.tvDailySalary.setText(formattedDailySalary);
            } catch (NumberFormatException e) {
                // 급여값이 숫자로 변환되지 않으면 예외 처리
                binding.tvDailySalary.setText("Invalid Salary");
            }
        } else {
            binding.tvDailySalary.setText("Invalid Time");
        }
    }
    private Calendar parseTimeString(String timeString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
            Date date = sdf.parse(timeString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
            // 파싱 오류를 처리합니다.
            return null;
        }
    }

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
}