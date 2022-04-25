package com.mobileprogramming.mobileprogamming3.camera;


import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImagViewAdapter  {
    private final Context context;
    private ArrayList<String> imagePathList;


    public ImagViewAdapter(Context context, ArrayList<String> imagePathList) {
        this.context = context;
        this.imagePathList = imagePathList;
    }
}