package com.game.src.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * This class takes input from mouse
 * For the Buttons
 */

public class MouseInput implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	/**
	 *  For mouse pressed
	 */
	public void mousePressed(MouseEvent e) {

		int mx = e.getX();
		int my = e.getY();

		/**
		 * public Rectangle playButton = new Rectangle(750 + 120, 250, 100, 50); public
		 * Rectangle helpButton = new Rectangle(750 + 120, 350, 100, 50); public
		 * Rectangle quitButton = new Rectangle(750 + 120, 450, 100, 50);
		 */
      
		// Play Button
				if (mx >= 750 + 120 && mx <= 750 + 220) {
					if (my >= 250 && my <= 300) {
						// Pressed Play Button
						Game.State = Game.STATE.GAME;
					}
				}
				
		// Help Button
		if (mx >= 750 + 120 && mx <= 750 + 220) {
			if (my >= 250 && my <= 300) {
				// Pressed Help Button
				
			}
		}

		// Quit Button
		if (mx >= 750 + 120 && mx <= 750 + 220) {
			if (my >= 450 && my <= 500) {
				// Pressed Quit Button
				System.exit(1);
			}
		}
		
		//Back Button
		if(mx >= 5 && mx <= 105) {
			if(my >= 930 && my <= 960) {
				Game.State = Game.STATE.MENU;
			}
		}

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
