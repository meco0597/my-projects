
package stars;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Melvin Bosnjak, u0989241
 * @author Adrian Bollerslev, u1115021
 * 
 */
public class Black_Hole extends Satellite{

	/**
	 * Inherits from satellite, creates a JComponent that has the characteristics of a Blackhole.
	 */
	public Black_Hole(double d, double saturnDistance, double e, String string) {
		super(d, saturnDistance, 0, 0, e, 10, string);
	}
	
	/**
	 * Overrides the paint component to make a black hole
	 */
	@Override
	public void paintComponent(Graphics g)
    {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
	    g.fillOval(0,0, this.getWidth(),this.getHeight());
	    
    }
	
	/**
	 * Overrides the update display size in satellite to accustom Black Holes
	 */
	@Override
	protected void update_display_size(double radius_of_system) {
		this.setSize((int)radius,(int)radius);
		
	}
}


