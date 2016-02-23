/*
    Group 60:
    Michael Irizarry (miriza6@uic.edu)
    Nikhil Shankar (nshank3@uic.edu)

    CS 342 - Project 2
    Minesweeper

    Timer GUI
*/

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.Timer;
import javax.swing.*;

public class GuiTimer extends GuiCounter
{
    /**********************
     * Instance Variables * 
     **********************/
    
    private boolean timerActive;
    
    public GuiTimer()
    {
        super(3);
        timerActive = false;
        
        // Schedule timer event for every 1000ms (1 second)
        (new Timer()).scheduleAtFixedRate(new GameTimer(), 1000, 1000);
    }
    
    
    
    /*******************
     * Timer Functions *
     *******************/
    
    public void startTimer()
    {
        timerActive = true;
    }
    
    public void stopTimer()
    {
        timerActive = false;
    }
    
    public void resetTimer()
    {
        stopTimer();
        setValue(0);
    }
    
    
    /***************
     * Other Stuff *
     ***************/
    
    private void incrementTimer()
    {
        if(timerActive) super.increment();
    }
    
    private class GameTimer extends TimerTask
    {
        @Override
        public void run()
        {
            incrementTimer();
        }
    }
}
