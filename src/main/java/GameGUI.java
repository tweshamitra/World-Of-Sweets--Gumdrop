import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

public class GameGUI {
	private final int HEIGHT = 695;
    private final int WIDTH = 1050;
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
	private int[] nums = {2,3,4};
	private String[] colors = {"red", "blue", "yellow", "green"};
	private JTextField text_1 = new JTextField("Player 1");
    private JTextField text_2 = new JTextField("Player 2");
    private JTextField text_3 = new JTextField("Player 3");
    private JTextField text_4 = new JTextField("Player 4");
	private JLabel playNameLabel;
	private JButton submit;
	private int numPlayers;
	private ArrayList<String> playerNames = new ArrayList<String>();
	private JComboBox num_players_menu = new JComboBox();

	public GameGUI()
	{
		
	}
	//THIS FUNCTION WILL DRAW THE START SCREEN
	private void drawStartScreen(Container pane) {
		pane.removeAll();
		pane.setLayout(new GridBagLayout());

		JPanel startPanel = new JPanel();
		for(int i = 0; i < nums.length; i++){
			num_players_menu.addItem(nums[i]);
		}
		


		num_players_menu.setBackground(Color.GREEN);
		ActionListener comboBoxListener = new ComboBoxListener(pane);		
		num_players_menu.addActionListener(comboBoxListener);

		
		ImageIcon welcomeIcon = new ImageIcon(getImage("WelcomeSign.png"));
		JLabel welcomeLabel = new JLabel();
		welcomeLabel.setIcon(welcomeIcon);
		welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

		ImageIcon numPlayIcon = new ImageIcon(getImage("NumPlaySign.png"));
		JLabel numPlayLabel = new JLabel();
		numPlayLabel.setIcon(numPlayIcon);
		numPlayLabel.setHorizontalAlignment(JLabel.CENTER);
		
		ImageIcon playNameIcon = new ImageIcon(getImage("PlayNameSign.png"));
		playNameLabel = new JLabel();
		playNameLabel.setIcon(playNameIcon);
		playNameLabel.setHorizontalAlignment(JLabel.CENTER);
				
		JPanel welcomePanel = new JPanel();
		welcomePanel.setLayout(new GridLayout(1,1));
		welcomePanel.setBackground(Color.PINK);
		welcomePanel.add(welcomeLabel);
		welcomePanel.setMaximumSize(new Dimension(10,10));
		startPanel.setLayout(new GridLayout(0,2));
		startPanel.setBackground(Color.PINK);
		

		startPanel.add(numPlayLabel);
		startPanel.add(num_players_menu);

		startPanel.add(playNameLabel);
		playNameLabel.setVisible(false);
		startPanel.add(text_1);
        startPanel.add(new JLabel());
		
		startPanel.add(text_2);
        startPanel.add(new JLabel());

		startPanel.add(text_3);
        startPanel.add(new JLabel());

		startPanel.add(text_4);
		
		text_1.setVisible(false);
		text_2.setVisible(false);
		text_3.setVisible(false);
		text_4.setVisible(false);
		text_1.setBackground(Color.GREEN);
		text_2.setBackground(Color.GREEN);
		text_3.setBackground(Color.GREEN);
		text_4.setBackground(Color.GREEN);

		JPanel submitPanel = new JPanel(new GridLayout(1,1));
		submitPanel.setBackground(Color.PINK);
		submit = new JButton();
		submit.setIcon(new ImageIcon(getImage("StartSign.png")));
		submit.setBackground(Color.PINK);
		submit.setBorder(null);
		ActionListener submitListener = new SubmitListener(pane);
		JPanel infoPanel = new JPanel(new GridLayout(0,1));
		infoPanel.add(welcomePanel);
		submit.addActionListener(submitListener);
		submitPanel.setLayout(new FlowLayout());
		submitPanel.add(submit);
		submitPanel.setMaximumSize(new Dimension(50,50));
		pane.setBackground(Color.PINK);
		infoPanel.add(startPanel);
		infoPanel.add(submitPanel);
		
		submit.setVisible(false);
		
		
		pane.add(infoPanel);
		// pane.add(welcomePanel);
		// pane.add(startPanel);
		// pane.add(submitPanel);
		
	}
	
