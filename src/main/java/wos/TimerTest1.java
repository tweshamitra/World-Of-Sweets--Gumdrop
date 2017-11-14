package wos;

import wos.*;
import javax.swing.*;

public class TimerTest1{
	
	static GameGUI thegoo = new GameGUI();
	public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {                
               timerTest();
            }
        });
	}
	
		
	static void timerTest()
	{
		thegoo.testResetBoard();
		(new timerTestThread()).execute();
	}
	
	static class timerTestThread extends SwingWorker<Void, Object> {
	    @Override
		public Void doInBackground() {
			thegoo.gamePlaying = true;
			thegoo.updateTicker("This is a visual test, is the timer working?", "black");
			
			return null;
		}
	}
}