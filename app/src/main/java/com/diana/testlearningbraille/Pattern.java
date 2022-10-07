package com.diana.testlearningbraille;

public class Pattern {

    public static long[] pattern(String pat) {

        long[] pattern_result = {};

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
                // Onomatopeya:  Tema: Esto es África (Shakira)


                long[] pattern6 = {
                        //sleep//vibrate/Largo

                        0, 200,
                        50, 200,
                        50, 200,
                        50, 200,
                        50, 200,
                        50, 400,

                };
                pattern_result = pattern6;
                break;

            case "G":
                //    Onomatopeya: Ritmo grillo  gri,gri,gri .....
                long[] pattern7 = {
                        0, 200,//Largo
                        300, 200,//Corto
                        300, 200,//Corto
                        300, 200,//Corto
                        300, 200,//Largo
                        300, 200 //Final letra
                };
                pattern_result = pattern7;
                break;

            case "H":
                // Onomatopeya: H en Morse

                long[] pattern8 = {
                        0, 100,
                        50, 100, // H
                        50, 100,
                        50, 100

                };
                pattern_result = pattern8;
                break;

            case "I":
                // Onomatoperya: Campana iglesia.

                long[] pattern9 = {
                        0, 200,// 1ª Toque
                        30, 500,
                        300, 200,// 2º Toque
                        30, 500
                };
                pattern_result = pattern9;
                break;

            case "J":
                // Onomatopeya: Retorno del Jedi, Marcha Imperial
                long[] pattern10 = {
                        0, 4000,
                        200, 4000,
                        200, 4000,
                        200, 6000,
                        200, 2000,
                        200, 2000,
                        200, 6000,
                        200, 2000,
                        200, 2000,
                        200,6000,
                };
                pattern_result = pattern10;
                break;

            case "K":
                // Onomatopeya: Una llamada de un koala

                long[] pattern11 = {
                        //sleep//vibrate/Largo
                        0, 400,
                        30, 50,
                        50, 50,
                        50, 50,
                        50, 50,
                        50, 50,
                        50, 50,
                        50, 50,
                        150, 300
                };
                pattern_result = pattern11;
                break;

            case "L":
                //    Onomatopeya: Melodia "Levando Ancla"
                //    Levando Anclas "Anchors Aweigh" (Zimmerman)
                long[] pattern12 = {
                        0, 600,//Largo
                        150, 200,//Corto
                        150, 200,//Corto
                        150, 200,//Corto
                        300, 200,//Largo
                        100, 200 //Final letra
                };
                pattern_result = pattern12;
                break;

            case "M":
                // Onomatopeya: Sonido de Marmota

                long[] pattern13 = {
                        //sleep//vibrate// Corto
                        0, 200,//Largo
                        500, 200,
                        500, 200, //sleep//vibrate// Corto
                        700, 200, //sleep//vibrate// Corto

                };
                pattern_result = pattern13;
                break;

            case "N":
                // Onomatoperya: Melodia de New York, New yorK

                long[] pattern14 = {
                        0, 300,// 1ª Toque
                        200, 300,
                        200, 300,
                        50, 200,
                        50, 200,
                        200, 100,
                        50, 100
                };
                pattern_result = pattern14;
                break;
            case "Ñ":
                // Onomatopeya: Ñ en Morse
                long[] pattern15 = {

                        //Largo
                        50, 100,
                        50, 100,
                        50, 50,//Corto
                        100, 100,
                        50, 100

                };

                pattern_result = pattern15;
                break;

            case "O":
                // Onomatopeya: Odisea Espacial - Encuentros en la III Fase
                long[] pattern16 = {
                        //sleep//vibrate/Largo
                        0, 300,
                        100, 300,
                        100, 300,
                        150, 500,
                        200, 700,

                };
                pattern_result = pattern16;
                break;


            case "P":
                //      Onomatopeya:  Golpeo puerta
                long[] pattern17 = {
                        0, 200,//Largo
                        50, 200,//Corto
                        50, 100,//Corto

                };
                pattern_result = pattern17;
                break;

            case "Q":
                // Onomatopeya: Orquesta Beethoven 5º Sinfonia.
                long[] pattern18 = {
                        0, 200,
                        50, 200,
                        50, 200,
                        50, 1500,
                        50, 100

                };
                pattern_result = pattern18;
                break;

            case "R": //Vals  Saga Strauss
                // Onomatopeya:
                long[] pattern19 = {
                        0, 400,
                        200, 200,
                        100, 200,
                        100, 200,
                        100, 200,
                        400, 400,
                        100, 200,
                        100, 200,
                        100, 200,
                        100, 200
                };
                pattern_result = pattern19;
                break;

            case "S":
                //  Onomatopeya: Contacto Sonar submarino; Golpe - rebote.
                long[] pattern20 = {
                        //sleep//vibrate/Largo
                        0, 300,
                        200, 700,

                };
                pattern_result = pattern20;
                break;
            case "T":
                // Onomatopeya: Toque de teléfono analógico
                long[] pattern21 = {
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,
                        30, 30,

                };
                pattern_result = pattern21;
                break;


            case "U":
                // Onomatopeya: Ululuar de un buho
                long[] pattern22 = {
                        0, 300,
                        30, 30,
                        30, 30,
                        30, 30,
                        100, 500,
                        20, 50
                };
                pattern_result = pattern22;
                break;

            case "V":
                // Onomatopa: Canción Supermán
                long[] pattern23 = {
                        0, 100,
                        50, 100,
                        50, 100,
                        50, 300,

                };
                pattern_result = pattern23;

                break;
            case "W":
                //  Onomatopeya: Himno U.S.A.
                long[] pattern24 = {
                        //sleep//vibrate/Largo
                        0, 200,
                        100, 200,
                        100, 400,
                        200, 300,
                        100, 300,
                        100, 700,
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

                        0, 200,
                        100, 200,
                        100, 200,   // El bueno el feo y el malo
                        100, 200,
                        50, 200,
                        50, 200,
                        50, 200,
                };

                pattern_result = pattern26;
                break;
            case "Z":
                // Onomatopa: Z en Morse (-- -- . .)

                long[] pattern27 = {

                        0, 300,
                        50, 300,
                        50, 100,
                        50, 100,
                };
                pattern_result = pattern27;
                break;

            case ".":
                // Onomatopeya: . en Morse (.--.--.--)
                long[] pattern28 = {

                        0,  100,
                        50, 300,
                        50, 100,
                        50, 300,
                        50, 100
                };
                pattern_result = pattern28;
                break;
            case ",":
                // Onomatopeya: , en morse
                long[] pattern29 = {

                        0,  300,
                        50, 300,
                        50, 100,
                        50, 100,
                        50, 300,
                        50, 300,
                };
                pattern_result = pattern29;
                break;

            case "1":
                // Onomatopeya: La muerte tenía un precio - Ennio Moricone

                long[] pattern30 = {

                        0, 200,
                        50, 1800,
                        50, 600,
                        50, 1200,
                };
                pattern_result = pattern30;
                break;

            case "2":
                // Onomatopeya: "Gotas de lluvia sobre mi cabeza" FRANCK POURCEL

                long[] pattern31 = {

                          0,600,
                        50, 400,
                        50, 200,
                        50, 400,
                        50, 200,
                        50, 400,
                        50, 200,
                        50, 400

                };
                pattern_result = pattern31;
                break;

            case "3":

                // Onomatopeya:“ Sound of Silence” Simon & Garfunken

                long[] pattern32 = {

                          0, 200,
                         50, 200,
                        100, 200,
                         50, 200,
                        100, 200,
                         50, 200,
                        100, 800

                };
                pattern_result = pattern32;
                break;

            case "4":

                // Onomatopeya:  "Las Cuatro estaciones: Primavera"  Vivaldi

                long[] pattern33 = {

                         0, 300,
                        100, 300,
                        50, 300,
                        50, 300,
                        50, 150,
                        50, 150,
                        50, 600,

                };
                pattern_result = pattern33;
                break;

            case "5":

                long[] pattern34 = {

                        // Onomatopeya:  "In the Mood" Glenn Miller

                        0, 200,
                        50, 200,
                        50, 300,
                        200, 100,
                        50, 500,

                };
                pattern_result = pattern34;
                break;

            case "6":
                // Onomatopeya:   "Jail Rock" Elvis Presley

                long[] pattern35 = {

                        0, 200,
                        50, 600,
                        50, 200,
                        50, 400,
                };
                pattern_result = pattern35;
                break;

            case "7":

                // Onomatopeya:  "Let It Be" The Beatles

                long[] pattern36 = {

                         0, 400,
                        50, 200,
                        50, 200,
                        50, 400,
                        50, 200,
                        50, 200,
                        50, 400,
                        50, 400,
                        50, 800,

                };
                pattern_result = pattern36;
                break;

            case "8":

                // Onomatopeya: "La Misión"   Ennio Moriconne

                long[] pattern37 = {


                         0, 200,
                        50, 200,
                        50, 200,
                        50, 200,
                        50, 800,
                };
                pattern_result = pattern37;
                break;

            case "9":

                // Onomatopeya: "Bajo el Mar: La Sirenita" - Disney

                long[] pattern38 = {


                         0, 200,
                        50, 200,
                        50, 200,
                        50, 100,
                        50, 100,
                        50, 200,
                        50, 200,
                        50, 100,
                        50, 200,
                        50, 200,


                };
                pattern_result = pattern38;
                break;

            case "0":

                //Onomatopeya: Cero en morse  (-- -- -- -- --)

                long[] pattern39 = {

                         0, 300,
                        50, 300,
                        50, 300,
                        50, 300,
                        50, 300,
                };
                pattern_result = pattern39;
                break;

            case "AA":
                // Onomatopeya Pantera rosa
                long[] pattern40 = {
                        //sleep//vibrate/Largo
                        0, 200,
                        30, 150,
                        200, 10,
                        50, 200,
                        50, 500,
                        500,200,

                };
                pattern_result = pattern40;

                break;

            case "EA":
                // Onomatopeya Máquina de escribir
                long[] pattern41 = {
                        50, 100,
                        50, 75,
                        100, 100,
                        75, 100,
                        200, 50,
                        50, 100,
                        50, 75,
                        100, 100,
                        75, 100,
                        500,200,
                };
                pattern_result = pattern41;
                break;

            case "IA":
                // Onomatoperya: Campana iglesia.

                long[] pattern42 = {
                        0, 200,// 1ª Toque
                        30, 500,
                        300, 200,// 2º Toque
                        30, 500,
                        500,200,
                };
                pattern_result = pattern42;
                break;


            case "OA":
                // Onomatopeya: Odisea Espacial - Encuentros en la III Fase
                long[] pattern43 = {
                        //sleep//vibrate/Largo
                        0, 300,
                        100, 300,
                        100, 300,
                        150, 500,
                        200, 700,
                        500,200,

                };
                pattern_result = pattern43;
                break;
            case "UA":
                // Onomatopeya: Ululuar de un buho
                long[] pattern44 = {
                        0, 300,
                        30, 30,
                        30, 30,
                        30, 30,
                        100, 500,
                        20, 50,
                        500,200,
                };
                pattern_result = pattern44;
                break;


        }