	class ComboBoxListener implements ActionListener{
		Container pane;
		public ComboBoxListener(Container pane){
			this.pane = pane;
		}
		public void actionPerformed(ActionEvent e ){
			playNameLabel.setVisible(true);
			submit.setVisible(true);
			numPlayers = (int) num_players_menu.getSelectedItem();
			if(numPlayers == 2){
				text_1.setVisible(true);
				text_2.setVisible(true);
				text_3.setVisible(false);
				text_4.setVisible(false);
			} else if (numPlayers == 3){
				text_1.setVisible(true);
				text_2.setVisible(true);
				text_3.setVisible(true);
				text_4.setVisible(false);				
			} else{
				text_1.setVisible(true);
				text_2.setVisible(true);
				text_3.setVisible(true);
				text_4.setVisible(true);
			}
		}
	}
	
	
	class SubmitListener implements ActionListener{
		Container pane;
		public SubmitListener(Container pane){
			this.pane = pane;
		}
		public void actionPerformed(ActionEvent e ){
			numPlayers = (int) num_players_menu.getSelectedItem();
			playerNames.add(text_1.getText());
			playerNames.add(text_2.getText());
			playerNames.add(text_3.getText());
			playerNames.add(text_4.getText());
			drawPlayerInfoScreen(pane);
		}
	}
	private void drawPlayerInfoScreen(Container pane) {
		pane.removeAll();
		theGame = new Game(numPlayers);
		for(int i = 0; i < numPlayers; i++){
			theGame.setPlayer(i, playerNames.get(i), colors[i]);
		}
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
	
	public void showSpecialCard(String spec)
	{
		String tempString = spec + ".png";
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
		pane.revalidate();
		pane.repaint();
		pane.setLayout(new BorderLayout());
		
		//INITIALIZING THE TICKER

		tickerPanel = new JPanel(new FlowLayout());;
		tickerPanel.setBackground(Color.PINK);
		tickerPanel.setPreferredSize(new Dimension(1050, 60));
	
		ticker.setFont(new Font("TimesRoman", Font.ITALIC, 48));
		ticker.setHorizontalAlignment(JLabel.CENTER);
		updateTicker("It is " + theGame.getCurPlayerName() + "'s turn!", theGame.getCurPlayerColor());
		
		tickerPanel.add(ticker);
		pane.add(tickerPanel, BorderLayout.PAGE_START);
		
		//INITIALIZING THE DECK PANEL
		
		deckPanel = new JPanel();
		deckPanel.setLayout(new BoxLayout(deckPanel, BoxLayout.Y_AXIS));
		deckPanel.setPreferredSize(new Dimension(250, 600));
		
		//INITIALIZING THE DRAWN CARD DISPLAY
		
		displayCardPanel = new JWelcomePanel();
		displayCardPanel.setLayout(new BorderLayout());
		displayCardPanel.setBackground(Color.GREEN);
		displayCardPanel.setPreferredSize(new Dimension(250, 300));		
		
		drawnCardLabel.setVerticalAlignment(SwingConstants.TOP);
		displayCardPanel.add(drawnCardLabel, BorderLayout.CENTER);
		
		deckPanel.add(displayCardPanel);
		
		//INITIALIZING THE DECK DISPLAY
		drawACardPanel = new JPanel(new BorderLayout());
		drawACardPanel.setBackground(Color.RED);
		drawACardPanel.setPreferredSize(new Dimension(250, 300));
		
		//ADDING THE DECK BUTTON
		ImageIcon icon = new ImageIcon(getImage("100Full.png")); 
		deckOfCards = new JButton();
		deckOfCards.setIcon(icon);
		deckOfCards.setPreferredSize(new Dimension(250, 300));
		
		ActionListener buttonListener = new ButtonListener();
		deckOfCards.addActionListener(buttonListener);
		
		//ADDING THE SHUFFLE BUTTON  --SHUFFLE BUTTON REMOVED
		/*icon = new ImageIcon(getImage("shuffle.png")); 
		shuffleCards = new JButton();
		shuffleCards.setIcon(icon);
		shuffleCards.setPreferredSize(new Dimension(250, 300));
		
		buttonListener = new ShuffleListener();
		shuffleCards.addActionListener(buttonListener);*/
		
		drawACardPanel.add(deckOfCards, BorderLayout.CENTER);
		//drawACardPanel.add(shuffleCards, BorderLayout.LINE_START);
		
		deckPanel.add(drawACardPanel);
		
		pane.add(deckPanel, BorderLayout.LINE_START);
		
		//INITIALIZING THE GAME BOARD
		
		boardPanel = new JBoardPanel();
		
		// Needed to add the mouselistener to the instance of the jboardpanel otherwise click events registered numerous times
		boardPanel.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					for(int i = 0; i < theGame.gameBoard.gameSpaces.length; i++){
						Space sp = theGame.gameBoard.gameSpaces[i];
						int xy[] = new int[2];
						if(sp.wasClicked(e)){
							xy = sp.nextFreeSpace(theGame.getCurPlayerNum());
							// System.out.println("PIn: " + sp.getPIndex() + " x: " + xy[0] + " y: " + xy[1] + " label: " + sp.getLabel());
							theGame.moveCurPlayer(xy, sp);
						}
					}
					
				}
			});
			
		boardPanel.setBackground(Color.BLUE);
		boardPanel.setPreferredSize(new Dimension(800, 615));
		
		pane.add(boardPanel, BorderLayout.CENTER);

    }

	//THIS CLASS HANDLES THE CUSTOM ANIMATION FOR THE GAME BOARD AND TOKENS
    public class JBoardPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);       
			g.drawImage(getImage("board.png"), 0, 0, null);
			

			// If it is the first players initial turn, draw all the players in the start space
			// otherwise, draw them whereever their coordinates indicate
			if(firstRun){
				Space startSpace = getSpaceAt(0);
				for(int i = 0; i < numPlayers; i++){
					int[] xy = startSpace.nextFreeSpace(i);
					theGame.players[i].updateLocation(xy, startSpace);
					g.drawImage(getImage(colors[i]+"token.png"), xy[0], xy[1], null);
				}
				firstRun = false;
			}
			
			drawPlayers(g);
			
			
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
				//shuffleCards.setEnabled(false);
				deckOfCards.setEnabled(false);
				(new GameLogicThread()).execute();
		}
		
	}
	
	//THE LISTENER FOR THE SHUFFLE CARD BUTTON
	private class ShuffleListener implements ActionListener {

		// Every time we click the button, it will shuffle the deck
		public void actionPerformed(ActionEvent e) {
				//shuffleCards.setEnabled(false);
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
           //shuffleCards.setEnabled(true);
		   deckOfCards.setEnabled(true);
       }
   }
	
	//THE WORKER FOR THE DRAW CARD LOGIC
	class GameLogicThread extends SwingWorker<Void, Object> {
       @Override
       public Void doInBackground() {
           	Card drawnCard = theGame.drawCard();
			updateDeckImage(theGame.getNumCardsLeft());
			String curPlayer = theGame.getCurPlayerName();
			String playerColor = theGame.getCurPlayerColor();
			if(drawnCard == null)
			{
				theGame.shuffleDeck();
				updateTicker("The deck was shuffled!", "black");
				updateDeckImage(theGame.getNumCardsLeft());
				theGame.pause(400);		
				drawnCard = theGame.drawCard();				
			}
			if(drawnCard.special == false)
			{
				String color = drawnCard.color;
				String doub = drawnCard.isdouble ? "double" : "single";
				showDrawnCard(color, doub);
				updateTicker(curPlayer + " drew a " + doub + " " + color + " card!", playerColor);
				//TODO:  Addison:  more logic will be needed here to determine where the player is moving to
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
				switch(drawnCard.specNum)
				{
					//the card is a skip
					case 1: showSpecialCard(drawnCard.specText);
							updateTicker(curPlayer + "'s turn is skipped!", playerColor);
							theGame.pause(700);
							theGame.incrementTurn();
							removeDrawnCard();
							updateTicker("It is " + theGame.getCurPlayerName() + "'s turn!", theGame.getCurPlayerColor());
							break;
					//the card is a middle
					case 2: showSpecialCard(drawnCard.specText);
							updateTicker(curPlayer + " drew a middle card!", playerColor);
							theGame.pause(600);
							//TODO:  Addison:  incorporate the movement logic
							
							updateTicker(curPlayer + " was sent to the middle of the board!", playerColor);
							theGame.pause(600);
							theGame.incrementTurn();
							removeDrawnCard();
							updateTicker("It is " + theGame.getCurPlayerName() + "'s turn!", theGame.getCurPlayerColor());
							break;
					//unknown card exception, game broke
					default: //keep going i guess
							System.out.println(drawnCard.color + " " + drawnCard.isdouble + " " + drawnCard.special + " " + drawnCard.specText + " " + drawnCard.specNum);
							theGame.incrementTurn();
							updateTicker("It is " + theGame.getCurPlayerName() + "'s turn!", theGame.getCurPlayerColor());
							break;
				}
			}
			return null;
       }

       @Override
       protected void done() {
           deckOfCards.setEnabled(true);
		   //shuffleCards.setEnabled(true);
       }
   }
}