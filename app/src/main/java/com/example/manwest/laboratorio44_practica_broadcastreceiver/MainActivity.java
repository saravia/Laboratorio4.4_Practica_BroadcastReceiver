package com.example.manwest.laboratorio44_practica_broadcastreceiver;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends Activity {


    private Button btnEjecutar;
    private ProgressBar pbarProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEjecutar = (Button)findViewById(R.id.btnEjecutar);
        pbarProgreso = (ProgressBar)findViewById(R.id.pbarProgreso);

        btnEjecutar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent msgIntent = new Intent(MainActivity.this, MiIntentService.class);
                msgIntent.putExtra("iteraciones", 10);
                startService(msgIntent);
            }
        });

        IntentFilter filter = new IntentFilter();
        filter.addAction(MiIntentService.ACTION_PROGRESO);
        filter.addAction(MiIntentService.ACTION_FIN);

        ProgressReceiver rcv = new ProgressReceiver();
        registerReceiver(rcv, filter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
