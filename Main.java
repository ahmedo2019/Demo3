
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import javax.swing.JPanel;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Main extends JFrame {
	
	public Main() {
		JPanel panel = new JPanel();
		
		setTitle("Tutorial");
		
		// Creates the window.
        Pane gameWindow = new Pane();
        gameWindow.setPrefSize(802,802);
		setSize(840, 840);
		setResizable(false);
		 
		// Calls menu 
		dispose();
		Menu menu = new Menu();
		add(menu);
		dispose();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	//runs the main class.
	public static void main(String[] args) {
		new Main();
		
	}
	
	
	
	
}