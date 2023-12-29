package com.example.faindsapplication.Board;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.BoardWriteActivity;
import com.example.faindsapplication.Cmt.CmtAdapter;
import com.example.faindsapplication.R;
import com.example.faindsapplication.Register.RegisterAdapter;
import com.example.faindsapplication.Register.RegisterVO;
import com.example.faindsapplication.databinding.FragmentBoardBinding;
import com.example.faindsapplication.databinding.FragmentRegisterBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding = null;
    private ArrayList<BoardVO> dataset = null;
    private BoardAdapter adapter = null;
    private RequestQueue queue;
    private boolean searcheck = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);

        dataset = new ArrayList<>();
        adapter = new BoardAdapter(dataset);
        if (queue == null) {
            queue = Volley.newRequestQueue(requireContext());
        }

      getBoardData();



        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BoardWriteActivity.class);
                startActivity(intent);
            }
        });

        binding.btnBoardSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.boardRV.setLayoutManager(manager);
        binding.boardRV.setAdapter(adapter);
        return binding.getRoot();
    }

    public String getUserId() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserID" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("UserID", null);
    }

    public void getBoardData() {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.219.54:8089/board",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                             String utf8String = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                            JSONArray jsonArray = new JSONArray(utf8String);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.d("getboard", jsonObject.toString());
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
                        }catch (UnsupportedEncodingException e) {
                            // 예외 처리
                            e.printStackTrace();
                        }  catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        queue.add(request);
    }


    public void getSearchBoardData() {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.219.54:8089/boardSearch",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                              JSONArray jsonArray = new JSONArray(response);
                            // 파싱한 데이터를 데이터셋에 추가
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Log.d("searchboard", jsonObject.toString());
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
                        } catch (JSONException e) {
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

                Map<String,String> params = new HashMap<>();
                String keyword = binding.tvBoardSearch.getText().toString();
                params.put("keyword",keyword);
                Log.d("keyword",keyword);
                return params;
            }
        };
        queue.add(request);
    }
}