/**
 * This class puts the clock drawing on the frame by calling Clock class and
 * DigitalClock class. It also has two constructors, one taking in the current time
 * and the other taking in the manually entered time. It provides with getter methods
 * that gets the time for other classes to use. 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class ClockComponent extends JComponent
{
	private static String hour;		//Declare variables.
	private static String minute;
	private static String second;
	private static String amPm;
	private static String day;
	private static double width;
	private static double height;
	
	/**
	 * This constructor takes in the current time, AM/PM and day of week
	 * and sets them to variables.
	 */
	public ClockComponent()
	{
		Calendar calendar = Calendar.getInstance();					
		SimpleDateFormat hourFormat = new SimpleDateFormat("h");	
		hour = hourFormat.format(calendar.getTime());				
		SimpleDateFormat minFormat = new SimpleDateFormat("m");	
		minute = minFormat.format(calendar.getTime());
		SimpleDateFormat secFormat = new SimpleDateFormat("s");
		second = secFormat.format(calendar.getTime());
		SimpleDateFormat amPm = new SimpleDateFormat("a");
		ClockComponent.amPm = amPm.format(calendar.getTime());
		SimpleDateFormat day = new SimpleDateFormat("EEEE");
		ClockComponent.day = day.format(calendar.getTime());
	}
	
	/**
	 * This constructor is used when manually input time and day are used.
	 * @param hour entered by user.
	 * @param minute entered by user.
	 * @param second entered by user.
	 * @param amPm entered by user.
	 * @param day entered by user.
	 */
	public ClockComponent(String hour, String minute, String second, String amPm, String day)
	{
		ClockComponent.hour = hour;
		ClockComponent.minute = minute;
		ClockComponent.second = second;
		ClockComponent.amPm = amPm;
		ClockComponent.day = day;
	}
	
	/**
	 * This method returns the hour.
	 * @return hour
	 */
	public String getHour()
	{
		return hour;
	}
	
	/**
	 * This method return the minute.
	 * @return minute
	 */
	public String getMin()
	{
		return minute;
	}
	
	/**
	 * This method returns the second.
	 * @return second
	 */
	public String getSec()
	{
		return second;
	}
	
	/**
	 * This method returns either AM or PM.
	 * @return AM OR PM
	 */
	public String getAmPm()
	{
		return amPm;
	}
	
	/**
	 * This method returns the day of week
	 * @return day of week
	 */
	public String getDay()
	{
		return day;
	}
	
	/**
	 * This method puts each parts of drawn clock on the frame.
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);	
																									
		int xMid = getWidth() / 2;	
		int yMid = getHeight() / 2;
		double radius = 200;
		
		ClockComponent.width = getWidth();
		ClockComponent.height = getHeight();
		
		//Passes arguments for Clock class.
		Clock clock = new Clock(xMid - 2 * radius / 2, yMid - 2 * radius / 2,  radius, xMid, yMid, Color.BLACK);
	
		clock.drawSquare(g2);	//Draws the outermost square.
		clock.drawCircle(g2);	//Draws the circle that forms the clock.
		clock.drawDial(g2);		//Draws the tick marks in clock face.
		clock.drawNum(g2);		//Draws the hour numbers in clock face.
		clock.drawDay(g2);		//Draws the day of week in clock face
		clock.drawTime(g2);		//Draws the hour, minute and second hand.
		clock.drawCap(g2);		//Draws the pivoting point in the centre of clock face.
	
		
		DigitalClock dClock = new DigitalClock();	
		dClock.drawBox(g2);		//Draws the outer rectangle of digital clock
		dClock.drawDigits(g2);	//Draws the numbers of digital clock.
		clock.drawName(g2);
		
	}
	
	/**
	 * This method returns the width of the frame.
	 * @return width of frame
	 */
	public double getTheWidth()
	{
		return width;
	}
	
	/**
	 * This method returns the height of the frame.
	 * @return height of frame
	 */
	public double getTheHeight()
	{
		return height;
	}
	
	
}
 