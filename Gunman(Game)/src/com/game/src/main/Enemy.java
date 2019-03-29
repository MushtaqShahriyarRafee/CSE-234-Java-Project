package com.game.src.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.main.classes.EntityA;
import com.game.src.main.classes.EntityB;

/**
 * This class is for enemy
 */

public class Enemy extends GameObject implements EntityB {

	private Textures tex;

	Random r = new Random();
	private int speed = r.nextInt(3) + 1;

	private Game game;
	private Controller c;

	public Enemy(double x, double y, Textures tex, Controller c, Game game) {
		super(x, y);
		this.tex = tex;
		this.c = c;
		this.game = game;

	}
	/**
	 * Set enemy speed
	 * Stop them in a particular place
	 */
	public void tick() {
		x -= speed;

		if (x < 1100) {
			x = 1100;
		}
		
		for (int i = 0; i < game.ea.size(); i++) {

			EntityA tempEnt = game.ea.get(i);

			if (Physics.Collision(this, tempEnt)) {
				
				c.removeEntity(tempEnt);
				c.removeEntity(this);
				game.setEnemy_killed(game.getEnemy_killed()+1);
				c.addEntity(new EnemyBullet(this.getX(), this.getY(), tex, game));
			}
		}
	}

	public void render(Graphics g) {
		g.drawImage(tex.enemy, (int) x, (int) y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 48, 48);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

}
