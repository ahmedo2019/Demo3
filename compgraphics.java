
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;
import javax.swing.JFrame;

import java.awt.AWTException;

//import compgraphics.Tile;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;

/*************************************************************************
 * The purpose of this compgraphics.java class is to show the GUI or Graphics User
 * Interface in the Card Match Memory Game for the computer vs. player game.
 * The GUI is using the imported Application called the java.fx or java.swing.
 * The compgraphics.java is not yet fully connected to the other classes rather it presents a
 * menu screen and allows for the menu and the game to be displayed by
 * running the Game.java class.
 * ***********************************************************************
 */

public class compgraphics extends Application {
	static int  numRows = 4;
	static int numCols = 4;

	public static void setRows(int row) {
		numRows = row;
	}
	public static void setCols(int cols) {
		numCols = cols;
	}

    Tile selected = null;	// selected will keep track of the card clicked
    int counter = 2;		// counter 2 = first card, counter 1 = second card
    int player = 1;			// will alternate between the players
    int[][] comp_mem = new int[numRows][numCols];	// array that will keep track of all the cards discovered
    int score1 = 0;			// score for the player
    int score2 = 0;			// score for the computer
	int [][] agrid = new int[numRows][numCols];		// empty grid that will be filled with random values
	String[][] array = new String[numRows][numCols];	// will keep track of all the matched cards
	int[][] grid =Grid.createGrid(agrid);			// grid will now point at agrid
	int[]coordtotile = new int[2];					// keep track of the coordinates of mouse clicked by the player
	int[]coordinatetotile = new int[4];				// will take the coordtotile for both cards and remember it
	int[] tiletocoordinate = new int[4];			// tile that will store the tile used by computer to coordinates
	int screenHeight;								// screenHeight will will store the height of the screen
	int screenWidth;								// screenWidth will store the width of the screen
	int []coordinate_array = new int[4];			// coordinate array will store the value that the vsComputer will give us

    public  Parent createContent() {

        Pane root = new Pane();
        root.setPrefSize(600, 600);					// will create a scene of this height and width
		for(int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				array[i][j] = "*";					// will make an array filled with * to be used by computer
			}
		}
			List<Tile> tiles = new ArrayList<>();	// will make the tiles into a array list
			
			for(int y = 0; y<numRows; y++){
				for(int e = 0; e<numCols; e++){
					tiles.add(new Tile(String.valueOf(grid[y][e])));		// adds the random values from grid to be transformed into tiles
				}
			}


