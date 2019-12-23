package ncaabasketball2018;

public class Pick 
{
	public String schoolName;
	public int seed;
	
	public Pick(String sn, String s)
	{
		this.schoolName = sn;
		this.seed = Integer.parseInt(s);
		if (seed == 9 && schoolName.equals("Kansas"))
		{
			schoolName += " State";
		}
		if (seed == 3 && schoolName.equals("Texas"))
		{
			schoolName += " Tech";
		}
	}
}
