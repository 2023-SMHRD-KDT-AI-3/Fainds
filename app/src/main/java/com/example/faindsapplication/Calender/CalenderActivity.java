package com.example.faindsapplication.Calender;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.faindsapplication.MainActivity;
import com.example.faindsapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CalenderActivity extends Activity {

    // 연월 텍스트뷰
    private TextView tvDate;
    // 그리드뷰 어댑터
    private GridAdapter gridAdapter;

    // 리스트
    private ArrayList<CalenderVO> calenderList;
    private ArrayList<CalenderDetailVO> calenderDetailList;
    private Map<String, Double> dailySalaryMap;
    // 그리드뷰
    private GridView gridView;

    // 캘린더 변수
    private Calendar mCal;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calender);

        if (queue == null) {
            queue = Volley.newRequestQueue(this);
        }
        // 데이터 통신 메소드
        getSalData();

        tvDate = (TextView) findViewById(R.id.tv_date);
        gridView = (GridView) findViewById(R.id.gridview);
        ImageView imgBack = findViewById(R.id.imgBack);

        imgBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CalenderActivity.this, MainActivity.class);
                intent.putExtra("moveFl", "setting");
                startActivity(intent);
            }
        });

        // 오늘에 날짜를 세팅 해준다.
        long now = System.currentTimeMillis();
        final Date date = new Date(now);
        //연,월,일을 따로 저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜 텍스트뷰에 뿌려줌
        tvDate.setText(curYearFormat.format(date) + " / " + curMonthFormat.format(date));

        //gridview 요일 표시
        calenderList = new ArrayList<CalenderVO>();
        calenderDetailList = new ArrayList<CalenderDetailVO>();
        dailySalaryMap = new HashMap<>();

        mCal = Calendar.getInstance();

        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //1일 - 요일 매칭 시키기 위해 공백 추가
        for (int i = 1; i < dayNum; i++) {
            calenderList.add(new CalenderVO("", ""));
        }

        gridAdapter = new GridAdapter(getApplicationContext(), calenderList);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // getItem 메소드로 CalenderVO 객체를 가져옴
                CalenderVO selectedCalenderItem = gridAdapter.getItem(position);
                // CalenderVO 객체에서 날짜를 가져옴
                String selectedDate = selectedCalenderItem.getDate();
                int realDate = Integer.parseInt(selectedDate);
                String startedAt = "";
                String endedAt = "";
                String workPay = "";
                String formattedDate = "";
                String workTimeString = "";
                String totalSalaryString = "";
                int workSeq = 0;
                long intWorkTime, intTotalSalary;
                double workTime, totalSalary;

                if(realDate<10) {
                    formattedDate = curMonthFormat.format(date) + "월 0" + selectedDate + "일";
                } else {
                    formattedDate = curMonthFormat.format(date) + "월 " + selectedDate + "일";
                }
                // 해당 날짜에 대한 정보가 있는지 확인
                for (CalenderDetailVO calenderDetail : calenderDetailList) {

                    if (calenderDetail.getWorkDay().equals(formattedDate)) {
                        startedAt = calenderDetail.getStartedAt();
                        endedAt = calenderDetail.getEndedAt();
                        workPay = calenderDetail.getWorkPay();
                        workTime = getTimeDifference(startedAt,endedAt);
                        totalSalary = workTime*(Double.parseDouble(workPay));
                        intWorkTime = Math.round(workTime);
                        intTotalSalary = Math.round(totalSalary);
                        workTimeString = String.valueOf(intWorkTime)+"시간";
                        totalSalaryString = String.valueOf(intTotalSalary);
                        workSeq = calenderDetail.getWorkSeq();
                        break;
                    }
                }
                if (!selectedDate.isEmpty()) {
                    Intent intent = new Intent(CalenderActivity.this, CalenderDetailActivity.class);
                    intent.putExtra("currentDate", formattedDate);
                    intent.putExtra("startedAt", startedAt);
                    intent.putExtra("endedAt", endedAt);
                    intent.putExtra("workPay", workPay);
                    intent.putExtra("workTimeString",workTimeString);
                    intent.putExtra("totalSalaryString",totalSalaryString);
                    intent.putExtra("workSeq",workSeq);
                    startActivity(intent);
                }
            }
        });

    }
    // 그리드뷰에 사용될 어댑터 클래스입니다.
    private class GridAdapter extends BaseAdapter {
        private List<CalenderVO> calenderList;
        private final LayoutInflater inflater;

        /**
         * @param context 현재 액티비티의 컨텍스트
         * @param list 캘린더 아이템의 리스트
         */
        public GridAdapter(Context context, List<CalenderVO> list) {
            this.calenderList = list;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        // 아이템의 개수를 반환합니다.
        @Override
        public int getCount() {
            return calenderList.size();
        }


        //특정 위치의 아이템을 반환합니다.
        /*
         * @param position 아이템의 위치
         * @return 해당 위치의 아이템 객체
         */
        @Override
        public CalenderVO getItem(int position) {
            return calenderList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * 각 아이템에 대한 뷰를 반환합니다.
         *
         * @param position    아이템의 위치
         * @param convertView 재사용되는 뷰
         * @param parent      부모 뷰그룹
         * @return 각 아이템에 대한 뷰
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                // 뷰가 초기화되지 않은 경우 새로운 레이아웃을 인플레이트하여 홀더에 저장
                convertView = inflater.inflate(R.layout.item_calender_gridview, parent, false);
                holder = new ViewHolder();
                holder.tvItemGridViewDate = convertView.findViewById(R.id.tv_item_gridview_date);
                holder.tvItemGridViewContent = convertView.findViewById(R.id.tv_item_gridview_content);
                convertView.setTag(holder);
            } else {
                // 뷰가 이미 초기화된 경우 기존의 홀더를 재사용
                holder = (ViewHolder) convertView.getTag();
            }

            // 현재 위치에 있는 캘린더 아이템을 가져와서 날짜와 내용을 뷰에 설정
            CalenderVO calenderItem = getItem(position);
            String date = calenderItem.getDate();
            holder.tvItemGridViewDate.setText(date);

            String content = calenderItem.getDailySalary();
            holder.tvItemGridViewContent.setText(content);


            // 해당 날짜에 대한 텍스트 컬러와 배경 변경
            mCal = Calendar.getInstance();
            //오늘 day 가져옴
            Integer today = mCal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);
            // 오늘 날짜와 현재 아이템의 날짜가 일치하는 경우 텍스트 컬러를 변경
            if (sToday.equals(getItem(position))) {
                holder.tvItemGridViewDate.setTextColor(getResources().getColor(R.color.blue));
            }
            return convertView;
        }
    }
    // 아이템의 뷰를 저장하는 viewHolder 클래스
    private class ViewHolder {
        TextView tvItemGridViewDate;
        TextView tvItemGridViewContent;
    }

    // 서버에서 근무 정보를 가져와서 캘린더에 표시하는 메소드
    protected void getSalData() {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.219.47:8089/calenderInfo",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // 서버로부터 받은 응답을 UTF-8로 디코딩한 후 JSON 배열로 변환
                            String utf8String = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                            JSONArray jsonArray = new JSONArray(utf8String);
                            Log.d("시작","시작");

                            // 서버 응답이 없는 경우 빈 캘린더 데이터를 생성
                            if (jsonArray.length() == 0) {
                                for (int f = 0; f < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); f++) {
                                    calenderList.add(new CalenderVO("" + (f + 1), ""));
                                    gridAdapter.notifyDataSetChanged();
                                }
                            }
                            else {
                                String startedAt;
                                String endedAt;
                                String workPay;
                                int workSeq;
                                String workDay;
                                int j = 0;
                                int monthTotalSalary = 0;
                                int monthTotalTime = 0;

                                for (int f = 0; f < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); f++) {

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                                        Log.d("qwer", jsonObject.toString());
                                        // 각 필요한 데이터를 추출
                                        startedAt = jsonObject.getString("startedAt");
                                        endedAt = jsonObject.getString("endedAt");
                                        workPay = jsonObject.getString("workPay");

                                        workDay = jsonObject.getString("workDay");
                                        workSeq = jsonObject.getInt("workSeq");
                                        calenderDetailList.add(new CalenderDetailVO(startedAt, endedAt, workPay, workDay,workSeq));
                                    }
                                    // dailyTotalSalary 계산
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                                    double dailyPay = Double.parseDouble(calenderDetailList.get(j).getWorkPay());
                                    double daysWorked = getTimeDifference(calenderDetailList.get(j).getStartedAt(), calenderDetailList.get(j).getEndedAt());
                                    double totalSalaryForDay = (dailyPay * daysWorked)/10000;
                                    dailySalaryMap.put(calenderDetailList.get(j).getWorkDay(), totalSalaryForDay);


                                    // 일자 표시 및 일별 일한 금액 표시
                                    String matchDay = "";
                                    if(f<9){
                                        matchDay = "01월 0" + (f + 1) + "일";
                                    } else {
                                        matchDay = "01월 " + (f + 1) + "일";
                                    }

                                    if (matchDay.equals(calenderDetailList.get(j).getWorkDay())) {
                                        String formattedSalary = NumberFormat.getInstance().format(totalSalaryForDay) + "만원";
                                        calenderList.add(new CalenderVO("" + (f + 1), formattedSalary));
                                        monthTotalSalary += totalSalaryForDay;
                                        monthTotalTime += daysWorked;
                                        j++;
                                    } else {
                                        calenderList.add(new CalenderVO("" + (f + 1), ""));
                                    }
                                }

                                // 총 급여와 총 근무 시간을 화면에 업데이트
                                TextView totalSalaryTextView = findViewById(R.id.totalSalary);
                                String formattedMonthTotalSalary = NumberFormat.getInstance().format(monthTotalSalary) + "만원";
                                totalSalaryTextView.setText(formattedMonthTotalSalary);
                                TextView totalTimeTextView = findViewById(R.id.totalTime);
                                String formattedMonthTotalTime = NumberFormat.getInstance().format(monthTotalTime) + "시간";
                                totalTimeTextView.setText(formattedMonthTotalTime);

                                // 그리드 어댑터에 데이터가 변경되었음을 알림
                                gridAdapter.notifyDataSetChanged();
                            }

                        } catch (
                                JSONException e) {
                            throw new RuntimeException(e);
                        } catch (
                                UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 에러 발생 시 처리
            }
        }

        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("workUser", getUserId());
                return params;
            }
        };
        // 요청을 큐에 추가
        queue.add(request);
    }

    // 두 시간 문자열 사이의 차이를 계산하여 시간 단위로 반환하는 메소드
    private double getTimeDifference(String startDateString, String endDateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            Date startDate = dateFormat.parse(startDateString);
            Date endDate = dateFormat.parse(endDateString);

            long differenceInMillis = endDate.getTime() - startDate.getTime();
            return differenceInMillis / (60 * 60 * 1000);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        // "UserID" 키로 저장된 값을 반환. 값이 없다면 null 반환
        return sharedPreferences.getString("UserID", null);
    }
}
