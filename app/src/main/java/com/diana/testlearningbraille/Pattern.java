package com.diana.testlearningbraille;

public class Pattern {

    public static long[] pattern(String pat){

       long[] pattern_result= {};

        switch (pat) {
            case "A":
                // Onomatopeya Pantera rosa
                long[] pattern1 = {
                        //sleep//vibrate/Largo
                         0, 200,
                        30, 150,
                        200, 10,
                        50, 200,
                        50, 500
                };
                pattern_result = pattern1;

            break;

            case "B":
                //  Onomatopeya de Bomba: Sirena de Barco  Niebla
                long[] pattern2 = {
                        0, 700,
                        300, 700
                };
                pattern_result = pattern2;
                break;

            case "C":
                // Onomatopeya: Galopar caballo
                long[] pattern3 = {
                        0, 100, //sleep//vibrate// Corto
                        50, 100,//Largo
                        50, 100,
                        200, 50,
                        0, 100, //sleep//vibrate// Corto
                        50, 100,//Largo
                        50, 100
                };

                pattern_result = pattern3;
                break;

            case "D":
                // Onomatopeya: Disparo
                long[] pattern4 = {
                        //sleep//vibrate/Largo
                        0, 200

                };
                pattern_result = pattern4;
                break;

            case "E":
                // Onomatopeya Máquina de escribir
                long[] pattern5 = {
                        50, 100,
                        50, 75,
                        100, 100,
                        75, 100,
                        200, 50,
                        50, 100,
                        50, 75,
                        100, 100,
                        75, 100
                };
                pattern_result = pattern5;
                break;
            case "F":
                // Onomatopeya: Golpeo Martillo en forja.
                // (Ritmo de martinete tac tac tac ...tac)

                long[] pattern6 = {
                        //sleep//vibrate/Largo
                          0,200,
                        300,200,
                        300,200,
                        300,200,
                        800,150
                };
                pattern_result=  pattern6;
            break;

            case "G":
                //    Onomatopeya: Ritmo grillo  gri,gri,gri .....
                long[] pattern7 = {
                        0,200,//Largo
                        300,200,//Corto
                        300,200,//Corto
                        300,200,//Corto
                        300,200,//Largo
                        300,200 //Final letra
                };
                pattern_result =  pattern7;
                break;

            case "H":
                // Onomatopeya: H en Morse

                long[] pattern8 = {
                        0, 100,
                        50,100, // H
                        50,100,
                        50,100

                };
                pattern_result =  pattern8;
                break;

            case "I":
                // Onomatoperya: Campana iglesia.

                long[] pattern9 = {
                          0,200,// 1ª Toque
                         30,500,
                        300,200,// 2º Toque
                         30,500
                };
                pattern_result= pattern9;
                break;

            case "J":
                // Onomatopeya saque de centro: Inicio juego.
                long[] pattern10 = {
                        0,1500
                };
                pattern_result = pattern10;
                break;

            case "K":
                // Onomatopeya: Una llamada de un koala

                long[] pattern11 = {
                        //sleep//vibrate/Largo
                        0,400,
                        30,50,
                        50,50,
                        50,50,
                        50,50,
                        50,50,
                        50,50,
                        50,50,
                        150,300
                };
                pattern_result = pattern11;
                break;

            case "L":
                //    Onomatopeya: Melodia "Levando Ancla"
                //    Levando Anclas "Anchors Aweigh" (Zimmerman)
                long[] pattern12 = {
                        0,600,//Largo
                        150,200,//Corto
                        150,200,//Corto
                        150,200,//Corto
                        300,200,//Largo
                        100,200 //Final letra
                };
                pattern_result = pattern12;
                break;

            case "M":
                // Onomatopeya: Sonido de Marmota

                long[] pattern13 = {
                       //sleep//vibrate// Corto
                          0,200,//Largo
                        500,200,
                        500,200, //sleep//vibrate// Corto
                        700,200, //sleep//vibrate// Corto

                };
                pattern_result = pattern13;
                break;

            case "N":
                // Onomatoperya: Melodia de New York, New yorK

                long[] pattern14 = {
                        0,300,// 1ª Toque
                        200,300,
                        200,300,
                        50,200,
                        50,200,
                        200,100,
                        50,100
                };
                pattern_result = pattern14;
                break;
            case "Ñ":
                // Onomatopeya: N en Morse
                long[] pattern15 = {

                              //Largo
                         50,100,
                         50,100,
                          50,50,//Corto
                        100,100,
                         50,100

                };

                pattern_result = pattern15;
                break;

            case "O":
                // Onomatopeya: Odisea Espacial - Encuentros en la III Fase
                long[] pattern16 = {
                        //sleep//vibrate/Largo
                          0,300,
                        100,300,
                        100,300,
                        200,600,
                        300,1000,

                };
                pattern_result = pattern16;
                break;


            case "P":
                //      Onomatopeya:  Golpeo puerta
                long[] pattern17 = {
                        0,200,//Largo
                        50,200,//Corto
                        50,100,//Corto

                };
                pattern_result = pattern17;
                break;

            case "Q":
                // Onomatopeya: Orquesta Beethoven 5º Sinfonia.
                long[] pattern18 = {
                        0,200,
                        50,200,
                        50,200,
                        50, 1500,
                        50,100

                };
                pattern_result = pattern18;
                break;

            case "R": //Vals  Saga Strauss
                // Onomatopeya:
                long[] pattern19 = {
                          0,400,
                        200,200,
                        100,200,
                        100,200,
                        100,200,
                        400,400,
                        100,200,
                        100,200,
                        100,200,
                        100,200
                };
                pattern_result= pattern19;
                break;

            case "S":
                //  Onomatopeya: Contacto Sonar submarino; Golpe - rebote.
                long[] pattern20 = {
                        //sleep//vibrate/Largo
                        0,300,
                        200,700,

                };
                pattern_result = pattern20;
                break;
            case "T":
                // Onomatopeya: Toque de teléfono analógico
                long[] pattern21 = {
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,
                        30,30,


                };
                pattern_result =  pattern21;
                break;


            case "U":
                // Onomatopeya: Ululuar de un buho
                long[] pattern22 = {
                        0,300,
                        30,30,
                        30,30,
                        30,30,
                        100,500,
                        20,50
                };
                pattern_result = pattern22;
                break;

            case "V":
                // Onomatopa: Canción Supermán
                long[] pattern23 = {
                         0,100,
                        50,100,
                        50,100,
                        50,300,

                };
                pattern_result = pattern23;

                break;
            case "W":
                //  Onomatopeya: Himno U.S.A.
                long[] pattern24 = {
                        //sleep//vibrate/Largo
                          0,200,
                        100,200,
                        100,400,
                        200,300,
                        100,300,
                        100,700,
                };
                pattern_result = pattern24;
                break;

            case "X":
                // Onomatopeya: Letra Y Morse
                long[] pattern25 = {


                         0, 100,
                        50, 100, // S
                        50, 100,

                         150, 300,
                         50, 300,// O
                         50, 300,

                         150, 100,
                         50, 100,// S
                         50, 100,


                };
                pattern_result = pattern25;
                break;

            case "Y":
                long[] pattern26 = {

                           0,200,
                         100,200,
                         100,200,   // El bueno el feo y el malo
                         100,200,
                          50,200,
                          50,200,
                          50,200,
                };

                pattern_result = pattern26;
                break;
            case "Z":
                // Onomatopa: Zorba el Griego (Zimmerman)

                long[] pattern27 = {

                         0,300,
                        50,300,
                        50,100,
                        50,100,
                };
                pattern_result= pattern27;
                break;


        }

       return pattern_result;

    }
}
