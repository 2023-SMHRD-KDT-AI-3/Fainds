package com.example.faindsapplication.Board;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BoardAdapter {

    private ArrayList<BoardVO> dataset;

    private ArrayList<String> keyset;

    public BoardAdapter(ArrayList<BoardVO> dataset, ArrayList<String> keyset) {
        this.dataset = dataset;
        this.keyset = keyset;
    }

}
