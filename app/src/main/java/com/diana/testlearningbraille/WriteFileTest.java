package com.diana.testlearningbraille;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class WriteFileTest {

      public void Create_path_and_file(Context context, String path, String name_file )
      {
          //Path
        File dir = new File( Environment.getExternalStorageDirectory() + "/" + path);
        if (!dir.exists()) {
              dir.mkdir();
              Toast.makeText(context,"Now, directory created",Toast.LENGTH_SHORT).show();
          }else{
              Toast.makeText(context,"Previously created, directory",Toast.LENGTH_LONG).show();
          }

          // File

          File file = new File(dir,name_file);
          if(!file.exists()){
          try {
              BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
                //  out.write(";Resp1;Resp2;Resp3;Resp4;Resp5;\n");
                  out.close();
                  Toast.makeText(context,"Created File",Toast.LENGTH_LONG).show();

              } catch (IOException e) {
                  Toast.makeText(context,"Not Created file",Toast.LENGTH_LONG).show();
              }
          }else{
               Toast.makeText(context,"Before, created file",Toast.LENGTH_LONG).show();
          }
      }

    /** FORMA 1 DE ESCRITURA **/

        public void Write_Text_File (Context context, String path,  String name_file,String text){


            File dir = new File(  Environment.getExternalStorageDirectory() + "/" + path);
            File file = new File(dir, name_file);
            try {

                BufferedWriter out = new BufferedWriter(new FileWriter(file, true));
                out.write(text);
                out.close();
            } catch (IOException e) {
                    Toast.makeText(context,"Texto no añadido",Toast.LENGTH_LONG).show();
            }
        }


}





