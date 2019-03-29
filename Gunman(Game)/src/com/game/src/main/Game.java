package com.game.src.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JFrame;
import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

/**
 * This is the main class for the game
 * It runs the game, draws graphics
 */
public class Game extends Canvas implements Runnable {

	public static final int WIDTH = 900;
	public static final int HEIGHT = 480;
	public static final int SCALE = 2;
	public final String TITLE = "GunMan";

	private boolean running = false;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	private BufferedImage menuBackground = null;
	public Rectangle backButton = new Rectangle(5, 930, 100, 30);

	private boolean is_shooting = false;

	private int enemy_count = 7;
	private int enemy_killed = 0, totalKilled = 0;

	private Player p;
	private Enemy e;
	private Controller c;
	private Textures tex;
	private Menu menu;

	public LinkedList<EntityA> ea;
	public LinkedList<EntityB> eb;

	public static int HEALTH = 150, health = 150;
	public static int POINTS = 0;

	public static enum STATE {
		MENU, GAME
	};

	public static STATE State = STATE.MENU;
	
	/**
	 * This method initially loads all the buffered images
	 * Basically it initalizes the game
	 */
	public void init() {
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/hero.png");
			menuBackground = loader.loadImage("/menuBackground.png");
			background = loader.loadImage("/background.png");

		} catch (IOException e) {
			e.printStackTrace();
		}

		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());

		tex = new Textures(this);

		c = new Controller(tex, this);
		p = new Player(45, 430, tex, this, c);
		menu = new Menu();

		ea = c.getEntityA();
		eb = c.getEntityB();

		c.createEnemy(enemy_count);
	}
	
	/**
	 * Starts the thread
	 */
	private synchronized void start() {
		if (running)
			return;

		running = true;
		thread = new Thread(this);
		thread.start();

	}
	
	/**
	 * Stops the thread
	 */
	private synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	/**
	 * Runs the game
	 */
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println(updates+" Ticks, Fps " +frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	/**
	 * Calling Palyer's tick() method
	 * Calling Controller's tick() method
	 * count total enemy killed
	 */
	private void tick() {
		if (State == STATE.GAME) {
			p.tick();
			c.tick();
		}
		if (enemy_killed == 1) {
			totalKilled++;
			enemy_killed = 0;
			c.createEnemy(1);
		}
	}
	
	/**
	 * Draws graphics
	 */
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		g.drawImage(menuBackground, 0, 0, null);

		if (State == STATE.GAME) {
			g.drawImage(background, 0, 0, null);
			p.render(g);
			c.render(g);

			g.setColor(Color.GRAY);
			g.fillRect(5, 5, 150, 50);
			g.setColor(Color.green);
			g.fillRect(5, 5, HEALTH, 50);
			g.setColor(Color.white);
			g.drawRect(5, 5, 150, 50);
			Font fnt0 = new Font("Serif", Font.BOLD, 20);
			g.setFont(fnt0);
			g.setColor(Color.black);
			g.drawString("Health = " + health, 27, 35);

			g.setColor(Color.blue);
			g.fillRect(160, 5, 120, 50);
			g.setColor(Color.blue);
			g.fillRect(160, 5, POINTS, 50);
			g.setColor(Color.WHITE);
			g.drawRect(160, 5, 120, 50);
			Font fnt1 = new Font("Serif", Font.BOLD, 20);
			g.setFont(fnt1);
			g.setColor(Color.black);
			g.drawString("Score = " + totalKilled, 170, 35);

			Graphics2D g2d = (Graphics2D) g;
			Font fnt2 = new Font("arial", Font.BOLD, 30);
			g.setFont(fnt2);
			g.setColor(Color.RED);
			g.drawString("Back", backButton.x + 15, backButton.y + 27);
			g2d.draw(backButton);

		}
		if (State == STATE.MENU) {
			menu.render(g);
		}

		g.dispose();
		bs.show();
	}
	
	/**
	 * Takeing input from the keyboard
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		System.out.println("x = " + p.getX() + " y = " + p.getY());

		if (State == STATE.GAME) {
			if (key == KeyEvent.VK_RIGHT) {
				p.setVelX(5);
			} else if (key == KeyEvent.VK_LEFT) {
				p.setVelX(-5);
			} else if (key == KeyEvent.VK_DOWN) {
				p.setVelY(5);
			} else if (key == KeyEvent.VK_UP) {
				p.setVelY(-5);
			} else if (key == KeyEvent.VK_SPACE && !is_shooting) {
				is_shooting = true;
				c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
			}
		}
	}
	
	/**
	 * Releasing the key when Player release it
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_LEFT) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_DOWN) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_UP) {
			p.setVelY(0);
		} else if (key == KeyEvent.VK_SPACE) {
			is_shooting = false;
		}
	}
	
	/**
	 * Main method to run the game
	 * @param agrs
	 */
	public static void main(String agrs[]) {
		Game game = new Game();

		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();

	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
}
