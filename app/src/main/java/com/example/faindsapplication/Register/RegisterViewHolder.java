package com.example.faindsapplication.Register;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.R;

import org.w3c.dom.Text;

public class RegisterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    RegisterItemClickListener listener;
    private TextView tvRegisterName;
    private TextView tvRegisterExample;
    private ImageView imgRegisterType;

    public RegisterViewHolder(@NonNull View itemView) {
        super(itemView);
        // 레이아웃에서 각 View 요소를 찾아 초기화
        this.tvRegisterName = itemView.findViewById(R.id.tvRegisterName);
        this.tvRegisterExample = itemView.findViewById(R.id.tvRegisterExample);
        this.imgRegisterType = itemView.findViewById(R.id.imgRegisterType);
        // 아이템 클릭 시 이벤트 처리를 위해 OnClickListener 설정
        itemView.setOnClickListener(this);
    }

    public TextView getTvRegisterName() {
        return tvRegisterName;
    }

    public TextView getTvRegisterExample() {
        return tvRegisterExample;
    }

    public ImageView getImgRegisterType() {
        return imgRegisterType;
    }

    @Override
    public void onClick(View v) {
        this.listener.onItemClickListener(v,getLayoutPosition());
    }
}
