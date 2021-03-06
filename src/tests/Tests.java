package tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import game.Board;
import game.Fraction;
import game.MixedNumber;
import game.ZeroDenomException;
import game.Problems.Core1Problem;
import game.Problems.Core2Problem;
import game.Problems.Core3BProblem;
import game.Problems.Core3CProblem;
import game.Problems.Core4Problem;
import game.Problems.Core5Problem;
import game.Player;

public class Tests {
	
	private static Board board;

	@Before
	public void initialize(){
		board = new Board();
		board.initialize();
	}
	
	// Test that initializing a fraction with a denominator of zero results in an exception
	@Test(expected=Exception.class)
	public void testDivByZeroException() throws ZeroDenomException {
		new Fraction(5, 0);
	}
	
	// Test that setting the denominator of a fraction results in an exception
	@Test(expected=Exception.class)
	public void testDivByZeroException_2() throws ZeroDenomException {
		Fraction badFrac = new Fraction(5, 2);
		badFrac.setDenominator(0);
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
	public void CPUMovesCorrectly() throws ZeroDenomException{
		Core1Problem fraction1 = new Core1Problem();
		board.setCurrentProblem(fraction1);
		// Testing wrong answer
		board.movePlayer();
		assertEquals(0, Board.getPlayers().get(0).getLocation());	//Make sure the correct player moved
		assertEquals(1, Board.getPlayers().get(1).getLocation());	//Make sure no other player moved
		assertEquals(0, Board.getPlayers().get(2).getLocation());
		assertEquals(0, Board.getPlayers().get(3).getLocation());
		
		// Testing correct answer
		fraction1.setAnswer(fraction1.getExpectedAnswer());
		board.movePlayer();
		assertEquals(1, Board.getPlayers().get(0).getLocation());
		assertEquals(1, Board.getPlayers().get(1).getLocation());
		assertEquals(0, Board.getPlayers().get(2).getLocation());
		assertEquals(0, Board.getPlayers().get(3).getLocation());
		
		Core2Problem fraction2 = new Core2Problem();
		board.setCurrentProblem(fraction2);
		// Testing wrong answer
		board.movePlayer();
		assertEquals(1, Board.getPlayers().get(0).getLocation());
		assertEquals(1, Board.getPlayers().get(1).getLocation());
		assertEquals(1, Board.getPlayers().get(2).getLocation());
		assertEquals(0, Board.getPlayers().get(3).getLocation());
		// Testing correct answer
		fraction2.setAnswer(fraction2.getExpectedAnswer());
		board.movePlayer();
		assertEquals(2, Board.getPlayers().get(0).getLocation());
		assertEquals(1, Board.getPlayers().get(1).getLocation());
		assertEquals(1, Board.getPlayers().get(2).getLocation());
		assertEquals(0, Board.getPlayers().get(3).getLocation());
		
		Core3BProblem fraction3 = new Core3BProblem();
		board.setCurrentProblem(fraction3);
		// Testing wrong answer
		board.movePlayer();
		assertEquals(2, Board.getPlayers().get(0).getLocation());
		assertEquals(1, Board.getPlayers().get(1).getLocation());
		assertEquals(1, Board.getPlayers().get(2).getLocation());
		assertEquals(1, Board.getPlayers().get(3).getLocation());
		// Testing correct answer
		fraction3.setAnswer(fraction3.getExpectedAnswer());
		board.movePlayer();
		assertEquals(3, Board.getPlayers().get(0).getLocation());
		assertEquals(1, Board.getPlayers().get(1).getLocation());
		assertEquals(1, Board.getPlayers().get(2).getLocation());
		assertEquals(1, Board.getPlayers().get(3).getLocation());
		
		Core3CProblem fraction4 = new Core3CProblem();
		board.setCurrentProblem(fraction4);
		// Testing wrong answer
		board.movePlayer();
		assertEquals(3, Board.getPlayers().get(0).getLocation());
		assertEquals(1, Board.getPlayers().get(1).getLocation());
		assertEquals(2, Board.getPlayers().get(2).getLocation());
		assertEquals(1, Board.getPlayers().get(3).getLocation());
		
		// Testing correct answer
		fraction4.setAnswer(fraction4.getExpectedAnswer());
		board.movePlayer();
		assertEquals(4, Board.getPlayers().get(0).getLocation());
		assertEquals(1, Board.getPlayers().get(1).getLocation());
		assertEquals(2, Board.getPlayers().get(2).getLocation());
		assertEquals(1, Board.getPlayers().get(3).getLocation());
		
		Core4Problem fraction5 = new Core4Problem();
		board.setCurrentProblem(fraction5);
		// Testing wrong answer
		board.movePlayer();
		assertEquals(4, Board.getPlayers().get(0).getLocation());
		assertEquals(1, Board.getPlayers().get(1).getLocation());
		assertEquals(2, Board.getPlayers().get(2).getLocation());
		assertEquals(2, Board.getPlayers().get(3).getLocation());
		
		// Testing correct answer
		fraction5.setAnswer(fraction5.getExpectedAnswer());
		board.movePlayer();
		assertEquals(5, Board.getPlayers().get(0).getLocation());
		assertEquals(1, Board.getPlayers().get(1).getLocation());
		assertEquals(2, Board.getPlayers().get(2).getLocation());
		assertEquals(2, Board.getPlayers().get(3).getLocation());
		
		Core5Problem fraction6 = new Core5Problem();
		board.setCurrentProblem(fraction6);
		// Testing wrong answer
		board.movePlayer();
		assertEquals(5, Board.getPlayers().get(0).getLocation());
		assertEquals(2, Board.getPlayers().get(1).getLocation());
		assertEquals(2, Board.getPlayers().get(2).getLocation());
		assertEquals(2, Board.getPlayers().get(3).getLocation());
		
		// Testing right answer
		fraction6.setAnswer(fraction6.getExpectedAnswer());
		board.movePlayer();
		assertEquals(6, Board.getPlayers().get(0).getLocation());
		assertEquals(2, Board.getPlayers().get(1).getLocation());
		assertEquals(2, Board.getPlayers().get(2).getLocation());
		assertEquals(2, Board.getPlayers().get(3).getLocation());
	}
	
	@Test
	public void gameEndsRight(){
		//Make sure the game ends when ANY player is at the end
		Board.getPlayers().get(0).setLocation(Board.MAX_SIZE);
		assertTrue(board.checkOver());
		Board.getPlayers().get(0).setLocation(0);
		Board.getPlayers().get(1).setLocation(Board.MAX_SIZE);
		assertTrue(board.checkOver());
		Board.getPlayers().get(1).setLocation(0);
		Board.getPlayers().get(2).setLocation(Board.MAX_SIZE);
		assertTrue(board.checkOver());
		Board.getPlayers().get(2).setLocation(0);
		Board.getPlayers().get(3).setLocation(Board.MAX_SIZE);
		assertTrue(board.checkOver());
		// Make sure the game doesn't end if no one is at the end
		Board.getPlayers().get(3).setLocation(0);
		assertFalse(board.checkOver());
		
	}
	
	@Test
	public void TestCore1() throws ZeroDenomException{
		Fraction core1Fraction1 = new Fraction(1, 2);
		Fraction core1Fraction2 = new Fraction(6, 12);
		Fraction core1Fraction3 = new Fraction(3, 4);
		assertTrue(core1Fraction1.getNumerator()*core1Fraction2.getDenominator() == core1Fraction1.getDenominator()*core1Fraction2.getNumerator());
		assertTrue(core1Fraction1.equals(core1Fraction2));
		assertFalse(core1Fraction1.equals(core1Fraction3));
	}
	
	@Test
	public void TestCore2() throws ZeroDenomException{
		Fraction core2Fraction1 = new Fraction(1,2);
		Fraction core2Fraction2 = new Fraction(2,4);
		assertTrue(core2Fraction1.getFraction().equals(core2Fraction2.getFraction()));
		
		Fraction core2Fraction3 = new Fraction(1,5);
		Fraction core2Fraction4 = new Fraction(3,4);
		assertTrue((core2Fraction3.getValue() < core2Fraction4.getValue()));
		
		Fraction core2Fraction5 = new Fraction(2,3);
		Fraction core2Fraction6 = new Fraction(1,4);
		assertTrue(core2Fraction5.getValue() > core2Fraction6.getValue());
		
	}
	
	@Test
	public void TestCore3b() throws ZeroDenomException{
		Fraction core3bFraction1 = new Fraction(1, 4);
		Fraction core3bFraction2 = new Fraction(2, 8);
		Fraction core3bFraction3 = new Fraction(3, 8);
		Fraction core3bFraction4 = new Fraction(7, 8);

		assertFalse(core3bFraction1.commonDenominator(core3bFraction2));
		assertTrue(core3bFraction2.commonDenominator(core3bFraction3));
		assertEquals(core3bFraction1.leastCommonDenominator(core3bFraction2), 8);
		try {
			assertTrue(core3bFraction1.changeDenomiator(core3bFraction1.leastCommonDenominator(core3bFraction2)).equals(core3bFraction2));
			assertTrue(core3bFraction1.addition(core3bFraction2.addition(core3bFraction3)).equals(core3bFraction4));
		} catch (ZeroDenomException e) {
			fail("Zero denominator");
		}
	}
	
	@Test
	public void TestCore3c() throws ZeroDenomException{
		MixedNumber core3cMixed1 = new MixedNumber(1, 8, 0);
		MixedNumber core3cMixed2 = new MixedNumber(2, 4, 1);
		MixedNumber core3cMixed3 = new MixedNumber(5, 8, 1);
		MixedNumber core3cMixed4 = new MixedNumber(3, 6, 2);
		MixedNumber core3cMixed5 = new MixedNumber(0, 12, 4);
		
		Fraction core3cFraction1 = core3cMixed1.toFraction();
		Fraction core3cFraction2 = core3cMixed2.toFraction();
		Fraction core3cFraction3 = core3cMixed3.toFraction();
		Fraction core3cFraction4 = core3cMixed4.toFraction();
		Fraction core3cFraction5 = core3cMixed5.toFraction();
		
		Fraction testResult1 = core3cFraction1.addition(core3cFraction2);
		Fraction testResult2 = core3cFraction2.addition(core3cFraction4);

		assertTrue(testResult1.equals(core3cFraction3));
		assertTrue(testResult1.equals(core3cMixed3));
		assertTrue(testResult2.equals(core3cFraction5));
		assertTrue(testResult2.equals(core3cMixed5));
	}
	
	@Test
	public void TestCore4() throws ZeroDenomException{
		int wholeNumber = 2;
		Fraction core4Fraction1 = new Fraction(17, 8);
		Fraction core4Fraction2 = new Fraction(34, 8);
		MixedNumber core4Mixed1 = new MixedNumber(core4Fraction1.getNumerator() % core4Fraction1.getDenominator(), core4Fraction1.getDenominator(), wholeNumber);
		
		assertTrue(core4Fraction1.equals(core4Mixed1));
		assertTrue(core4Mixed1.equals(core4Fraction1));
		assertTrue(core4Fraction1.intMultiplication(wholeNumber).equals(core4Fraction2));
	}
	
	@Test
	public void TestCore5() throws ZeroDenomException{
		 Fraction core5Fraction1 = new Fraction(2,10);
		 Fraction core5Fraction2 = new Fraction(20,100);
		 Fraction core5Fraction3 = new Fraction(5,100);
		 Fraction core5FractionSolution = new Fraction(25,100);
		 assertTrue(core5Fraction1.equals(core5Fraction2));
		 assertTrue(core5Fraction1.addition(core5Fraction3).equals(core5FractionSolution));
	}

}
