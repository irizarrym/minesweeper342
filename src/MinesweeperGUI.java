/*
    Group 60:
    Michael Irizarry (miriza6@uic.edu)
    Nikhil Shankar (nshank3@uic.edu)

    CS 342 - Project 2
    Minesweeper

    GUI Class
*/

import java.awt.*;
import javax.swing.*;

public class MinesweeperGUI extends JFrame implements iMinesweeper
{
    public MinesweeperGUI()
    {
        // Basic GUI Initialization and Configuration
        super("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640,480);
        
        getContentPane().setLayout(new BorderLayout());
        
        GridLayout grid = new GridLayout();
        
        // Initialize grid of buttons
        for(int x = 0; x < 10; ++x)
        {
            for(int y = 0; y < 10; ++y)
            {
                GridButton b = new GridButton(x, y);
            }
        }
        
        // Initialize game backend
        game = new MinesweeperGame(this);
        
        // Display GUI
        setVisible(true);
    }
    
    
    
    /********************
     * Extended Classes *
     ********************/
    
    private class GridButton extends JButton
    {
        public int gridX, gridY;
        
        GridButton(int x, int y)
        {
            super();
            gridX = x;
            gridY = y;
        }
    }
    
    
    
    /*********************
     * Private Variables *
     *********************/
    
    MinesweeperGame game;
    
    
    
    /***************
     * Image Files *
     ***************/
    
    private static final String[] imgNumber = {
        "./img/button_pressed", 
        "./img/button_1.gif", "./img/button_2.gif",
        "./img/button_3.gif", "./img/button_4.gif",
        "./img/button_5.gif", "./img/button_6.gif",
        "./img/button_7.gif", "./img/button_8.gif"
    };
    
    private static final String[] imgCounter = {
        "./img/countdown_0.gif", "./img/countdown_1.gif",
        "./img/countdown_2.gif", "./img/countdown_3.gif",
        "./img/countdown_4.gif", "./img/countdown_5.gif",
        "./img/countdown_6.gif", "./img/countdown_7.gif",
        "./img/countdown_8.gif", "./img/countdown_9.gif"
    };
    
    private static class img
    {
        private static final String
        BOMB_BLOWN = "./img/button_bomb_blown.gif",
        BOMB_PRESSED = "./img/button_bomb_pressed.gif",
        BOMB_X = "./img/button_bomb_x.gif",
        FLAG = "./img/button_flag.gif",
        NORMAL = "./img/button_normal.gif",
        PRESSED = "./img/button_pressed.gif",
        QUESTION = "./img/button_question.gif",
        QUESTION_PRESSED = "./img/button_question_pressed.gif",
        HEAD_DEAD = "./img/head_dead.gif",
        HEAD_GLASSES = "./img/head_glasses.gif",
        HEAD_O = "./img/head_o.gif",
        SMILE = "./img/smile_button.gif",
        SMILE_PRESSED = "./img/smile_button_pressed.gif";
    }
}
