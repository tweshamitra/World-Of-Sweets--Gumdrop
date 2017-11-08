package wos;

import wos.*;

public class Player{
	String name;
	String color;
	Space currentSpace;
	int[] coordinates = new int[2];
	int number;
	public Player(String pname, String pcolor){
		name = pname;
		color = pcolor;
	}

	public void updateLocation(int[] newCoords, Space s)
	{
		coordinates = newCoords;
		currentSpace = s;
	}
	public String getPlayerName(){
		return this.name;
	}
	public int[] getCurrentLocation(){
		return coordinates;
	}
	
	public Space getCurrentSpace(){
		return currentSpace;
	}
	
	public boolean checkWin(){
		if (currentSpace.isGoal()){
			return true;
		} else{
			return false;
		}
	}
}