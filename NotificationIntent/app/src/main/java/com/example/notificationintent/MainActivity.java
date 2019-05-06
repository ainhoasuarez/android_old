package com.example.notificationintent;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button, button2;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.btn_mostrar);
        button2 = findViewById(R.id.btn_cancelar);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }
    public void crearNotificacion() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iceditorial.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "ID")
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentTitle("Alerta")
                .setContentText("Debes visitar esta página sin falta")
                .setSubText("Recomendada");

        notificationManager.notify(1, builder.build());
    }
    public void eliminarNotificacion() {
        notificationManager.cancel(1);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mostrar:
                crearNotificacion();
                break;
            case R.id.btn_cancelar:
                eliminarNotificacion();
                break;
            default:
                return;
        }
    }
}

