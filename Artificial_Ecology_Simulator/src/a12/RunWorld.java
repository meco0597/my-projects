package a12;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.omg.Messaging.SyncScopeHelper;


/***
 * The main application class for the EcoSim. This class loads many of 
 * the resources, builds the GUI, and starts a timer to step through the 
 * simulation.
 * 
 * @author Melvin Bosnjak
 *
 */
public class RunWorld extends JFrame implements ActionListener{
	

	// Store the pixel colors to match in the minimap.
	// Use a packed int 4 Byte representation.
	private static final long serialVersionUID = 1L;
	private static int plainsColor = 0xFF7ECE2E;
	private static int desertColor = 0xFFCECE2E;
	private static int rockyColor = 0xFF272715;
	private static int swampColor = 0xFF0E400E;
	private static int waterColor = 0xFF00B0FF;
	private static int dirtColor = 0xFF755E15;
	
	// References to some UI components
	private Terrain panel;
	private Timer timer;
	private ImagePanel minimapPanel;
	private static JLabel animalCount;
	private static JSlider herbivoreSlider = new JSlider(0, 50, 7);
	private static JSlider carnivoreSlider = new JSlider(0, 50, 2);
	private static JSlider plantSlider = new JSlider(0, 50, 10);
	private JButton startButton;
	private JButton stopButton;


	// The world holds all the data
	private static World world;
	
	/***
	 * Construct the main application. It builds the UI, creates the world with terrain and population, and 
	 * starts a timer to tick the simulation forward.
	 * @param plainsImg
	 * @param desertImg
	 * @param rockyImg
	 * @param swampImg
	 * @param waterImg
	 * @param dirtImg
	 * @param minimapImg
	 */
	public RunWorld(BufferedImage plainsImg, BufferedImage desertImg, BufferedImage rockyImg, BufferedImage swampImg,
			        BufferedImage waterImg, BufferedImage dirtImg, BufferedImage minimapImg) {
		super("World Simulation");
  		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  		this.setPreferredSize(new Dimension(2000, 2000));

  		//Panel that holds the slider, buttons, map etc..
  		JPanel rightPanel = new JPanel();
  		rightPanel.setLayout(new GridLayout(10, 2));
  		
  		// This is the panel that will be the JFrame ContentPane. Add things to this.
  		JPanel overallPanel = new JPanel();
  		
  		// Construct the world tiles
  		Terrain[][] terrain = buildTerrain(plainsImg, desertImg, rockyImg, swampImg, waterImg, dirtImg, minimapImg);
  		
  		// Store the map in the world.
   		world = new World(terrain);
   		
   		// The screen is what draws the tiles and the living things
   		Screen screen = new Screen(world);
   		
   		// Add herbivore slider to the GUI
   		herbivoreSlider.setMajorTickSpacing(10);
   		herbivoreSlider.setPaintTicks(true);
   		herbivoreSlider.setMajorTickSpacing(10);
   		herbivoreSlider.setMinorTickSpacing(1);
   		herbivoreSlider.setPaintLabels(true);
   		JLabel herbivoreSliderLabel = new JLabel("Initial Herbivore Population (x10)", JLabel.CENTER);
        herbivoreSliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
   		// Add Carnivore slider to the GUI
   		carnivoreSlider.setMajorTickSpacing(10);
   		carnivoreSlider.setPaintTicks(true);
   		carnivoreSlider.setMajorTickSpacing(10);
   		carnivoreSlider.setMinorTickSpacing(1);
   		carnivoreSlider.setPaintLabels(true);
   		JLabel carnivoreSliderLabel = new JLabel("Initial Carnivore Population (x10)", JLabel.CENTER);
        carnivoreSliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add the start button
        startButton = new JButton("Start Button");
        startButton.addActionListener(this);
        startButton.setText("Start");
        
        // Add the stop button
        stopButton = new JButton("Start Button");
        stopButton.addActionListener(this);
        stopButton.setText("Stop");
        stopButton.setLocation(1, 1);
        
   		// Add plant slider to the GUI
   		plantSlider.setMajorTickSpacing(10);
   		plantSlider.setPaintTicks(true);
   		plantSlider.setMajorTickSpacing(10);
   		plantSlider.setMinorTickSpacing(1);
   		plantSlider.setPaintLabels(true);
   		JLabel plantSliderLabel = new JLabel("Initial Plant Population (x100)", JLabel.CENTER);
        plantSliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
   		
   		// Add a scrollpane. The size is set to give the scrollbars a little room.
  		JScrollPane scrollPanel = new JScrollPane(screen);
  		int scrollBarWidth = (int) scrollPanel.getVerticalScrollBar().getPreferredSize().getWidth();
  		int scrollBarHeight = (int)scrollPanel.getHorizontalScrollBar().getPreferredSize().getHeight();
  		scrollPanel.setPreferredSize(new Dimension(25*25+scrollBarWidth, 25*20+scrollBarHeight));
  		
  		// adding everything to the GUI
  		overallPanel.add(scrollPanel);
  		minimapPanel = new ImagePanel(minimapImg);
  		rightPanel.add(minimapPanel);
  		RunWorld.animalCount = new JLabel();
  		RunWorld.animalCount.setText("Day: " + world.getDay() + "\tHerbivores: " + world.numHerbivores() + "\tCarnivores: " + world.numCarnivores() + "\tPlants: " + world.numPlants());
  		rightPanel.add(startButton);
  		rightPanel.add(stopButton);
  		rightPanel.add(RunWorld.animalCount);
  		rightPanel.add(herbivoreSliderLabel);
  		rightPanel.add(herbivoreSlider);
  		rightPanel.add(carnivoreSliderLabel);
  		rightPanel.add(carnivoreSlider);
  		rightPanel.add(plantSliderLabel);
  		rightPanel.add(plantSlider);
  		overallPanel.add(rightPanel);
  		setContentPane(overallPanel);
  		
  		
  		// This is the main simulation timer
  		timer = new Timer(100, this);
  		
  		pack();
  		
	}
	
