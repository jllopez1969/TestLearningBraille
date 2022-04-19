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


public class Lamina_WZ extends AppCompatActivity {

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
    boolean flag_elec = false;
    char  Simbol= 'A';
    char  Simbol_elec;
    int  Sc = 0;
    int  Er =0;
    int  test_line = 0;


    //*************************************************
    //  Funciones de encapsulación de código
    //****************************************************



    // 1ª Acciones al pulsar doble click sobre linea de entrenamiento

    public boolean Double_Click(char character)
    {
        boolean disabled_button_resc = false;
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
                if (flag_sel &  flag_elec ) {
                    flag_elec = false;
                    flag_sel = false;

                    // Determinar éxito o no éxito
                    if (Simbol == character) {
                        Sc++;
                        flag_ok = true;
                        Sound(20);
                        disabled_button_resc = true;

                    } else {
                        Er++;
                        flag_ok = false;
                        Sound(21);
                    }
                    //Casos posibles de test
                    WriteFileTest File = new WriteFileTest();
                    switch (Sc + Er) {

                        case 1:
                            if (flag_ok) {

                                File.Write_Text_File( file_out, "(" + Simbol + ")::Sc;");
                            }else{
                                File.Write_Text_File( file_out, "(" + Simbol + "-"+ Simbol_elec +")::Er;");
                            }

                            break;
                        case 2:
                            if (flag_ok) {

                                File.Write_Text_File(file_out, "(" + Simbol + ")::Sc;");
                            }else{
                                File.Write_Text_File(file_out, "(" + Simbol + "-"+ Simbol_elec +")::Er;");
                            }
                            break;
                        case 3:
                            if (flag_ok) {

                                File.Write_Text_File(file_out, "(" + Simbol + ")::Sc;");
                            }else{
                                File.Write_Text_File(file_out, "(" + Simbol + "-"+ Simbol_elec +")::Er;");
                            }


                            break;


                        case 4:
                            if (flag_ok & (Sc == 5))  {

                                File.Write_Text_File(file_out, "(" + Simbol + ")::Sc;\n - End line::" + test_line + "\n** End Test **");
                                secondLeft = 0;
                                On = false;
                                Activ_test = false;
                                Sound(36);

                            }else{
                                if (!flag_ok){
                                    File.Write_Text_File( file_out, "(" + Simbol + "-"+ Simbol_elec +")::Er;\n - End line::" + test_line + "\n");
                                }
                                else{
                                    File.Write_Text_File( file_out, "(" + Simbol+ ")::Sc;\n - End line::" + test_line + "\n");

                                }
                                secondLeft = 0;
                                Sound(35);
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
        return  disabled_button_resc;
    }

    // Función de locuciones

    public void Sound( int code) {

        MediaPlayer mp;

        switch (code) {
            case 1:
                mp = MediaPlayer.create(this, R.raw.w_himno_usa);
                mp.start();
                break;

            case 2:
                mp = MediaPlayer.create(this, R.raw.x_sos_auxilio_morse);
                mp.start();
                break;

            case 3:
                mp = MediaPlayer.create(this, R.raw.y_bfymalo);
                mp.start();
                break;

            case 4:
                mp = MediaPlayer.create(this, R.raw.z_morse);
                mp.start();
                break;



            case 11:
                mp = MediaPlayer.create(this, R.raw.alphabet_w);
                mp.start();
                break;

            case 12:
                mp = MediaPlayer.create(this, R.raw.alphabet_x);
                mp.start();
                break;

            case 13:
                mp = MediaPlayer.create(this, R.raw.alphabet_y);
                mp.start();
                break;

            case 14:
                mp = MediaPlayer.create(this, R.raw.alphabet_z);
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
                mp = MediaPlayer.create(this, R.raw.lamona_w_z);
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
            case 35:
                mp = MediaPlayer.create(this, R.raw.linea_er);
                mp.start();
                break;
            case 36:
                mp = MediaPlayer.create(this, R.raw.linea_sc);
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
        Intent i = new Intent(Lamina_WZ.this, Message.class);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getActivity(Lamina_WZ.this, 0, i, 0);
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

    //Variables de instancias de objetos de pantalla
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;

    private TextView textView_file1;
    private TextView textView_file2;


    // Variable de estado y tiempo de test
    int Status_test = 21;
    int Time_slot = 120;



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
                            if (Sc < 5 & (Status_test == 1|Status_test == 3|Status_test == 5|Status_test == 7|Status_test == 9))
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
        setContentView(R.layout.activity_lamina_wz);

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

        //Botón de habilitación de patrones de reloj

        Switch pin = findViewById(R.id.switch1);

        /*****************************
         * Respuesta acertada: desabilitar botón
         */
        boolean disable_button_resc= false;


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
                            Cabecera = "** Start TEST **\n" +
                                    " - Id :: " + file_out + ";\n"+
                                    " - Date :: " + currentDateandTime + ";\n";

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
                                    button61.setEnabled(true);
                                    button62.setEnabled(true);
                                    button63.setEnabled(true);
                                    button64.setEnabled(true);
                                    button65.setEnabled(true);
                                    Sc = 0;
                                    Er = 0;
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
                                    button61.setEnabled(true);
                                    button62.setEnabled(true);
                                    button63.setEnabled(true);
                                    button64.setEnabled(true);
                                    button65.setEnabled(true);
                                    Sc = 0;
                                    Er = 0;
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
                                    button61.setEnabled(true);
                                    button62.setEnabled(true);
                                    button63.setEnabled(true);
                                    button64.setEnabled(true);
                                    button65.setEnabled(true);
                                    Sc = 0;
                                    Er = 0;
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
                                    Iniciar_test.setEnabled(false);
                                    Iniciar_test_off.setVisibility(View.VISIBLE);
                                    Iniciar_test_off.setEnabled(true);
                                    Sound(30);
                                    break;
                                case 7:
                                    On = true;
                                    textView2.setText("Test Línea 4ª");
                                    button61.setEnabled(true);
                                    button62.setEnabled(true);
                                    button63.setEnabled(true);
                                    button64.setEnabled(true);
                                    button65.setEnabled(true);
                                    Sc = 0;
                                    Er = 0;
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
                                    button61.setEnabled(true);
                                    button62.setEnabled(true);
                                    button63.setEnabled(true);
                                    button64.setEnabled(true);
                                    button65.setEnabled(true);
                                    if (Activ_test) {
                                        WriteFileTest File5 = new WriteFileTest();
                                        File5.Write_Text_File(file_out, " - Line 5 ;");
                                    }
                                    Sc = 0;
                                    Er = 0;
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

        //  Símbolo "W"

        button61.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                        {
                            if (secondLeft>0) {
                                if (Double_Click('W'))
                                {
                                    button61.setEnabled(false);
                                }
                                Simbol_elec = 'W';
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

        //  Símbolo "X"

        button62.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                        {
                            if (secondLeft>0) {
                                if (Double_Click('X'))
                                {
                                    button62.setEnabled(false);
                                }
                                Simbol_elec = 'X';
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

        //  Símbolo "Y"

        button63.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                        {
                            if (secondLeft>0) {
                                if (Double_Click('Y'))
                                {
                                    button63.setEnabled(false);

                                }
                                Simbol_elec = 'Y';
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

        //  Símbolo "Z"

        button64.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                        {
                            if (secondLeft>0) {
                                if ( Double_Click('Z'))
                                {
                                    button64.setEnabled(false);
                                }
                                Simbol_elec = 'Z';
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


        //**************************************************************************************
        //  linea de Entrenamiento
        //vibra N milisegundos
        //  SIGNO "W"

        button1.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if (((Status_test == 2) | (Status_test == 4) | (Status_test == 6) | (Status_test == 8) | (Status_test == 0)) & (secondLeft>0)) {
                    if (pin.isChecked())
                    {
                        Sound(1);
                        try {
                            //Retardo
                            Thread.sleep(6500);
                        } catch (Exception e) {

                        }
                        Notification("W", "Himno EE.UU: The Star-Spangled Banner", R.mipmap.png1, Pattern.pattern("W"));
                    }else
                    {
                        Sound(11);
                    }
                }
                return false;
            }
        });

        //SIGNO "X"


        button2.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                if (((Status_test == 2) | (Status_test == 4) | (Status_test == 6) | (Status_test == 8) | (Status_test == 0)) & (secondLeft>0)) {
                    if (pin.isChecked()) // Vibraciones Activadas
                    {
                        Sound(2);
                        try {
                            //Retardo
                            Thread.sleep(4000);
                        } catch (Exception e) {

                        }
                        Notification("X", "S.O.S.: Auxilio Morse ", R.mipmap.png2, Pattern.pattern("X"));
                    }else
                    {
                        Sound(12);
                    }

                }
                return false;
            }});
        //SIGNO "Y"

        button3.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                if (((Status_test == 2) | (Status_test == 4) | (Status_test == 6) | (Status_test == 8) | (Status_test == 0)) & (secondLeft>0)) {
                    if (pin.isChecked()) {
                        Sound(3);
                        try {
                            //Retardo
                            Thread.sleep(1500);
                        } catch (Exception e) {

                        }
                        Notification("Y", "Tema: El Bueno, El Malo y el Feo", R.mipmap.png3, Pattern.pattern("Y"));
                    } else {
                        Sound(13);
                    }
                }
                return false;

            }
        });

        // Letra "Z"

        button4.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                if (((Status_test == 2) | (Status_test == 4) | (Status_test == 6) | (Status_test == 8) | (Status_test == 0)) & (secondLeft>0)) {
                    if (pin.isChecked()) {
                        Sound(4);
                        try {
                            //Retardo
                            Thread.sleep(5000);
                        } catch (Exception e) {

                        }
                        Notification("Z", "Z en Morse", R.mipmap.png4, Pattern.pattern("Z"));
                    } else {
                        Sound(14);
                    }
                }
                return false;

            }
        });

        // *******************************************************************
        // Primera linea
        // Test en primera linea
        //vibra N milisegundos
        //SIGNO "X"


        button6.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if ((Status_test == 1) & (secondLeft>0)) {
                    if (pin.isChecked()){
                        Notification("X", "S.O.S: Auxilio en Morse",R.mipmap.png2,Pattern.pattern("X") );
                    }
                    Simbol = 'X';
                    flag_sel= true;
                }
                return false;
            }
        });

        //SIGNO "Y"


        button7.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if ((Status_test == 1) & (secondLeft>0)) {
                    if (pin.isChecked())
                    {
                       Notification("Y","Tema: El Bueno, El Malo y el Feo", R.mipmap.png3,Pattern.pattern("Y") );
                    }
                    Simbol = 'Y';
                    flag_sel= true;
                }
                return false;
            }});
        //SIGNO "Z"

        button8.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if ((Status_test == 1) & (secondLeft>0)) {
                    if (pin.isChecked())
                    {
                        Notification("Z", "Himno EE.UU: The Star-Spangled Banner", R.mipmap.png5,Pattern.pattern("Z") );
                    }
                    Simbol = 'Z';
                    flag_sel= true;
                }
                return false;
            }});

        // Letra "W"

        button9.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if ((Status_test == 1) & (secondLeft>0)) {
                    if (pin.isChecked())
                    {
                        Notification("A", "", R.mipmap.png1, Pattern.pattern("A"));
                    }
                    Simbol = 'A';
                    flag_sel= true;
                }
                return false;
            }});


