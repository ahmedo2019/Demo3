/**
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

public class graphics extends Application {
    public  final int numCard = 8;
    public  final int row = 4;

    public Tile selected = null;
    public int counter = 2;
	public  int[][] createGrid() {
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
    public  Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(600, 600);
	
		int numCols = 4;
		int numRows = 4;
		
		int[][] grid =createGrid();  
			List<Tile> tiles = new ArrayList<>();
			for(int y = 0; y<numRows; y++){
				for(int e = 0; e<numCols; e++){
					tiles.add(new Tile(String.valueOf(grid[y][e])));
					//tiles.add(new Tile(String.valueOf(c)));
				}
			}

			Collections.shuffle(tiles);

			for (int i = 0; i < tiles.size(); i++) {
				Tile tile = tiles.get(i);
				tile.setTranslateX(150 * (i % row));
				tile.setTranslateY(150 * (i / row));
				root.getChildren().add(tile);
			


		}
		return root;
	}
	

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }
	
    public class Tile extends StackPane {

        public Text text = new Text();

        public Tile(String value) {
            Rectangle border = new Rectangle(150, 150);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setText(value);
            text.setFont(Font.font(30));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(this::handleMouseClick);
            close();
        }

		public void handleMouseClick(MouseEvent event) {
			if (isOpen() || counter == 0) {
				return;
			}
			counter--;

			if (selected == null) {
				selected = this;
				open(() -> {});
			}
			else {
				open(() -> {
					if (!hasSameValue(selected)) {
						selected.close();
						this.close();
						String y = selected.getId();
						String x = this.getId();

					}

					selected = null;
					counter = 2;
				});
			}
		}

        public boolean isOpen() {
            return text.getOpacity() == 1;
        }

        public void open(Runnable action) {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), text);
            ft.setToValue(1);
            ft.setOnFinished(e -> action.run());
            ft.play();
        }

        public void close() {
            FadeTransition ft = new FadeTransition(Duration.seconds(0.5), text);
            ft.setToValue(0);
            ft.play();
        }

        public boolean hasSameValue(Tile other) {
            return text.getText().equals(other.text.getText());
        }
    }

    public static void main(String[] args) {

        launch(args);
		
    }
}
**/