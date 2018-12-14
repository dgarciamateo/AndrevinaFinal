package com.example.ylw_03.myapplicationprueba;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button boton;
    private Button boton2;
    private EditText text;
    private int intentos;
    private String name= "";
    private TextView label, label2,label3;
    private int numeroRandom = (int)(Math.random()*100+1);
    public static List<Jugador> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentos = 1;

        boton = (Button) findViewById(R.id.adivina);
        boton2 = (Button) findViewById(R.id.recordB);
        text = (EditText) findViewById(R.id.tf);


        label2 = (TextView) findViewById(R.id.label2);

        label2.setText("Pista, el numero es: "+numeroRandom);

        label3 = (TextView) findViewById(R.id.label3);
        label3.setText("Intentos  "+intentos);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String numero = text.getText().toString();

                if(numero.equals("")){
                    Context context = getApplicationContext();
                    CharSequence text = "Introduce un numero";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else{

                    int num = Integer.parseInt(numero);

                    if(numeroRandom==num){

                        Context context = getApplicationContext();
                        CharSequence text = "Lo has adivinado";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        preguntarNombre();
                        numeroRandom = (int)(Math.random()*100+1);

                        label3.setText("Intentos  "+intentos);
                        label2.setText("Pista, el numero es: "+numeroRandom);

                    }else{
                        intentos++;
                        label3.setText("Intentos  "+intentos);
                        text.setText("");
                        if(num>numeroRandom){

                            Toast.makeText(getApplicationContext(), "El numero es menor",Toast.LENGTH_SHORT).show();

                        }else{

                            Toast.makeText(getApplicationContext(), "El numero es mayor",Toast.LENGTH_SHORT).show();

                        }

                    }
                }


                }




        });



        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tablaRecord();
            }
        });

    }

    public void tablaRecord() {
        Intent intent= new Intent(getApplicationContext(), TablaDeRecords.class);

        startActivity(intent);
    }

    private void preguntarNombre(){
        final Dialog dialog = new Dialog(MainActivity.this);

        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Registro de Usuario");
        dialog.show();
        Button register = dialog.findViewById(R.id.botonDialog);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText textName = dialog.findViewById(R.id.etNombre);
                name = textName.getText().toString();
                int trys = intentos;
                Jugador j1 = new Jugador(name,trys);
                players.add(j1);
                writeFile(j1);

                tablaRecord();
                dialog.dismiss();
            }
        });

    }

    private void writeFile(Jugador j){
        try {
            OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("jugadors.txt",Context.MODE_APPEND));

            fout.write(j.getName() + "," + j.getTrys());
            fout.append("\r\n");
            fout.close();

        } catch (Exception  e) {
            e.printStackTrace();
        }
    }



}
