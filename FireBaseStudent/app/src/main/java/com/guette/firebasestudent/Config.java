package com.guette.firebasestudent;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by MAGUETTE on 28/07/2016.
 */
public class Config extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
