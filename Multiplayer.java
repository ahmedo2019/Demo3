import java.util.Scanner;
import java.util.*;
import java.lang.Math;
import java.util.Arrays;
public class Multiplayer extends Grid {
	public static void Multiplay() {
		// this code will decide on which mode the game will run on
    	System.out.println("\n******Welcome to the Card Match Memory Game******\n");
		System.out.print("Enter 1 for Multiplayer or Enter 2 to play against a computer: ");
	    Scanner input = new Scanner(System.in);
	    int levelNum = input.nextInt(); 
		// leven num takes the first input

		// if levelNum = 1, it goes to multiplayer, levelNum =2 will go to vsComputer 
	   if (levelNum == 1|| levelNum ==2) {
		   // Multiplayer is chosen
	    	if (levelNum == 1) {
		    	System.out.println("You have chose the multiplayer option:\n");
				System.out.print("Enter 1 for Easy Level or Enter 2 for Hard Level: ");
			// pressing 1 will now initiate easy mode and pressing 2 will start hard mode in Multiplater
				Scanner go = new Scanner(System.in);
				int difficulty = go.nextInt();
				if (difficulty == 1 || difficulty == 2) {
					if (difficulty == 1) {
						System.out.println("\nLEVEL DIFFICULTY: EASY :\n");
						// inherets gridPlayEasy from the class Grid
						gridPlayEasy();
					}
					else if (difficulty == 2) {
						System.out.println("\nLEVEL DIFFICULTY: HARD :\n");
						//inherets gridPlayHard from class Grid
						gridPlayHard();
						
					}
				}
			}
			// pressing 2 in the beginning will move us to vsComputer
		   else if (levelNum == 2) {
				System.out.println("You have chosen the vsComputer option:\n");
				System.out.println("Please enter 1 for easy mode or Enter 2 for hard mode:\n");
				Scanner go = new Scanner(System.in);
				//compdifficulty will take the inputs given from player in vsCOmputer
				int compdifficulty = input.nextInt();
				if (compdifficulty ==1 || compdifficulty == 2){
					// compdifficulty 1 will initiate the easy vsCOmputer mode which has a 4x4 array with a low chance of the computer guess the cards rigjt
					if (compdifficulty ==1){
						System.out.println("Level Difficulty : EASY: \n");
						// imports the easycomp() from vsComputer to initiate easy mode
						vsComputer.easycomp();
					}
					// compdifficulty = 2 will initiate the hard version of vsComputer with a 6x6 array and a higher prob of the computer guess the cards right
					if (compdifficulty == 2){
						System.out.println("Level Difficulty : Hard: \n");
						// imports the hardcomp() from vsComputer to initiate hard mode
						vsComputer.hardcomp();
					}
					}
				}
			
		}
	}
}