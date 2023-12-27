package com.example.faindsapplication.Setting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faindsapplication.EmailActivity;
import com.example.faindsapplication.PwActivity;
import com.example.faindsapplication.R;
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

//        //급여 계산기 클릭시
//        binding.settingDeclaration3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getActivity(),);
//                startActivity(intent);
//            }
//        });

        //로그아웃 클릭시
//        binding.settingCalculator.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getActivity(),);
//                startActivity(intent);
//            }
//        });
        return binding.getRoot();
    }
}