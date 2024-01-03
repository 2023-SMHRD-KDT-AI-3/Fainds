package com.example.faindsapplication.Board;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.example.faindsapplication.BoardWriteActivity;
import com.example.faindsapplication.Home.HomeFragment;
import com.example.faindsapplication.R;
import com.example.faindsapplication.SearchFragment;
import com.example.faindsapplication.databinding.FragmentBoardBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding = null;
    private ArrayList<BoardVO> dataset = null;
    private BoardAdapter adapter = null;
    private RequestQueue queue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);

        // 데이터셋과 어댑터 초기화
        dataset = new ArrayList<>();
        adapter = new BoardAdapter(dataset);

        // Volley 요청을 위한 큐 초기화
        if (queue == null) {
            queue = Volley.newRequestQueue(requireContext());
        }
        // 게시글 데이터를 서버에서 가져오는 메소드
        getBoardData();

        // 게시글 추가 버튼 이벤트
        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // BoardWriteActivity로 이동
                Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
                startActivity(intent);
            }
        });

        // 홈 Fragment로 이동하는 이벤트 처리
        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                transaction.replace(R.id.fl, homeFragment);
                transaction.commit();
            }
        });

        // 검색 버튼 클릭 이벤트 처리
        binding.btnBoardSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 검색어를 가져와서 SearchFragment로 이동
                String keyword = binding.tvBoardSearch.getText().toString();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                Bundle bundle = new Bundle();
                bundle.putString("keyword", keyword);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                SearchFragment searchFragment = new SearchFragment();
                searchFragment.setArguments(bundle);

                transaction.replace(R.id.fl, searchFragment);
                transaction.commit();
            }
        });

        // 리사이클러뷰 초기화
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.boardRV.setLayoutManager(manager);
        binding.boardRV.setAdapter(adapter);
        return binding.getRoot();
    }

    // 사용자 ID를 가져오는 메서드
    public String getUserId() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserID" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("UserID", null);
    }

    // 서버에서 게시글 데이터를 가져오는 메서드
    public void getBoardData() {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.219.47:8089/board",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // 문자열을 UTF-8로 변환하고 JSONArray로 파싱
                            String utf8String = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                            JSONArray jsonArray = new JSONArray(utf8String);
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
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 에러처리
            }
        }
        );
        queue.add(request);
    }
}