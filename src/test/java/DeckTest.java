import org.junit.Test;
import static org.junit.Assert.*;
import wos.*;

public class DeckTest{
    Deck test = new Deck();
	Card mcard = new Card(3);
	Card bcard = new Card(5);
    
    //This tests the size of the deck. The size should be 70
    @Test
    public void checkSize(){
        assertEquals(test.sizeOfDeck(), 70);
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
	
	/**Testing the new special cards
	   new Card(3) should be mountain
	 */
	@Test
	public void testMountain(){
		assertEquals(mcard.specText, "mountain");
	}
	
	/**Testing the new special cards
	  new Card(5) should be bridge
	*/
	@Test
	public void testBridge(){
		assertEquals(bcard.specText, "bridge");
	}
	
}