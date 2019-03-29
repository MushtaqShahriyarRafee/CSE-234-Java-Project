package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.main.classes.EntityA;

/**
 * This is the class for player's bullet 
 * For shooting the enemy
 */
public class Bullet extends GameObject implements EntityA{
	
	private Textures tex;
	private Game game;
	
	public Bullet(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex; 
		this.game = game;
	}
	
	/**
	 * Bullet speed
	 */
	public void tick() {
		x += 30;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 48, 48);
	}
	
	/**
	 * Draws bullet 
	 */
	public void render(Graphics g) {
		g.drawImage(tex.bullet, (int)x, (int)y, null);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

}
