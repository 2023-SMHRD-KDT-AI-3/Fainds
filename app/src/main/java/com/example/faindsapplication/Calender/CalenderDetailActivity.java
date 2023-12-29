package com.example.faindsapplication.Calender;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.faindsapplication.R;
import com.example.faindsapplication.WorkPopupActivity;
import com.example.faindsapplication.databinding.ActivityCalenderBinding;
import com.example.faindsapplication.databinding.ActivityCalenderDetailBinding;

import java.util.Calendar;

public class CalenderDetailActivity extends AppCompatActivity {

    private TimePicker startTimePicker, endTimePicker;

    private ActivityCalenderDetailBinding binding;

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
                v.getContext().startActivity(intent);
            }
        });
        binding.tvEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderDetailActivity.this, WorkPopupActivity.class);
                intent.putExtra("ID", "workEndTime");
                v.getContext().startActivity(intent);
            }
        });

        String startTime = getStartTime();
        String endTime = getEndTime();
        binding.tvStartTime.setText(startTime);
        binding.tvEndTime.setText(endTime);

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String selectedDate = getIntent().getStringExtra("selectedDate");
        String currentMonth = getIntent().getStringExtra("currentMonth");
        binding.tvMonth.setText(currentMonth);
        binding.tvDay.setText(selectedDate);
/*
        binding.btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateWorkDuration();
            }
        });

    }
    private void calculateWorkDuration() {
        // 현재 시간을 가져와서 시작 시간과 끝 시간을 설정
//        Calendar calendar = Calendar.getInstance();
//        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
//        int currentMinute = calendar.get(Calendar.MINUTE);

        int startHour = binding.startTimepicker.getHour();
        int startMinute = binding.startTimepicker.getMinute();
        int endHour = binding.endTimepicker.getHour();
        int endMinute = binding.endTimepicker.getMinute();

        // 시작 시간과 끝 시간을 분 단위로 변환
        int startTimeInMinutes = startHour * 60 + startMinute;
        int endTimeInMinutes = endHour * 60 + endMinute;
//        int currentTimeInMinutes = currentHour * 60 + currentMinute;

        // 만약 끝 시간이 시작 시간보다 이전이라면, 하루를 더해줌
        if (endTimeInMinutes < startTimeInMinutes) {
            endTimeInMinutes += 24 * 60;
        }

        // 일한 시간 계산
        int workDurationInMinutes = endTimeInMinutes - startTimeInMinutes;

        // 현재 시간이 시작 시간 이전이면 하루를 더해줌
//        if (currentTimeInMinutes < startTimeInMinutes) {
//            currentTimeInMinutes += 24 * 60;
//        }
//
//        // 현재까지의 시간 계산
//        int elapsedTimeInMinutes = currentTimeInMinutes - startTimeInMinutes;

        // 시간과 분으로 분리
        int hours = workDurationInMinutes / 60;
        int minutes = workDurationInMinutes % 60;

        // 결과 텍스트뷰에 표시
        String result = String.format("%d시간 %d분", hours, minutes);

        // 결과 텍스트뷰에 표시
        binding.tvStartTime.setText(result);
        */

    }
    public String getStartTime() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserPW" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("startTime", null);
    }
    public String getEndTime() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserPW" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("endTime", null);
    }
}