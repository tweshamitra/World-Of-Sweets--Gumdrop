public class Game{
	Board gameBoard;
	Player[] players;
	Deck gameDeck;
	int turn;
	
	public Game(int numPlayers)
	{
		players = new Player[numPlayers];
		gameBoard = new Board();
		gameDeck = new Deck();
		turn = 0;
	}
	
	public void setPlayer(int playerNum, String playerName, String playerColor)
	{
		if(playerNum > players.length)
		{
			return;
		}
		players[playerNum] = new Player(playerName, playerColor);
	}
	
	public String getCurPlayerName()
	{
		return players[turn].name;
	}
	
	public String getCurPlayerColor()
	{
		return players[turn].color;
	}
	
	public String getPlayerName(int playerNum)
	{
		return players[playerNum].name;
	}
	
	public String getPlayerColor(int playerNum)
	{
		return players[playerNum].color;
	}
	
	public int getCurPlayerNum(){
		int ret = -1;
		for(int i = 0; i < players.length; i++){
			if(getCurPlayerName().equals(getPlayerName(i))){
				ret = i;
			}
		}
		return ret;
	}
	
	public String getCurrentSpaceLabel(){
		return players[turn].getCurrentSpace().getLabel();
	}
	
	//unsure?
	public void getPlayerLocation(int playerNum)
	{
		//TODO: Addison:  Return a representation of the requested player's location, however you decide to represent that
	}
	
	//TODO
	//Or however we want to manifest location

	public void moveCurPlayer(int[] location, Space s)
	{
		players[turn].updateLocation(location, s);
		System.out.println("current space: " + getCurrentSpaceLabel());
	}
	
	//TODO
	// public Space getNextValidSpace(String color, String doub)
	// {
		// Space currentSpace = players[turn].getCurrentSpace();
		// int currentIndex;
		// Gets the index of the space in gameSpaces the current player is on
		// for(int i = 0; i < gameBoard.NUMBER_OF_SPACES; i++){
			// if(gameBoard.gameSpaces[i].getLabel().equals(currentSpace.getLabel())){
				// currentIndex = i;
			// }
		// }
		
	// }

	public void pause(long ms)
	{
		try        
		{
			Thread.sleep(ms);
		} 
		catch(InterruptedException ex)   //simulate movement time for testing purposes
		{
			Thread.currentThread().interrupt();
		}
	}
	
	
	public void incrementTurn()
	{
		turn++;
		if(turn == players.length)
		{
			turn = 0;
		}
	}
	
	public Card drawCard()
	{
		return gameDeck.drawCard();
	}
	
	public int getNumCardsLeft()
	{
		return gameDeck.sizeOfDeck();
	}
	
	public boolean shuffleDeck()
	{
		return gameDeck.shuffleDeck();
	}
}