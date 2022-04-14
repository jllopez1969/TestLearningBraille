package com.diana.testlearningbraille;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Lamina_KNN extends AppCompatActivity {


    /*********************************  ********************************************************

     Estados dentro del ciclo de ejecución

     Secuencia
     de estados : 21-20- ( creación fichero log *.txt) -
                         - 0 - 1- 2 - 3 - 4- 5 - 6- 7 - 8- 9 - 0 ------
                          (Pares = Periodo de Entrenamiento)
                          (Impares = Periodo de Test)

     *********************************  **********************************************************/



    //*******************************
    // Control tiempo de doble click
    //*********************************************

    long mLastTime=System.currentTimeMillis();
    long mCurTime=System.currentTimeMillis();
    boolean flag_db = false;


    //************************************
    // Control de escritura línea en fichero texto
    //********************************************
    String file_out ="";

    // Control de test
    boolean flag_sel = false;
    boolean flag_ok= false;
    boolean flag_elec= false;
    char  Simbol= 'A';
    char  Simbol_elec;
    int  Success = 0;
    int  Error =0;
    int  test_line = 0;


    //*************************************************
    //  Funciones de encapsulación de código
    //****************************************************



    // 1ª Acciones al pulsar doble click sobre linea de entrenamiento

    public void Double_Click(char character){

        // Toma de tiempo actual
        mCurTime= System.currentTimeMillis();

        if (flag_db & (mCurTime - mLastTime < 300)) {// Evento de doble clic
            mCurTime = System.currentTimeMillis();
            mLastTime = System.currentTimeMillis();
            flag_db = false;

            // Realizando test en una línea activa
            if ((Status_test == 1) | (Status_test == 3) | (Status_test == 5) | (Status_test == 7) | (Status_test == 9))
            {
                // Seleccionado símbolo en línea de test activa
                if (flag_sel & flag_elec) {
                    flag_sel= false;
                    flag_elec = false;

                    // Determinar éxito o no éxito
                    if (Simbol == character) {
                        Success++;
                        flag_ok = true;
                        Sound(20);

                    } else {
                        Error++;
                        flag_ok = false;
                        Sound(21);
                    }
                    //Casos posibles de test
                    WriteFileTest File = new WriteFileTest();
                    switch (Success + Error) {

                        case 1:
                            if (flag_ok) {

                                File.Write_Text_File( file_out, "(" + Simbol + ")::Success;");
                            }else{
                                File.Write_Text_File( file_out, "(" + Simbol + "-"+ Simbol_elec +")::Error;");
                            }

                            break;
                        case 2:
                            if (flag_ok) {

                                File.Write_Text_File(file_out, "(" + Simbol + ")::Success;");
                            }else{
                                File.Write_Text_File(file_out, "(" + Simbol + "-"+ Simbol_elec +")::Error;");
                            }
                            break;
                        case 3:
                            if (flag_ok) {

                                File.Write_Text_File(file_out, "(" + Simbol + ")::Success;");
                            }else{
                                File.Write_Text_File(file_out, "(" + Simbol + "-"+ Simbol_elec +")::Error;");
                            }


                            break;

                        case 4:
                            if (flag_ok){

                                File.Write_Text_File(file_out, "(" + Simbol + ")::Success;");
                            }else{
                                File.Write_Text_File( file_out, "(" + Simbol + "-"+ Simbol_elec +")::Error;");
                            }


                            break;
                        case 5:
                            if (flag_ok & (Success == 5))  {

                                File.Write_Text_File(file_out, "(" + Simbol + ")::Success;\n - End line::" + test_line + "\n\n** End Test **");
                                secondLeft = 0;
                                On = false;
                                Activ_test = false;
                                Sound(23);

                            }else{
                                if (!flag_ok){
                                    File.Write_Text_File( file_out, "(" + Simbol + "-"+ Simbol_elec +")::Error;\n - End line::" + test_line + "\n");
                                }
                                else{
                                    File.Write_Text_File( file_out, "(" + Simbol+ ")::Success;\n - End line::" + test_line + "\n");

                                }
                                secondLeft = 0;
                                // On=false;

                            }


                            break;

                    }
                    flag_sel= false;
                }

            }

        }

        // Rearme de doble click
        flag_db = true;
        mLastTime= System.currentTimeMillis();
    }

    // Función de locuciones

    public void Sound( int code) {

        MediaPlayer mp;

        switch (code) {
            case 1:
                mp = MediaPlayer.create(this, R.raw.k_sonido_koala);
                mp.start();
                break;

            case 2:
                mp = MediaPlayer.create(this, R.raw.l_levando_anclas);
                mp.start();
                break;

            case 3:
                mp = MediaPlayer.create(this, R.raw.m_sonido_marmota);
                mp.start();
                break;

            case 4:
                mp = MediaPlayer.create(this, R.raw.n_new_yook_new_york);
                mp.start();
                break;

            case 5:
                mp = MediaPlayer.create(this, R.raw.nn_sonido_morse);
                mp.start();
                break;

            case 11:
                mp = MediaPlayer.create(this, R.raw.alphabet_k);
                mp.start();
                break;

            case 12:
                mp = MediaPlayer.create(this, R.raw.alphabet_l);
                mp.start();
                break;

            case 13:
                mp = MediaPlayer.create(this, R.raw.alphabet_m);
                mp.start();
                break;

            case 14:
                mp = MediaPlayer.create(this, R.raw.alphabet_n);
                mp.start();
                break;

            case 15:
                mp = MediaPlayer.create(this, R.raw.alphabet_nn);
                mp.start();
                break;

            case 20:
                mp = MediaPlayer.create(this, R.raw.loc_acierto);
                mp.start();
                break;

            case 21:
                mp = MediaPlayer.create(this, R.raw.loc_error);
                mp.start();
                break;

            case 22:
                mp = MediaPlayer.create(this, R.raw.loc_final_test);
                mp.start();
                break;
            case 23:
                mp = MediaPlayer.create(this, R.raw.lamina_k_nn);
                mp.start();
                break;
            case 24:
                mp = MediaPlayer.create(this, R.raw.primer_entrenamiento);
                mp.start();
                break;

            case 25:
                mp = MediaPlayer.create(this, R.raw.primera_linea_test);
                mp.start();
                break;
            case 26:
                mp = MediaPlayer.create(this, R.raw.segundo_entrenamiento);
                mp.start();
                break;
            case 27:
                mp = MediaPlayer.create(this, R.raw.segunda_linea_test);
                mp.start();
                break;
            case 28:
                mp = MediaPlayer.create(this, R.raw.tercer_entrenamiento);
                mp.start();
                break;
            case 29:
                mp = MediaPlayer.create(this, R.raw.tercera_linea_test);
                mp.start();
                break;
            case 30:
                mp = MediaPlayer.create(this, R.raw.cuarto_entrenamiento);
                mp.start();
                break;
            case 31:
                mp = MediaPlayer.create(this, R.raw.cuarta_linea_test);
                mp.start();
                break;
            case 32:
                mp = MediaPlayer.create(this, R.raw.quinto_entrenamiento);
                mp.start();
                break;
            case 33:
                mp = MediaPlayer.create(this, R.raw.quinta_linea_test);
                mp.start();
                break;

            case 34:
                mp = MediaPlayer.create(this, R.raw.idprueba);
                mp.start();
                break;


        }


    }


    //      2ª Notificación de Patrones


    public void Notification( String Text1, String Text2, int icono, long[] pattern)
    {
        NotificationCompat.Builder mBuilder;
        NotificationManager mNotifyMgr = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        //  int icono = R.mipmap.png1;
        Intent i = new Intent(Lamina_KNN.this, Message.class);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getActivity(Lamina_KNN.this, 0, i, 0);
        mBuilder = new NotificationCompat.Builder(getApplicationContext());
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(icono, 2);
        mBuilder.setContentTitle("Patrón: "+ Text1);
        mBuilder.setContentText(Text2);
        mBuilder.setVibrate(pattern);
        mBuilder.setAutoCancel(true);
        mNotifyMgr.notify(1, mBuilder.build());

    }

//*************************************************
    //  TEMPORIZADOR
//******************************************************************

    // Control temporización
    boolean On = false;
    int secondLeft = 0;
    boolean Activ_test = false;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;

    private TextView textView_file1;
    private TextView textView_file2;


    // Variable de estado y tiempo de test
    int Status_test = 21;
    int Time_slot = 120;
  //  boolean timerOn = false;


    // Instanciación de objeto Timer
    Timer  timer = new Timer();

    // Sobrecarga del objeto TimerTask

    TimerTask task = new TimerTask() {

        @Override
        public void run() {

            // TODO Auto-generated method stub
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    // TODO Auto-generated method stub

                    if (On & Activ_test) {
                        if(secondLeft>0) {
                            secondLeft--;
                        }
                        textView.setText("" + secondLeft);
                        if ((secondLeft < 1) & On) {
                            textView2.setText("Fin del test");
                            if (Success < 5 & (Status_test == 1|Status_test == 3|Status_test == 5|Status_test == 7|Status_test == 9))
                            {
                                WriteFileTest File = new WriteFileTest();
                                File.Write_Text_File(file_out, "- End line ( not completed) ::" + test_line +"\n");

                            }

                            secondLeft = Time_slot;
                            On = false;

                            //Cambio de estado Test

                            switch (Status_test) {
                                case 0:
                                    Status_test = 1;
                                    //textView3.setText(Status_test);
                                    break;
                                case 1:
                                    Status_test = 2;
                                    //textView3.setText(Status_test);
                                    break;
                                case 2:
                                    Status_test = 3;
                                    //textView3.setText(Status_test);
                                    break;
                                case 3:
                                    Status_test = 4;
                                    //textView3.setText(Status_test);
                                    break;
                                case 4:
                                    Status_test = 5;
                                    //textView3.setText(Status_test);
                                    break;
                                case 5:
                                    Status_test = 6;
                                    // textView3.setText(Status_test);
                                    break;
                                case 6:
                                    Status_test = 7;
                                    //textView3.setText(Status_test);
                                    break;
                                case 7:
                                    Status_test = 8;
                                    //textView3.setText(Status_test);
                                    break;
                                case 8:
                                    Status_test = 9;
                                    //textView3.setText(Status_test);
                                    break;
                                case 9:
                                    Status_test = 0;
                                    //textView3.setText(Status_test);
                                    break;

                            }

                        }
                    }
                }
            });
        }
    };

