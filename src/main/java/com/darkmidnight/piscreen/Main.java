package com.darkmidnight.piscreen;

import java.util.ArrayList;
import processing.core.PApplet;

public class Main extends PApplet {

    public static void main(String args[]) {

        Main mySketch = new Main();
                
        String[] processingArgs = new String[] { "com.darkmidnight.piscreen.Main" };
        PApplet.runSketch(processingArgs, mySketch);
        
        GaugeExample ge = new GaugeExample(mySketch);
        mySketch.addDrawItem(ge);
 
    }
    private ArrayList<MyProcessingInterface> theList = new ArrayList<>();

    int pressX = 0;
    int pressY = 0;
    int releaseX = 0;
    int releaseY = 0;

    public void settings() {
        size(640, 480);
        fullScreen();
    }
    @Override
    public void setup() {
        
    }
    
    public void addDrawItem(MyProcessingInterface mpi) {
            theList.add(mpi);
    }

//    /**
//     * Class for updating a text note. Probably should rewrite this to allow it
//     * to specify which node to update
//     *
//     * @param toString
//     */
//    public void updateTextNode(String toString) {
//        for (MyProcessingInterface mpi : theList) {
//            if (mpi instanceof TextHUDExample) {
//                TextHUDExample txt = (TextHUDExample) mpi;
//                txt.setText(toString);
//                return;
//            }
//        }
//    }

    @Override
    public void draw() {
        background(100);
        for (MyProcessingInterface mpi : theList) {
            pushMatrix();
            translate(mpi.getX(), mpi.getY());
            mpi.draw();
            popMatrix();
            stroke(0);
            fill(0);
        }
    }

    @Override
    public void mousePressed() {
        pressX = mouseX;
        pressY = mouseY;
    }

    @Override
    public void mouseDragged() {
        pressX = mouseX;
        pressY = mouseY;
        MyProcessingInterface tgt = null;
        for (MyProcessingInterface mpi : theList) {
            if (mpi.inBounds(mouseX, mouseY) && mpi.notifyDrag()) {
                tgt = mpi;
            }
        }
        if (tgt != null) {
            tgt.handleDrag(mouseX, mouseY, pmouseX, pmouseY);
        }
    }

    @Override
    public void mouseReleased() {
        releaseX = mouseX;
        releaseY = mouseY;
        MyProcessingInterface tgtFrom = null;
        MyProcessingInterface tgtTo = null;
        for (MyProcessingInterface mpi : theList) {
            if (mpi.inBounds(pressX, pressY) && mpi.notifyRelease()) {
                tgtFrom = mpi;
            } else if (mpi.inBounds(releaseX, releaseY) && mpi.notifyRelease()) {
                tgtTo = mpi;
            }
        }
        if (tgtFrom != null) {
            tgtFrom.handleRelease(pressX, pressY);
        }
        if (tgtTo != null) {
            tgtTo.handleRelease(pressX, pressY);
        }
    }
}
