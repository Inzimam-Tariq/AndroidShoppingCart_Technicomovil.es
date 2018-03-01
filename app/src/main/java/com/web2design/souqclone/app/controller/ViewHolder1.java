package com.web2design.souqclone.app.controller;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.web2design.souqclone.app.R;


/**
 * Created by Inzimam on 22-Nov-17.
 */

public class ViewHolder1 extends RecyclerView.ViewHolder {
    
    private TextView title;
    private RecyclerView mRecyclerView;
    
    ViewHolder1(View v) {
        super(v);
        title = v.findViewById(R.id.main_cat_title);
        mRecyclerView = v.findViewById(R.id.main_cat_recycler_view);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }
    
    public TextView getTitle() {
        return title;
    }
    
    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
    
}
