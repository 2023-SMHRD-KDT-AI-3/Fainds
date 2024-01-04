package com.example.faindsapplication.Register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.faindsapplication.Home.HomeFragment;
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

        binding.imgLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                transaction.replace(R.id.fl, homeFragment);
                transaction.commit();
            }
        });
        // 데이터셋 초기화 및 데이터 추가
        dataset = new ArrayList<>();
        dataset.add(new RegisterVO("표준근로계약서(기간의 정함이 없음)","정규직 근로자, 장기적인 근로자 등",R.drawable.icon_contract_regular));
        dataset.add(new RegisterVO("표준근로계약서(기간의 정함이 있음)","아르바이트생, 계약직 근로자, 대체 근로자 등",R.drawable.icon_irregular1));
        dataset.add(new RegisterVO("표준근로계약서(18세 미만인 자)","학생, 미성년자 아르바이트생 등",R.drawable.icon_contract_student));
        dataset.add(new RegisterVO("건설일용근로자 표준근로계약서","건설현장 관리자, 건축기사, 건설일용근로자 등", R.drawable.icon_contract_architect));

        // 리사이클러뷰 설정
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.registerRV.setLayoutManager(manager);
        adapter = new RegisterAdapter(dataset);
        binding.registerRV.setAdapter(adapter);


        return binding.getRoot();
    }
}