package com.example.faindsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;

public class LargeImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image);

        ImageView imgLarge = findViewById(R.id.imgLarge);

        // 인텐트에서 이미지 경로 또는 리소스 ID를 받아옴
        String imagePath = getIntent().getStringExtra("imagePath");

        // imagePath가 리소스 ID일 경우
        // int resourceId = getIntent().getIntExtra("imageResourceId", 0);
        // imgLarge.setImageResource(resourceId);

        // imagePath가 파일 경로일 경우
//        Glide.with(this).load(new File(imagePath)).into(imgLarge);

        // 닫기 버튼 클릭 이벤트
        ImageButton btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 액티비티 종료
            }
        });
    }
}