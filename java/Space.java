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
	Shape cir;
	boolean clicked = false;
	boolean start = false;
	boolean goal = false;
	int x;
	int y;
	int playerIndex = 0;
	int positionA[] = new int[2];
	int positionB[] = new int[2];
	int positionC[] = new int[2];
	int positionD[] = new int[2];
	
	
	public Space(int x, int y, boolean start, boolean goal){
		this.x = x;
		this.y = y;
		this.start = start;
		this.goal = goal;
		if(start){
			cir = new Ellipse2D.Double(x, y, 106, 113);
		}else if(goal){
			cir = new Rectangle2D.Double(x, y, 140, 135);
		}else{
			cir = new Ellipse2D.Double(x, y, 63, 68);
		}
		// wLine = new Line2D.Double(x, y+68/2, x+63, y+68/2);
		// hLine = new Line2D.Double(x+63/2, y, x+63/2, y+68);
		
		positionA[0] = x+63*(1/3);
		positionA[1] = y+68*(1/3)-10;
		
		positionB[0] = x+63*3/5;
		positionB[1] = y+68*(1/3)-10;
		
		positionC[0] = x+63*(1/3);
		positionC[1] = y+68*2/7;
		
		positionD[0] = x+63*3/5;
		positionD[1] = y+68*2/7;
		
	}
	
	public int[] nextFreeSpace(){
		int[] ret = new int[2];
		switch(playerIndex){
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
	
	public int getPIndex(){
		return playerIndex;
	}
	
	public void setGoal(){
		goal = true;
	}
	
	public void setStart(){
		start = true;
	}
	
	public Shape getSpace(){
		return cir;
	}
	
	// public Shape divHeight(){
		// return hLine;
	// }
	
	// public Shape divWidth(){
		// return wLine;
	// }
	
	public void wasClicked(MouseEvent e){
		if(cir.contains(e.getPoint())){
			clicked = true;
			System.out.println( this + " clicked");
		}
	}
	
	public boolean isStart(){
		return start;
	}
	
	public boolean isGoal(){
		return goal;
	}
	
	public boolean clickedStatus(){
		return clicked;
	}
	
	public int[] addPlayer(){
		int ret[] = new int[2];
		ret[0] = x+63*(1/3);
		ret[1] = y+68*(1/3)-10;
		System.out.println("ret[0]: " + ret[0]);
		System.out.println("ret[1]: " + ret[1]);
		clicked = false;
		return ret;
	}
	
}