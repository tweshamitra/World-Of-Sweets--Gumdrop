package wos;

import wos.*;

public class GUITest{
	public static void main(String args[]){
		GameGUI thegoo = new GameGUI();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {                
               thegoo.Test();
            }
        });
	}
}