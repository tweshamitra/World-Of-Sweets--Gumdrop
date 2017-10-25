import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;


public class GameGUI {
	private final int HEIGHT = 695;
    private final int WIDTH = 1300;
	private JLabel ticker = new JLabel("");
	private JLabel drawnCardLabel = new JLabel("", SwingConstants.CENTER);
	private JPanel tickerPanel, deckPanel, drawACardPanel;
	private JBoardPanel boardPanel;
	private JWelcomePanel displayCardPanel;
	private JButton deckOfCards, shuffleCards;
	private final String html1 = "<html><body style='width: ";
    private final String html2 = "px'>";
	private Game theGame;
	private boolean canDraw;
	private ImageIcon drawnCard;
	
	public GameGUI()
	{
		
	}
	//THIS FUNCTION WILL DRAW THE START SCREEN
	private void drawStartScreen(Container pane) {
		//You might want to call pane.removeAll(); here just to be safe.
		//Plus, it'll add functionality for a "Main Menu" button.
		
		//TODO: Twesha:  Insert your logic for operating the start screen here
		
		//Call this to switch to the get info screen.  You probably want to call this in a button listener after a click. 
		drawPlayerInfoScreen(pane);
	}
	
	private void drawPlayerInfoScreen(Container pane) {
		//Calling pane.removeAll(); will remove all the elements from the start screen.
		
		//TODO: Twesha:  Insert your logic for getting the player info here
		//This is for testing purposes.  Overwrite it.
		theGame = new Game(4);
		theGame.setPlayer(0,"Forrest","blue");
		theGame.setPlayer(1,"Jessica","red");
		theGame.setPlayer(2,"Addison", "yellow");
		theGame.setPlayer(3,"Twesha", "green");
		
		//Call this to progress to the main logic of the game screen.   Again, you should probably call this in a 'submit' button
		//along with pushing the data to the game object.
		addBoardComponentsToPane(pane);
	}
	
	public void updateDeckImage(int numCards){
		String imgString = "Full.png";
		if(numCards > 50)
		{
			imgString = "100" + imgString;
		}
		else if(numCards > 40 && numCards <= 50)
		{
			imgString = "75" + imgString;
		}
		else if(numCards > 30 && numCards <= 40)
		{
			imgString = "50" + imgString;
		}
		else if(numCards > 15 && numCards <= 30)
		{
			imgString = "25" + imgString;
		}
		else if(numCards > 6 && numCards <= 15)
		{
			imgString = "10" + imgString;
		}
		else if(numCards > 0)
		{
			imgString = "5" + imgString;

		}
		else
		{
			imgString = "0" + imgString;
		}
		
		ImageIcon icon = new ImageIcon(getImage(imgString)); 
		deckOfCards.setIcon(icon);
	}
	
	public void showDrawnCard(String color, String doub)
	{
		String tempString = doub + color + ".png";
		drawnCardLabel.setIcon(new ImageIcon(getImage(tempString)));
	}
	
	public void removeDrawnCard()
	{
		drawnCardLabel.setIcon(null);
	}
	
	public void updateTicker(String text, String color){
		switch(color.toLowerCase()){
            case "red":  ticker.setForeground(Color.RED);
                    break;
			case "blue":  ticker.setForeground(Color.BLUE);
                    break;
			case "green":  ticker.setForeground(Color.GREEN);
                    break;
			case "orange":  ticker.setForeground(Color.ORANGE);
                    break;
			case "yellow":  ticker.setForeground(Color.YELLOW);
                    break;
			default:
					ticker.setForeground(Color.BLACK);
		}
			
		ticker.setText(text);
		ticker.revalidate();
	}

