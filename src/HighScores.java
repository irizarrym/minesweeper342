/*
    Group 60:
    Michael Irizarry (miriza6@uic.edu)
    Nikhil Shankar (nshank3@uic.edu)

    CS 342 - Project 2
    Minesweeper

    High Scores for Minesweeper
*/

public class HighScores
{
    // @path = Path to file containing top ten scores
    public HighScores(String path)
    {
    }
    
    // Number of scores in table
    public int count()
    {
        return 0;
    }
    
    // Return high score (1 to 10)
    public int getScore(int index)
    {
        assert(1 <= index && index <= 10);
        return 0;
    }
    
    // Return name for high score (1 to 10)
    public String getName(int index)
    {
        assert(1 <= index && index <= 10);
        return "";
    }
    
    // Add score to high scores list
    // If time is not in the top ten, do nothing
    public void addScore(String name, int time)
    {
        
    }
    
    // Does @time make it into the top ten?
    public boolean isTopTen(int time)
    {
        return false;
    }
    
    
    
    private final class Score
    {
        String name;
        int time;
    }
}