package com.example.faindsapplication;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.util.Log;
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
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.Board.BoardAdapter;
import com.example.faindsapplication.Board.BoardVO;
import com.example.faindsapplication.Home.HomeFragment;
import com.example.faindsapplication.databinding.FragmentBoardBinding;
import com.example.faindsapplication.databinding.FragmentSearchBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SearchFragment extends Fragment {

    private FragmentSearchBinding binding = null;
    private ArrayList<BoardVO> dataset = null;
    private BoardAdapter adapter = null;
    private RequestQueue queue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        dataset = new ArrayList<>();
        adapter = new BoardAdapter(dataset);

        // Volley RequestQueue 초기화
        if (queue == null) {
            queue = Volley.newRequestQueue(requireContext());
        }

        // 전달된 키워드 받기
        String keyword = getArguments().getString("keyword");
        binding.tvBoardSearch.setText(keyword);
        getSearchBoardData(keyword);

        // 게시글 추가 버튼 클릭 시
        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
                startActivity(intent);
            }
        });

        // 로고 클릭 시 홈 화면으로 이동
        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                transaction.replace(R.id.fl, homeFragment);
                transaction.commit();
            }
        });
        binding.btnBoardSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = binding.tvBoardSearch.getText().toString();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                // Create a Bundle to pass data to SearchFragment
                Bundle bundle = new Bundle();
                bundle.putString("keyword", keyword);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                SearchFragment2 searchFragment2 = new SearchFragment2();
                searchFragment2.setArguments(bundle);

                transaction.replace(R.id.fl, searchFragment2);
                transaction.commit();
            }
        });


        // 리사이클러뷰 설정
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.SearchRV.setLayoutManager(manager);
        binding.SearchRV.setAdapter(adapter);
        return binding.getRoot();
    }

    // 키워드를 이용해 게시물 검색하는 메서드
    public void getSearchBoardData(String keyword) {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.219.47:8089/boardSearch",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // 인코딩 문제 해결
                            String utf8String = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                            JSONArray jsonArray = new JSONArray(utf8String);
                            Log.d("qwer", jsonArray.toString());

                            // 파싱한 데이터를 데이터셋에 추가
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.d("qwer", jsonObject.toString());

                                // 각 필요한 데이터를 추출
                                int boardSeq = jsonObject.getInt("boardSeq");
                                String boardTitle = jsonObject.getString("boardTitle");
                                String boardContent = jsonObject.getString("boardContent");
                                String boardWriter = getUserId();
                                String createdAt = jsonObject.getString("createdAt");
                                int boardCmtNum = jsonObject.getInt("boardCmtNum");

                                // 데이터셋에 추가
                                dataset.add(new BoardVO(boardTitle, boardContent, createdAt, boardWriter, boardCmtNum, boardSeq));
                            }

                            // 어댑터에 데이터셋 변경을 알림
                            adapter.notifyDataSetChanged();

                        } catch (UnsupportedEncodingException e) {
                            // 예외 처리
                            e.printStackTrace();
                        }catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // 서버로 전송할 파라미터 설정
                Map<String,String> params = new HashMap<>();
                Log.d("keyword","실행");
                params.put("keyword",keyword);
                return params;
            }
        };
        queue.add(request);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dataset.clear();
                getSearchBoardData(keyword);
            }
        }, 1000); // 500 밀리초 (0.5초) 딜레이
    }
    public String getUserId() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserID" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("UserID", null);
    }
}