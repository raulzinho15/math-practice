package customNodes;

import interfaces.Clicked;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

/** 
 *  Handles the creation of smooth buttons in the form which they are typically
 *  used in Darkness Overhaul.
 *  @author Raul Hernandez, 6/5/2020
 */
public class GameSmoothButton extends GameButton {
	
	//The nodes that consist of this game button
	private final Rectangle highlight;
	private final Label lbl;
	
	/**
	 *  Creates a GameButton object with text and with the specified button properties.
	 *  @param width The width of the button's background.
	 *  @param height The height of the button's background.
	 *  @param color The color of the button's background.
	 *  @param lblText The text to be displayed on this game button.
	 *  @param lblTextFill The TextFill property of this game button's text.
	 *  @param lblFont The font of this game button's text.
	 *  @param c The action to be taken when the user clicks on this game button.
	 */
	public GameSmoothButton(double width, double height, Color color, String lblText, Color lblTextFill, Font lblFont, Clicked c) {
		//Sets up the button's background
		final Rectangle background = new Rectangle(width, height, color);
		
		//Sets up the button's highlight
		highlight = new Rectangle(width, height, Color.WHITE);
		highlight.setOpacity(0.2);
		highlight.setVisible(false);
		
		//Creates a Label for the button's text
		lbl = new Label(lblText);
		lbl.setPrefSize(width, height);
		lbl.setTextFill(lblTextFill);
		lbl.setAlignment(Pos.CENTER);
		lbl.setFont(lblFont);
		
		//Finalizes the game button setup
		setupMouseEvents();
		super.c = c;
		getChildren().addAll(background, highlight, lbl);
	}
	
	/** Sets up the MouseEvent EventHandlers for the game button.
	 */
	private void setupMouseEvents() {
		//Handles the image/cursor change that happens when the cursor hovers over the button
		setOnMouseEntered(e -> {
			//If the user returned to the button after having pressed it and not letting go, make the button show that it is still being pressed
			if (e.isPrimaryButtonDown())
				highlight.setOpacity(0.1);
			else
				highlight.setOpacity(0.2);

			highlight.setVisible(true);
			setCursor(Cursor.HAND);
		});
	
		//Handles the image/cursor change that happens when the cursor exits the button
		setOnMouseExited(e -> {
			highlight.setVisible(false);
			setCursor(Cursor.DEFAULT);
		});
	
		//Handles the image change that happens when the cursor presses the button
		setOnMousePressed(e -> highlight.setOpacity(0.1));
	
		//Handles the process that takes place when the cursor clicks the long button
		setOnMouseClicked(e -> {
			highlight.setOpacity(0.2);
			c.clicked(this);
		});
	}
	
	/**
	 * Executes what would normally be done when the button is pressed.
	 */
	public void pressed() {
		getOnMousePressed().handle(null);
	}
	
	/**
	 * Changes the background color.
	 */
	public void changeColor(Color c) {
		((Rectangle)(getChildren().get(0))).setFill(c);
	}
	
	/**
	 * Enables this button.
	 */
	public void enable() {
		setupMouseEvents();
	}
	
	/**
	 * Disables this button.
	 */
	public void disable() {
		setOnMouseEntered(null);
		setOnMouseExited(null);
		setOnMousePressed(null);
		setOnMouseClicked(null);
		highlight.setVisible(false);
	}
}
