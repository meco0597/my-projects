package a10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * This class creates a GUI that starts the Lights Out Game.
 * 
 * @author Melvin Bosnjak
 */
public class LightsOut extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	// Used 2D arrays to make things easier to locate.
		private JButton[][] buttons;
		private boolean[][] board;
		private ArrayList<LightsOutButton> buttonLocation;
		private boolean mode;
		private int counter;
		private JLabel clickCount;
	
	
	public static void main(String[] args) {
		LightsOut lightsout = new LightsOut();
		lightsout.setLocationRelativeTo(null);
		lightsout.setVisible(true);
	}
	

	// The GUI. Looks and feel of the game.
	public LightsOut() {
		try {
			UIManager.setLookAndFeel(UIManager
					.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Lights Out");
		this.board = new boolean[5][5];
		this.buttons = new JButton[5][5];

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		JPanel top = new JPanel();
		top.setLayout(new GridLayout(5, 5));

		//Sets the buttons and adds the action listener
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				this.buttons[i][j] = new JButton();
				this.buttons[i][j].setName(i*10 + j + "");
				top.add(this.buttons[i][j]);
				this.buttons[i][j].addActionListener(this);
				this.buttons[i][j].setBackground(Color.black);
			}
		}

		//Keeps track of the buttons selected
		setButtonLocation(new ArrayList<LightsOutButton>());  

		mainPanel.add(top, "Center");
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(1, 3));
		
		//make the reset button, click count and solver and add them to the GUI/actionListener
		JButton resetButton = new JButton("Reset");
		JButton solver = new JButton("solve");
		clickCount = new JLabel("Number of moves: " + counter);
		bottom.add(clickCount);
		bottom.add(solver);
		solver.addActionListener(this);
		solver.setName("Solve");
		resetButton.setName("randomize");
		resetButton.addActionListener(this);
		bottom.add(resetButton);
		bottom.setVisible(true);
		mainPanel.add(bottom, "South");

		setContentPane(mainPanel);
		setSize(600, 600);
		randomize();
	}

	/** 
	 * This makes a normal move in the game, and increments clickCount.
	 * 	
	 * @param JButton b
	 */
	private void normalMove(JButton b) {
			toggleButton(b);
			
			int above = Integer.parseInt(b.getName()) - 10;
			int below = Integer.parseInt(b.getName()) + 10;
			int left = Integer.parseInt(b.getName()) - 1;
			int right = Integer.parseInt(b.getName()) + 1;
			if (above >= 0) {
				int x = above / 10;
				int y = above % 10;
				toggleButton(this.buttons[x][y]);
			}
			if (below <= 44) {
				int x = below / 10;
				int y = below % 10;
				toggleButton(this.buttons[x][y]);
			}
			if ((left % 10 >= 0) && (left % 10 <= 4)) {
				int x = left / 10;
				int y = left % 10;
				toggleButton(this.buttons[x][y]);
			}
			if (right % 10 <= 4) {
				int x = right / 10;
				int y = right % 10;
				toggleButton(this.buttons[x][y]);
			}
			//increments the clickCounter and writes it on the label
			this.counter++;
			this.clickCount.setText("Number of moves: " + counter);
		}
		
	
	/**
	 * Tells the program what to do when a certain button is clicked.	
	 * 
	 * @param ActionEvent e
	 */
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getName().equals("Solve")) {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					this.board[i][j] = false;
					this.buttons[i][j].setBackground(Color.black);
				}
			}
			if (isWin()) {
				JFrame frame = new JFrame("Message Dialog");
				JOptionPane.showMessageDialog(frame, "Lights out!");
			}
		}
			else if (b.getName().equals("randomize")) {
			randomize();
		} else if (this.mode) {
				toggleButton(b);
			} else {
				normalMove(b);
			}
			if (isWin()) {
				JFrame frame = new JFrame("Message Dialog");
				JOptionPane.showMessageDialog(frame,
						"Lights out, You Won in " + counter + " moves!");
			}
		}

	
	/**
	 * Method for the reset button, does a random set of moves from a full black board.	
	 * 
	 */
	private void randomize() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				this.board[i][j] = false;
				this.buttons[i][j].setBackground(Color.black);
			}
		}
		Random rand = new Random();
		for (int x = 0; x < 32; x++) {
			int r = rand.nextInt(4);
			int c = rand.nextInt(4);
			toggleButton(this.buttons[r][c]);
		}
		this.counter = 0;
		this.clickCount.setText("Number of moves: " + counter);
	}
	
	
	/**
	 * Changes the background color when a button is pushed.
	 *
	 * @param Jbutton b
	 */
	private void toggleButton(JButton b) {
		int buttonVal = Integer.parseInt(b.getName());
		int i = buttonVal / 10;
		int j = buttonVal % 10;
		if (this.board[i][j] == true) 
		{
			this.board[i][j] = false;
			b.setBackground(Color.black);
		} else 
		{
			this.board[i][j] = true;
			b.setBackground(Color.white);
		}
	}
	
	
	/**
	 * Returns true or false whether the user has completed the game or not.
	 * 
	 * @return boolean
	 */
	private boolean isWin() {
		int lightsOff = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (this.board[i][j] == false) {
					lightsOff++;
				}
			}
		}
		if (lightsOff == 25) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the value in the ArrayList
	 * 
	 * @return LightsOutButton
	 */
	public ArrayList<LightsOutButton> getButtonLocation() {
		return buttonLocation;
	}

	/**
	 * Sets the value in the ArrayList
	 * 
	 * @param buttonLocation
	 */
	public void setButtonLocation(ArrayList<LightsOutButton> buttonLocation) {
		this.buttonLocation = buttonLocation;
	}
}
