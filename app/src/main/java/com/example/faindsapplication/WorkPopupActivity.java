package com.example.faindsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

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
                Intent resultIntent = new Intent();
                // startTime과 endTime을 Intent에 담음
                resultIntent.putExtra("startTime", startTime);
                resultIntent.putExtra("endTime", endTime);
                // setResult 메소드를 사용하여 결과를 설정
                setResult(RESULT_OK, resultIntent);

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

}