        return pattern_result;
    }




    public static long[] pattern_number(String pat) {

        long[] pattern_result = {};

        switch (pat) {
            case "A":
                // 1
                long[] pattern1 = {
                        //sleep//vibrate/Largo
                        0, 600,

                };
                pattern_result = pattern1;

                break;

            case "B":
                //  1 2 ( --, .. )
                long[] pattern2 = {
                            0, 600,

                          300, 300,
                           50,300
                };
                pattern_result = pattern2;
                break;

            case "C":
                // 1 4 ( --,--,--)
                long[] pattern3 = {
                            0, 600,

                          300, 600,
                           50, 600
                };

                pattern_result = pattern3;
                break;

            case "D":
                // 1 4 5 (--,-- --,-- . )
                long[] pattern4 = {
                        //sleep//vibrate/Largo
                          0, 600,

                        300, 600,
                         50, 600,

                        300, 600,
                         50, 200

                };
                pattern_result = pattern4;
                break;

            case "E":
                //  1 5 ( --,--.)
                long[] pattern5 = {
                          0, 600,

                        300, 600,
                         50, 200,
                };
                pattern_result = pattern5;
                break;
            case "F":
                // 1 2 4 ( --,..,-- --)
                long[] pattern6 = {
                        //sleep//vibrate/Largo
                          0, 600,

                        300, 200,
                         50, 200,

                        300, 600,
                         50, 600
                       };
                pattern_result = pattern6;
                break;

            case "G":
                //  1 2 4 5 ( --,..,-- --,--.)
                long[] pattern7 = {
                          0, 600,

                        300, 200,
                         50, 200,

                        300, 600,
                         50, 600,

                        300, 600,
                         50, 200
                };
                pattern_result = pattern7;
                break;

            case "H":
                // 1 2 5 ( --,..,--.)
                long[] pattern8 = {
                          0, 600,

                        300, 200,
                         50, 200,

                        300, 600,
                         50, 200
                };
                pattern_result = pattern8;
                break;

            case "I":
                // 2 4 ( ..,-- --)
                long[] pattern9 = {
                          0, 200,
                         50, 200,

                        300, 600,
                         50, 600,
                };
                pattern_result = pattern9;
                break;

            case "J":
                // 2 4 5 ( ..,-- --,--.)
                long[] pattern10 = {
                          0, 200,
                         50, 200,

                        300, 600,
                         50, 600,

                        300, 600,
                         50, 200

                };
                pattern_result = pattern10;
                break;

            case "K":
                // 1 3 ( --,...)

                long[] pattern11 = {
                          0, 600,

                        300, 200,
                         50, 200,
                         50, 200,
                };
                pattern_result = pattern11;
                break;

            case "L":
                //  1 2 3 ( --,..,...)
                long[] pattern12 = {
                          0, 600,

                        300, 200,
                         50, 200,

                        300, 200,
                         50, 200,
                         50, 200
                };
                pattern_result = pattern12;
                break;

            case "M":
                // 1 3 4 ( --,...,-- --)

                long[] pattern13 = {

                          0, 600,

                        300, 200,
                         50, 200,
                         50, 200,

                        300, 600,
                         50, 600

                };
                pattern_result = pattern13;
                break;

            case "N":
                // 1 3 4 5 (--,...,-- --,--.)
                long[] pattern14 = {
                          0, 600,

                        300, 200,
                         50, 200,
                         50, 200,

                        300, 600,
                         50, 600,

                        300, 600,
                         50, 200


                };
                pattern_result = pattern14;
                break;
            case "Ñ":
                 // 1 2 4 5 6
                long[] pattern15 = {
                          0, 600,

                        300, 200,
                         50, 200,

                        300 ,600,
                         50, 600,

                        300, 600,
                         50, 200,

                        300, 200,
                         50, 600
                };

                pattern_result = pattern15;
                break;

            case "O":
                // 1 3 5 ( --,...,--.)
                long[] pattern16 = {
                        //sleep//vibrate/Largo
                          0, 600,

                        300, 200,
                         50, 200,
                         50, 200,

                        300, 600,
                         50, 200,

                };
                pattern_result = pattern16;
                break;


            case "P":
                //  1 2 3 4 ( --,..,...,-- --)
                long[] pattern17 = {
                          0, 600,

                        300, 200,
                         50, 200,

                        300, 200,
                         50, 200,
                         50, 200

                };
                pattern_result = pattern17;
                break;

            case "Q":
                // 1 2 3 4 5  ( --,..,...,-- --,--.)
                long[] pattern18 = {
                           0, 600,

                         300, 200,
                          50, 200,

                         300, 200,
                          50, 200,
                          50, 200,

                         300, 600,
                          50, 600,

                         300, 600,
                          50, 200,

                };
                pattern_result = pattern18;
                break;

            case "R":
                // 1 2 3 5 ( --,..,...,--.)
                long[] pattern19 = {
                          0, 600,

                        300, 200,
                         50, 200,

                        300, 200,
                         50, 200,
                         50, 200,

                        150, 600,
                         50, 100,
                };
                pattern_result = pattern19;
                break;

            case "S":
                //  2 3 4 ( ..,...,-- --)
                long[] pattern20 = {

                          0, 200,
                         50, 200,

                        300, 200,
                         50, 200,
                         50, 200,

                        300, 600,
                         50, 600
                };
                pattern_result = pattern20;
                break;
            case "T":
                // 2 3 4 5 (..,...,-- --,--.)
                long[] pattern21 = {
                          0, 200,
                         50, 200,
                        300, 100,
                         50, 100,
                         50, 100,

                        300, 600,
                         50, 600,

                        300, 600,
                         50, 100

                };
                pattern_result = pattern21;
                break;


            case "U":
                // 1 3 6 ( --,...,.--)
                long[] pattern22 = {
                          0, 600,

                        300, 200,
                         50, 200,
                         50, 200,

                        300, 200,
                         50, 600,
                };
                pattern_result = pattern22;
                break;

            case "V":
                // 1 2 3 6 ( --,..,...,.--)
                long[] pattern23 = {
                          0, 600,

                        300, 200,
                         50, 200,

                        300, 200,
                         50, 200,
                         50, 200,

                        300, 200,
                         50, 600,
                };
                pattern_result = pattern23;

                break;
            case "W":
                //  2 4 5 6  ( ..,-- --,--.,.--)
                long[] pattern24 = {
                          0, 200,
                         50, 200,

                        300, 600,
                         50, 600,

                        300, 600,
                         50, 200,

                        300, 200,
                         50, 600,
                };

                pattern_result = pattern24;
                break;

            case "X":
                // 1 3 4 6 (--,...,-- --,.--)
                long[] pattern25 = {

                          0, 600,

                        150, 200,
                         50, 200,
                         50, 200,

                        150, 600,
                         50, 600,

                        150, 200,
                         50, 600



                };
                pattern_result = pattern25;
                break;

            case "Y":
                // 1 3 4 5 6 ( --,...,-- --,--.,.--)
                long[] pattern26 = {
                          0, 600,

                        300, 200,
                         50, 200,
                         50, 200,

                        300, 600,
                         50, 600,

                        300, 600,
                         50, 200,

                        300, 200,
                         50, 600,
                };

                pattern_result = pattern26;
                break;
            case "Z":
                // 1 3 5 6  ( --,...,--.,.--)

                long[] pattern27 = {
                          0, 600,

                        300, 100,
                         50, 100,
                         50, 100,

                        300, 600,
                         50, 100,

                        300, 200,
                         50, 600,
                };
                pattern_result = pattern27;
                break;


            case "1":
                //  1 (--)

                long[] pattern28 = {

                        0, 600,

                };
                pattern_result = pattern28;
                break;

            case "2":
                //  1-2 ( --,..)

                long[] pattern29 = {

                          0, 600,

                        300, 200,
                         50, 200,


                };
                pattern_result = pattern29;
                break;

            case "3":

                //  1-4  (--,-- -- )

                long[] pattern30 = {

                          0, 600,

                        300, 600,
                         50, 600,

                };
                pattern_result = pattern30;
                break;

            case "4":

                // 1-4-5  ( --,-- --,--.)

                long[] pattern31 = {

                         0, 600,

                        300, 600,
                         50, 600,

                        300, 600,
                         50, 200,

                };
                pattern_result = pattern31;
                break;

            case "5":
                  //  1-5 ( --,--.)

                long[] pattern32 = {

                        // Onomatopeya:  "In the Mood" Glenn Miller

                         0, 600,

                        300, 600,
                         50, 200,

                };
                pattern_result = pattern32;
                break;

            case "6":
                // 1-2-4 (--,..,-- --)

                long[] pattern33 = {

                         0, 600,

                        300, 200,
                         50, 200,

                        300, 600,
                         50, 600,

                };
                pattern_result = pattern33;
                break;

            case "7":

                // 1-2-4-5  (--,..,-- --,--.)

                long[] pattern34 = {

                         0, 600,

                        300, 200,
                         50, 200,

                        300, 600,
                         50, 600,

                        300, 600,
                         50, 200,

                };
                pattern_result = pattern34;
                break;

            case "8":

                // 1-2-5  (--,..,--.)

                long[] pattern35 = {


                         0, 600,

                        300, 200,
                         50, 200,

                        300, 600,
                         50, 200,
                };
                pattern_result = pattern35;
                break;

            case "9":

                //  2-4 (..,-- --)

                long[] pattern36 = {


                          0, 200,
                         50, 200,

                        300, 600,
                         50, 600,

                };
                pattern_result = pattern36;
                break;

            case "0":

                // 2-4-5  (..,-- --, --.)

                long[] pattern37 = {

                        300, 200,
                        50, 200,

                        300, 600,
                        50, 600,

                        300, 600,
                        50, 200,
                };
                pattern_result = pattern37;
                break;

            case "AA":
                // 1 2 3 5 6  (--,..,...,--.,.--)
                long[] pattern38 = {

                        0, 600,

                        150, 200,
                        50, 200,

                        150, 200,
                        50, 200,
                        50, 200,

                        150, 600,
                        50, 600,

                        150, 600,
                        50, 200,

                        150, 200,
                        50, 600


                };
                pattern_result = pattern38;
                break;

            case "EA":
                // 2 3 4 6  (..,...,-- --,.--)
                long[] pattern39 = {


                        150, 200,
                        50, 200,

                        150, 200,
                        50, 200,
                        50, 200,

                        150, 600,
                        50, 600,

                        150, 200,
                        50, 600


                };
                pattern_result = pattern39;
                break;

            case "IA":
                //  3 4   (...,-- --)
                long[] pattern40 = {


                        150, 200,
                        50, 200,
                        50, 200,

                        150, 600,
                        50, 600,

                };
                pattern_result = pattern40;
                break;

            case "OA":
                // 2 3 4 6  (..,...,-- --,--.,.--)
                long[] pattern41 = {


                        150, 200,
                        50, 200,
                        50, 200,

                        150, 600,
                        50, 600,

                        150, 200,
                        50, 600

                };
                pattern_result = pattern41;
                break;

            case "UA":
                // 2 3 4 5 6  (--,...,-- --,--.,.--)
                long[] pattern42 = {


                        150, 200,
                        50, 200,

                        150, 200,
                        50, 200,
                        50, 200,

                        150, 600,
                        50, 600,

                        150, 600,
                        50, 200,

                        150, 200,
                        50, 600

                };
                pattern_result = pattern42;
                break;

            case ".":
                //  3   (...)
                long[] pattern43 = {

                        150, 200,
                        50, 200,
                        50, 200,

                };
                pattern_result = pattern43;
                break;

            case ",":
                //  2  (-- --)
                long[] pattern44 = {

                        150, 200,
                         50, 200,

                };
                pattern_result = pattern44;
                break;

        }

        return pattern_result;
    }
}
