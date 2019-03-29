package com.game.src.main;

import java.awt.image.BufferedImage;

/**
 * This calss creates Buffered Images for the Player, Bullet, Enemy and EnemyBullet
 * It grabs their images
 */

public class Textures {
	
	public BufferedImage[] player = new BufferedImage[3]; 
	public BufferedImage bullet, enemy, enemyBullet;
	
	private SpriteSheet ss;
	
	public Textures(Game game) {
		 ss = new SpriteSheet(game.getSpriteSheet());
		 
		 getTextures();
	}
	
	/**
	 * Grabbing images
	 */
	private void getTextures() {
		player[0] = ss.grabImage(1, 1, 48, 48);
		player[1] = ss.grabImage(1, 2, 48, 48);
		player[2] = ss.grabImage(1, 3, 48, 48);
		bullet = ss.grabImage(2, 1, 48, 48);
		enemyBullet = ss.grabImage(3, 1, 48, 48);
		enemy = ss.grabImage(4, 1, 48, 48);
	}
}
