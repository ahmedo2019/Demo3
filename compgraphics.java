
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

public class compgraphics extends Application {
	static int  numRows = 4;
	static int numCols = 4;
	static int size = 1100;
	public static void setRows(int row) {
		numRows = row;
	}
	public static void setCols(int cols) {
		numCols = cols;
	}

    Tile selected = null;	// selected will keep track of the card clicked
    int counter = 4;		// counter 2 = first card, counter 1 = second card
    int player = 1;			// will alternate between the players
    int[][] comp_mem = new int[numRows][numCols];	// array that will keep track of all the cards discovered
    int score1 = 0;			// score for the player
    int score2 = 0;			// score for the computer
	int [][] agrid = new int[numRows][numCols];		// empty grid that will be filled with random values
	String[][] array = new String[numRows][numCols];	// will keep track of all the matched cards
	int[][] grid =Grid.createGrid(agrid, numRows, numCols);			// grid will now point at agrid
	int[]coordtotile = new int[2];					// keep track of the coordinates of mouse clicked by the player
	int[]coordinatetotile = new int[4];				// will take the coordtotile for both cards and remember it
	int[] tiletocoordinate = new int[4];			// tile that will store the tile used by computer to coordinates
	int screenHeight;								// screenHeight will will store the height of the screen
	int screenWidth;								// screenWidth will store the width of the screen
	int []coordinate_array = new int[4];			// coordinate array will store the value that the vsComputer will give us

