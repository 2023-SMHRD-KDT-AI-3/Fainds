package com.example.faindsapplication.Cmt;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.Board.BoardDetailActivity;
import com.example.faindsapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

// 댓글 목록을 표시하는 RecyclerView에 사용되는 Adapter
public class CmtAdapter extends RecyclerView.Adapter<CmtViewHolder> {

    // 댓글 목록 저장할 리스트
    private ArrayList<CmtVO> dataset;

    // CmtAdapter의 생성자
    public CmtAdapter(ArrayList<CmtVO> dataset){
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public CmtViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 뷰 홀더를 생성하고 반환
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cmt, parent, false);
        CmtViewHolder holder = new CmtViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CmtViewHolder holder, int position) {
        // 뷰 홀더에 데이터를 바인딩
        String cmtWriter = dataset.get(position).getCmtWriter();
        String cmtContent = dataset.get(position).getCmtContent();

        String createdAt = dataset.get(position).getCreatedAt();
        String formatTime = timeDi(createdAt);

        // 2. 날짜를 특정 형식으로 포맷팅
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.getDefault());


        holder.getCmtWriter().setText(cmtWriter);
        holder.getCmtContent().setText(cmtContent);
        holder.getCmtTime().setText(formatTime);
        holder.getCmtTime().setText(formatTime);

        holder.listener = new CmtItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Intent intent = new Intent(v.getContext(), BoardDetailActivity.class);
                intent.putExtra("cmtWriter",cmtWriter);
                intent.putExtra("cmtContent",cmtContent);
                intent.putExtra("createdAt",formatTime);

                v.getContext().startActivity(intent);
            }
        };


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    // 댓글 작성 시간을 현재 시간과 비교하여 "몇 분 전", "몇 시간 전", "몇 일 전" 등으로 변환하는 메소드
    public static String timeDi(String createdAt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault());
        try {
            Date createDate = sdf.parse(createdAt);
            Date currentDate = new Date();

            long timeDifference = currentDate.getTime() - createDate.getTime();

            // 밀리세컨드를 시간으로 변환
            long hours = timeDifference / (60 * 60 * 1000);

            if (hours < 1) {
                // 한 시간 미만이면 "몇 분 전"
                long minutes = timeDifference / (60 * 1000);
                if (minutes < 1) {
                    return "방금 전";
                } else {
                    return minutes + "분 전";
                }
            } else if (hours < 24) {
                // 하루 미만이면 "몇 시간 전"
                return hours + "시간 전";
            } else if(hours<48){
                long hours1 = hours-24;
                return "1일"+hours1+"시간 전";
            } else if(hours<72){
                long hours1 = hours-48;
                return "2일"+hours1+"시간 전";
            } else if(hours<96){
                long hours1 = hours-72;
                return "3일"+hours1+"시간 전";
            } else if(hours<120){
                long hours1 = hours-96;
                return "4일"+hours1+"시간 전";
            } else if(hours<144){
                long hours1 = hours-120;
                return "5일"+hours1+"시간 전";
            } else if(hours<168){
                long hours1 = hours-144;
                return "6일"+hours1+"시간 전";
            } else {
                // 그 이상이면 "yyyy-MM-dd HH:mm" 형식으로 표시
                SimpleDateFormat displayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
                return displayFormat.format(createDate);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return createdAt; // 파싱 실패 시 원본 날짜 문자열 반환
        }
    }


}
