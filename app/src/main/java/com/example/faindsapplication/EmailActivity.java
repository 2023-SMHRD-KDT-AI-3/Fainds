package com.example.faindsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.faindsapplication.databinding.ActivityEmailBinding;

public class EmailActivity extends AppCompatActivity {

    private ActivityEmailBinding binding;
    private EditText currentEmail, newEmail;
    private Button btnEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        currentEmail=findViewById(R.id.currentEmail);
        newEmail=findViewById(R.id.newEmail);
        btnEmail=(Button)findViewById(R.id.btnEmail);

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateID(currentEmail,newEmail);
            }
        });
    }

    private void updateID(EditText currentEmail, EditText newEmail) {
    }


}