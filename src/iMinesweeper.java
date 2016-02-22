/*
    Group 60:
    Michael Irizarry (miriza6@uic.edu)
    Nikhil Shankar (nshank3@uic.edu)

    CS 342 - Project 2
    Minesweeper

    Interface which allows game backend to update GUI
*/

public interface iMinesweeper
{
    // Set cell at x, y
    void setCell(int x, int y, String state);
    
        // Get Cell Status at x, y
	String getCell(int x, int y);
	
    // Player successfully cleared all non-mine cells
    void gameWin();
    
    // Player failed (clicked on a mine)
    void gameLose();
}
