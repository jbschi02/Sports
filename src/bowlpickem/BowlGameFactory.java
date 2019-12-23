package bowlpickem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * This class represents a factory for creating {@link BowlGame BowlGames}.
 */
public class BowlGameFactory
{
	private static final String teamsFile = "./teams.txt";

	/**
	 * Reads from a text file in the format of Bowl Name\n Team1 \n Team2. Reads
	 * these lines of a text file in to a map which maps an integer to the bowl
	 * game.
	 *
	 * @return A {@link HashMap} containing the bowl game information.
	 */
	public static HashMap<Integer, BowlGame> generateBowlGames()
	{
		System.out.println("Generating bowl games...");
		HashMap<Integer, BowlGame> bowlGames = new HashMap<Integer, BowlGame>();
		FileReader inputFile;
		try
		{
			inputFile = new FileReader(teamsFile);

			BufferedReader br = new BufferedReader(inputFile);

			String line;
			int i = 0;
			int bowlGameNumber = 0;
			String bowlGameName = "";
			String team1Name = "";
			String team2Name = "";
			while ((line = br.readLine()) != null)
			{
				if (i % 3 == 0)
				{
					bowlGameName = line;
				}
				else if (i % 3 == 1)
				{
					team1Name = line;
				}
				else if (i % 3 == 2)
				{
					team2Name = line;
					bowlGames.put(bowlGameNumber, new BowlGame(bowlGameName, team1Name, team2Name));
					bowlGameNumber++;
				}
				i++;
			}

			inputFile.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return bowlGames;
	}
}
