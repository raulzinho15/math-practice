package app;

import customNodes.GameSmoothButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

class QuestionGeneration {
	
	static String addition() {
		
		//Generates the parts of the equation
		final int n1 = (int)(10*Math.random()+1);
		final int n2 = (int)(10*Math.random()+1);
		
		//Generates the lowest multiple choice option
		final int base = n1+n2 + (int)(4*Math.random()-4);
		
		//Sets up the multiple choice display
		final GameSmoothButton[] mc = new GameSmoothButton[4];
		for (int i = 0; i < 4; i++) {
			
			final int index = i;
			
			//Sets up the button
			final GameSmoothButton gb = new GameSmoothButton(200, 50, Color.web("#004f90"), ""+(base+i), Color.WHITE, new Font("Cambria Bold", 24), gb1 -> {
				if (base+index == n1+n2) {
					for (GameSmoothButton g : mc)
						GUI.pane.getChildren().remove(g);
					GUI.equation.setText(addition());
					GUI.correct();
				} else {
					GUI.incorrect();
				}
			});
			gb.setLayoutX(300);
			gb.setLayoutY(250 + 60*i);
			GUI.pane.getChildren().add(gb);
			mc[i] = gb;
		}
		
		return n1+" + "+n2+" = ?";
	}
	
	static String subtraction() {
		
		//Generates the parts of the equation
		final int n2 = (int)(10*Math.random()+1);
		final int n1 = n2 + (int)(10*Math.random()+1);
		
		//Generates the lowest multiple choice option
		final int base = n1-n2 + (int)(4*Math.random()-4);
		
		//Sets up the multiple choice display
		final GameSmoothButton[] mc = new GameSmoothButton[4];
		for (int i = 0; i < 4; i++) {
			
			final int index = i;
			
			//Sets up the button
			final GameSmoothButton gb = new GameSmoothButton(200, 50, Color.web("#004f90"), ""+(base+i), Color.WHITE, new Font("Cambria Bold", 24), gb1 -> {
				if (base+index == n1-n2) {
					for (GameSmoothButton g : mc)
						GUI.pane.getChildren().remove(g);
					GUI.equation.setText(subtraction());
					GUI.correct();
				} else {
					GUI.incorrect();
				}
			});
			gb.setLayoutX(300);
			gb.setLayoutY(250 + 60*i);
			GUI.pane.getChildren().add(gb);
			mc[i] = gb;
		}
		
		return n1+" - "+n2+" = ?";
	}
	
	static String multiplication() {
		
		//Generates the parts of the equation
		final int n1 = (int)(10*Math.random()+1) * (Math.random() < 0.5 ? -1 : 1);
		final int n2 = (int)(10*Math.random()+1) * (Math.random() < 0.5 ? -1 : 1);
		
		//Generates the randomness of answer choices
		final int add = Math.abs(Math.random() < 0.5 ? n1 : n2) * (Math.random() < 0.5 ? -1 : 1);
		final int sign = Math.random() < 0.5 ? -1 : 1;
		final int fs = Math.random() < 0.5 ? 0 : 1;
		
		//Sets up the multiple choice display
		final GameSmoothButton[] mc = new GameSmoothButton[4];
		for (int i = 0; i < 4; i++) {
			
			//Generates the answer choice
			final int choice = sign * (1-i/2*2) * (n1*n2 + add*((i%2 + fs)%2));
			
			//Sets up the button
			final GameSmoothButton gb = new GameSmoothButton(200, 50, Color.web("#004f90"), ""+choice, Color.WHITE, new Font("Cambria Bold", 24), gb1 -> {
				if (choice == n1*n2) {
					for (GameSmoothButton g : mc)
						GUI.pane.getChildren().remove(g);
					GUI.equation.setText(multiplication());
					GUI.correct();
				} else {
					GUI.incorrect();
				}
			});
			gb.setLayoutX(300);
			gb.setLayoutY(250 + 60*i);
			GUI.pane.getChildren().add(gb);
			mc[i] = gb;
		}
		
		return n1+" × "+n2+" = ?";
	}
	
	static String division() {
		
		//Generates the parts of the equation
		final int n2 = (int)(10*Math.random()+1) * (Math.random() < 0.5 ? -1 : 1);
		final int n1 = n2 * (int)(10*Math.random()+1) * (Math.random() < 0.5 ? -1 : 1);
		
		//Generates the randomness of answer choices
		final int add = Math.random() < 0.5 ? -1 : 1;
		final int sign = Math.random() < 0.5 ? -1 : 1;
		final int fs = Math.random() < 0.5 ? 0 : 1;
		
		//Sets up the multiple choice display
		final GameSmoothButton[] mc = new GameSmoothButton[4];
		for (int i = 0; i < 4; i++) {
			
			//Generates the answer choice
			final int choice = sign * (1-i/2*2) * (n1/n2 + add*((i%2 + fs)%2));
			
			//Sets up the button
			final GameSmoothButton gb = new GameSmoothButton(200, 50, Color.web("#004f90"), ""+choice, Color.WHITE, new Font("Cambria Bold", 24), gb1 -> {
				if (choice == n1/n2) {
					for (GameSmoothButton g : mc)
						GUI.pane.getChildren().remove(g);
					GUI.equation.setText(division());
					GUI.correct();
				} else {
					GUI.incorrect();
				}
			});
			gb.setLayoutX(300);
			gb.setLayoutY(250 + 60*i);
			GUI.pane.getChildren().add(gb);
			mc[i] = gb;
		}
		
		return n1+" / "+n2+" = ?";
	}

