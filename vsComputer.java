import java.util.Scanner;
import java.util.*;
import java.lang.Math;
import java.util.Arrays;
import java.util.Random;

public class vsComputer extends Grid{

	// easy mode for vsComputer
	public static void easycomp(){
		// returns column = 4 for easy mode
		int numCols = Grid.getCols();
		// returns row = 4 for easy mode
		int numRows = Grid.getRows();
		// redturns the 1d array full of random values 
		setCount( Count);
		computer(agrid , 4 , 4);
		
	}
	public static void hardcomp(){
		// returns column = 6 for hard mode
		Grid.setCols(6);
		// returns row = 6 for hard mode
		Grid.setRows(6);
		// sets the grid to grid2 for hard mode which is a 6x6 using the numCols and numRows from above
		Grid.setGrid( grid2);
		Grid.setCount( Count2);
		computer(agrid , 6 , 6);
	}
	
	
	public static void computer(int [][]agrid2 , int nn , int cc){
		// makes the num rows to the either 4 or 6
		int numRows = nn;
		//makes the num columns to either 4 or 6
		int numCols = cc;
		// uses agrid2 as object to switch between grids
		int[][] grid =  Grid.createGrid(agrid2);
		// the String array that will display the "*" or the number matched
		String[][] array = new String[numRows][numCols];
		//comp_mem will remember the card matched or not matched for the computer to use
		int [][] comp_mem = new int[numRows][numRows];
		// fills array with "*"
		for(int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				array[i][j] = "*";
			}
		}
		
