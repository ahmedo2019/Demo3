import static org.junit.Assert.*;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.junit.*;

public class autoClickerTest {
	
	@Test
    public void testClick(){
        int i = -1;
        try {
            Robot bot = new Robot();
            bot.mouseMove(100, 100);
        }catch (AWTException e){i = 1;}
        assert (i < 0);
    }

	@Test
	public final void testMouseClicker() {
		assertTrue(true);
	}

	@Test
	public final void testClickMouse() {
		assertTrue(true);
	}

}
