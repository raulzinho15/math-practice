package interfaces;

import customNodes.GameButton;

/**
 *  Handles the method to be run when the game button is clicked.
 *  @author Raul Hernandez, 6/5/2020
 */
public interface Clicked {
	/**
	 *  Method to be run when the game button is clicked and after the game button's image is changed.
	 *  @param gb The GameButton on which the user clicked.
	 */
	void clicked(GameButton gb);
}