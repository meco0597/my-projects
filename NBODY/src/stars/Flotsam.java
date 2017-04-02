
package stars;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Melvin Bosnjak, u0989241
 * @author Adrian Bollerslev, u1115021
 */
public class Flotsam extends Satellite {
	
	/**
	 * Inherits from satellite, creates a JComponent that functions as a floating piece of debris
	 */
	public Flotsam(int x, int y, int i, int j) {
		super(x, y, i, j, 1000000000 , 10, "Flotsam");
	}
	
	/**
	 * Overrides the paint component to make a black hole
	 */
	@Override
	public void paintComponent(Graphics g)
    {
		super.paintComponent(g);
		g.setColor(Color.GRAY);
	    g.fillOval(0,0, this.getWidth(),this.getHeight());   
    }
	
	/**
	 *  Overrides the update display size in satellite to accustom Black Holes
	 */
	@Override
	protected void update_display_size(double radius_of_system) {
		this.setSize((int)radius/2,(int)radius/2);
	}
}