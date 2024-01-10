package com.example.faindsapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class FlaskConnect {
    private Context context;
    private boolean isRequesting = false;
    public void flaskconn(String imgurl,String contracttype, Context context, FlaskResponseListener listener){
        if (isRequesting){
            return;
        }
        isRequesting = true;
        this.context = context;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="http://192.168.219.47:5000/receive_data";


        // 전송할 JSON 데이터
        JSONObject postData = new JSONObject();
        try {
            postData.put("Data", imgurl);
            postData.put("Data2", contracttype);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // JSON Request 생성 및 전송
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // 응답 처리
                        Log.d("Response", response.toString());
                        listener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 에러 처리
                Log.d("Error.Response", error.toString());
                listener.onError("flask 통신 실패..");
            }
        });

        jsonObjectRequest.setRetryPolicy(new com.android.volley.DefaultRetryPolicy(

                50000,

                com.android.volley.DefaultRetryPolicy.DEFAULT_MAX_RETRIES,

                com.android.volley.DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        // 요청을 요청 큐에 추가
        queue.add(jsonObjectRequest);
        isRequesting = false;

    }
}
