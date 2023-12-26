package com.example.faindsapplication.Board;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.faindsapplication.BoardWriteActivity;
import com.example.faindsapplication.Home.HomeFragment;
import com.example.faindsapplication.R;
import com.example.faindsapplication.Register.RegisterAdapter;
import com.example.faindsapplication.Register.RegisterVO;
import com.example.faindsapplication.databinding.FragmentBoardBinding;
import com.example.faindsapplication.databinding.FragmentRegisterBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding = null;
    private ArrayList<BoardVO> dataset = null;
    private BoardAdapter adapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HomeFragment.class);
                startActivity(intent);
            }
        });
        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(v.getContext(), HomeFragment.class);
                startActivity(intent1);
            }
        });
        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(v.getContext(), BoardWriteActivity.class);
                startActivity(intent2);
            }
        });

        dataset = new ArrayList<>();

        dataset.add(new BoardVO("w제목", "내용", 1, "1", "1"));

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.boardRV.setLayoutManager(manager);
        adapter = new BoardAdapter(dataset);
        binding.boardRV.setAdapter(adapter);
        return binding.getRoot();
    }
}