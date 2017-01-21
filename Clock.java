/**
 * This class draws the analog clock and all its parts such as hour hand,
 * minute hand, second hand, day of week display, numbers around dial and tick marks.
 */


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.Stroke;
import java.awt.BasicStroke;



public class Clock 
{
	private double xTopLeft;	
	private double yTopLeft;
	private double radius;
	private static int xCentre;
	private static int yCentre;
	private Color color;
	private Color dialColor = Color.GRAY;
	private Color secColor = Color.RED;
	private Color circleColor= Color.WHITE;
	private static final Stroke STROKE_CIRCLE = new BasicStroke(4.0f);
	private static final Stroke STROKE_SQUARE = new BasicStroke(2.0f);
	private static final Stroke STROKE_MARK_THICK = new BasicStroke(6.0f);
	private static final Stroke STROKE_MARK_THIN = new BasicStroke(1.0f);
	private static final Stroke STROKE_DAYBOX= new BasicStroke(1.1f);
	private static final Stroke STROKE_HMHAND = new BasicStroke(3.0f);
	private static final Stroke STROKE_SECHAND = new BasicStroke(1.8f);

	/**
	 * This is no argument constructor.
	 */
	public Clock()
	{
		
	}
	
	/**
	 * This constructor takes some parameters and sets them to variables.
	 * @param xTopLeft, x-coordinate of top left point of the outer square and circle.
	 * @param yTopLeft, y-coordinate of top left point of the outer square and circle.
	 * @param radius, radius of clock's circle.
	 * @param xCentre, x-coordinate of the centre of clock.
	 * @param yCentre, y-coordinate of the centre of clock.
	 * @param color, main color used in drawing the clock.
	 */
	public Clock(double xTopLeft, double yTopLeft, double radius, int xCentre, int yCentre, Color color)
	{
		this.xTopLeft = xTopLeft;
		this.yTopLeft = yTopLeft;
		this.radius = radius;
		this.xCentre = xCentre;
		this.yCentre = yCentre;
		this.color = color;
	}
	
	/**
	 * This method returns x-coordinate for centre of clock.
	 * @return x-coordinate for centre of clock
	 */
	public int getXCentre()
	{
		return xCentre;
	}
	
	/**
	 * This method returns y-coordinate for centre of clock.
	 * @return y-coordinate for centre of clock.
	 */
	public int getYCentre()
	{
		return yCentre;
	}
	
	/**
	 * This method draws the outermost square. It fills the square with
	 * colour first and then draws the outline.
	 * @param g2
	 */
	public void drawSquare(Graphics2D g2)
	{
		g2.setColor(dialColor);
		Rectangle2D.Double square = new Rectangle2D.Double(xTopLeft, yTopLeft, 2 * radius, 2 * radius);
		g2.fill(square);
		g2.setColor(color);
		g2.setStroke(STROKE_SQUARE);
		g2.draw(square);
	}
	
	/**
	 * This method draws the clock circle. It fills the circle with
	 * colour first and then draws the outline.
	 * @param g2
	 */
	public void drawCircle(Graphics2D g2)
	{
		g2.setColor(circleColor);
		Ellipse2D.Double circle = new Ellipse2D.Double(xTopLeft, yTopLeft, 2 * radius, 2 * radius);
		g2.fill(circle);
		g2.setColor(color);
		g2.setStroke(STROKE_CIRCLE);
		g2.draw(circle);	
	}
	
	/**
	 * This method draws the tick marks around the clock circle. The main markers are thicker and
	 * darker and the others are lighter and smaller. 
	 * @param g2
	 */
	public void drawDial(Graphics2D g2)
	{
		for (int index = 0; index < 360; index += 6)
		{
			if (index % 5 == 0)	
			{
				g2.setColor(color);
				g2.setStroke(STROKE_MARK_THICK);
				Line2D.Double tMarkThick = new Line2D.Double(		
						xCentre + (radius - 15) * Math.cos(Math.toRadians(index)), 
						yCentre + (radius - 15) * Math.sin(Math.toRadians(index)), 
						xCentre + (radius - 4) * Math.cos(Math.toRadians(index)),
						yCentre + (radius - 4) * Math.sin(Math.toRadians(index)));
				g2.draw(tMarkThick);
			}
			else	//Draws smaller tick markers every 6 degree angle except for every
			{		
				g2.setColor(dialColor);
				g2.setStroke(STROKE_MARK_THIN);
				Line2D.Double tMarkThin = new Line2D.Double(
						xCentre + (radius - 10) * Math.cos(Math.toRadians(index)), 
						yCentre + (radius - 10) * Math.sin(Math.toRadians(index)), 
						xCentre + (radius - 2) * Math.cos(Math.toRadians(index)),
						yCentre + (radius - 2) * Math.sin(Math.toRadians(index)));
				g2.draw(tMarkThin);
			}
		}
	}
	
