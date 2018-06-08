package com.appetizers.app.serverapp;

/**
 * Created by Alon on 22-May-18.
 */

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Alon on 22-May-18.
 */

public class ServerTask extends AsyncTask<Void,String,Void> {
    String ip;
    int port;
    Main2Activity activity;
    String line;

    public ServerTask(String ip, int port, Main2Activity activity) {
        this.ip = ip;
        this.port = port;
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Socket socket=new Socket(ip,port);
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            line=reader.readLine();
            while (line!=null&&!line.equals("Bye"))
            {
                publishProgress(line);
                line=reader.readLine();
                Thread.sleep((long) 0.05);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        activity.useData(values[0]);
    }
}

