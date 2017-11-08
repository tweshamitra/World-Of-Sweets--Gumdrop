package wos;

import java.util.*;
import java.io.*;
import java.net.*;
import wos.*;

public class Board{
	final int NUMBER_OF_SPACES = 32;
	Space[] gameSpaces = new Space[NUMBER_OF_SPACES];
	public Board(){
		//TODO: Addison:  add the logic however necessary, don't forget to update the size of this array
		/*File file  = new File("temp.txt");
		Scanner in = new Scanner(System.in);
		URL url = Board.class.getResource("SpaceCoordinates.txt");
		
		try {
		  file = new File(url.toURI());
		} catch(URISyntaxException e) {
		  file = new File(url.getPath());
		}*/
		
		BufferedReader in = new BufferedReader(new InputStreamReader( Board.class.getResourceAsStream("SpaceCoordinates.txt") ) );
		if ( in == null )
		{
			System.out.println("Space Coordinates file not found!");
			System.exit(1);
		}
		
		
		
		/*try{
			file = new File("./SpaceCoordinates.txt");
			in = new Scanner(file);
		}
		catch(FileNotFoundException e){
			System.out.println("Space Coordinates file not found!");
			System.exit(1);
		}*/
		
		// Cycle through all coordinates and generate Spaces at those coordinates
		// For spaces that aren't the start or end, repeatedly assign colors in the order r->y->b->g->o
		int i = 0;
		try{
		while(in.ready()){
			String line[];
			String input = in.readLine();
			line = input.split(", ");
			int x1, y1;
			x1 = Integer.parseInt(line[0]);
			y1 = Integer.parseInt(line[2]);
			if(i == 0){
				gameSpaces[i] = new Space(x1, y1, true, false, "start", 0);
			}
			else if(i == 31){
				gameSpaces[i] = new Space(x1, y1, false, true, "all", 0);
			}
			else{
				switch(i%5){
					case 0:
					gameSpaces[i] = new Space(x1, y1, false, false, "orange", (i/5));
					break;
					
					case 1:
					gameSpaces[i] = new Space(x1, y1, false, false, "red", (i/5)+1);
					break;
					
					case 2:
					gameSpaces[i] = new Space(x1, y1, false, false, "yellow", (i/5)+1);
					break;
					
					case 3:
					gameSpaces[i] = new Space(x1, y1, false, false, "blue", (i/5)+1);
					break;
					
					case 4:
					gameSpaces[i] = new Space(x1, y1, false, false, "green", (i/5)+1);
					break;
				}
			}
			i++;
		}
		
	}
	catch(IOException e)
	{
		System.exit(1);
	}
	}
	
	
}