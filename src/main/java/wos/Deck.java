package wos;

import java.util.*;
import java.io.Serializable;

import wos.*;

public class Deck implements Serializable{
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
		//add five skips
		for(int s = 0; s < 5; s ++)
		{
			thedeck.add( rand.nextInt(thedeck.size()+1) , new Card(1) );
		}
		//add each of the movement cards
		thedeck.add( rand.nextInt(thedeck.size()+1) , new Card(2) );
		thedeck.add( rand.nextInt(thedeck.size()+1) , new Card(3) );
		thedeck.add( rand.nextInt(thedeck.size()+1) , new Card(4) );
		thedeck.add( rand.nextInt(thedeck.size()+1) , new Card(5) );
		thedeck.add( rand.nextInt(thedeck.size()+1) , new Card(6) );
	
		
		return true;
	}
	
	//Given a players position as an double, draw worst possible remaining card in deck.  
	//44.5 = Chocolate Falls
	//22.5 = Lollipop Mountain
	//50.5 = Cupcake Forest
	//45.5 = Brittle Bridge
	//38.5 = Licorice Lake
	public Card drawWorstCard(double position)
	{
		//Searching for the Card that gives the worst position possible
		if(isDeckEmpty())
		{
			return null;
		}
		double toBeat = 100;
		int toReturn = 0;
		String[] order = {"orange", "red", "yellow", "blue", "green"};
		double[] specialPos = {position, 44.5, 22.5, 50.5, 45.5, 38.5};
		int curColor = ((int)Math.floor(position))%5;
		try{
		for(int x = 0; x < sizeOfDeck(); x++) //not using foreach because i need the index later
		{
			Card current = thedeck.get(x);
			if(!current.special)
			{
				int offset;
				double afinale = position;
				switch(order[curColor]) 
				{
					case "red": offset = 1;
							break;
					case "yellow":  offset = 2;
							break;
					case "blue":  offset = 3;
							break;
					case "green":  offset = 4;
							break;
					case "orange":  offset = 0;
							break;
					default: offset = 0;  //error
							break;
				}
				int times = 1;
				if(current.isdouble)
					times = 2;
				int found = 0;
				do{
					offset++;
					afinale++;
					if(offset > 4) offset = 0;
					if(order[offset].equals(current.color)) found++;
					
				}while(!order[offset].equals(current.color) || found < times);
				
				if (afinale < toBeat)
				{
					toBeat = afinale;
					toReturn = x;
				}
			}
			else
			{
				double aNum = specialPos[current.specNum-1];
				if(aNum < toBeat) {
					toBeat = aNum;
					toReturn = x;
				}
			}
			
		}
		return(thedeck.remove(toReturn));
		}catch(Exception e) { e.printStackTrace(); }
		return null;
	}
}