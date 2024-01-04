package com.example.faindsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;

import com.example.faindsapplication.Board.BoardDetailActivity;
import com.example.faindsapplication.Setting.SettingFragment;
import com.example.faindsapplication.databinding.ActivityTipBinding;

public class TipActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityTipBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityTipBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnWorkRepo1.setOnClickListener(this);
        binding.btnWorkRepo2.setOnClickListener(this);

        // 로고 클릭 시 HomeFragment로 이동
        binding.imgLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TipActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // 뒤로가기 버튼 클릭시 이전 화면으로 돌아가기
        binding.imgBack4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }




    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        //intent를 활용하여 설치되어 있는 웹 활용하기
        Intent intent=null;

        if(viewId == R.id.btnWorkRepo1){
            //실행될 액티비티에 전달할 값을 정의
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.moel.go.kr/policy/policydata/view.do?bbs_seq=20190700008"));
        }else {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.work.go.kr/workContract/workContractInfoNew.do"));

        }
        startActivity(intent);

        binding.imgLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