	static String negativeAddSub() {
		
		//Generates the parts of the equation
		final int n1, n2, op, ans;
		n1 = (int)(10*Math.random()+1) * (Math.random() < 0.5 ? -1 : 1);
		n2 = (int)(10*Math.random()+1) * (n1 > 0 || Math.random() < 0.5 ? -1 : 1);
		op = Math.random() < 0.5 ? -1 : 1;
		ans = n1 + op*n2;
		
		//Generates the randomness of answer choices
		final int add = Math.random() < 0.5 ? -1 : 1;
		final int sign = Math.random() < 0.5 ? -1 : 1;
		final int fs = Math.random() < 0.5 ? 0 : 1;
		
		//Sets up the multiple choice display
		final GameSmoothButton[] mc = new GameSmoothButton[4];
		for (int i = 0; i < 4; i++) {
			
			//Generates the answer choice
			final int choice = sign * (1-i/2*2) * (n1 + op*n2 + add*((i%2 + fs)%2));
			
			//Sets up the button
			final GameSmoothButton gb = new GameSmoothButton(200, 50, Color.web("#004f90"), ""+choice, Color.WHITE, new Font("Cambria Bold", 24), gb1 -> {
				if (ans == choice) {
					for (GameSmoothButton g : mc)
						GUI.pane.getChildren().remove(g);
					GUI.equation.setText(negativeAddSub());
					GUI.correct();
				} else {
					GUI.incorrect();
				}
			});
			gb.setLayoutX(300);
			gb.setLayoutY(250 + 60*i);
			GUI.pane.getChildren().add(gb);
			mc[i] = gb;
		}
		
		return n1+(op == 1 ? " + " : " - ")+(n2 < 0 ? "("+n2+")" : n2)+" = ?";
	}
	
	static String linearEquation() {
		
		//Calculates the parts of the equation
		final int coef, cons, eq, x;
		coef = (int)(11*Math.random()+2);
		cons = (int)(10*Math.random()+1) * (Math.random() < 0.5 ? -1 : 1);
		x = (int)(12*Math.random()+1);
		eq = (int) (coef*x+cons);
		
		//Calculates the lowest multiple choice option
		final int base = x + (int)(4*Math.random()-4);
		
		//Sets up the multiple choice display
		final GameSmoothButton[] mc = new GameSmoothButton[4];
		for (int i = 0; i < 4; i++) {
			final int index = i;
			
			//Sets up the button
			final GameSmoothButton gb = new GameSmoothButton(200, 50, Color.web("#004f90"), "x = "+(base+index), Color.WHITE, new Font("Cambria Bold", 24), gb1 -> {
				if (x == base+index) {
					for (GameSmoothButton g : mc)
						GUI.pane.getChildren().remove(g);
					GUI.equation.setText(linearEquation());
					GUI.correct();
				} else {
					GUI.incorrect();
				}
			});
			gb.setLayoutX(300);
			gb.setLayoutY(250 + 60*i);
			GUI.pane.getChildren().add(gb);
			mc[i] = gb;
		}
		
		return (coef+"x"+(cons < 0 ? " - " : " + ")+Math.abs(cons)+" = "+eq);
	}
	
	static String quadraticEquation() {
		
		//Generates x-values for the equation
		final int x1 = (int)(Math.random()*26-13);
		final int x2 = (int)(Math.random()*26-13);
		
		//Generates the beginning difference for the x-values
		final int dif1 = Math.random() < 0.5 ? 0 : -1;
		final int dif2 = Math.random() < 0.5 ? 0 : -1;
		
		//Sets up the multiple choice display
		final GameSmoothButton[] mc = new GameSmoothButton[4];
		for (int i = 0; i < 4; i++) {
			
			//Generates the x-values to be displayed
			final int disp1 = x1 + dif1 + i/2;
			final int disp2 = x2 + dif2 + i%2;
			
			//Sets up the button
			final GameSmoothButton gb = new GameSmoothButton(200, 50, Color.web("#004f90"), "x = "+disp1+", "+disp2, Color.WHITE, new Font("Cambria Bold", 24), gb1 -> {
				if (x1 == disp1 && x2 == disp2) {
					for (GameSmoothButton g : mc)
						GUI.pane.getChildren().remove(g);
					GUI.equation.setText(quadraticEquation());
					GUI.correct();
				} else {
					GUI.incorrect();
				}
			});
			gb.setLayoutX(300);
			gb.setLayoutY(250 + 60*i);
			GUI.pane.getChildren().add(gb);
			mc[i] = gb;
		}
		
		//Generates the linear term
		String linTerm = "";
		if (x1+x2 == -1)
			linTerm = " + ";
		else if (x1+x2 == 1)
			linTerm = " - ";
		else if (x1+x2 > 0)
			linTerm = " - "+(x1+x2);
		else if (x1+x2 < 0)
			linTerm = " + "+(-x1-x2);
		linTerm += "x";
		if (x1+x2 == 0)
			linTerm = "";
		
		//Generates the constant term
		int modCons = x1*x2 + (int)(22*Math.random()-11);
		String cons = "";
		if (modCons > 0)
			cons = " + "+modCons;
		else if (modCons < 0)
			cons = " - "+(-modCons);

		return "x^2"+linTerm+cons+" = "+(modCons - x1*x2);
	}
}
