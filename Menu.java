import java.awt.*;
import javax.swing.JPanel;

import javafx.scene.layout.Pane;
import sun.applet.Main;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;

public class Menu extends JPanel {
	
	//instance variables
	boolean difficultyCPU = false;
	boolean started = false;
	boolean started2 = false;
	
	public Menu() {
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// lowers position of the buttons
		add(Box.createVerticalStrut(280));
		
		
		//draws button 1 and its properties.
		 CustomButton button1 = new CustomButton("Player vs Player");
		 button1.setAlignmentX(LEFT_ALIGNMENT);
		 button1.setAlignmentY(CENTER_ALIGNMENT);
		 
		 // button 1 pressed
		 button1.addActionListener(new AbstractAction() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 PVP(); // this will take us to pvp. 
			 }
			 
		 });
		 
		 
		 
		 
		 //draws button 2 and its positions
		 CustomButton button2 = new CustomButton("Player vs Computer");
		 button2.setAlignmentX(RIGHT_ALIGNMENT);
		 button2.setAlignmentY(CENTER_ALIGNMENT);
		 
		 // button 2 pressed
		 button2.addActionListener(new AbstractAction() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				
				PVC(); //This will take us to pvc.
				
				
			 }
			 
		 });
		 
		 
		 
		 
		 //draws button 3 and its positions
		 CustomButton button3 = new CustomButton("EXIT");
		 button3.setAlignmentX(LEFT_ALIGNMENT);
		 button3.setAlignmentY(BOTTOM_ALIGNMENT);
		 
		 // button 3 pressed
		 button3.addActionListener(new AbstractAction() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 
				 System.exit(0); // We need the computer gui from adarsha.
			 }
			 
		 });
		 
		 
		 
		 // adds buttons 1 and buttons 2 to game. 
		 add(button1);
		 add(button2);
		 add(button3);
			 
		//add(Box.createVerticalGlue());
		
		try {
			URL url = Menu.class.getResource("Poketheme.wav");
			AudioInputStream audio = AudioSystem.getAudioInputStream(url);
			Clip music = AudioSystem.getClip();
			music.open(audio);
			music.loop(-1);
		} catch (Exception ex) {}	
	}
	
	// Player vs Player 
	public void PVP() {
		removeAll();
		repaint();
		started = true;
	}
	
	public void PVC() {
		removeAll();
		started2 = true; 
		repaint();
		
	}
	
	 
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (started) {
			//System.out.println("Hello!");
			Main2.main(null);
			
		
		}
		else if (started2) {
			System.out.println("Player vs Computer");
		}
		else {
			System.out.println("");
			/*
			g.drawImage(new ImageIcon(Menu.class.getResource("PokeMenu.png")).getImage(), 0, 0, 840, 840, this);
			g.drawImage(new ImageIcon(Menu.class.getResource("PokemonCard.png")).getImage(), 300, 300, 240, 240, this);
			g.drawImage(new ImageIcon(Menu.class.getResource("Pikachu.png")).getImage(), 550, 300, 240, 240, this);
			*/
		}
	}

}
