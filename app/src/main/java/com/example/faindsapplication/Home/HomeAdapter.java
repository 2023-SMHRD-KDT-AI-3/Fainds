package com.example.faindsapplication.Home;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.Board.BoardItemClickListener;
import com.example.faindsapplication.ContractDetail.ContractDetailActivity;
import com.example.faindsapplication.MainActivity;
import com.example.faindsapplication.R;

import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    private RequestQueue queue;
    private ArrayList<HomeVO> dataset;
    public HomeAdapter(ArrayList<HomeVO> dataset) {
        this.dataset = dataset;
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // item_main 레이아웃을 inflate하여 ViewHolder를 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        HomeViewHolder holder = new HomeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        // 데이터셋에서 현재 위치에 해당하는 데이터 가져오기
        int img = R.drawable.icon_contract_architect;
        String contractName = dataset.get(position).getContractName();
        String contractType = dataset.get(position).getContractType();
        String contractId = dataset.get(position).getContractId();
        String res = dataset.get(position).getRes();

        // 계약 유형에 따라 이미지 설정
        if(contractType.equals("표준근로계약서(기간의 정함이 없음)")){
            img = R.drawable.icon_contract_regular;
        } else if (contractType.equals("표준근로계약서(기간의 정함이 있음)")) {
            img = R.drawable.icon_irregular1;
        } else if (contractType.equals("표준근로계약서(18세 미만인 자)")) {
            img = R.drawable.icon_contract_student;
        }

        // 뷰홀더에 데이터 설정
        holder.getTvRegisterName().setText(contractName);
        holder.getTvRegisterExample().setText(contractType);
        holder.getImgRegisterType().setImageResource(img);
        holder.getDeleteContract().setOnClickListener(v -> {
            deleteData(v,contractId);
            Intent intent =  new Intent(v.getContext(), MainActivity.class);
            intent.putExtra("moveFl","home");
            v.getContext().startActivity(intent);
        });

        // 아이템 클릭 시의 이벤트 처리
        holder.listener = new BoardItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                // 계약 상세 화면으로 이동하는 Intent 생성
                Intent intent = new Intent(v.getContext(), ContractDetailActivity.class);
                intent.putExtra("contractId",contractId);
                intent.putExtra("res",res);
                v.getContext().startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void deleteData(View v, String userid){
        if(queue==null){
            queue = Volley.newRequestQueue(v.getContext());
        }
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.219.65:8089/mongo/deleteid",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("mongodelete", "onResponse: "+response);
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
                //전송방식을 POST로 지정했을 때 사용하는 메소드
                Map<String,String> params = new HashMap<>();
                params.put("userid",userid);
                return params;
            }
        };

        queue.add(request);
    }
}
