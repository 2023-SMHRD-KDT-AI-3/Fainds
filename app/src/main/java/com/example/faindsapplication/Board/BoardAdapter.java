package com.example.faindsapplication.Board;

import android.content.Intent;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.faindsapplication.R;
import com.example.faindsapplication.Register.RegisterViewHolder;
import com.example.faindsapplication.databinding.ActivityBoardDetailBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BoardAdapter extends RecyclerView.Adapter<BoardViewHolder> {

    private ArrayList<BoardVO> dataset;
    public BoardAdapter(ArrayList<BoardVO> dataset) {
        this.dataset = dataset;
    }
    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 레이아웃 파일을 inflate하여 ViewHolder를 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board, parent, false);
        BoardViewHolder holder = new BoardViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        // 데이터셋에서 현재 위치에 해당하는 데이터 가져오기
        String boardTitle = dataset.get(position).getBoardTitle();
        String boardContent = dataset.get(position).getBoardContent();
        int boardCmtNum = dataset.get(position).getBoardCmtNum();
       // String createdAt = dataset.get(position).getCreated_at().substring(0,10) +" " +dataset.get(position).getCreated_at().substring(11,16);
        int boardSeq=dataset.get(position).getBoardSeq();

        // 뷰홀더에 데이터 설정
        holder.getBoardTitle().setText(boardTitle);
        holder.getBoardContent().setText(boardContent);
        holder.getBoardCmtNum().setText(String.valueOf(boardCmtNum));

      //  holder.getBoardTime().setText(createdAt);
        // 작성 시간을 변환하여 표시
        String createdAt = dataset.get(position).getCreated_at();
        String formatTime = timeDi(createdAt);
        holder.getBoardTime().setText(formatTime);
        // 게시글 수정 삭제 버튼을 위한 팝업 메뉴 설정
        holder.getBtnAddPopup().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
                popupMenu.inflate(R.menu.popupboard);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getItemId() == R.id.boardFix){
                            // 수정 기능 구현

                        } else if (item.getItemId() == R.id.boardDelete){
                            // 삭제 기능 구현

                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        // 게시글 클릭 시 상세 화면으로 이동
        holder.listener = new BoardItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Intent intent = new Intent(v.getContext(), BoardDetailActivity.class);
                intent.putExtra("boardTitle",boardTitle);
                intent.putExtra("boardContent",boardContent);
                intent.putExtra("createdAt",createdAt);
                intent.putExtra("boardSeq",boardSeq);

                Log.d("boardSeq1234", String.valueOf(boardSeq));
                Log.d("boardTitle1234", boardTitle);
                Log.d("boardContent1234", boardContent);

                v.getContext().startActivity(intent);
            }
        };
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    // 작성 시간을 "방금 전", "몇 분 전" 등으로 변환하는 메서드
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
                return "1일전";
            } else if(hours<72){
                return "2일전";
            } else if(hours<96){
                return "3일전";
            } else if(hours<120){
                return "4일전";
            } else if(hours<144){
                return "5일전";
            } else if(hours<168){
                return "6일전";
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
