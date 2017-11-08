package wos;

import wos.*;

public class GameRunner{
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        GameGUI thegoo = new GameGUI();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {                
               thegoo.createBoardGUI();
            }
        });
    }
}
