import java.util.*;

public class Card{
	String color;
	boolean isdouble;
	boolean special;
	
	public Card(String c, boolean d) //throws InvalidColorException
	{
		c = c.toLowerCase();
		if(!(c.equals("red") || c.equals("blue") || c.equals("orange") || c.equals("yellow") || c.equals("green")))
		{
			//throw new InvalidColorException("Color must be red, blue, green, yellow, or orange!");
			System.out.println("Color must be red, blue, green, yellow, or orange!");
			return;
		}
		color = c;
		isdouble = d;
		special = false;
	}
}