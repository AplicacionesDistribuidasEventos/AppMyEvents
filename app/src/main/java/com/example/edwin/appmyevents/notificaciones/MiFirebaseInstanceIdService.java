package com.example.edwin.appmyevents.notificaciones;


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MiFirebaseInstanceIdService extends FirebaseInstanceIdService{

    public static final String TAG = "NOTIFICACIONES";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Token: " + token);
    }
}
