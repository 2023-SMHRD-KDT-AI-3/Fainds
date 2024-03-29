package com.example.faindsapplication.Setting;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.Calender.CalenderActivity;
import com.example.faindsapplication.EmailActivity;
import com.example.faindsapplication.Home.HomeFragment;
import com.example.faindsapplication.LoginActivity;
import com.example.faindsapplication.PwActivity;
import com.example.faindsapplication.R;
import com.example.faindsapplication.TipActivity;
import com.example.faindsapplication.databinding.FragmentSettingBinding;

import java.util.HashMap;
import java.util.Map;


public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding;

    private RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingBinding.inflate(getLayoutInflater());

        // Volley RequestQueue 초기화
        if (queue == null) {
            queue = Volley.newRequestQueue(requireContext());
        }

        // 이메일 수정 클릭 시
        binding.settingEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 서버에 사용자 이메일을 가져오기 위한 요청 보내기
                StringRequest request = new StringRequest(
                        Request.Method.POST,
                        "http://192.168.219.47:8089/settingemail",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Intent intent = new Intent(getActivity(), EmailActivity.class);
                                intent.putExtra("LoginEmail", response);
                                startActivity(intent);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
                ) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        String id = getUserId();
                        Map<String, String> params = new HashMap<>();
                        params.put("userId", id);
                        return params;
                    }
                };
                queue.add(request);

            }
        });
        // 로고 이미지 클릭 시 홈으로 이동
        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                transaction.replace(R.id.fl, homeFragment);
                transaction.commit();

            }
        });

        // 비밀번호 수정 클릭시
        binding.settingPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PwActivity.class);
                startActivity(intent);
            }
        });

        // 신고채널 1 클릭시
        binding.settingDeclaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://labor.moel.go.kr/minwonApply/minwonFormat.do?searchVal=SN001"));
                startActivity(intent);
            }
        });

        //신고채널 2 클릭시
        binding.settingDeclaration2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://labor.moel.go.kr/reportCntr/illegalLaborType2.do"));
                startActivity(intent);
            }
        });

        // tip게시판 클릭 시
        binding.settingTip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TipActivity.class);
                startActivity(intent);
            }
        });

        //급여 계산기 클릭시
        binding.settingCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalenderActivity.class);
                startActivity(intent);
            }
        });

        // 로그아웃 클릭 시
        binding.settingLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeUserId();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return binding.getRoot();
    }


    // 로그아웃 메소드
    public void removeUserId() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("UserID");
        editor.apply();
    }

    public String getUserId() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserID" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("UserID", null);
    }

}