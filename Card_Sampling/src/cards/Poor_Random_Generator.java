package cards;

/**
 * This method is a poor random generator because it uses the nano time which is
 * not random...
 * 
 * @author Melvin Bosnjak
 * @author Britton Gaul
 */
public class Poor_Random_Generator implements Random_Generator
{
	long seed;
	long const1;
	long const2;

	public int next_int(int max)
	{
		long time = System.nanoTime();
		return (int) (time % max);
	}

	public void set_seed(long _seed)
	{
		this.seed = _seed;
	}

	public void set_constants(long _const1, long _const2)
	{
		this.const1 = _const1;
		this.const2 = _const2;
	}

}
