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
	
	private void gameFinished(Graphics2D g2d) {
		
		Font font = new Font("Verdana", Font.BOLD, 18);
		FontMetrics metr = this.getFontMetrics(font);
		
		g2d.setColor (Color.BLACK);
		g2d.setFont(font);
		g2d.drawString(message