//************************************************************
//                  Función principal: Control pantalla
//**********************************************************************

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lamina_knn);

        // Control test


        // Display cronómetro
        textView = findViewById(R.id.textView10);
        textView1 = findViewById(R.id.textView11);
        textView2 = findViewById(R.id.textView12);
        textView3 = findViewById(R.id.textView13);

        textView_file1 = findViewById(R.id.textView14);
        textView_file2= findViewById(R.id.textView15);
        EditText edit_file = findViewById(R.id.editTextTextPersonName);



        textView_file1.setVisibility(View.INVISIBLE);
        textView_file2.setVisibility(View.INVISIBLE);
        edit_file.setVisibility(View.INVISIBLE);




// Declarición de variables gráficas

        ImageButton button1 = findViewById(R.id.imageButton1);
        ImageButton button2 = findViewById(R.id.imageButton2);
        ImageButton button3 = findViewById(R.id.imageButton3);
        ImageButton button4 = findViewById(R.id.imageButton4);
        ImageButton button5 = findViewById(R.id.imageButton5);
        ImageButton button6 = findViewById(R.id.imageButton6);
        ImageButton button7 = findViewById(R.id.imageButton7);
        ImageButton button8 = findViewById(R.id.imageButton8);
        ImageButton button9 = findViewById(R.id.imageButton9);
        ImageButton button10 = findViewById(R.id.imageButton10);
        ImageButton button11 = findViewById(R.id.imageButton11);
        ImageButton button12 = findViewById(R.id.imageButton12);
        ImageButton button13 = findViewById(R.id.imageButton13);
        ImageButton button14 = findViewById(R.id.imageButton14);
        ImageButton button15 = findViewById(R.id.imageButton15);
        ImageButton button16 = findViewById(R.id.imageButton16);
        ImageButton button17 = findViewById(R.id.imageButton17);
        ImageButton button18 = findViewById(R.id.imageButton18);
        ImageButton button19 = findViewById(R.id.imageButton19);
        ImageButton button20 = findViewById(R.id.imageButton20);
        ImageButton button21 = findViewById(R.id.imageButton21);
        ImageButton button22 = findViewById(R.id.imageButton22);
        ImageButton button23 = findViewById(R.id.imageButton23);
        ImageButton button24 = findViewById(R.id.imageButton24);
        ImageButton button25 = findViewById(R.id.imageButton25);
        ImageButton button26 = findViewById(R.id.imageButton26);
        ImageButton button27 = findViewById(R.id.imageButton27);
        ImageButton button28 = findViewById(R.id.imageButton28);
        ImageButton button29 = findViewById(R.id.imageButton29);
        ImageButton button30 = findViewById(R.id.imageButton30);


        ImageButton button61 = findViewById(R.id.imageButton61);
        ImageButton button62 = findViewById(R.id.imageButton62);
        ImageButton button63 = findViewById(R.id.imageButton63);
        ImageButton button64 = findViewById(R.id.imageButton64);
        ImageButton button65 = findViewById(R.id.imageButton65);

        // Botón de creación fichero log. *.txt
        View Create_file = findViewById(R.id.imageView);
        Create_file.setVisibility(View.INVISIBLE);

        Switch pin = findViewById(R.id.switch1);

        // Selección de símbolo



        // Declariación de  Variable gráfica de Inicio Test

        ImageButton Iniciar_test = findViewById(R.id.Iniciar_Test);

        //Declaración de variable gráfica de Interrupcióon de Test
        ImageButton Iniciar_test_off= findViewById(R.id.Iniciar_Test_off);


        // Habilitación de la opción de vibración

        Vibrator vibrator;
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


        // Habilitar Permiso de escritura en fichero

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para leer.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso para leer y escribir!");
        }


        // PUESTA EN MARCHA TEMPORIZADOR - Sólo una vez en toda la ejecución

        timer.schedule(task, 1000, 1000);
        Sound(23);

        // Inicio de test - Comienzo
        // Y, Transiciones de estado de test

        Create_file.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if (Status_test == 20) {
                            Create_file.setVisibility(View.INVISIBLE);
                            edit_file.setVisibility(View.INVISIBLE);
                            file_out = edit_file.getText().toString();
                            textView_file2.setText(edit_file.getText());
                            textView_file2.setVisibility(View.VISIBLE);
                            textView_file1.setVisibility(View.VISIBLE);
                            Status_test = 0;

                            // Texto incial del fichero


                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            String currentDateandTime = simpleDateFormat.format(new Date());
                            String Cabecera;
                            Cabecera = "** Start TEST **\n\n" +
                                    " - Id :: " + file_out + ";\n"+
                                    " - Date :: " + currentDateandTime + ";\n\n";

                            // Crear fichero log *.txt

                            WriteFileTest File = new WriteFileTest();
                            File.Create_file_text(file_out,Cabecera);

                        }

                    }});

        Iniciar_test_off.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        //Interrupción test - Botón emergencia
                        Iniciar_test.setVisibility(View.VISIBLE);
                        Iniciar_test.setEnabled(true);
                        Iniciar_test_off.setVisibility((View.INVISIBLE));
                        Iniciar_test_off.setEnabled(false);
                        secondLeft=0;


                    }});

        Iniciar_test.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {


                            switch (Status_test) {

                                case 21:
                                    textView_file1.setVisibility(View.VISIBLE);
                                    edit_file.setVisibility(View.VISIBLE);
                                    Create_file.setVisibility(View.VISIBLE);
                                    Status_test = 20;
                                    secondLeft = 0;

                                    Sound(34);
                                    break;

                                case 0:
                                    On = true;
                                    Activ_test = true;
                                    textView2.setText("Entrenamiento nº1");
                                    secondLeft = Time_slot;
                                    Iniciar_test.setEnabled(false);
                                    Iniciar_test_off.setVisibility(View.VISIBLE);
                                    Iniciar_test_off.setEnabled(true);
                                    Sound(24);
                                    break;
                                case 1:
                                    On = true;
                                    textView2.setText("Test Línea 1ª");
                                    Success = 0;
                                    Error = 0;
                                    flag_sel = false;
                                    if (Activ_test) {
                                        WriteFileTest File = new WriteFileTest();
                                        File.Write_Text_File(file_out, " - Line 1;");
                                    }
                                    secondLeft = Time_slot;
                                    test_line = 1;
                                    Iniciar_test.setEnabled(false);
                                    Iniciar_test_off.setVisibility(View.VISIBLE);
                                    Iniciar_test_off.setEnabled(true);
                                    Sound(25);
                                    break;

                                case 2:
                                    On = true;
                                    textView2.setText("Entrenamiento nº2");
                                    secondLeft = Time_slot;
                                    Iniciar_test.setEnabled(false);
                                    Iniciar_test_off.setVisibility(View.VISIBLE);
                                    Iniciar_test_off.setEnabled(true);
                                    Sound(26);
                                    break;

                                case 3:
                                    On = true;
                                    textView2.setText("Test Línea 2ª");
                                    Success = 0;
                                    Error = 0;
                                    flag_sel = false;
                                    if (Activ_test) {
                                        WriteFileTest File2 = new WriteFileTest();
                                        File2.Write_Text_File(file_out, " - Line 2;");
                                    }
                                    secondLeft = Time_slot;
                                    test_line = 2;
                                    Iniciar_test.setEnabled(false);
                                    Iniciar_test_off.setVisibility(View.VISIBLE);
                                    Iniciar_test_off.setEnabled(true);
                                    Sound(27);
                                    break;
                                case 4:
                                    On = true;
                                    textView2.setText("Entrenamiento nº3");
                                    secondLeft = Time_slot;
                                    Iniciar_test.setEnabled(false);
                                    Iniciar_test_off.setVisibility(View.VISIBLE);
                                    Iniciar_test_off.setEnabled(true);
                                    Sound(28);
                                    break;
                                case 5:
                                    On = true;
                                    textView2.setText("Test Línea 3ª");
                                    Success = 0;
                                    Error = 0;
                                    flag_sel = false;
                                    if (Activ_test) {
                                        WriteFileTest File3 = new WriteFileTest();
                                        File3.Write_Text_File(file_out, " - Line 3;");
                                    }
                                    secondLeft = Time_slot;
                                    test_line = 3;
                                    Iniciar_test.setEnabled(false);
                                    Iniciar_test_off.setVisibility(View.VISIBLE);
                                    Iniciar_test_off.setEnabled(true);
                                    Sound(29);
                                    break;
                                case 6:
                                    On = true;
                                    textView2.setText("Entrenamiento nº4");
                                    secondLeft = Time_slot;
                                    Sound(30);
                                    Iniciar_test.setEnabled(false);
                                    Iniciar_test_off.setVisibility(View.VISIBLE);
                                    Iniciar_test_off.setEnabled(true);
                                    break;
                                case 7:
                                    On = true;
                                    textView2.setText("Test Línea 4ª");
                                    Success = 0;
                                    Error = 0;
                                    flag_sel = false;
                                    if (Activ_test) {
                                        WriteFileTest File4 = new WriteFileTest();
                                        File4.Write_Text_File(file_out, " - Line 4;");
                                    }
                                    secondLeft = Time_slot;
                                    test_line = 4;
                                    Iniciar_test.setEnabled(false);
                                    Iniciar_test_off.setVisibility(View.VISIBLE);
                                    Iniciar_test_off.setEnabled(true);
                                    Sound(31);
                                    break;
                                case 8:
                                    On = true;
                                    textView2.setText("Entrenamiento nº5");
                                    flag_sel = false;
                                    secondLeft = Time_slot;
                                    Iniciar_test.setEnabled(false);
                                    Iniciar_test_off.setVisibility(View.VISIBLE);
                                    Iniciar_test_off.setEnabled(true);
                                    Sound(32);
                                    break;

                                case 9:
                                    On = true;
                                    textView2.setText("Test Linea 5º");
                                    if (Activ_test) {
                                        WriteFileTest File5 = new WriteFileTest();
                                        File5.Write_Text_File(file_out, " - Line 5 ;");
                                    }
                                    Success = 0;
                                    Error = 0;
                                    secondLeft = Time_slot;
                                    test_line = 5;
                                    Iniciar_test.setEnabled(false);
                                    Iniciar_test_off.setVisibility(View.VISIBLE);
                                    Iniciar_test_off.setEnabled(true);
                                    Sound(33);
                                    break;

                            }
                    }});


        //****************************************************
        // Columna de Respuestas

        //  Símbolo "K"

        button61.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                        {
                          if (secondLeft>0) {
                              Double_Click('K');
                              Simbol_elec = 'A';
                          }
                        }
                    }
                });
        button61.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                {
                    Sound(11);
                    flag_elec = true;
                }
                return false;
            }
        });

        //  Símbolo "L"

        button62.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                        {
                            if (secondLeft>0) {
                                Double_Click('L');
                                Simbol_elec = 'L';
                            }

                        }
                    }
                });
        button62.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                {
                    Sound(12);
                    flag_elec = true;
                }
                return false;
            }
        });

        //  Símbolo "M"

        button63.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                        {
                            if (secondLeft>0) {
                                Double_Click('M');
                                Simbol_elec = 'M';
                            }
                        }
                    }
                });
        button63.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                {
                    Sound(13);
                    flag_elec = true;
                }
                return false;
            }
        });

        //  Símbolo "N"

        button64.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                        {
                            if (secondLeft>0) {
                                Double_Click('N');
                                Simbol_elec = 'N';
                            }
                        }
                    }
                });
        button64.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                {
                    Sound(14);
                    flag_elec = true;
                }
                return false;
            }
        });

        //  Símbolo "Ñ"

        button65.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                        {
                            if (secondLeft>0) {
                                Double_Click('Ñ');
                                Simbol_elec = 'Ñ';
                            }
                        }
                    }
                });
        button65.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                {
                    Sound(15);
                    flag_elec = true;
                }
                return false;
            }
        });

        //**************************************************************************************
        //  linea de Entrenamiento
        //vibra N milisegundos
        //  SIGNO ""K""

        button1.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if (((Status_test == 2)|(Status_test == 4)|(Status_test == 6)|(Status_test == 8)|(Status_test == 0)) & (secondLeft>0)) {
                    if (pin.isChecked())
                    {
                        Sound(1);
                        try {
                            //Retardo
                            Thread.sleep(3500);
                        } catch (Exception e) {

                        }
                        Notification("K", "Llamada Koala", R.mipmap.png1, Pattern.pattern("K"));
                    }else
                    {
                        Sound(11);
                    }
                }
                return false;
            }
        });

        //SIGNO "L"


        button2.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                if (((Status_test == 2)|(Status_test == 4)|(Status_test == 6)|(Status_test == 8)|(Status_test == 0)) & (secondLeft>0)) {
                    if (pin.isChecked()) // Vibraciones Activadas
                    {
                        Sound(2);
                        try {
                            //Retardo
                            Thread.sleep(5300);
                        } catch (Exception e) {

                        }
                        Notification("L", "\"Levando Anclas-Anchors Aweigh-(Zimmerman) ", R.mipmap.png2, Pattern.pattern("L"));
                    }else
                    {
                        Sound(12);
                    }

                }
                return false;
            }});
        //SIGNO "M"

        button3.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                if (((Status_test == 2)|(Status_test == 4)|(Status_test == 6)|(Status_test == 8)|(Status_test == 0)) & (secondLeft>0)) {
                    if (pin.isChecked()) {
                        Sound(3);
                        try {
                            //Retardo
                            Thread.sleep(4700);
                        } catch (Exception e) {

                        }
                        Notification("M", "Llamada Marmota", R.mipmap.png3, Pattern.pattern("M"));
                    } else {
                        Sound(13);
                    }
                }
                return false;

            }
        });

        // Letra "N"

        button4.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                if (((Status_test == 2)|(Status_test == 4)|(Status_test == 6)|(Status_test == 8)|(Status_test == 0)) & (secondLeft>0)) {
                    if (pin.isChecked()) {
                        Sound(4);
                        try {
                            //Retardo
                            Thread.sleep(3700);
                        } catch (Exception e) {

                        }
                        Notification("N", "Tema: New York, New York", R.mipmap.png4, Pattern.pattern("N"));
                    } else {
                        Sound(14);
                    }
                }
                return false;

            }
        });
        //SIGNO "Ñ"

        button5.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                if (((Status_test == 2)|(Status_test == 4)|(Status_test == 6)|(Status_test == 8)|(Status_test == 0)) & (secondLeft>0)) {
                    if (pin.isChecked()) {
                        Sound(5);
                        try {
                            //Retardo
                            Thread.sleep(3500);
                        } catch (Exception e) {

                        }
                        Notification("Ñ", "Ñ en Morse", R.mipmap.png5, Pattern.pattern("E"));
                    } else {
                        Sound(15);
                    }
                }
                return false;
            }
        });
        // *******************************************************************
        // Primera linea
        // Test en primera linea
        //vibra N milisegundos
        //SIGNO ""B""


        button6.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if ((Status_test == 1) & (secondLeft>0)) {
                    if (pin.isChecked()){
                        Notification("K", "Llamada Koala",R.mipmap.png2,Pattern.pattern("K") );
                    }
                    Simbol = 'K';
                    flag_sel= true;
                }
                return false;
            }
        });

        //SIGNO "M"

        button7.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {

                    if ((Status_test == 1) & (secondLeft>0)) {
                        if (pin.isChecked())
                        {
                        Notification("M","Llamada Marmota", R.mipmap.png3,Pattern.pattern("M") );
                    }
                    Simbol = 'M';
                    flag_sel= true;
                }
                return false;
            }});
        //SIGNO ""E""

        button8.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {

                    if ((Status_test == 1) & (secondLeft>0)) {
                        if (pin.isChecked())
                        {
                        Notification("Ñ", "Ñ en Morse", R.mipmap.png5,Pattern.pattern("Ñ") );
                    }
                    Simbol = 'Ñ';
                    flag_sel= true;
                }
                return false;
            }});

        // Letra "L"



        button9.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                    if ((Status_test == 1) & (secondLeft>0)) {
                        if (pin.isChecked())
                        {
                        Notification("L", "Levando Anclas-Anchors Aweigh-(Zimmerman)", R.mipmap.png1, Pattern.pattern("L"));
                    }
                    Simbol = 'L';
                    flag_sel= true;
                }
                return false;
            }});

        // Signo "N"

        button10.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                    if ((Status_test == 1) & (secondLeft>0)) {
                        if (pin.isChecked())
                        {
                        Notification("N","Tema: New York, New York",R.mipmap.png4,Pattern.pattern("N") );
                    }
                    Simbol = 'N';
                    flag_sel= true;
                }
                return false;
            }});

