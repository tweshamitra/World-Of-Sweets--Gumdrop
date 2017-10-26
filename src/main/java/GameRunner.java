public class GameRunner{
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
		//StartGUI start = new StartGUI();
        GameGUI thegoo = new GameGUI();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //start.drawStartGui();
                //System.out.println(start.getNumPlayers());
                
               thegoo.createBoardGUI();
            }
        });
    }
}
