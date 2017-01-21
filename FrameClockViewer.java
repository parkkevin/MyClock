/**
 * This class creates JFrame that the clock can be added onto
 * for display.
 */

import javax.swing.JFrame;



public class FrameClockViewer 
{
	/**
	 * This is the main method that creates frame that clock can be added.
	 * It sets the frame's width, height, default location of the window in the screen and
	 * window resizability. This method can also passes arguments for manual time setting.
	 * @param void
	 */
	public static void main(String[] args)
	{
		final int FRAME_WIDTH = 600;
		final int FRAME_HEIGHT = 600;
			
		JFrame frame = new JFrame();
		
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setTitle("The Clock");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		frame.setLocationRelativeTo(null);	
		frame.setResizable(true);
		
		ClockComponent component = new ClockComponent();	
	
		//ClockComponent component = new ClockComponent("4", "37", "1", "AM", "WEDNESDAY");		
                                                                                             
		frame.add(component);
		frame.setVisible(true);
	}
}
