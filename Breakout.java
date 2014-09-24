import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Breakout {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			//main function that sets up the JFrame, and GamePanel
		frame.setSize(600,500);
        frame.setTitle("Breakout");
        frame.setResizable(false);
        frame.add(new GamePanel());
        frame.setVisible(true);
	}

	private static class GamePanel extends JPanel {
		
		Ball ball;
		Paddle paddle;
		BrickConfiguration bconfig;						// Gamepanel is a subclass of Jpanel, and includes
		Timer timer;									// the initialazation of variables
		int score;
		boolean isDead;

		Rectangle2D.Double background;

		public GamePanel() {
			super();

			// call initializeGameObjects()
			initializeGameObjects();

			// add PaddleMover as a keyListener
			addKeyListener(new PaddleMover());
			setFocusable(true);		
		}

		public void initializeGameObjects() {
			// instantiate ball, paddle, and brick configuration

			paddle = new Paddle();
			ball = new Ball();
			bconfig = new BrickConfiguration();
			background = new Rectangle2D.Double(0,0,1000,1000);
			score = 0;
			isDead = false;

			// set up timer to run GameMotion() every 10ms
			timer = new Timer(10, new GameMotion());
			timer.start();
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
		
			// paint ball, paddle, and brick configuration
			g2.draw(background);
			g2.setColor(Color.BLACK);
			g2.fill(background);

				paddle.paint(g2);
				ball.paint(g2);
				bconfig.paint(g2);

				// paint score counter, and number of lives  to screen
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("Serif", Font.PLAIN, 20));
				g2.drawString("Score: " + score, 5, 15);
				g2.drawString("Lives: " + ball.getLives(), 520, 15);
			
			// check if isDead is true, and if so then print Gameover message that includes final score
			if(isDead) {
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("Serif", Font.PLAIN, 75));
				g2.drawString("Game Over!", 100, 250);
				g2.setFont(new Font("Serif", Font.PLAIN, 50));
				g2.drawString("Your final score was " + score, 50, 300);
				timer.stop();
			}
		}

		private class GameMotion implements ActionListener {
			public GameMotion() {

			}

			public void actionPerformed(ActionEvent evt) {
				//move ball automatically
				ball.move();

				//move paddle according to key press
				paddle.move();	

				//check if the ball hits the paddle or a brick
				checkForHit();
				
				//call repaint
				repaint();

				//call getLives to check if game is over
				isDead = ball.isDead();
			}
		}


		private class PaddleMover implements KeyListener {
			public void keyPressed(KeyEvent evt) {
				// change paddle speeds for left and right key presses
				int key = evt.getKeyCode();

				if(key == KeyEvent.VK_RIGHT) {
					int speed = 7;
					paddle.setSpeed(speed);
				}
				else if(key == KeyEvent.VK_LEFT) {
					int speed = -7;
					paddle.setSpeed(speed);
				}
			}
			public void keyReleased(KeyEvent evt) {
				// set paddle speed to 0
				int key = evt.getKeyCode();

				if(key == KeyEvent.VK_RIGHT) {
					int speed = 0;
					paddle.setSpeed(speed);
				}
				else if(key == KeyEvent.VK_LEFT) {
					int speed = 0;
					paddle.setSpeed(speed);
				}

			}
			public void keyTyped(KeyEvent evt) {}
		}

		public int score() {
			// increases the score by 1 everytime method is called
			score++;
			return score;
		}

		public void checkForHit() {
			
			// change ball speed when ball hits paddle
			if (ball.getShape().intersects(paddle.getShape())) {
				int leftSide = paddle.getX();
				int middleLeft = paddle.getX() + (int)(paddle.getWidth()/3);
				int middleRight = paddle.getX() + (int)(2*paddle.getWidth()/3);
				int rightSide = paddle.getX() + paddle.getWidth();

				if ((ball.getX() >= leftSide) && (ball.getX() < middleLeft)) {
					// change ball speed
					int speed = -1;

					ball.setYspeed(speed);
					ball.setXspeed(speed);

					
				}
				if ((ball.getX() >= middleLeft) && (ball.getX() <= middleRight)) {
					// change ball speed
					int speed = -1;

					ball.setYspeed(speed);
				}
				if ((ball.getX() > middleRight) && (ball.getX() <= rightSide)) {
					// change ball speed
					int speed = -1;

					ball.setYspeed(speed);
					ball.setXspeed(speed);
				}
			}

			// change ball speed when ball hits brick
			for (int i = 0; i < bconfig.getRows(); i++) {
				for (int j = 0; j < bconfig.getCols(); j++) {
					if (bconfig.exists(i,j)) {
						if (ball.getShape().intersects(bconfig.getBrick(i,j).getShape())) {
							Point ballLeft = new Point((int)ball.getShape().getX(), (int)(ball.getShape().getY() + ball.getShape().getHeight()/2));
							Point ballRight = new Point((int)(ball.getShape().getX() + ball.getShape().getWidth()), (int)(ball.getShape().getY() + ball.getShape().getHeight()/2));
							Point ballTop = new Point((int)(ball.getShape().getX() + ball.getShape().getWidth()/2), (int)ball.getShape().getY());
							Point ballBottom = new Point((int)(ball.getShape().getX() + ball.getShape().getWidth()/2), (int)(ball.getShape().getY() + ball.getShape().getHeight()));
							if (bconfig.getBrick(i,j).getShape().contains(ballLeft)) {
								// change ball speed
								int speed = -1;

								ball.setYspeed(speed);
								ball.setXspeed(speed);
							}
							else if(bconfig.getBrick(i,j).getShape().contains(ballRight)) {
								// change ball speed
								int speed = -1;

								ball.setYspeed(speed);
								ball.setXspeed(speed);
							}
							if (bconfig.getBrick(i,j).getShape().contains(ballTop)) {
								// change ball speed
								int speed = -1;

								ball.setYspeed(speed);
							}
							else if (bconfig.getBrick(i,j).getShape().contains(ballBottom)) {
								// change ball speed
								int speed = -1;

								ball.setYspeed(speed);
								ball.setXspeed(speed);
							}
							

							// remove brick
							bconfig.removeBrick(i, j);
							score();
						} 
					}
				}
			} 
		}
	}
}