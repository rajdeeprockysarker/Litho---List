package com.example.rajdeeps.litho;

import android.app.Application;

import com.facebook.soloader.SoLoader;

/**
 * Created by rajdeeps on 21-04-2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        SoLoader.init(this, false);
    }
}
