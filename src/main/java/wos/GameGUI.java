package wos;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;
import wos.*;
import java.time.Duration;
import java.net.URL;
import javax.sound.sampled.*;


public class GameGUI{
	private final int HEIGHT = 695;
    private final int WIDTH = 1050;
	private JLabel ticker = new JLabel("");
	private JLabel timer = new JLabel("");
	private JLabel drawnCardLabel = new JLabel("", SwingConstants.CENTER);
	private JPanel tickerPanel, deckPanel, drawACardPanel, timeTick, timerPanel, optionPanel, optionPane;
	private JBoardPanel boardPanel;
	private JFrame frame;
	private JWelcomePanel displayCardPanel;
	private JButton deckOfCards, shuffleCards, submit, optionButton, load;
	Game theGame;
	volatile boolean gameOver;
	volatile boolean gamePlaying = false;
	private boolean optionPanelOpen = false;
	private ImageIcon drawnCard;
	private String[] colors = {"red", "blue", "yellow", "green"};
	private JTextField text_1 = new JTextField("Player 1");
    private JTextField text_2 = new JTextField("Player 2");
    private JTextField text_3 = new JTextField("Player 3");
    private JTextField text_4 = new JTextField("Player 4");
	private JLabel playNameLabel;
	private int numPlayers;
	private int[] nums = {2,3,4};
	private ArrayList<String> playerNames = new ArrayList<String>();
	private JComboBox num_players_menu = new JComboBox();
	private Font font, font40, font48;
	private Date gameStarted;
	//private File soundFile;
	//private AudioInputStream in;
	//private Clip clip;


	public GameGUI()
	{
		num_players_menu.addItem(nums[0]);
		num_players_menu.addItem(nums[1]);
		num_players_menu.addItem(nums[2]);
	}
	//THIS FUNCTION WILL DRAW THE START SCREEN
	private void drawStartScreen(Container pane) {	
		playAudio("WorldSweetsTheme.wav", true);
		pane.removeAll();
		pane.setLayout(new GridBagLayout());
		playerNames.clear();
		JPanel startPanel = new JPanel();
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

		JPanel submitPanel = new JPanel(new GridLayout(1,2));
		submitPanel.setBackground(Color.PINK);
		load = new JButton();
		load.setIcon(new ImageIcon(getImage("LoadSign.png")));
		load.setBackground(Color.PINK);
		load.setBorder(null);
		ActionListener loadListener = new LoadListener(pane);
		load.addActionListener(loadListener);
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
		submitPanel.add(load);
		submitPanel.setMaximumSize(new Dimension(50,50));
		pane.setBackground(Color.PINK);
		infoPanel.add(startPanel);
		infoPanel.add(submitPanel);
		
		submit.setVisible(false);
		load.setVisible(false);
		
		pane.add(infoPanel);
		pane.setComponentZOrder(infoPanel, 0);
		
		pane.revalidate();
		pane.repaint();
	}
	
	class ComboBoxListener implements ActionListener{
		Container pane;
		public ComboBoxListener(Container pane){
			this.pane = pane;
		}
		public void actionPerformed(ActionEvent e ){
			playAudio("ButtonClick.wav", false);
			playNameLabel.setVisible(true);
			submit.setVisible(true);
			load.setVisible(true);
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
			playAudio("ButtonClick.wav", false);
			numPlayers = (int) num_players_menu.getSelectedItem();
			playerNames.add(text_1.getText());
			playerNames.add(text_2.getText());
			playerNames.add(text_3.getText());
			playerNames.add(text_4.getText());
			drawPlayerInfoScreen(pane);
		}
	}
	
	class LoadListener implements ActionListener{
		Container pane;
		public LoadListener(Container pane){
			this.pane = pane;
		}
		public void actionPerformed(ActionEvent e ){
			
		}
	}
	
