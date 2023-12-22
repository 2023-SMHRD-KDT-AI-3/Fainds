package com.example.faindsapplication.Board;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faindsapplication.R;
import com.example.faindsapplication.Register.RegisterAdapter;
import com.example.faindsapplication.Register.RegisterVO;
import com.example.faindsapplication.databinding.FragmentBoardBinding;
import com.example.faindsapplication.databinding.FragmentRegisterBinding;

import java.util.ArrayList;


public class BoardFragment extends Fragment {

    private FragmentBoardBinding binding = null;
    private ArrayList<RegisterVO> dataset = null;
    private RegisterAdapter adapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater,container,false);
        dataset = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.boardRV.setLayoutManager(manager);
        adapter = new RegisterAdapter(dataset);
        binding.boardRV.setAdapter(adapter);
        return binding.getRoot();
    }
}