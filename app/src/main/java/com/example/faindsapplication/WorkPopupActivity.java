package com.example.faindsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.faindsapplication.Calender.CalenderDetailActivity;
import com.example.faindsapplication.databinding.ActivityWorkPopupBinding;

public class WorkPopupActivity extends AppCompatActivity {

    private ActivityWorkPopupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWorkPopupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String data = ((Intent) intent).getStringExtra("ID");

        binding.workBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startTime = "";
                String endTime = "";
                if(data.equals("workStartTime")){
                    int startHour = binding.workTime.getHour();
                    int startMin = binding.workTime.getMinute();
                    startTime = String.format("%02d:%02d", startHour, startMin);
                } else if (data.equals("workEndTime")) {
                    int endHour = binding.workTime.getHour();
                    int endMin = binding.workTime.getMinute();
                    endTime = String.format("%02d:%02d", endHour, endMin);
                }
                saveWorkTime(startTime,endTime);
                finish();
            }
        });
    }

    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
    public void saveWorkTime(String startTime, String endTime) {
        // SharedPreferences 인스턴스 얻기
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // Editor를 사용하여 값을 저장
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("startTime", startTime);
        editor.putString("endTime",endTime);
        editor.apply();
    }
}