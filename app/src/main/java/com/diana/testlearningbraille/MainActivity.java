package com.diana.testlearningbraille;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    //public static final String TAG = "MainActivity";
    //private static final String INBOX_STYLE = "INBOX_STYLE";
    //Context context;
    //Resources resources;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);


        // Idioma

        String l8 = Locale.getDefault().toString(); // es_ES



        TextView titulo = findViewById(R.id.textView);
        TextView V_acent= findViewById(R.id.textView1);
        TextView text_AE = findViewById(R.id.textView2);
        TextView text_FJ= findViewById(R.id.textView3);
        TextView text_KO = findViewById(R.id.textView4);
        TextView text_PT = findViewById(R.id.textView5);
        TextView text_UZ = findViewById(R.id.textView6);
        TextView text_SP = findViewById(R.id.textView7);
        TextView text_04 = findViewById(R.id.textView8);
        TextView text_59 = findViewById(R.id.textView9);

     /*   if ( l8.equals("es_ES"))
        {
          //  titulo.setText("SELECCIONAR LÁMINA DE SÍMBOLOS");
            V_acent.setText("Lámina de Vocales acentuadas");
            text_AE.setText("Lámina de símbolos de A a E");
            text_FJ.setText("Lámina de símbolos de F a J");
            text_KO.setText("Lámina de símbolos de K a O");
            text_PT.setText("Lámina de símbolos de P a T");
            text_SP.setText("Lámina de símbolos Ñ,W,ü, , , .");
            text_UZ.setText("Lámina de símbolos U,V,X,Y,Z");
            text_04.setText("Lámina de símbolos de 0 a 4");
            text_59.setText("Lámina de símbolos de 5 a 9");
        }*/

        // Referenciando ImageButton




        ImageButton Iniciar_test= findViewById(R.id.Iniciar_Test);
        ImageButton  Sheet_stressed_vowel = findViewById(R.id.imageButton1);
        ImageButton  Sheet_AE = findViewById(R.id.imageButton2);
        ImageButton  Sheet_FJ = findViewById(R.id.imageButton3);
        ImageButton  Sheet_KO = findViewById(R.id.imageButton4);
        ImageButton  Sheet_PT = findViewById(R.id.imageButton5);
        ImageButton  Sheet_UZ = findViewById(R.id.imageButton6);
        ImageButton Sheet_punctuation_symbols= findViewById(R.id.imageButton7);
        ImageButton  Sheet_04 = findViewById(R.id.imageButton8);
        ImageButton  Sheet_59 = findViewById(R.id.imageButton9);



        Intent intent1 = new Intent(this, Sheet_stressed_vowel.class);
        Intent intent2 = new Intent(this, Sheet_AE.class);
        Intent intent3 = new Intent(this, Sheet_FJ.class);
        Intent intent4 = new Intent(this, Sheet_KO.class);
        Intent intent5 = new Intent(this, Sheet_PT.class);
        Intent intent6 = new Intent(this, Sheet_UZ.class);
        Intent intent7 = new Intent(this, Sheet_punctuation_symbols.class);
        Intent intent8 = new Intent(this, Sheet_04.class);
        Intent intent9 = new Intent(this, Sheet_59.class);

        // Enables Always-on
        //setAmbientEnabled();




        Sheet_stressed_vowel.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent1);


                    }});

        Sheet_AE.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent2);


                    }});
        Sheet_FJ.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent3);


                    }});
        Sheet_KO.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent4);


                    }});

        Sheet_PT.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent5);


                    }});

        Sheet_UZ.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent6);


                    }});

        Sheet_punctuation_symbols.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent7);


                    }});
        Sheet_04.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent8);


                    }});

        Sheet_59.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent9);


                    }});

        MediaPlayer mp;
        mp = MediaPlayer.create(this, R.raw.seleccion);
        mp.start();



    }



    }