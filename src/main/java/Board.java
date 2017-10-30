import java.util.*;
import java.io.*;

public class Board{
	ArrayList<Space> gameSpaces = new ArrayList<>();
	public Board(){
		//TODO: Addison:  add the logic however necessary, don't forget to update the size of this array
		File file  = new File("temp.txt");
		Scanner in = new Scanner(System.in);
		
		
		try{
			file = new File("./SpaceCoordinates.txt");
			in = new Scanner(file);
		}
		catch(FileNotFoundException e){
			System.out.println("whomp whomp");
			System.exit(1);
		}
		
		// Cycle through all coordinates and generate Spaces at those coordinates
		// For spaces that aren't the start or end, repeatedly assign colors in the order r->y->b->g->o
		int i = 0;
		while(in.hasNext()){
			String line[];
			String input = in.nextLine();
			line = input.split(", ");
			int x1, x2, y1, y2;
			x1 = Integer.parseInt(line[0]);
			// x2 = Integer.parseInt(line[1]);
			y1 = Integer.parseInt(line[2]);
			// y2 = Integer.parseInt(line[3]);
			if(i == 0){
				gameSpaces.add(new Space(x1, y1, true, false, "start"));
			}
			else if(i == 31){
				gameSpaces.add(new Space(x1, y1, false, true, "all"));
			}
			else{
				switch(i%5){
					case 0:
					gameSpaces.add(new Space(x1, y1, false, false, "orange"));
					break;
					
					case 1:
					gameSpaces.add(new Space(x1, y1, false, false, "red"));
					break;
					
					case 2:
					gameSpaces.add(new Space(x1, y1, false, false, "yellow"));
					break;
					
					case 3:
					gameSpaces.add(new Space(x1, y1, false, false, "blue"));
					break;
					
					case 4:
					gameSpaces.add(new Space(x1, y1, false, false, "green"));
					break;
				}
			}
			i++;
		}
		
	}
	
}