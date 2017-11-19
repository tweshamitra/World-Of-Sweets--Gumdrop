package wos;

import wos.*;
import javax.swing.*;

public class BoardTest{
	
	static GameGUI thegoo = new GameGUI();
	public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {                
               boardTest();
            }
        });
	}
	
		
	static void boardTest()
	{
		thegoo.testResetBoard();
		(new boardTestThread()).execute();
	}
	
	static class boardTestThread extends SwingWorker<Void, Object> {
	    @Override
		public Void doInBackground() {
			Board gameBoard = thegoo.theGame.gameBoard;
			for(Space s : gameBoard.gameSpaces)
			{
				System.out.println(s.label);
			}
		
			return null;
		}
	}
	
}