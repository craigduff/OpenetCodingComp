import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class tele {
	int x;
	boolean isEmpty = true;
	
	tele() {
		x = -1;
	}
	
	tele(int x0) {
		x = x0;
		isEmpty = false;
	}

}

public class CodingComp {
	public static void main (String [] args) {
		try {
			//read and create board
			Scanner input = new Scanner(new File("input.txt"));
			int n = input.nextInt();
			int m = input.nextInt();
			int snakes = input.nextInt();
			int ladders = input.nextInt();
			
			int play = 0; //players position
			int movesTaken = 0; //moves taken
			
			tele [] grid = new tele [(n*m+1)];
			for(int i=0;i<grid.length;i++) {
				grid[i] = new tele();
			}
			
			int i = 0; //add snakes
			while(i < snakes && input.hasNext()) {
				int h = input.nextInt();
				int t = input.nextInt();
				grid[h]=new tele(t);
			}
			
			i = 0; //add ladders
			while(i < ladders && input.hasNext()) {
				int p = input.nextInt();
				int q = input.nextInt();
				grid[q]=new tele(p);
			}
			
			//read in list of gems and add to arraylist
			ArrayList<Integer> gems = new ArrayList<Integer>();
			while(input.hasNextInt()) {
				int num = input.nextInt();
				gems.add(num);
			}
			
			for(int y = 1; y<grid.length;y++){
				if(grid[y].isEmpty) {
					System.out.print("-");
				}
				else System.out.print("S");
				if(y%10==0) {
					System.out.println("");
				}
			}
			System.out.print("\n");
		
			//THE MAIN LOOP
			while(play!=100) {
				int best = 0; //save the best possible space reachable
				int bestRoll = 0; //save the best roll
				int closestGem = 23; //for testing purposes
				boolean gemsRemaining = true;
				
				/*	
				//begin by checking for closest gem
				if((closestGem - play) < 7 && gemsRemaining) { //check if the gem is within 6 spaces and move to it
					bestRoll = closestGem - play;
					best = closestGem;
					movesTaken++;
					System.out.println("FROM: "+(play)+" TO: "+(best)+" ROLL: "+bestRoll +" MOVES: "+movesTaken);
					play = best;
					System.out.println("GEM FOUND!");
					gemsRemaining = false;
				}
				*/
				//check if the goal is within 6 spaces and move to it
				
				if((grid.length-1 - play) < 7) { //don't go out of bounds
					bestRoll = grid.length-1 - play;
					best = grid.length-1;
					movesTaken++;
					System.out.println("FROM: "+(play)+" TO: "+(best)+" ROLL: "+bestRoll +" MOVES: "+movesTaken);
					play = best;
					System.out.println("Done");
					System.exit(0);
				}
				
				//check 6 spaces
				for(i=1;i<=6;i++) {
					if(grid[(play+i)].isEmpty) {
						if(play+i>best) {
							best = play+i; //records the new best space
							bestRoll = i; //records the new best roll
						}
					} else if(!grid[(play+i)].isEmpty) {
						//if a space is a snake or a ladder, check where it leads to
						if(grid[play+i].x > best) {
							best = grid[play+i].x;
							bestRoll = i;
						}
					}
				}
				movesTaken++; //increment moves
				System.out.println("FROM: "+(play)+" TO: "+(best)+" ROLL: "+bestRoll +" MOVES: "+movesTaken);
				play = best;
			}
		} catch (IOException e) {
			System.out.print(e);
		}
	}
}
