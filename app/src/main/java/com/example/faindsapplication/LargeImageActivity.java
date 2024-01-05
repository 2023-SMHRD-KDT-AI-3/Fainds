package com.example.faindsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
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

        // 인텐트에서 이미지 url 또는 uri를 받아옴
        String url = getIntent().getStringExtra("url");
        Uri uri = getIntent().getParcelableExtra("uri");

        if (url != null) {
            // URL로 이미지를 로드하는 경우
            Glide.with(LargeImageActivity.this).load(url).into(binding.imgLarge);
        } else if (uri != null) {
            // URI로 이미지를 로드하는 경우
            binding.imgLarge.setImageURI(uri);
        }

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