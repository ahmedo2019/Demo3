import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.omg.CORBA.portable.InputStream;

import com.sun.glass.ui.Timer;
import com.sun.prism.paint.Paint;

public class gui extends Application {

    //The Tile object is used for this GUI to show the function of the game.
    private Card selected = null;
    private int numOfClicks = 2;
    private boolean animationOn = false;
    public ImageView imageView;
    boolean loaded = false;
   private BufferedImage image;
   boolean buttonBoolean = false;
   
	Image img = new Image("file:C:\\Users\\Aaron\\eclipse-workspace\\CardMatchMemoryGame\\bin\\loopmusicjavaupdated\\PokemonCard.png");
	Image img2 = new Image("file:C:\\Users\\Aaron\\eclipse-workspace\\CardMatchMemoryGame\\bin\\loopmusicjavaupdated\\Bulbasaur.png");
	Image img3 = new Image("file:C:\\Users\\Aaron\\eclipse-workspace\\CardMatchMemoryGame\\bin\\loopmusicjavaupdated\\Charmander.png");
	Image img4 = new Image("file:C:\\Users\\Aaron\\eclipse-workspace\\CardMatchMemoryGame\\bin\\loopmusicjavaupdated\\Squirtle.png");
	Image imgX = img3;
	
	Image BackgroundImage = new Image("file:C:\\Users\\Aaron\\eclipse-workspace\\CardMatchMemoryGame\\src\\PokeMenu.png");
	
