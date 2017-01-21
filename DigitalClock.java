/**
 * This class draws the digital clock below analog clock. Its draws hour, minute,
 * second as well as AM and PM. 
 */


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.Stroke;
import java.awt.BasicStroke;


public class DigitalClock
{
	private double xTopLeft;	
	private double yTopLeft;
	private double width;
	private double height;
	private int xCentre;
	private int yCentre;
	private Color color = Color.RED;
	private Color boxColor = Color.WHITE;
	private static final Stroke BOX_STROKE = new BasicStroke(2.0f);
	
	Clock clock = new Clock();		
	ClockComponent component = new ClockComponent();
	
	/**
	 * This constructor sets width, height of frame and x and y coordinates
	 * of centre of frame.
	 */
	public DigitalClock()
	{
		width = component.getTheWidth();
		height = component.getTheHeight();
		xCentre = clock.getXCentre();
		yCentre = clock.getYCentre();
	}
	
	/**
	 * This method draws the square that contains the digital time.
	 * @param g2
	 */
	public void drawBox(Graphics2D g2)
	{
		g2.setColor(boxColor);
		Rectangle2D.Double box = new Rectangle2D.Double(xCentre - 84, yCentre + 220, 168, 35);
		g2.fill(box);	
		g2.setColor(color);
		g2.setStroke(BOX_STROKE);
		g2.draw(box);
	}
	
	/**
	 * This method draws the digital time.
	 * @param g2
	 */
	public void drawDigits(Graphics2D g2)
	{
		String hour = component.getHour();	
		String minute = component.getMin();
		String second = component.getSec();
		String amPm = component.getAmPm();
		
		g2.setFont(new Font("Monospace", Font.BOLD, 22));	
		g2.setColor(color);
		
		int add = 35;
		for (int index = 0; index < 2; index++)	
		{
			g2.drawString(":", (xCentre - 84) + add, (yCentre + 245));
			add += 42;
		}
		
		if (hour.length() == 1)		
			g2.drawString("0" + hour, (xCentre - 84) + 4, (yCentre + 246));	
		else
			g2.drawString(hour, (xCentre - 84) + 4, (yCentre + 246));
		
		if (minute.length() == 1)	
			g2.drawString("0" + minute, (xCentre - 84) + 43, (yCentre + 246));
		else
			g2.drawString(minute, (xCentre - 84) + 43, (yCentre + 246));
		
		if (second.length() == 1)	
			g2.drawString("0" + second, (xCentre - 84) + 85, (yCentre + 246));
		else
			g2.drawString(second, (xCentre - 84) + 85, (yCentre + 246));
		
		g2.drawString(amPm, (xCentre - 84) + 129, (yCentre + 246));	
	}
}