	/***
	 * Gets plant slider value
	 * 
	 * @return Plant Slider Value
	 */
	public static int getPlantSliderValue() {
		return plantSlider.getValue()*100;
	}
	
	/***
	 * Gets herbivore slider value
	 * 
	 * @return Herbivore Slider Value
	 */
	public static int getHerbivoreSliderValue() {
		return herbivoreSlider.getValue()*10;
	}
	
	/***
	 * Gets carnivore slider value
	 * 
	 * @return carnivore Slider Value
	 */
	public static int getCarnivoreSliderValue() {
		return carnivoreSlider.getValue()*10;
	}
	
	/***
	 * Convert the minimap to tiles and store in an array.
	 * @return the 2D array of Terrain tiles
	 */
	private Terrain[][] buildTerrain(BufferedImage plainsImage, BufferedImage desertImage, BufferedImage rockyImage, BufferedImage swampImage,
	        BufferedImage waterImage, BufferedImage dirtImage, BufferedImage minimapImage) {
		Terrain[][] terrain = new Terrain[minimapImage.getHeight()][minimapImage.getWidth()];
		for (int row = 0; row < minimapImage.getHeight(); row++) {
			for (int col = 0; col < minimapImage.getWidth(); col++) {
				int colorVal = minimapImage.getRGB(col,row);
				if (colorVal == plainsColor) {
					panel = new Terrain(plainsImage, 0.25, 2.0, 1.0);
				}
				else if (colorVal == desertColor) {
					panel = new Terrain(desertImage, 0.05, 1.0, 2.0);
				}
				else if (colorVal == rockyColor) {
					panel = new Terrain(rockyImage, 0.4, 0.5, 2.0);
				}
				else if (colorVal == swampColor) {
					panel = new Terrain(swampImage, 0.9, 0.3, 2.0);
				}
				else if (colorVal == waterColor) {
					panel = new Terrain(waterImage, 1.0, 0.1, 3.0);
				}
				else if (colorVal == dirtColor) {
					panel = new Terrain(dirtImage, 0.5, 4.0, 1.0);
				}
				else {
					System.out.println("no match for color " + Integer.toHexString(colorVal));
					System.exit(0);
				}
				terrain[row][col] = panel;
			}
		}
		return terrain;
	}
	
	
	/***
	 * The main simulation clock ticks here
	 */
	public void actionPerformed(ActionEvent e) {
		// starts the simulation 
		if (e.getSource() == startButton) {
			World.initializeHerbivorePopulation(RunWorld.getHerbivoreSliderValue());
			World.initializeCarnivorePopulation(RunWorld.getCarnivoreSliderValue());
			World.initializePlantPopulation(RunWorld.getPlantSliderValue());
			timer.start();
		}
		// Stops the simulation
		if (e.getSource() == stopButton) {
			timer.stop();
		}
		// change the population of the plants and herbivores
		if (e.getSource() == plantSlider || e.getSource() == herbivoreSlider || e.getSource() == carnivoreSlider) {
			JSlider source = (JSlider)e.getSource();
			if (source.getValueIsAdjusting()) {
				if (source == plantSlider) {
					world.initializePlantPopulation((int)source.getValue()*100);
				}
				if (source == herbivoreSlider) {
					world.initializeHerbivorePopulation((int)source.getValue()*100);
				}
				if (source == carnivoreSlider) {
					world.initializeCarnivorePopulation((int)source.getValue()*100);
				}
			}
	    }
		// Enact a step of the Lifeforms' lives.
		world.simulateAll();
  		animalCount.setText("Day: " + world.getDay() + "\tHerbivores: " + world.numHerbivores() + "\tCarnivores: " + world.numCarnivores() + "\tPlants: " + world.numPlants());
  		// Draw the tiles and creatures
		repaint();
	}
	
	/***
	 * The "main" main function which loads some tile resources and
	 * starts the program.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedImage plainsImg = ImageIO.read(new File("images/Plains.png"));
			BufferedImage desertImg = ImageIO.read(new File("images/Desert.png"));
			BufferedImage rockyImg = ImageIO.read(new File("images/Rocky.png"));
			BufferedImage swampImg = ImageIO.read(new File("images/Swamp.png"));
			BufferedImage waterImg = ImageIO.read(new File("images/Water.png"));
			BufferedImage dirtImg = ImageIO.read(new File("images/Dirt.png"));
			BufferedImage minimap = ImageIO.read(new File("images/minimap.png"));
			System.out.println(minimap.getHeight());
			RunWorld si = new RunWorld(plainsImg, desertImg, rockyImg, swampImg, waterImg, dirtImg, minimap);
			si.setVisible(true);
			if (world.getDay()%50 == 0)
	  			animalCount.setText("Day: " + world.getDay() + "\tHerbivores: " + world.numHerbivores() + "\tCarnivores: " + world.numCarnivores() + "\tPlants: " + world.numPlants());
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
