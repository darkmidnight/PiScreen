package com.darkmidnight.piscreen;

import processing.core.PApplet;

/**
 * Basic Interface for breaking out the Draw Objects
 */
public class MyProcessingExample implements MyProcessingInterface {
    int xPos = 0;
    int yPos = 0;
    int itemWidth = 100;
    int itemHeight = 100;
    boolean defaultDraggable = false;
    protected PApplet pa;

    public MyProcessingExample(PApplet pa) {
        this.pa = pa;
        xPos = (int)(Math.random()*pa.width);
        yPos = (int)(Math.random()*pa.height);
    }

    @Override
    public void draw() {
      pa.stroke(0);
      pa.fill(255);
      pa.rect(0, 0, itemWidth, itemHeight);
    }
    @Override
    public boolean inBounds(int x, int y) {
      if (
         (x > xPos) &&
         (x <= (xPos+itemWidth)) &&
         (y > yPos) &&
         (y <= (yPos+itemHeight))) {
           return true;
         }
      else { return false; }
    }
    @Override
    public int getX() { return xPos; }
    @Override
    public int getY() { return yPos; }
    @Override
    public int getWidth() { return itemWidth; }
    @Override
    public int getHeight() { return itemHeight; }

    @Override
    public void setDefaultDrag(boolean b) { this.defaultDraggable = b; }
    @Override
    public void setX(int x) { xPos = x; }
    @Override
    public void setY(int y) { yPos = y; }
    @Override
    public void setWidth(int w) { itemWidth = w; }
    @Override
    public void setHeight(int h) { itemHeight = h; }

    @Override
    public void handlePress(int x, int y) {}
    @Override
    public void handleRelease(int x, int y) {}
    @Override
    public void handleDrag(int x, int y, int px, int py) {
      if (defaultDraggable) {
        int moveX = x-px;
        int moveY = y-py;
        xPos = xPos + moveX;
        yPos = yPos + moveY;
      }
    }
    @Override
    public boolean notifyPress() { return true; }
    @Override
    public boolean notifyDrag() { return true; }
    @Override
    public boolean notifyRelease() { return true; }
    @Override
    public String getThingType() { return this.getClass().getName(); }

}

