/* COP 3502
 *	
 * Paddle.java
 *
 *	Puropse: This class sets up the basic framework for the paddle object used in the game Breakout.
 *	The paddle class handles everything has to deal with the physical features of the paddle object.
 *
 *	The Paddle class is run with the Breakout class, and cannot be run by its self. 
 *
 *	@author Scott Thelemann
 *
 *	@version 1.0 03/23/14
 * 
*/

import java.awt.*;			// imports the Abstract Windowing Toolkit
import java.awt.geom.*;		// imports the geometry kits of the AWT 

public class Paddle extends ColorShape{
	  
	// location and size variables
	private static int speed;				// The declaration of the integer of the speed for the paddle.	 
	private static int xPos;				// The declaration of the integer of the xPos for the paddle.	
	private static final int yPos = 450;	// The yPos of the paddle at all times. Ypos of the paddle never changes.
	private static final int width = 50;	// The width of the paddle at all times. Width of the paddle  never changes.
	private static final int height = 5;	// The height of the paddle at all times. Height of the paddle never changes.

	private static final Rectangle2D.Double shape = new Rectangle2D.Double(0,yPos,width,height);  // Initialization of the paddle's offical shape.

	public Paddle() {
		super(shape);

		// set paddle color to Gray
		setFillColor(Color.GRAY);
		setBorderColor(Color.GRAY);
	}

	public void move() {
		// move paddle
		xPos += speed;

		// stop the paddle from moving at the edges
		if(xPos <= 0) {
			xPos = 0;
		}
		else if(xPos >= 600 - width) {
			xPos = 600 - width;
		}
		// update shape
		shape.setRect(xPos, yPos, width, height);
	}

	public void setSpeed(int newSpeed) {
		// method sets the newSpeed for the paddle
		speed = newSpeed;
	}

	public int getWidth() {
		// method returns the width of the paddle
		return width;
	}

	public int getHeight() {
		// method returns the height of the paddle.
		return height;
	}

	public int getX() {
		// method returns xPos of the paddle
		return xPos;
	}

	public Rectangle2D.Double getShape() {
		// method returns the shape of the paddle.
		return shape;
	} 

	public void paint(Graphics2D brush) {
		//method paints the paddle to the screen.
		brush.draw(shape);
		brush.setColor(Color.GRAY);
		brush.fill(shape);
		
	}
}