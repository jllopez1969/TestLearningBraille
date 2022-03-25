package com.diana.testlearningbraille;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class WriteFileTest {


    /** FORMA 1 DE ESCRITURA **/


    public void Create_file_text(String fileout, String text, Context context, boolean creado) {

        File raiz = Environment.getExternalStorageDirectory();
        if (raiz.canWrite()) {
            File file = new File(raiz, fileout + ".txt");
            if (!file.exists()) {
                // FileWriter fichero = null;
                try {
                    FileWriter fichero = new FileWriter(file, true);
                    fichero.write(text + "\n");
                    fichero.close();
                    creado = true;

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(context, "Fichero ya existente: Cambie el nombre de fichero", Toast.LENGTH_SHORT).show();
                creado = false;
            }
        }

    }




        public void Write_Text_File (String name_file, String Text){
            File raiz = Environment.getExternalStorageDirectory();
            if (raiz.canWrite()) {
                File file = new File(raiz, name_file + ".txt");
                try {

                    BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
                    out.write(Text);
                    out.close();

                } catch (
                        IOException e) {
                    Log.e("FILE I/O", "Error en la escritura de fichero: " + e.getMessage());
                }
            }
        }
}











