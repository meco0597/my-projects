
package stars;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 * @author Melvin Bosnjak, u0989241
 * @author Adrian Bollerslev, u1115021
 */
public class Star extends Satellite{
	

	public boolean selected;
	
	/**
	 * Inherits from satellite, creates a JComponent that has the characteristics of a Star.
	 */
	public Star(double x, double y, double velocity_x, double velocity_y, double my_mass, double my_radius,
			String name) {
		super(x, y, velocity_x, velocity_y, my_mass, my_radius, name);
	}
	
	/**
	 * Overrides the paint component to make a star, also changes the colors when supernova is called.
	 */
	@Override
	public void paintComponent(Graphics g)
    {
		if(!selected){
			super.paintComponent(g);
			g.setColor(getForeground());
			g.fillOval(0, 0, this.getWidth(), this.getHeight());
		}
		else{
			// creates random RGB colors closer to red for supernova
			super.paintComponent(g);
			Random rand = new Random();
			float r = rand.nextFloat();
			float g2 = rand.nextFloat() / 2f;
			float b = rand.nextFloat() / 2f;
			g.setColor(new Color(r, g2, b));
			g.fillOval(0, 0, this.getWidth(), this.getHeight());
			
		}
    }
	
	/**
	 * Getter for the mass
	 * @return mass
	 */
	@Override
	public double get_mass() {
		return mass;
	}
	
	/**
	 * triggers when the sun is clicked, the sun goes into "SUPERNOVA DISCO."
	 */
	public void super_nova(){
		// A little added creativity to the project
		String message = "\"Unfortunately\"\n"
		        + "the time has come...\n";
		JOptionPane.showMessageDialog(new JFrame(), message, "WARNING",
		        JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(new JFrame(), "SUPERNOVA DISCO", "DANCE",
		        JOptionPane.ERROR_MESSAGE);
		selected = true;
	}

	/**
	 * Overrides the update display size in satellite to accustom Black Holes
	 */
	@Override
	protected void update_display_size(double radius_of_system) {
		double temp = Math.sqrt(radius)/radius_of_system*50_000_000;
		this.setSize((int)temp,(int)temp);
	}
}