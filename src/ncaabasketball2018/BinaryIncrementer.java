package ncaabasketball2018;

import java.util.ArrayList;

public class BinaryIncrementer 
{
	public final static int roundNumber = 2; //sweet sixteen = 8 elite eight = 4 final four = 2 championship = 1
	public final static String binaryString = "00"; //sweet sixteen = 00000000 elite eight = 0000 final four = 00 championship = 0
	public static int counter = 0;
	
	public static void parseBinaryCode(ArrayList<String> aliveTeams, ArrayList<Team> teams, String binaryCode) 
	{
		String permanentBinary = binaryCode;
		ArrayList<String> permanentTeams = new ArrayList<String>();
		permanentTeams = new ArrayList<String>(aliveTeams);
		for (Team t : teams)
		{
			binaryCode = permanentBinary;
			aliveTeams = new ArrayList<String>(permanentTeams);
			int runningTotal = 0;
			ArrayList<String> newAliveTeams = new ArrayList<String>();
			if (roundNumber > 4)
			{
				for(int i = 0; i < 8; i++)
				{
					if (binaryCode.charAt(i) == '0')
					{
						//System.out.println(aliveTeams.get(i * 2) + " beats " + aliveTeams.get((i * 2) + 1));
						runningTotal = addScore(t, aliveTeams.get(i * 2), 8, binaryCode, runningTotal);
						newAliveTeams.add(aliveTeams.get(i * 2));
					}
					else
					{
						//System.out.println(aliveTeams.get((i * 2) + 1) + " beats " + aliveTeams.get(i * 2));
						runningTotal = addScore(t, aliveTeams.get((i * 2) + 1), 8, binaryCode, runningTotal);
						newAliveTeams.add(aliveTeams.get((i * 2) + 1));
					}
				}
				binaryCode = binaryCode.substring(8);
				aliveTeams = new ArrayList<String>(newAliveTeams);
			}
			if (roundNumber > 2)
			{
				for(int i = 0; i < 4; i++)
				{
					if (binaryCode.charAt(i) == '0')
					{
						//System.out.println(aliveTeams.get(i * 2) + " beats " + aliveTeams.get((i * 2) + 1));
						runningTotal = addScore(t, aliveTeams.get(i * 2), 4, binaryCode, runningTotal);
						newAliveTeams.add(aliveTeams.get(i * 2));
					}
					else
					{
						//System.out.println(aliveTeams.get((i * 2) + 1) + " beats " + aliveTeams.get(i * 2));
						runningTotal = addScore(t, aliveTeams.get((i * 2) + 1), 4, binaryCode, runningTotal);
						newAliveTeams.add(aliveTeams.get((i * 2) + 1));
					}
				}
				binaryCode = binaryCode.substring(4);
				aliveTeams = new ArrayList<String>(newAliveTeams);
			}
			if (roundNumber > 1)
			{
				for(int i = 0; i < 2; i++)
				{
					if (binaryCode.charAt(i) == '0')
					{
						//System.out.println(aliveTeams.get(i * 2) + " beats " + aliveTeams.get((i * 2) + 1));
						runningTotal = addScore(t, aliveTeams.get(i * 2), 2, binaryCode, runningTotal);
						newAliveTeams.add(aliveTeams.get(i * 2));
					}
					else
					{
						//System.out.println(aliveTeams.get((i * 2) + 1) + " beats " + aliveTeams.get(i * 2));
						runningTotal = addScore(t, aliveTeams.get((i * 2) + 1), 2, binaryCode, runningTotal);
						newAliveTeams.add(aliveTeams.get((i * 2) + 1));
					}
				}
				binaryCode = binaryCode.substring(2);
				aliveTeams = new ArrayList<String>(newAliveTeams);
			}
			
			
			if (binaryCode.charAt(binaryCode.length() - 1) == '0')
			{
				//System.out.println(aliveTeams.get(i * 2) + " beats " + aliveTeams.get((i * 2) + 1));
				runningTotal = addScore(t, aliveTeams.get(0), 1, binaryCode, runningTotal);
			}
			else
			{
				//System.out.println(aliveTeams.get((i * 2) + 1) + " beats " + aliveTeams.get(i * 2));
				runningTotal = addScore(t, aliveTeams.get(1), 1, binaryCode, runningTotal);
			}
			
			t.scores.add(new Score(runningTotal + t.initialScore, permanentBinary));
		}
	}

	private static int addScore(Team team, String winningTeam, int roundNumber, String binaryCode, int runningTotal) 
	{
		for (Pick p : team.picks)
		{
			if (p.schoolName.equals(winningTeam))
			{
				int multiplier = 0;
				if (roundNumber == 8)
				{
					multiplier = 4;
				}
				else if (roundNumber == 4)
				{
					multiplier = 8;
				}
				else if (roundNumber == 2)
				{
					multiplier = 16;
				}
				else
				{
					multiplier = 32;
				}
				runningTotal += multiplier * p.seed;
			}
		}
		return runningTotal;
	}

	public static String increment(String binaryString) 
	{

		char[] A = binaryString.toCharArray();
	    boolean carry = true;
	    for (int i = (A.length - 1); i >= 0; i--) 
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
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void allGameCombinations(int gamesInRound, String bin, ArrayList<String> aliveTeams, Team team, String binaryCode)
	{
		do
		{
			printAndIncrementBinary(bin, gamesInRound, aliveTeams, team, binaryCode, false);
			bin = increment(bin);
		} while (!(allGamesHaveBeenSimulated(bin, gamesInRound)));
		
		printAndIncrementBinary(bin, gamesInRound, aliveTeams, team, binaryCode, true);
	}

	private static void printAndIncrementBinary(String bin, int gamesInRound, ArrayList<String> aliveTeams, Team team, String binaryCode, boolean isLastRound) 
	{
		ArrayList<String> newAliveTeams = new ArrayList<String>();
		newAliveTeams = printWinningTeams(bin, aliveTeams);
		
		if (bin.length() != 1)
		{
			int newGames = gamesInRound / 2;	
			String newBinary = "00000000";
			binaryCode += newBinary.substring(0, newGames);
			allGameCombinations(newGames, newBinary.substring(0, newGames), newAliveTeams, team, binaryCode);
		}
		else System.out.println(binaryCode);;
	}

	public static ArrayList<String> printWinningTeams(String bin, ArrayList<String> aliveTeams) 
	{
		ArrayList<String> newAliveTeams = new ArrayList<String>();
		for (int i = 0; i < bin.length(); i++)
		{
			if (bin.charAt(i) == '0')
			{
				//System.out.println(aliveTeams.get(i * 2) + " beats " + aliveTeams.get((i * 2) + 1));
				newAliveTeams.add(aliveTeams.get(i * 2));
			}
			else
			{
				//System.out.println(aliveTeams.get((i * 2) + 1) + " beats " + aliveTeams.get(i * 2));
				newAliveTeams.add(aliveTeams.get((i * 2) + 1));
			}
		}
		return newAliveTeams;
	}
	
	private static boolean allGamesHaveBeenSimulated(String bin, int gamesInRound) 
	{
		for (int i = bin.length() - 1; i >= 0; i--)
		{
			if (bin.charAt(i) == '0')
			{
				return false;
			}
		}
		return true;
	}

}
