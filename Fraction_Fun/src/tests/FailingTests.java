package tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import game.Board;
import game.Player;

public class FailingTests {
	
	private static Board board;

	@Before
	public void initialize(){
		board = new Board();
		board.initialize();
	}
	
	// Test makes sure the human Player moves correctly
	@Test
	public void playerMovesOneForward() {
		Player humanPlayer = new Player();
		humanPlayer.move();
		assertEquals(1, humanPlayer.getLocation());	//Player started at 0 and moved once, so should be at location 1
		humanPlayer.move();
		assertEquals(2, humanPlayer.getLocation());	// PLayer moved again, so now should be at location 2
	}
	
	// Tests make sure the computer players move as expected
	@Test
	public void CPUMovesCorrectly(){
		
	}
	
	@Test
	public void gameEndsRight(){
		
	}
	
	@Test
	public void TestCore1(){
		
	}
	
	@Test
	public void TestCore2(){
		
	}
	
	@Test
	public void TestCore3b(){
		
	}
	
	@Test
	public void TestCore3c(){
		
	}
	
	@Test
	public void TestCore4(){
		
	}
	
	@Test
	public void TestCore5(){
		
	}

}
