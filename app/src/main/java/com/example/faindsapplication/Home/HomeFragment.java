package com.example.faindsapplication.Home;

import static android.content.Context.MODE_PRIVATE;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.Board.BoardAdapter;
import com.example.faindsapplication.Board.BoardVO;
import com.example.faindsapplication.EmailActivity;
import com.example.faindsapplication.R;
import com.example.faindsapplication.databinding.FragmentBoardBinding;
import com.example.faindsapplication.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding = null;
    private ArrayList<HomeVO> dataset = null;
    private HomeAdapter adapter = null;
    private RequestQueue queue;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        dataset = new ArrayList<>();

        if (queue == null) {
            queue = Volley.newRequestQueue(requireContext());
        }
        mongofindall(getUserId());

        binding.btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = binding.tvSearch.getText().toString();
                sendSearchRequest(keyword);
            }
        });


        binding.tvUserName.setText(getUserId());


        dataset.add(new HomeVO(1,"스타벅스 계약서1","표준근로계약서(미성년자)",R.drawable.icon_contract_student));
        dataset.add(new HomeVO(1,"스타벅스 계약서1","표준근로계약서(미성년자)",R.drawable.icon_contract_student));
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.homeRV.setLayoutManager(manager);
        adapter = new HomeAdapter(dataset);
        binding.homeRV.setAdapter(adapter);
        return binding.getRoot();
    }

    private void sendSearchRequest(String keyword) {
        String url = "http://192.168.219.47:8089/search";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 에러 응답을 처리하는 코드
                Toast.makeText(getView().getContext(), "서버응답 실패", Toast.LENGTH_SHORT).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                //전송방식을 POST로 지정했을 때 사용하는 메소드
                //데이터를 전송할 때 Map형태로 구성하여 리턴해줘야 한다.
                // Map<String,String> 앞은 Key 뒤는 Value 임
                // Map은 인터페이스 Map을 상속받은 클래스가 HashMap
                Map<String,String> params = new HashMap<>();
                params.put("key",keyword);
                //Spring서버에서도 "keyword"로 받아야 함

                return params;
            }
        };
        queue.add(request);
    }

    public String getUserId() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserID" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("UserID", null);
    }

    public void mongofindall(String userid){
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.219.65:8089/mongo/findall",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String utf8String = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                            JSONArray jsonArray = new JSONArray(utf8String);
                            Log.d("qwer", jsonArray.toString());
                            Log.d("qwer", "onResponse: "+response);
                        }catch (Exception e){
                            e.printStackTrace();
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
                params.put("userid", userid);
                return params;
            }
        };

        queue.add(request);
    }
}