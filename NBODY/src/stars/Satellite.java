package stars;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * @author Melvin Bosnjak, u0989241
 * @author Adrian Bollerslev, u1115021
 * 			
 *         Satellites are bodies that float around each other pulling at each
 *         other with gravity. The mass of an object determines how much gravitational
 *         force it applies to others.
 * 
 *
 *         Most of the methods below are labeled ABSTRACT... It is up to you to
 *         keep this labeling (and thus implement the functions in the children
 *         classes), or to remove the abstract label and implement the method
 *         for EVERY child, or two implement it here, but override it in specific children classes
 *         
 *         At the same time you are writing this class, you should consider writing specific children
 *         classes such as Planet, Star, or Flotsam. Also feel free to add comets, saucer, etc., but
 *         don't do so until the main program is itself working.
 *
 *         Finally, if you haven't implemented and testing your Geometry Vector code, any changes here
 *         will be in vain, as all math should use it.
 */

public abstract class Satellite extends JComponent
{

	/**
	 * Any data that is common to all heavenly bodies should go here.
	 * 
	 * It is very likely that your Satellite will need to know _at least_ the following:
	 * 
	 * 
	 *     1) it's location (in x,y)
	 *     2) it's velocity (how fast it is moving in x,y)
	 *     3) it's mass
	 * 
	 *
	 * Please note that if a PLANET (which ISA Satellite) needs some specific data
	 * it would NOT go here, but instead in child class. 
	 * 
	 * Note: Unlike the previous Circle Program, you _will_ need a location
	 * point for the Satellite separate from the JComponent.  (JComponent x,y are integers
	 * and our simulation needs to use doubles. See the update_screen_coordinates method
	 * below which has the job of converting between Simulation x,y and screen x,y)
	 * 
	 * To make it easier to user your vector class, I suggest that the location be a vector as
	 * well as the velocity
	 *
	 */
	Geometry_Vector location;
	Geometry_Vector velocity;
	double mass;
	double radius;
	String name;
	
	
	/**
	 * This serialVersionUID is there to keep the compiler happy.  We don't have plans for
	 * future versions... but perhaps we will
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * helps with debugging. Returns the needed information of each instance of satellite
	 */
	public String toString()
	{
		return "Location: " + location.toString() + " Velocity: " + velocity.toString() + " Mass: " + this.mass + " Radius: " + this.radius + " Name: " + this.name;
	}

	/**
	 * If necessary you can create this constructor
	 * 
	 * @param x            - where (in 2D) we are in the solar system
	 * @param y            - where (in 2D) we are in the solar system
	 * @param velocity_x   - how fast in 2D we are moving in meters per second
	 * @param velocity_y   - how fast in 2D we are moving in meters per second
	 * @param mass         - how big we are (in kilograms)
	 * 
	 */
	public Satellite (double x, double y, double velocity_x, double velocity_y,
	        double my_mass, double my_radius,
	         String name)
	{
		this.location = new Geometry_Vector(x, y);
		this.velocity = new Geometry_Vector(velocity_x, velocity_y);
		this.mass = my_mass;
		this.radius = my_radius;
		this.name = name;
		this.setSize((int)radius,(int)radius);
	}
	
	/**
	 * Change position of this object by adding this object's velocity to this object's position.
	 *
	 * @param dt the time constant
	 */
	public void update_position( double dt ) {
		Geometry_Vector newVelocity = new Geometry_Vector(velocity);
		newVelocity.multiply_me_by(dt);
		this.location.add_to_me(newVelocity);
	}

	/**
	 *
	 * change our velocity based on acceleration vector (the force applied on us) multiplied
	 * by the delta (time step) 
	 * 
	 * @param acceleration  - a vector of the force being exerted on us
	 */
	public void update_velocity( Geometry_Vector acceleration, double dt ) {
		acceleration.multiply_me_by(dt);
		this.velocity.add_to_me(acceleration);
	}
	
