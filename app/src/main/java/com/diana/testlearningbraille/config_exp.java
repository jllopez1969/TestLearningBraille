package com.diana.testlearningbraille;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class config_exp extends AppCompatActivity {

    public void onBackPressed() {
        super.onBackPressed();

        finish();

    }
    int selected_test = 0;
    int Status_test = 0;
    int test_line =0;
    String file_out;
    String Selected_test;

    private TextView textView_file1;
    private TextView textView_file2;

    String path_braille = "Braille";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_config_exp);




         // Object display

        Intent intent1 = new Intent(this, Sheet_AE.class);
        Intent intent2= new Intent(this, Sheet_FJ.class);
        Intent intent3 = new Intent(this, Sheet_KO.class);
        Intent intent4 = new Intent(this, Sheet_PT.class);
        Intent intent5 = new Intent(this, Sheet_UZ.class);
        Intent intent6 = new Intent(this, Sheet_stressed_vowel.class);
        Intent intent7 = new Intent(this, Sheet_punctuation_symbols.class);
     //   Intent intent8 = new Intent(this, Sheet_04.class);
      //  Intent intent9 = new Intent(this, Sheet_59.class);

        textView_file1 = findViewById(R.id.textView14);
        textView_file2= findViewById(R.id.textView15);
        EditText edit_file = findViewById(R.id.editTextTextPersonName);


        View Create_file = findViewById(R.id.imageView);
        Create_file.setVisibility(View.VISIBLE);

        // Entrada de datos externos de la pantalla de configuración

        Bundle datos = this.getIntent().getExtras();
        int sheet_selection = datos.getInt("sheet_select");

        ImageButton backscreen = findViewById(R.id.backscreen);
        backscreen.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        //     finishActivity;
                        onBackPressed();
                    }});


        // Type test variables creation

        RadioButton pin_Onom = findViewById(R.id.Test_V_Onom);
        RadioButton pin_Control= findViewById(R.id.Test_Control);
        RadioButton pin_Number = findViewById(R.id.Test_V_Number);

        //Init selected_test
        pin_Control.setEnabled(true);
        pin_Onom.setEnabled(true);
        pin_Number.setEnabled(true);

        pin_Onom.setChecked(true);
        selected_test = 1;


        pin_Control.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        selected_test= 0;
                        Selected_test = "_C_";


                    }});
        pin_Onom.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        selected_test = 1;
                        Selected_test = "_O_";

                    }});
        pin_Number.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        selected_test = 2;
                        Selected_test = "_N_";
                    }});




        Create_file.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                       // Crear fichero de datos de experimento

                  /*     Create_file.setVisibility(View.INVISIBLE);
                        edit_file.setVisibility(View.INVISIBLE);
                        textView_file2.setText(edit_file.getText());
                        textView_file2.setVisibility(View.VISIBLE);
                        textView_file1.setVisibility(View.VISIBLE); */



                        Status_test = 0;
                        test_line=0;
                        file_out = edit_file.getText().toString() +Selected_test ; // Add test name


                        // Create file *.csv

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                        String currentDateandTime = simpleDateFormat.format(new Date());
                        file_out = file_out +  currentDateandTime + ".csv"; // Add current time to file name

                        WriteFileTest File = new WriteFileTest();
                        File.Create_path_and_file(config_exp.this, path_braille,file_out);




                        switch (sheet_selection)
                      {
                          case 1:

                              intent1.putExtra("selected_test", selected_test);
                              intent1.putExtra("file_out",file_out);
                              startActivity(intent1);
                              break;
                          case 2:
                              intent2.putExtra("selected_test", selected_test);
                              intent2.putExtra("file_out",file_out);
                              startActivity(intent2);
                              break;
                          case 3:
                              intent3.putExtra("selected_test", selected_test);
                              intent3.putExtra("file_out",file_out);
                              startActivity(intent3);
                              break;
                          case 4:
                              intent4.putExtra("selected_test", selected_test);
                              intent4.putExtra("file_out",file_out);
                              startActivity(intent4);
                              break;
                          case 5:
                              intent5.putExtra("selected_test", selected_test);
                              intent5.putExtra("file_out",file_out);
                              startActivity(intent5);
                              break;

                          case 6:
                              intent6.putExtra("selected_test", selected_test);
                              intent6.putExtra("file_out",file_out);
                              startActivity(intent6);
                              break;
                          case 7:
                              intent7.putExtra("selected_test", selected_test);
                              intent7.putExtra("file_out",file_out);
                              startActivity(intent7);
                              break;
                       /*  case 8:
                              intent8.putExtra("selected_test", selected_test);
                              startActivity(intent8);
                              break;
                          case 9:
                              intent9.putExtra("selected_test", selected_test);
                              startActivity(intent9);
                              break; */
                      }


                    }});



    }
}