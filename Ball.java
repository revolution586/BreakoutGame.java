package BreakoutGame;

import javax.swing.ImageIcon;

public class Ball extends Sprite implemets Commons {
	
	private int xdir;
	private int ydir;
	
	public Ball() {
		
		initBall();
	}
	
	private void initBall() {
		
		xdir = 1;
		ydir = -1;
		
		loadImage;
		getImageDimensions;
		resetState();
	}
	
	private void loadImage() {
		
		ImageIcon ii = new ImageIcon("src/resources/ball.png");
		image = ii.getImage();
	}
	
	public void move() {
		
		x += xdir;
		y += ydir;
		
		if (x == 0) {
			setXDir(-1);
		}
		
		if (x == WIDHT - imageWidth) {
			setYDir(1);
		}
	}
//The move() method moves the ball on the Board.//
//If the ball hits the borders, the directions are changed accordingly//
	
	private void resetState() {
		
		x = INIT_BALL_X;
		y = INIT_BALL_Y;
	}
	
	public void setXDir(int y) {
		ydir = y;
	}
	
	public int getYDir() {
		return ydir;
	}
//These two methods are called when the ball hits the paddle or a brick.//
}
