import java.util.Random;

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
    
    private iMinesweeper gui;
    public String[][] grid;
    public int numClicked;
    
    private static final int[] xOffset =
        { -1, 0, 1, -1, 1, -1, 0, 1 };
    private static final int[] yOffset =
        { -1, -1, -1, 0, 0, 1, 1, 1 };
    
    public MinesweeperGame(iMinesweeper iGui)
    {
        gui = iGui;
        newGame();
    }
    
    // Start a new game
    public void newGame()
    {
    	grid = new String[10][10]; 
    	numClicked = 0;
    	Random r1 = new Random();
    	
        for(int x = 0; x < 10; ++x)
        {
            for(int y = 0; y < 10; ++y)
            {
                gui.setCell(x, y, CellState.Blank);
                grid[x][y] = CellState.Blank;
            }
        }
        
        // Place bombs on board
        for(int i=0; i<10; i++)
        {
            int a = r1.nextInt(10);
            int b = r1.nextInt(10);
            if(grid[a][b] != CellState.Mine) grid[a][b] = CellState.Mine;
            else i--; //do it again
        }
    }
    
    // User clicks cell at x, y
    public void clickCell(int x, int y)
    {
    	if(grid[x][y] == CellState.Mine)
        {
            for(int i = 0; i < 10; ++i) for(int j = 0; j < 10; ++j)
            {
                if(grid[i][j] == CellState.Mine)
                {
                    gui.setCell(i, j, CellState.Mine);
                }
            }
            
            gui.setCell(x, y, CellState.Explode);
            gui.gameLose();
        }
        else if(grid[x][y] == CellState.Blank)
        {
            int count = countMines(x, y);
            grid[x][y] = Integer.toString(count);
            gui.setCell(x, y, Integer.toString(count));
            
            if(count == 0) for(int i = 0; i < 8; ++i)
            {
                int newX = x + xOffset[i];
                int newY = y + yOffset[i];
                if(0 <= newX && newX < 10 && 0 <= newY && newY < 10)
                {
                    clickCell(newX, newY);
                }
            }
            
            checkWin();
        }
    }
    
    // Count the number of mines surrounding cell (x, y)
    private int countMines(int x, int y)
    {
        int count = 0;
        
        for(int i = 0; i < 8; ++i)
        {
            int newX = x + xOffset[i];
            int newY = y + yOffset[i];
            if(0 <= newX && newX < 10 && 0 <= newY && newY < 10)
            {
                if(grid[newX][newY] == CellState.Mine) ++count;
            }
        }
        
        return count;
    }
    
    // Check Game Win
    private void checkWin()
    {
        for(int x = 0; x < 10; ++x) for(int y = 0; y < 10; ++y)
        {
            if(grid[x][y] == CellState.Blank) return;
        }
        
        gui.gameWin();
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
