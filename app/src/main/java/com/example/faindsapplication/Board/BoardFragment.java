package com.example.faindsapplication.Board;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.R;
import com.example.faindsapplication.Register.RegisterAdapter;
import com.example.faindsapplication.Register.RegisterVO;
import com.example.faindsapplication.databinding.FragmentBoardBinding;
import com.example.faindsapplication.databinding.FragmentRegisterBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding = null;
    private ArrayList<BoardVO> dataset = null;
    private BoardAdapter adapter = null;

    private RequestQueue queue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater,container,false);

        dataset = new ArrayList<>();
        adapter = new BoardAdapter(dataset);


        if (queue == null) {
            queue = Volley.newRequestQueue(requireContext());
        }
        getBoardData();



       // dataset.add(new BoardVO("제목","내용",""));

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.boardRV.setLayoutManager(manager);
        binding.boardRV.setAdapter(adapter);
         return binding.getRoot();
    }
        public void getBoardData(){
            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    "http://192.168.219.54:8089/board",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                Log.d("qwer",jsonArray.toString());
                                // 파싱한 데이터를 데이터셋에 추가
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    Log.d("qwer",jsonObject.toString());
                                    // 각 필요한 데이터를 추출
                                    int boardSeq = jsonObject.getInt("boardSeq");
                                    String boardTitle = jsonObject.getString("boardTitle");
                                    String boardContent = jsonObject.getString("boardContent");

                                    String createdAt = jsonObject.getString("createdAt");
                                    int boardCmtNum = jsonObject.getInt("boardCmtNum");

                                    // 데이터셋에 추가
                                    dataset.add(new BoardVO(boardTitle, boardContent,createdAt,boardCmtNum));
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
            );
            queue.add(request);
        }


}