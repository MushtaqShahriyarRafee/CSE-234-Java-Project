package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

/**
 * This class is for creating enemy bullet
 * Bullet speed and Graphics
 */
public class EnemyBullet extends GameObject implements EntityB{

	private Textures tex;
	private Game game;
	
	public EnemyBullet(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex; 
		this.game = game;
	}
	
	/**
	 * Set Bullet speed
	 */
	public void tick() {
		x -= 40;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 48, 48);
	}
	
	/**
	 * Draw Bullet Graphics
	 */
	public void render(Graphics g) {
		g.drawImage(tex.enemyBullet, (int)x, (int)y, null);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
}
