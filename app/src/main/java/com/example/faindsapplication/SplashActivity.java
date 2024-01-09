package com.example.faindsapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        checkCameraPermission();

    }

    private void checkCameraPermission() {
        // Android 버전이 23 이상인 경우 권한 체크
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkCameraPermissionGranted()) {
                // 카메라 권한이 이미 허용된 경우
                moveToLoginActivity();
            } else {
                // 카메라 권한이 허용되지 않은 경우
                requestCameraPermission();
            }
        } else {
            // Android 버전이 23 미만인 경우 권한을 요청하지 않음
            moveToLoginActivity();
        }
    }

    private boolean checkCameraPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestCameraPermission() {
        // 카메라 권한 요청
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.CAMERA},
                CAMERA_PERMISSION_REQUEST_CODE
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // 권한 요청 결과 처리
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 카메라 권한이 허용된 경우
                moveToLoginActivity();
            } else {
                // 권한이 거부된 경우
                showPermissionDeniedToast();
            }
        }
    }

    private void showPermissionDeniedToast() {
        Toast.makeText(this, "카메라 권한을 허용해야 앱을 사용할 수 있습니다.", Toast.LENGTH_SHORT).show();
        requestCameraPermission();
    }




    private void moveToLoginActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000); // 2초 딜레이 후 LoginActivity로 이동
    }

}