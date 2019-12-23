package ncaabasketball2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BasketballDriverClass {

	public static void main(String[] args) throws IOException 
	{	
		URL url = null;
		BufferedReader bufferedreader = null;
		System.out.println("Connecting to stats...");
		try {
			url = new URL("http://home.earthlink.net/~sluggoswebpage/ncaac18/teams.htm");
			System.out.println("Connected.");
			bufferedreader = new BufferedReader(new InputStreamReader(url.openStream())); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String line = "";
		int i = 0;
		while ((line = bufferedreader.readLine()) != null && i < 8) 
		{
			i++;
		}

		ArrayList<Team> teams = new ArrayList<Team>();

		while ((line = bufferedreader.readLine()) != null && teams.size() < 133) 
		{
			String[] lines = new String[22];
			i = 0;
			while (i < 21)
			{
				lines[i] = line;
				i++;
				line = bufferedreader.readLine();
			}

	 		Team newTeam = new Team(lines);
	 		teams.add(newTeam);
		}
		teams.get(117).teamName = "Schieman";
		
		//teams.get(100).printTeamDetails();
		
		ArrayList<String> aliveTeams = new ArrayList<>();
		
		//aliveTeams.add("Kansas State");
		//aliveTeams.add("Kentucky");
		
		aliveTeams.add("Loyola-Chicago");
		//aliveTeams.add("Nevada");
		
		//aliveTeams.add("Florida");
		//aliveTeams.add("Gonzaga");
		
		aliveTeams.add("Michigan");
		//aliveTeams.add("Texas");
		
		aliveTeams.add("Villanova");
		//aliveTeams.add("West");
		
		//aliveTeams.add("Texas Tech");
		//aliveTeams.add("Purdue");
		
		aliveTeams.add("Kansas");
		//aliveTeams.add("Clemson");
		
		//aliveTeams.add("Syracuse");
		//aliveTeams.add("Duke");
		
		
		String binary = "000"; // 15 for SS, 7 for EE, 3 for FF, 1 for championship
		ArrayList<String> binaryCodes = new ArrayList<String>();
		binaryCodes.add(binary);
		for (i = 0; i < 7; i++) //32767, 127, 7, 1, 
		{
			binary = BinaryIncrementer.increment(binary);
			binaryCodes.add(binary);
		}
		
		for (String s : binaryCodes)
		{
			//System.out.println(s);
		}
		
		System.out.println("Calculating all possible scores...");
		for (String s : binaryCodes)
		{
			BinaryIncrementer.parseBinaryCode(aliveTeams, teams, s);
		}
		
		System.out.println("Schieman wins at....");
		PrintWriter writer = new PrintWriter("wins4.txt", "UTF-8");
		writer.println("hello");
		for (i= 0; i <= 7; i++)
		{
			System.out.println(i);
			int schiemanScore = teams.get(117).scores.get(i).score;
			String schiemanBinary = teams.get(117).scores.get(i).binaryCode;
			boolean schiemanWins = true;
			int place = 1;
			for (Team t : teams)
			{
				for (Score score : t.scores)
				{
					if (score.binaryCode.equals(schiemanBinary))
					{
						if (score.score > schiemanScore)
						{
							//System.out.println(t.teamName + " beats schieman at " + schiemanBinary + " with score " + score.score + " to " + schiemanScore);
							place++;
							schiemanWins = false;
						}
					}
				}
			}
			if (schiemanWins)
			{
				writer.println(schiemanBinary);
				System.out.println(schiemanBinary);
			}
			if (place == 2)
			{
				writer.println("2nd place " + schiemanBinary);
			}
			else if (place == 3)
			{
				writer.println("3rd place " + schiemanBinary);
			}
		}
		
		writer.close();
		System.out.println("done");
		/*for (Score s : teams.get(117).scores)
		{
			System.out.println(s.binaryCode + " " + s.score);
		}*/
		//BinaryIncrementer.printWinningTeams("11", aliveTeams);
		//teams.get(117).printTeamDetails();
		//System.out.println(BinaryIncrementer.counter);
	}

}
