package BreakoutGame

import javax.swing.ImageIcon;

public class Brick extends Sprite {
	
	private boolean destroyed;
	
	//This is the Brick class.//
	//In the destroyed variable we keep the state of a brick.//
	
	public Brick(int x, int y) {
		
		initBrick(x, y);
	}
	
	private void initBrick(int x, int y) {
		
		this.x = x;
		this.y = y;
			
		destroyed = false;	
		
		loadImage();
		getImageDimensions();
	}
	
	private void loadImage() {
		
		ImageIcon ii = new ImageIcon("src/resources/brick.png");
		image = ii.getImage();
	}
	
	public boolean isDestroyed() {
		return destroyed;
	}
	
	public void setDestroyed(boolean val) {
		
		destroyed = val;
	}
}
