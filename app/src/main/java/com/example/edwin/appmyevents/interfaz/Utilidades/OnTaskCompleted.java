package com.example.edwin.appmyevents.interfaz.Utilidades;

import android.view.View;

/**
 * Interface para listener de ClienteRest para captura de notificaciones de ClienteRest
 * @autor UPS on 24/05/2015.
 */
public interface OnTaskCompleted {
    void onTaskCompleted(int idSolicitud);

    void onClick(View view);
}
