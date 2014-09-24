import java.awt.*;
import java.awt.geom.*;

public class BrickConfiguration {
	
	//location and size variables
	private static final int xStart = 3;
	private static final int yStart = 50;	
	private static final int numCols = 10;
	private static final int numRows = 6;
	private static final int brickHeight = 25;
	private static final int brickWidth = 56;

	// 2D array containing brick objects
	private static Brick[][] bricks = new Brick[numCols][numRows];

	// 2D array telling us whether the brick should be painted (has it been hit?)
	private static boolean[][] paintBricks = new boolean[numCols][numRows];
	
	// constructor
	public BrickConfiguration() {
		
		// create new bricks and store them in bricks array
		for (int i = 0; i < numCols; i++) {
			for (int j = 0; j < numRows; j++) {
				// initialize paintBricks[i][j]
				 paintBricks[i][j] = true;
				// initialize bricks[i][j]
				 int x = xStart + i * (brickWidth + 3);
				 int y = yStart + j * (brickHeight + 3);
				 
				 bricks[i][j] = new Brick(x, y, brickWidth, brickHeight);
			}
		}		
	}

	// paint the bricks array to the screen
	public void paint(Graphics2D brush) {
		for (int i = 0; i < numCols; i++) {
			for (int j = 0; j < numRows; j++) {
				// determine if brick should be painted
				// if so, call paintBrick()
				if(paintBricks[i][j]) {
					paintBrick(bricks[i][j], brush, j + 1);
				}
			}
		}
	}

	// paint an individual brick
	public void paintBrick(Brick brick, Graphics2D brush, int rows) {
		// call brick's paint method
		brick.paint(brush, rows);
		
	}

	public void removeBrick(int row, int col) {
		// update paintBricks array for destroyed brick
		paintBricks[col][row] = false;
	}

	public Brick getBrick(int row, int col) {
		return bricks[col][row];
	}

	public boolean exists(int row, int col) {
		return paintBricks[col][row];
	}

	public int getRows() {
		return numRows;
	}

	public int getCols() {
		return numCols;
	}

}