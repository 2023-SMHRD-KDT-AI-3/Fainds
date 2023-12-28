package com.example.faindsapplication.Setting;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.faindsapplication.Calender.CalenderActivity;
import com.example.faindsapplication.EmailActivity;
import com.example.faindsapplication.LoginActivity;
import com.example.faindsapplication.PwActivity;
import com.example.faindsapplication.R;
import com.example.faindsapplication.TipActivity;
import com.example.faindsapplication.databinding.ActivityEmailBinding;
import com.example.faindsapplication.databinding.FragmentSettingBinding;


public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(getLayoutInflater());

        // 이메일 수정 클릭 시
        binding.settingEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EmailActivity.class);
                startActivity(intent);
            }
        });

        // 비밀번호 수정 클릭시
        binding.settingPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PwActivity.class);
                startActivity(intent);
            }
        });

        // 신고채널 1 클릭시
        binding.settingDeclaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://labor.moel.go.kr/minwonApply/minwonFormat.do?searchVal=SN001"));
                startActivity(intent);
            }
        });

        //신고채널 2 클릭시
        binding.settingDeclaration2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("https://labor.moel.go.kr/reportCntr/illegalLaborType2.do"));
                startActivity(intent);
            }
        });
        // tip게시판 클릭시
        binding.settingTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), TipActivity.class);
                startActivity(intent);
            }
        });

        //급여 계산기 클릭시
        binding.settingDeclaration3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), CalenderActivity.class);
                startActivity(intent);
            }
        });

        //로그아웃 클릭시
        binding.settingCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeUserId();
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });


        return binding.getRoot();
    }
    public void removeUserId() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("UserID");
        editor.apply();
    }
}