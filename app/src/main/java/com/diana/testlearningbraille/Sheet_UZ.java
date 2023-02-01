package com.diana.testlearningbraille;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Sheet_UZ extends AppCompatActivity {

    /*********************************  ********************************************************

     Cycle of execution states

     *.csv File Creation          :  21-20-
     Development of the experiment:  0 - 1- 2 - 3 - 4- 5 - 6- 7 - 8- 9 - 0 ------
                                         (Even state = Training Period)
                                         (Odd state  = Testing Period)

     *********************************  **********************************************************/


    //*******************************
    // Double Click Control
    //*********************************************

    long mLastTime=System.currentTimeMillis();
    long mCurTime=System.currentTimeMillis();
    boolean flag_db = false;


    //************************************
    // Control de escritura línea en fichero texto
    //********************************************
    String file_out ="";
    String path_braille = "/Braille/";

    // Control de test
    boolean flag_sel = false;
    boolean flag_ok= false;
    boolean flag_elec= false;
    char  Symbol;
    char  Symbol_elec;
    int  Sc = 0;
    int  Er =0;
    int  test_line = 0;
    int selected_test = 0;
    int selected_mode= 0;


    //*************************************************
    //  Encapsulation functions
    //****************************************************

    // Common Wait
    public void Wait( float time ) {

        try {
            //Retardo
            Thread.sleep((long) time*1000);
        } catch (Exception e) {

        }
    }


    public boolean Double_Click(char character){

        // Response disable
        boolean disable_button_resc= false;

        // Current time capture
        mCurTime= System.currentTimeMillis();

        if (flag_db & (mCurTime - mLastTime < 300)) {
            mCurTime = System.currentTimeMillis();
            mLastTime = System.currentTimeMillis();
            flag_db = false;

            // Active line test
            if ((Status_test == 1) | (Status_test == 3) | (Status_test == 5) | (Status_test == 7) | (Status_test == 9))
            {
                // Selected symbol on active test line
                if (flag_sel & flag_elec) {
                    flag_sel= false;
                    flag_elec = false;

                    // Succes , not success
                    if (Symbol == character) {
                        Sc++;
                        flag_ok = true;
                        Sound(20);
                        disable_button_resc= true;

                    } else {
                        Er++;
                        flag_ok = false;
                        Sound(21);
                    }
                    //  Cases
                    WriteFileTest File = new WriteFileTest();
                    switch (Sc + Er) {

                        case 1:
                            if (flag_ok) {

                                File.Write_Text_File( Sheet_UZ.this,path_braille,file_out, Symbol + ";" );  // Succes
                            }else{
                                File.Write_Text_File( Sheet_UZ.this,path_braille,file_out,Symbol + "-"+ Symbol_elec +";"); // Error
                            }

                            break;
                        case 2:
                            if (flag_ok) {

                                File.Write_Text_File( Sheet_UZ.this,path_braille,file_out,Symbol + ";" );  // Success
                            }else{
                                File.Write_Text_File(Sheet_UZ.this, path_braille,file_out, Symbol + "-"+ Symbol_elec +";"); // Error
                            }
                            break;
                        case 3:
                            if (flag_ok) {

                                File.Write_Text_File(Sheet_UZ.this, path_braille,file_out,Symbol + ";");  // Success
                            }else{
                                File.Write_Text_File( Sheet_UZ.this,path_braille,file_out, Symbol + "-"+ Symbol_elec +";"); // Error
                            }
                            break;

                        case 4:
                            if (flag_ok) {

                                File.Write_Text_File(Sheet_UZ.this, path_braille,file_out,Symbol + ";" );  // Success
                            }else{
                                File.Write_Text_File( Sheet_UZ.this,path_braille,file_out, Symbol + "-"+ Symbol_elec +";"); // Error
                            }


                            break;
                        case 5:
                            if (Sc == 5)  {
                                if(flag_ok)

                                    File.Write_Text_File(Sheet_UZ.this,path_braille,file_out,Symbol + ";\n" ); //Final Test
                                secondLeft = 0;
                                On = false;
                                Activ_test = false;
                                Sound(36);

                            }else {
                                File.Write_Text_File( Sheet_UZ.this,path_braille,file_out, Symbol + "-"+ Symbol_elec +";\n"); // Line error


                                Sound(35);
                                secondLeft = 0;
                                // On=false;

                            }


                            break;

                    }

                }

            }

        }

        // double click reset
        flag_db = true;
        mLastTime= System.currentTimeMillis();
        return disable_button_resc;
    }
    
    
    // Locutions function

    public void Sound( int code) {

        MediaPlayer mp;

        switch (code) {
            case 1:
                mp = MediaPlayer.create(this, R.raw.u_lular_buho);
                mp.start();
                break;

            case 2:
                mp = MediaPlayer.create(this, R.raw.v_morse);
                mp.start();
                break;

            case 3:
                mp = MediaPlayer.create(this, R.raw.x_sos_auxilio_morse);
                mp.start();
                break;

            case 4:
                mp = MediaPlayer.create(this, R.raw.y_bfymalo);
                mp.start();
                break;

            case 5:
                mp = MediaPlayer.create(this, R.raw.z_morse);
                mp.start();
                break;

            case 11:
                mp = MediaPlayer.create(this, R.raw.alphabet_u);
                mp.start();
                break;

            case 12:
                mp = MediaPlayer.create(this, R.raw.alphabet_v);
                mp.start();
                break;

            case 13:
                mp = MediaPlayer.create(this, R.raw.alphabet_x);
                mp.start();
                break;

            case 14:
                mp = MediaPlayer.create(this, R.raw.alphabet_y);
                mp.start();
                break;

            case 15:
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
                mp = MediaPlayer.create(this, R.raw.sheet_uz);
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

            case 40:
                mp = MediaPlayer.create(this, R.raw.numbers_u);
                mp.start();
                break;
            case 41:
                mp = MediaPlayer.create(this, R.raw.numbers_v);
                mp.start();
                break;
            case 42:
                mp = MediaPlayer.create(this, R.raw.numbers_x);
                mp.start();
                break;
            case 43:
                mp = MediaPlayer.create(this, R.raw.numbers_y);
                mp.start();
                break;
            case 44:
                mp = MediaPlayer.create(this, R.raw.numbers_z);
                mp.start();
                break;




        }


    }


    // Pattern notification


    public void Notification( String Text1, String Text2, int icono, long[] pattern)
    {
        NotificationCompat.Builder mBuilder;
        NotificationManager mNotifyMgr = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);

        //  int icono = R.mipmap.png1;
        Intent i = new Intent(Sheet_UZ.this, Message.class);
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getActivity(Sheet_UZ.this, 0, i, 0);
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
    //  TIMER
    //******************************************************************

    // Timer control
    boolean On = false;
    int secondLeft = 0;
    boolean Activ_test = false;
    private TextView textView;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;

    private TextView textView_file1;
    private TextView textView_file2;


    // State variable and time of test
    int Status_test = 21;
    int Time_slot = 120;



    //  Timer Objects instantation
    //  Action flag
    Boolean  Tacton_trip = true;
    Boolean  Train_Selecc_trip = true;

    Timer timer = new Timer();
    Timer timerSlot1 = new Timer();

    // Flags trip vibrates
    boolean Symbol_U = false;
    boolean Symbol_V = false;
    boolean Symbol_Y = false;
    boolean Symbol_X= false;
    boolean Symbol_Z= false;



    int count = 0;
    TimerTask task1 = new TimerTask() {
        @Override
        public void run() {

            // TODO Auto-generated method stub
            runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    //******  Symbol U  *****
                        if (Symbol_U) {
                            count++;
                        }
                        // Training case
                        if (Symbol_U & ((Status_test==0)||(Status_test==2)||(Status_test==4) || (Status_test==6)||(Status_test==8))) {

                            switch (selected_test) {
                                case 1:
                                    if (count == 3) {
                                        Notification("U", "Ulular Búho", R.mipmap.png3, Pattern.pattern("U"));
                                        Tacton_trip = true;
                                        count = 0;
                                        Symbol_U = false;
                                    }

                                break;
                                case 2:
                                    if (count==2) {
                                        // Number list phrase
                                        Sound(40);
                                    }
                                    if (count==4) {
                                        // Coded Pattern of number list phrase
                                        Notification("U", "1-3-6", R.mipmap.png3, Pattern.pattern_number("U"));
                                        Tacton_trip = true;
                                        count = 0;
                                        Symbol_U = false;
                                    }

                                    break;
                            }

                        }
                        // Experiment case

                        if ((count == 1) &  (Symbol_U) & ((Status_test==1)||(Status_test==3)||(Status_test==5) || (Status_test==7)||(Status_test==9)) )
                        {
                            switch (selected_test) {
                                case 1:
                                    Notification("U", "Ulular Búho", R.mipmap.png3, Pattern.pattern("U"));
                                    Tacton_trip = true;
                                    count = 0;
                                    Symbol_U = false;
                                    break;
                                case 2 :
                                    Notification("U", "1-3-6", R.mipmap.png3, Pattern.pattern_number("U"));
                                    Tacton_trip = true;
                                    count = 0;
                                    Symbol_U = false;
                                    break;

                            }
                        }

                   //***** Symbol V ******



                    if (Symbol_V) {
                        count++;
                    }
                    // Training case
                    if (Symbol_V & ((Status_test==0)||(Status_test==2)||(Status_test==4) || (Status_test==6)||(Status_test==8))) {

                        switch (selected_test) {
                            case 1:
                                if (count == 3) {
                                    Notification("V", "Tema Superman", R.mipmap.png3, Pattern.pattern("V"));
                                    Tacton_trip = true;
                                    count = 0;
                                    Symbol_V = false;
                                }

                                break;
                            case 2:
                                if (count==2) {
                                    // Number list phrase
                                    Sound(41);
                                }
                                if (count==4) {
                                    // Coded Pattern of number list phrase
                                    Notification("V", "1-2-3-6", R.mipmap.png4, Pattern.pattern_number("V"));
                                    Tacton_trip = true;
                                    count = 0;
                                    Symbol_V = false;
                                }

                                break;
                        }

                    }
                    // Experiment case

                    if ((count == 1) &  (Symbol_V) & ((Status_test==1)||(Status_test==3)||(Status_test==5) || (Status_test==7)||(Status_test==9)) )
                    {
                        switch (selected_test) {
                            case 1:
                                Notification("V", "Tema Superman", R.mipmap.png3, Pattern.pattern("V"));
                                Tacton_trip = true;
                                count = 0;
                                Symbol_V = false;
                                break;
                            case 2 :
                                Notification("V", "1-2-3-6", R.mipmap.png4, Pattern.pattern_number("V"));
                                Tacton_trip = true;
                                count = 0;
                                Symbol_V = false;
                                break;

                        }
                    }


                    // *******  Symbol X ****



                    if (Symbol_X) {
                        count++;
                    }

                    // Training case
                    if (Symbol_X & ((Status_test==0)||(Status_test==2)||(Status_test==4) || (Status_test==6)||(Status_test==8))) {

                        switch (selected_test) {
                            case 1:
                                if (count == 4) {
                                    Notification("X", "S.O.S.: Auxilio Morse ", R.mipmap.png2, Pattern.pattern("X"));
                                    Tacton_trip = true;
                                    count = 0;
                                    Symbol_X = false;
                                }

                                break;
                            case 2:
                                if (count==2) {
                                    // Number list phrase
                                    Sound(42);
                                }
                                if (count==4) {
                                    // Coded Pattern of number list phrase
                                    Notification("X", "1-3-4-6", R.mipmap.png2, Pattern.pattern_number("X"));
                                    Tacton_trip = true;
                                    count = 0;
                                    Symbol_X = false;
                                }

                                break;
                        }

                    }
                    // Experiment case

                    if ((count == 1) &  (Symbol_X) & ((Status_test==1)||(Status_test==3)||(Status_test==5) || (Status_test==7)||(Status_test==9)) )
                    {
                        switch (selected_test) {
                            case 1:
                                Notification("X", "S.O.S.: Auxilio Morse ", R.mipmap.png2, Pattern.pattern("X"));
                                Tacton_trip = true;
                                count = 0;
                                Symbol_X = false;
                                break;
                            case 2 :
                                Notification("X", "1-3-4-6", R.mipmap.png2, Pattern.pattern_number("X"));
                                Tacton_trip = true;
                                count = 0;
                                Symbol_X = false;
                                break;

                        }
                    }



                 //******  Symbol Y *********

                    if (Symbol_Y) {
                        count++;
                    }

                    // Training case
                    if (Symbol_Y & ((Status_test==0)||(Status_test==2)||(Status_test==4) || (Status_test==6)||(Status_test==8))) {

                        switch (selected_test) {
                            case 1:
                                if (count == 4) {
                                    Notification("Y", "Tema: El Bueno, El Malo y el Feo", R.mipmap.png3, Pattern.pattern("Y"));
                                    Tacton_trip = true;
                                    count = 0;
                                    Symbol_Y = false;
                                }

                                break;
                            case 2:
                                if (count==2) {
                                    // Number list phrase
                                    Sound(43);
                                }
                                if (count==4) {
                                    // Coded Pattern of number list phrase
                                    Notification("Y", "1-3-4-5-6", R.mipmap.png3, Pattern.pattern_number("Y"));
                                    Tacton_trip = true;
                                    count = 0;
                                    Symbol_Y = false;
                                }

                                break;
                        }

                    }
                    // Experiment case

                    if ((count == 1) &  (Symbol_Y) & ((Status_test==1)||(Status_test==3)||(Status_test==5) || (Status_test==7)||(Status_test==9)) )
                    {
                        switch (selected_test) {
                            case 1:
                                Notification("Y", "Tema: El Bueno, El Malo y el Feo", R.mipmap.png3, Pattern.pattern("Y"));
                                Tacton_trip = true;
                                count = 0;
                                Symbol_Y = false;
                                break;
                            case 2 :
                                Notification("Y", "1-3-4-5-6", R.mipmap.png3, Pattern.pattern_number("Y"));
                                Tacton_trip = true;
                                count = 0;
                                Symbol_Y = false;
                                break;

                        }
                    }




                   // ****** Symbol Z  ******


                    if (Symbol_Z) {
                        count++;
                    }

                    // Training case
                    if (Symbol_Z & ((Status_test==0)||(Status_test==2)||(Status_test==4) || (Status_test==6)||(Status_test==8))) {

                        switch (selected_test) {
                            case 1:
                                if (count == 3) {
                                    Notification("Z", "Z en Morse", R.mipmap.png4, Pattern.pattern("Z"));
                                    Tacton_trip = true;
                                    count = 0;
                                    Symbol_Z = false;
                                }

                                break;
                            case 2:
                                if (count==2) {
                                    // Number list phrase
                                    Sound(44);
                                }
                                if (count==4) {
                                    // Coded Pattern of number list phrase
                                    Notification("Z", "1-3-5-6", R.mipmap.png4, Pattern.pattern_number("Z"));
                                    Tacton_trip = true;
                                    count = 0;
                                    Symbol_Z = false;
                                }

                                break;
                        }

                    }
                    // Experiment case

                    if ((count == 1) &  (Symbol_Z) & ((Status_test==1)||(Status_test==3)||(Status_test==5) || (Status_test==7)||(Status_test==9)) )
                    {
                        switch (selected_test) {
                            case 1:
                                Notification("Z", "Z en Morse", R.mipmap.png4, Pattern.pattern("Z"));
                                Tacton_trip = true;
                                count = 0;
                                Symbol_Z = false;
                                break;
                            case 2 :
                                Notification("Z", "1-3-5-6", R.mipmap.png4, Pattern.pattern_number("Z"));
                                Tacton_trip = true;
                                count = 0;
                                Symbol_Z = false;
                                break;

                        }
                    }



                } // end run

            });
        }
    };







            //  Overload TimerTask Object
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
                              //  WriteFileTest File = new WriteFileTest();
                              //  File.Write_Text_File(Sheet_UZ.this,path_braille,file_out,"- End line ( not completed) ::" + test_line +"\n");

                            }

                            secondLeft = Time_slot;
                            On = false;

                            // States evolution

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


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();

    }

