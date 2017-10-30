public class Player{
	String name;
	String color;
	Space currentSpace;
	int number;
	public Player(String pname, String pcolor){
		name = pname;
		color = pcolor;
		//TODO: Addison: initialize and represent location in this class in whatever way
	}

	public void updateLocation(Space s)
	{
		//Again, however you want to do it
		currentSpace = s;
	}
}