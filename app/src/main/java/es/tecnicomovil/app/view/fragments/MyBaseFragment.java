package es.tecnicomovil.app.view.fragments;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import es.tecnicomovil.app.utils.Preferences;
import es.tecnicomovil.app.utils.Utils;

import static es.tecnicomovil.app.utils.AppConstants.CURRENCY_SYMBOL_KEY;
import static es.tecnicomovil.app.utils.AppConstants.appContext;

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
