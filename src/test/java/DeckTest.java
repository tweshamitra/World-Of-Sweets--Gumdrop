import org.junit.Test;
import static org.junit.Assert.*;
import wos.*;

public class DeckTest{
    Deck test = new Deck();
    
    //This tests the size of the deck. The size should be 68
    @Test
    public void checkSize(){
        assertEquals(test.sizeOfDeck(), 68);
    }
    
    /** This tests what is returned when drawCard is called. 
        An object should be returned, so it should not be null
    */
    @Test
    public void testDrawCard(){
        assertNotNull(test.drawCard());
    }
    /**
        Since we created a Deck object, the deck should not be empty.
        Thus, it should not return null.
     */
    @Test
    public void testIsDeckEmpty(){
        assertFalse(test.isDeckEmpty());
    }
}