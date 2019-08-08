import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

/* This mouse click class is allows the computer to automatically click two tiles
 * from the compgraphics class and matches or chooses tiles base on the value from 
 * the grid which is converted to coordinates. The coordinates is then used to
 * as the X and Y coordinates to be clicked by the click method.
 */

public class autoClicker {
	
	private static Robot clickBot; //Making a variable for the computer class
	private int delay; //setting a delay time for the clickMouse
	
	public autoClicker() { //A method for auto-clicking rather than manually clicking 
		try {
			clickBot = new Robot();
		} catch (Exception e) {
			e.printStackTrace();
		}
		delay = 300; //delay of 300 milliseconds.
	}
	
	public static Robot click(int x, int y) throws AWTException{ //This is the click method
		clickBot = new Robot(); //This is the new robot instance that is created
		clickBot.mouseMove(x, y); //Click mouse on the X and Y coordinates given by the computer
		clickBot.mousePress(InputEvent.BUTTON1_MASK); //This is will click the left click mouse 
		clickBot.mouseRelease(InputEvent.BUTTON1_MASK); //This will release the left click mouse
		return clickBot;
	}
	
	public void clickMouse (int button) { //This will click the mouse based on the input
		try {
			clickBot.mousePress(button); //Press the left mouse.
			clickBot.delay(300); //delay of 300 ms
			clickBot.mouseRelease(button); //releasing the mouse
			clickBot.delay(delay); //delay to the instance delay
		} catch (Exception e) { //An exception is also made.
			e.printStackTrace();
		}
	}

}