//****************************************************************
// Test en la segunda linea

        //vibra N milisegundos
        //SIGNO "M"


        button11.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 3) & (secondLeft>0)) {
                            if (pin.isChecked())
                            {
                                    Notification("M", "Llamada Marmota", R.mipmap.png4, Pattern.pattern("M"));
                                }
                                Simbol = 'M';
                                flag_sel= true;
                        }
                        return false;
                    }});


        //SIGNO "K"

        button12.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 3) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("K", "Llamada Koala", R.mipmap.png1, Pattern.pattern("K"));
                            }
                            Simbol= 'K';
                            flag_sel= true;
                        }
                        return false;
                    }});
        //SIGNO "N"

        button13.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 3) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("N", "Tema: New York, New York", R.mipmap.png3, Pattern.pattern("N"));
                            }
                            Simbol = 'N';
                            flag_sel= true;
                        }
                        return false;
                    }});

        // Letra "Ñ"

        button14.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 3) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("Ñ", "Tema: New York, New York",R.mipmap.png2,Pattern.pattern("Ñ") );
                            }
                            Simbol= 'Ñ';
                            flag_sel= true;
                        }

                        return false;
                    }});

        //SIGNO "L"

        button15.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 3) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("L", "Levando Anclas-Anchors Aweigh-(Zimmerman)", R.mipmap.png5, Pattern.pattern("L"));
                            }
                            Simbol = 'L';
                            flag_sel= true;
                        }
                        return false;
                    }});


