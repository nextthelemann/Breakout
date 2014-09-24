import java.awt.geom.*;			// imports java AWT geom folder
import java.awt.*;				// imports all folders of java AWT
import java.util.*;				// imports java util package
import java.lang.*;				// imports java lang package

public class Ball extends ColorShape {
	
	// location and size variables
	private int xPos;
	private int yPos;
	private int xSpeed;
	private int ySpeed;
	private int lives;
	private boolean isDead;
	private static final int height = 7;
	private static final int width = 7;
	private static final Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, width, height);

	// initialization of the random class, and thread class
	Random rand = new Random();
	Thread thread = new Thread();

	// constructor
	public Ball() {
		super(shape);

		// set ball color
		super.setFillColor(Color.WHITE);
		super.setBorderColor(Color.WHITE);
		
		//set initial values for x and y position, speed, lives, and value of isDead
		xPos += 250;
		yPos += 300;
		xSpeed = -1;
		ySpeed = -2;
		lives = 3;
		isDead = false;
	}

	public void move() {
		// move ball
		xPos += xSpeed;
		yPos += ySpeed;
		// detect if ball should bounce off an edge, and second else if checks if ball fell
		// off the bottom of the screen, which calls setLives to change the value of lives left.
		if(xPos <= 0 || xPos >= 600 - width) {
			xSpeed = xSpeed * -1;
		}
		else if(yPos <= 0 - height) {
			ySpeed = ySpeed * -1;
		}
		else if(yPos >= 500) {
			setLives();
			xPos = 250;
			yPos = 300;
			shape.setFrame(xPos,yPos,width,height);
			try {
				thread.sleep(1000);
			} catch(InterruptedException e) {

			}
		}
		// update shape to new values
		shape.setFrame(xPos, yPos, width, height);
	}

	public void setXspeed(int newSpeed) {		
		xSpeed = newSpeed * xSpeed;			// sets the new xSpeed, is usually called when collisions occur.
	}

	public void setYspeed(int newSpeed) {
		ySpeed = newSpeed * ySpeed;			// sets the new ySpeed, is again usually called whe collisions occur.
	}

	public int getX() {
		return xPos;					// returns the value of the ball's xPos.
	}

	public int getY() {
		return yPos;					// returns the value of the ball's yPos.
	}

	public int getWidth() {
		return width;					// returns the width of the Ball.
	}

	public int getHeight() {
		return height;					// returns the height of the Ball.
	}

	public Ellipse2D.Double getShape() {
		return shape;						// returns the shape values of the Ball.
	}

	public void paint(Graphics2D brush) {
		brush.draw(shape);					// method paints the ball to the screen.
		brush.setColor(Color.WHITE);
		brush.fill(shape);
	} 
	
	public int setLives() {
		lives = lives - 1;					// method sets the number of lives when called, and returns the integer value
		return lives;
	}
	
	public int getLives() {
		return lives;						// method returns the value of number of lives left
	}

	public boolean isDead() {
		if(lives == 0) {
			isDead = true;
			return isDead;					// method returns boolean to tell if ball is out of lives or not.
		}
		else {
			isDead = false;
			return isDead;
		}
	}
}