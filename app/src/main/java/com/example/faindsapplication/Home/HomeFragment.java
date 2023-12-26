package com.example.faindsapplication.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faindsapplication.Board.BoardAdapter;
import com.example.faindsapplication.Board.BoardVO;
import com.example.faindsapplication.R;
import com.example.faindsapplication.databinding.FragmentBoardBinding;
import com.example.faindsapplication.databinding.FragmentHomeBinding;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding = null;
    private ArrayList<HomeVO> dataset = null;
    private HomeAdapter adapter = null;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        dataset = new ArrayList<>();


        dataset.add(new HomeVO(1,"스타벅스 계약서1","표준근로계약서(미성년자)",R.drawable.icon_contract_student));
        dataset.add(new HomeVO(1,"스타벅스 계약서1","표준근로계약서(미성년자)",R.drawable.icon_contract_student));
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.homeRV.setLayoutManager(manager);
        adapter = new HomeAdapter(dataset);
        binding.homeRV.setAdapter(adapter);
        return binding.getRoot();
    }
}