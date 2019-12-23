package bowlpickem;

import java.util.HashMap;
import java.util.HashSet;

/**
 * This class represents a driver class for the program.
 */
public class BowlPickEm
{
	static HashMap<Integer, BowlGame> games;
	static HashSet<Player> players = new HashSet<Player>();
	static Player me;

	public static void main(String[] args)
	{
		games = BowlGameFactory.generateBowlGames();
		players = getPlayers();
		Player.setMaxiumPossibleScore(275);

		final int possibilities = 268435456;
		String binaryGames = "000000000000000000000000000000";

		if (binaryGames.length() != 30)
		{
			System.out.println(binaryGames.length());
			System.exit(0);
		}

		long startTime = System.nanoTime();
		System.out.println("Analyzing " + possibilities + " possibilities...");
		float i = 0;
		while (binaryGames.contains("0"))
		{
			if (i == 2684354)
			{
				System.out.println("1%");
				long endTime = System.nanoTime();
				long totalTime = endTime - startTime;
				System.out.println(totalTime);
			}
			// System.out.println(i / possibilities + "%");

			determineWinners(binaryGames, games);
			binaryGames = increment(binaryGames);
			i++;
			// System.out.println(binaryGames);
		}

		long endTime = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println(totalTime);

		for (Player player : players)
		{
			System.out.println(player.name + ": " + player.getScore());
		}
	}

	/**
	 * This method gets the players as a {@link HashSet}. This method takes a
	 * text file and reads all of the players into {@link Player} objects with
	 * their picks.
	 *
	 * @return A {@link HashSet} of the players.
	 */
	private static HashSet<Player> getPlayers()
	{
		System.out.println("Downloading player picks...");
		players = new HashSet<Player>();
		me = new Player("me");
		me.generateMyPicks();
		for (int i = 0; i < 45; i++)
		{
			Player computer = new Player("computer" + i);
			computer.generateRandomPicks(games);
			players.add(computer);
		}
		players.add(me);
		return players;
	}

	private static void determineWinners(String binaryGames, HashMap<Integer, BowlGame> games)
	{
		for (int i = 0; i < binaryGames.length(); i++)
		{
			if (binaryGames.charAt(i) == '0')
			{
				// System.out.println(games.get(i).team1 + " beats " +
				// games.get(i).team2 + " in the "
				// + games.get(i).bowlName + " bowl.");
				me.incrementScore(games.get(i).team1);
			}
			else
			{
				// System.out.println(games.get(i).team2 + " beats " +
				// games.get(i).team1 + " in the "
				// + games.get(i).bowlName + " bowl.");
				me.incrementScore(games.get(i).team2);
			}
		}
		// if (me.getScore() >= Player.maximumPossibleScore)
		// {
		// for (int i = 0; i < binaryGames.length(); i++)
		// {
		// if (binaryGames.charAt(i) == '0')
		// {
		// addScores(games.get(i).team1);
		// }
		// else
		// {
		// addScores(games.get(i).team2);
		// }
		// }
		// }
	}

	/**
	 * This method takes a winning team and increments all players scores who
	 * picked that team.
	 */
	private static void addScores(String teamName)
	{
		for (Player player : players)
		{
			if (player.name != "me")
			{
				player.incrementScore(teamName);
			}
		}
	}

	public static String increment(String binaryString)
	{

		char[] A = binaryString.toCharArray();
		boolean carry = true;
		for (int i = A.length - 1; i >= 0; i--)
		{
			if (carry)
			{
				if (A[i] == '0')
				{
					A[i] = '1';
					carry = false;
				}
				else
				{
					A[i] = '0';
					carry = true;
				}
			}
		}
		return String.valueOf(A);
	}
}
