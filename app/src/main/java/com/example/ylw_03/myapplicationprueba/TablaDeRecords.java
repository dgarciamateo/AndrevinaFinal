package com.example.ylw_03.myapplicationprueba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TablaDeRecords extends AppCompatActivity {

    private List<Jugador> players2,players3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabla_records);

        players2 = MainActivity.players;
        readFile();
        final TextView nameRecords = findViewById(R.id.tV);
        final TextView trysRecords = findViewById(R.id.tV2);
        nameRecords.setText("");
        trysRecords.setText("");
        if(players2.size()>0){
            Collections.sort(players2);
            for (Jugador jug: players2) {
                nameRecords.setText(nameRecords.getText() + jug.getName() +"\n");
                trysRecords.setText(trysRecords.getText() + String.valueOf(jug.getTrys()) +"\n");
            }
        }else{

            nameRecords.setText(nameRecords.getText() + "No hay datos registrados");
        }


    }

    private void readFile(){
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("jugadors.txt")));

            String texto;
            while((texto = br.readLine())!=null){
                String[] cadena = texto.split(",");
                players3.add(new Jugador(cadena[0],Integer.parseInt(cadena[1])));
            }
            br.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void onBackPressed(){
        Intent intent= new Intent(getApplicationContext(), MainActivity.class);

        startActivity(intent);
    }
}
