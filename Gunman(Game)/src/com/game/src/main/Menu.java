package com.game.src.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * This class creates the game menu
 * Provides some buttons for the starting or exiting the game
 */

public class Menu {
	
	public Rectangle playButton = new Rectangle(750 + 120, 250, 100, 50);
	public Rectangle helpButton = new Rectangle(750 + 120, 350, 100, 50);
	public Rectangle quitButton = new Rectangle(750 + 120, 450, 100, 50);
	
	/**
	 * It generates game title in the menu
	 * Creates buttons
	 */
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		Font fnt0 = new Font("Serif", Font.BOLD, 80);
		g.setFont(fnt0);
		g.setColor(Color.CYAN);
		g.drawString("GunMan", 750, 200);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.setColor(Color.white);
		g.fillRect(870, 250, 100, 50);
		g.setColor(Color.RED);
		g.drawString("Play", playButton.x + 19, playButton.y + 32);
		g2d.draw(playButton);
		g.setColor(Color.white);
		g.fillRect(870, 350, 100, 50);
		g.setColor(Color.red);
		g.drawString("Help", helpButton.x + 19, helpButton.y + 32);
		g2d.draw(helpButton);
		g.setColor(Color.white);
		g.fillRect(750+120, 450, 100, 50);
		g.setColor(Color.red);
		g.drawString("Quit", quitButton.x + 19, quitButton.y + 32);
		g2d.draw(quitButton);
		
	}
}