//****************************************************************
// Test en la segunda linea

        //vibra N milisegundos
        //SIGNO "W"

        button11.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 3) & (secondLeft>0)) {
                            if (pin.isChecked())
                            {
                                if (pin.isChecked()) {
                                    Notification("W", "Himno EE.UU: The Star-Spangled Banner", R.mipmap.png4, Pattern.pattern("W"));
                                }
                                Simbol = 'W';
                                flag_sel= true;
                            }
                        }
                        return false;
                    }});


        //SIGNO "Z"

        button12.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 3) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("Z", "Tema: Zorba el Griego", R.mipmap.png1, Pattern.pattern("Z"));
                            }
                            Simbol= 'Z';
                            flag_sel= true;
                        }
                        return false;
                    }});
        //SIGNO "X"

        button13.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 3) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("X", "S.O.S: Auxilio en Morse", R.mipmap.png3, Pattern.pattern("X"));
                            }
                            Simbol = 'X';
                            flag_sel= true;
                        }
                        return false;
                    }});

        // Letra "Y"

        button14.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 3) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("Y", "Tema: El Bueno, El Malo y el Feo",R.mipmap.png2,Pattern.pattern("Y") );
                            }
                            Simbol= 'Y';
                            flag_sel= true;
                        }

                        return false;
                    }});



