/*
    Group 60:
    Michael Irizarry (miriza6@uic.edu)
    Nikhil Shankar (nshank3@uic.edu)

    CS 342 - Project 2
    Minesweeper

    GUI Class
*/

/*
    Game >>
        Reset
        Top ten
        eXit

    Help >>
        Help
        About
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MinesweeperGui extends JFrame implements iMinesweeper
{
    /**********************
     * Instance Variables * 
     **********************/
    
    private MinesweeperGame game;
    private HighScores topTen;
    private GridIcon[][] gameMatrix;
    
    public MinesweeperGui()
    {
        // Basic GUI Initialization and Configuration
        super("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640,480);
        
        getContentPane().setLayout(new BorderLayout());
        
        // Initialize grid of icons
        gameMatrix = new GridIcon[10][];
        for(int x = 0; x < 10; ++x)
        {
            gameMatrix[x] = new GridIcon[10];
            for(int y = 0; y < 10; ++y)
            {
                GridIcon b = new GridIcon(img.NORMAL, x, y);
                gameMatrix[x][y] = b;
            }
        }
        
        // Initialize game backend
        game = new MinesweeperGame(this);
        
        // Display GUI
        setVisible(true);
    }
    
    
    
    /******************************
     * iMinesepper Implementation *
     ******************************/
    
    // Set cell at @x, @y to @state
    @Override
    public void setCell(int x, int y, String state)
    {
        
    }
    
    // Player won the game (cleared all cells not containing mines)
    @Override
    public void gameWin()
    {
        
    }
    
    // Player lost the game (clicked on a mine)
    @Override
    public void gameLose()
    {
        
    }
    
    
    
    /********************
     * Extended Classes *
     ********************/
    
    private class GridIcon extends ImageIcon implements MouseListener
    {
        // Instance Variables
        public final int cellX, cellY;
        private String state;
        
        // Constructor
        GridIcon(String path, int x, int y)
        {
            super(path);
            addMouseListener(this);
            
            cellX = x;
            cellY = y;
            setState(GuiState.Blank);
        }
        
        // Set state of icon
        void setState(String s)
        {
            switch(s)
            {
                default:
                    state = s;
            }
        }
        
        
        
        /********************************
         * MouseListener Implementation *
         ********************************/
        
        @Override
        public void mouseClicked(MouseEvent e)
        {
            game.clickCell(cellX, cellY);
        }
        
        @Override
        public void mousePressed(MouseEvent e){ }
        @Override
        public void mouseReleased(MouseEvent e){ }
        @Override
        public void mouseEntered(MouseEvent e){ }
        @Override
        public void mouseExited(MouseEvent e){ }
    }
    
    
    
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
    
    private static final class img
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
    
    
    
    private class GuiState extends CellState
    {
        public static final String
        MarkMine        = "M",
        MarkQuestion    = "?";
    }
}
