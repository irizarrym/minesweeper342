
import java.io.*;

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
	File Scores;
    // @path = Path to file containing top ten scores
    public HighScores(String path)
    {																					//Ex. File
																				    	// Name Score Time
																					    // Nikhil 100 0.12
																						// Michael 200 100
    	if(!Scores.exists();){ 
    		Scores.createNewFile();
    		Scores.write("Name Score Time");
    	}
    	else 
    		Scores.toPath("Scores.txt");
    	
    }
    											
    // Number of scores in table
    public int count()
    {
    	Scores.readLine(); //Discard first line 		
    	int count=0;
    	while (Scores.readLine()) count++; //maybe .readLine doesn't exist
        return count;
    }
    
    // Return high score (1 to 10)
    public int getScore(int index)
    {
        assert(1 <= index && index <= 10);
        Scores.readLine(); //Discard first line
        
        for(int i=1; i<=index-1; i++) Scores.readLine();
        
        String line = Scores.readLine();
        
        int begin = line.indexOf(' ');
        
        int end = line.indexOf(' ', begin);
        
        return Scores.substring(y,z); //Return String of first ' ' to second ' ' i.e. Score
    }
    
    // Return name for high score (1 to 10)
    public String getName(int index)
    {
        assert(1 <= index && index <= 10);
        
        for(int i=1; i<=index-1; i++) Scores.readLine();
        
        String line = Scores.readLine();
        
        int end = line.indexOf(' ');
        
        return Scores.substring(end);
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
