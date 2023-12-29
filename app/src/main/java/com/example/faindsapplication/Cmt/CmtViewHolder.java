package com.example.faindsapplication.Cmt;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.faindsapplication.R;

public class CmtViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    CmtItemClickListener listener;

    private TextView cmtWriter;
    private TextView cmtContent;
    private TextView cmtTime;

    public CmtViewHolder(@NonNull View itemView) {
        super(itemView);
        this.cmtWriter = itemView.findViewById(R.id.tvCmtWriter);
        this.cmtContent = itemView.findViewById(R.id.tvCmtContent);
        this.cmtTime = itemView.findViewById(R.id.tvCmtTime);

        itemView.setOnClickListener(this);
    }

    public CmtItemClickListener getListener() {
        return listener;
    }

    public TextView getCmtWriter() {
        return cmtWriter;
    }

    public TextView getCmtContent() {
        return cmtContent;
    }

    public TextView getCmtTime() {
        return cmtTime;
    }

    @Override
    public void onClick(View v) {
        this.listener.onItemClickListener(v,getLayoutPosition());
    }
}
