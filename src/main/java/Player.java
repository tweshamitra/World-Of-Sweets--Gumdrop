public class Player{
	String name;
	String color;
	public Player(String pname, String pcolor){
		name = pname;
		color = pcolor;
	}


	public void updateLocation(int[] newCoords, Space s)
	{
		coordinates = newCoords;
		currentSpace = s;
	}
	
	public int[] getCurrentLocation(){
		return coordinates;
	}
	
	public Space getCurrentSpace(){
		return currentSpace;
	}
}