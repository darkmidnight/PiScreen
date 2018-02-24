
package com.darkmidnight.piscreen;

import processing.core.PApplet;

public interface MyProcessingInterface {
    public void draw();
    public boolean inBounds(int x, int y);
    public int getX();
    public int getY();
    public int getWidth();
    public int getHeight();
    public void setDefaultDrag(boolean b);
    public void setX(int x);
    public void setY(int y);
    public void setWidth(int w);
    public void setHeight(int h);
    public void handlePress(int x, int y);
    public void handleRelease(int x, int y);
    public void handleDrag(int x, int y, int px, int py);
    public boolean notifyPress();
    public boolean notifyDrag();
    public boolean notifyRelease();
    public String getThingType();
}


