package com.example.faindsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.faindsapplication.databinding.ActivityModifBinding;

public class ModifActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityModifBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityModifBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.edtEmail.setOnClickListener(this);
        binding.edtPw.setOnClickListener(this);
        binding.edtChannel1.setOnClickListener(this);
        binding.edtChannel2.setOnClickListener(this);
        binding.edtChannel3.setOnClickListener(this);
        binding.edtSal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId=v.getId();
        Intent intent=null;

        if (viewId== R.id.edtEmail) {
            intent=new Intent(this, EmailActivity.class);
        }else if (viewId==R.id.edtPw){
            intent=new Intent(this, PwActivity.class);
        }else if(viewId==R.id.edtChannel1) {
            intent=new Intent();
        }else if(viewId==R.id.edtChannel2) {
            intent=new Intent();

        }else if(viewId==R.id.edtChannel3) {

        }else
        {
            intent=new Intent();
        }

    }

}