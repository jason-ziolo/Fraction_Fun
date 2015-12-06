package game;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.Problems.Core1Problem;
import game.Problems.Core2Problem;
import game.Problems.Core3BProblem;
import game.Problems.Core3CProblem;
import game.Problems.Core4Problem;
import game.Problems.Core5Problem;

@SuppressWarnings("serial")
public class ProblemDisplay extends JDialog {
	public static final int HEIGHT = 500;
	public static final int WIDTH = 500;
	private Numpad pad;
	private Problem problem;

	public ProblemDisplay(){
		setName("Problem");
		setTitle("Problem");
		setSize(WIDTH, HEIGHT);
		this.setLayout(new GridLayout(0,2));
		pad = new Numpad(this);
		try {
			makeQuestion();
		} catch (ZeroDenomException e) {
			System.out.println(e.getMessage());
		}
		add(pad);
		add(problem);
	}
	
	public void makeQuestion() throws ZeroDenomException{
		Random rand = new Random();
		int question = 2;	//TODO: Change back to rand.nextInt(6) + 1
		switch(question){
			case 1: setProblem(new Core1Problem());
					break;
			case 2: setProblem(new Core2Problem());
					break;
			case 3: setProblem(new Core3BProblem());
					break;
			case 4: setProblem(new Core3CProblem());
					break;
			case 5: setProblem(new Core4Problem());
					break;
			case 6: setProblem(new Core5Problem());
					break;
		}
		pad.setKeypadEnabled(problem.involvesKeypad());
		System.out.println(problem + " " + problem.getExpectedAnswer()); // TODO: Remove
	}
	
	public void setProblem(Problem problem){
		this.problem = problem;
	}
	
	public Problem getProblem(){
		return problem;
	}
	
	public void noDisplay(){
		setVisible(false);
	}
	
	public class Numpad extends JPanel{
		
		private LinkedList<KeypadButton> buttons = new LinkedList<KeypadButton>();
		private ProblemDisplay display;
		
		private int answer = 0;
		
		public Numpad(ProblemDisplay display){
			this.display = display;
			this.setLayout(new GridLayout(0,3));
			buttons = new LinkedList<KeypadButton>();
			initializeButtons();
			for (KeypadButton button : buttons){
				add(button); // Adding actual JButton here
			}
		}
		
		public void updateAnswer(){
			problem.setAnswer(answer);
			//answerText.setText(Integer.toString(answer));
		}
		
		public void buttonPressed(int pressed){
			answer = answer * 10 + pressed;
			updateAnswer();
		}
		
		public void clearAnswer(){
			answer = 0;
		}
		
		public void initializeButtons(){
			// Buttons corresponding to the numbers
			for (int i = 0; i < 10; i++){
				KeypadButton button = new KeypadButton(i, Integer.toString(i));
				button.addActionListener(new ButtonListener());
				buttons.add(button);
			}
			// Submit button
			String name = "Submit";
			KeypadButton button = new KeypadButton(0, name);
			button.addActionListener(new ButtonListener());
			buttons.add(button);
			// Clear button
			name = "Clear";
			button = new KeypadButton(0, name);
			button.addActionListener(new ButtonListener());
			buttons.add(button);
		}
		
		public void setKeypadEnabled(boolean b) {
			for(KeypadButton i : buttons) {
				if(i.getName().equals("Submit")) {	// For those problems with a drop down, the submit button
					continue;						// is the only one which stays highlighted
				}
				i.setEnabled(b);
			}
		}
		
		public class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String name = ((Component) e.getSource()).getName();
				if (name.equals("Clear")){			// Clear
					clearAnswer();
					updateAnswer();
				} else if(name.equals("Submit")){	// Submit
					//display.getProblem().setAnswer(answer);
					clearAnswer();
					Game.checkAnswer(problem);
					updateAnswer();
					noDisplay();
				} else {
					buttonPressed(((KeypadButton) e.getSource()).getNumValue());
				}
			}
		}
	}
}
