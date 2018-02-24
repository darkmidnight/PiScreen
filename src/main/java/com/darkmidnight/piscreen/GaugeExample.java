package com.darkmidnight.piscreen;

import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import processing.core.PConstants;
import static processing.core.PConstants.PI;
import static processing.core.PConstants.PIE;

public class GaugeExample extends MyProcessingExample {

    int xPos = 100;
    int yPos = 100;
    int gaugeWidth = 200;
    int itemWidth = 400;
    
    int itemHeight = 200;
    boolean defaultDraggable = true;

    private SensorThread aThread;
    private float lastValue = 0.0f;

    public GaugeExample(PApplet pa) {
        super(pa);
        aThread = new SensorThread();
        aThread.start();
    }

    @Override
    public void draw() {
        if (!aThread.isAlive()) {
            aThread = new SensorThread();
            aThread.start();
        }
        pa.stroke(0);

        float value = aThread.getTemp();

        double perc = value / 100.0d;

        pa.fill(255, 0, 0);
        pa.arc(0, 0, gaugeWidth, itemHeight, PConstants.PI, PConstants.PI * 2, PIE);
        pa.fill(255, 255, 0);
        pa.arc(0, 0, gaugeWidth, itemHeight, PConstants.PI, (float) (PConstants.PI * 1.8), PIE);
        pa.fill(0, 255, 0);
        pa.arc(0, 0, gaugeWidth, itemHeight, PConstants.PI, (float) (PConstants.PI * 1.4), PIE);

        pa.fill(127);
        pa.arc(0, 0, (float) (gaugeWidth * 0.8), (float) (itemHeight * 0.8), PConstants.PI, PConstants.PI * 2, PIE);

        pa.fill(255);
        pa.arc(0, 0, (float) (gaugeWidth * 0.1), (float) (itemHeight * 0.1), 0, PConstants.PI * 2, PIE);

        pa.fill(255);
        pa.stroke(255);
        pa.textSize(32);
        pa.text("Temperature:", 150, -75);
        pa.text(Float.toString(value), 200, -25);        
        
        pa.rotate((float) (PI + (PI * perc)));
        pa.stroke(255);
        pa.rect(0, -(float) (itemHeight * 0.005), gaugeWidth / 2, (float) (itemHeight * 0.01));
        pa.stroke(0);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GaugeExample.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @Override
    public boolean inBounds(int x, int y) {
        if ((x > xPos)
                && (x <= (xPos + gaugeWidth))
                && (y > yPos)
                && (y <= (yPos + itemHeight))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getX() {
        return xPos;
    }

    @Override
    public int getY() {
        return yPos;
    }

    @Override
    public int getWidth() {
        return gaugeWidth;
    }

    @Override
    public int getHeight() {
        return itemHeight;
    }

    @Override
    public void setDefaultDrag(boolean b) {
        this.defaultDraggable = b;
    }

    @Override
    public void setX(int x) {
        xPos = x;
    }

    @Override
    public void setY(int y) {
        yPos = y;
    }

    @Override
    public void setWidth(int w) {
        gaugeWidth = w;
    }

    @Override
    public void setHeight(int h) {
        itemHeight = h;
    }

    @Override
    public void handlePress(int x, int y) {
    }

    @Override
    public void handleRelease(int x, int y) {
    }

    @Override
    public void handleDrag(int x, int y, int px, int py) {
        if (defaultDraggable) {
            int moveX = x - px;
            int moveY = y - py;
            xPos = xPos + moveX;
            yPos = yPos + moveY;
        }
    }

    @Override
    public boolean notifyDrag() {
        return true;
    }

    @Override
    public boolean notifyRelease() {
        return true;
    }

    @Override
    public String getThingType() {
        return this.getClass().getName();
    }

}
