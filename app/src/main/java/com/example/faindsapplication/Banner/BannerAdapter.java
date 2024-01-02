package com.example.faindsapplication.Banner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class BannerAdapter extends FragmentStateAdapter {

    public int mCount;

    public BannerAdapter(@NonNull FragmentActivity fragmentActivity, int count) {
        super(fragmentActivity);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index==0){
            return new Banner_1();
        }else if(index==1){
            return new Banner_2();
        }
        else return new Banner_2();
    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    public int getRealPosition(int position){
        return position % mCount;
    }
}