	private void drawPlayerInfoScreen(Container pane) {
		pane.removeAll();
		theGame = new Game(numPlayers);
		for(int i = 0; i < numPlayers; i++){
			theGame.setPlayer(i, playerNames.get(i), colors[i]);
		}
		gameOver = false;
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
		if(text.length() > 55)
		{
			ticker.setFont(font.deriveFont(20f));
		}
		else if(text.length() > 45)
		{
			ticker.setFont(font.deriveFont(30f));
		}
		
		else if(text.length() > 35)
		{
			ticker.setFont(font40);
		}

		else
			ticker.setFont(font48);
			
		ticker.setText(text);
		ticker.revalidate();
	}
	
	public void updateTimer(Date start)
	{
		Date current = new Date();
		int seconds = (int) (current.getTime()-start.getTime())/1000;
		int minutes = seconds / 60;
		int hours = minutes / 60;
		int days = hours / 24;
		String toPrint = "";
		if(days > 0)
		{
			if(days < 10)
			{
				toPrint = toPrint + "0";
			}
			toPrint = toPrint + Integer.toString(days) + ":";
		}
		hours =  hours%24;
		if(hours > 0 || days > 0)
		{
			if(hours < 10)
			{
				toPrint = toPrint + "0";
			}
			toPrint = toPrint + Integer.toString(hours) + ":";
		}
		minutes =  minutes%60;
		if(minutes < 10)
		{
			toPrint = toPrint + "0";
		}
		toPrint = toPrint + Integer.toString(minutes) + ":";
		seconds = seconds%60;
		if(seconds < 10)
		{
			toPrint = toPrint + "0";
		}
		toPrint = toPrint + Integer.toString(seconds);
		timer.setText(toPrint);
		timer.revalidate();
	}

    private void addBoardComponentsToPane(Container pane) {

		pane.removeAll();
		pane.revalidate();
		pane.repaint();
		pane.setLayout(new BorderLayout());
		
		//INITIALIZING THE TICKER
		
		timeTick = new JPanel(new BorderLayout());
		timeTick.setPreferredSize(new Dimension(1050, 60));
		
		timerPanel = new JPanel(new FlowLayout());
		
		timerPanel.setBackground(Color.PINK);
		timerPanel.setPreferredSize(new Dimension(250, 60));
		
		timer.setFont(font48);
		timer.setHorizontalAlignment(JLabel.CENTER);

		timer.setForeground(Color.BLACK);
		timerPanel.add(timer);

		tickerPanel = new JPanel(new FlowLayout());
		tickerPanel.setBackground(Color.PINK);
		tickerPanel.setPreferredSize(new Dimension(740, 60));
	
		ticker.setFont(font48);
		ticker.setHorizontalAlignment(JLabel.CENTER);
		ticker.setVerticalAlignment(JLabel.CENTER);
        ticker.setVerticalTextPosition(JLabel.CENTER);
		updateTicker("It is " + theGame.getCurPlayerName() + "'s turn!", theGame.getCurPlayerColor());
		tickerPanel.add(ticker);
		
		optionPanel = new JPanel(new FlowLayout());
		optionPanel.setBackground(Color.PINK);
		optionPanel.setPreferredSize(new Dimension(60, 60));
		optionButton = new JButton();
		optionButton.setIcon(new ImageIcon(getImage("OptionsSign.png")));
		optionButton.setBackground(Color.PINK);
		optionButton.setBorder(null);
		optionButton.setHorizontalAlignment(JLabel.CENTER);
		ActionListener optionListener = new OptionListener(pane);
		optionButton.addActionListener(optionListener);
		optionPanel.add(optionButton);		

		


		
		timeTick.add(tickerPanel, BorderLayout.CENTER);
		timeTick.add(timerPanel, BorderLayout.LINE_START);
		timeTick.add(optionPanel,BorderLayout.LINE_END);
		
		
		pane.add(timeTick, BorderLayout.PAGE_START);
		pane.setComponentZOrder(timeTick, 0);
		
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
		
		ActionListener buttonListener = new ButtonListener(pane);
		deckOfCards.addActionListener(buttonListener);
		
	
		drawACardPanel.add(deckOfCards, BorderLayout.CENTER);
		
		deckPanel.add(drawACardPanel);
		
		pane.add(deckPanel, BorderLayout.LINE_START);
		pane.setComponentZOrder(deckPanel, 0);
		
		//INITIALIZING THE GAME BOARD
		
		boardPanel = new JBoardPanel();
		
		// MouseListener to test space coordinates
		// boardPanel.addMouseListener(new MouseAdapter(){
				// @Override
				// public void mouseClicked(MouseEvent e){
					// for(int i = 0; i < theGame.gameBoard.gameSpaces.length; i++){
						// Space sp = theGame.gameBoard.gameSpaces[i];
						// int xy[] = new int[2];
						// if(sp.wasClicked(e)){
							// xy = sp.nextFreeSpace(theGame.getCurPlayerNum());
							// System.out.println("PIn: " + sp.getPIndex() + " x: " + xy[0] + " y: " + xy[1] + " label: " + sp.getLabel());
							// theGame.moveCurPlayer(xy, sp);
						// }
					// }
					
				// }
			// });
		

		boardPanel.setBackground(Color.BLUE);
		boardPanel.setPreferredSize(new Dimension(800, 615));
		
		pane.add(boardPanel, BorderLayout.CENTER);
		pane.setComponentZOrder(boardPanel, 0);

		(new TimerThread()).execute();
    }

