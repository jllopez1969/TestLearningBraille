package com.diana.testlearningbraille;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private static final String INBOX_STYLE = "INBOX_STYLE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton Iniciar_test= findViewById(R.id.Iniciar_Test);
        ImageButton  Lamina_Vocales = findViewById(R.id.imageButton1);
        ImageButton  Lamina_AE = findViewById(R.id.imageButton2);
        ImageButton  Lamina_FJ = findViewById(R.id.imageButton3);
        ImageButton  Lamina_KNN = findViewById(R.id.imageButton4);
        ImageButton  Lamina_OR = findViewById(R.id.imageButton5);
        ImageButton  Lamina_SV = findViewById(R.id.imageButton6);
        ImageButton  Lamina_WZ = findViewById(R.id.imageButton7);
        ImageButton  Lamina_04 = findViewById(R.id.imageButton8);
        ImageButton  Lamina_59 = findViewById(R.id.imageButton9);



        Intent intent1 = new Intent(this, Lamina_Vocales.class);
        Intent intent2 = new Intent(this, Lamina_AE.class);
        Intent intent3 = new Intent(this, Lamina_FJ.class);
        Intent intent4 = new Intent(this, Lamina_KNN.class);
        Intent intent5 = new Intent(this, Lamina_OR.class);
        Intent intent6 = new Intent(this, Lamina_SV.class);
        Intent intent7 = new Intent(this, Lamina_WZ.class);
        Intent intent8 = new Intent(this, Lamina_04.class);
        Intent intent9 = new Intent(this, Lamina_59.class);

        // Enables Always-on
        //setAmbientEnabled();

        Lamina_Vocales.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent1);


                    }});

        Lamina_AE.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent2);


                    }});
        Lamina_FJ.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent3);


                    }});
        Lamina_KNN.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent4);


                    }});

        Lamina_OR.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent5);


                    }});

        Lamina_SV.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent6);


                    }});
        Lamina_WZ.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent7);


                    }});
        Lamina_04.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent8);


                    }});

        Lamina_59.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        startActivity(intent9);


                    }});





    }







    }