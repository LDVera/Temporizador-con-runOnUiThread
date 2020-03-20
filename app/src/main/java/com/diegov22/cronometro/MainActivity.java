package com.diegov22.cronometro;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Timer;
import java.util.TimerTask;
import android.graphics.Typeface;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    int time=0;
    int rate = 100;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= findViewById(R.id.textview);

        timer = new  Timer("Temporizador");
        Tarea tarea = new Tarea();
        timer.scheduleAtFixedRate(tarea,0,rate);
    }
    @Override
    public void onPause(){
        super.onPause();
        timer.cancel();
    }
    class  Tarea extends  TimerTask{
        @Override
        //run ejecuta el hilo
        public void run(){

            //runnable es para poder modificar la interfaz de uzuario}
            Runnable cambiaTexto = new CambiaTexto();
            //es un hilo que permite modificar los textview en tiempo real
            runOnUiThread(cambiaTexto);
        }
    }
    class CambiaTexto implements  Runnable{
        @Override
        public void run(){
            time=time+rate;
            String texto="TEMPORIZADOR\n rate = " + rate + "\n t= "+ time;
            tv.setText(texto);
            tv.setTypeface(null, Typeface.BOLD);
            tv.setTextSize(30);
        }
    }
}