//******************************************
// Test en la tercera linea

        //SIGNO "C"

        button16.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 5) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("Ñ","Ñ en Morse", R.mipmap.png3,Pattern.pattern("Ñ") );
                            }
                            Simbol = 'Ñ';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //SIGNO "L"

        button17.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 5) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("L", "Levando Anclas-Anchors Aweigh-(Zimmerman)", R.mipmap.png4, Pattern.pattern("L"));
                            }
                            Simbol = 'L';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //SIGNO "M"

        button18.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 5) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("M", "Llamada Marmota", R.mipmap.png1, Pattern.pattern("M"));
                            }
                            Simbol = 'M';
                            flag_sel= true;
                        }
                        return false;
                    }});


        // Letra "N"

        button19.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 5) & (secondLeft>0)) {
                            if (pin.isChecked()) {

                                Notification("N", "Tema: New York, New York", R.mipmap.png5, Pattern.pattern("N"));
                            }
                            Simbol = 'N';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //SIGNO "K"

        button20.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 5) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("K", "Llamada Koala", R.mipmap.png2, Pattern.pattern("K"));
                            }
                            Simbol = 'K';
                            flag_sel= true;
                        }
                        return false;
                    }});


//******************************************
// Test en la cuarta linea

        //SIGNO "M"

        button21.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 7) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("M","LLamada Marmota", R.mipmap.png3,Pattern.pattern("M") );
                            }
                            Simbol = 'M';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //SIGNO "K"

        button22.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 7) & (secondLeft>0)) {
                            if (pin.isChecked()) {

                                Notification("K", "Llamada Koala", R.mipmap.png1,Pattern.pattern("K") );
                            }
                            Simbol = 'K';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //SIGNO "N"

        button23.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 7) & (secondLeft>0)) {
                            if (pin.isChecked()) {

                                Notification("N", "Tema: New York, New York", R.mipmap.png5, Pattern.pattern("N"));
                            }
                            Simbol = 'N';
                            flag_sel= true;
                        }
                        return false;
                    }});

        // Letra "M"
        button24.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 7) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("M", "Llamada Marmota", R.mipmap.png2, Pattern.pattern("M"));
                            }
                            Simbol = 'M';
                            flag_sel= true;
                        }
                        return false;
                    }});




        //SIGNO ""D""

        button25.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 7) & (secondLeft>0)) {
                            if (pin.isChecked()) {

                                Notification("D", "Ñ en Morse", R.mipmap.png4, Pattern.pattern("Ñ"));
                            }
                            Simbol = 'Ñ';
                            flag_sel= true;
                        }
                        return false;
                    }});

