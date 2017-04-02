package a12;

import java.util.Random;

public class Carnivore extends Lifeform {

	/***
	 * Make a herbivore at an x,y pixel location
	 * @param _x
	 * @param _y
	 */
	public Carnivore(double _x, double _y) {
		super(_x, _y, 70.0, 600.0, 0.011, 2.0, 10.0);
		// TODO Auto-generated constructor stub
		super.loadSprite("images/Carnivore.png");
	}
	
	/***
	 * Make a child of Carnivore type
	 */
	@Override
	public Lifeform makeChild() {
		Carnivore h =  new Carnivore(this.getLocationX(), this.getLocationY());
		return h;
	}

	/***
	 * A Carnivore can eat a Herbivore prey.
	 */
	@Override
	public boolean canEat(Lifeform prey) {
		if (prey instanceof Herbivore)
			return true;
		// TODO Auto-generated method stub
		return false;
	}
	
	/***
	 * A Carnivore reproduces if it has enough energy and 
	 * has luck.
	 */
	@Override
	public void tryToReproduce(World world) {
		Random rng = new Random();
		if (this.getFoodValue() > 100 && rng.nextDouble() < this.getReproductionRate()) {
			Lifeform l = makeChild();
			world.addToPopulation(l);
			l.useCalories(60);
		}
	}
	
	/***
	 * Some creatures don't hunt, so return that status for efficiency
	 * @return
	 */
	public boolean isHunter() {
		return true;
	}
}
