package com.example.faindsapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.faindsapplication.RegisterDetail.RegisterDetailActivity;
import com.example.faindsapplication.databinding.ActivityPopupBinding;

public class PopupActivity extends AppCompatActivity {
    private TextView txt;
    private ActivityPopupBinding binding;


    private ActivityResultLauncher<Intent> cameraLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        // 기본 갤러리에서 선택한 이미지를 Uri값으로 가져온 후 ImageView에 초기화
                        Intent data = result.getData();
                        String registerdata = getIntent().getStringExtra("RegisterName");
                        Log.d("계약서내용", "onActivityResult: "+registerdata);
                        Bundle bundle = data.getExtras(); // 캡처한 이미지 저장 공간을 접근
                        Bitmap bitmap = (Bitmap) bundle.get("data");
                        Intent intent = new Intent(PopupActivity.this, RegisterDetailActivity.class);
                        intent.putExtra("TestImg",bitmap);
                        intent.putExtra("RegisterName",registerdata);
                        startActivity(intent);
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> albumLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK){
                        // 기본 갤러리에서 선택한 이미지를 Uri값으로 가져온 후 ImageView에 초기화
                        Intent data = result.getData();
                        String registerdata = getIntent().getStringExtra("RegisterName");
                        Log.d("계약서내용", "onActivityResult: "+registerdata);
                        Uri imgUri = data.getData();
                        // RegisterDetailActivity로 이미지 URI를 전달
                        Intent intent = new Intent(PopupActivity.this, RegisterDetailActivity.class);
                        intent.putExtra("TestImgUri", imgUri);
                        intent.putExtra("RegisterName",registerdata);
                        startActivity(intent);

                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPopupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String data = ((Intent) intent).getStringExtra("RegisterName");
        Log.d("계약서내용", "onCreate: "+data);
        getWindow().getAttributes().gravity = Gravity.BOTTOM;


        // 카메라 버튼
        binding.btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra("RegisterName", data);
                cameraLauncher.launch(intent);

            }
        });
        // 갤러리 버튼
        binding.btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI,"image/*");
                intent.putExtra("RegisterName", data);
                // 선택한 이미지를 다시 받아올 수 있도록
                albumLauncher.launch(intent);
            }
        });

        // 닫기 버튼
        binding.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }
}