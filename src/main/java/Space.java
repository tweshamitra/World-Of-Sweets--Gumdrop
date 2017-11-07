import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.*;

public class Space{
	Player players[] = new Player[4];
	String color;
	String label;
	Shape cir;
	boolean start = false;
	boolean goal = false;
	int x, y, width, height;
	int playerIndex = 0;
	int positionA[] = new int[2];
	int positionB[] = new int[2];
	int positionC[] = new int[2];
	int positionD[] = new int[2];
	Shape horizontal;
	Shape vertical;
	
	public Space(int x, int y, boolean start, boolean goal, String color, int labelInt){
		this.x = x;
		this.y = y;
		this.start = start;
		this.goal = goal;
		this.color = color;
		label = color + "-" + labelInt;
		horizontal = new Line2D.Double(x+63/2, y, x+63/2, y+68);
		vertical = new Line2D.Double(x, y+68/2, x+63, y+68/2);
		if(start){
			cir = new Ellipse2D.Double(x, y, 106, 113);
			width = 106;
			height = 113;
			label = "start";
		}else if(goal){
			cir = new Rectangle2D.Double(x, y, 140, 135);
			width = 140;
			height = 145;
			label = "goal";
		}else{
			cir = new Ellipse2D.Double(x, y, 63, 68);
			width = 63;
			height = 68;
		}
		
		if(start){
			positionA[0] = x+width*2/10;
			positionA[1] = y+height*2/10;
			
			positionB[0] = x+width*6/10;
			positionB[1] = y+height*2/10;
			
			positionC[0] = x+width*2/10;
			positionC[1] = y+height*6/10;
			
			positionD[0] = x+width*6/10;
			positionD[1] = y+height*6/10;
		
		}
		else{
			positionA[0] = x+width*(1/3);
			positionA[1] = y+height*(1/3);
			
			positionB[0] = x+width*3/5;
			positionB[1] = y+height*(1/3);
			
			positionC[0] = x+width*(1/3);
			positionC[1] = y+height*3/7;
			
			positionD[0] = x+width*3/5;
			positionD[1] = y+height*3/7;
		}
		
	}
	
	// Will return an xy pair for one of the four free spaces within a space
	public int[] nextFreeSpace(int playerNum){
		int[] ret = new int[2];
		switch(playerNum){
			case 0:
			ret = positionA;
			break;
			
			case 1:
			ret = positionB;
			break;
			
			case 2:
			ret = positionC;
			break;
			
			case 3:
			ret = positionD;
			break;
		}
		return ret;
	}
	
	public Shape getHorizontal(){
		return horizontal;
	}
	
	public Shape getVertical(){
		return vertical;
	}
	
	public boolean contains(int x, int y){
		double dX = (double) x;
		double dY = (double) y;
		return cir.contains(dX, dY);
	}
	
	public int getPIndex(){
		return playerIndex;
	}
	
	// Used in defining the dimensions of the space on the gameboard
	public Shape getSpace(){
		return cir;
	}
	
	public String getColor(){
		return color;
	}
	
	public boolean wasClicked(MouseEvent e){
		return cir.contains(e.getPoint());
	}
	
	public boolean isStart(){
		return start;
	}
	
	public boolean isGoal(){
		return goal;
	}
	
	public String getLabel(){
		return label;
	}
	
}