package com.example.faindsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.faindsapplication.databinding.ActivityLargeImageBinding;

import java.io.File;

public class LargeImageActivity extends AppCompatActivity {

    private ActivityLargeImageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLargeImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 인텐트에서 이미지 경로 또는 리소스 ID를 받아옴
        String imagePath = getIntent().getStringExtra("imagePath");

        // imagePath가 리소스 ID일 경우
        // int resourceId = getIntent().getIntExtra("imageResourceId", 0);
        // imgLarge.setImageResource(resourceId);

        String imageUrl = "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory&fname=https://k.kakaocdn.net/dn/EShJF/btquPLT192D/SRxSvXqcWjHRTju3kHcOQK/img.png";

        // imagePath가 파일 경로일 경우
        Glide.with(LargeImageActivity.this).load(imageUrl).into(binding.imgLarge);

        // 닫기 버튼 클릭 이벤트
        ImageView btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 액티비티 종료
            }
        });
    }
}