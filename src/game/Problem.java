package game;

import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Problem class:
 * Abstract, parent of Core1Problem, Core2Problem, Core3BProblem, Core3CProblem, Core4Problem, Core5Problem
 * Contains abstract functions: void draw(), boolean isCorrect()
 * Contains empty constructor
 */

@SuppressWarnings("serial")
public abstract class Problem extends JPanel{
	protected LinkedList<Fraction> fractions = new LinkedList<Fraction>();
	protected int answer = -1; // this will never be -1 for an actual problem
	protected int expectedAnswer;
	protected JTextField blankAnswer;
	protected boolean involvesKeypad;
	
	public boolean involvesKeypad() {
		return involvesKeypad;
	}

	public Problem() {
		involvesKeypad = true; // there are more problems that involve it than not
	}
	
	public abstract void advanceComputer(LinkedList<Player> players);

	public boolean isCorrect() {
		if(answer == expectedAnswer)
			return true;
		return false;
	}
	
	public void setAnswer(int answer) {
		this.answer = answer;
		if(involvesKeypad) {
			this.blankAnswer.setText(Integer.toString(this.answer));
			this.blankAnswer.repaint();
		}
	}

	public int getExpectedAnswer() {
		return expectedAnswer;
	}
}
