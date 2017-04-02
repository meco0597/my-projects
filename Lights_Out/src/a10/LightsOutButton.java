package a10;

import javax.swing.JButton;

public class LightsOutButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	private int row;
	private int column;
	
//	Constructed using row and column to find them
	public LightsOutButton(int _row, int _column) {
		row = _row;
		column = _column;
	}

// Helps get the values for the button position
	public String getName() {
		return "" + row + column;
		
	}
}