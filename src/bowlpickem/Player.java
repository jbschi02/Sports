package bowlpickem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * This class represents a human player in a college bowl pick em challenge.
 */
public class Player
{
	public final String name;
	private int score;
	private HashMap<String, Integer> picks;
	private HashSet<String> losers;
	public static int maximumPossibleScore;

	/**
	 * The default constructor for this class.
	 *
	 * @param name The player's team name.
	 */
	public Player(String name)
	{
		this.name = name;
		score = 0;
		picks = new HashMap<String, Integer>();
		addLosers();
	}

	/**
	 * This method adds a team name and a confidence number to a player's picks.
	 */
	public void addPick(String teamName, int confidence)
	{
		picks.put(teamName, confidence);
	}

	/**
	 * This method takes a team name and checks this player's score to see if
	 * they were picked. If they were picked, increment their score by the
	 * confidence point.
	 *
	 * @param teamName The team name.
	 */
	public void incrementScore(String teamName)
	{
		if (picks.containsKey(teamName) && !losers.contains(teamName))
		{
			score += picks.get(teamName);
		}
	}

	/**
	 * This method gets the score of the player.
	 *
	 * @return An int containing the player's score.
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * This method generates 30 random picks. Intended for testing purposes.
	 *
	 * @param games
	 */
	public void generateRandomPicks(HashMap<Integer, BowlGame> games)
	{
		ArrayList<Integer> confidenceScores = new ArrayList<Integer>();
		for (int i = 1; i < 31; i++)
		{
			confidenceScores.add(i);
		}

		for (HashMap.Entry<Integer, BowlGame> entry : games.entrySet())
		{
			Random randWinner = new Random();
			Random randScore = new Random();
			int index = randScore.nextInt(confidenceScores.size());
			int confidence = confidenceScores.get(index);
			confidenceScores.remove(index);
			int winner = randWinner.nextInt(2);
			if (winner == 0)
			{
				picks.put(entry.getValue().team1, confidence);
			}
			else
			{
				picks.put(entry.getValue().team2, confidence);
			}
		}
	}

	/**
	 * This method prints this player's picks.
	 */
	public void printPicks()
	{
		for (HashMap.Entry<String, Integer> pick : picks.entrySet())
		{
			System.out.println(pick.getKey() + ": " + pick.getValue());
		}
	}

	/**
	 * This method creates John Schieman's picks.
	 */
	public void generateMyPicks()
	{
		picks.put("BoiseState", 8);
		picks.put("AppalachianState", 30);
		picks.put("CentralFlorida", 28);
		picks.put("Hawaii", 2);
		picks.put("Miami(FL)", 15);
		picks.put("Pittsburgh", 26);
		picks.put("NorthCarolina", 12);
		picks.put("MichiganState", 4);
		picks.put("OklahomaState", 1);
		picks.put("Iowa", 13);
		picks.put("AirForce", 7);
		picks.put("NotreDame", 20);
		picks.put("PennState", 21);
		picks.put("LSU", 27);
		picks.put("OhioState", 9);
		picks.put("WesternKentucky", 11);
		picks.put("California", 16);
		picks.put("MississippiState", 14);
		picks.put("Florida", 29);
		picks.put("VirginiaTech", 6);
		picks.put("ArizonaState", 17);
		picks.put("Navy", 10);
		picks.put("Wyoming", 22);
		picks.put("Utah", 19);
		picks.put("Alabama", 25);
		picks.put("Auburn", 23);
		picks.put("Oregon", 5);
		picks.put("Georgia", 18);
		picks.put("Cincinnati", 24);
		picks.put("Indiana", 3);
	}

	private void addLosers()
	{
		losers = new HashSet<String>();
		losers.add("BoiseState");
		losers.add("Alabama-Birmingham");
	}

	public static void setMaxiumPossibleScore(int max)
	{
		maximumPossibleScore = Math.max(max, 275);
	}
}
