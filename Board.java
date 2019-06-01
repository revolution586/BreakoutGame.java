package BreakoutGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class Board extends JPanel implements Commons {
	
	private Timer timer;
	private String message = "Game Over";
	private Ball ball;
	private Paddle paddle;
	private Brick bricks[];
	private boolean inGame = true;
	
	public Board() {
		
		initBoard();
	}
	
	private void initBoard() {
		
		addKeyListener(new TAdapter());
		setFocusable(true);
		
		bricks = new Brick[N_OF_BRICKS];
		timer = new Timer();
		timer.scheduleAtFixedRate(new ScheduleTask(), DELAY, PERIOD);
	}
	
	@Override
	public void addNotify() {
		
		super.addNotify();
		gameInit();
	}
	
	private void gameInit() {
		
		ball = new Ball();
		paddle = new Paddle();
		
		int k = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 6; j++) {
				bricks[k] = new Brick(j * 40 + 30, i * 10 + 50);
				k++;
			}
		}
	}
	
	//In the gameInit() method we create a ball, a paddle, and thirty bricks//
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIASING_ON);
		
		g2d.setRenderingHint*RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		
		if (inGame) {
			
			drawObjects(g2d);
		} else {
			
			gameFinished(g2d);
		}
		//Depending on the inGame variable, we either draw all the objects in the drawObjects() method or finish the game with the gameFinished() method.//
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void drawObjects(Graphics2D g2d) {
		
		g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(), ball.getImageWidth(), ball.getImageHeight(), this);
		g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(), paddle.getImageWidth(), paddle.getImagHeight(), this);
		
		for (int i = 0; i < N_OF_BRICKS; i++) {
			if (!bricks[i].isDestroyed()) {
				g2d.drawImage(bricks[i].getImage(), bricks[i].getX(), bricks[i].getY(), bricks[i].getImageHeight(), bricks[i].getImageWidth(), this);
			}
		}
	}
	
	//The drawObjects() method draws all the objects of the game//
	//The sprites are drawn with the drawImage() method//
	
	private void gameFinished(Graphics2D g2d) {
		
		Font font = new Font("Verdana", Font.BOLD, 18);
		FontMetrics metr = this.getFontMetrics(font);
		
		g2d.setColor (Color.BLACK);
		g2d.setFont(font);
		g2d.drawString(message, (Commons.WIDTH - metr.stringWidth(message)) / 2, Commons.WIDTH / 2);
	}
	
	//The gameFinished() method draws "Game over" or "Victory" to the middle of the windows//
	
	private class TAdapter extends KeyAdaptewr {
		
		@Override
		public void keyReleased(KeyEvent e) {
			paddle.keyReleased(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			paddle.keyPressed(e);
		}
	}
	
	private class SheduleTask extends TimerTask {
		
		@Override
		public void run() {
			
			ball.move();
			paddle.move();
			checkCollision();
			repaint();
		}
	}
	
	//The ScheduleTask is triggered every PERIOD ms.//
	//In its run() method, we move the ball and the paddle.//
	//We check for possible collisions and repaint the screen.//
	
	private void stopGame() {
		
		inGame = false;
		timer.cancel();
	}
	
	private void checkCollision() {
		
		if (ball.getRect().getMaxY() > Commons.BOTTOM_EDGE) {
			stopGame();
		}
		
		//If the ball hits the bottom, we stop the game.//
		
		for (int i = 0, j = 0; i < N_OF_BRICKS; i++) {
			
			if (bricks[i].isDestroyed()) {
				j++;
			}
			
			if (j == N_OF_BRICKS) {
				message = "Victory";
				stopGame();
			}
		}
		
		//We check for how many bricks are destroyed//
		//If we destroyed all N_OF_BRICKS bricks, we win the game.//
		
		if ((ball.getRect()).intersects(paddle.getRect())) {
			
			int paddleLPos = (int) paddle.getRect().getMinX();
			int ballLPos = (int) ball.getRect().getMinx();
			
			int first = paddleLPos + 8;
			int second = paddleLPos + 16;
			int third = paddleLPos + 24;
			int fourth = paddleLPos + 32;
			
			if (ballLPos < first) {
				ball.setXDir(-1);
				ball.setYDir(-1);
			}
			
			//If the ball hits the first part of the paddle, we change the direction of the ball to the north-west//
			
			if (ballLPos >= first && ballLPos < second) {
				ball.setXDir(-1);
				ball.setYDir(-1 * ball.getYDir());
			}
			
			if (ballLPos >= second && ballLPos < third) {
				ball.setXDir(0);
				ball.setYDir(-1);
			}
			
			if (ballLPos >= third && ballLPos < fourth) {
				ball.setXDir(1);
				ball.setYDir(-1 * ball.getYDir());
			}
			
			if (ballLPos > fourth) {
				ball.setXDir(1);
				ball.setYDir(-1);
			}
		}
		
		for (int i = 0; i < N_OF_BRICKS; i++) {
			
			if ((ball.getRect()).intersects(bricks[i].getRect())) {
				
				int ballLeft = (int) ball.getRect().getMinX();
				int ballHeight = (int) ball.getRect().getHeight();
				int ballWidth = (int) ball.getRect().getWidth();
				int ballTop = (int) ball.getRect().getMinY();
					
				Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
				Point pointLeft = new Point(ballLeft - 1, ballTop);
				Point pointTop = new Point(ballLeft, ballTop - 1);
				Poiint pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);
				
				if (!bricks[i].isDestroyed()) {
					if (bricks[i].getRect().contains(pointRight)) {
						ball.setXDir(-1);
					} else if (bricks[i].getRect().contains(pointLeft)) {
						ball.setXDir(1):
					}
					
					if (bricks[i].getRect().contains(pointTop)) {
						ball.setYDir(1);
					} else if (bricks[i].getRect().contains(pointBottom)) {
						ball.setYDir(-1);
					}
					
					bricks[i].setDestroyed(true);
				}
				
				//If the ball hits the bottom of the brick, we change the y direction of the ball; it goes down.//
			}
		}
	}
}
		
