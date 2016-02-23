/*
    Group 60:
    Michael Irizarry (miriza6@uic.edu)
    Nikhil Shankar (nshank3@uic.edu)

    CS 342 - Project 2
    Minesweeper

    GUI Class
*/

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MinesweeperGui extends JFrame implements iMinesweeper
{
    /**********************
     * Instance Variables *
     **********************/
    
    private MinesweeperGame game;
    private HighScores topTen;
    private GridIcon[][] gameGrid;
    private JPanel gridPanel;
    private JPanel topPanel;
    private Icon[] iconNumber;
    private Icon[] iconCounter;
    private boolean gameActive;
    private GameMenu menu;
    private GuiTimer timerPanel;
    private GuiCounter bombCounter;
    private ButtonSmiley smiley;
    
    
    
    /***************
     * Constructor *
     ***************/
    
    public MinesweeperGui() throws Exception
    {
        // Basic GUI Initialization and Configuration
        super("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setLayout(new BorderLayout());
        
        // Load images into icons
        iconNumber = new Icon[imgNumber.length];
        for(int i = 0; i < imgNumber.length; ++i)
        {
            iconNumber[i] = new ImageIcon(basePath + imgNumber[i]);
        }
        
        iconCounter = new Icon[imgCounter.length];
        for(int i = 0; i < imgCounter.length; ++i)
        {
            iconCounter[i] = new ImageIcon(basePath + imgCounter[i]);
        }
        
        // Initialize grid of icons
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(10, 10));
        
        gameGrid = new GridIcon[10][10];
        for(int y = 0; y < 10; ++y)
        {
            for(int x = 0; x < 10; ++x)
            {
                GridIcon b = new GridIcon(iconOther.NORMAL, x, y);
                assert(b != null);
                gameGrid[x][y] = b;
                gridPanel.add(b);
            }
        }
        
        super.add(gridPanel, BorderLayout.CENTER);
        
        // Initialize top panel
        topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 4, 0, 0));
        super.add(topPanel, BorderLayout.NORTH);
        
        // Intialize menu bar
        menu = new GameMenu();
        topPanel.add(menu.getMenu());
        
        // Initialize bomb counter
        bombCounter = new GuiCounter(3);
        topPanel.add(bombCounter);
        
        // Initialize smiley button
        smiley = new ButtonSmiley();
        topPanel.add(smiley);
        
        // Initialize timer panel
        timerPanel = new GuiTimer();
        topPanel.add(timerPanel);
        
        // Initialize game backend
        game = new MinesweeperGame(this);
        startNewGame();
        
        // Display GUI
        super.pack();
        super.setResizable(false);
        super.setVisible(true);
    }
    
    
    
    /*****************
     * GUI Functions *
     *****************/
    
    // Start a new game
    void startNewGame()
    {
        gameActive = true;
        game.newGame();
        timerPanel.resetTimer();
        smiley.setIcon(iconOther.SMILE);
        bombCounter.setValue(10);
    }
    
    
    
    /******************************
     * iMinesepper Implementation *
     ******************************/
    
    // Set cell at (@x, @y) to @state
    @Override
    public void setCell(int x, int y, String state)
    {
        try {
            gameGrid[x][y].setState(state);
        } catch (Exception ex) {
            Logger.getLogger(MinesweeperGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // Player won the game (cleared all cells not containing mines)
    @Override
    public void gameWin()
    {
        gameActive = false;
        timerPanel.stopTimer();
        int time = timerPanel.getValue();
        smiley.setIcon(iconOther.HEAD_GLASSES);
        
        //@@ Insert code for high score
    }
    
    // Player lost the game (clicked on a mine)
    @Override
    public void gameLose()
    {
        gameActive = false;
        timerPanel.stopTimer();
        smiley.setIcon(iconOther.HEAD_DEAD);
    }
    
    
    
    /***************
     * Sub-Classes *
     ***************/
    
    private class GridIcon extends JLabel implements MouseListener
    {
        // Instance Variables
        public final int cellX, cellY;
        private String state;
        
        // Constructor
        GridIcon(Icon icon, int x, int y) throws Exception
        {
            // super("", icon, JLabel.CENTER);
            super(icon);
            super.setSize(icon.getIconWidth(), icon.getIconHeight());
            super.addMouseListener(this);
            
            cellX = x;
            cellY = y;
            setState(GuiState.Blank);
        }
        
        // Set state of icon
        void setState(String s) throws Exception
        {
            switch(s)
            {
                case GuiState.Num0: case GuiState.Num1:
                case GuiState.Num2: case GuiState.Num3:
                case GuiState.Num4: case GuiState.Num5:
                case GuiState.Num6: case GuiState.Num7:
                case GuiState.Num8:
                    super.setIcon(iconNumber[s.charAt(0) - "0".charAt(0)]);
                    break;
                    
                case GuiState.Blank:
                    super.setIcon(iconOther.NORMAL);
                    break;
                    
                case GuiState.Pressed:
                    super.setIcon(iconOther.PRESSED);
                    break;
                    
                case GuiState.Mine:
                    super.setIcon(iconOther.BOMB_PRESSED);
                    break;
                    
                case GuiState.Explode:
                    super.setIcon(iconOther.BOMB_BLOWN);
                    break;
                    
                case GuiState.MarkMine:
                    super.setIcon(iconOther.FLAG);
                    break;
                    
                case GuiState.MarkQuestion:
                    super.setIcon(iconOther.QUESTION);
                    break;
                    
                default:
                    throw new Exception("Unknown state");
            }
            
            state = s;
        }
        
        /********************************
         * MouseListener Implementation *
         ********************************/
        
        @Override
        public void mouseClicked(MouseEvent e)
        {
            // Player middle clicks on cell (used for debugging purposes)
            if(e.getButton() == MouseEvent.BUTTON2)
            {
                startNewGame();
            }
            
            // Game is not active so do nothing
            if(!gameActive) return;
            
            // Player left clicks on cell
            if(e.getButton() == MouseEvent.BUTTON1)
            {
                // Player marked cell as mine or unknown so do nothing
                if(state.equals(GuiState.MarkMine) || state.equals(GuiState.MarkQuestion))
                {
                    return;
                }
                
                if(state.equals(GuiState.Blank) || state.equals(GuiState.Pressed))
                {
                    timerPanel.startTimer();
                    game.clickCell(cellX, cellY);
                }
            }
            
            // Player right clicks on cell
            if(e.getButton() == MouseEvent.BUTTON3)
            {
                try
                {
                    switch(state)
                    {
                        case GuiState.Blank:
                            setState(GuiState.MarkMine);
                            bombCounter.decrement();
                            break;

                        case GuiState.MarkMine:
                            bombCounter.increment();
                            setState(GuiState.MarkQuestion);
                            break;

                        case GuiState.MarkQuestion:
                            setState(GuiState.Blank);
                            break;
                            
                        default:
                            break;
                    }
                }
                finally{ return; }
            }
        }
        
        @Override
        public void mousePressed(MouseEvent e)
        {
            // Game is not active so do nothing
            if(!gameActive) return;
            
            // Player is holding down left button on blank cell
            if(e.getButton() == MouseEvent.BUTTON1 && state.equals(GuiState.Blank))
            {
                try {
                    setState(GuiState.Pressed);
                    smiley.setIcon(iconOther.HEAD_O);
                } catch (Exception ex) {
                    Logger.getLogger(MinesweeperGui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        @Override
        public void mouseReleased(MouseEvent e)
        {
            // Player released left button on blank cell
            if(e.getButton() == MouseEvent.BUTTON1 && state.equals(GuiState.Pressed))
            {
                try {
                    setState(GuiState.Blank);
                    smiley.setIcon(iconOther.SMILE);
                } catch (Exception ex) {
                    Logger.getLogger(MinesweeperGui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        @Override
        public void mouseEntered(MouseEvent e)
        {
        }
        
        @Override
        public void mouseExited(MouseEvent e)
        {
        }
    }
    
    
    
    private class GameMenu implements ActionListener
    {
        private JMenuBar menuBar;
        private JMenu menuGame;
        private JMenuItem menuReset;
        private JMenuItem menuTopTen;
        private JMenuItem menuExit;
        private JMenu menuHelp;
        private JMenuItem menuHelpHelp;
        private JMenuItem menuAbout;
        
        public GameMenu()
        {
            // Initialize menus and items
            menuBar = new JMenuBar();
            menuGame = new JMenu("Game");
            menuReset = new JMenuItem("Reset");
            menuTopTen = new JMenuItem("Top ten");
            menuExit = new JMenuItem("eXit");
            menuHelp = new JMenu("Help");
            menuHelpHelp = new JMenuItem("Help");
            menuAbout = new JMenuItem("About");
            
            // Add sub-menus and items
            menuBar.add(menuGame);
            menuBar.add(menuHelp);
            
            menuGame.add(menuReset);
            menuGame.add(menuTopTen);
            menuGame.add(menuExit);
            
            menuHelp.add(menuHelpHelp);
            menuHelp.add(menuAbout);
            
            // Add item listeners
            menuReset.addActionListener(this);
            menuTopTen.addActionListener(this);
            menuExit.addActionListener(this);
            menuHelpHelp.addActionListener(this);
            menuAbout.addActionListener(this);
        }
        
        public JMenuBar getMenu()
        {
            return menuBar;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == menuReset)
            {
                startNewGame();
            }
            else if(e.getSource() == menuTopTen)
            {
                
            }
            else if(e.getSource() == menuExit)
            {
                System.exit(0);
            }
            else if(e.getSource() == menuHelpHelp)
            {
                
            }
            else if(e.getSource() == menuAbout)
            {
                
            }
        }
    }
    
    
    
    private class ButtonSmiley extends JLabel implements MouseListener
    {
        public ButtonSmiley()
        {
            super(iconOther.SMILE);
            super.addMouseListener(this);
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {
            startNewGame();
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            super.setIcon(iconOther.SMILE_PRESSED);
        }

        @Override
        public void mouseReleased(MouseEvent e)
        {
            super.setIcon(iconOther.SMILE);
        }

        @Override
        public void mouseEntered(MouseEvent e)
        {
        }

        @Override
        public void mouseExited(MouseEvent e)
        {
        }
    }
    
    
    
    /****************
     * Meta Classes *
     ****************/
    
    private static final String basePath =
        "." + File.separator + "img" + File.separator;
    
    private static final String[] imgNumber = {
        "button_pressed.gif", 
        "button_1.gif", "button_2.gif",
        "button_3.gif", "button_4.gif",
        "button_5.gif", "button_6.gif",
        "button_7.gif", "button_8.gif"
    };
    
    private static final String[] imgCounter = {
        "countdown_0.gif", "countdown_1.gif",
        "countdown_2.gif", "countdown_3.gif",
        "countdown_4.gif", "countdown_5.gif",
        "countdown_6.gif", "countdown_7.gif",
        "countdown_8.gif", "countdown_9.gif"
    };
    
    private static final class imgOther
    {
        private static final String
        BOMB_BLOWN = "button_bomb_blown.gif",
        BOMB_PRESSED = "button_bomb_pressed.gif",
        BOMB_X = "button_bomb_x.gif",
        FLAG = "button_flag.gif",
        NORMAL = "button_normal.gif",
        PRESSED = "button_pressed.gif",
        QUESTION = "button_question.gif",
        QUESTION_PRESSED = "button_question_pressed.gif",
        HEAD_DEAD = "head_dead.gif",
        HEAD_GLASSES = "head_glasses.gif",
        HEAD_O = "head_o.gif",
        SMILE = "smile_button.gif",
        SMILE_PRESSED = "smile_button_pressed.gif";
    }
    
    private static final class iconOther
    {
        private static final Icon
        BOMB_BLOWN = new ImageIcon(basePath + imgOther.BOMB_BLOWN),
        BOMB_PRESSED = new ImageIcon(basePath + imgOther.BOMB_PRESSED),
        BOMB_X = new ImageIcon(basePath + imgOther.BOMB_X),
        FLAG = new ImageIcon(basePath + imgOther.FLAG),
        NORMAL = new ImageIcon(basePath + imgOther.NORMAL),
        PRESSED = new ImageIcon(basePath + imgOther.PRESSED),
        QUESTION = new ImageIcon(basePath + imgOther.QUESTION),
        QUESTION_PRESSED = new ImageIcon(basePath + imgOther.QUESTION_PRESSED),
        HEAD_DEAD = new ImageIcon(basePath + imgOther.HEAD_DEAD),
        HEAD_GLASSES = new ImageIcon(basePath + imgOther.HEAD_GLASSES),
        HEAD_O = new ImageIcon(basePath + imgOther.HEAD_O),
        SMILE = new ImageIcon(basePath + imgOther.SMILE),
        SMILE_PRESSED = new ImageIcon(basePath + imgOther.SMILE_PRESSED);
    }
    
    private class GuiState extends CellState
    {
        public static final String
        Pressed         = "P",
        MarkMine        = "M",
        MarkQuestion    = "?";
    }
}
