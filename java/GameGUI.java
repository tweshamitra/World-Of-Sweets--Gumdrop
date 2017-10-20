import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class GameGUI {
	private final int HEIGHT = 660;
    private final int WIDTH = 1300;
	private JLabel ticker = new JLabel("");
	private JPanel tickerPanel, deckPanel, displayCardPanel, drawACardPanel;
	private JBoardPanel boardPanel;
	private JButton deckOfCards, shuffleCards;
	private final String html1 = "<html><body style='width: ";
    private final String html2 = "px'>";
	private Game theGame;
	private boolean canDraw;
	
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
		theGame = new Game(2);
		theGame.setPlayer(0,"Samantha","blue");
		theGame.setPlayer(1,"Ronald","red");
		
		//Call this to progress to the main logic of the game screen.   Again, you should probably call this in a 'submit' button
		//along with pushing the data to the game object.
		addBoardComponentsToPane(pane);
	}
	
	public void updateDeckImage(int numCards){
		
		//TODO: Forrest:  Modify the logic and/or images accordingly to your needs
		String imgString = "cards.png";
		if(numCards > 25)
		{
			imgString = "60" + imgString;
		}
		else if(numCards > 15 && numCards <= 25)
		{
			imgString = "25" + imgString;
		}
		else if(numCards > 10 && numCards <= 15)
		{
			imgString = "15" + imgString;
		}
		else if(numCards > 6 && numCards <= 10)
		{
			imgString = "6" + imgString;
		}
		else if(numCards == 6)
		{
			imgString = "6" + imgString;
		}
		else if(numCards == 5)
		{
			imgString = "5" + imgString;
		}
		else if(numCards == 4)
		{
			imgString = "4" + imgString;
		}
		else if(numCards == 3)
		{
			imgString = "3" + imgString;
		}
		else if(numCards == 2)
		{
			imgString = "2" + imgString;
		}
		else if(numCards == 1)
		{
			imgString = imgString;
		}
		else
		{
			imgString = "0" + imgString;
		}
		
		ImageIcon icon = new ImageIcon(getImage(imgString)); 
		deckOfCards.setIcon(icon);
	}
	
	//TODO: modify this to add color capabilities
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
		
		displayCardPanel = new JPanel(new FlowLayout());
		displayCardPanel.setBackground(Color.YELLOW);
		displayCardPanel.setPreferredSize(new Dimension(500, 300));
		
		JLabel display = new JLabel(html1 + "400" + html2 + "This is where the drawn card goes");
		display.setFont(new Font("TimesRoman", Font.ITALIC, 48));
		display.setForeground(Color.BLACK);
		displayCardPanel.add(display);
		
		deckPanel.add(displayCardPanel);
		
		//INITIALIZING THE DECK DISPLAY
		drawACardPanel = new JPanel(new BorderLayout());
		drawACardPanel.setBackground(Color.RED);
		drawACardPanel.setPreferredSize(new Dimension(500, 300));
		
		JLabel draw = new JLabel(html1 + "400" + html2 + "This is where the deck goes");
		draw.setFont(new Font("TimesRoman", Font.ITALIC, 48));
		draw.setForeground(Color.BLACK);
		
		//ADDING THE DECK BUTTON
		ImageIcon icon = new ImageIcon(getImage("60cards.png")); 
		deckOfCards = new JButton();
		deckOfCards.setIcon(icon);
		
		ActionListener buttonListener = new ButtonListener();
		deckOfCards.addActionListener(buttonListener);
		
		//ADDING THE SHUFFLE BUTTON
		icon = new ImageIcon(getImage("shuffle.png")); 
		shuffleCards = new JButton();
		shuffleCards.setIcon(icon);
		
		buttonListener = new ShuffleListener();
		shuffleCards.addActionListener(buttonListener);
		
		
		
		drawACardPanel.add(draw, BorderLayout.PAGE_START);
		drawACardPanel.add(deckOfCards, BorderLayout.LINE_END);
		drawACardPanel.add(shuffleCards, BorderLayout.LINE_START);
		
		deckPanel.add(drawACardPanel);
		
		pane.add(deckPanel, BorderLayout.LINE_START);
		
		//INITIALIZING THE GAME BOARD
		
		boardPanel = new JBoardPanel();
		boardPanel.setBackground(Color.BLUE);
		boardPanel.setPreferredSize(new Dimension(800, 600));
		
		JLabel boardlabel = new JLabel(html1 + "400" + html2 +"This is where the board goes");
		boardlabel.setFont(new Font("TimesRoman", Font.ITALIC, 48));
		boardlabel.setForeground(Color.BLACK);
		boardPanel.add(boardlabel);
		
		pane.add(boardPanel, BorderLayout.CENTER);

    }

	//THIS CLASS HANDLES THE CUSTOM ANIMATION FOR THE GAME BOARD AND TOKENS
    public class JBoardPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);       
			//TODO: Forrest:  Update this with the real board image OR replace the board.png image with it
			g.drawImage(getImage("board.png"), 0, 0, null);
			//This is where the animation for moving the tokens will be as well.  
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
				String curPlayer = theGame.getCurPlayerName();
				String playerColor = theGame.getCurPlayerColor();
				updateTicker(curPlayer + " drew a " + doub + " " + color + " card!", playerColor);
				//TODO:  more logic will be needed here to determine where the player is moving to
				theGame.pause(200);
				String oor2 = drawnCard.isdouble ? "two" : "one";
				String plur = drawnCard.isdouble ? "s" : "";
				updateTicker(curPlayer + " moved " + oor2 + " " + color + " space" + plur + "!", playerColor);
				theGame.incrementTurn();
				theGame.pause(200);
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