		System.out.println("Before: ");
		// prints array with | between each column
	    for(int i = 0; i<numRows; i++){
			for(int j = 0; j<numCols; j++){
				if (j <= numCols - 2)
				System.out.print("|" + array[i][j]);
				if (j == numCols - 1)
				System.out.print("|" + array[i][j] + "|");
			}
		System.out.println();
		}
		// m will decide whose turn it is
		// score 1 represents player 1's score
		// score 2 represents player 2 , in this case the computers score
			int score1=0;int m=1; int score2 = 0;
			// will make a loop until all the card are matched , ie. score1 + score 2
			for(int i = 0;((score1 + score2)<((numRows * numCols)/2));i++) {
				// if m is divisivble by 2, it will reset it to 2, ie player 2
				if (m%2==0)
		    		m=2;
				// if m is not divisible by 2, it will automatically switch it to player 1 turn
		    	else
		    		m=1;
				// invokes condition for player 1
				if (m == 1){
				// b = the x coordinate for input 1
				System.out.print("\nPlayer " + m+" please enter your X-coordinate for card one: ");
				Scanner input = new Scanner(System.in);
				int b = input.nextInt();
				// a= the y coordinate for input 1
				System.out.print("Player " +m+ " please enter your Y-coordinate for card one: ");
				int a = input.nextInt();
				// will ask to re enter a and b if it is outside the allowed limit
				if (a >= numRows && b >= numCols) {
					System.out.println("\nPlease Choose valid X and Y coordinates");}
				// proceeds to this if a and b is within the allowed limit
				else {	
					// the user1 frist input will be stored to value1
					int value1 =grid[a][b];
					// will input the valid x and y coordinates to comp_mem for the computer to use later
					// because the comp_mem is a int list, it is filled with 0
					// thus if the input is 0, then we convert it to 100
					if (grid[a][b] == 0){
						comp_mem[a][b] = 100;
					}
					// if the input is not 0, the comp mem will directly copy the grid[a][b] to the same coordinate
					else{
						comp_mem[a][b] = grid[a][b];
						System.out.println("yes");
					}
					// prints the first card the player has picked
					System.out.println("Your first card is: " +value1);

				// int d is the x coordinate for the second card for player 1
				System.out.print("\nPlayer " +m+ " please enter your X-coordinate for card two: ");
				int d = input.nextInt();
				// int c is the y coordinate for the second card that player 1 picks
				System.out.print("Player " +m+ " please enter your Y-coordinate for card two: ");
				int c = input.nextInt();
				// gives limit , and if the value c and d is outside the range, it will prompt the user to re enter a valid number
				if (c >= numRows && d >= numCols) {
					System.out.println("\nPlease Choose valid X and Y coordinates");}
				else {
					// if c and d also fall in the correct range this will prompt
					//grid[a][b] , which is the second card picked will be stored to value2
					int value2 =grid[c][d];
					// just like above if the second card picked is 0, it will store  100 in comp_mem
					if (grid[c][d] == 0){
						comp_mem[c][d] = 100;
					}
					// if second card picked is not 0, it will store that amount
					else{
						comp_mem[c][d] = grid[c][d];
					}
					//prints the second card to the player
					System.out.println("Your second card is: " +value2);
				if ( a== c && b ==d ){
					System.out.println( "The cards: " + grid[a][b] + " and: " + grid[c][d] + " are the same card \nPlease choose 2 different card");
				}
				if ((array[a][b].equals("" + grid[a][b]))){
					System.out.println("These cards have already been matched");
				}
				// presents a restriction of that the user cannot pick the same card twice
				if ( ( a != c || b!= d) && (!array[a][b].equals("" + grid[a][b]))){
					// if the first and second card is the same number, the program will procceed to this
					if (value1 == value2) {
						// player 1 score will increase by 1
						score1=score1+1;
						System.out.println("Player " + m + " score is: "+score1);
						System.out.println("Computer  score is: "+score2);
						// stores the string version of value 1 to store in the variable array
						String v1=Integer.toString(value1);
						array[a][b]=v1; 
						// stores the string version of value 2 to the varable array
						String v2=Integer.toString(value2);
						array[c][d]=v2;
						// will increase m by 1, prompting the computer's turn
						m++;
					
					}
				// will prompt this if the first and the second card donot match
				else 
					{
					// will display the score of the player and computer if the cards donot match
					System.out.println();
					System.out.println("They do not Match!");
					System.out.println("Player " + m + " score is: "+score1);
					System.out.println("Computer  score is: "+score2);
					// increases m by 1 so that the turn changes to the computer
					m++;	
						}
					}
				}
			}
		}  
				// will execute the random class from the import random
				Random rand = new Random();	
				// prob will store a random value from 0 to 99 for later use
				int prob = rand.nextInt(100);
				//System.out.println("prob is: " + prob);
				// promts the computer's turn
				if (m ==2){
					// if the probabilty goes from 0 to 49 ( 50% chance ) for easy mode, this function will prompt
					// if the probabilty goes from 0 to 24 ( 24% chance) for hard mode, this function will be prompt
					if (prob >= 0 && ((prob <= 49 && numRows == 4) || (prob <= 24 && numRows == 6))){
						int n = rand.nextInt(numRows); // row of the first random number 
						int c = rand.nextInt(numCols); // column of the first random number
						int n2 = rand.nextInt(numRows); // row of the second random number
						int c2 = rand.nextInt(numCols); // column of the second random number
						
						// makes sure the random value that has been generated has not already been matched before in the "array" grid
						for(int y = 0; y<numRows; y++){
							for(int e = 0; e<numCols; e++){
								while (array[y ][e ].equals("" + grid[n][c])){
									n = rand.nextInt(numRows);
									c= rand.nextInt(numCols);
								}
								while (array[y ][e ].equals("" + grid[n2][c2])){
									n2 = rand.nextInt(numRows);
									c2 = rand.nextInt(numCols);
								}
							}
						}
						//store the first card that the computer picks as value3
						int value3 = grid[n][c];
						// stores the first card to comp_mem for later use
						// because the comp_mem cannot store 0, it will convert any 0 to 100
						if (grid[n][c] == 0){
							comp_mem[n][c] = 100;
							}
							// if the first card is not 0, it will store the same card is the same place from grid to comp_mem
							else{
								comp_mem[n][c] = grid[n][c];
							}
						// stores the second card picked by the comuputer
						int value4 = grid[n2][c2];
						//just like above, converts 0 to 100 for storage
						if (grid[n2][c2] == 0){
							comp_mem[n2][c2] = 100;
						}
						// stores the non 0 card to comp_mem from grid
						else{
							comp_mem[n2][c2] = grid[n2][c2];
						}
						// prompts this if the first card values result to be the same, and that they are not the same card
						// also safety measure so that they are the same number but not the same card
						if (value3 == value4 &&((n != n2) || (c != c2) )){
							System.out.println("The computer has matched 2 cards");
							System.out.println("The computer has picked: " + value3);
							System.out.println("The second number the computer has picked is: " + value4);
							// increases score by 1 if this happens
							score2+=1;
							// stores them string version of value 3 in the varaible array
							String v3=Integer.toString(value3);
							array[n][c]=v3; 
							// stores the string version of value4 in the variable array
							String v4=Integer.toString(value4);
							array[n2][c2]=v4;
							// prints the computer's score and lplayer's score
							System.out.println("Player 1 score is: "+score1);
							System.out.println("Computer Score is: " + score2);
							// subtracts m by 1 so that the program will go back to player 1 again
							m--;
						}	
						// promots these rules if the first card and the second card the computer picks donot equal each other
						if (value3 != value4) {
							// if the first card and the second card donot equal each other
							// the first card will be stores to value 5 to be printed
							System.out.println();
							int value5 = grid[n][c];
							System.out.println("The computer has picked: " + value5);
							// the second card will be stroed as value 6 to be stored
							int value6 = grid[n2][c2];
							System.out.println("The second number the computer has picked is: " + value6);
							System.out.println("They do not Match!");
							// will display the score of player 1 and computer 
							System.out.println("Computer Score is: " + score2);
							System.out.println("Player 1 score is: "+score1);
							// will increase the m by 1, so that the program will go back to player 1 for inputs and continue the program
							m--;
							}	
						// at the end of 2 turns, the program will print the array will updated value
						for(int y = 0; y<numRows; y++){
							for(int e = 0; e<numCols; e++){
								System.out.print("|" + array[y][e]);
								}
								System.out.println();
							}							
						}
						
					
	
				}

					int tracker = 0; // will recrod the number 
					int q = 0; // row of the first number the computer will pick 
					int r = 0;// column of the first number the computer will pick
					int s = 0;// row of the second number the computer will pick
					int t = 0;// column of the second number the computer will pick
					int track = 0; 
					int got = 0; 
					int counter = 0;
					int tracking = 0;
					int rw = 0;
					int cl = 0;
					int[] arr = new int[numCols * numRows];
				// will prompt this option if the probabilty is more than 50 and lower that 99 and is easy more, ie numCols & numRows = 4
					// or if probility is higher than 25 and lower than 99  and is hard mode, ie. numCols & numRows = 6
					// 75% chance for this option to be prompt in hard mode, and 50% chance for this option to be prompt in easy mode
				if ( m == 2){
					if (((prob >= 50 && numRows == 4) || (prob>= 25 && numRows == 6)	) && prob <= 99){
						// will convert the comp_mem (2d array ) to arr (1d array) for easier access
						for(int p = 0 ; p<numRows; p++){
							for(int z = 0; z<numCols; z++){
								arr[counter] = comp_mem[p][z];
								// counter will keep track of the column number for arr and will increase by 1 so that the values from comp_mem 
								//can be spread to every column
								counter +=1;
							}
						}
						//System.out.println(Arrays.toString(arr));
						// will reset the counter for further use
						counter = 0;
						// compares grid, arr and array so that no values already guess right from array can be picked \
						// got will store the number if there is 2 of the same unmatched card in the arr 
						// if there is such a card, traking will be converted to 1 so that there is a indication for this process
						for(int p = 0 ; p<(numRows * numCols); p++){
							for(int z = 0; z<(numRows*numCols); z++){
								if ( (arr[counter] != 0) && 
								((!array[rw ][cl ].equals("" + arr[counter])) &&( arr[counter] != 100 &&(!array[rw][cl].equals("" +0))) )){
									if ((arr[counter] == arr[z]) && (counter != z)){
										got = arr[z];
										//System.out.println("got is: " + got );
										tracking = 1;
									}
								}
								cl += 1;
								if (cl == numCols){
									cl = 0;
									rw += 1;
								}
							}
							counter += 1;
							rw = 0;
						}
						//will prompt this option if tracking was changed to one, indicating there is 2 of the same card unmatched
						if (tracking == 1){
							for(int p = 0 ; p<numRows; p++){
								for(int z = 0; z<numCols; z++){
									if ( (comp_mem[p][z] == got) && track == 0 ){
										// q will take the row of the unmatched card
										q = p;
										// r will take the column of the first unmatched card
										r = z;
										// track = 1 indicates that the first card has been taken
										track = 1;
										
									}
									// q and the new p or r and and the new z must not be the same, otherwise it is the same card
									if ((comp_mem[p][z] == got) && ((q != p) || ( r != z)) && track == 1){
										// s will take the row of the second unmatched card
										s = p;
										// t will stroed the column of the second unmatched card
										t = z;
										// track changed to 2, so that the computer will not automatically keep doing this process
										track = 2;

									}
								}
							}
						//value 5 will take that first card value and store it
						int value5 = grid[q][r];
						
						System.out.println("The computer has picked: " + value5);
						
						// value 6 will store the potential second of the same number and store it
						int value6 = grid[s][t];
						System.out.println("The second number the computer has picked is: " + value6);
						// prompts this if the two cards picked are the same, which it should be, and if the first and second card is not the same
						if (value5 == value6 && ((q != s)||(r!= t))){							
							System.out.println("The computer has matched 2 cards");
							// increases the score for computer if card matched
							score2 += 1;
							String v5=Integer.toString(value5);
							array[q][r]=v5; 
							String v6=Integer.toString(value6);
							array[s][t]=v6;
							m--;
							// prints player 1 and computer's score
							System.out.println("Player 1 score is: "+score1);
							System.out.println("Computer Score is: " + score2);
							//prints the updated array to show the matched card
							for(int o = 0; o<numRows; o++){
								for(int j = 0; j<numCols; j++){
									System.out.print("|" + array[o][j]);
									}
									System.out.println();
								}
						}
						// if the two card donot match this will prompt
						// which should not happen, but just in case
						if (value5 != value6){
							// will change m to 1 so that player 1 can be prompt again
							m--;
							// prints the update array
							for(int o = 0; o<numRows; o++){
								for(int j = 0; j<numCols; j++){
									System.out.print("|" + array[o][j]);
									}
									System.out.println();
								}
						}
						}
						// tracking is 0,indicating that the commputer couldnot find 2 card that matched in comp_mem
						if (tracking == 0){

							// these will go from 0 to numCols - 1 or numRows - 1
							int no = rand.nextInt(numRows); // row of the first random number 
							int co = rand.nextInt(numCols); // column of the first random number
							int no2 = rand.nextInt(numRows); // row of the second random number
							int co2 = rand.nextInt(numCols); // column of the second random number
							
							// makes sure the random value have not already been matched
							for(int y = 0; y<numRows; y++){
								for(int e = 0; e<numCols; e++){
									while (array[y ][e ].equals("" + grid[no][co])){
										no = rand.nextInt(numRows);
										co = rand.nextInt(numCols);
									}
									while (array[y ][e ].equals("" + grid[no2][co2])){
										no2 = rand.nextInt(numRows);
										co2 = rand.nextInt(numCols);
									}
								}
							}
								// thus we will do the same as aboce, where it will pick random card and store them onto variable
								// stores the first card into value3
								int value3 = grid[no][co];
								// will stores everything but 0, which is turned into 100 in comp_mem
								if (grid[no][co] == 0){
									comp_mem[no][co] = 100;
									}
									else{
										comp_mem[no][co] = grid[no][co];
									}
							// value 4 is the second card that was randomly picked
								int value4 = grid[no2][co2];
							// stores that into the comp_mem for later use
								if (grid[no2][co2] == 0){
									comp_mem[no2][co2] = 100;
								}
								else{
									comp_mem[no2][co2] = grid[no2][co2];
								}
							// this path occurs in the instance that the two cards match and are not the exact same cards
								if (value3 == value4 &&((no != no2) || (co != co2) )){
									System.out.println("The computer has matched 2 cards");
									System.out.println("The computer has picked: " + value3);
									System.out.println("The second number the computer has picked is: " + value4);
									// the computer score will increase by 1
									score2+=1;
									String v3=Integer.toString(value3);
									array[no][co]=v3; 
									String v4=Integer.toString(value4);
									array[no2][co2]=v4;
									System.out.println("Player 1 score is: "+score1);

									System.out.println("Computer Score is: " + score2	);
									// m will become 1 again, so that the next thing that occurs is player 1 turn
									m--;
								}	
								// this patch occurs if the 2 card values donot match
								if (value3 != value4) {
									// stores the first card as value3
									System.out.println();	
									 value3 = grid[no][co];
									System.out.println("The computer has picked: " + value3);
									// stores the second card as value 4
									 value4 = grid[no2][co2];
									 //displayes the current score for both parties
									System.out.println("The second number the computer has picked is: " + value4);
									System.out.println("They do not Match!");
									System.out.println("Player 1 score is : "+ score1);
									System.out.println("Computer Score is : " + score2);
									// m becomes 1 again and will promp player 1 turn
									m--;
									}		
									// will display the updated  array again 
								for(int y = 0; y<numRows; y++){
									for(int e = 0; e<numCols; e++){
										System.out.print("|" + array[y][e]);
										}
										System.out.println();
									}
								}
						}
					}
			}
				// this will prompt when the game is over and the score 1 + score 2 is higher than numCols * numRows   / 2
				System.out.println("Game Over\nMatch Summary\n\n");
				
				// if score 1 is higher player 1 wins
				if (score1 > score2) {
					System.out.println("Player 1 wins!");
				}
				// if score 2 is higher the computer wins
				else if (score1 < score2) {
					System.out.println("The Computer has won!");
				}
				else {
					System.out.println("Play again!");
				} 
				
	}
}