//************************************************************
//                  Create function
//************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_sheet_uz);



        // Timer display
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




// Object graphics of symbols

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

        // Back screen

        ImageButton backscreen = findViewById(R.id.backscreen);
        backscreen.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        //     finishActivity;
                        onBackPressed();
                    }});




        // *.cvs file creation button
        View Create_file = findViewById(R.id.imageView);
        Create_file.setVisibility(View.INVISIBLE);

        //Selection  mode program

      /*  RadioButton pin_mode_work= findViewById(R.id.Chek);
        RadioButton pin_mode_check= findViewById(R.id.Test);


        // Init selected_mode
        pin_mode_check.setEnabled(true);
        pin_mode_work.setEnabled(true);

        pin_mode_work.setChecked(true);
        selected_mode = 1;


        pin_mode_check.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        selected_mode = 0;

                    }});

        pin_mode_work.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        selected_mode= 1;

                    }}); */





        // Selection type test variables

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

                    }});
        pin_Onom.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        selected_test = 1;

                    }});
        pin_Number.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        selected_test = 2;
                    }});



        //Test Start variable
        ImageButton Start_test = findViewById(R.id.Start_test);

        //Test Stop variable
        ImageButton Stop_test= findViewById(R.id.Stop_test);

        //Enable vibrate option
        Vibrator vibrator;
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        //Enable write on disk
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para leer.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso para leer y escribir!");
        }


        // Start threads timer&control
        Stop_test.setVisibility(View.INVISIBLE);
        timer.schedule(task, 1000, 1000);
        timerSlot1.schedule(task1,1000,1000);

        // Initial locution
        Sound(23);

        // Start test

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

                            file_out = edit_file.getText().toString(); // Add test name

                            // Create file *.csv

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                            String currentDateandTime = simpleDateFormat.format(new Date());
                            file_out = file_out +  currentDateandTime + ".csv"; // Add current time to file name

                            WriteFileTest File = new WriteFileTest();
                            File.Create_path_and_file(Sheet_UZ.this, path_braille,file_out);

                        }

                    }});
        

        Stop_test.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        //Interrupción test - Botón emergencia
                        Start_test.setVisibility(View.VISIBLE);
                        Start_test.setEnabled(true);
                        Stop_test.setVisibility((View.INVISIBLE));
                        Stop_test.setEnabled(false);
                        secondLeft=0;


                    }});

        Start_test.setOnClickListener(
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

                                //Mode programme

                   /*             if  (selected_mode == 0)  // Check mode
                                {
                                    pin_mode_check.setEnabled(true);
                                    pin_mode_work.setEnabled(false);
                                }else
                                {
                                    pin_mode_check.setEnabled(false);
                                    pin_mode_work.setEnabled(true);
                                }*/

                                // Selected test
                                if (selected_mode == 0){
                                    switch(selected_test){
                                        case 0:

                                            pin_Control.setEnabled(true);
                                            pin_Onom.setEnabled(false);
                                            pin_Number.setEnabled(false);
                                            break;


                                        case 1:

                                            pin_Control.setEnabled(false);
                                            pin_Onom.setEnabled(true);
                                            pin_Number.setEnabled(false);
                                            break;


                                        case 2:

                                            pin_Control.setEnabled(false);
                                            pin_Onom.setEnabled(false);
                                            pin_Number.setEnabled(true);
                                            break;


                                    }}



                                break;

                            case 0:
                                On = true;
                                Activ_test = true;
                                textView2.setText("Entrenamiento nº1");
                                secondLeft = Time_slot;
                                Start_test.setEnabled(false);
                                Stop_test.setVisibility(View.VISIBLE);
                                Stop_test.setEnabled(true);
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
                                //    File.Write_Text_File(Sheet_UZ.this,path_braille,file_out," - Line 1;");
                                }
                                secondLeft = Time_slot;
                                test_line = 1;
                                Start_test.setEnabled(false);
                                Stop_test.setVisibility(View.VISIBLE);
                                Stop_test.setEnabled(true);
                                Sound(25);
                                break;

                            case 2:
                                On = true;
                                textView2.setText("Entrenamiento nº2");
                                secondLeft = Time_slot;
                                Start_test.setEnabled(false);
                                Stop_test.setVisibility(View.VISIBLE);
                                Stop_test.setEnabled(true);
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
                                  //  File2.Write_Text_File(Sheet_UZ.this,path_braille,file_out," - Line 2;");
                                }
                                secondLeft = Time_slot;
                                test_line = 2;
                                Start_test.setEnabled(false);
                                Stop_test.setVisibility(View.VISIBLE);
                                Stop_test.setEnabled(true);
                                Sound(27);
                                break;
                            case 4:
                                On = true;
                                textView2.setText("Entrenamiento nº3");
                                secondLeft = Time_slot;
                                Start_test.setEnabled(false);
                                Stop_test.setVisibility(View.VISIBLE);
                                Stop_test.setEnabled(true);
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
                                  //  File3.Write_Text_File(Sheet_UZ.this,path_braille,file_out," - Line 3;");
                                }
                                secondLeft = Time_slot;
                                test_line = 3;
                                Start_test.setEnabled(false);
                                Stop_test.setVisibility(View.VISIBLE);
                                Stop_test.setEnabled(true);
                                Sound(29);
                                break;
                            case 6:
                                On = true;
                                textView2.setText("Entrenamiento nº4");
                                secondLeft = Time_slot;
                                Sound(30);
                                Start_test.setEnabled(false);
                                Stop_test.setVisibility(View.VISIBLE);
                                Stop_test.setEnabled(true);
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
                                   // File4.Write_Text_File(Sheet_UZ.this,path_braille,file_out, " - Line 4;");
                                }
                                secondLeft = Time_slot;
                                test_line = 4;
                                Start_test.setEnabled(false);
                                Stop_test.setVisibility(View.VISIBLE);
                                Stop_test.setEnabled(true);
                                Sound(31);
                                break;
                            case 8:
                                On = true;
                                textView2.setText("Entrenamiento nº5");
                                flag_sel = false;
                                secondLeft = Time_slot;
                                Start_test.setEnabled(false);
                                Stop_test.setVisibility(View.VISIBLE);
                                Stop_test.setEnabled(true);
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
                                  //  File5.Write_Text_File(Sheet_UZ.this,path_braille,file_out," - Line 5 ;");
                                }
                                Sc = 0;
                                Er = 0;
                                secondLeft = Time_slot;
                                test_line = 5;
                                Start_test.setEnabled(false);
                                Stop_test.setVisibility(View.VISIBLE);
                                Stop_test.setEnabled(true);
                                Sound(33);
                                break;

                        }
                    }});

        /*****************************
         * True response: To disable button
         */
        boolean disable_button_resc= false;

        //****************************************************
        // Responses Column
        //****************************************************


        //  Símbolo "U"

        button61.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                        {
                            if (secondLeft>0) {
                                if ( Double_Click('U'))
                                {
                                    button61.setEnabled(false);
                                }
                                Symbol_elec = 'U';
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

        //  Símbolo "V"

        button62.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                        {
                            if (secondLeft>0) {
                                if ( Double_Click('V'))
                                {
                                    button62.setEnabled(false);
                                }
                                Symbol_elec = 'V';
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

        //  Símbolo "X"

        button63.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                        {
                            if (secondLeft>0) {

                                if ( Double_Click('X'))
                                {
                                    button63.setEnabled(false);
                                }
                                Symbol_elec = 'X';
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

        //  Símbolo "Y"

        button64.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                        {
                            if (secondLeft>0) {
                                if ( Double_Click('Y'))
                                {
                                    button64.setEnabled(false);
                                }
                                Symbol_elec = 'Y';
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

        //  Símbolo "Z"

        button65.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {

                        if ((Status_test == 1)|(Status_test == 3)|(Status_test == 5)|(Status_test == 7)|(Status_test == 9))
                        {
                            if (secondLeft>0) {
                                if ( Double_Click('Z'))
                                {
                                    button65.setEnabled(false);
                                }
                                Symbol_elec = 'Z';
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
        //  Training line
        //**************************************************************************************

        //  Symbol "U"

        button1.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {

                if (((Status_test == 2)|(Status_test == 4)|(Status_test == 6)|(Status_test == 8)|(Status_test == 0)) & (secondLeft>0) & Tacton_trip)   {
                    Tacton_trip = false;
                    switch (selected_test) {
                        case 0:
                            //Symbol locution
                            Sound(11);
                            break;

                        case 1:
                            //Symbol locution and onomatopoeid sound
                            Sound(1);
                            Symbol_U = true;
                             break;

                        case 2:
                            //Symbol Locution
                            Sound(11);
                            Symbol_U= true;
                        /*    Wait(1);
                            // Number list phrase
                            Sound(40);
                            Wait(1);
                            // Coded Pattern of number list phrase
                            Notification("U", "2-3-6", R.mipmap.png3, Pattern.pattern_number("U"));
                        */
                            break;

                    }

                }
                return false;
            }
        });


        //Symbol "V"


        button2.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                if (((Status_test == 2)|(Status_test == 4)|(Status_test == 6)|(Status_test == 8)|(Status_test == 0)) & (secondLeft>0) & Tacton_trip) {
                    Tacton_trip = false;
                    switch (selected_test) {
                        case 0:
                            //Symbol locution
                            Sound(12);
                            break;

                        case 1:
                            //Symbol locution and onomatopoeid sound
                            Sound(2);
                            Symbol_V = true;

                            break;

                        case 2:
                            //Symbol Locution
                            Sound(12);
                            Symbol_V= true;

                    /*        Wait(1);
                            // Number list phrase
                            Sound(41);
                            Wait(1);
                            // Coded Pattern of number list phrase
                            Notification("V", "1-2-3-6", R.mipmap.png4, Pattern.pattern_number("V"));
*/
                            break;

                    }


                }
                return false;
            }});
        //Symbol "X"

        button3.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                if (((Status_test == 2)|(Status_test == 4)|(Status_test == 6)|(Status_test == 8)|(Status_test == 0)) & (secondLeft>0) & Tacton_trip) {
                    Tacton_trip = false;
                    switch (selected_test) {
                        case 0:
                            //Symbol locution
                            Sound(13);
                            break;

                        case 1:
                            //Symbol locution and onomatopoeid sound
                            Sound(3);
                            // Vibration Pattern
                            Symbol_X = true;
                            break;

                        case 2:
                            //Symbol Locution
                            Sound(13);
                            Symbol_X = true;
          /*                  Wait(1);
                            // Number list phrase
                            Sound(42);
                            Wait(1);
                            // Coded Pattern of number list phrase
                            Notification("X", "1-3-4-6", R.mipmap.png2, Pattern.pattern_number("X"));
*/
                            break;

                    }

                }
                return false;

            }

        });

        // Letra "Y"

        button4.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                if (((Status_test == 2)|(Status_test == 4)|(Status_test == 6)|(Status_test == 8)|(Status_test == 0)) & (secondLeft>0) & Tacton_trip) {
                    Tacton_trip = false;
                    switch (selected_test) {
                        case 0:
                            //Symbol locution
                            Sound(14);
                            break;

                        case 1:
                            //Symbol locution and onomatopoeid sound
                            Sound(4);
                            Symbol_Y = true;

                            break;

                        case 2:
                            //Symbol Locution
                            Sound(14);
                            Symbol_Y= true;
                            /*
                            Wait(1);
                            // Number list phrase
                            Sound(43);
                            Wait(1);
                            // Coded Pattern of number list phrase
                            Notification("Y", "1-3-4-5-6", R.mipmap.png3, Pattern.pattern_number("Y"));
                           */
                            break;


                    }

                }
                return false;
            }
        });
        //Symbol "Z"

        button5.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                if (((Status_test == 2)|(Status_test == 4)|(Status_test == 6)|(Status_test == 8)|(Status_test == 0)) & (secondLeft>0) & Tacton_trip) {
                    Tacton_trip = false;
                    switch (selected_test) {
                        case 0:
                            //Symbol locution
                            Sound(15);
                            break;

                        case 1:
                            //Symbol locution and onomatopoeid sound
                            Sound(5);
                            // Vibration Pattern
                            Symbol_Z = true;
                            break;

                        case 2:
                            //Symbol Locution
                            Sound(15);
                            Symbol_Z = true;
                      /*      Wait(1);
                            // Number list phrase
                            Sound(44);
                            Wait(1);
                            // Coded Pattern of number list phrase
                            Notification("Z", "1-3-5-6", R.mipmap.png4, Pattern.pattern_number("Z"));
                        */
                            break;

                    }

                }
                return false;
            }
        });
        // *******************************************************************
        // Test on first Line
        // *******************************************************************
        //Symbol "U"


        button6.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if ((Status_test == 1) & (secondLeft>0) & Tacton_trip) {
                    Tacton_trip = false;
                    Symbol_U = true;
                    /*switch (selected_test)
                    {
                        case 0:
                            break;
                        case 1:
                            Notification("U", "Ulular Búho", R.mipmap.png3, Pattern.pattern("U"));
                            Symbol_U = true;
                            break;
                        case 2:
                            Notification("U", "1-3-6", R.mipmap.png3, Pattern.pattern_number("U"));
                            break;

                    }*/
                    Symbol = 'U';
                    flag_sel= true;
                }
                return false;
            }
        });

        //Symbol "X"

        button7.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {

                if ((Status_test == 1) & (secondLeft>0)) {
                    Tacton_trip = false;
                    Symbol_X= true;
                  /*  switch (selected_test)
                    {
                        case 0:
                            break;
                        case 1:
                            Notification("X", "S.O.S.: Auxilio Morse ", R.mipmap.png2, Pattern.pattern("X"));
                            break;
                        case 2:
                            Notification("X", "1-3-4-6", R.mipmap.png2, Pattern.pattern_number("X"));
                            break;

                    }*/

                    Symbol = 'X';
                    flag_sel= true;
                }
                return false;
            }});
        //Symbol "Y"

        button8.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {

                if ((Status_test == 1) & (secondLeft>0)) {
                    Tacton_trip = false;
                    Symbol_Y= true;

                    /*switch (selected_test)
                    {
                        case 0:
                            break;
                        case 1:
                            Notification("Y", "Tema: El Bueno, El Malo y el Feo", R.mipmap.png3, Pattern.pattern("Y"));
                            break;
                        case 2:
                            Notification("Y", "1-3-4-5-6", R.mipmap.png3, Pattern.pattern_number("Y"));
                            break;

                    }*/

                    Symbol = 'Y';
                    flag_sel= true;
                }
                return false;
            }});

        // Letra "Z"



        button9.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if ((Status_test == 1) & (secondLeft>0)) {
                    Tacton_trip = false;
                    Symbol_Z= true;
                    /*switch (selected_test)
                    {
                        case 0:
                            break;
                        case 1:
                            Notification("Z", "Z en Morse", R.mipmap.png4, Pattern.pattern("Z"));
                            break;
                        case 2:
                            Notification("Z", "1-3-5-6", R.mipmap.png4, Pattern.pattern("Z"));
                            break;

                    }*/

                    Symbol = 'Z';
                    flag_sel= true;
                }
                return false;
            }});

        // Signo "V"

        button10.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                if ((Status_test == 1) & (secondLeft>0)) {
                    Tacton_trip = false;
                    Symbol_V= true;/*
                    switch (selected_test)
                    {
                        case 0:
                            break;
                        case 1:
                            Notification("V", "V en Morse", R.mipmap.png4, Pattern.pattern("V"));
                            break;
                        case 2:
                            Notification("V", "1-2-3-6", R.mipmap.png4, Pattern.pattern_number("V"));
                            break;

                    }*/
                    Symbol = 'V';
                    flag_sel= true;
                }
                return false;
            }});

        //****************************************************************
        // Test on second line
        //****************************************************************

        //Symbol "U"


        button11.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 3) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_U= true;
                         /*   switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("U", "Ulular Búho", R.mipmap.png3, Pattern.pattern("U"));
                                    break;
                                case 2:
                                    Notification("U", "1-3-6", R.mipmap.png3, Pattern.pattern_number("U"));
                                    break;

                            }*/
                            Symbol = 'U';
                            flag_sel= true;
                        }
                        return false;
                    }});


        //Symbol "V"

        button12.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 3) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_V= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("V", "V en Morse", R.mipmap.png4, Pattern.pattern("V"));
                                    break;
                                case 2:
                                    Notification("V", "1-2-3-6", R.mipmap.png4, Pattern.pattern_number("V"));
                                    break;

                            }*/
                            Symbol= 'V';
                            flag_sel= true;
                        }
                        return false;
                    }});
        //Symbol "Z"

        button13.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 3) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_Z= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("Z", "Z en Morse", R.mipmap.png4, Pattern.pattern("Z"));
                                    break;
                                case 2:
                                    Notification("Z", "1-3-5-6", R.mipmap.png4, Pattern.pattern("Z"));
                                    break;

                            }*/

                            Symbol = 'Z';
                            flag_sel= true;
                        }
                        return false;
                    }});

        // Letra "X"

        button14.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 3) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_X= true; /*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("X", "S.O.S.: Auxilio Morse ", R.mipmap.png2, Pattern.pattern("X"));
                                    break;
                                case 2:
                                    Notification("X", "1-3-4-6", R.mipmap.png2, Pattern.pattern_number("X"));
                                    break;

                            }*/
                            Symbol= 'X';
                            flag_sel= true;
                        }

                        return false;
                    }});

        //Symbol "Y"

        button15.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 3) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_Y= true; /*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("Y", "Tema: El Bueno, El Malo y el Feo", R.mipmap.png3, Pattern.pattern("Y"));
                                    break;
                                case 2:
                                    Notification("Y", "1-3-4-5-6", R.mipmap.png3, Pattern.pattern_number("Y"));
                                    break;

                            }*/
                            Symbol = 'Y';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //**********************************************************
        // Test on third line
        //**********************************************************

        //Symbol "U"

        button16.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 5) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_U= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("U", "Ulular Búho", R.mipmap.png3, Pattern.pattern("U"));
                                    break;
                                case 2:
                                    Notification("U", "1-3-6", R.mipmap.png3, Pattern.pattern_number("U"));
                                    break;

                            }*/
                            Symbol = 'U';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //Symbol "Z"

        button17.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 5) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_Z= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("Z", "Z en Morse", R.mipmap.png4, Pattern.pattern("Z"));
                                    break;
                                case 2:
                                    Notification("Z", "1-3-5-6", R.mipmap.png4, Pattern.pattern("Z"));
                                    break;

                            }*/

                            Symbol = 'Z';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //Symbol "V"

        button18.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 5) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_V= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("V", "V en Morse", R.mipmap.png4, Pattern.pattern("V"));
                                    break;
                                case 2:
                                    Notification("V", "1-2-3-6", R.mipmap.png4, Pattern.pattern_number("V"));
                                    break;

                            }*/
                            Symbol = 'V';
                            flag_sel= true;
                        }
                        return false;
                    }});


        // Letra "Y"

        button19.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 5) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_Y= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("Y", "Tema: El Bueno, El Malo y el Feo", R.mipmap.png3, Pattern.pattern("Y"));
                                    break;
                                case 2:
                                    Notification("Y", "1-3-4-5-6", R.mipmap.png3, Pattern.pattern_number("Y"));
                                    break;

                            }*/
                            Symbol = 'Y';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //Symbol "X"

        button20.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 5) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_X= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("X", "S.O.S.: Auxilio Morse ", R.mipmap.png2, Pattern.pattern("X"));
                                    break;
                                case 2:
                                    Notification("X", "1-3-4-6", R.mipmap.png2, Pattern.pattern_number("X"));
                                    break;

                            }*/
                            Symbol = 'X';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //****************************************************
        // Test on fourth line
        //****************************************************

        //Symbol "U"

        button21.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 7) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_U= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("U", "Ulular Búho", R.mipmap.png3, Pattern.pattern("U"));
                                    break;
                                case 2:
                                    Notification("U", "1-3-6", R.mipmap.png3, Pattern.pattern_number("U"));
                                    break;

                            }*/
                            Symbol = 'U';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //Symbol "Y"

        button22.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 7) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_Y= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("Y", "Tema: El Bueno, El Malo y el Feo", R.mipmap.png3, Pattern.pattern("Y"));
                                    break;
                                case 2:
                                    Notification("Y", "1-3-4-5-6", R.mipmap.png3, Pattern.pattern_number("Y"));
                                    break;

                            }*/
                            Symbol = 'Y';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //Symbol "X"

        button23.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 7) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_X= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("X", "S.O.S.: Auxilio Morse ", R.mipmap.png2, Pattern.pattern("X"));
                                    break;
                                case 2:
                                    Notification("X", "1-3-4-6", R.mipmap.png2, Pattern.pattern_number("X"));
                                    break;

                            }*/
                            Symbol = 'X';
                            flag_sel= true;
                        }
                        return false;
                    }});

        // Letra "V"
        button24.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 7) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_V= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("V", "V en Morse", R.mipmap.png4, Pattern.pattern("V"));
                                    break;
                                case 2:
                                    Notification("V", "1-2-3-6", R.mipmap.png4, Pattern.pattern_number("V"));
                                    break;

                            }*/
                            Symbol = 'V';
                            flag_sel= true;
                        }
                        return false;
                    }});




        //Symbol "Z"

        button25.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 7) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_Z= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("Z", "Z en Morse", R.mipmap.png4, Pattern.pattern("Z"));
                                    break;
                                case 2:
                                    Notification("Z", "1-3-5-6", R.mipmap.png4, Pattern.pattern("Z"));
                                    break;

                            }*/

                            Symbol = 'Z';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //*****************************************************
        // Test on fifth line
        //******************************************************

        //Symbol "U"


        button26.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 9) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_U= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("U", "Ulular Búho", R.mipmap.png3, Pattern.pattern("U"));
                                    break;
                                case 2:
                                    Notification("U", "1-3-6", R.mipmap.png3, Pattern.pattern_number("U"));
                                    break;

                            }*/
                            Symbol = 'U';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //Symbol "V"

        button27.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 9) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_V= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("V", "V en Morse", R.mipmap.png4, Pattern.pattern("V"));
                                    break;
                                case 2:
                                    Notification("V", "1-2-3-6", R.mipmap.png4, Pattern.pattern_number("V"));
                                    break;

                            }*/
                            Symbol = 'V';
                            flag_sel= true;
                        }
                        return false;
                    }});

        //Symbol "X"

        button28.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 9) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_X= true;/*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("X", "S.O.S.: Auxilio Morse ", R.mipmap.png2, Pattern.pattern("X"));
                                    break;
                                case 2:
                                    Notification("X", "1-3-4-6", R.mipmap.png2, Pattern.pattern_number("X"));
                                    break;

                            }*/
                            Symbol = 'X';
                            flag_sel= true;
                        }
                        return false;
                    }});

        // Letra "Y"

        button29.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 9) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_Y= true;
                            /*
                            switch (selected_test) {
                                case 0:
                                    break;
                                case 1:
                                    Notification("Y", "Tema: El Bueno, El Malo y el Feo", R.mipmap.png3, Pattern.pattern("Y"));
                                    break;
                                case 2:
                                    Notification("Y", "1-3-4-5-6", R.mipmap.png3, Pattern.pattern_number("Y"));
                                    break;

                            }*/
                            Symbol = 'Y';
                            flag_sel = true;
                        }
                        return false;

                    }});

        //Symbol "Z"

        button30.setOnLongClickListener(
                new View.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if ((Status_test == 9) & (secondLeft>0)) {
                            Tacton_trip = false;
                            Symbol_Z= true;
                            /*
                            switch (selected_test)
                            {
                                case 0:
                                    break;
                                case 1:
                                    Notification("Z", "Z en Morse", R.mipmap.png4, Pattern.pattern("Z"));
                                    break;
                                case 2:
                                    Notification("Z", "1-3-5-6", R.mipmap.png4, Pattern.pattern("Z"));
                                    break;

                            }*/

                            Symbol = 'Z';
                            flag_sel= true;
                        }
                        return false;
                    }});




    }
}