			for (int i = 0; i < tiles.size(); i++) {
				Tile tile = tiles.get(i);
				tile.setTranslateX(150 * (i % numRows));				// will create the tile into a certian width
				tile.setTranslateY(150 * (i / numCols));				// will create the tile into a certain length
				root.getChildren().add(tile);							// adds the tile into the scene
		}
		return root;
	}
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(createContent()));				// will make the tiles to be shown
        primaryStage.show();											// will show the tiles currently made
        primaryStage.setTitle("CARD MATCH MEMORY GAME");
    }

    public class Tile extends StackPane {
        public Text text = new Text();

        public Tile(String value) {
            Rectangle border = new Rectangle(600/numRows, 600/numCols); 	// will make a rectangle that will fix into the tile

            border.setStroke(Color.MEDIUMTURQUOISE); //will choose what the border of the rectangle is
            border.setFill(Color.BLACK); // will choose the color of it

            text.setText(value); // the value of tile will be shown
            text.setFont(Font.font(100)); // font of the value will be shown
            text.setStroke(Color.MEDIUMTURQUOISE);
            
            setAlignment(Pos.CENTER);										// where the number will be shown
            getChildren().addAll(border, text);							

            setOnMouseClicked(event -> {
				try {
					handleMouseClick(event);								// executes the handle mouse click methof
				} catch (AWTException e) {
					e.printStackTrace();									// if execution if made
				}
			});
            close();														// closes the scene
        }
        
    	public int screenHeight() {
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        screenHeight = screenSize.height;				// will determine the screen height
	        int tileHeight = (screenHeight / 2) - 375;		// makes it so the height fits in the javafx 
	        System.out.println("screenheight is : " + tileHeight);
	       return tileHeight;								// returns the new height
    	}
    	
    	public int screenWidth() {
	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        screenWidth = screenSize.width;					// will determine the screen width
	        int tileWidth = (screenWidth / 2) - 300;		// will made thw width into the javafx screen
	        System.out.println("screenwidth is: "+ tileWidth);
	        return tileWidth;								// returns the value
    	}
    	
    	public int[] computerCoordsToTile(int x, int y) {
    		int width = screenWidth();
    		int height = screenHeight();
    		int distance = 600 / numRows;
    		for(int i = 0; i< numRows  ; i++){
    			for(int j = 0; j< numCols  ; j++){
    				if (((x >= (width +(distance * j))) && ( x < (width +(distance * (j+1)))) ) 
    				&& (y >= (height +(distance * i)))&& (y < (height +(distance * (i+1)))) ){
    					coordtotile[0] = j;					// will store the coordinates x clicked by player into a tile location
    					coordtotile[1] = i;					// will store the coordinates y clicked by player into a tile location
    				}    				
    			}
    		}
    		return(coordtotile);			// returns the array
    	}
    	public int[] tiletoCoordinate(int x , int y, int x2, int y2) {
    		int width = screenWidth();
    		int height = screenHeight();
    		int distance = 600 / numRows;
    		if (x != 0 ) {
			tiletocoordinate[0] = ((width + (distance * x)) + (distance/2)) ;		// will take the first tile's x given by comp into a coordinate
    		}
			if (x2 != 0 ) {
			tiletocoordinate[2] = ((width + (distance * x2))+ (distance /2)) ;		// will take the second tile's x given by comp into a coordinate
    		}
    		if(x== 0 ) {
    			tiletocoordinate[0] = width + (distance / 2);						
    		}
    		if (x2 == 0) {
    			tiletocoordinate[2] = width + (distance / 2) ;	
    		}      	
			if (y != 0) {
				tiletocoordinate[1] = ((height + (distance * y)) + (distance / 2)) ;	// will take the first tile's y given by comp into coordinate
			}
			if(y2 != 0) {
				tiletocoordinate[3] = ((height + (distance * y2)) + (distance / 2)) ;	// will take the first tile's y given by comp into coordinate
			}
			if (y == 0 ) {
				tiletocoordinate[1] = height + (distance/2) ;
			}
			if (y2 == 0) {
				tiletocoordinate[3] = height+ (distance / 2) ;
			}			
			System.out.println("width is: "+ (width +600) + "height is: "+ (height + 600)); 
    		return(tiletocoordinate);			// returns that as a array
    	}
		public void handleMouseClick(MouseEvent event) throws AWTException {
			if (player%2 != 0) {			// if player is greater than 2 or equals 1, the player is defaulted into player 1
				player = 1;
			}
			if (player == 1) {
				if (counter == 2) {			// will print that its player's 1 turn only once
					System.out.println("Current player is "+ player);
				}
				if ((isOpen() || event.getClickCount() > 1) || counter == 0) {	// makes it so that one tile can't be clicked multiple times
					return;
				}
				counter--;					// decrease the counter by 1 
				if (counter == 1) {		// will prompt the first click
					int coordx = MouseInfo.getPointerInfo().getLocation().x;		// will give the x coordinates clicked
					int coordy = MouseInfo.getPointerInfo().getLocation().y;		// will give the y coordinates clicked
					System.out.println("x is: " +MouseInfo.getPointerInfo().getLocation().x);
					System.out.println("y is: " +MouseInfo.getPointerInfo().getLocation().y);
					coordtotile = computerCoordsToTile(coordx , coordy);			// will store the x and y coordinates that was clicked
					coordinatetotile[0] = coordtotile[0];
					coordinatetotile[1] = coordtotile[1];
				}
				if (counter == 0) {												//will prompt the player for the second click
					int coordx1 = MouseInfo.getPointerInfo().getLocation().x;	// gives the x axis of this click
					int coordy1 = MouseInfo.getPointerInfo().getLocation().y;	// gives the y axis of this click
					System.out.println("x is: " +MouseInfo.getPointerInfo().getLocation().x);
					System.out.println("y is: " +MouseInfo.getPointerInfo().getLocation().y);
					coordtotile = computerCoordsToTile(coordx1,coordy1);		// stores the second x and y axis to coordtotile
					coordinatetotile[2] = coordtotile[0];
					coordinatetotile[3] = coordtotile[1];
				} 
				if (counter <= 2) {
					if (grid[coordtotile[1]][coordtotile[0]] != 0) {				// will store the coordinate to comp mem if it isnt the value 0
						comp_mem[coordtotile[1]][coordtotile[0]] = grid[coordtotile[1]][coordtotile[0]];
					}
					else {
						comp_mem[coordtotile[1]][coordtotile[0]] = 100;				// will store the coordinate as 100 in comp mem is the value is 0
					}
					if (selected == null ) {										
						selected = this;									// will make the tile into selected
						open(() -> {});
					}
					else {
						open(() -> {
							if (hasSameValue(selected)) {				// if both the clicks have the same value
								array[coordinatetotile[1]][coordinatetotile[0]] = Integer.toString(grid[coordinatetotile[1]][coordinatetotile[0]]);
								// will store the value in the array coordinate of the first card if the card have matched
								array[coordinatetotile[3]][coordinatetotile[2]] = Integer.toString(grid[coordinatetotile[3]][coordinatetotile[2]]);
								// will store the value in the array variable if the second card is matched
								score1 ++;
								// score increases by one because the cards have matched
								System.out.println("Player 1 score is: "+ score1);
								// displayes the score
	
							}
							else if (!hasSameValue(selected)) {	// if the values were not the same
								selected.close();				// closes the tiles
								this.close();					// closes the second tile
								System.out.println("this is: "+ this);
							}
						
							selected = null;				// selected reset
							counter = 2;					// counter reset
							player++;						// player increased by one, and prompts the computer's turn
						});
					}
				}
			}
			
			if (player == 2) {
				
				if (counter == 2) {
					//this.close();
					//selected = null;
					System.out.println("COMPUTER'S TURN");	// will print the computer's turn once
				}
				if ((isOpen() || event.getClickCount() > 1) || counter < 0) {
					return;									// makes it that the computer cannot click the same tile twice and not make the tile
				}
			if (counter == 2) {		// only occurs one per computer turn
				coordinate_array = vsComputer.computer(grid,comp_mem,array, numRows, numCols, counter);
				// will call the method in vsComp for computer player
				while ((coordinate_array[0] != coordinate_array[2]) && (coordinate_array[1] !=coordinate_array[3] )) {
					coordinate_array = vsComputer.computer(grid,comp_mem,array, numRows, numCols, counter);
					// checks if the coordinate array are not all the same coordinate
				}
				while (coordinate_array[0] == 0 &&coordinate_array[1]== 0 &&coordinate_array[2] == 0 &&coordinate_array[3] == 0 ) {
					coordinate_array = vsComputer.computer(grid,comp_mem,array, numRows, numCols, counter);
					// checks if the coordinate array isnt all 0
					}
				}
				tiletocoordinate = tiletoCoordinate( coordinate_array[0] ,  coordinate_array[1],  coordinate_array[2],  coordinate_array[3]);
				// will convert the coordinate array tiles location into coordinates 
				System.out.println(Arrays.toString(coordinate_array));
				System.out.println(Arrays.toString(tiletocoordinate));
				counter--;
				if (counter == 1) {	
					this.close();
					selected = null;
					autoClicker.click(tiletocoordinate[0], tiletocoordinate[1]);	// will click the first card picked

					System.out.println("coordd is: " + tiletocoordinate[0] + " " + tiletocoordinate[1]);	// print the coordinates that is clicked
					if (grid[coordinate_array[1]][coordinate_array[0]] != 0) {
						comp_mem[coordinate_array[1]][coordinate_array[0]] = grid[coordinate_array[1]][coordinate_array[0]];
						// will store the value into the comp mem array if it is not 0
					}
					else {
						comp_mem[coordinate_array[1]][coordinate_array[0]] = 100;
						// will store the value into comp mem as 100 if it is 0
					}

				}
				if (counter == 0) {		// will check if counter is 1 and will prompt the click
					System.out.println("count click is: " + counter);
					autoClicker.click(tiletocoordinate[2], tiletocoordinate[3]);		// will the second coordinate given by computer
					System.out.println("coorrd is: " + tiletocoordinate[2]+" "+ tiletocoordinate[3]);
					if (grid[coordinate_array[3]][coordinate_array[2]] != 0) {
						comp_mem[coordinate_array[3]][coordinate_array[2]] = grid[coordinate_array[3]][coordinate_array[2]];
						// will store the comp mem if it is not 0
					}
					else {
						comp_mem[coordinate_array[3]][coordinate_array[2]] = 100;
						// will store the comp mem as 100, if the value is not 0
					}
				}	

				System.out.println("count  is: " + counter);
				if (counter == 1) {
					if (selected == null) {
						selected = null;
					//	selected.close();
					}
				}
				if (counter < 1) {
					if (selected == null ) {	
						selected = this;
						open(() -> {});
					}
					else {
						open(() -> {
							if (hasSameValue(selected)) {
								array[coordinate_array[1]][coordinate_array[0]] = Integer.toString(grid[coordinate_array[1]][coordinate_array[0]]);
								// will store the values into the array variable if they match
								array[coordinate_array[3]][coordinate_array[2]] = Integer.toString(grid[coordinate_array[3]][coordinate_array[2]]);
								// will store the values into the array variable if they match
								score2 ++;	// will increase the score by 1
								System.out.println("Computer 1 score is: "+ score2);
							}
							else if (!hasSameValue(selected)) {
								selected.close();	// will close the selected tile
								this.close();		// will close the current tile
							}
						
							selected = null;		// resets the selected to null
							counter = 2;			// resets the counter to 2
							player++;				// increases the player by 1, prompting the player to be changed to player 1
						});
					}
				}
				
			}
		}
		
        public boolean isOpen() {
            return text.getOpacity() == 1.00;			// will change the opacity of the tile to 1, making it completely blank
        }

        public void open(Runnable actions) {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.6), text);
            ft.setToValue(1.0);
            ft.setOnFinished(e -> actions.run());
            ft.play();
        }

        public void close() {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), text);
            ft.setToValue(0);
            ft.play();
        }

        public boolean hasSameValue(Tile something) {
            return text.getText().equals(something.text.getText());	// if both the tiles have the same value, this is passed
        }
    }

    public static void main(String[] args) {

        launch(args);
		
    }

}