	/**
	 * This method draws the numbers around the clock circle.The centre position of clock
	 * needs to be moved in order to draw numbers in proper positions.
	 * @param g2
	 */
	public void drawNum(Graphics2D g2)
	{
		int intNum = 3;
		
		for (int index = 0; index < 330; index += 30)	
		{
			String strNum = Integer.toString(intNum);	
			g2.setFont(new Font("Dialog", Font.BOLD, 30));	
			g2.setColor(color);
			
			if (intNum >= 3 && intNum <= 9)		
			{
				g2.drawString(strNum, (int) ((xCentre - 10) + (radius - 35) * Math.cos(Math.toRadians(index))), 
						(int) ((yCentre + 11) + (radius - 35) * Math.sin(Math.toRadians(index))));
			}
			if (intNum >= 10 && intNum <= 12)
			{
				index -= 1;
				g2.drawString(strNum, (int) ((xCentre - 10) + (radius - 35) * Math.cos(Math.toRadians(index))), 
						(int) ((yCentre + 11) + (radius - 35) * Math.sin(Math.toRadians(index))));
			}
			if (intNum == 13)	
			{
				intNum = 1;
				strNum = strNum.replace("13", "1");
			}
			if (intNum == 1 || intNum == 2)		
			{
				index += 1;
				g2.drawString(strNum, (int) ((xCentre - 10) + (radius - 35) * Math.cos(Math.toRadians(index))), 
						(int) ((yCentre + 11) + (radius - 35) * Math.sin(Math.toRadians(index))));
			}
			intNum++;
		}
	}
	
	/**
	 * This method draws the day of week and the square that contains it. 
	 * @param g2
	 */
	public void drawDay(Graphics2D g2)
	{
		ClockComponent component = new ClockComponent();
		String day = component.getDay();	
		String upperDay = "";
		
		if (!(day.equals("Wednesday") || day.equals("WEDNESDAY")))	
			upperDay = day.toUpperCase();                          
		else
			upperDay = "Wednesday";		
		
		g2.setColor(Color.LIGHT_GRAY);	
		Rectangle2D.Double dayBox = new Rectangle2D.Double(xCentre + 52, yCentre - 15, 100, 30);
		g2.fill(dayBox);	
		
		g2.setColor(color);		
		g2.setStroke(STROKE_DAYBOX);
		g2.draw(dayBox);
		
		g2.setColor(color);
		g2.setFont(new Font("Dialog", Font.PLAIN, 18));
		g2.drawString(upperDay, xCentre + 55, yCentre + 7);		
	}
	
