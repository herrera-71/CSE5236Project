package com.project.cse5236.habitofgravity;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.project.cse5236.habitofgravity.BitmapSingletons.blockBitmap;

/**
 * Created by Brent on 10/29/2017.
 */

public class ContextHolder {

    private static volatile ContextHolder instance;

    private ContextHolder() {
    }

    public static ContextHolder getInstance()
    {
        if (instance == null) {
            synchronized(ContextHolder.class) {
                if (instance == null) {
                    instance = new ContextHolder();
                }
            }
        }
        return instance;
    }

    private Context context;

    public void SetContext(Context cont)
    {
        context = cont;
    }

    public Context GetContext()
    {
        return context;
    }
}