//********************************************
// Test en la quinta linea

        //vibra N milisegundos
        //SIGNO "L"


        button26.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 9) & (secondLeft>0)) {
                            if (pin.isChecked()) {

                                Notification("L", "Levando Anclas-Anchors Aweigh-(Zimmerman)", R.mipmap.png1, Pattern.pattern("L"));
                            }
                            Simbol = 'L';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //SIGNO "Ñ"

        button27.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 9) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("Ñ", "Ñ en Morse", R.mipmap.png2, Pattern.pattern("Ñ"));
                            }
                            Simbol = 'Ñ';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //SIGNO "K"

        button28.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 9) & (secondLeft>0)) {
                            if (pin.isChecked()) {

                                Notification("K", "Llamada Koala", R.mipmap.png3, Pattern.pattern("K"));
                            }
                            Simbol = 'K';
                            flag_sel= true;
                        }
                        return false;
                    }});

        // Letra "N"

        button29.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 9) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                            }
                            Notification("N","Tema: New York, New York",R.mipmap.png4,Pattern.pattern("N") );
                        }
                        Simbol = 'N';
                        flag_sel= true;
                        return false;

                    }});

        //SIGNO "M"

        button30.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 9) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("M", "Llamada de Marmota", R.mipmap.png3, Pattern.pattern("M"));
                            }
                            Simbol = 'M';
                            flag_sel= true;
                        }
                        return false;
                    }});

    }
}



