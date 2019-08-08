import java.util.Random;

public class vsComputer extends Grid {
	public static int[] computer(int [][]agrid2 , int[][] acomp_mem,String[][]anarray, int nn , int cc, int acounter ){		// will execute the random class from the import random

		Random rand = new Random();	
		 // prob will store a random value from 0 to 99 for later use
		final int prob = rand.nextInt(100);
		// makes the num rows to the either 4 or 6
		int numRows = nn;
		//makes the num columns to either 4 or 6
		int numCols = cc;
		// uses agrid2 as object to switch between grids
		int[][] grid =  agrid2;
		// the String array that will display the "*" or the number matched
		//comp_mem will remember the card matched or not matched for the computer to use
		int [][] comp_mem = acomp_mem;
		String[][] array = anarray;
		int[]coordinate = new int[4];
		int counter = acounter;
		// if the probabilty goes from 0 to 49 ( 50% chance ) for easy mode, this function will prompt
		// if the probabilty goes from 0 to 24 ( 24% chance) for hard mode, this function will be prompt
	    if (counter ==2) {
				if (prob >= 0 && ((prob <= 49 && numRows == 4) || (prob <= 24 && numRows == 6) || (prob <= 74 && numRows == 2))){
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
				coordinate[0] = c;
				coordinate[1] = n;
				coordinate[2] = c2;
				coordinate[3] = n2;
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
					// stores them string version of value 3 in the varaible array
					String v3=Integer.toString(value3);
					array[n][c]=v3; 
					// stores the string version of value4 in the variable array
					String v4=Integer.toString(value4);
					array[n2][c2]=v4;
					// subtracts m by 1 so that the program will go back to counter 1 again
		
				}	
				// promots these rules if the first card and the second card the computer picks donot equal each other
				if (value3 != value4) {
					// if the first card and the second card donot equal each other
					// the first card will be stores to value 5 to be printed
					value3 = grid[n][c];
					System.out.println("The computer has picked: " + value3);
					// the second card will be stroed as value 6 to be stored
					value4 = grid[n2][c2];
					System.out.println("The second number the computer has picked is: " + value4);
					}	
				// at the end of 2 turns, the program will print the array will updated value
							}
			}
					int tracker = 0; // will record the number 
					int q = 0; // row of the first number the computer will pick 
					int r = 0;// column of the first number the computer will pick
					int s = 0;// row of the second number the computer will pick
					int t = 0;// column of the second number the computer will pick
					int track = 0; 
					int got = 0; 
					int counting = 0;
					int tracking = 0;
					int rw = 0;
					int cl = 0;
					int[] arr = new int[numCols * numRows];
				// will prompt this option if the probability is more than 50 and lower that 99 and is easy more, ie numCols & numRows = 4
					// or if propelled is higher than 25 and lower than 99  and is hard mode, ie. numCols & numRows = 6
					// 75% chance for this option to be prompt in hard mode, and 50% chance for this option to be prompt in easy mode
				if (counter ==2) {
					if (((prob >= 50 && numRows == 4) || (prob>= 25 && numRows == 6) || (prob >= 75)	) && prob <= 99){
						// will convert the comp_mem (2d array ) to arr (1d array) for easier access
						for(int p = 0 ; p<numRows; p++){
							for(int z = 0; z<numCols; z++){
								arr[counting] = comp_mem[p][z];
								// counter will keep track of the column number for arr and will increase by 1 so that the values from comp_mem 
								//can be spread to every column
								counting +=1;
							}
						}
						// will reset the counter for further use
						counting = 0;
						// compares grid, arr and array so that no values already guess right from array can be picked \
						// got will store the number if there is 2 of the same unmatched card in the arr 
						// if there is such a card, tracking will be converted to 1 so that there is a indication for this process
						for(int p = 0 ; p<(numRows * numCols); p++){
							for(int z = 0; z<(numRows*numCols); z++){
								if ( (arr[counting] != 0) && 
								((!array[rw ][cl ].equals("" + arr[counting])) &&( arr[counting] != 100 &&(!array[rw][cl].equals("" +0))) )){
									if ((arr[counting] == arr[z]) && (counting != z)){
										got = arr[z];
										tracking = 1;
									}
								}
								cl += 1;
								if (cl == numCols){
									cl = 0;
									rw += 1;
								}
							}
							counting += 1;
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
										// t will store the column of the second unmatched card
										t = z;
										// track changed to 2, so that the computer will not automatically keep doing this process
										track = 2;
									}
								}
							}
							coordinate[1] = q;
							coordinate[0] = r;
							coordinate[3] = s;
							coordinate[2] = t;
						//value 5 will take that first card value and store it
						int value5 = grid[q][r];
						System.out.println("Tthe computer has picked: " + value5);
						// value 6 will store the potential second of the same number and store it
						int value6 = grid[s][t];
						System.out.println("Tthe second number the computer has picked is: " + value6);
						// prompts this if the two cards picked are the same, which it should be, and if the first and second card is not the same
						if (value5 == value6 && ((q != s)||(r!= t))){							
							String v5=Integer.toString(value5);
							array[q][r]=v5; 
							String v6=Integer.toString(value6);
							array[s][t]=v6;
							//prints the updated array to show the matched card

						// if the two card donot match this will prompt
						// which should not happen, but just in case
						if (value5 != value6){
							// will change m to 1 so that counter 1 can be prompt again
							// prints the update array
						}
						}
						// tracking is 0,indicating that the commputer couldnot find 2 card that matched in comp_mem
						if (tracking == 0){

								int n = rand.nextInt(numRows); // row of the first random number 
								int c = rand.nextInt(numCols); // column of the first random number
								int n2 = rand.nextInt(numRows); // row of the second random number
								int c2 = rand.nextInt(numCols); // column of the second random number
								// makes sure the random value that has been generated has not already been matched before in the "array" grid
								for(int y = 0; y<numRows; y++){
									for(int e = 0; e<numCols; e++){
										while (array[y ][e ].equals("" + grid[n][c])){
											// row of first card
											n = rand.nextInt(numRows);
											//colum of first card
											c= rand.nextInt(numCols);
										}

										while (array[y ][e ].equals("" + grid[n2][c2])){
											//row of second card
											n2 = rand.nextInt(numRows);
											//column of first card
											c2 = rand.nextInt(numCols);
										}
									}
								}
								coordinate[1] = n;
								coordinate[0] = c;
								coordinate[3] = n2;
								coordinate[2] = c2;
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
			System.out.println("The computer has picked: " + value3);
			System.out.println("The second number the computer has picked is: " + value4);
			// stores them string version of value 3 in the varaible array
			String v3=Integer.toString(value3);
			array[n][c]=v3; 
			// stores the string version of value4 in the variable array
			String v4=Integer.toString(value4);
			array[n2][c2]=v4;
		}	
		// prompts these rules if the first card and the second card the computer picks donot equal each other
		if (value3 != value4) {
			// if the first card and the second card donot equal each other
			// the first card will be stores to value 5 to be printed
			value3 = grid[n][c];
			System.out.println("The computer has picked: " + value3);
			// the second card will be stroed as value 6 to be stored
			value4 = grid[n2][c2];
			System.out.println("The second number the computer has picked is: " + value4);
			}	
						}
						}
					}
				}

		return(coordinate);
		}
}
