import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import wos.*;
public class GameTest{

    Game testGame;
    @Before
    public void setup(){
        testGame = new Game(2);
    }
    /**
        This tests the getCurPlayerName method of Game. 
     */
    @Test
    public void testCurPlayerName(){
        testGame.setPlayer(0, "Twesha", "Human", "Red");
        testGame.setPlayer(1, "Jessica", "Human",  "Green");
        assertNotNull(testGame.getCurPlayerName());
    }
    /**
        This tests makes sure that when getCard is called, the object returned is a Card object and not null.
     */
    @Test
    public void testGetCard(){
        assertNotNull(testGame.drawCard());
    }

    @Test
    public void testGetPlayerName(){
        testGame.setPlayer(0, "Twesha", "Human",  "Red");
        assertEquals("Twesha", testGame.getPlayerName(0));
    }

    @Test
    public void testGetPlayerColor(){
        testGame.setPlayer(0, "Twesha", "Human",  "Red");
        assertEquals("Red", testGame.getPlayerColor(0));
    }
}