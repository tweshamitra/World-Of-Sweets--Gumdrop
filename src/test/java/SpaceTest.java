import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class SpaceTest{
	
	Game testGame;
	@Before
	public void setup(){
		testGame = new Game(3);
	}
	
	@Test
	public void testColors(){
		for(int i = 0; i < testGame.gameBoard.NUMBER_OF_SPACES; i++){
			if(i == 0){
				assertEquals("start", testGame.gameBoard.gameSpaces[i].getColor());
			}
			if(i == 31){
				assertEquals("goal", testGame.gameBoard.gameSpaces[i].getColor());
			}
			else{
				switch(i%5){
					case 0: assertEquals("orange", testGame.gameBoard.gameSpaces[i].getColor());
					break;
					
					case 1: assertEquals("red", testGame.gameBoard.gameSpaces[i].getColor());
					break;
					
					case 2: assertEquals("yellow", testGame.gameBoard.gameSpaces[i].getColor());
					break;
					
					case 3: assertEquals("blue", testGame.gameBoard.gameSpaces[i].getColor());
					break;
					
					case 4: assertEquals("green", testGame.gameBoard.gameSpaces[i].getColor());
					break;
				}
			}
		}
	}
	
	@Test
	public void testNumOfSpaces(){
		assertEquals(32, testGame.gameBoard.NUMBER_OF_SPACES);
	}
}