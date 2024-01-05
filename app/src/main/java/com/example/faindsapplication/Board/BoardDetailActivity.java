package com.example.faindsapplication.Board;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.BoardEditActivity;
import com.example.faindsapplication.Cmt.CmtAdapter;
import com.example.faindsapplication.Cmt.CmtVO;
import com.example.faindsapplication.MainActivity;
import com.example.faindsapplication.R;
import com.example.faindsapplication.databinding.ActivityBoardDetailBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoardDetailActivity extends AppCompatActivity {

    private ActivityBoardDetailBinding binding;
    private ArrayList<CmtVO> dataset;
    private CmtAdapter adapter;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBoardDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 게시글 제목과 내용을 인텐트에서 가져와 설정
        Intent intent = getIntent();
        String boardtitle = getIntent().getStringExtra("boardTitle");
        String boardcontent = getIntent().getStringExtra("boardContent");
        String boardWriter = getIntent().getStringExtra("boardWriter");

        Log.d("board???",boardWriter);
        Log.d("board???",getUserId());

        binding.boardDetailTitle.setText(boardtitle);
        binding.boardDetailContent.setText(boardcontent);

        // Volley 요청을 위한 큐 초기화
        if (queue == null) {
            queue = Volley.newRequestQueue(this);
        }

        // 댓글 데이터를 저장할 ArrayList 및 어댑터 초기화
        dataset = new ArrayList<>();
        adapter = new CmtAdapter(dataset);

        // 뒤로가기 버튼 클릭 시 BoardFragment로 이동
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardDetailActivity.this, MainActivity.class);
                intent.putExtra("moveFl", "board");
                startActivity(intent);
            }
        });

        // 로고 클릭 시 HomeFragment로 이동
        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BoardDetailActivity.this, MainActivity.class);
                intent.putExtra("moveFl", "home");
                startActivity(intent);
            }
        });


        // 댓글데이터 가져오는 메소드
        getComentData();

        // 댓글 리스트 리사이클러뷰 초기화
        LinearLayoutManager manager = new LinearLayoutManager(this);
        binding.cmtRV.setLayoutManager(manager);
        binding.cmtRV.setAdapter(adapter);

        // 댓글 작성 버튼 클릭 이벤트 처리
        binding.imgCmtWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력한 댓글 내용 가져오기
                String cmtContent = binding.tvCmtWrite.getText().toString();
                binding.tvCmtWrite.setText(""); // 댓글 입력 창 비우기

                // 댓글 작성 요청을 위한 JSON 객체 생성
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("cmtContent", cmtContent);
                    jsonBody.put("boardSeqId", getIntent().getIntExtra("boardSeq", 0));
                    jsonBody.put("cmtWriterUser", getUserId());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // 댓글 작성 요청
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        "http://192.168.219.54:8089/cmtwrite",
                        jsonBody,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("response", response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // 에러 처리
                    }
                });
                queue.add(jsonObjectRequest);
                // 댓글 작성 후 딜레이
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dataset.clear();
                        getComentData();
                    }
                }, 500); // 500 밀리초 (0.5초) 딜레이
            }
        });

        if (boardWriter.equals(getUserId())) {
            binding.btnBoardPopup.setVisibility(View.VISIBLE);
        }else{
            binding.btnBoardPopup.setVisibility(View.INVISIBLE);
        }

        // 수정삭제 팝업버튼
        binding.btnBoardPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(BoardDetailActivity.this, v);
                popupMenu.inflate(R.menu.popupboard);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.boardFix){

                            // 수정 기능 구현
                            Intent intent = new Intent(BoardDetailActivity.this, BoardEditActivity.class);
                            intent.putExtra("boardTitle", getIntent().getStringExtra("boardTitle"));
                            intent.putExtra("boardContent",getIntent().getStringExtra("boardContent"));
                            intent.putExtra("createdAt",getIntent().getStringExtra("createdAt"));
                            intent.putExtra("boardSeq",getIntent().getIntExtra("boardSeq",0));

                            startActivity(intent);

                        } else if (item.getItemId() == R.id.boardDelete){
                            // 삭제 기능 구현
                            DialogInterface.OnClickListener deleteListener =new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            };
                            DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            };
                            // 다이얼로그 구현
                            AlertDialog.Builder builder = new AlertDialog.Builder(BoardDetailActivity.this);
                            builder.setTitle("정말 삭제하시겠습니까?");
                            builder.setPositiveButton("삭제",deleteListener);
                            builder.setNegativeButton("취소",cancelListener);
                            builder.show();
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    // 사용자 ID를 가져오는 메서드
    public String getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserID" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("UserID", null);
    }


    // 댓글 리스트를 서버에서 가져오는 메소드
    public void getComentData() {
        // 게시글 번호 가져오기
        getIntent().getIntExtra("boardSeq", 0);
        String boardSeqId = String.valueOf(getIntent().getIntExtra("boardSeq", 0));

        // 댓글 리스트 요청
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.219.54:8089/cmtlist",
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

                                // 각 필요한 데이터를 추출
                                String cmtWriter = jsonObject.getString("cmtWriterUser");
                                String cmtContent = jsonObject.getString("cmtContent");
                                String createdAt = jsonObject.getString("createdAt");

                                // 데이터셋에 추가
                                dataset.add(new CmtVO(cmtWriter, cmtContent, createdAt));
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
                // 에러 처리
            }
        }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // 게시글 번호를 파라미터로 전달
                Map<String, String> params = new HashMap<>();
                params.put("boardSeqId", boardSeqId);
                return params;
            }
        };
        queue.add(request);
    }
}