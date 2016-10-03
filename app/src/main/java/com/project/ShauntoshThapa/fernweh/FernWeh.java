package com.project.ShauntoshThapa.fernweh;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Owner on 01/09/2016.
 */
public class FernWeh extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