	/**
	 * This method draws hour, minute and second hand. It uses polygon class to draw hour
	 * and minute hands with pointy shape. The second hand is drawn as straight line.  
	 * @param g2
	 */
	public void drawTime(Graphics2D g2)
	{
		ClockComponent component = new ClockComponent();
		String strHour = component.getHour();	
		String strMin = component.getMin();
		String strSec = component.getSec();
		int intHour = Integer.parseInt(strHour);	
		int intMin = Integer.parseInt(strMin);
		int intSec = Integer.parseInt(strSec);
		
		g2.setColor(color);		
		g2.setStroke(STROKE_HMHAND);
		
		//Draws the hour hand. Calculates 3 points in order to draw a long triangle shape.
		int[] xPointsHour = {(int)(xCentre + ((radius / 1.7) * Math.cos(Math.toRadians((intHour - 3) + (intMin / 60.0)) * 30))),
				(int)(xCentre + ((radius / 21) * Math.cos(Math.toRadians((intHour - 12) + (intMin / 60.0)) * 30))),
				(int)(xCentre + ((radius / 21) * Math.cos(Math.toRadians((intHour - 6) + (intMin / 60.0)) * 30)))};
		int[] yPointsHour = {(int)(yCentre + ((radius / 1.7) * Math.sin(Math.toRadians((intHour - 3) + (intMin / 60.0)) * 30))),
				(int)(yCentre + ((radius / 21) * Math.sin(Math.toRadians((intHour - 12) + (intMin / 60.0)) *30))),
				(int)(yCentre + ((radius / 21) * Math.sin(Math.toRadians((intHour - 6) + (intMin / 60.0)) * 30)))};
		g2.fillPolygon(xPointsHour, yPointsHour, 3);
		
		//Draws the minute hand. Calculates 3 points in order to draw a long triangle shape.
		int[] xPointsMin = {(int)(xCentre + ((radius / 1.15) * Math.cos(Math.toRadians((intMin - 15) + (intSec / 60.0)) * 6))),
				(int)(xCentre + ((radius / 24) * Math.cos(Math.toRadians((intMin - 60) + (intSec / 60.0)) * 6))),
				(int)(xCentre + ((radius / 24) * Math.cos(Math.toRadians((intMin - 30) + (intSec / 60.0)) * 6)))};
		int[] yPointsMin = {(int)(yCentre + ((radius / 1.15) * Math.sin(Math.toRadians((intMin - 15) + (intSec / 60.0)) * 6))),
				(int)(yCentre + ((radius / 24) * Math.sin(Math.toRadians((intMin - 60) + (intSec / 60.0)) * 6))),
				(int)(yCentre + ((radius / 24) * Math.sin(Math.toRadians((intMin - 30) + (intSec / 60.0)) * 6)))};
		g2.fillPolygon(xPointsMin, yPointsMin, 3);
		
		//Draws the second hand. The second hand is drawn as straight line and its colour is red. 
		g2.setColor(secColor);
		g2.setStroke(STROKE_SECHAND);
		Point2D.Double centre = new Point2D.Double(xCentre, yCentre);
		Point2D.Double secEnd = new Point2D.Double(
				xCentre + ((radius - 21) * Math.cos(Math.toRadians((intSec - 15) * 6))),
				yCentre + ((radius - 21) * Math.sin(Math.toRadians((intSec - 15) * 6))));
		Line2D.Double secHand = new Line2D.Double(centre, secEnd);
		g2.draw(secHand);
	}
	
	/**
	 * This method draws the centre pivoting point circle.
	 * @param g2
	 */
	public void drawCap(Graphics2D g2)
	{
		g2.setColor(secColor);
		Ellipse2D.Double cap = new Ellipse2D.Double(xCentre - 11, yCentre - 11, 25, 25);
		g2.fill(cap);
		
		g2.setColor(color);
		g2.setStroke(STROKE_DAYBOX);
		g2.draw(cap);	
	}
	
	/**
	 * This method draws the name signature at the bottom of clock face. X and y coordinates and angle
	 * of signature changes for each string.
	 * @param g2
	 */
	public void drawName(Graphics2D g2)
	{
		String[] nameArray = {"K", "E", "V", "I", "N", " ", "P", "A", "R", "K"};
		
		g2.translate(xCentre - 92, yCentre + 173);	
		g2.setColor(color);
		g2.setFont(new Font("Dialog", Font.PLAIN, 12));	
		double angChange = 30;	
		int xPos = 0;	
		int yPos = 0;
		int index = 0;
		String letter;
	
		for ( ; index < 2; index++)		
		{
			letter = nameArray[index];
			AffineTransform turn = g2.getTransform();
			g2.rotate(Math.toRadians(angChange));	
			g2.drawString(letter, xPos, yPos);
			g2.setTransform(turn);
			xPos += 20;		
			yPos += 1;
			angChange -= 4.6;	
		}
		for ( ; index < 4; index++)		
		{
			letter = nameArray[index];
			AffineTransform turn = g2.getTransform();
			g2.rotate(Math.toRadians(angChange));
			g2.drawString(letter, xPos, yPos);
			g2.setTransform(turn);
			xPos += 21;
			yPos += 4;
			angChange -= 6.8;
		}
		for ( ; index < 6; index++)		
		{
			letter = nameArray[index];
			g2.drawString(letter, xPos - 5, yPos + 14);
			xPos += 21;
		}
		g2.translate(120, 22);	
		xPos = 0;				
		yPos = 0;
		angChange = -13;
		for ( ; index < 10; index++)	
		{
			letter = nameArray[index];
			AffineTransform turn = g2.getTransform();
			g2.rotate(Math.toRadians(angChange));
			g2.drawString(letter, xPos, yPos);
			g2.setTransform(turn);
			xPos += 20;
			yPos += 2;
			angChange -= 3.5;
		}
	}
}
