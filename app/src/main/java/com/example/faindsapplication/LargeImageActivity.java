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
        String url = getIntent().getStringExtra("url");

        // imagePath가 파일 경로일 경우
        Glide.with(LargeImageActivity.this).load(url).into(binding.imgLarge);

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