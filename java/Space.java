import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class Space{
	Player players[] = new Player[4];
	Shape cir;
	Shape wLine;
	Shape hLine;
	int x;
	int y;
	public Space(int x, int y){
		this.x = x;
		this.y = y;
		cir = new Ellipse2D.Double(x, y, 63, 68);
		wLine = new Line2D.Double(x, y+68/2, x+63, y+68/2);
		hLine = new Line2D.Double(x+63/2, y, x+63/2, y+68);
		
	}
	
	public Shape getSpace(){
		return cir;
	}
	
	public Shape divHeight(){
		return hLine;
	}
	
	public Shape divWidth(){
		return wLine;
	}
	
	public void drawSpace(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(cir);
		g2d.draw(wLine);
		g2d.draw(hLine);
	}
	
	public void wasClicked(MouseEvent e){
		if(cir.contains(e.getPoint())){
			System.out.println( this + " clicked");
		}
	}
	
}