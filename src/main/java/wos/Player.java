package wos;

import wos.*;
import java.io.Serializable;


public class Player implements Serializable{
	String name;
	String color;
	Space currentSpace;
	int[] coordinates = new int[2];
	int number;
	int boomerangs = 3;
	boolean isAI = false;
	boolean onSpecial;
	
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
	
	public void decrementBooms(){
		if (boomerangs > 0){
			boomerangs--;
		}
	}
	
	public boolean haveBoomerangs(){
		return boomerangs > 0;
	}
	
	public boolean checkWin(){
		if (currentSpace.isGoal()){
			return true;
		} else{
			return false;
		}
	}
	
	public void setAI(){
		isAI = true;
	}
	
	public boolean isAI(){
		return isAI;
	}
}