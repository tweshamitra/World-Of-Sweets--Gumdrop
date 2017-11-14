package wos;

import wos.*;
import javax.swing.*;

public class WinTest{
	
	static GameGUI thegoo = new GameGUI();
	public static void main(String args[]){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {                
               winTest();
            }
        });
	}
	
		
	static void winTest()
	{
		thegoo.testResetBoard();
		(new winTestThread()).execute();
	}
	
	static class winTestThread extends SwingWorker<Void, Object> {
	    @Override
		public Void doInBackground() {
			try{
			String[] colors = {"red", "yellow", "blue", "green", "orange"};
			String[] fives = {"red-5", "yellow-5", "blue-5", "green-5", "orange-5"};
			int[] fivenum = {21, 22, 23, 24, 25};
			String[] sixes = {"red-6", "yellow-6", "blue-6", "green-6", "orange-6"};
			int[] sixnum = {26, 27, 28, 29, 30};
			Space nextValid, middleSpace;
			
			thegoo.theGame.pause(2000);
			
			//testing the fives with doubles
			for( int x = 0; x < 5; x ++)
			{
				for( int y = 0; y <= x; y ++)
				{
					thegoo.updateTicker("Moving TEST to " + fives[x], "black");
					
					middleSpace =thegoo.theGame.gameBoard.gameSpaces[fivenum[x]];
					thegoo.theGame.moveCurPlayer(middleSpace.nextFreeSpace(thegoo.theGame.getCurPlayerNum()), middleSpace);
					
					thegoo.theGame.pause(500);
					
					thegoo.updateTicker("Testing a double " + colors[y], "black");
					
					nextValid =thegoo.theGame.getNextValidSpace(colors[y], "double");
					thegoo.theGame.moveCurPlayer(nextValid.nextFreeSpace(thegoo.theGame.getCurPlayerNum()), nextValid);
					
					thegoo.theGame.pause(500);
					
					if(thegoo.theGame.players[0].checkWin())
					{
						thegoo.updateTicker("Test passed.", "black");
						thegoo.theGame.pause(200);
					}
					else
					{
						thegoo.updateTicker("TEST FAILED", "red");
						System.out.println(fives[x] + " drawing a double " + colors[y] + " DOES NOT WIN THE GAME.");
						thegoo.theGame.pause(1200);
					}
				}
			}
			
			//testing the sixes with doubles
			for( int x = 0; x < 5; x ++)
			{
				for( int y = 0; y <= x; y ++)
				{
					thegoo.updateTicker("Moving TEST to " + sixes[x], "black");
					
					middleSpace =thegoo.theGame.gameBoard.gameSpaces[sixnum[x]];
					thegoo.theGame.moveCurPlayer(middleSpace.nextFreeSpace(thegoo.theGame.getCurPlayerNum()), middleSpace);
					
					thegoo.theGame.pause(500);
					
					thegoo.updateTicker("Testing a double " + colors[y], "black");
					
					nextValid =thegoo.theGame.getNextValidSpace(colors[y], "double");
					thegoo.theGame.moveCurPlayer(nextValid.nextFreeSpace(thegoo.theGame.getCurPlayerNum()), nextValid);
					
					thegoo.theGame.pause(500);
					
					if(thegoo.theGame.players[0].checkWin())
					{
						thegoo.updateTicker("Test passed.", "black");
						thegoo.theGame.pause(200);
					}
					else
					{
						thegoo.updateTicker("TEST FAILED", "red");
						System.out.println(sixes[x] + " drawing a double " + colors[y] + " DOES NOT WIN THE GAME.");
						thegoo.theGame.pause(1200);
					}
				}
			}
			//testing the sixes with singles
			for( int x = 0; x < 5; x ++)
			{
				for( int y = 0; y <= x; y ++)
				{
					thegoo.updateTicker("Moving TEST to " + sixes[x], "black");
					
					middleSpace =thegoo.theGame.gameBoard.gameSpaces[sixnum[x]];
					thegoo.theGame.moveCurPlayer(middleSpace.nextFreeSpace(thegoo.theGame.getCurPlayerNum()), middleSpace);
					
					thegoo.theGame.pause(500);
					
					thegoo.updateTicker("Testing a single " + colors[y], "black");
					
					nextValid =thegoo.theGame.getNextValidSpace(colors[y], "single");
					thegoo.theGame.moveCurPlayer(nextValid.nextFreeSpace(thegoo.theGame.getCurPlayerNum()), nextValid);
					
					thegoo.theGame.pause(500);
					
					if(thegoo.theGame.players[0].checkWin())
					{
						thegoo.updateTicker("Test passed.", "black");
						thegoo.theGame.pause(200);
					}
					else
					{
						thegoo.updateTicker("TEST FAILED", "red");
						System.out.println(sixes[x] + " drawing a single " + colors[y] + " DOES NOT WIN THE GAME.");
						thegoo.theGame.pause(1200);
					}
				}
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			
			return null;
		}
	}
	
}