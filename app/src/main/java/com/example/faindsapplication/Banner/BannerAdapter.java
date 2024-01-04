package com.example.faindsapplication.Banner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

// ViewPager에 사용될 배너 Fragment를 관리하는 어댑터 클래스
public class BannerAdapter extends FragmentStateAdapter {

    public int mCount;

     /* BannerAdapter 생성자
        @param fragmentActivity 어댑터를 사용하는 FragmentActivity
        @param count 배너의 개수
     */
    public BannerAdapter(@NonNull FragmentActivity fragmentActivity, int count) {
        super(fragmentActivity);
        mCount = count;
    }

     /* position에 해당하는 배너 Fragment 생성
        @param position ViewPager 내의 위치
        @return 생성된 배너 Fragment
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        // 인덱스에 따라 각 배너 Fragment를 생성하여 반환
        if(index==0){
            return new Banner_1();
        }else if(index==1){
            return new Banner_2();
        }else {
            return new Banner_3();
        }
    }

     /* ViewPager에서 사용할 총 배너 개수 반환
        @return 배너 개수
     */
    @Override
    public int getItemCount() {
        // 충분히 큰 수를 반환하여 무한 스크롤처럼 보이도록 설정
        return 2000;
    }

     /* 실제로 표시될 배너의 위치를 반환
        @param position ViewPager 내의 위치
        @return 표시될 배너의 위치
     */
    public int getRealPosition(int position){
        // 현재 위치를 배너 개수로 나눈 나머지를 반환하여 무한 스크롤 구현
        return position % mCount;
    }
}
