import java.awt.*;
import javax.swing.JPanel;
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

public class PVPSelect extends JPanel {
	
	boolean difficultyCPU = false;
	boolean started = false;
	boolean started2 = false;

	
	public PVPSelect() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		System.out.println("works");
		
		//draws button 1 and its properties.
		 CustomButton button1 = new CustomButton("EASY");
		 button1.setAlignmentX(CENTER_ALIGNMENT);
		 //button1.setAlignmentY(TOP_ALIGNMENT);
		 button1.setBounds(40, 100, 100, 60);
		 
		 // button 1 pressed
		 button1.addActionListener(new AbstractAction() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				 PVP(); // this will take us to pvp. 
				 
			 }
			 
		 });
		 

		 
		 
		 
		 //draws button 2 and its positions
		 setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		 CustomButton button2 = new CustomButton("MEDIUM");
		 button2.setAlignmentX(CENTER_ALIGNMENT);
		 //button2.setAlignmentY(CENTER_ALIGNMENT);
		 
		 // button 2 pressed
		 button2.addActionListener(new AbstractAction() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
				PVE();
				
			 }
			 
		 });
		 
		 
		 
		 
		 //draws button 3 and its positions
		 //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		 CustomButton button3 = new CustomButton("HARD");
		 button3.setAlignmentX(CENTER_ALIGNMENT);
		 //button3.setAlignmentY(BOTTOM_ALIGNMENT);
		 
		 // button 3 pressed
		 button3.addActionListener(new AbstractAction() {
			 @Override
			 public void actionPerformed(ActionEvent e) {
			
				 PVP(); // We need the computer gui from adarsha.
			 }
			 
		 });
		 
		 
		 
		 // adds buttons 1 and buttons 2 to game. 
		 add(button1);
		 add(button2);
		 add(button3);
			 
		//add(Box.createVerticalGlue());
		
	}	 
	
	// Player vs Player 
	public void PVP() {
		removeAll();
		started = true;
		repaint();
		
	}
	
	public void PVE() {
		removeAll();
		started2 = true;
		repaint();
		
	}
	
	 
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (started) {
			g.drawImage(new ImageIcon(Menu.class.getResource("PokemonCard.png")).getImage(), 0, 0, 640, 480, this);
			gui.main(null);
			
		}
		else if (started2) {
			System.out.println("Yipee!");
		}
		else
		{
			
			g.drawImage(new ImageIcon(Menu.class.getResource("PokeMenu.png")).getImage(), 0, 0, 840, 840, this);
			g.drawImage(new ImageIcon(Menu.class.getResource("Jigglypuff.png")).getImage(), 550, 300, 240, 240, this);
		}
	}
	
	
	
}