    public Parent createContent() {

        Pane root = new Pane();
        root.setPrefSize(size, size);					// will create a scene of this height and width
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
				tile.setTranslateX((size / numRows) * (i % numRows));				// will create the tile into a certianw width
				tile.setTranslateY((size/ numCols) * (i / numCols));				// will create the tile into a certain length
				root.getChildren().add(tile);							// adds the tile into the scene
		}
		return root;
	}
    
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(createContent()));				// will make the tiles to be shown
        primaryStage.show();											// will show the tiles currently made
        primaryStage.setFullScreen(true);
    }

    public class Tile extends StackPane {
    
    	
        public Text text = new Text();

        public Tile(String value) {
            Rectangle border = new Rectangle(size/numRows, size/numCols); 	// will make a rectangle that will fix into the tile

            border.setFill(null);											// will choose what the border of rectangle is
            border.setStroke(Color.BLACK);									// will choose the color of it

            text.setText(value);											// the value of tile will be shown 
            text.setFont(Font.font(30));
            text.setId("fancytext");
            
            setAlignment(Pos.CENTER);										// where the number will be shown
            getChildren().addAll(border, text);							

            setOnMouseClicked(event -> {
				try {
					handleMouseClick(event);								// exectues the handle mouse click methof
				} catch (AWTException e) {
					e.printStackTrace();									// if exection if made
				}
			});
            close();														// closes the scene
        }
        
    	public int screenHeight() {
	       return 0;								// returns the new height
    	} 	 
    	
    	public int screenWidth() {
	        return 0;								// returns the value
    	}
    	
    	public int[] computerCoordsToTile(int x, int y) {
    		int width = screenWidth();
    		int height = screenHeight();
    		int distance = size / numRows;
    		int x_cc = x;
    		int y_cc = y;
    		for(int i = 0; i< numRows  ; i++){
    			for(int j = 0; j< numCols  ; j++){
    				if (((x_cc >= (width +(distance * j))) && ( x_cc < (width +(distance * (j+1)))) ) 
    				&& (y_cc >= (height +(distance * i)))&& (y_cc < (height +(distance * (i+1)))) ){
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
    		int distance = size / numRows;
    		if (x >= 0 ) {
			tiletocoordinate[0] = ((width + (distance * x)) + (distance/2)) ;		// will take the first tile's x given by comp into a coordinate
    		}
			if (x2 >= 0 ) {
			tiletocoordinate[2] = ((width + (distance * x2))+ (distance /2)) ;		// will take the second tile's x given by comp into a coordinate
    		}     	
			if (y >= 0) {
				tiletocoordinate[1] = ((height + (distance * y)) + (distance / 2)) ;	// will take the first tile's y given by comp into coordinate
			}
			if(y2 >= 0) {
				tiletocoordinate[3] = ((height + (distance * y2)) + (distance / 2)) ;	// will take the first tile's y given by comp into coordinate
			}	
    		return(tiletocoordinate);			// returns that as a array
    	}
		public void handleMouseClick(MouseEvent event) throws AWTException {
			counter--;					// decrease the counter by 1 

			if (counter == 3) {			// will print that its player's 1 turn only once
				System.out.println("Current player is "+ player);
			}
			if (counter == 3 || counter == 2) 
				if ((isOpen() || event.getClickCount() > 1) || counter == 0) {	// makes it so that one tile can't be clicked multiple times
					return;
				}
			if (counter == 3) {		// will prompt the first click
				int coordx = MouseInfo.getPointerInfo().getLocation().x;		// will give the x coordinates clicked
				int coordy = MouseInfo.getPointerInfo().getLocation().y;		// will give the y coordinates clicked
				coordtotile = computerCoordsToTile(coordx , coordy);			// will store the x and y coordinates that was clicked
				coordinatetotile[0] = coordtotile[0];
				coordinatetotile[1] = coordtotile[1];
			}
			if (counter == 2) {												//will prompt the player for the second click
				int coordx1 = MouseInfo.getPointerInfo().getLocation().x;	// gives the x axis of this click
				int coordy1 = MouseInfo.getPointerInfo().getLocation().y;	// gives the y axis of this click
				coordtotile = computerCoordsToTile(coordx1,coordy1);		// stores the second x and y axis to coordtotile
				coordinatetotile[2] = coordtotile[0];
				coordinatetotile[3] = coordtotile[1];
			} 
			if (counter == 2) {
				if (grid[coordinatetotile[1]][coordinatetotile[0]] == grid[coordinatetotile[3]][coordinatetotile[2]]){
					array[coordinatetotile[1]][coordinatetotile[0]] = Integer.toString(grid[coordinatetotile[1]][coordinatetotile[0]]);
					// will store the value in the array coordinate of the first card if the card have matched
					array[coordinatetotile[3]][coordinatetotile[2]] = Integer.toString(grid[coordinatetotile[3]][coordinatetotile[2]]);
					// will store the value in the array variable if the second card is matched
				}
			}
			if (counter >= 2) {

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
							score1 ++;
							// score increases by one because the cards have matched
							System.out.println("Player 1 score is: "+ score1);
							// displayes the score

						}
						else if (!hasSameValue(selected)) {	// if the values were not the same
							selected.close();				// closes the tiles
							this.close();					// closes the second tile
						}
						
						selected = null;				// selected reset
						player = 2;

					});
				}
			}

			if (counter <=2 ) {
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
					while ((coordinate_array[0] == coordinate_array[2]) && (coordinate_array[1] ==coordinate_array[3] )) {
						coordinate_array = vsComputer.computer(grid,comp_mem,array, numRows, numCols, counter);
						// checks if the coordinate array are not all the same coordinate
					}
					
					}
	
					tiletocoordinate = tiletoCoordinate( coordinate_array[0] ,  coordinate_array[1],  coordinate_array[2],  coordinate_array[3]);
					// will convert the coordinate array tiles location into coordinates 
					if (counter == 2) {	
						Robot bot = new Robot();
						bot.mouseMove(0, 0); 

						bot.delay(400);
 				// will auto click this x and y axis
						click(tiletocoordinate[0], tiletocoordinate[1]);	// will click the first card picked
	
						if (grid[coordinate_array[1]][coordinate_array[0]] != 0) {
							comp_mem[coordinate_array[1]][coordinate_array[0]] = grid[coordinate_array[1]][coordinate_array[0]];
							// will store the value into the comp mem array if it is not 0
						}
						else {
							comp_mem[coordinate_array[1]][coordinate_array[0]] = 100;
							// will store the value into comp mem as 100 if it is 0
						}

					}
					if (counter == 1) {		// will check if counter is 1 and will prompt the click
						Robot bot = new Robot();
						bot.mouseMove(0, 0); 

						bot.delay(400);

						click(tiletocoordinate[2], tiletocoordinate[3]);		// will the second coordinate given by computer

						if (grid[coordinate_array[3]][coordinate_array[2]] != 0) {
							comp_mem[coordinate_array[3]][coordinate_array[2]] = grid[coordinate_array[3]][coordinate_array[2]];
							// will store the comp mem if it is not 0
						}
						else {
							comp_mem[coordinate_array[3]][coordinate_array[2]] = 100;
							// will store the comp mem as 100, if the value is not 0
						}
					}	
	
					if (counter == 1) {
						if (selected == null) {
							selected = null;
						//	selected.close();
						}
					if (counter == 1) {
						if (grid[coordinate_array[1]][coordinate_array[0]] == grid[coordinate_array[3]][coordinate_array[2]]) {
							array[coordinate_array[1]][coordinate_array[0]] = Integer.toString(grid[coordinate_array[1]][coordinate_array[0]]);
							// will store the values into the array variable if they match
							array[coordinate_array[3]][coordinate_array[2]] = Integer.toString(grid[coordinate_array[3]][coordinate_array[2]]);
							// will store the values into the array variable if they match
						}
					}
					}

					if (counter <= 1 && player == 2) {
	
						if (selected == null ) {	
							selected = this;
							open(() -> {});
						}
						else {
							open(() -> {
								if (hasSameValue(selected)) {
									score2 ++;	// will increase the score by 1
									System.out.println("Computer 1 score is: "+ score2);
								}
								else if (!hasSameValue(selected)) {
									selected.close();	// will close the selected tile
									this.close();		// will close the current tile
								}
							
								selected = null;		// resets the selected to null
								counter = 4;			// resets the counter to 2
								player =1; 
							});
						}
					}
			}
				
			}
		
		public void click(int x, int y) throws AWTException{	// the auto click bot
			Robot bot = new Robot();
			bot.mouseMove(0, 0); 

			bot.mouseMove(x, y);  				// will auto click this x and y axis
			bot.mouseMove(x, y);  				// will auto click this x and y axis
			bot.mouseMove(x, y);  				// will auto click this x and y axis
			bot.mouseMove(x, y);  				// will auto click this x and y axis
			bot.mousePress(InputEvent.BUTTON1_MASK);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
		}

        public boolean isOpen() {
            return text.getOpacity() == 1.00;			// will change the opacity of the tile to 1, making it completely blank
        }

        public void open(Runnable actions) {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.2), text);
            ft.setToValue(1.0);
            ft.setOnFinished(e -> actions.run());
            ft.play();
        }

        public void close() {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.2), text);
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
