package com.web2design.souqclone.app.view.fragments;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.web2design.souqclone.app.utils.Preferences;
import com.web2design.souqclone.app.utils.Utils;

import static com.web2design.souqclone.app.utils.AppConstants.CURRENCY_SYMBOL_KEY;
import static com.web2design.souqclone.app.utils.AppConstants.appContext;

/**
 * Created by Inzimam on 11-Nov-17.
 */

public class MyBaseFragment extends Fragment {
    
    public Context mContext;
    public Utils utils;
    public RecyclerView mRecyclerView;
    public String symbol;
    public Handler handler;
    
    public MyBaseFragment() {
    
    }
    
    protected void initUtils() {
        this.mContext = getActivity();
        this.utils = new Utils(mContext);
        symbol = Preferences.getSharedPreferenceString(appContext
                , CURRENCY_SYMBOL_KEY, "$");
    }
    
}
