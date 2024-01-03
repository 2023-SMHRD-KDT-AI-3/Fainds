package com.example.faindsapplication.Banner;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class BannerAdapter extends FragmentStateAdapter implements View.OnClickListener{

    public int mCount;

    public BannerAdapter(@NonNull FragmentActivity fragmentActivity, int count) {
        super(fragmentActivity);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // banner 넘겨주기
        int index = getRealPosition(position);
        if (index == 0) {
            Banner_1 banner1 = new Banner_1();
            return banner1;
        } else if (index == 1) {
            Banner_2 banner2 = new Banner_2();
            return banner2;
        } else {
            Banner_3 banner3 = new Banner_3();
            return banner3;
        }


    }






    @Override
    public int getItemCount() {
        return 100;
    }

    public int getRealPosition(int position){
        return position % mCount;
    }


    @Override
    public void onClick(View v) {
    }
}
