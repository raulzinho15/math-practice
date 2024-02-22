package app;

import java.text.DecimalFormat;

import customNodes.GameButton;
import customNodes.GameSmoothButton;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class GUI extends Application {
	
	static AnchorPane pane;
	static Label equation;
	private static Label result;
	
	private static Label time;
	private static AnimationTimer timeCounter;
	
	private static Label accuracy;
	private static int totalQ;
	private static int correctQ;
	private static boolean attempted = false;
	
	private static AnimationTimer at;
	
	private static GameSmoothButton[] tabs;
	private static HBox[] tabRows = {new HBox(), new HBox()};

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		//Sets up pane
		pane = new AnchorPane();
		pane.setPrefSize(800, 600);
		
		//Stores all tabs
		tabs = new GameSmoothButton[7];

		//Linear Equation tab
		final GameSmoothButton linear = new GameSmoothButton(400, 30, Color.web("#003f70"), "Linear Equations", Color.WHITE, new Font("System Bold", 16), gb ->
			newType(0, gb, QuestionGeneration.linearEquation()));
		tabRows[0].getChildren().add(linear);
		tabs[0] = linear;
		
		//Quadratic Equation tab
		final GameSmoothButton quadratic = new GameSmoothButton(400, 30, Color.web("#003f70"), "Quadratic Equations", Color.WHITE, new Font("System Bold", 16), gb ->
			newType(0, gb, QuestionGeneration.quadraticEquation()));
		tabRows[0].getChildren().add(quadratic);
		tabs[1] = quadratic;
		
		//Addition tab
		final GameSmoothButton add = new GameSmoothButton(160, 30, Color.web("#00509a"), "Addition", Color.WHITE, new Font("System Bold", 16), gb ->
			newType(1, gb, QuestionGeneration.addition()));
		add.disable();
		tabRows[1].getChildren().add(add);
		tabs[2] = add;

		//Subtraction tab
		final GameSmoothButton sub = new GameSmoothButton(160, 30, Color.web("#003f70"), "Subtraction", Color.WHITE, new Font("System Bold", 16), gb ->
			newType(1, gb, QuestionGeneration.subtraction()));
		tabRows[1].getChildren().add(sub);
		tabs[3] = sub;

		//Multiplication tab
		final GameSmoothButton mul = new GameSmoothButton(160, 30, Color.web("#003f70"), "Multiplication", Color.WHITE, new Font("System Bold", 16), gb ->
			newType(1, gb, QuestionGeneration.multiplication()));
		tabRows[1].getChildren().add(mul);
		tabs[4] = mul;

		//Division tab
		final GameSmoothButton div = new GameSmoothButton(160, 30, Color.web("#003f70"), "Division", Color.WHITE, new Font("System Bold", 16), gb ->
			newType(1, gb, QuestionGeneration.division()));
		tabRows[1].getChildren().add(div);
		tabs[5] = div;

		//Negative Arithmetic tab
		final GameSmoothButton neg = new GameSmoothButton(160, 30, Color.web("#003f70"), "Negative Add/Sub", Color.WHITE, new Font("System Bold", 16), gb ->
			newType(1, gb, QuestionGeneration.negativeAddSub()));
		tabRows[1].getChildren().add(neg);
		tabs[6] = neg;
		
		//Finalizes the rows
		for (int i = 0; i < tabRows.length; i++) {
			pane.getChildren().add(tabRows[i]);
			tabRows[i].setLayoutY(30*i);
		}
		
		//Sets up the blue background
		final Rectangle back = new Rectangle(800, 540);
		back.setLayoutY(60);
		back.setFill(Color.web("#00509a"));
		pane.getChildren().add(back);
		
		//Sets up the label to display the current equation question
		equation = new Label(QuestionGeneration.addition());
		equation.setPrefWidth(800);
		equation.setLayoutY(100);
		equation.setFont(new Font("Cambria Bold", 48));
		equation.setAlignment(Pos.CENTER);
		equation.setTextAlignment(TextAlignment.CENTER);
		equation.setTextFill(Color.WHITE);
		pane.getChildren().add(equation);
		
		//Sets up the label that displays whether the user answered correctly
		result = new Label();
		result.setPrefSize(800, 50);
		result.setLayoutY(520);
		result.setFont(new Font("System Bold", 24));
		result.setAlignment(Pos.CENTER);
		result.setTextAlignment(TextAlignment.CENTER);
		result.setTextFill(Color.web("#00ff00"));
		pane.getChildren().add(result);
		
		//Sets up the time title label
		final Label timeTitle = new Label("Time:");
		timeTitle.setLayoutX(118);
		timeTitle.setLayoutY(235);
		timeTitle.setFont(new Font("System Bold", 24));
		timeTitle.setTextFill(Color.WHITE);
		pane.getChildren().add(timeTitle);
		
		//Sets up the time label
		time = new Label("00:00");
		time.setPrefWidth(300);
		time.setLayoutY(270);
		time.setFont(new Font("System Bold", 24));
		time.setAlignment(Pos.CENTER);
		time.setTextAlignment(TextAlignment.CENTER);
		time.setTextFill(Color.WHITE);
		pane.getChildren().add(time);
		
		//Sets up the accuracy title label
		final Label accuracyTitle = new Label("Accuracy:");
		accuracyTitle.setLayoutX(596);
		accuracyTitle.setLayoutY(235);
		accuracyTitle.setFont(new Font("System Bold", 24));
		accuracyTitle.setTextFill(Color.WHITE);
		pane.getChildren().add(accuracyTitle);
		
		//Sets up the accuracy label
		accuracy = new Label("None");
		accuracy.setPrefWidth(300);
		accuracy.setLayoutX(500);
		accuracy.setLayoutY(270);
		accuracy.setFont(new Font("System Bold", 24));
		accuracy.setAlignment(Pos.CENTER);
		accuracy.setTextAlignment(TextAlignment.CENTER);
		accuracy.setTextFill(Color.WHITE);
		pane.getChildren().add(accuracy);
		
		//Finalizes scene/stage setup
		final Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.setTitle("Math Practice");
		stage.getIcons().add(new Image("img/mathIcon.png"));
		stage.show();
		
		resetTimer();
	}
	
	/**
	 * Displays the "Correct!" message.
	 */
	static void correct() {
		result.setTextFill(Color.web("#00ff00"));
		result.setText("Correct!");
		
		//Updates the accuracy display
		if (!attempted)
			accuracy.setText(++correctQ+"/"+(++totalQ)+" ("+100*correctQ/totalQ+"%)");
		attempted = false;

		if (at != null) at.stop();
		at = new AnimationTimer() {
			
			final long start = System.nanoTime();

			//Makes message disappear after 2 seconds
			public void handle(long now) {
				if (now - start > 2E9) {
					result.setText("");
					stop();
				}
			}
		};
		at.start();
	}
	
	/**
	 * Displays the "Incorrect." message.
	 */
	static void incorrect() {
		result.setTextFill(Color.web("#ff0000"));
		result.setText("Incorrect.");
		
		//Updates the accuracy display
		if (!attempted)
			accuracy.setText(correctQ+"/"+(++totalQ)+" ("+100*correctQ/totalQ+"%)");
		attempted = true;

		if (at != null) at.stop();
		at = new AnimationTimer() {
			
			final long start = System.nanoTime();
			
			//Makes message disappear after 2 seconds
			public void handle(long now) {
				if (now - start > 2E9) {
					result.setText("");
					stop();
				}
			}
		};
		at.start();
	}
	
	/**
	 * Sets up a new problem type.
	 * @param rowNum The number of the row containing this tab.
	 * @param gb The tab selected.
	 * @param prob The problem to display.
	 */
	private static void newType(int rowNum, GameButton gb, String prob) {
		//Resets all tabs
		for (GameSmoothButton gsb : tabs) {
			gsb.changeColor(Color.web("#003f70"));
			gsb.enable();
		}

		//Disables the button
		final GameSmoothButton gsb = (GameSmoothButton)gb;
		gsb.changeColor(Color.web("#00509a"));
		gsb.disable();
		
		//Sets up the labels
		equation.setText(prob);
		result.setText("");
		
		//Sets up the tab rows' positions
		for (int i = 0; i < tabRows.length; i++) {
			if (i == rowNum) {
				tabRows[i].setLayoutY(30*(tabRows.length-1));
				continue;
			}
			tabRows[i].setLayoutY(30*i - (i > rowNum ? 30 : 0));
		}
		
		resetTimer();
		resetAccuracy();
	}
	
	/**
	 * Resets the timer.
	 */
	private static void resetTimer() {
		if (timeCounter != null) timeCounter.stop();
		time.setText("00:00");
		
		timeCounter = new AnimationTimer() {
			
			private final long start = System.nanoTime();
			
			//Counts the time elapsed in mm:ss
			public void handle(long now) {
				final long seconds = (now-start)/1000000000L;
				final DecimalFormat df = new DecimalFormat("00");
				
				time.setText(df.format(seconds/60)+":"+df.format(seconds%60));
			}
		};
		timeCounter.start();
	}

	/**
	 * Resets the accuracy count.
	 */
	private static void resetAccuracy() {
		accuracy.setText("None");
		correctQ = 0;
		totalQ = 0;
	}
}
