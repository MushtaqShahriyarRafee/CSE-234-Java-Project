package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;
import com.game.src.main.libs.Animation;

/**
 * This the class for only Player class
 * Controlling the player
 * Controlls the basic collision of player object
 */
public class Player extends GameObject implements EntityA{
	
	private double velX = 0;
	private double velY = 0;
	
	private Textures tex;
	
	Game game;
	Controller controller;
	Animation anim;
	
	public Player(double x, double y, Textures tex, Game game, Controller controller) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		this.controller = controller;
		
		anim = new Animation(7, tex.player[0], tex.player[1], tex.player[2]);
	}
	
	/**
	 *  It gives movement to the player
	 */
	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0)
			x = 0;
		if(x >= 1800 - 25)
			x = 1800 - 25;
		if(y <= 0)
			y = 0;
		if(y >= 960 - 34)
			y = 960 - 34;
		if(x == 425 && (y >= 105 && y <= 265)) {
			x = 425;
		}
		if(x == 430 && (y >= 105 && y <= 265)) {
			x = 430;
		}
		
		
		for(int i=0; i<game.eb.size(); i++) {
			
			EntityB tempEnt = game.eb.get(i);
			
			if(Physics.Collision(this, tempEnt)) {
				controller.removeEntity(tempEnt);
				Game.HEALTH -= 2;
				Game.health -= 2;
				game.setEnemy_killed(game.getEnemy_killed() + 1);
			}
		}
		
		anim.runAnimation();
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 48, 48);
	}
	
	public void render(Graphics g) {
		anim.drawAnimation(g, x, y, 0);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}
}