	//THIS CLASS HANDLES THE CUSTOM ANIMATION FOR THE GAME BOARD AND TOKENS
    public class JBoardPanel extends JPanel{
		int xy[] = new int[2];
		boolean firstRun = true;
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);       
			g.drawImage(getImage("board.png"), 0, 0, null);
			

			// If it is the first players initial turn, draw all the players in the start space
			// otherwise, draw them whereever their coordinates indicate
			if(firstRun){
				Space startSpace = getSpaceAt(0);
				for(int i = 0; i < numPlayers; i++){
					xy = startSpace.nextFreeSpace(i);
					theGame.players[i].updateLocation(xy, startSpace);
					g.drawImage(getImage(colors[i]+"token.png"), xy[0], xy[1], null);
				}
				
				
				firstRun = false;
			}
			// Space middleSpace = getSpaceAt(16);
			// drawSpace(g, middleSpace);
			for(int i = 0; i < theGame.gameBoard.gameSpaces.length; i++){
				Space s = getSpaceAt(i);
				drawSpace(g, s);
			}
			drawPlayers(g);
			
			
		}
		
		public void drawSpace(Graphics g, Space s){
			Graphics2D g2d = (Graphics2D) g;
			// g2d.draw(s.getHorizontal());
			// g2d.draw(s.getVertical());
			g2d.draw(s.getSpace());
		}
		
		public Space getSpaceAt(int s){
			return theGame.gameBoard.gameSpaces[s];
		}
		
		public void drawPlayers(Graphics g){
			for(int i = 0; i < numPlayers; i++){
				int[] xy = theGame.players[i].getCurrentLocation();
				g.drawImage(getImage(colors[i]+"token.png"), xy[0], xy[1], null);
			}
			this.revalidate();
			this.repaint();
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
        frame = new JFrame("World of Sweets!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Set up the content pane.
		
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setResizable(false);
		
        frame.setVisible(true);
		
		try{
		InputStream is = GameGUI.class.getResourceAsStream("FORTE.ttf");	
		font = Font.createFont(Font.TRUETYPE_FONT, is);	
		font48 = font.deriveFont(48f);
		font40 = font.deriveFont(40f);
		}
		catch(Exception e)
		{}
		
		drawStartScreen(frame.getContentPane());


        //Display the window.
        frame.pack();
    }
	
	//FUNCTION THAT CREATES THE WIN SCREEN
	private void createWinScreen(Player winner){ //, Container pane) {
		//TODO
		Container pane = frame.getContentPane();
		pane.removeAll();
		pane.revalidate();
		pane.repaint();
		pane.setLayout(new BorderLayout());

		JPanel textPanel = new JPanel(new FlowLayout());
		textPanel.setBackground(Color.PINK);
		JLabel text = new JLabel("Game over!");
	
		text.setPreferredSize(new Dimension(1050,60));
		text.setFont(new Font("TimesRoman", Font.ITALIC, 60));
		text.setHorizontalAlignment(JLabel.CENTER);

		textPanel.add(text);
		pane.add(textPanel, BorderLayout.PAGE_START);
		pane.setComponentZOrder(textPanel, 0);
		deckPanel = new JPanel();
		deckPanel.setLayout(new BoxLayout(deckPanel, BoxLayout.Y_AXIS));
		deckPanel.setPreferredSize(new Dimension(250, 600));
				
		displayCardPanel = new JWelcomePanel();
		displayCardPanel.setLayout(new BorderLayout());
		displayCardPanel.setBackground(Color.GREEN);
		displayCardPanel.setPreferredSize(new Dimension(250, 300));		
		
		drawnCardLabel.setVerticalAlignment(SwingConstants.TOP);
		displayCardPanel.add(drawnCardLabel, BorderLayout.CENTER);
		
		deckPanel.add(displayCardPanel);
		
		drawACardPanel = new JPanel(new BorderLayout());
		drawACardPanel.setBackground(Color.RED);
		drawACardPanel.setPreferredSize(new Dimension(250, 300));
		
		ImageIcon icon = new ImageIcon(getImage("100Full.png")); 
		deckOfCards = new JButton();
		deckOfCards.setEnabled(false);
		deckOfCards.setIcon(icon);
		deckOfCards.setPreferredSize(new Dimension(250, 300));
		drawACardPanel.add(deckOfCards, BorderLayout.CENTER);
		deckPanel.add(drawACardPanel);
		JLabel winnerLabel = new JLabel(winner.getPlayerName()+ " has won!");
		winnerLabel.setFont(new Font("TimesRoman", Font.ITALIC, 72));
		winnerLabel.setForeground(Color.RED);
		winnerLabel.setBackground(Color.GREEN);
		JPanel winnerPanel = new JPanel(new GridLayout(2,1));
		JPanel actions = new JPanel(new FlowLayout());
		actions.setBackground(Color.YELLOW);
		
		JButton playAgain = new JButton();
		playAgain.setIcon(new ImageIcon(getImage("PlaySign.png")));
		playAgain.setBackground(Color.YELLOW);
		playAgain.setBorder(null);
		JButton quit = new JButton();
		quit.setIcon(new ImageIcon(getImage("QuitSign.png")));
		quit.setBackground(Color.YELLOW);
		quit.setBorder(null);
		ActionListener quitListener = new QuitListener();
		ActionListener playAgainButtonListener = new PlayAgainListener(pane);
		playAgain.addActionListener(playAgainButtonListener);
		quit.addActionListener(quitListener);
		winnerPanel.setBackground(Color.YELLOW);
		winnerPanel.setPreferredSize(new Dimension(1050, 60));

		winnerLabel.setHorizontalAlignment(JLabel.CENTER);

		winnerPanel.add(winnerLabel);
		actions.add(playAgain);
		actions.add(quit);
		winnerPanel.add(actions);
		pane.add(deckPanel, BorderLayout.LINE_START);
		pane.setComponentZOrder(deckPanel, 0);
		pane.add(winnerPanel);
		pane.setComponentZOrder(winnerPanel, 0);
		pane.revalidate();
		pane.repaint();
	}
	
	private void drawOptionsScreen(Container pane){
		pane.remove(boardPanel);
		optionPane = new JPanel();
		optionPane.setLayout(new FlowLayout());
		optionPane.setPreferredSize(new Dimension(1050, 600));
		optionPane.setBackground(Color.PINK);
		JButton saveButton = new JButton();
		saveButton.setIcon(new ImageIcon(getImage("SaveSign.png")));
		saveButton.setBackground(Color.PINK);
		saveButton.setBorder(null);
		saveButton.setHorizontalAlignment(JLabel.CENTER);
		saveButton.setVerticalAlignment(JLabel.CENTER);
		ActionListener saveListener = new SaveListener(pane);
		saveButton.addActionListener(saveListener);
		JButton quitButton = new JButton();
		quitButton.setIcon(new ImageIcon(getImage("QuitSign.png")));
		quitButton.setBackground(Color.PINK);
		quitButton.setBorder(null);
		quitButton.setHorizontalAlignment(JLabel.CENTER);
		quitButton.setVerticalAlignment(JLabel.CENTER);
		ActionListener quitListener = new QuitListener();
		quitButton.addActionListener(quitListener);
		optionPane.add(saveButton);
		optionPane.add(quitButton);
		pane.add(optionPane);
		pane.setComponentZOrder(optionPane, 1);
		deckOfCards.setEnabled(false);
		//pane = frame.getContentPane();
		pane.revalidate();
		pane.repaint();
		
	}
	
	class PlayAgainListener implements ActionListener{
		Container pane;
		public PlayAgainListener(Container pane){
			this.pane = pane;
		}
		public void actionPerformed(ActionEvent e){
			playAudio("ButtonClick.wav", false);
			drawStartScreen(pane);
		}
	}

	class QuitListener implements ActionListener{
		public QuitListener(){

		}
		public void actionPerformed(ActionEvent e){
			playAudio("ButtonClick.wav", false);			
			System.exit(0);
		}
	}
	
	class OptionListener implements ActionListener{
		Container pane;
		public OptionListener(Container pane){
			this.pane = pane;
		}
		public void actionPerformed(ActionEvent e){
			playAudio("ButtonClick.wav", false);
			if (optionPanelOpen){
				optionPanelOpen = false;
				pane.remove(optionPane);
				deckOfCards.setEnabled(true);
				pane.add(boardPanel);
				pane.revalidate();
				pane.repaint();
				
			} else{
				optionPanelOpen = true;
				drawOptionsScreen(pane);
			}
		}
	}
	
	class SaveListener implements ActionListener{
		Container pane;
		public SaveListener(Container pane){
			this.pane = pane;
		}
		public void actionPerformed(ActionEvent e){
			//this code just removes the options panel and replaces it back to the board panel
			pane.remove(optionPane);
			deckOfCards.setEnabled(true);
			pane.add(boardPanel);
			pane.revalidate();
			pane.repaint();
		}
	}
	

	//A FUNCTION THAT SIMPLIFIES THE GETTING OF IMAGES FROM THE RESOURCES FOLDER
	private Image getImage(String name)
	{
		BufferedImage img = null;
		try {
			img = ImageIO.read(GameGUI.class.getResource(name));
		} catch (IOException e) {
		}
		return img;
	}
	
	private void playAudio(String name, boolean loop){
		URL url = null;
		AudioInputStream audioIn = null;
		Clip clip =  null;
		try{
			url = GameGUI.class.getResource(name);
			audioIn = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioIn);
			if (loop){
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}else{
				clip.start();
			}
		} catch(UnsupportedAudioFileException e){
			System.out.println("UnsupportedAudioFileException");
		} catch(IOException d){
			System.out.println("IOException");
		} catch (LineUnavailableException f){
			System.out.println("LineUnavailableException");
		}		
	}
	
	
	//THE LISTENER FOR THE DRAW CARD BUTTON
	private class ButtonListener implements ActionListener {
		Container pane;
		public ButtonListener(Container pane){
			this.pane = pane;
		}
		// Every time we click the button, it will draw a card and take a turn

		public void actionPerformed(ActionEvent e) {
				playAudio("CardFlip.wav", false);
				deckOfCards.setEnabled(false);
				(new GameLogicThread()).execute();

		}
		
	}
   
	
	//THE WORKER FOR THE DRAW CARD LOGIC
	class GameLogicThread extends SwingWorker<Void, Object> {
       @Override
       public Void doInBackground() {
			gamePlaying = true;
           	Card drawnCard = theGame.drawCard();
			updateDeckImage(theGame.getNumCardsLeft());
			String curPlayer = theGame.getCurPlayerName();
			String playerColor = theGame.getCurPlayerColor();
			if(drawnCard == null)
			{
				playAudio("DeckShuffle.wav", false);
				theGame.shuffleDeck();
				updateTicker("The deck was shuffled!", "black");
				updateDeckImage(theGame.getNumCardsLeft());
				theGame.pause(1000);		
				drawnCard = theGame.drawCard();				
			}
			if(drawnCard.special == false)
			{
				String color = drawnCard.color;
				String doub = drawnCard.isdouble ? "double" : "single";
				showDrawnCard(color, doub);
				updateTicker(curPlayer + " drew a " + doub + " " + color + " card!", playerColor);
				theGame.pause(1000);
				//TODO:  Addison:  more logic will be needed here to determine where the player is moving to
				Space nextValid = theGame.getNextValidSpace(color, doub);
				theGame.moveCurPlayer(nextValid.nextFreeSpace(theGame.getCurPlayerNum()), nextValid);
				
				String oor2 = drawnCard.isdouble ? "two" : "one";
				String plur = drawnCard.isdouble ? "s" : "";
				updateTicker(curPlayer + " moved " + oor2 + " " + color + " space" + plur + "!", playerColor);
				theGame.incrementTurn();
				theGame.pause(2000);
				removeDrawnCard();
				//updateTicker("It is " + theGame.getCurPlayerName() + "'s turn!", theGame.getCurPlayerColor());
			}
			else
			{
				//input logic for special cards here
				switch(drawnCard.specNum)
				{
					//the card is a skip
					case 1: showSpecialCard(drawnCard.specText);
							updateTicker(curPlayer + "'s turn is skipped!", playerColor);
							theGame.pause(2000);
							theGame.incrementTurn();
							removeDrawnCard();
							//updateTicker("It is " + theGame.getCurPlayerName() + "'s turn!", theGame.getCurPlayerColor());
							break;
					/*  I'm leaving this code in here since the other cards will probably be very similar to implement.
					case 2: showSpecialCard(drawnCard.specText);
							updateTicker(curPlayer + " drew a middle card!", playerColor);
							theGame.pause(1000);
							//TODO:  Addison:  incorporate the movement logic
							Space middleSpace = theGame.gameBoard.gameSpaces[16];
							theGame.moveCurPlayer(middleSpace.nextFreeSpace(theGame.getCurPlayerNum()), middleSpace);
							
							updateTicker(curPlayer + " was sent to the middle of the board!", playerColor);
							theGame.pause(1000);
							theGame.incrementTurn();
							removeDrawnCard();
							//updateTicker("It is " + theGame.getCurPlayerName() + "'s turn!", theGame.getCurPlayerColor());
							break; */
					//Chocolate Falls
					case 2: showSpecialCard(drawnCard.specText);
							updateTicker(curPlayer + " drew a chocolate card!", playerColor);
							theGame.pause(1000);
							//TODO:  Addison:  incorporate the movement logic
							//Space middleSpace = theGame.gameBoard.gameSpaces[16];
							//theGame.moveCurPlayer(middleSpace.nextFreeSpace(theGame.getCurPlayerNum()), middleSpace);
							
							updateTicker(curPlayer + " was sent to Chocolate Falls!", playerColor);
							theGame.pause(1000);
							theGame.incrementTurn();
							removeDrawnCard();
							break;
					//Lollipop Mountain
					case 3: showSpecialCard(drawnCard.specText);
							updateTicker(curPlayer + " drew a lollipop card!", playerColor);
							theGame.pause(1000);
							//TODO:  Addison:  incorporate the movement logic
							//Space middleSpace = theGame.gameBoard.gameSpaces[16];
							//theGame.moveCurPlayer(middleSpace.nextFreeSpace(theGame.getCurPlayerNum()), middleSpace);
							
							updateTicker(curPlayer + " was sent to Lollipop Mountain!", playerColor);
							theGame.pause(1000);
							theGame.incrementTurn();
							removeDrawnCard();
							break;
					//Frosted Forest
					case 4: showSpecialCard(drawnCard.specText);
							updateTicker(curPlayer + " drew a cupcake card!", playerColor);
							theGame.pause(1000);
							//TODO:  Addison:  incorporate the movement logic
							//Space middleSpace = theGame.gameBoard.gameSpaces[16];
							//theGame.moveCurPlayer(middleSpace.nextFreeSpace(theGame.getCurPlayerNum()), middleSpace);
							
							updateTicker(curPlayer + " was sent to the Cupcake Forest!", playerColor);
							theGame.pause(1000);
							theGame.incrementTurn();
							removeDrawnCard();
							break;
					//Peanut Brittle Bridge
					case 5: showSpecialCard(drawnCard.specText);
							updateTicker(curPlayer + " drew a brittle card!", playerColor);
							theGame.pause(1000);
							//TODO:  Addison:  incorporate the movement logic
							//Space middleSpace = theGame.gameBoard.gameSpaces[16];
							//theGame.moveCurPlayer(middleSpace.nextFreeSpace(theGame.getCurPlayerNum()), middleSpace);
							
							updateTicker(curPlayer + " was sent to Brittle Bridge!", playerColor);
							theGame.pause(1000);
							theGame.incrementTurn();
							removeDrawnCard();
							break;
					//Something Lake
					case 6: showSpecialCard(drawnCard.specText);
							updateTicker(curPlayer + " drew a licorice card!", playerColor);
							theGame.pause(1000);
							//TODO:  Addison:  incorporate the movement logic
							Space middleSpace = theGame.gameBoard.gameSpaces[16];
							theGame.moveCurPlayer(middleSpace.nextFreeSpace(theGame.getCurPlayerNum()), middleSpace);
							
							updateTicker(curPlayer + " was sent to Licorice Lake!", playerColor);
							theGame.pause(1000);
							theGame.incrementTurn();
							removeDrawnCard();
							break;
					//unknown card exception, game broke
					default: //keep going i guess
							System.out.println(drawnCard.color + " " + drawnCard.isdouble + " " + drawnCard.special + " " + drawnCard.specText + " " + drawnCard.specNum);
							theGame.incrementTurn();
							//updateTicker("It is " + theGame.getCurPlayerName() + "'s turn!", theGame.getCurPlayerColor());
							break;
				}
			}
			return null;
       }

       @Override
       protected void done() {
			boolean won = false;
			for (int i = 0; i < theGame.players.length; i++){
				if(theGame.players[i].checkWin())
				{
					updateTicker("Game over!", "BLACK");
					won = true;
					gamePlaying = false;
					gameOver = true;
					createWinScreen(theGame.players[i]); //, this.pane);
				}
				int z = i + 1;
				//System.out.println("Player " + z + ": " + theGame.players[i].currentSpace.label); 
			}
			if(!won) { 
				updateTicker("It is " + theGame.getCurPlayerName() + "'s turn!", theGame.getCurPlayerColor());
				deckOfCards.setEnabled(true);
			}
       }
	}
	   
	class TimerThread extends SwingWorker<Void, Object> {
	   @Override
		public Void doInBackground() {
			while(!gameOver){
				gameStarted = new Date();
				updateTimer(gameStarted);
				while(gamePlaying)
				{
					try{
						Thread.sleep(1000);
					}catch(InterruptedException e){  }
					updateTimer(gameStarted);
				}
				try{
					Thread.sleep(100);
				}catch(InterruptedException e){  }
			}
			return null;
		}
	}
	
	//Resets the board for testing purposes.
	//Creates a game of one player and disables the card drawing function
	void testResetBoard()
	{
		theGame = new Game(1);
		numPlayers = 1;
		theGame.setPlayer(0, "TestPlayer", "orange");
		
		frame = new JFrame("World of Sweets!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set up the content pane.
		
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setResizable(false);
		
		frame.setVisible(true);
		
		try{
		InputStream is = GameGUI.class.getResourceAsStream("FORTE.ttf");	
		font = Font.createFont(Font.TRUETYPE_FONT, is);	
		font48 = font.deriveFont(48f);
		font40 = font.deriveFont(40f);
		}
		catch(Exception e)
		{}
		
		addBoardComponentsToPane(frame.getContentPane());
		updateTicker("Testing...", "black");
		deckOfCards.setEnabled(false);
	}

   
}