//******************************************
// Test en la tercera linea

        //SIGNO "Z"

        button16.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 5) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("Z","Tema: Zorba el Griego", R.mipmap.png3,Pattern.pattern("Z") );
                            }
                            Simbol = 'Z';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //SIGNO "W"

        button17.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 5) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("W", "Himno EE.UU: The Star-Spangled Banner", R.mipmap.png4, Pattern.pattern("W"));
                            }
                            Simbol = 'W';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //SIGNO "Y"

        button18.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 5) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("Y", "Tema: El Bueno, El Malo y el Feo", R.mipmap.png1, Pattern.pattern("Y"));
                            }
                            Simbol = 'Y';
                            flag_sel= true;
                        }
                        return false;
                    }});


        // Letra "X"

        button19.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 5) & (secondLeft>0)) {
                            if (pin.isChecked()) {

                                Notification("X", "S.O.S: Auxilio Morse", R.mipmap.png5, Pattern.pattern("X"));
                            }
                            Simbol = 'X';
                            flag_sel= true;
                        }
                        return false;
                    }});




//******************************************
// Test en la cuarta linea

        //SIGNO "Y"

        button21.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 7) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("Y","Tema: El Bueno, El Malo y el Feo", R.mipmap.png3,Pattern.pattern("Y") );
                            }
                            Simbol = 'Y';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //SIGNO "X"

        button22.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 7) & (secondLeft>0)) {
                            if (pin.isChecked()) {

                                Notification("X", "S.O.S: Auxilio Morse", R.mipmap.png1,Pattern.pattern("X") );
                            }
                            Simbol = 'X';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //SIGNO ""E""

        button23.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 7) & (secondLeft>0)) {
                            if (pin.isChecked()) {

                                Notification("W", "Himno EE.UU: The Star-Spangled Banner", R.mipmap.png5, Pattern.pattern("W"));
                            }
                            Simbol = 'W';
                            flag_sel= true;
                        }
                        return false;
                    }});

        // Letra "Z"


        button24.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 7) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("Z", "Tema: Zorba el Griego", R.mipmap.png2, Pattern.pattern("Z"));
                            }
                            Simbol = 'Z';
                            flag_sel= true;
                        }
                        return false;
                    }});







//********************************************
// Test en la quinta linea

        //vibra N milisegundos
        //SIGNO "W"


        button26.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 9) & (secondLeft>0)) {
                            if (pin.isChecked()) {

                                Notification("W", "Himno EE.UU: The Star-Spangled Banner", R.mipmap.png1, Pattern.pattern("W"));
                            }
                            Simbol = 'W';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //SIGNO "X"

        button27.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 9) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                                Notification("X", "Auxilio Morse", R.mipmap.png2, Pattern.pattern("X"));
                            }
                            Simbol = 'X';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //SIGNO "X"

        button28.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 9) & (secondLeft>0)) {
                            if (pin.isChecked()) {

                                Notification("E", "Tema: El Bueno, El Malo y el Feo", R.mipmap.png3, Pattern.pattern("E"));
                            }
                            Simbol = 'Y';
                            flag_sel= true;
                        }
                        return false;
                    }});

        // Letra "Z"

        button29.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 9) & (secondLeft>0)) {
                            if (pin.isChecked()) {
                            }
                            Notification("Z","Tema: Zorba el Griego",R.mipmap.png4,Pattern.pattern("Z") );
                        }
                        Simbol = 'Z';
                        flag_sel= true;
                        return false;

                    }});



    }
}



