package com.example.faindsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.faindsapplication.databinding.ActivityPwBinding;

public class PwActivity extends AppCompatActivity {
    private ActivityPwBinding binding;
    private EditText currentPw, newPw, confirmPw;
    private Button btnPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPwBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        currentPw=findViewById(R.id.currentPw);
        newPw=findViewById(R.id.newPw);
        confirmPw=findViewById(R.id.confirmPw);
        Button btnPw=(Button)findViewById(R.id.btnPw);

        btnPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentpw=currentPw.getText().toString();
                String newpw=newPw.getText().toString();
                String confirmpw=confirmPw.getText().toString();
            }
        });


    }
}