package com.example.faindsapplication;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.PulseRing;
import com.github.ybq.android.spinkit.style.WanderingCubes;

public class ProgressDialog extends Dialog {
    public ProgressDialog(@NonNull Context context) {
        super(context);

//        requestWindowFeature(Window.FEATURE_NO_TITLE); //title 없이
        setContentView(R.layout.progress_bar);

        //라이브러리 로딩이미지 사용
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite WanderingCubes = new WanderingCubes();
        progressBar.setIndeterminateDrawable(WanderingCubes);


    }
}