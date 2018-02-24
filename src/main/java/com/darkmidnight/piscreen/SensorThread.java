package com.darkmidnight.piscreen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SensorThread extends Thread {

    public SensorThread() {
    }
    
    float temp = 0.0f;

    @Override
    public void run() {
        
        while (true) {
            try {
                String line;
//                Process p = Runtime.getRuntime().exec(new String[]{"sensors"});
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in/*p.getInputStream()*/));
                while ((line = input.readLine()) != null) {
                    if (line.contains("Package id 0")) {
                        line = line.substring(line.indexOf(":") + 1, line.indexOf("("));
                        line = line.trim();
                        line = line.replace("+", "");
                        line = line.replace("°C", "");
                       
                        setTemp(Float.parseFloat(line));
                    }
                }
                input.close();
                Thread.sleep(500);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(SensorThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public synchronized float getTemp() {
        return temp;
    }

    public synchronized void setTemp(float temp) {
        this.temp = temp;
    }
    
}
