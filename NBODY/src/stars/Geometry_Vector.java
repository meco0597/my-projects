
package stars;

import java.awt.*;

import javax.swing.*;

import java.awt.geom.*;
import java.util.Vector;

/**
 * @author Melvin Bosnjak, u0989241
 * @author Adrian Bollerslev, u1115021
 */
public class Geometry_Vector extends Point2D.Double
{
	Geometry_Vector copy;
  /**
   *
   * Constructor
   *
   * @param x - the X 
   * @param y - and Y values
   */
  public  Geometry_Vector(double xx, double yy)
  {
	super(xx,yy);
  }

  /**
   *
   * A "Copy" constructor.  Creat ourself based on the given vector
   * @param the_copy 
   */
   public  Geometry_Vector(Geometry_Vector the_copy)
   {
	   super(the_copy.x,the_copy.y);
   }

  /**
   * Add the components of the given vector to me.
   *
   * @param vector
   */
  public void add_to_me(Geometry_Vector vector)
  {
	this.x+=vector.x;
	this.y+=vector.y;
  }

  /**
   * Subtract the components of the given vector from me.
   * @param vector
   */
  public void subtract_from_me(Geometry_Vector vector)
  {
	  this.x-=vector.x;
	  this.y-=vector.y;
  }

  /**
   * Divide my components by the scalar
   * @param scalar
   */
  public void divide_by( double scalar )
  {
	  this.x/=scalar;
	  this.y/=scalar;
  }

  /**
   * Multiply my components by the scalar
   * @param scalar
   */
  public  void   multiply_me_by(double scalar)
  {
	  this.x*=scalar;
	  this.y*=scalar;
  }

  /**
   * @return my magnitude (the distance from the origin to my X,Y)
   * Think Pythagoras
   */
  public  double  magnitude()
  {
	  return Math.sqrt(this.x * this.x + this.y * this.y);
  }

  /**
   * @return an informative (but concise) description of this object
   */
  public  String toString()
  {
	  return "FIXME";
  }

  /**
   * Take this vector and turn it into a vector of length 1.  This is done by
   * dividing each component (i.e., x,y)  by the magnitude.
   */
  public   void   normalize()
  {
	  double fullmag = magnitude();
	  this.x/=fullmag;
	  this.y/=fullmag;
	 
  } 
}
