import java.util.Scanner;
import java.util.*;
import java.util.Arrays;

public class Grid{
	//public static int[][] agrid = new int[numRows][numCols];
	//public static int[][] grid2 = new int[numRows2][numCols2];
	// 1d array to put values in


	public static int[][] createGrid(int [][] agrid , int row, int col) {
		int numRows = row;
		int numCols = col;
		int [] Count = new int[ ( ( numRows * numCols))];
		// specifies what grid we will use
		int [][]grid =agrid; 
		// counter will keep track of the 1d array
		int counter = 0;
		// fills the enpty grid with 2 pairs of each number
		for( int i = 0; i < ( ( numRows * numCols)); i+= 2){ 
			if (i == 0 || i == 1)
				Count[0] = 0;
				Count[1] = 0;
			if (i >= 2)
				Count[i] = i/2;
				Count[i+1] = i/2;
		}
		
		// random will allow random to occur
		Random random = new Random();
		// will randomize the grid 
		for (int i = Count.length - 1; i > 0; i--) {
			int m = random.nextInt(i + 1);
			int temp = Count[i];
			Count[i] = Count[m];
			Count[m] = temp;
			}

		// counts the amount of elements in list
		for(int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = Count[counter];
				counter += 1;
			}

			String array = Arrays.toString(grid[i]);
			System.out.println(array);
		}
			System.out.println();
			return (grid);
	}

	

}
