package wos;

import wos.*;
import java.io.Serializable;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Game implements Serializable{
	Board gameBoard;
	Player[] players;
	Deck gameDeck;
	int turn;
	int playerNum;
	
	public Game(int numPlayers)
	{
		players = new Player[numPlayers];
		gameBoard = new Board();
		gameDeck = new Deck();
		turn = 0;
		playerNum = numPlayers;
	}
	

	
	public void setPlayer(int playerNum, String playerName, String playerType, String playerColor)
	{
		if(playerNum > players.length)
		{
			return;
		}
		players[playerNum] = new Player(playerName, playerColor);
		if(playerType.equals("AI")){
			players[playerNum].setAI();
		}
	}
	
	public boolean isCurPlayerAI(){
		return players[turn].isAI();
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
	
	public String getPlayerNameAt(int index){
		return players[index].name;
	}
	
	public String getPlayerColorAt(int index){
		return players[index].color;
	}
	public int getNumPlayers(){
		return playerNum;
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

	public void moveCurPlayer(int[] location, Space s)
	{
		players[turn].updateLocation(location, s);
		// System.out.println("current space: " + getCurrentSpaceLabel());
	}
	
	public void moveCurPlayer(int[] location, Space s, int playerNum)
	{
		players[playerNum].updateLocation(location, s);
		// System.out.println("current space: " + getCurrentSpaceLabel());
	}
	
	//TODO
	public Space getNextValidSpace(String color, String doub)
	{
		boolean found = false;
		Space returnSpace = players[turn].getCurrentSpace();
		Space currentSpace = players[turn].getCurrentSpace();
		int currentIndex = -1;
		// Gets the index of the space in gameSpaces the current player is on
		if(color.equals("start")){
			currentIndex = 0;
		}
		else{
			for(int i = 0; i < gameBoard.NUMBER_OF_SPACES; i++){
				if(gameBoard.gameSpaces[i].getLabel().equals(currentSpace.getLabel())){
					currentIndex = i;
					//System.out.println("found current space, index: " + i);
				}
			}
		}
		// Looks ahead at most five spaces to find the next valid space
		if(doub.equals("single")){
			if(currentIndex + 1 == gameBoard.NUMBER_OF_SPACES-1){
				returnSpace = gameBoard.gameSpaces[gameBoard.NUMBER_OF_SPACES-1];
			}
			else{
				for(int j = currentIndex+1; j < Math.min(currentIndex+6, gameBoard.NUMBER_OF_SPACES-1); j++){
					if(gameBoard.gameSpaces[j].getColor().equals(color)){
						returnSpace = gameBoard.gameSpaces[j];
						found = true;
						//System.out.println("first if, index: " + j);
					}
					if(!found){
						returnSpace = gameBoard.gameSpaces[gameBoard.NUMBER_OF_SPACES-1];
					}
				}
			}
			
		}
		// Starts the search for the next valid space five spaces ahead of current, then will search at most five spaces ahead for the next valid space
		else if(doub.equals("double")){
			if(currentIndex < gameBoard.NUMBER_OF_SPACES - 7){
				for(int j = currentIndex+6; j < Math.min(currentIndex+11, gameBoard.NUMBER_OF_SPACES-1); j++){
					if(gameBoard.gameSpaces[j].getColor().equals(color)){
						returnSpace = gameBoard.gameSpaces[j];
						found = true;
						//System.out.println("second if, index: " + j);
					}
					if(!found){
						returnSpace = gameBoard.gameSpaces[gameBoard.NUMBER_OF_SPACES-1];
					}
				}
			}
			else{
				returnSpace = gameBoard.gameSpaces[gameBoard.NUMBER_OF_SPACES-1];
			}
		}
		
		return returnSpace;
	}
	
	public Space getNextValidBoomSpace(String color, String doub, int boomChoice)
	{
		boolean found = false;
		Space returnSpace = gameBoard.gameSpaces[0];
		Space currentSpace = players[boomChoice].getCurrentSpace();
		int currentIndex = -1;
		// Gets the index of the space in gameSpaces the current player is on
		if(color.equals("start")){
			currentIndex = 0;
		}
		else{
			for(int i = 0; i < gameBoard.NUMBER_OF_SPACES; i++){
				if(gameBoard.gameSpaces[i].getLabel().equals(currentSpace.getLabel())){
					currentIndex = i;
					// System.out.println("found current space, index: " + i);
				}
			}
		}
		// Looks ahead at most five spaces to find the next valid space
		if(doub.equals("single")){
			if(currentIndex == 0){
				returnSpace = gameBoard.gameSpaces[0];
			}
			else{
				for(int j = currentIndex-1; j > Math.max(currentIndex-6, 0); j--){
					if(gameBoard.gameSpaces[j].getColor().equals(color)){
						returnSpace = gameBoard.gameSpaces[j];
						found = true;
						//System.out.println("first if, index: " + j);
					}
					if(!found){
						returnSpace = gameBoard.gameSpaces[0];
					}
				}
			}
			
		}
		// Starts the search for the next valid space five spaces ahead of current, then will search at most five spaces ahead for the next valid space
		else if(doub.equals("double")){
			if(currentIndex > 7){
				for(int j = currentIndex-6; j > Math.max(currentIndex-11, 0); j--){
					if(gameBoard.gameSpaces[j].getColor().equals(color)){
						returnSpace = gameBoard.gameSpaces[j];
						found = true;
						//System.out.println("second if, index: " + j);
					}
					if(!found){
						returnSpace = gameBoard.gameSpaces[0];
					}
				}
			}
			else{
				returnSpace = gameBoard.gameSpaces[0];
			}
		}
		
		return returnSpace;
	}

	public void pause(long ms)
	{
		try        
		{
			Thread.sleep(ms);
		} 
		//simulate movement time for testing purposes
		catch(InterruptedException ex)   
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
		// doAIWork();
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
	
	public void doAIWork(boolean mode){
		Random drawOrBoom = new Random();
		Robot aiPlay;
		try{
			Point originalLocation = MouseInfo.getPointerInfo().getLocation();
			aiPlay = new Robot();
			if(isCurPlayerAI() && mode){
				if(drawOrBoom.nextInt(2) == 1 && players[turn].haveBoomerangs()){
					aiPlay.mouseMove(27, 350);
					aiPlay.mousePress(InputEvent.BUTTON1_MASK);
					aiPlay.mouseRelease(InputEvent.BUTTON1_MASK);
					aiPlay.mouseMove(125, 537);
					aiPlay.mousePress(InputEvent.BUTTON1_MASK);
					aiPlay.mouseRelease(InputEvent.BUTTON1_MASK);
					aiPlay.mouseMove((int) originalLocation.getX(), (int) originalLocation.getY());
				}
				else{
					aiPlay.mouseMove(125, 537);
					aiPlay.mousePress(InputEvent.BUTTON1_MASK);
					aiPlay.mouseRelease(InputEvent.BUTTON1_MASK);
					aiPlay.mouseMove((int) originalLocation.getX(), (int) originalLocation.getY());
				}
			}
			else if(isCurPlayerAI()){
				aiPlay.mouseMove(125, 537);
				aiPlay.mousePress(InputEvent.BUTTON1_MASK);
				aiPlay.mouseRelease(InputEvent.BUTTON1_MASK);
				aiPlay.mouseMove((int) originalLocation.getX(), (int) originalLocation.getY());
			}
		}
		catch(AWTException ae){}
		catch(IllegalArgumentException iae){}
		
	}
	
	
}