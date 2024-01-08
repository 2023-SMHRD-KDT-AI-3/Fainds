package com.example.faindsapplication;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.PaintDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.Manifest;

import com.example.faindsapplication.RegisterDetail.RegisterDetailActivity;
import com.example.faindsapplication.databinding.ActivityPopupBinding;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PopupActivity extends AppCompatActivity {
    private TextView txt;
    private ActivityPopupBinding binding;
    private Uri imageUri;

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
//                        Bundle bundle = data.getExtras(); // 캡처한 이미지 저장 공간을 접근
//                        Bitmap bitmap = (Bitmap) bundle.get("data");
                        Log.d("확인용", "onActivityResult: 확인");
                        Log.d("Uri확인용", "onActivityResult: "+imageUri);
                        Intent intent = new Intent(PopupActivity.this, RegisterDetailActivity.class);
                        intent.putExtra("TestImg",imageUri);
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
                requestPermissions();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    try {
                        Log.d("확인용", "onClick: 실행");
                        imageUri = createImageFile(); // 임시 파일의 Uri 생성
                    } catch (IOException ex) {
                        // 파일 생성 중 오류 발생 시 처리
                    }
                    if (imageUri != null) {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//                        intent.putExtra("RegisterName", data);
                        cameraLauncher.launch(intent);
                    }
                }
//                intent.putExtra("RegisterName", data);
//                cameraLauncher.launch(intent);
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
    private Uri createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return FileProvider.getUriForFile(this,
                "com.example.faindsapplication.fileprovider",
                image);
    }
    private static final int PERMISSIONS_REQUEST_CODE = 1;
    private static final String[] PERMISSIONS_REQUIRED = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private void requestPermissions() {
        // 권한이 이미 부여되었는지 확인
        boolean hasCameraPermission = ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        boolean hasWriteStoragePermission = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        // 필요한 권한이 없으면 사용자에게 권한 요청
        if (!hasCameraPermission || !hasWriteStoragePermission) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_REQUIRED, PERMISSIONS_REQUEST_CODE);
        }
    }
}