    //This will introduce the Grid creation into the tiles so that each tile is made according
    //to the Grid.java file.
	
	
	public static int[][] createGrid() {
		int numRows =4;
		int numCols =4; 
		int []Count = new int[numCols * numRows];
		int [][]grid = new int[numRows][numCols]; 
		int counter = 0;
		for( int i = 0; i < ( ( numRows * numCols)); i+= 2){ 
			if (i == 0 || i == 1)
				Count[0] = 0;
				Count[1] = 0;
			if (i >= 2)
				Count[i] = i/2;
				Count[i+1] = i/2;
		}
		
		// shuffles the above list
		Random random = new Random();

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
    
	//Using the Grid creation method createGrid to make the tiles array to display the text
	//The value of each tile has been displayed from the value of the grid.
	public Parent createContent() {
        Pane gameWindow = new Pane();
        gameWindow.setPrefSize(820,820);
	
    	/*Initializing the number of Cards with each pair of cards so for the 
    	 *easy level there would be a total of 16 cards with 8 cards that show 
    	 *the same image which can be matched.
    	 */
		int numCols = 4;
		int numRows = 4;
		
		int[][] grid = createGrid();  
			List<Card> cards = new ArrayList<>();
		
			
			for(int y = 0; y<numRows; y++){
				for(int e = 0; e<numCols; e++){
					cards.add(new Card(String.valueOf(grid[y][e]), grid));
					//tiles.add(new Tile(String.valueOf(c)));
				}
			}

			
			for (int i = 0; i < cards.size(); i++) {
				Card tile = cards.get(i);
				tile.setTranslateX(200 * (i % numRows));
				tile.setTranslateY(200 * (i / numRows));
				gameWindow.getChildren().add(tile);
            	 
            	
            	/*
            	if (buttonBoolean == true) {
            	
				ImageView imgGame2 = new ImageView(img2);
            	imgGame2.setImage(img2);
            	imgGame2.setFitHeight(100);
            	imgGame2.setFitWidth(100);
            	tile.getChildren().add(imgGame2);
            	}
            	else if (buttonBoolean == false)
            	{
    				ImageView imgGame = new ImageView(img);
                	imgGame.setImage(img);
                	imgGame.setFitHeight(100);
                	imgGame.setFitWidth(100);
                	tile.getChildren().add(imgGame);
            	}
            	else
            	{
            		System.out.println("fuck");
            	}
            	*/
		}
		return gameWindow;
	}
	
	
	/*This method is the stage that sets the window itself. It calls the method
	 * to run the createContent method to create the grid of tiles with the text.
	 * 
	 */

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Memory Game");
        /*
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 200);
    	
        Image img = new Image("file:C:\\Users\\Aaron\\eclipse-workspace\\CardMatchMemoryGame\\src\\PokemonCard.png", 100, 150, true, true);
        ImageView iv = new ImageView(img);
        
        Ellipse ellipse = new Ellipse();
        ellipse.setFill(Color.WHITE);
        
        ellipse.radiusXProperty().bind(scene.widthProperty().divide(2));
        ellipse.radiusYProperty().bind(scene.heightProperty().divide(2));
    	
        root.getChildren().addAll(ellipse, iv);
        primaryStage.setScene(scene);
        */
        primaryStage.setScene(new Scene(createContent()));
       
        primaryStage.show();
        //primaryStage.setFullScreen(true);
        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> Platform.exit());

       
        
    }
    
    /*
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 400, 200);
        
        Image img = new Image("file:PokemonCard.png");
        ImageView iv = new ImageView(img);
        
        Ellipse ellipse = new Ellipse();
        ellipse.setFill(Color.WHITE);
        
        ellipse.radiusXProperty().bind(scene.widthProperty().divide(2));
        ellipse.radiusYProperty().bind(scene.heightProperty().divide(2));
        
        root.getChildren().addAll(ellipse, iv);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    */
	
    //A method to show the card display, flipping functionality, equality checking and returning the proper results.
    public class Card extends StackPane {

        private Text text = new Text();
		private ImageView imgGame = new ImageView(img);
		private ImageView imgGame2 = new ImageView(img2);
		private ImageView imgGameX = new ImageView(imgX);
		private ImageView BGImg = new ImageView(BackgroundImage);

        private ImageView imageview; 
        Rectangle border = new Rectangle(200, 200);

        //A method to show the initial display of the tiles and the border with different colors.
        //Positioning of the tiles and the font of the card value has also been set by this method.
        public Card(String firstCard, int[][] grid) {
        	
            //border.setStroke(Color.MEDIUMTURQUOISE);
            border.setFill(Color.BLACK);
            
            //BufferedImage image = new Image("Jigglypuff.png");
        	
            BGImg.setImage(BackgroundImage);
            
        
        	text.setText(firstCard);
        	imgGame2.setAccessibleText(firstCard);
        	
			//ImageView imgGame = new ImageView(img);
        	imgGame.setImage(img);
        	imgGame.setFitHeight(100);
        	imgGame.setFitWidth(100);
        	
			//ImageView imgGame2 = new ImageView(img2);
        	imgGame2.setImage(img2);
        	imgGame2.setFitHeight(100);
        	imgGame2.setFitWidth(100);
        	
        	System.out.println("grid[0][0] is:" + grid[0][0]);
        	System.out.println("grid[0][1] is: " + grid[0][1]);
        	
        	if (grid[0][0] == 0) {
        		imgGameX.setImage(img2);
        	}
        	if (grid[0][0] == 1)
        	{
        		imgGameX.setImage(img3);
        	}
        	if (grid[0][0] == 2)
        	{
        		imgGameX.setImage(img4);
        	}
        	if (grid[0][0] == 3)
        	{
        		imgGameX.setImage(img2);
        	}
        	if (grid[0][0] == 4)
        	{
        		imgGameX.setImage(img3);
        	}
        	if (grid[0][0] == 5)
        	{
        		imgGameX.setImage(img4);
        	}
        	if (grid[0][0] == 6)
        	{
        		imgGameX.setImage(img2);
        	}
        	if (grid[0][0] == 7)
        	{
        		imgGameX.setImage(img3);
        	}
        	if (grid[0][0] == 8)
        	{
        		imgGameX.setImage(img4);
        	}
        	
        	
        	if (grid[0][1] == 0) {
        		imgGameX.setImage(img2);
        	}
        	if (grid[0][1] == 1)
        	{
        		imgGameX.setImage(img3);
        	}
        	if (grid[0][1] == 2)
        	{
        		imgGameX.setImage(img4);
        	}
        	if (grid[0][1] == 3)
        	{
        		imgGameX.setImage(img2);
        	}
        	if (grid[0][1] == 4)
        	{
        		imgGameX.setImage(img3);
        	}
        	if (grid[0][1] == 5)
        	{
        		imgGameX.setImage(img4);
        	}
        	if (grid[0][1] == 6)
        	{
        		imgGameX.setImage(img2);
        	}
        	if (grid[0][1] == 7)
        	{
        		imgGameX.setImage(img3);
        	}
        	if (grid[0][1] == 8)
        	{
        		imgGameX.setImage(img4);
        	}
        	
        	
        	imgGameX.setFitHeight(100);
        	imgGameX.setFitWidth(100);
        	
        	
            text.setFont(Font.font(100));
            text.setStroke(Color.RED
            	);
            
            System.out.println(grid[0][0]);
        	
            setAlignment(Pos.CENTER);
            getChildren().addAll(border, imgGame, imgGameX);

            setOnMouseClicked(this::cardClickedByMouse);
            hideCard();
        }

        /*This method reads the MouseEvent and looks at the user clicks and performs what is necessary
         * in the code to check for equality between the two cards.
         * 
         * If the cards are equal then the cards will become the same color as to show equality.
         * If the cards are NOT equal then the cards will go back to its "flipping" mechanism.
         * 
         */
       
        
        public void cardClickedByMouse(MouseEvent event) {
            if ((cardClicked() || numOfClicks == 0)){
            	return;
            }
            numOfClicks--;

            if (selected == null && animationOn == false)  {
                selected = this;
                System.out.println("click 1");
           
                
                showCard(() -> {});
            }
            else {
                showCard(() -> {
                    if (!equalCards(selected) && animationOn == true) {
                        selected.hideCard();
                        this.hideCard();
                      
                    }
                    else if (equalCards(selected) && animationOn == false) {
                		selected.border.setFill(Color.MEDIUMTURQUOISE);
                		this.border.setFill(Color.MEDIUMTURQUOISE);
                    }
                    
                    else if (equalCards(selected) && animationOn == true) {
                    	buttonBoolean = true;
                    	
                		selected.hideCard();
                		this.hideCard();
                	
                    }
                    
                    selected = null;
                    numOfClicks = 2;
                });
            }
        
        }
        

        //This method will check if the card is open and return the opacity to 1 to indicate that the card
        //has been opened as to show the user not to pick the same card again.
        public boolean cardClicked() {
            //return text.getOpacity() == 1;
            return imgGameX.getOpacity() == 0.5;
        }

        /*This method will fade the card to show it and runs the action to show the card to view it separately
         * from the background stroke color.
         */
        public void showCard(Runnable action) {
        	animationOn = true;
        	System.out.println("animation is on!");
        	System.out.println("It is" + imgGameX);
            FadeTransition flip = new FadeTransition(Duration.seconds(0.5), imgGameX);
            flip.setToValue(1);
            flip.setOnFinished(e -> action.run());
            flip.play();
            
        }

        //This method will close the card by performing a fade transition to hide the card
        public void hideCard() {
        	animationOn = false;
        	System.out.println("hidecard is on!");
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), imgGameX);
            ft.setToValue(0);
            ft.play();
        }

        //This method checks if both the values of the text are the same. EQUALITY TO CHECK TO HIDE CARDS OR REVEL THEM.
        
        public boolean equalCards(Card secondCard) {
            //return text.getText().equals(secondCard.text.getText());
        	return imgGameX.getImage().equals(secondCard.imgGameX.getImage());
           
        }
    }
    
  

    /*This is called as the GUI.java class extends to the Application class in
     * java.fx so the launch(args) is also extended by the Application class.
     */
    public static void main(String[] args) {
    	
    	launch(args);
    	
    }
   
    public static void main1(String[] args) {
    	// will execute the game by calling the class Multiplay from the file Multiplay
    	// which will run the game inputs and sequences 
    	gui.main(args);
    	//Multiplayer.Multiplay();

    	}
    
    
    
    
}