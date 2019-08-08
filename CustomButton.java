import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class CustomButton extends JButton implements MouseListener {
	
	// instance variables
	Dimension size = new Dimension(250, 150);
	
	boolean hover = false;
	boolean click = false;
	
	String text = "";
	
	public CustomButton(String text) {
		setVisible(true);
		setFocusable(true);
		setContentAreaFilled(false);
		setBorderPainted(false);
		
		this.text = text;
		
		addMouseListener(this);
	}
	
	// paint components
	@Override 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if (click) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 250, 150);
		}
		
		// colour form
		g.setColor(new Color(0, 50, hover ? 255 : 125));
		
		g.fillRect(0, 0, 250, 7);
		g.fillRect(0, 143, 250, 7);
		g.fillRect(0, 0, 7, 150);
		g.fillRect(243, 0, 7, 150);
		
		//changes colour of buttons
		g.setColor(new Color(0,250,154));
		
		g.fillRect(14,  14, 222, 122);
		
		g.setColor(Color.BLACK);
		
		g.setFont(Font.decode("arial-BOLD-24"));
		
		FontMetrics metrics = g.getFontMetrics();
		
		int width = metrics.stringWidth(text);
		
		g.drawString(text,  125 - width / 2, 75);
	}
	
	// returns the size of the buttons
	@Override 
	public Dimension getPreferredSize() {
		return size;
	}
	
	@Override 
	public Dimension getMaximumSize() {
		return size;
	}
	
	@Override 
	public Dimension getMinimumSize() {
		return size;
	}
	
	
	// we can set text with this method.
	public void setButtonText(String text) {
		this.text = text; 
	}
	
	public String getButtonText() {
		return text;
	}
	
	// checks for the mouse as it hovers onto the location of the button object. 
	@Override
	public void mouseEntered(MouseEvent e) {
		hover = true;
	}
	
	// checks if the mouse leaves the location of the button object.
	@Override
	public void mouseExited(MouseEvent e) {
		hover = false;
	}
	
	// checks that mouse is clicked. 
	@Override
	public void mousePressed(MouseEvent e) {
		click = true;
	}
	
	// check for mouse release.
	@Override
	public void mouseReleased(MouseEvent e) {
		click = false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

}
