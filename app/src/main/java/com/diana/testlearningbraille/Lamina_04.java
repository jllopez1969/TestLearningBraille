package com.diana.testlearningbraille;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Lamina_04 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamina04);
        ImageButton Iniciar_test= findViewById(R.id.Iniciar_Test);

        ImageButton button1 = findViewById(R.id.imageButton1);
        ImageButton button2 = findViewById(R.id.imageButton2);
        ImageButton button3 = findViewById(R.id.imageButton3);
        ImageButton button4 = findViewById(R.id.imageButton4);
        ImageButton button5  = findViewById(R.id.imageButton5);
        //  ImageButton button6 =  findViewById(R.id.imageButton6);
        // ImageButton button7 =  findViewById(R.id.imageButton7);
        //  ImageButton button8 =  findViewById(R.id.imageButton8);
        //ImageButton button9 =  findViewById(R.id.imageButton9);
        // ImageButton button10  = findViewById(R.id.imageButton10);
        Vibrator vibrator;

        // Obtiene instancia a Vibrator
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        // Habilitación de  escritura de ficheros texto

        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para leer.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso para leer y escribir!");
        }

        Iniciar_test.setOnClickListener(
                new View.OnClickListener() {

                    public void onClick(View view) {

                        // Crear fichero *.txt
                        try {
                            File raiz = Environment.getExternalStorageDirectory();
                            if (raiz.canWrite()){
                                File file = new File(raiz, "fichero.txt");
                                BufferedWriter out = new BufferedWriter(new FileWriter(file));
                                out.write("Inicio Test braille + ");
                                out.close();
                            }
                        } catch (IOException e) {
                            Log.e("FILE I/O", "Error en la escritura de fichero: " + e.getMessage());
                        }


                    }});


        //Compruebe si dispositivo tiene un vibrador.
        //if (vibrator.hasVibrator()) {//Si tiene vibrador

        //vibra N milisegundos
        //SIGNO ""A""
        button1.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        // Onomatopeya Pantera rosa

                        long[] pattern = {
                                //sleep//vibrate/Largo
                                0,200,
                                30,150,
                                200,10,
                                50,200,
                                50,500,

                        };


                        NotificationCompat.Builder mBuilder;
                        NotificationManager mNotifyMgr =(NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

                        int icono = R.mipmap.png1;
                        Intent i=new Intent(Lamina_04.this, Message.class);
                        PendingIntent pendingIntent;
                        pendingIntent = PendingIntent.getActivity(Lamina_04.this, 0, i,                                    0);                long[] pattern1 = {1500, 800, 800, 800};


                        mBuilder = new NotificationCompat.Builder(getApplicationContext());
                        mBuilder.setContentIntent(pendingIntent);
                        mBuilder.setSmallIcon(icono,2);
                        mBuilder.setContentTitle("Patrón");
                        mBuilder.setContentText("A");
                        mBuilder.setVibrate(pattern);
                        mBuilder.setAutoCancel(true);


                        mNotifyMgr.notify(1, mBuilder.build());


                        //long[] pattern = {
                        //        100,1000, //sleep//vibrate/largo
                        //       100,50//Final letra
                        // };
                        // long tiempo = 500; //en milisegundos
                        // vibrator.vibrate(pattern, -1);



                    }});
        //vibra segun un patron dado
        //SIGNO ""E""
        button2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        //      long[] pattern = {
                        //              100,1000,//Largo
                        //              100,300,//Corto
                        //              100,300,//Corto
                        //              100,1000,//Largo
                        //              100,50 //Final letra
                        //      };
                        // con -1 se indica desactivar repeticion del patron
                        //      vibrator.vibrate(pattern, -1);

                        NotificationCompat.Builder mBuilder;
                        NotificationManager mNotifyMgr =(NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

                        int icono = R.mipmap.png5;
                        Intent i=new Intent(Lamina_04.this, Message.class);
                        PendingIntent pendingIntent;
                        pendingIntent = PendingIntent.getActivity(Lamina_04.this, 0, i, 0);                long[] pattern1 = {1500, 800, 800, 800};


                        mBuilder = new NotificationCompat.Builder(getApplicationContext());
                        mBuilder.setContentIntent(pendingIntent);
                        mBuilder.setSmallIcon(icono,2);
                        mBuilder.setContentTitle("Patrón");
                        mBuilder.setContentText("E");
                        mBuilder.setVibrate(new long[]{100, 250, 100, 500});
                        mBuilder.setAutoCancel(true);


                        mNotifyMgr.notify(1, mBuilder.build());


                    }});

        //vibra segun un patron dado indefinidamente
        //SIGNO ""I""
        button3.setOnClickListener(

                new View.OnClickListener() {
                    public void onClick(View view) {
                        //long[] pattern = {
                        //        100,300, //sleep//vibrate// Corto
                        //        100,1000,//Largo
                        //        100,1000,//Largo
                        //        100,50 // Final letra
                        // };
                        // con -1 se indica desactivar repeticion del patron
                        //vibrator.vibrate(pattern, -1);

                        NotificationCompat.Builder mBuilder;
                        NotificationManager mNotifyMgr =(NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

                        int icono = R.mipmap.png1;
                        Intent i=new Intent(Lamina_04.this, Message.class);
                        PendingIntent pendingIntent;
                        pendingIntent = PendingIntent.getActivity(Lamina_04.this, 0, i, 0);                long[] pattern1 = {1500, 800, 800, 800};


                        mBuilder = new NotificationCompat.Builder(getApplicationContext());
                        mBuilder.setContentIntent(pendingIntent);
                        mBuilder.setSmallIcon(icono,2);
                        mBuilder.setContentTitle("Patrón");
                        mBuilder.setContentText("I");
                        mBuilder.setVibrate(new long[]{100, 250, 100, 500});
                        mBuilder.setAutoCancel(true);


                        mNotifyMgr.notify(1, mBuilder.build());

                    }});

        // new View.OnClickListener() {
        //   public void onClick(View view) {

        //if(!button3.getText().equals("Detener")){
        //   button3.setText("Detener");
        //   long[] pattern = {400, 600, 100,300,100,150,100,75};
        //   vibrator.vibrate(pattern, 0);
        //}else{
        //   button3.setText("Vibrar indefinidamente");
        //   vibrator.cancel();//cancela vibración
        //}
        //}});
        button4.setOnClickListener(
                //   new View.OnClickListener() {
                //     public void onClick(View view) {
                //SIGNO ""O""
                new View.OnClickListener() {
                    public void onClick(View view) {
                        //long[] pattern = {
                        //        0,1000, //sleep//vibrate
                        //        100,300,
                        //        100,300,
                        //        100,1000,
                        //        100,1000,
                        //        100,50
                        //};
                        //vibrator.vibrate(pattern, -1);

                        NotificationCompat.Builder mBuilder;
                        NotificationManager mNotifyMgr =(NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

                        int icono = R.mipmap.png1;
                        Intent i=new Intent(Lamina_04.this, Message.class);
                        PendingIntent pendingIntent;
                        pendingIntent = PendingIntent.getActivity(Lamina_04.this, 0, i, 0);                long[] pattern1 = {1500, 800, 800, 800};


                        mBuilder = new NotificationCompat.Builder(getApplicationContext());
                        mBuilder.setContentIntent(pendingIntent);
                        mBuilder.setSmallIcon(icono,2);
                        mBuilder.setContentTitle("Patrón");
                        mBuilder.setContentText("O");
                        mBuilder.setVibrate(new long[]{100, 250, 100, 500});
                        mBuilder.setAutoCancel(true);


                        mNotifyMgr.notify(1, mBuilder.build());


                    }});
        button5.setOnClickListener(
                //   new View.OnClickListener() {
                //     public void onClick(View view) {
                //SIGNO ""U""
                new View.OnClickListener() {
                    public void onClick(View view) {
                        long[] pattern = {
                                0,50,
                                50,50,
                                50,50,
                                100,500,
                                20,50
                                //largo
                                // final letra.
                        };
                        //vibrator.vibrate(pattern, -1);
                        NotificationCompat.Builder mBuilder;
                        NotificationManager mNotifyMgr =(NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

                        int icono = R.mipmap.png1;
                        Intent i=new Intent(Lamina_04.this, Message.class);
                        PendingIntent pendingIntent;
                        pendingIntent = PendingIntent.getActivity(Lamina_04.this, 0, i, 0);                long[] pattern1 = {1500, 800, 800, 800};


                        mBuilder = new NotificationCompat.Builder(getApplicationContext());
                        mBuilder.setContentIntent(pendingIntent);
                        mBuilder.setSmallIcon(icono,2);
                        mBuilder.setContentTitle("Patrón");
                        mBuilder.setContentText("U");
                        mBuilder.setVibrate(pattern);
                        mBuilder.setAutoCancel(true);


                        mNotifyMgr.notify(1, mBuilder.build());

                    }});





    }
}