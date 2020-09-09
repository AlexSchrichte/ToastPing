package com.mrstricty.ToastPingSender;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    public void onClick_send_ping(View view) {

        try {

            Socket socket = new Socket("XXX.XXX.XXX.XXX", XXXX);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("WAKE");
            String resp = in.readLine();

            if(resp.equals("WAKE")) {
                Toast.makeText(getApplicationContext(),"Ping confirmation received",Toast.LENGTH_SHORT).show();
                Log.d("TCPRESP", "Wake received from destination");
            }
            socket.close();


        }
        catch(Exception e) {
            Log.d("ERR", e.toString());
        }


    }

}
