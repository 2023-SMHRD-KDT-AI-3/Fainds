package com.example.faindsapplication.Register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faindsapplication.R;
import com.example.faindsapplication.databinding.FragmentRegisterBinding;

import java.util.ArrayList;


public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding = null;
    // recycler뷰를 가져오기위해
    private ArrayList<RegisterVO> dataset = null;
    private RegisterAdapter adapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater,container,false);
        dataset = new ArrayList<>();
        dataset.add(new RegisterVO("표준근로계약서(기간의 정함이 없음)","정규직 회사원 등",R.drawable.icon_contract_regular));
        dataset.add(new RegisterVO("표준근로계약서(기간의 정함이 있음)","아르바이트생, 일용직 근로자 등",R.drawable.icon_contract_irregular));
        dataset.add(new RegisterVO("표준근로계약서(미성년자용)","학생, 미성년자 아르바이트생 등",R.drawable.icon_contract_student));

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.registerRV.setLayoutManager(manager);
        adapter = new RegisterAdapter(dataset);
        binding.registerRV.setAdapter(adapter);


        return binding.getRoot();
    }
}