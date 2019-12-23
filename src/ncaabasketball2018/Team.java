package ncaabasketball2018;

import java.util.ArrayList;

public class Team 
{
	public String teamName;
	public ArrayList<Pick> picks = new ArrayList<Pick>();
	public int initialScore;
	public ArrayList<Score> scores = new ArrayList<Score>();
	
	public Team (String[] lines)
	{
		this.teamName = lines[0];
		
		for (int i = 3; i <= 18; i++)
		{
			String[] tokens = lines[i].split(" +");
			Pick newPick = new Pick(tokens[2], tokens[1]);
			this.picks.add(newPick);
		}
		
		String[] tokens = lines[20].split(" +");
		this.initialScore = Integer.parseInt(tokens[2]);
	}
	
	public void printTeamDetails()
	{
		System.out.println(teamName);
		for (Pick p : this.picks)
		{
			System.out.println(p.schoolName + " with seed " + p.seed);
		}
		System.out.println("With score " + this.initialScore);
	}
}


//21 lines
