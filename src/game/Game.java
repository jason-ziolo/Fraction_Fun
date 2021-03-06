package game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Game extends JFrame{
	private static Board board;
	private static DisplayPanel display;
	public static final int SIZE = 500;
	public static final int PADDING = 100;

	public Game(){
		this.setJMenuBar(mainJMenuBar());
		setName("Fraction Fun!");
		setTitle("Fraction Fun!");
		setSize(SIZE + PADDING, SIZE * 2 / 3 + PADDING);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		board = new Board();
		display = new DisplayPanel();
		Game game = new Game();
		game.add(board, BorderLayout.CENTER);
		game.add(display, BorderLayout.SOUTH);
		game.setVisible(true);
		
		while(!board.checkOver()){
		}
		
		board.repaint();
		String message;
		Player winner = null;
		for (Player players : Board.getPlayers()){
			if (players.getLocation() == Board.MAX_SIZE){
				winner = players;
				break;
			}
		}
		if (winner.equals(Board.getPlayers().get(0))){
			message = "You win the game!";
		} else {
			message = "The computer wins. You have want to brush up on the following cores: ";
			if (winner.equals(Board.getPlayers().get(1))){
				message += "1 and 5.";
			} else if(winner.equals(Board.getPlayers().get(2))){
				message += "2 and 3C.";
			} else if(winner.equals(Board.getPlayers().get(3))){
				message += "3B and 4.";
			}
			message += '\n' + "Visit the following website for more information: http://www.free-test-online.com/ccss/grade4/grade4_fractions.html";
		}
		JOptionPane.showMessageDialog(board, message);
		System.exit(1);
	}
	
	public static void checkAnswer(Problem problem){
		board.setCurrentProblem(problem);
		board.movePlayer();
		if (board.getCurrentProblem().isCorrect()){
			JOptionPane.showMessageDialog(board, "Correct!");
		} else {
			JOptionPane.showMessageDialog(board, "Incorrect: Expected answer was " + board.getCurrentProblem().getExpectedAnswerStr() + ".");
		}
		display.newDisplay();
	}
	
	private JMenuBar mainJMenuBar() {
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menu.add(createFileExitItem());
		bar.add(menu);
		return bar;
	}
	
	private JMenuItem createFileExitItem() {
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	
}
