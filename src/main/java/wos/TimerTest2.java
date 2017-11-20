package wos;

import wos.*;
import javax.swing.*;
import java.util.Date;

public class TimerTest2{
	
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
			long my = 1036800000;
			
			Date testDate = new Date();
			long newTime = testDate.getTime()-my;
			testDate = new Date(newTime);
			thegoo.updateTicker("This is a visual test, the timer should start and freeze at 12 days.", "black");
			thegoo.gameOver = true;
			thegoo.updateTimer(testDate);
			
			thegoo.theGame.pause(3500);
			
			thegoo.updateTicker("What a terrible game of World of Sweets.", "black");
			
			return null;
		}
	}
}