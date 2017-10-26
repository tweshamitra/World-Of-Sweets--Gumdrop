import java.util.*;

public class Deck{
	ArrayList<Card> thedeck = new ArrayList<Card>();
	String[] colors = {"red", "blue", "orange", "yellow", "green"};
	
	public Deck()
	{
		shuffleDeck();
	}
	public Card drawCard()
	{
		if(this.isDeckEmpty())
			return null;
		else
			return thedeck.remove(0);
	}
	public boolean isDeckEmpty()
	{
		return thedeck.size() == 0;
	}
	public int sizeOfDeck()
	{
		return thedeck.size();
	}
	public boolean shuffleDeck()
	{
		if(!isDeckEmpty())
		{
			return false;
		}
		int iterations = 2;
		Card acard;
		
		Random rand = new Random();
		for(int x = 0; x < iterations; x ++)
		{
			for(int y = 0; y < colors.length; y++)
			{
				for(int z = 0; z < 6; z++)
				{
					thedeck.add( rand.nextInt(thedeck.size()+1) , new Card(colors[y], (z == 0) ? true : false) );
				}
			}				
		}
		
		return true;
	}
}