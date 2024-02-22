package customNodes;

import interfaces.Clicked;
import javafx.scene.Group;

/**
 * Handles the general in-game Button.
 * @author Raul Hernandez, 7/7/2020
 */
public class GameButton extends Group {
	
	//The Clicked interface
	Clicked c;
	
	/**
	 * Sets the action upon click.
	 * @param c The action to execute when the GameButton is clicked.
	 */
	public void setClicked(Clicked c) {
		this.c = c;
	}
}
