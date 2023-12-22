package com.example.faindsapplication.Register;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.PopupActivity;
import com.example.faindsapplication.R;
import com.example.faindsapplication.RegisterDetail.RegisterDetailActivity;

import java.util.ArrayList;

public class RegisterAdapter extends RecyclerView.Adapter<RegisterViewHolder> {
    private ArrayList<RegisterVO> dataset;
    public RegisterAdapter(ArrayList<RegisterVO> dataset) {
        this.dataset = dataset;
    }

    @NonNull
    @Override
    public RegisterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_register, parent, false);
        RegisterViewHolder holder = new RegisterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RegisterViewHolder holder, int position) {
        String RegisterName = dataset.get(position).getContractName();
        String RegisterExample = dataset.get(position).getContractExample();
        int RegisterTypeImg = dataset.get(position).getContractImg();
        holder.getTvRegisterName().setText(RegisterExample);
        holder.getTvRegisterExample().setText(RegisterExample);
        holder.getImgRegisterType().setImageResource(RegisterTypeImg);
        holder.listener = new RegisterItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                Intent intent = new Intent(v.getContext(), PopupActivity.class);
                intent.putExtra("RegisterName",RegisterName);
                v.getContext().startActivity(intent);
            }
        };
}

    @Override
    public int getItemCount() {

        return dataset.size();
    }
}
