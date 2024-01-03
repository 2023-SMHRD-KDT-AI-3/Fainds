package com.example.faindsapplication.Home;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.faindsapplication.Banner.BannerAdapter;
import com.example.faindsapplication.R;
import com.example.faindsapplication.databinding.FragmentHomeBinding;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding = null;
    private ArrayList<HomeVO> dataset = null;
    private HomeAdapter adapter = null;
    private RequestQueue queue;
    // 배너 관련
    private ViewPager2 vp;
    private BannerAdapter bannerAdapter;
    private int num_page = 2;

    private Handler sliderHandler = new Handler();
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);

        // 메인페이지에서 보여줄 게시판 목록 리스트
        dataset = new ArrayList<>();

        // 현재 로그인한 아이디 가져오기
        binding.tvUserName.setText(getUserId());

        // 메인페이지에서 보여줄 게시판 목록 리스트 추가
        dataset.add(new HomeVO(1,"스타벅스 계약서1","표준근로계약서(미성년자)",R.drawable.icon_contract_student));
        dataset.add(new HomeVO(1,"스타벅스 계약서1","표준근로계약서(미성년자)",R.drawable.icon_contract_student));

        // 배너 초기화 메소드 호출
        Banner();

        // RecyclerView 설정
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.homeRV.setLayoutManager(manager);
        adapter = new HomeAdapter(dataset);
        binding.homeRV.setAdapter(adapter);

        return binding.getRoot();
    }

    // 배너 초기화 메소드
    private void Banner(){
        // banner
        vp = binding.banner;
        // adaptor
        bannerAdapter = new BannerAdapter(getActivity(), num_page);
        vp.setAdapter(bannerAdapter);
        vp.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        vp.setCurrentItem(0); // 시작지점
        vp.setOffscreenPageLimit(2);// 최대 이미지 수

        vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(positionOffsetPixels == 0){
                    vp.setCurrentItem(position);
                }
            }
            @Override
            public void onPageSelected(int position){
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRun);
                sliderHandler.postDelayed(sliderRun, 2000);
            }
        });
    }

    // 자동 슬라이드 실행 메소드
    private Runnable sliderRun = new Runnable() {
        @Override
        public void run() {
            binding.banner.setCurrentItem(binding.banner.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRun);
    }

    @Override
    public void onResume(){
        super.onResume();
        sliderHandler.postDelayed(sliderRun, 10000);
    }

    // 현재 로그인한 아이디 가져오는 메소드
    public String getUserId() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserID" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("UserID", null);
    }

}