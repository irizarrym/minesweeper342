/*
    Group 60:
    Michael Irizarry (miriza6@uic.edu)
    Nikhil Shankar (nshank3@uic.edu)

    CS 342 - Project 2
    Minesweeper

    Top frame of the GUI which contains timer
*/

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.Timer;
import javax.swing.*;

public class GuiTimer extends JPanel
{
    /**********************
     * Instance Variables * 
     **********************/
    
    private boolean timerActive;
    private int counter;
    private final Icon[] icons;
    private JLabel[] digits;
    
    public GuiTimer()
    {
        super();
        super.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        timerActive = false;
        counter = 0;
        
        icons = new Icon[imgCounter.length];
        for(int i = 0; i < icons.length; ++i)
        {
            Icon tmp = new ImageIcon(basePath + imgCounter[i]);
            assert(tmp != null);
            icons[i] = tmp;
        }
        
        digits = new JLabel[3];
        for(int i = 0; i < digits.length; ++i)
        {
            digits[i] = new JLabel(icons[0]);
            digits[i].setSize(icons[0].getIconWidth(), icons[0].getIconHeight());
            add(digits[i]);
        }
        
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
        counter = 0;
        updateDigits();
    }
    
    public int getTimer()
    {
        return counter;
    }
    
    
    /***************
     * Other Stuff *
     ***************/
    
    private void incrementTimer()
    {
        if(timerActive)
            {
                counter += 1;
                updateDigits();
            }
    }
    
    private void updateDigits()
    {
        int time = counter;
        for(int i = digits.length - 1; i > 0; --i)
        {
            assert(0 <= i && i < digits.length);
            if(icons[time%10] == null)
            {
                JOptionPane.showMessageDialog(null, "DAMN IT!!!");
            }
            if(digits[i] == null)
            {
                JOptionPane.showMessageDialog(null, "NOOO!!!");
            }
            digits[i].setIcon(icons[time%10]);
            time /= 10;
        }
    }
    
    private class GameTimer extends TimerTask
    {
        @Override
        public void run()
        {
            incrementTimer();
        }
    }
    
    private static final String basePath =
        "." + File.separator + "img" + File.separator;
    
    private static final String[] imgCounter = {
        "countdown_0.gif", "countdown_1.gif",
        "countdown_2.gif", "countdown_3.gif",
        "countdown_4.gif", "countdown_5.gif",
        "countdown_6.gif", "countdown_7.gif",
        "countdown_8.gif", "countdown_9.gif"
    };
}
