import java.awt.geom.*;
import java.awt.*;

public class Brick extends ColorShape {
		private int xPos = 0;
		private int yPos = 0;
		private int width = 53;
		private int height = 25;
		private Rectangle2D.Double shape;

		// constructor
		public Brick(int x, int y, int w, int h) {
			super(new Rectangle2D.Double(x, y, w, h));
			
			//set brick x, y, width, and height
			xPos = x;
			yPos = y;
			width = w;
			height = h;
			// update shape
			shape = (Rectangle2D.Double)super.shape;
			shape.setRect(xPos, yPos, width, height);
		}

		public int getX() {
			return xPos;
		}

		public Rectangle2D.Double getShape() {
			return shape;
		}
		public void paint(Graphics2D brush, int row) {
			//method sets each row of bricks to a different color, and paints row of bricks to screen

				if(row == 1) {
					brush.draw(shape);
					brush.setColor(Color.RED);
					brush.fill(shape);
				}
				else if(row == 2) {
					brush.draw(shape);
					brush.setColor(Color.ORANGE);
					brush.fill(shape);
				}
				else if(row == 3) {
					brush.draw(shape);
					brush.setColor(Color.YELLOW);
					brush.fill(shape);
				}
				else if(row == 4) {
					brush.draw(shape);
					brush.setColor(Color.GREEN);
					brush.fill(shape);
				}
				else if(row == 5) {
					brush.draw(shape);
					brush.setColor(Color.CYAN);
					brush.fill(shape);
				}
				else if(row == 6) {
					brush.draw(shape);
					brush.setColor(Color.BLUE);
					brush.fill(shape);
				}
		}
	}