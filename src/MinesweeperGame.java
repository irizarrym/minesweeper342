import java.util.Random;

/*
    Group 60:
    Michael Irizarry (miriza6@uic.edu)
    Nikhil Shankar (nshank3@uic.edu)

    CS 342 - Project 2
    Minesweeper

    Implements Minesweeper game logic
*/


/*
 * The backend is implemented in class MinesweeperGame and consists of only two functions:
void newGame(); // Start a new game
void clickCell(int x, int y); // User clicks on cell at (x, y)
The backend updates the frontend (the GUI) by calling into interface iMinesweeper which consists of three functions:

void setCell(int x, int y, String state); // Set cell at (x, y) to specified state
void gameWin(); // Tell GUI that player has beaten the game
void gameLose(); // Tell GUI that player has lost the game (i.e. clicked on a mine)
 */

public class MinesweeperGame
{
    /**********************
     * Instance Variables * 
     **********************/
    
    // Frontend for Minesweeper game
    private iMinesweeper gui;
    public String[][] grid;
    public int numClicked;
    
    public MinesweeperGame(iMinesweeper iGui)
    {
        gui = iGui;
        
        newGame();
    }
    
    // Start a new game
    public void newGame()
    {
    	grid = new String[11][11]; 
    	numClicked = 0;
    	Random r1 = new Random();
    	
        for(int x = 0; x < 10; ++x)
        {
            for(int y = 0; y < 10; ++y)
            {
                gui.setCell(x, y, CellState.Blank);
            }
        }
        
        for(int i=0; i<9; i++){				//Initialize bombs//
         int a = r1.nextInt(9);
         int b = r1.nextInt(9);
         if(grid[a][b] != "*") grid[a][b]="*";
         else i--; //do it again
        }
        
        for(int i=0; i<10; i++)	//Checking to see if there are bombs nearby
        	for(int j=0; j<10; j++){
        		
        		int numBombs=0;
        		if(grid[i][j]!="*"){
        		if( (i-1) >= 0){						
        			if(grid[i-1][j]=="*") numBombs++;
        		}
        		
        		if((i+1) < 10){
        			if(grid[i+1][j]=="*") numBombs++;
        		}
        		
        		if( (j-1) >= 0){
        			if(grid[i][j-1]=="*") numBombs++;
        		}
        		
        		if( (j+1) < 10){
        			if(grid[i][j+1]=="*") numBombs++;
        		}
        		
        		if( ( (i-1) >= 0) && ( (j-1) >= 0) ){
        			if(grid[i-1][j-1]=="*") numBombs++;
        		}
        		
        		if( ( (i-1) >= 0) && ( (j+1) < 10) ){
        			if(grid[i-1][j+1]=="*") numBombs++;
        		}
        		
        		if( ( (i+1) < 10) && ( (j+1) < 10) ){
        			if(grid[i+1][j+1]=="*") numBombs++;
        		}
        		
        		if( ( (i+1) < 10) && ( (j-1) >= 0) ){
        			if(grid[i+1][j+1]=="*") numBombs++;
        		}
        		
        		
        		grid[i][j] = String.valueOf(numBombs);
        		
        		}
        		
        		
        	}
        
        
        
    }
    
    // User clicks cell at x, y
    public void clickCell(int x, int y)
    {
    	if(grid[x][y]=="*") gui.gameLose();
        
    	else{
    	  gui.setCell(x, y, grid[x][y]);
    	  if(grid[x][y]=="0"){
    		  if( (x+1) <10) clickCell(x+1,y);
    		  if( (x-1) >= 0) clickCell(x-1,y);
    		  if( (y-1) >=0) clickCell(x,y-1);
    		  if( (y+1) < 10) clickCell(x,y+1);
    		  if( ( (x+1) <10) && ( (y+1) <10)) clickCell(x+1, y+1);
    		  if(( (x+1) <10) &&  ( (y-1) >=0)) clickCell(x+1, y-1);
    		  if( ( (x-1) >=0) && ( (y+1) <10)) clickCell(x-1, y+1);
    		  if( ( (x-1) >=0) && ( (y-1) >= 0)) clickCell(x-1, y-1);
    	  }
    	numClicked++;
    	}	
    //Once all tiles are cleared
    if (numClicked == 90) gui.gameWin();
    
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
