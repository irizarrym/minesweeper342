/*
    Group 60:
    Michael Irizarry (miriza6@uic.edu)
    Nikhil Shankar (nshank3@uic.edu)

    CS 342 - Project 2
    Minesweeper

    Generic counter control
*/

import java.awt.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class GuiCounter extends JPanel
{
    /**********************
     * Instance Variables * 
     **********************/
    
    private int counter;
    private final Icon[] icons;
    private JLabel[] digits;
    
    public GuiCounter(int numDigit)
    {
        super();
        super.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        super.setBorder(new BevelBorder(BevelBorder.RAISED));
        counter = 0;
        
        icons = new Icon[imgCounter.length];
        for(int i = 0; i < icons.length; ++i)
        {
            Icon tmp = new ImageIcon(basePath + imgCounter[i]);
            assert(tmp != null);
            icons[i] = tmp;
        }
        
        digits = new JLabel[numDigit];
        for(int i = 0; i < digits.length; ++i)
        {
            digits[i] = new JLabel(icons[0]);
            digits[i].setSize(icons[0].getIconWidth(), icons[0].getIconHeight());
            add(digits[i]);
        }
    }
    
    
    
    /***********
     * Methods *
     ***********/
    
    public void setValue(int value)
    {
        counter = value;
        updateDigits();
    }
    
    public int getValue()
    {
        return counter;
    }
    
    public void increment()
    {
        setValue(getValue() + 1);
    }
    
    public void decrement()
    {
        setValue(getValue() - 1);
    }
    
    private void updateDigits()
    {
        int value = counter;
        if(value < 0) value = 0;
        for(int i = digits.length - 1; i >= 0; --i)
        {
            digits[i].setIcon(icons[value%10]);
            value /= 10;
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
