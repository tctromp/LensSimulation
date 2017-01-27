package org.trompgames.Lens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

import org.trompgames.utils.Location;

public class LensPanel extends JPanel{

	private LensFrame frame;
	
	private int midX = -1;
	private int midY = -1;	
	public LensPanel(LensFrame frame) {

		this.frame = frame;
		midX = frame.getWidth()/2;
		midY = frame.getHeight()/2;
	}
	
	private int xOffset = 0;
	private int yOffset = 0;
	
	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.WHITE);
		g2d.clearRect(0, 0, frame.getWidth(), frame.getHeight());
		
		//g2d.drawOval(100, 100, 50, 50);
		//g2.draw(new Ellipse2D.Double(x, y, rectwidth, rectheight));
		g2d.setColor(Color.BLACK);
		int focus = 0;
		for(Lens lens : frame.getHandler().getLenses()){
			g2d.draw(new Ellipse2D.Double(lens.getLocation().getX() - lens.getWidth()/2, lens.getLocation().getY() - lens.getHeight()/2, lens.getWidth(), lens.getHeight()));	
			focus = lens.getFocus();
		}
		
		//xOffset += 1;
		
		
		int xLoc = frame.getHandler().getSimObject().getRayOriginLocation().getX() + xOffset;
		int yLoc = frame.getHandler().getSimObject().getRayOriginLocation().getY() + yOffset;
		
		
		//g2d.drawLine(0, this.getHeight()/2, this.getWidth(), this.getHeight()/2);
		
		
		//g2d.setColor(Color.RED);
		
		
		//MIDDLE
		//g2d.drawRect(midX, midY, 0, 0);
		
		//X
		g2d.drawLine(0, midY, frame.getWidth(), midY);
		
		//Y
		g2d.drawLine(midX, 0, midX, frame.getHeight());
		
		//Object
		g2d.setColor(Color.DARK_GRAY);
		SimObject obj = frame.getHandler().getSimObject();
		//NOTE: Height is subracted due to java's cordinate system
		g2d.drawLine(xLoc, obj.getLocation().getY(), xLoc, yLoc);
		
		//Focus
		g2d.setColor(Color.MAGENTA);
		Location focus1Loc = new Location(midX - focus, midY);
		Location focus2Loc = new Location(midX + focus, midY);
		g2d.drawRect(midX - focus - 3, midY - 3, 6, 6);
		g2d.drawRect(midX + focus - 3, midY - 3, 6, 6);


		
		//Line1
		g2d.setColor(Color.RED);
		g2d.drawLine(xLoc, yLoc, midX, yLoc);
		
		double m = (1.0 * focus2Loc.getY() - yLoc)/(1.0 * focus2Loc.getX() - midX);
		int x = frame.getWidth();
		int y = (int) (m*(x - midX) + yLoc);
		
		g2d.drawLine(midX, yLoc, x, y);

		
		//Line2
		g2d.setColor(Color.BLUE);
		m = (1.0 * focus1Loc.getY() - yLoc)/(1.0 * focus1Loc.getX() - xLoc);
		x = midX;
		y = (int) (m*(x - focus1Loc.getX()) + focus1Loc.getY());
		
		g2d.drawLine(xLoc, yLoc, x, y);
		g2d.drawLine(x, y, frame.getWidth(), y);
		
		
		//Line3
		g2d.setColor(new Color(0, 255, 0));
		m = (1.0 * midY - yLoc)/(1.0 * midX - xLoc);
		
		x = frame.getWidth();
		y = (int) (m*(x - midX) + midY);
		
		
		g2d.drawLine(xLoc, yLoc, x, y);

		//g2d.draw(new Ellipse2D.Double(200, 200, 10, 25));
		
		
	}
	
	
}
