/*
    Group 60:
    Michael Irizarry (miriza6@uic.edu)
    Nikhil Shankar (nshank3@uic.edu)

    CS 342 - Project 2
    Minesweeper

    Implements Minesweeper game logic
*/

public class MinesweeperGame
{
    /**********************
     * Instance Variables * 
     **********************/
    
    // Frontend for Minesweeper game
    private iMinesweeper gui;
    
    
    public MinesweeperGame(iMinesweeper iGui)
    {
        gui = iGui;
        
        newGame();
    }
    
    // Start a new game
    public void newGame()
    {
        for(int x = 0; x < 10; ++x)
        {
            for(int y = 0; y < 10; ++y)
            {
                gui.setCell(x, y, CellState.Blank);
            }
        }
    }
    
    // User clicks cell at x, y
    public void clickCell(int x, int y)
    {
        
    }
}

class CellState
{
    public static final String
    Blank   = " ",
    Mine    = "*",
    Explode = "E",
    Pressed = "0",
    Num0    = "0",
    Num1    = "1",
    Num2    = "2",
    Num3    = "3",
    Num4    = "4",
    Num5    = "5",
    Num6    = "6",
    Num7    = "7",
    Num8    = "8";
}