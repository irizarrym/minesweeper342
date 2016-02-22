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
    	Random r1 = new Random();
    	
        for(int x = 0; x < 10; ++x)
        {
            for(int y = 0; y < 10; ++y)
            {
                gui.setCell(x, y, CellState.Blank);
            }
        }
        
        for(int i=0; i<9; i++){
         int a = r1.nextInt(9);
         int b = r1.nextInt(9);
         if (gui.getCell(a,b) !="*") gui.setCell(a,b, CellState.Mine);
         else i--; //do it again
        }
        
        
    }
    
    // User clicks cell at x, y
    public void clickCell(int x, int y)
    {
        gui.setCell(x, y, CellState.Num0);
        // gui.gameWin();
        // gui.gameLose();
    }
}

class CellState
{
    public static final String
    Blank   = " ",
    Mine    = "*",
    Explode = "E",
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
