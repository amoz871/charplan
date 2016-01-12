package com.daoc.charplan;

import android.app.Application;

/**
 * App class that starts initialization.
 */
public class CharplanApp extends Application {

    /**
     * Application tag used for logging.
     */
    public static final String LOG_TAG = "Charplan";

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate() {
        super.onCreate();
        com.daoc.charplan.util.Log.d("Charplan application is created");;
    }
}