    private void addBoardComponentsToPane(Container pane) {

		pane.removeAll();
	
		pane.setLayout(new BorderLayout());
		
		//INITIALIZING THE TICKER

		tickerPanel = new JPanel(new FlowLayout());;
		tickerPanel.setBackground(Color.PINK);
		tickerPanel.setPreferredSize(new Dimension(1300, 60));
		
		ticker.setFont(new Font("TimesRoman", Font.ITALIC, 48));
		updateTicker("It is " + theGame.getCurPlayerName() + "'s turn!", theGame.getCurPlayerColor());
		
		tickerPanel.add(ticker);
		pane.add(tickerPanel, BorderLayout.PAGE_START);
		
		//INITIALIZING THE DECK PANEL
		
		deckPanel = new JPanel();
		deckPanel.setLayout(new BoxLayout(deckPanel, BoxLayout.Y_AXIS));
		deckPanel.setPreferredSize(new Dimension(500, 600));
		
		//INITIALIZING THE DRAWN CARD DISPLAY
		
		displayCardPanel = new JWelcomePanel();
		displayCardPanel.setLayout(new BorderLayout());
		displayCardPanel.setBackground(Color.YELLOW);
		displayCardPanel.setPreferredSize(new Dimension(500, 300));		
		displayCardPanel.setMaximumSize(new Dimension(500, 300));	
		drawnCardLabel.setVerticalAlignment(SwingConstants.TOP);
		displayCardPanel.add(drawnCardLabel, BorderLayout.CENTER);
		
		deckPanel.add(displayCardPanel);
		
		//INITIALIZING THE DECK DISPLAY
		drawACardPanel = new JPanel(new BorderLayout());
		drawACardPanel.setBackground(Color.RED);
		drawACardPanel.setPreferredSize(new Dimension(500, 300));
		
		//ADDING THE DECK BUTTON
		ImageIcon icon = new ImageIcon(getImage("100Full.png")); 
		deckOfCards = new JButton();
		deckOfCards.setIcon(icon);
		deckOfCards.setPreferredSize(new Dimension(250, 300));
		
		ActionListener buttonListener = new ButtonListener();
		deckOfCards.addActionListener(buttonListener);
		
		//ADDING THE SHUFFLE BUTTON
		icon = new ImageIcon(getImage("shuffle.png")); 
		shuffleCards = new JButton();
		shuffleCards.setIcon(icon);
		shuffleCards.setPreferredSize(new Dimension(250, 300));
		
		buttonListener = new ShuffleListener();
		shuffleCards.addActionListener(buttonListener);
		
		drawACardPanel.add(deckOfCards, BorderLayout.LINE_END);
		drawACardPanel.add(shuffleCards, BorderLayout.LINE_START);
		
		deckPanel.add(drawACardPanel);
		
		pane.add(deckPanel, BorderLayout.LINE_START);
		
		//INITIALIZING THE GAME BOARD
		
		boardPanel = new JBoardPanel();
		boardPanel.setBackground(Color.BLUE);
		boardPanel.setPreferredSize(new Dimension(800, 615));
		
		pane.add(boardPanel, BorderLayout.CENTER);

    }

	//THIS CLASS HANDLES THE CUSTOM ANIMATION FOR THE GAME BOARD AND TOKENS
    public class JBoardPanel extends JPanel{
		ArrayList<Space> spaceList = new ArrayList<>();
		int xy[] = new int[2];
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);       
			g.drawImage(getImage("board.png"), 0, 0, null);
			
			// for(Space sp : theGame.gameBoard.gameSpaces){
				// drawSpace(g, sp);
			// }
			
