package cards;

/**
 * @author Melvin Bosnjak
 * @author Britton Gaul
 */
public class Better_Random_Generator implements Random_Generator
{
	private long scalar;
	private long adder;
	private long seed;
	
	private static final long mask = (1L << 48) - 1;
	
	/**
	 * Constructor sets the constants to what they need to be
	 */
	public Better_Random_Generator()
	{
		this.scalar = 25214903917L;
		this.adder = 11;
	}
	
	@Override
	public int next_int(int max) 
	{
		seed = (scalar * seed + adder) & mask;
		return (int) (seed % max);
	}

	@Override
	public void set_seed(long seed) 
	{
		this.seed = seed;
	}

	@Override
	public void set_constants(long const1, long const2) 
	{
		this.scalar = const1;
		this.adder = const2;
	}

}