	/**
	 * getter for name
	 * @return name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 *
	 *
	 * As previously stated, the location of the Satellite in the simulation is not the same as the pictures
	 * location in our GUI. For example, the earth might be at: x,y --> 149600000, 0
	 * but on the GUI the earth is displayed at 300,300 
	 * 
	 * In this method, transform the current objects position from WORLD coordinates into GUI coordinates x,y.
	 * 
	 * In order to do this we have to know our relation to the center of the solar system and how wide the solar
	 * system is. We also have to know how wide the GUI window is.
	 * 
	 * Note: The first thing to in an update_screen_coordinates method would be to update the display size of 
	 * the object. the last thing you would do is set the location of the object.
	 * 
	 * Abstract Method Choice:
	 *    Once again, if you believe the math to transform from one x,y to another is different for different
	 *    objects, then you should leave this method abstract and let each child class implement it.
	 *    
	 *    If you believe the math is the same for all objects, you should remove the "abstract" keyword and
	 *    write the code here.
	 *    
	 *  Math: to start try the following:  divide the simluation X,Y by say 1,000,000.  This should transform
	 *        the positions into something reasonable, (e.g., the earth becomes 149,0)  Then simply use this
	 *        vale.
	 *        
	 *        The planets (assuming everything else is correct) will now rotate around the 0,0 location
	 *        of the GUI (the upper left hand corner).  By then adding half the width of the screen/height of the
	 *        screen to this number (hard code to say 500,500) the planets will rotate around 500, 500.
	 *        
	 *        Once you have this working, try and make it work for any size screen
	 *
	 */
	public void update_screen_coordinates(Geometry_Vector system_center, double system_radius, int window_width, int window_height ) {
		this.update_display_size(system_radius);
		Geometry_Vector simCoords = new Geometry_Vector(this.location);
		
		// converts the simulation coordinates into GUI coordinates
		simCoords.divide_by(system_radius);
		simCoords.multiply_me_by(window_width/2);
		int screenX = (window_width/2)-(this.getWidth()/2);
		int screenY = window_height/2-(this.getHeight()/2);
		
		//finally set the location with the new coordinates
		this.setLocation(screenX+(int)simCoords.x, screenY+(int)simCoords.y);
	}

	/**
	 *
	 * Abstract Method Choice: if the mass of an object is a common property to all satellites, then
	 * define this getter here.  If the (property) mass of an object is different across various objects,
	 * leave this function abstract.  
	 *
	 * @return our mass
	 */
	public double get_mass() {
		return this.mass;
	}

	/**
	 *
	 * Abstract Method Choice: does what type we are define what are position is?
	 *
	 * @return our position
	 */
	public Geometry_Vector get_position() {
		return this.location;
	}

	/**
	 *
	 * Abstract Method Choice: You can implement here or in the children, as appropriate
	 *
	 * @return our velocity
	 */
	public Geometry_Vector get_velocity() {
		return this.velocity;
	}
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
	}

	
	/**
	 *
	 * based on our physical size, change the gui component to be large enough to show itself....
	 * 
	 * unfortunately, the scales of the solar system are so large that we wouldn't
	 * be able to see more than one planet at a time if we simply divided the
	 * radius of the object by the radius of the solar system, so we must scale the satellite
	 * by some arbitrary "looks good" factor. You will have to play with this to
	 * find an appropriate size such that we can see everything, but they don't
	 * overlap each other
	 * 
	 * Abstract Method Choice: Consider the problems caused by the scales involved in "Seeing" the solar system.
	 *                         Choice 1) If all satellites should be drawn at the same scale, implement the code here.
	 *                         Choice 2) If all satellites need to be drawn at a larger scale, then implement this
	 *                         in the child classes.
	 *                         Choice 3) If some satellites need to be drawn at a larger scale, but most can be drawn
	 *                         at a common scale, write the code here, then override it in the appropriate child class.
	 *                          
	 * 
	 * Note: this function should use the JComponent Set Size function to change the size of the GUI display of
	 *       the object.
	 *       
	 * Note 2: we take in the radius_of_system parameter to allow this function to know how big the displayed area
	 *         is which can factor into how big we draw our planets.  To start you may want to make all displayed
	 *         objects the same size regardless, then see how changing their size affects the simulation
	 *         
	 * Note 3: in general, the larger the region we want to see on the screen, the smaller the satellites should be
	 *         drawn         
	 * 
	 * @param radius_of_universe - how far across the displayable universe (in our standard case we set the
	 *                             orbit of saturn as this value)
	 */
	protected abstract void update_display_size( double radius_of_system );

}