			// MouseListener used to see if any of the space have been clicked
			addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					super.mouseClicked(e);
					for(Space sp : theGame.gameBoard.gameSpaces){
						sp.wasClicked(e);
					}
				}
			});
			
			
			
			//This is where the animation for moving the tokens will be as well.  
			//For now, dummy data to test
			g.drawImage(getImage("bluetoken.png"), 15, 535, null);
			g.drawImage(getImage("redtoken.png"), 50, 120, null);
			/*for(int x = 0; x < theGame.getNumPlayers(); x++)
			{
				String imgName = theGame.getPlayerColor(x) + "token.png";
				g.drawImage(getImage(imgName),  ... location data ..., null);
			}*/
		}  
		
		public void drawSpace(Graphics g, Space s){
			Graphics2D g2d = (Graphics2D) g;
			g2d.draw(s.getSpace());
		}
	}
	
	//THIS CLASS IS THE WELCOME PANEL
	public class JWelcomePanel extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);   
			g.drawImage(getImage("welcome.png"), 0, 0, null);
		}
	}
	
	//THIS IS THE FIRST FUNCTION CALLED THAT INITIALIZES THE GUI
    public void createBoardGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("World of Sweets!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
		
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setResizable(false);
		
        frame.setVisible(true);
		
		drawStartScreen(frame.getContentPane());

        //Display the window.
        frame.pack();
    }
	
	//FUNCTION THAT CREATES THE WIN SCREEN
	private void createWinScreen() {
		//TODO
	}

	//A FUNCTION THAT SIMPLIFIES THE GETTING OF IMAGES FROM THE IMG FOLDER
	private Image getImage(String name)
	{
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("../img/" + name));
		} catch (IOException e) {
		}
		return img;
	}
	
	//THE LISTENER FOR THE DRAW CARD BUTTON
	private class ButtonListener implements ActionListener {

		// Every time we click the button, it will draw a card and take a turn

		public void actionPerformed(ActionEvent e) {
				shuffleCards.setEnabled(false);
				deckOfCards.setEnabled(false);
				(new GameLogicThread()).execute();
		}
		
	}
	
	//THE LISTENER FOR THE SHUFFLE CARD BUTTON
	private class ShuffleListener implements ActionListener {

		// Every time we click the button, it will shuffle the deck
		public void actionPerformed(ActionEvent e) {
				shuffleCards.setEnabled(false);
				deckOfCards.setEnabled(false);
				(new ShuffleLogicThread()).execute();
		}
		
	}
	
	//THE WORKER FOR THE SHUFFLE CARDS LOGIC
	class ShuffleLogicThread extends SwingWorker<Void, Object> {
       @Override
       public Void doInBackground() {
           	boolean shuf = theGame.shuffleDeck();
			if(shuf)
			{
				updateTicker("The deck was shuffled!", "black");
				updateDeckImage(theGame.getNumCardsLeft());
			}
			else
			{
				updateTicker("The deck does not need shuffled!", "black");
			}
			return null;
       }

       @Override
       protected void done() {
           shuffleCards.setEnabled(true);
		   deckOfCards.setEnabled(true);
       }
   }
	
	//THE WORKER FOR THE DRAW CARD LOGIC
	class GameLogicThread extends SwingWorker<Void, Object> {
       @Override
       public Void doInBackground() {
           	Card drawnCard = theGame.drawCard();
			updateDeckImage(theGame.getNumCardsLeft());
			if(drawnCard == null)
			{
				updateTicker("The deck needs shuffled!", "black");
			}
			else if(drawnCard.special == false)
			{
				String color = drawnCard.color;
				String doub = drawnCard.isdouble ? "double" : "single";
				showDrawnCard(color, doub);
				String curPlayer = theGame.getCurPlayerName();
				String playerColor = theGame.getCurPlayerColor();
				updateTicker(curPlayer + " drew a " + doub + " " + color + " card!", playerColor);
				//TODO:  more logic will be needed here to determine where the player is moving to
				theGame.pause(600);
				String oor2 = drawnCard.isdouble ? "two" : "one";
				String plur = drawnCard.isdouble ? "s" : "";
				updateTicker(curPlayer + " moved " + oor2 + " " + color + " space" + plur + "!", playerColor);
				theGame.incrementTurn();
				theGame.pause(600);
				removeDrawnCard();
				updateTicker("It is " + theGame.getCurPlayerName() + "'s turn!", theGame.getCurPlayerColor());
			}
			else
			{
				//input logic for special cards here
			}
			return null;
       }

       @Override
       protected void done() {
           deckOfCards.setEnabled(true);
		   shuffleCards.setEnabled(true);
       }
   }
}