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
import android.widget.Toast;

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
        //1일 - 요일 매칭 시키기 위해 공백 add
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
                    startActivity(intent);
                }
            }
        });

    }
    // 그리드뷰 어댑터
    private class GridAdapter extends BaseAdapter {

        private List<CalenderVO> calenderList;

        private final LayoutInflater inflater;

        /**
         * 생성자
         *
         * @param context
         * @param list
         */
        public GridAdapter(Context context, List<CalenderVO> list) {
            this.calenderList = list;
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return calenderList.size();
        }

        @Override
        public CalenderVO getItem(int position) {
            return calenderList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_calender_gridview, parent, false);
                holder = new ViewHolder();

                holder.tvItemGridViewDate = convertView.findViewById(R.id.tv_item_gridview_date);
                holder.tvItemGridViewContent = convertView.findViewById(R.id.tv_item_gridview_content);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            // getItem 메소드를 통해 CalenderVO 객체를 가져옴
            CalenderVO calenderItem = getItem(position);
            String date = calenderItem.getDate();

            holder.tvItemGridViewDate.setText(date);

            // 해당 날짜에 대한 내용 설정
            String content = calenderItem.getDailySalary();
            holder.tvItemGridViewContent.setText(content);


            //해당 날짜 텍스트 컬러,배경 변경
            mCal = Calendar.getInstance();
            //오늘 day 가져옴
            Integer today = mCal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);
            if (sToday.equals(getItem(position))) { //오늘 day 텍스트 컬러 변경
                holder.tvItemGridViewDate.setTextColor(getResources().getColor(R.color.blue));
            }
            return convertView;
        }
    }

    private class ViewHolder {
        TextView tvItemGridViewDate;
        TextView tvItemGridViewContent;
    }
    // 근무정보 불러오기 및 입력
    protected void getSalData() {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.219.54:8089/calenderInfo",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            String utf8String = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                            JSONArray jsonArray = new JSONArray(utf8String);
                            Log.d("시작","시작");

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
                                String boardWriter;
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
                                        boardWriter = getUserId();
                                        workDay = jsonObject.getString("workDay");
                                        calenderDetailList.add(new CalenderDetailVO(startedAt, endedAt, workPay, workDay));
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
                                TextView totalSalaryTextView = findViewById(R.id.totalSalary);
                                String formattedMonthTotalSalary = NumberFormat.getInstance().format(monthTotalSalary) + "만원";
                                totalSalaryTextView.setText(formattedMonthTotalSalary);
                                TextView totalTimeTextView = findViewById(R.id.totalTime);
                                String formattedMonthTotalTime = NumberFormat.getInstance().format(monthTotalTime) + "시간";
                                totalTimeTextView.setText(formattedMonthTotalTime);
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
        queue.add(request);
    }

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
