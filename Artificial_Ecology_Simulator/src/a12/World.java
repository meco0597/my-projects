package a12;

import java.util.ArrayList;
import java.util.Random;

/***
 * The World class holds most important data for the simulation.
 * The Terrain array holds the terrain tiles, which have the images as well as 
 * modifier values for the map.
 * 
 * The population is a class an ArrayList of Lifeforms, which is the abstract super class
 * for all life types, so all living things are in population.
 * @author dejohnso
 *
 */
public class World {
	private Terrain [][] terrain; // a reference to terrain
	private static ArrayList<Lifeform> population; // all living things
	private static int widthInPixels; // We need pixel dimensions to handle the drawing of living things
	private static int heightInPixels;
	private int day;
	
	/***
	 * The World holds the terrain and living things and is responsible for
	 * actually doing the simulation on the population of living things.
	 * @param _terrain
	 */
	public World(Terrain[][] _terrain) {
		terrain = _terrain;
		widthInPixels = getTerrainCols() * terrain[0][0].getWidth(); // Use the width of a tile * number of cols
		heightInPixels = getTerrainRows() * terrain[0][0].getHeight(); // Height of a tile * number of rows.
		System.out.println("map pixel width " + widthInPixels + " height " + heightInPixels);
		day = 0;
	}
	
	/***
	 * Getter for world pixel width.
	 * @return width in pixels
	 */
	public int getWorldWidthInPixels() {
		return widthInPixels;
	}

	/***
	 * Getter for world pixel height.
	 * @return height in pixels
	 */
	public int getWorldHeightInPixels() {
		return heightInPixels;
	}
	
	/***
	 * Getter for world pixel height.
	 * @return height in pixels
	 */
	public int getDay() {
		return day;
	}
	
	/***
	 * Convert between pixel x coordinate and terrain tile column index.
	 * @param x position in pixels
	 * @return tile column index
	 */
	public int worldPixelXToColumn(double x) {
		return (int)(x/terrain[0][0].getWidth());
	}

	/***
	 * Convert between pixel y coordinate and terrain tile row index.
	 * @param y position in pixels
	 * @return tile row index
	 */
	public int worldPixelYToRow(double y) {
		return (int)(y/terrain[0][0].getHeight());
	}

	/***
	 * Get the number of rows of terrain tiles
	 * @return terrain rows
	 */
	public int getTerrainRows() {
		return terrain.length;
	}
	
	/***
	 * Get the number of columns of terrain tiles
	 * @return terrain cols
	 */
	public int getTerrainCols() {
		return terrain[0].length;
	}
	
	/***
	 * Access a single tile out of the map
	 * @param row 
	 * @param col
	 * @return a reference to the tile
	 */
	public Terrain getTerrain(int row, int col) {
//		System.out.println("row " + row + " col " + col);
		return terrain[row][col];
	}
	
	/**
	 * Initializes the Herbivore population for the slider.
	 * 
	 * @param pop
	 */
	public static void initializeHerbivorePopulation(int pop) {
		population = new ArrayList<Lifeform>();
		Random rng = new Random();
		for (int i = 0; i < pop; i++) {
			Herbivore h = new Herbivore(rng.nextDouble()*heightInPixels, rng.nextDouble()*widthInPixels);
			population.add(h);
		}
	}
	
	/**
	 * Initializes the Carnivore population for the slider.
	 * 
	 * @param pop
	 */
	public static void initializeCarnivorePopulation(int pop) {
		Random rng = new Random();
		for (int i = 0; i < pop; i++) {
			Carnivore c = new Carnivore(rng.nextDouble()*heightInPixels, rng.nextDouble()*widthInPixels);
			population.add(c);
		}
	}
	
	/**
	 * Initializes the Plant population for the slider.
	 * 
	 * @param pop
	 */
	public static void initializePlantPopulation(int pop) {
		Random rng = new Random();
		for (int i = 0; i < pop; i++) {
			Plant p = new Plant(rng.nextDouble()*heightInPixels, rng.nextDouble()*widthInPixels);
			population.add(p);
		}
	}
	
	/***
	 * Add a new Lifeform to the population. The new creature is moved randomnly
	 * from the current location.
	 * @param l
	 */
	public void addToPopulation(Lifeform l) {
		l.offset(60.0, widthInPixels-1, heightInPixels-1);
		population.add(l);
	}
	
	/***
	 * Getter for the size of the population
	 * @return population size
	 */
	public static int getPopulationSize() {
		return population == null ? 0 : population.size();
	}
	
	/***
	 * Get a reference to the creature at an index
	 * @param index
	 * @return
	 */
	public Lifeform getLifeformAtIndex(int index) {
		return population.get(index);
	}
	
	/***
	 * Remove a Lifeform from the population (it has died).
	 * @param index
	 */
	public void removeLifeformAtIndex(int index) {
		population.remove(index);
	}
	
	/***
	 * Hunt for other creatures to eat. Success in hunting depends on:
	 * whether it can eat that kind of creature and if it is nearby.
	 * @param h
	 */
	public void hunt(Lifeform h) {
		int i = 0;
		while (i < getPopulationSize()) {
			Lifeform otherLife = getLifeformAtIndex(i);
		
			if (h != otherLife && h.canEat(otherLife) && h.isNear(otherLife, 50.0)) {
				h.eat(otherLife.getFoodValue());
//				System.out.println("Eating " + i);
				removeLifeformAtIndex(i);
				break;
			}
			i++;
		}
	}

	
	/***
	 * Return the number of plant typs
	 * @return
	 */
	public static int numPlants() {
		int numPlants = 0;
		for (int i = 0; i < getPopulationSize(); i++) {
			if (population.get(i) instanceof Plant)
				numPlants++;
		}
		return numPlants;
	}

	/***
	 * Return the number of Herbivores
	 * @return
	 */
	public static int numHerbivores() {
		int numHerbivores = 0;
		for (int i = 0; i < getPopulationSize(); i++) {
			if (population.get(i) instanceof Herbivore)
				numHerbivores++;
		}
		return numHerbivores;
	}

	/***
	 * Return the number of Herbivores
	 * @return
	 */
	public static int numCarnivores() {
		int numCarnivores = 0;
		for (int i = 0; i < getPopulationSize(); i++) {
			if (population.get(i) instanceof Carnivore)
				numCarnivores++;
		}
		return numCarnivores;
	}
	
	/***
	 * This is the main simulation method. It goes through the population and
	 * ages them, uses up food reserves, hunts for food, and reproduces.
	 */
	public void simulateAll() {
		int i = 0;
		while ( i < getPopulationSize()) {
			Lifeform l = population.get(i);
			
			l.tryToReproduce(this);

			// Move based on the terrain it is on
			Terrain tile = getTerrain(this.worldPixelYToRow(l.getLocationY()), this.worldPixelXToColumn(l.getLocationX()));
			l.move(tile.getSpeedModifier(),widthInPixels-1, heightInPixels-1);

			// Only hunt if need food
			if (l.getFoodValue() < 300.0)
				hunt(l);
			
			l.incrementAge(1.0);
			l.useCalories();
			
			// Remove if it dies
			if (l.tooOld() || l.starve()) {
				population.remove(i);
			}
			else
				i++; // if we had deleted a lifeform, then i should not change
			
			
		}
		day++;
		if (day%50 == 0)
			// Dump values in csv format that can be made into a graph easily
			System.out.println("" + day + "\t" + numHerbivores() + "\t" + numCarnivores() + "\t" + numPlants());
	}

}
