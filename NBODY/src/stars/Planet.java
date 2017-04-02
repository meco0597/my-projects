

package stars;

import java.awt.Color;
import java.awt.Graphics;

/**
 * @author Melvin Bosnjak, u0989241
 * @author Adrian Bollerslev, u1115021
 */
public class Planet extends Satellite{
	
	/**
	 * Inherits from satellite, creates a JComponent that has the characteristics of a Planet.
	 */
	public Planet(double x, double y, double velocity_x, double velocity_y, double my_mass, double my_radius,
			String name) {
		super(x, y, velocity_x, velocity_y, my_mass, my_radius, name);
	}
	
	/**
	 * Overrides the paintComponent in Satellite to accustom planets
	 * 
	 */
	@Override
	public void paintComponent(Graphics g)
    {
		super.paintComponent(g);
		g.setColor(getForeground());
		g.fillOval(0,0, this.getWidth(),this.getHeight());
    }
	
	/**
	 * Mass getter
	 * @return mass
	 */
	@Override
	public double get_mass() {
		return mass;
	}

	/**
	 * Overrides the update display size in satellite to accustom planets
	 */
	@Override
	protected void update_display_size(double radius_of_system) {
		double tempRadius = radius/radius_of_system *2_500_000;
		this.setSize((int)tempRadius, (int)tempRadius);
	}
}